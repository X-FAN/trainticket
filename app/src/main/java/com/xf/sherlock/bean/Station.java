package com.xf.sherlock.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TC on 2016/1/7.
 */
public class Station implements Parcelable {
    private String stationName;//车站名
    private String stationCode;//车站代码
    private String fullPY;//车站全拼
    private String shortPY;//车站简拼
    private String section;
    private boolean isShow = false;//是否展示section

    public Station() {

    }

    protected Station(Parcel in) {
        stationName = in.readString();
        stationCode = in.readString();
        fullPY = in.readString();
        shortPY = in.readString();
        section = in.readString();
        isShow = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stationName);
        dest.writeString(stationCode);
        dest.writeString(fullPY);
        dest.writeString(shortPY);
        dest.writeString(section);
        dest.writeByte((byte) (isShow ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getFullPY() {
        return fullPY;
    }

    public void setFullPY(String fullPY) {
        this.fullPY = fullPY;
    }

    public String getShortPY() {
        return shortPY;
    }

    public void setShortPY(String shortPY) {
        this.shortPY = shortPY;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
}
