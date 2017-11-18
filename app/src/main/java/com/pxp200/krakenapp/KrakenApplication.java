package com.pxp200.krakenapp;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pxp200.krakenapp.api.KrakenApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class KrakenApplication extends Application {
    public static final String CONTENT_TYPE = "content-type";
    public static final String APPLICATION_JSON = "application/json";
    public static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    public static final String BASE_URL = "http://192.241.225.133/";
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
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okBuilder.addInterceptor(logInterceptor);

        Interceptor jsonInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException { //https://www.youtube.com/watch?v=JDG2m5hN1vo
                Request req = chain.request();
                if(req.body() != null && req.body().contentType().equals(DEFAULT_MEDIA_TYPE)) {
                    Request newReq = req.newBuilder()
                            .addHeader(CONTENT_TYPE,APPLICATION_JSON)
                            .build();
                    return chain.proceed(newReq);
                } else {
                    return chain.proceed(req);
                }
            }
        };
        okBuilder.addInterceptor(jsonInterceptor);

        OkHttpClient okHttp = okBuilder.build();


        Retrofit retro = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttp)
                .build();

        krakenApi = retro.create(KrakenApi.class);
    }
}
