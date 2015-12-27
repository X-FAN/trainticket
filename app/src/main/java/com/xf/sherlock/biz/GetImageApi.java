package com.xf.sherlock.biz;

import android.graphics.Bitmap;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Administrator on 2015/11/15.
 */
public interface GetImageApi {
    @GET("/")
    void getImage(Callback<Bitmap> callback);
}
