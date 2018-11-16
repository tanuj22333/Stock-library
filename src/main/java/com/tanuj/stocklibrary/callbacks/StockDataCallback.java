package com.tanuj.stocklibrary.callbacks;

import com.tanuj.stocklibrary.models.Company;


/*
    interface used to provide callback to library user
* */
public interface StockDataCallback {

    void onSuccess(Company company);

    void onFailure();
}
