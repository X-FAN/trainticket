package com.xf.sherlock.request;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by TC on 2016/1/8.
 */
public interface QueryService {
    @GET("leftTicket/init")
    Observable<Void> getCookie();
}
