package com.xf.sherlock.request;

import com.xf.sherlock.bean.CheckImage;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by TC on 2015/12/29.
 */
public interface CheckImageService {
    @FormUrlEncoded
    @POST("otn/login/loginAysnSuggest")
    Observable<CheckImage> checkImage(@Field("randCode") String randCode, @Field("rand") String rand);

}
