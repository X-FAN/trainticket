package com.xf.sherlock.request;

import android.support.annotation.NonNull;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by TC on 2016/1/8.
 */
public interface QueryService {
    @GET("leftTicket/init")
    Observable<Void> getCookie();

    //"https://kyfw.12306.cn/otn/%s?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT";
    @GET("queryT?purpose_codes=ADULT")
    Observable<String> getTrainTicketResult(  @Query("leftTicketDTO.train_date") @NonNull String date, @Query("leftTicketDTO.from_station") @NonNull String fromStation, @Query("leftTicketDTO.to_station") @NonNull String toStation);
}
