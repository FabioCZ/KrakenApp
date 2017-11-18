package com.pxp200.krakenapp;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pxp200.krakenapp.api.KrakenApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class KrakenApplication extends Application {

    public static final String BASE_URL = "http://pizza.com/";
    private KrakenApi krakenApi;

    public static KrakenApi getKrakenApi(Context context) {
        KrakenApplication app = (KrakenApplication) context.getApplicationContext();
        if(app.krakenApi == null) {
            app.initApi();
        }
        return app.krakenApi;
    }

    private void initApi() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(interceptor);

        OkHttpClient okHttp = okBuilder.build();


        Retrofit retro = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttp)
                .build();

        krakenApi = retro.create(KrakenApi.class);
    }
}
