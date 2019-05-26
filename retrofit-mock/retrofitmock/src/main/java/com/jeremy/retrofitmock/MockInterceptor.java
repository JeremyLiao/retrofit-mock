package com.jeremy.retrofitmock;

import com.jeremy.retrofitmock.data.MockDataManager;
import com.jeremy.retrofitmock.data.ResponseInfo;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liaohailiang on 2018/12/4.
 */
public abstract class MockInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!accept(chain.request())) {
            return chain.proceed(chain.request());
        }
        String path = chain.request().url().encodedPath();
        Map<String, ResponseInfo> infoMap = MockDataManager.get().getInfoMap();
        if(infoMap == null){
            throw new IllegalStateException("Please make sure that the mock file is specified in the src/assets directory.");
        }
        if (infoMap.containsKey(path)) {
            ResponseInfo responseInfo = infoMap.get(path);
            Response.Builder builder = new Response.Builder()
                    .message(responseInfo.getMessage())
                    .code(responseInfo.getCode())
                    .protocol(Protocol.get(responseInfo.getProtocol()))
                    .request(chain.request())
                    .body(new MockResponseBody(chain.request()));
            Map<String, String> header = responseInfo.getHeader();
            if (header != null && header.size() > 0) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    builder.header(entry.getKey(), entry.getValue());
                }
            }
            return builder.build();
        } else {
            return chain.proceed(chain.request());
        }
    }

    abstract public boolean accept(Request request);
}
