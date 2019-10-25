package com.zx.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zx.network.LotteryNewWorkApi;
import com.zx.network.NewsNetWorkApi;
import com.zx.network.beans.LotteryNetRequestResult;
import com.zx.network.beans.NewsNetRequestResult;
import com.zx.network.callBack.OnHttpCallBack;
import com.zx.networkdemo.beans.LotteryType;
import com.zx.networkdemo.beans.Toutiao;
import com.zx.networkdemo.services.LotteryServices;
import com.zx.networkdemo.services.NewsServices;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsNetWorkApi.getInstence().toSubscribe(NewsNetWorkApi.getInstence().serviceApi(NewsServices.class).gettoutiao("top", "28ae2eb3e23ae6sb4e072c8bcc44f6af5"), new OnHttpCallBack<NewsNetRequestResult<List<Toutiao>>>() {
                    @Override
                    public void onSuccessful(NewsNetRequestResult<List<Toutiao>> response) {
                        Log.d("Tag", response.toString());
                    }

                    @Override
                    public void onFaild(String errorMsg) {
                        Log.d("Tag", errorMsg);

                    }
                });

            }
        });

        findViewById(R.id.ext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LotteryNewWorkApi.getInstence().toSubscribe(LotteryNewWorkApi.getInstence().serviceApi(LotteryServices.class)
                        .getLottery("789ee2c7e98e19d2f2462c0cbbe8c8b0"), new OnHttpCallBack<LotteryNetRequestResult<List<LotteryType>>>() {
                    @Override
                    public void onSuccessful(LotteryNetRequestResult<List<LotteryType>> response) {

                        Log.d("Tag", response.toString());
                    }

                    @Override
                    public void onFaild(String errorMsg) {
                        Log.d("Tag", errorMsg);

                    }
                });
            }
        });

    }
}
