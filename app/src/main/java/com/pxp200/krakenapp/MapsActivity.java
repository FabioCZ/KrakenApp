package com.pxp200.krakenapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pxp200.krakenapp.BBD.BBDActivity;
import com.pxp200.krakenapp.Manager.Manager;
import com.pxp200.krakenapp.Storage.UsernamePreference;
import com.pxp200.krakenapp.Tutorial.TutorialActivity;
import com.pxp200.krakenapp.api.KrakenApi;
import com.pxp200.krakenapp.model.Building;
import com.pxp200.krakenapp.model.BuildingInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    MediaPlayer musicPlayer;
    int soundVolume = 50;
    List<BuildingInfo> buildingMarkers = new ArrayList<>();
    private KrakenApi krakenApi;
    private Manager manager;

    @Override
    protected void onResume() {
        super.onResume();

        musicPlayer = MediaPlayer.create(this, R.raw.prelude);
        musicPlayer.setVolume(100, soundVolume);
        musicPlayer.start();
        musicPlayer.setLooping(true);
    }


    @BindView(R.id.map_username)
    TextView username;

    @OnClick(R.id.fab_tutorial)
    public void tutorial() {
        musicPlayer.stop();

        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.fab_bbd)
    public void bbd() {
        musicPlayer.stop();

        Intent intent = new Intent(this, BBDActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.fab_logout)
    public void logout() {
        UsernamePreference.clear(this);
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.fab_buildings)
    public void showBuildingMenu() {
        Intent intent = new Intent(this, BuildingsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_upgrades)
    public void showUpgradeMenu() {
        Intent intent = new Intent(this, UpgradesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_resources)
    public void showResourcesMenu() {
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);
        username.setText(UsernamePreference.get(this));

        krakenApi = KrakenApplication.getKrakenApi(this);
        manager = KrakenApplication.getManager(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the mMap to location user
                    .zoom(17)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mMap.setMyLocationEnabled(true);
//            addAllMarkers();
            updateBuildingsNearby();
        }
    }

    public void updateBuildingsNearby() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        } catch (SecurityException e) {
            //go away
        }
        int lat = (int)(location.getLatitude() * 1000);
        int longi = (int)(location.getLongitude() * 1000);

        krakenApi.getBuildingsInArea(lat, longi).enqueue(new Callback<ArrayList<Building>>() {
            @Override
            public void onResponse(Call<ArrayList<Building>> call, Response<ArrayList<Building>> response) {
                clearMarkers();
                ArrayList<Building> nearbyBldg= response.body();
                ArrayList<BuildingInfo> bldgInfo = manager.staticBuildings;

                if(nearbyBldg == null) {
                    return;
                }
                for(Building b : nearbyBldg) {
                    for(BuildingInfo bi : bldgInfo) {
                        if(bi.getId().equals(b.getStaticId())) {
                            mMap.addMarker(b.getMarkerOptions(bi));
                        }
                    }
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateBuildingsNearby();
                    }
                }, 30000); //every 30 sec?
            }

            @Override
            public void onFailure(Call<ArrayList<Building>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng));
    }

    public void clearMarkers() {
        mMap.clear();
    }
}
