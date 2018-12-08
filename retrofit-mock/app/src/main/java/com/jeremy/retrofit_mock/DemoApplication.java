package com.jeremy.retrofit_mock;

import android.app.Application;

import com.jeremy.retrofitmock.RetrofitMock;

/**
 * Created by liaohailiang on 2018/12/7.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitMock.init(this, "mock_demo.json");
    }
}
