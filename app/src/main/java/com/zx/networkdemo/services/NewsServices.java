package com.zx.networkdemo.services;


import com.zx.network.beans.NewsNetRequestResult;
import com.zx.networkdemo.beans.Toutiao;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsServices {

    @GET("/toutiao/index?")
    Observable<NewsNetRequestResult<List<Toutiao>>> gettoutiao(@Query("type") String type, @Query("key") String key);

}
