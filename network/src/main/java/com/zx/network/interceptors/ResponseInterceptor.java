package com.zx.network.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * 拦截器作用：计算请求时间
 */
public class ResponseInterceptor implements Interceptor {
    private static final String TAG = ResponseInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        long reqTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());

        Log.d(TAG, "responseTime====>" + (System.currentTimeMillis() - reqTime));

        return response;
    }
}
