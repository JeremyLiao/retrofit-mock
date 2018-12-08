package com.jeremy.retrofit_mock.api;

import com.jeremy.retrofit_mock.bean.TestBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hailiangliao on 2017/12/11.
 */

public interface DemoApi {
    @GET("/api/getBean")
    Call<TestBean> getBean();
}
