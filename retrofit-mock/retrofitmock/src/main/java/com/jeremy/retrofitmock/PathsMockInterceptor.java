package com.jeremy.retrofitmock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Request;

/**
 * Created by liaohailiang on 2019-05-16.
 */
public class PathsMockInterceptor extends MockInterceptor {

    private final Set<String> pathSet;

    public PathsMockInterceptor(String[] paths) {
        pathSet = new HashSet<>();
        if (paths != null && paths.length > 0) {
            pathSet.addAll(Arrays.asList(paths));
        }
    }

    public PathsMockInterceptor(List<String> paths) {
        pathSet = new HashSet<>();
        if (paths != null && paths.size() > 0) {
            pathSet.addAll(paths);
        }
    }

    @Override
    public boolean accept(Request request) {
        return pathSet.contains(request.url().encodedPath());
    }
}
