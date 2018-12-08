package com.jeremy.retrofitmock;


import com.jeremy.retrofitmock.data.MockDataManager;
import com.jeremy.retrofitmock.data.ResponseInfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

/**
 * Created by liaohailiang on 2018/12/4.
 */
public class MockResponseBody extends ResponseBody {

    private static final String DEFAULT_RESPONSE = "{\"code\":200}";
    private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    private final Request request;

    public MockResponseBody(Request request) {
        this.request = request;
    }

    @Override
    public MediaType contentType() {
        String path = request.url().encodedPath();
        Map<String, ResponseInfo> infoMap = MockDataManager.get().getInfoMap();
        if (infoMap.containsKey(path)) {
            return MediaType.parse(infoMap.get(path).getContentType());
        } else {
            return DEFAULT_MEDIA_TYPE;
        }
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(Okio.source(inputStream()));
    }

    private InputStream inputStream() {
        String path = request.url().encodedPath();
        Map<String, ResponseInfo> infoMap = MockDataManager.get().getInfoMap();
        if (infoMap.containsKey(path)) {
            return new ByteArrayInputStream(infoMap.get(path).getBodyAsString().getBytes());
        } else {
            return new ByteArrayInputStream(DEFAULT_RESPONSE.getBytes());
        }
    }
}
