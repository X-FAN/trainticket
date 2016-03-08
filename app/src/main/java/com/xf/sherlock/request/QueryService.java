package com.xf.sherlock.request;

import android.support.annotation.NonNull;

import com.xf.sherlock.bean.TrainTicketResult;
import com.xf.sherlock.bean.UnfinishedOrder;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by TC on 2016/1/8.
 */
public interface QueryService {
    @GET("leftTicket/init")
    Observable<Void> getCookie();

    @GET("leftTicket/queryT?purpose_codes=ADULT")
    Observable<String> getTrainTicketResult(@Query("leftTicketDTO.train_date") @NonNull String date, @Query("leftTicketDTO.from_station") @NonNull String fromStation, @Query("leftTicketDTO.to_station") @NonNull String toStation);

    @GET("leftTicket/{requestUrl}")
    Observable<TrainTicketResult> getTrainTicketResult(@Path("requestUrl") @NonNull String requestUrl, @Query("leftTicketDTO.train_date") @NonNull String date, @Query("leftTicketDTO.from_station") @NonNull String fromStation, @Query("leftTicketDTO.to_station") @NonNull String toStation, @Query("purpose_codes") @NonNull String type);

    //查询未完成订单
    @GET("queryOrder/queryMyOrderNoComplete")
    Observable<UnfinishedOrder> queryUnfinishedOrder();

}

