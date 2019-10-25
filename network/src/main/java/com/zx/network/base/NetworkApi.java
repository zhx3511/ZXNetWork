package com.zx.network.base;

import android.annotation.SuppressLint;

import com.zx.network.callBack.OnHttpCallBack;
import com.zx.network.errorhandler.ExceptionHandle;
import com.zx.network.observer.BaseObserver;
import com.zx.network.environmental.IEnvironmentalScience;
import com.zx.network.interceptors.RequestInterceptor;
import com.zx.network.interceptors.ResponseInterceptor;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 由于要兼容多域名，多环境，多模块所以必须要抽象类
 */
public abstract class NetworkApi implements IEnvironmentalScience {

    protected static INetWorkInit mINetWorkInit;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();
    private String mBaseUrl;
    private OkHttpClient mOkHttpClient;

    private static boolean isRelases = true;


    public NetworkApi() {
        mBaseUrl = isRelases ? getReleaseBaseUrl() : getDabugBaseUrl();
    }


    public static void init(INetWorkInit iNetWorkInit) {
        mINetWorkInit = iNetWorkInit;
    }

    /**
     * 当主域名{@link com.zx.network.base.NetworkApi#mBaseUrl}和Retrofit接口类一致时缓存Retrofit，避免重复初始化
     *
     *
     * @param service
     * @return
     */
    protected Retrofit getRetrofit(Class service) {
        if (retrofitHashMap.get(mBaseUrl + service.getName()) != null) {
            return retrofitHashMap.get(mBaseUrl + service.getName());
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitHashMap.put(mBaseUrl + service.getName(), retrofit);
        return retrofit;
    }


    /**
     * 创建OKHttpClient
     * 注册相关拦截器
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {

        if (mOkHttpClient == null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            if (getInterceptor() != null) {
                okHttpClient.addInterceptor(getInterceptor());
            }
            okHttpClient.addInterceptor(new RequestInterceptor(mINetWorkInit));
            okHttpClient.addInterceptor(new ResponseInterceptor());
            if (mINetWorkInit != null && mINetWorkInit.isDebug()) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClient.addInterceptor(loggingInterceptor);
            }
            mOkHttpClient = okHttpClient.build();
        }
        return mOkHttpClient;
    }

    /**
     * RxJava 处理切换线程、处理错误等
     */
    private ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .map(getAppErrorHandler())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * 获取一个Retrofit对象
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T serviceApi(Class<T> service) {
        return getRetrofit(service).create(service);
    }


    /**
     * 切换线程、处理返回结果
     *
     * @param o
     * @param mOnHttpCallBack
     * @param <T>
     * @return
     */
    @SuppressLint("CheckResult")
    public <T> Observer toSubscribe(Observable<T> o, final OnHttpCallBack<T> mOnHttpCallBack) {
        Observer observer = o.compose(schedulersTransformer).subscribeWith(new BaseObserver<T>() {
            @Override
            public void onSuccess(T t) {
                mOnHttpCallBack.onSuccessful(t);
            }

            @Override
            public void onFailure(ExceptionHandle.ResponeThrowable e) {
                mOnHttpCallBack.onFaild(e.message);

            }
        });

        return observer;
    }


    /**
     * 提供于子类添加拦截器入口
     *
     * @return
     */
    protected abstract Interceptor getInterceptor();

    /**
     * 提供于子类错误处理
     *
     * @return
     */
    protected abstract Function getAppErrorHandler();

}
