package com.zx.networkdemo.services;


import com.zx.network.beans.LotteryNetRequestResult;
import com.zx.networkdemo.beans.LotteryType;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LotteryServices {

    @GET("/lottery/types/")
    Observable<LotteryNetRequestResult<List<LotteryType>>> getLottery(@Query("key") String key);

}
