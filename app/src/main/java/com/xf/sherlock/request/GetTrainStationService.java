package com.xf.sherlock.request;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by TC on 2016/1/5.
 */
public interface GetTrainStationService {
    @GET("resources/js/framework/station_name.js")
    Observable<String> getTrainStation();
}
