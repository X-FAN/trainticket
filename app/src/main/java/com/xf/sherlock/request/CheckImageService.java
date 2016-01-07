package com.xf.sherlock.request;

import android.support.annotation.NonNull;

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
    @POST("passcodeNew/checkRandCodeAnsyn")
    Observable<CheckImage> checkImage(@Field("randCode") @NonNull String randCode, @Field("rand") @NonNull String rand);

}
