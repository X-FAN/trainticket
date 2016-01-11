
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TicketInfo {

    @SerializedName("train_no")
    @Expose
    private String trainNo;
    @SerializedName("station_train_code")
    @Expose
    private String stationTrainCode;
    @SerializedName("start_station_telecode")
    @Expose
    private String startStationTelecode;
    @SerializedName("start_station_name")
    @Expose
    private String startStationName;
    @SerializedName("end_station_telecode")
    @Expose
    private String endStationTelecode;
    @SerializedName("end_station_name")
    @Expose
    private String endStationName;
    @SerializedName("from_station_telecode")
    @Expose
    private String fromStationTelecode;
    @SerializedName("from_station_name")
    @Expose
    private String fromStationName;
    @SerializedName("to_station_telecode")
    @Expose
    private String toStationTelecode;
    @SerializedName("to_station_name")
    @Expose
    private String toStationName;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("arrive_time")
    @Expose
    private String arriveTime;
    @SerializedName("day_difference")
    @Expose
    private String dayDifference;
    @SerializedName("train_class_name")
    @Expose
    private String trainClassName;
    @SerializedName("lishi")
    @Expose
    private String lishi;
    @SerializedName("canWebBuy")
    @Expose
    private String canWebBuy;
    @SerializedName("lishiValue")
    @Expose
    private String lishiValue;
    @SerializedName("yp_info")
    @Expose
    private String ypInfo;
    @SerializedName("control_train_day")
    @Expose
    private String controlTrainDay;
    @SerializedName("start_train_date")
    @Expose
    private String startTrainDate;
    @SerializedName("seat_feature")
    @Expose
    private String seatFeature;
    @SerializedName("yp_ex")
    @Expose
    private String ypEx;
    @SerializedName("train_seat_feature")
    @Expose
    private String trainSeatFeature;
    @SerializedName("seat_types")
    @Expose
    private String seatTypes;
    @SerializedName("location_code")
    @Expose
    private String locationCode;
    @SerializedName("from_station_no")
    @Expose
    private String fromStationNo;
    @SerializedName("to_station_no")
    @Expose
    private String toStationNo;
    @SerializedName("control_day")
    @Expose
    private Integer controlDay;
    @SerializedName("sale_time")
    @Expose
    private String saleTime;
    @SerializedName("is_support_card")
    @Expose
    private String isSupportCard;
    @SerializedName("controlled_train_flag")
    @Expose
    private String controlledTrainFlag;
    @SerializedName("controlled_train_message")
    @Expose
    private String controlledTrainMessage;
    @SerializedName("gg_num")
    @Expose
    private String ggNum;
    @SerializedName("gr_num")
    @Expose
    private String grNum;
    @SerializedName("qt_num")
    @Expose
    private String qtNum;
    @SerializedName("rw_num")
    @Expose
    private String rwNum;
    @SerializedName("rz_num")
    @Expose
    private String rzNum;
    @SerializedName("tz_num")
    @Expose
    private String tzNum;
    @SerializedName("wz_num")
    @Expose
    private String wzNum;
    @SerializedName("yb_num")
    @Expose
    private String ybNum;
    @SerializedName("yw_num")
    @Expose
    private String ywNum;
    @SerializedName("yz_num")
    @Expose
    private String yzNum;
    @SerializedName("ze_num")
    @Expose
    private String zeNum;
    @SerializedName("zy_num")
    @Expose
    private String zyNum;
    @SerializedName("swz_num")
    @Expose
    private String swzNum;

    /**
     * 
     * @return
     *     The trainNo
     */
    public String getTrainNo() {
        return trainNo;
    }

    /**
     * 
     * @param trainNo
     *     The train_no
     */
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    /**
     * 
     * @return
     *     The stationTrainCode
     */
    public String getStationTrainCode() {
        return stationTrainCode;
    }

    /**
     * 
     * @param stationTrainCode
     *     The station_train_code
     */
    public void setStationTrainCode(String stationTrainCode) {
        this.stationTrainCode = stationTrainCode;
    }

    /**
     * 
     * @return
     *     The startStationTelecode
     */
    public String getStartStationTelecode() {
        return startStationTelecode;
    }

    /**
     * 
     * @param startStationTelecode
     *     The start_station_telecode
     */
    public void setStartStationTelecode(String startStationTelecode) {
        this.startStationTelecode = startStationTelecode;
    }

    /**
     * 
     * @return
     *     The startStationName
     */
    public String getStartStationName() {
        return startStationName;
    }

    /**
     * 
     * @param startStationName
     *     The start_station_name
     */
    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    /**
     * 
     * @return
     *     The endStationTelecode
     */
    public String getEndStationTelecode() {
        return endStationTelecode;
    }

    /**
     * 
     * @param endStationTelecode
     *     The end_station_telecode
     */
    public void setEndStationTelecode(String endStationTelecode) {
        this.endStationTelecode = endStationTelecode;
    }

    /**
     * 
     * @return
     *     The endStationName
     */
    public String getEndStationName() {
        return endStationName;
    }

    /**
     * 
     * @param endStationName
     *     The end_station_name
     */
    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    /**
     * 
     * @return
     *     The fromStationTelecode
     */
    public String getFromStationTelecode() {
        return fromStationTelecode;
    }

    /**
     * 
     * @param fromStationTelecode
     *     The from_station_telecode
     */
    public void setFromStationTelecode(String fromStationTelecode) {
        this.fromStationTelecode = fromStationTelecode;
    }

    /**
     * 
     * @return
     *     The fromStationName
     */
    public String getFromStationName() {
        return fromStationName;
    }

    /**
     * 
     * @param fromStationName
     *     The from_station_name
     */
    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    /**
     * 
     * @return
     *     The toStationTelecode
     */
    public String getToStationTelecode() {
        return toStationTelecode;
    }

    /**
     * 
     * @param toStationTelecode
     *     The to_station_telecode
     */
    public void setToStationTelecode(String toStationTelecode) {
        this.toStationTelecode = toStationTelecode;
    }

    /**
     * 
     * @return
     *     The toStationName
     */
    public String getToStationName() {
        return toStationName;
    }

    /**
     * 
     * @param toStationName
     *     The to_station_name
     */
    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The start_time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The arriveTime
     */
    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * 
     * @param arriveTime
     *     The arrive_time
     */
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * 
     * @return
     *     The dayDifference
     */
    public String getDayDifference() {
        return dayDifference;
    }

    /**
     * 
     * @param dayDifference
     *     The day_difference
     */
    public void setDayDifference(String dayDifference) {
        this.dayDifference = dayDifference;
    }

    /**
     * 
     * @return
     *     The trainClassName
     */
    public String getTrainClassName() {
        return trainClassName;
    }

    /**
     * 
     * @param trainClassName
     *     The train_class_name
     */
    public void setTrainClassName(String trainClassName) {
        this.trainClassName = trainClassName;
    }

    /**
     * 
     * @return
     *     The lishi
     */
    public String getLishi() {
        return lishi;
    }

    /**
     * 
     * @param lishi
     *     The lishi
     */
    public void setLishi(String lishi) {
        this.lishi = lishi;
    }

    /**
     * 
     * @return
     *     The canWebBuy
     */
    public String getCanWebBuy() {
        return canWebBuy;
    }

    /**
     * 
     * @param canWebBuy
     *     The canWebBuy
     */
    public void setCanWebBuy(String canWebBuy) {
        this.canWebBuy = canWebBuy;
    }

    /**
     * 
     * @return
     *     The lishiValue
     */
    public String getLishiValue() {
        return lishiValue;
    }

    /**
     * 
     * @param lishiValue
     *     The lishiValue
     */
    public void setLishiValue(String lishiValue) {
        this.lishiValue = lishiValue;
    }

    /**
     * 
     * @return
     *     The ypInfo
     */
    public String getYpInfo() {
        return ypInfo;
    }

    /**
     * 
     * @param ypInfo
     *     The yp_info
     */
    public void setYpInfo(String ypInfo) {
        this.ypInfo = ypInfo;
    }

    /**
     * 
     * @return
     *     The controlTrainDay
     */
    public String getControlTrainDay() {
        return controlTrainDay;
    }

    /**
     * 
     * @param controlTrainDay
     *     The control_train_day
     */
    public void setControlTrainDay(String controlTrainDay) {
        this.controlTrainDay = controlTrainDay;
    }

    /**
     * 
     * @return
     *     The startTrainDate
     */
    public String getStartTrainDate() {
        return startTrainDate;
    }

    /**
     * 
     * @param startTrainDate
     *     The start_train_date
     */
    public void setStartTrainDate(String startTrainDate) {
        this.startTrainDate = startTrainDate;
    }

    /**
     * 
     * @return
     *     The seatFeature
     */
    public String getSeatFeature() {
        return seatFeature;
    }

    /**
     * 
     * @param seatFeature
     *     The seat_feature
     */
    public void setSeatFeature(String seatFeature) {
        this.seatFeature = seatFeature;
    }

    /**
     * 
     * @return
     *     The ypEx
     */
    public String getYpEx() {
        return ypEx;
    }

    /**
     * 
     * @param ypEx
     *     The yp_ex
     */
    public void setYpEx(String ypEx) {
        this.ypEx = ypEx;
    }

    /**
     * 
     * @return
     *     The trainSeatFeature
     */
    public String getTrainSeatFeature() {
        return trainSeatFeature;
    }

    /**
     * 
     * @param trainSeatFeature
     *     The train_seat_feature
     */
    public void setTrainSeatFeature(String trainSeatFeature) {
        this.trainSeatFeature = trainSeatFeature;
    }

    /**
     * 
     * @return
     *     The seatTypes
     */
    public String getSeatTypes() {
        return seatTypes;
    }

    /**
     * 
     * @param seatTypes
     *     The seat_types
     */
    public void setSeatTypes(String seatTypes) {
        this.seatTypes = seatTypes;
    }

    /**
     * 
     * @return
     *     The locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * 
     * @param locationCode
     *     The location_code
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * 
     * @return
     *     The fromStationNo
     */
    public String getFromStationNo() {
        return fromStationNo;
    }

    /**
     * 
     * @param fromStationNo
     *     The from_station_no
     */
    public void setFromStationNo(String fromStationNo) {
        this.fromStationNo = fromStationNo;
    }

    /**
     * 
     * @return
     *     The toStationNo
     */
    public String getToStationNo() {
        return toStationNo;
    }

    /**
     * 
     * @param toStationNo
     *     The to_station_no
     */
    public void setToStationNo(String toStationNo) {
        this.toStationNo = toStationNo;
    }

    /**
     * 
     * @return
     *     The controlDay
     */
    public Integer getControlDay() {
        return controlDay;
    }

    /**
     * 
     * @param controlDay
     *     The control_day
     */
    public void setControlDay(Integer controlDay) {
        this.controlDay = controlDay;
    }

    /**
     * 
     * @return
     *     The saleTime
     */
    public String getSaleTime() {
        return saleTime;
    }

    /**
     * 
     * @param saleTime
     *     The sale_time
     */
    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    /**
     * 
     * @return
     *     The isSupportCard
     */
    public String getIsSupportCard() {
        return isSupportCard;
    }

    /**
     * 
     * @param isSupportCard
     *     The is_support_card
     */
    public void setIsSupportCard(String isSupportCard) {
        this.isSupportCard = isSupportCard;
    }

    /**
     * 
     * @return
     *     The controlledTrainFlag
     */
    public String getControlledTrainFlag() {
        return controlledTrainFlag;
    }

    /**
     * 
     * @param controlledTrainFlag
     *     The controlled_train_flag
     */
    public void setControlledTrainFlag(String controlledTrainFlag) {
        this.controlledTrainFlag = controlledTrainFlag;
    }

    /**
     * 
     * @return
     *     The controlledTrainMessage
     */
    public String getControlledTrainMessage() {
        return controlledTrainMessage;
    }

    /**
     * 
     * @param controlledTrainMessage
     *     The controlled_train_message
     */
    public void setControlledTrainMessage(String controlledTrainMessage) {
        this.controlledTrainMessage = controlledTrainMessage;
    }

    /**
     * 
     * @return
     *     The ggNum
     */
    public String getGgNum() {
        return ggNum;
    }

    /**
     * 
     * @param ggNum
     *     The gg_num
     */
    public void setGgNum(String ggNum) {
        this.ggNum = ggNum;
    }

    /**
     * 
     * @return
     *     The grNum
     */
    public String getGrNum() {
        return grNum;
    }

    /**
     * 
     * @param grNum
     *     The gr_num
     */
    public void setGrNum(String grNum) {
        this.grNum = grNum;
    }

    /**
     * 
     * @return
     *     The qtNum
     */
    public String getQtNum() {
        return qtNum;
    }

    /**
     * 
     * @param qtNum
     *     The qt_num
     */
    public void setQtNum(String qtNum) {
        this.qtNum = qtNum;
    }

    /**
     * 
     * @return
     *     The rwNum
     */
    public String getRwNum() {
        return rwNum;
    }

    /**
     * 
     * @param rwNum
     *     The rw_num
     */
    public void setRwNum(String rwNum) {
        this.rwNum = rwNum;
    }

    /**
     * 
     * @return
     *     The rzNum
     */
    public String getRzNum() {
        return rzNum;
    }

    /**
     * 
     * @param rzNum
     *     The rz_num
     */
    public void setRzNum(String rzNum) {
        this.rzNum = rzNum;
    }

    /**
     * 
     * @return
     *     The tzNum
     */
    public String getTzNum() {
        return tzNum;
    }

    /**
     * 
     * @param tzNum
     *     The tz_num
     */
    public void setTzNum(String tzNum) {
        this.tzNum = tzNum;
    }

    /**
     * 
     * @return
     *     The wzNum
     */
    public String getWzNum() {
        return wzNum;
    }

    /**
     * 
     * @param wzNum
     *     The wz_num
     */
    public void setWzNum(String wzNum) {
        this.wzNum = wzNum;
    }

    /**
     * 
     * @return
     *     The ybNum
     */
    public String getYbNum() {
        return ybNum;
    }

    /**
     * 
     * @param ybNum
     *     The yb_num
     */
    public void setYbNum(String ybNum) {
        this.ybNum = ybNum;
    }

    /**
     * 
     * @return
     *     The ywNum
     */
    public String getYwNum() {
        return ywNum;
    }

    /**
     * 
     * @param ywNum
     *     The yw_num
     */
    public void setYwNum(String ywNum) {
        this.ywNum = ywNum;
    }

    /**
     * 
     * @return
     *     The yzNum
     */
    public String getYzNum() {
        return yzNum;
    }

    /**
     * 
     * @param yzNum
     *     The yz_num
     */
    public void setYzNum(String yzNum) {
        this.yzNum = yzNum;
    }

    /**
     * 
     * @return
     *     The zeNum
     */
    public String getZeNum() {
        return zeNum;
    }

    /**
     * 
     * @param zeNum
     *     The ze_num
     */
    public void setZeNum(String zeNum) {
        this.zeNum = zeNum;
    }

    /**
     * 
     * @return
     *     The zyNum
     */
    public String getZyNum() {
        return zyNum;
    }

    /**
     * 
     * @param zyNum
     *     The zy_num
     */
    public void setZyNum(String zyNum) {
        this.zyNum = zyNum;
    }

    /**
     * 
     * @return
     *     The swzNum
     */
    public String getSwzNum() {
        return swzNum;
    }

    /**
     * 
     * @param swzNum
     *     The swz_num
     */
    public void setSwzNum(String swzNum) {
        this.swzNum = swzNum;
    }

}
