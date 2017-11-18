package com.pxp200.krakenapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public interface KrakenApi {

    @GET("user/{username}")
    Call<String> getUserData(@Path("username") String username);
}
