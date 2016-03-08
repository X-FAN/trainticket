
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationTrainDTO {

    @SerializedName("trainDTO")
    @Expose
    private TrainDTO trainDTO;
    @SerializedName("station_train_code")
    @Expose
    private String stationTrainCode;
    @SerializedName("from_station_telecode")
    @Expose
    private String fromStationTelecode;
    @SerializedName("from_station_name")
    @Expose
    private String fromStationName;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("to_station_telecode")
    @Expose
    private String toStationTelecode;
    @SerializedName("to_station_name")
    @Expose
    private String toStationName;
    @SerializedName("arrive_time")
    @Expose
    private String arriveTime;
    @SerializedName("distance")
    @Expose
    private String distance;

    /**
     * 
     * @return
     *     The trainDTO
     */
    public TrainDTO getTrainDTO() {
        return trainDTO;
    }

    /**
     * 
     * @param trainDTO
     *     The trainDTO
     */
    public void setTrainDTO(TrainDTO trainDTO) {
        this.trainDTO = trainDTO;
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
     *     The distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

}
