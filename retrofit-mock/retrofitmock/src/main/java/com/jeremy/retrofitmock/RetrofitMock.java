package com.jeremy.retrofitmock;

import android.content.Context;

import com.jeremy.retrofitmock.data.MockDataManager;

/**
 * Created by liaohailiang on 2018/12/7.
 */
public final class RetrofitMock {

    private RetrofitMock() {
    }

    public static void init(Context context, String path) {
        MockDataManager.get().init(context.getApplicationContext(), path);
    }
}
