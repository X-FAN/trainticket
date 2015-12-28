package com.xf.sherlock.request;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface LoginService {
    //    "https://kyfw.12306.cn/otn/login/loginAysnSuggest"
    @FormUrlEncoded
    @POST("otn/login/loginAysnSuggest")
    Observable<Object> login(@Field("loginUserDTO.user_name") String account, @Field("userDTO.password") String password, @Field("randCode_validate:") String validate, @Field("myversion") String version, @Field("randCode") String randcode);
}
