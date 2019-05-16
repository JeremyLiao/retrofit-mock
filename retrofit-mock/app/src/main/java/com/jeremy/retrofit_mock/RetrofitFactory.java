package com.jeremy.retrofit_mock;

import com.jeremy.retrofitmock.SimpleMockInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liaohailiang on 2018/12/4.
 */
public final class RetrofitFactory {

    private RetrofitFactory() {
    }

    public static Retrofit createMockRetrofit(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new SimpleMockInterceptor(true))
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
