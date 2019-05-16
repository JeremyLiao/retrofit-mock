package com.jeremy.retrofitmock;

import okhttp3.Request;

/**
 * Created by liaohailiang on 2019-05-16.
 */
public class SimpleMockInterceptor extends MockInterceptor {

    private final boolean enableMock;

    public SimpleMockInterceptor(boolean enableMock) {
        this.enableMock = enableMock;
    }

    @Override
    public boolean accept(Request request) {
        return enableMock;
    }
}
