package com.pxp200.krakenapp.api;

import com.pxp200.krakenapp.model.Building;
import com.pxp200.krakenapp.model.BuildingInfo;
import com.pxp200.krakenapp.model.Resource;
import com.pxp200.krakenapp.model.User;
import com.pxp200.krakenapp.model.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public interface KrakenApi {

    @POST("users")
    Call<UserResponse> getUser(@Body User user);

    @GET("static/resources")
    Call<ArrayList<Resource>> getStaticResources();

    @GET("static/structures")
    Call<ArrayList<BuildingInfo>> getStaticBuildings();

    @GET("structures/{lat}/{long}")
    Call<ArrayList<Building>> getBuildingsInArea(@Path("lat")int lat, @Path("long")int longitude);
}
