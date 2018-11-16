package com.tanuj.stocklibrary.apiClient;

import com.tanuj.stocklibrary.models.Company;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
        interface need for retrofit to define apis
* */
public interface StockApi {

    @GET("stock/{stock-name}/company")
    Call<Company> companyDetail(@Path("stock-name") String stockName);

    @GET("stock/{stock-name}/price")
    Call<String> stockPrice(@Path("stock-name") String stockName);
}
