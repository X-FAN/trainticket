package com.xf.sherlock.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by TC on 2016/1/12.
 */
public class QueryCondition implements Parcelable {
    private Station fromStation;
    private Station toStation;
    private String date;

    public QueryCondition(@NonNull Station fromStation, @NonNull Station toStation, @NonNull String date) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.date = date;
    }

    protected QueryCondition(Parcel in) {
        fromStation = in.readParcelable(Station.class.getClassLoader());
        toStation = in.readParcelable(Station.class.getClassLoader());
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fromStation, flags);
        dest.writeParcelable(toStation, flags);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QueryCondition> CREATOR = new Creator<QueryCondition>() {
        @Override
        public QueryCondition createFromParcel(Parcel in) {
            return new QueryCondition(in);
        }

        @Override
        public QueryCondition[] newArray(int size) {
            return new QueryCondition[size];
        }
    };

    public Station getFromStation() {
        return fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public String getDate() {
        return date;
    }
}
