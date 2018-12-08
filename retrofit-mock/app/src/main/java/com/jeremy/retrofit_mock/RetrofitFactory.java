package com.jeremy.retrofit_mock;

import com.jeremy.retrofitmock.MockInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liaohailiang on 2018/12/4.
 */
public final class RetrofitFactory {

    private RetrofitFactory() {
    }

    public static Retrofit createRetrofit(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(new OkHttpClient.Builder()
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit createMockRetrofit(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new MockInterceptor())
                        .build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
