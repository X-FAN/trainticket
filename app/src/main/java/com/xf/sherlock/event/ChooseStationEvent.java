package com.xf.sherlock.event;

import android.support.annotation.NonNull;

import com.xf.greendao.Station;

/**
 * Created by TC on 2016/1/8.
 */
public class ChooseStationEvent {
    private Station mFromStation;
    private Station mToStaion;

    public ChooseStationEvent(@NonNull Station mFromStation, @NonNull Station mToStation) {
        this.mFromStation = mFromStation;
        this.mToStaion = mToStation;
    }

    public Station getFromStation() {
        return mFromStation;
    }

    public Station getToStaion() {
        return mToStaion;
    }
}
