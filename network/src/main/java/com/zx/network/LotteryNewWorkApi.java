package com.zx.network;

import com.zx.network.base.NetworkApi;
import com.zx.network.beans.LotteryNetRequestResult;
import com.zx.network.errorhandler.ExceptionHandle;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

public class LotteryNewWorkApi extends NetworkApi {


    private static volatile LotteryNewWorkApi newsNetWorkApi;


    public static LotteryNewWorkApi getInstence() {
        if (newsNetWorkApi == null) {
            synchronized (LotteryNewWorkApi.class) {
                if (newsNetWorkApi == null) {
                    newsNetWorkApi = new LotteryNewWorkApi();
                }
            }
        }
        return newsNetWorkApi;
    }

    @Override
    protected Interceptor getInterceptor() {
        return null;
    }

    @Override
    protected Function getAppErrorHandler() {
        return new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                if (o instanceof LotteryNetRequestResult && ((LotteryNetRequestResult) o).getError_code() != 0) {
                    ExceptionHandle.ServerException err = new ExceptionHandle.ServerException();
                    err.code = ((LotteryNetRequestResult) o).getError_code();
                    err.message = ((LotteryNetRequestResult) o).getReason();
                    throw err;
                }
                return o;
            }
        };
    }

    @Override
    public String getReleaseBaseUrl() {
        return "http://apis.juhe.cn/";
    }

    @Override
    public String getDabugBaseUrl() {
        return "http://apis.juhe.cn/";
    }
}
