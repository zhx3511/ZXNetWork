package com.zx.networkdemo.app;

import android.app.Application;

import com.zx.network.base.INetWorkInit;
import com.zx.network.base.NetworkApi;
import com.zx.networkdemo.BuildConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new MyINetWorkApi(this));
    }


    private class MyINetWorkApi implements INetWorkInit {

        private Application application;

        public MyINetWorkApi(App app) {
            application = app;
        }

        @Override
        public boolean isDebug() {
            return BuildConfig.DEBUG;
        }

        @Override
        public String getAppVersionName() {
            return BuildConfig.VERSION_NAME;
        }

        @Override
        public String getAppVersionCode() {
            return String.valueOf(BuildConfig.VERSION_CODE);
        }

        @Override
        public Application getApplicationContext() {
            return application;
        }
    }


}
