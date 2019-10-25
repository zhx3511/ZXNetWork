package com.zx.network.interceptors;

import com.zx.network.base.INetWorkInit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器目的： 添加公共的请求头
 */
public class RequestInterceptor implements Interceptor {

    private INetWorkInit mINetWorkInit;

    public RequestInterceptor(INetWorkInit mINetWorkInit) {
        this.mINetWorkInit = mINetWorkInit;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
//        builder.addHeader("apiClientVersion", mINetWorkInit.getAppVersionCode());
//        builder.addHeader("apiClientVersionName", mINetWorkInit.getAppVersionName());
        return chain.proceed(builder.build());
    }
}
