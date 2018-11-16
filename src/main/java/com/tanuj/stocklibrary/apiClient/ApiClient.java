package com.tanuj.stocklibrary.apiClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Retrofit used as a api client to asynchronously hit web-services
*
* */
public class ApiClient {

    static private StockApi instance = null;

    public static final String BASE_URL = "https://api.iextrading.com/1.0/";

//    synchronized used to provide thread safe access
    public static synchronized StockApi getInstance() {

        if (instance == null) {

//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//            OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
//            httpClient.addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(httpClient.build())
                    .build();

            instance = retrofit.create(StockApi.class);
        }

        return instance;
    }

//    to maintain singleton properly
    private ApiClient() {

    }
}
