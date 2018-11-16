package com.tanuj.stocklibrary;

import com.tanuj.stocklibrary.apiClient.ApiClient;
import com.tanuj.stocklibrary.callbacks.StockDataCallback;
import com.tanuj.stocklibrary.models.Company;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 *  This is a singleton class used to provide stock data. We need to provide access
 *  of internet in manifest file of our application, since all data fetch from remote
 *  back-end.
 *
 *  Retrofit is used as http-client to hit web-services.
 * */
public class StockDataProvider {

    private static StockDataProvider dataProvider = new StockDataProvider();

    private StockDataProvider() {
    }

//    no need to put synchronized here since instance is already created
//    with multi-threading safety in mind
    public static StockDataProvider getInstance() {
        return dataProvider;
    }

//    in this method we made a api call and get stock price from server if call is
//    successful then we hit the second api to get some other data otherwise
//    we send api failure through callback
    public void getData(final String stockName, final StockDataCallback stockDataCallback) {
        ApiClient.getInstance().stockPrice(stockName)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String price = response.body();
                        getCompanyDetail(price, stockName, stockDataCallback);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        onApiFailure(stockDataCallback);
                        t.printStackTrace();
                    }
                });
    }

//    in this api hit we get company related information like company name, company website
//    C.E.O ect, then combine information with price we got in last api call and provide
//    data through callback otherwise we provide error through callback
    private void getCompanyDetail(final String price, String stockName, final StockDataCallback stockDataCallback) {
        ApiClient.getInstance().companyDetail(stockName)
                .enqueue(new Callback<Company>() {
                    @Override
                    public void onResponse(Call<Company> call, Response<Company> response) {
                        Company company = response.body();
                        if (company == null) {
                            onApiFailure(stockDataCallback);
                            return;
                        }
                        company.setPrice(price);
                        onApiSuccess(company, stockDataCallback);
                    }

                    @Override
                    public void onFailure(Call<Company> call, Throwable t) {
                        onApiFailure(stockDataCallback);
                        t.printStackTrace();
                    }
                });
    }

//    when both api hit successfully we get here and provide data back to
//    through callback
    private void onApiSuccess(Company company, StockDataCallback stockDataCallback) {
        if (stockDataCallback == null) {
            return;
        }

        stockDataCallback.onSuccess(company);
    }

//    if any api call failed due to any reason we get here and provide error through
//    callback
    private void onApiFailure(StockDataCallback stockDataCallback) {
        if (stockDataCallback == null) {
            return;
        }

        stockDataCallback.onFailure();
    }
}








