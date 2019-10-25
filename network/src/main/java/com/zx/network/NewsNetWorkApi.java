package com.zx.network;

import com.zx.network.base.NetworkApi;
import com.zx.network.beans.NewsNetRequestResult;
import com.zx.network.errorhandler.ExceptionHandle;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * 聚合数据
 * 新闻类 请求API子类
 */
public class NewsNetWorkApi extends NetworkApi {


    private static volatile NewsNetWorkApi newsNetWorkApi;


    public static NewsNetWorkApi getInstence() {
        if (newsNetWorkApi == null) {
            synchronized (NewsNetWorkApi.class) {
                if (newsNetWorkApi == null) {
                    newsNetWorkApi = new NewsNetWorkApi();
                }
            }
        }
        return newsNetWorkApi;
    }


    @Override
    protected Interceptor getInterceptor() { // 无需特殊头文件
        return null;
    }

    @Override
    protected Function getAppErrorHandler() { // 拦截错误处理
        return new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                if (o instanceof NewsNetRequestResult && ((NewsNetRequestResult) o).getError_code() != 0) {
                    ExceptionHandle.ServerException err = new ExceptionHandle.ServerException();
                    err.code = ((NewsNetRequestResult) o).getError_code();
                    err.message = ((NewsNetRequestResult) o).getReason();
                    throw err;
                }
                return o;
            }
        };
    }

    @Override
    public String getReleaseBaseUrl() {
        return "http://v.juhe.cn/";
    }

    @Override
    public String getDabugBaseUrl() {
        return "http://v.juhe.cn/";
    }
}
