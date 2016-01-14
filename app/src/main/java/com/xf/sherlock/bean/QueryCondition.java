package com.xf.sherlock.bean;

import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * Created by TC on 2016/1/12.
 */
public class QueryCondition {
    private Station fromStation;
    private Station toStation;
    private Calendar date;

    public QueryCondition(@NonNull Station fromStation, @NonNull Station toStation, @NonNull Calendar date) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.date = date;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public Calendar getDate() {
        return date;
    }

}
