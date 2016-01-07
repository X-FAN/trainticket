package com.xf.sherlock.request;

import android.support.annotation.NonNull;

import com.xf.sherlock.bean.Login;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST("login/loginAysnSuggest")
    Observable<Login> login(@Field("loginUserDTO.user_name") @NonNull String account, @Field("userDTO.password") @NonNull String password, @Field("randCode") @NonNull String randCode);
}
