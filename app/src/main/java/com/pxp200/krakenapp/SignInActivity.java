package com.pxp200.krakenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pxp200.krakenapp.Manager.Manager;
import com.pxp200.krakenapp.Storage.UsernamePreference;
import com.pxp200.krakenapp.api.KrakenApi;
import com.pxp200.krakenapp.model.BuildingInfo;
import com.pxp200.krakenapp.model.Resource;
import com.pxp200.krakenapp.model.User;
import com.pxp200.krakenapp.model.UserResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.sign_in_username) EditText usernameEdit;
    @BindView(R.id.sign_in_continue) Button continueButton;
    @BindView(R.id.sign_in_progress) ProgressBar progressBar;

    KrakenApi krakenApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        startService(new Intent(this, Manager.class));

        usernameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onContinueClick();
                    return true;
                }
                return false;
            }
        });

        krakenApi = KrakenApplication.getKrakenApi(this);

        if(UsernamePreference.exists(this)) {
            usernameEdit.setText(UsernamePreference.get(this));
            onContinueClick();
        }
    }

    @OnClick(R.id.sign_in_continue)
    public void onContinueClick() {
        final String name = usernameEdit.getText().toString();
        if(name == null || name.length() == 0) {
            Toast.makeText(this, "Yo, enter in a username", Toast.LENGTH_LONG).show();
        } else {
            continueButton.setEnabled(false);
            usernameEdit.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.animate();
            krakenApi.getUser(new User(name))
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            //set up resources
                            final UserResponse userRes = response.body();

                            krakenApi.getStaticBuildings().enqueue(new Callback<ArrayList<BuildingInfo>>() {
                                @Override
                                public void onResponse(Call<ArrayList<BuildingInfo>> call, Response<ArrayList<BuildingInfo>> response) {
                                    final Manager manager = KrakenApplication.getManager(SignInActivity.this);
                                    manager.staticBuildings = response.body();
                                    krakenApi.getStaticResources().enqueue(new Callback<ArrayList<Resource>>() {
                                        @Override
                                        public void onResponse(Call<ArrayList<Resource>> call, Response<ArrayList<Resource>> response) {
                                            manager.staticResources = response.body();
                                            manager.setInitialUser(userRes);
                                            finishLogin(userRes.getName());
                                        }

                                        @Override
                                        public void onFailure(Call<ArrayList<Resource>> call, Throwable t) {
                                            onLoginFail();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<ArrayList<BuildingInfo>> call, Throwable t) {
                                    onLoginFail();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            onLoginFail();
                        }
                    });
        }
    }

    void finishLogin(String username) {
        UsernamePreference.set(getApplicationContext(), username);
        Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFail() {
        progressBar.setVisibility(View.GONE);
        continueButton.setEnabled(true);
        usernameEdit.setEnabled(true);
        Toast.makeText(SignInActivity.this, "Error logging in", Toast.LENGTH_LONG).show();
    }
}
