package com.jeremy.retrofitmock.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liaohailiang on 2018/12/7.
 */
public final class AssetsUtil {

    private AssetsUtil() {
    }

    public static String getAssetsAsString(Context context, String path) {
        BufferedReader bufferedReader = null;
        try {
            StringBuilder buf = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(path), "UTF-8"));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            return null;
        } finally {
            close(bufferedReader);
        }
    }

    private static void close(Closeable c) {
        try {
            c.close();
        } catch (Exception e) {
        }
    }
}
