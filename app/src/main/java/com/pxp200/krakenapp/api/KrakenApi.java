package com.pxp200.krakenapp.api;

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


    /*
            KrakenApplication.getKrakenApi(this).getStaticResources()
                .enqueue(new Callback<ArrayList<Resource>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Resource>> call, Response<ArrayList<Resource>> response) {
                        Toast.makeText(SignInActivity.this, "stuff", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Resource>> call, Throwable t) {
                        Toast.makeText(SignInActivity.this, "no stuff", Toast.LENGTH_LONG).show();

                    }
                });
     */
}
