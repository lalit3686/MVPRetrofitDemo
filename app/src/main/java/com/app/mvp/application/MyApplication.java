package com.app.mvp.application;

import android.app.Application;

import com.app.mvp.retrofit.APIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by plalit on 8/12/2016.
 */
public class MyApplication extends Application {

    private static final boolean RETROFIT_HTTP_LOGGING_ENABLED = true;

    /**
     *
     * @return
     */
    private static OkHttpClient.Builder getHTTPLoggingInterceptor(){
        OkHttpClient.Builder httpClient = null;
        if(httpClient == null){
            httpClient = new OkHttpClient.Builder();
            if(RETROFIT_HTTP_LOGGING_ENABLED){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logging);
            }
        }
        return httpClient;
    }

    /**
     *
     * @return
     */
    public static APIService getJsonAPIService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHTTPLoggingInterceptor().build())
                .build();

        return retrofit.create(APIService.class);
    }

    /**
     *
     * @return
     */
    public static APIService getXmlAPIService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.thomas-bayer.com")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(getHTTPLoggingInterceptor().build())
                .build();

        return retrofit.create(APIService.class);
    }
}
