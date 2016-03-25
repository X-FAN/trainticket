
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengerDTO {

    @SerializedName("passenger_name")
    @Expose
    private String passengerName;
    @SerializedName("passenger_id_type_code")
    @Expose
    private String passengerIdTypeCode;
    @SerializedName("passenger_id_type_name")
    @Expose
    private String passengerIdTypeName;
    @SerializedName("passenger_id_no")
    @Expose
    private String passengerIdNo;
    @SerializedName("total_times")
    @Expose
    private String totalTimes;

    /**
     * 
     * @return
     *     The passengerName
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * 
     * @param passengerName
     *     The passenger_name
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * 
     * @return
     *     The passengerIdTypeCode
     */
    public String getPassengerIdTypeCode() {
        return passengerIdTypeCode;
    }

    /**
     * 
     * @param passengerIdTypeCode
     *     The passenger_id_type_code
     */
    public void setPassengerIdTypeCode(String passengerIdTypeCode) {
        this.passengerIdTypeCode = passengerIdTypeCode;
    }

    /**
     * 
     * @return
     *     The passengerIdTypeName
     */
    public String getPassengerIdTypeName() {
        return passengerIdTypeName;
    }

    /**
     * 
     * @param passengerIdTypeName
     *     The passenger_id_type_name
     */
    public void setPassengerIdTypeName(String passengerIdTypeName) {
        this.passengerIdTypeName = passengerIdTypeName;
    }

    /**
     * 
     * @return
     *     The passengerIdNo
     */
    public String getPassengerIdNo() {
        return passengerIdNo;
    }

    /**
     * 
     * @param passengerIdNo
     *     The passenger_id_no
     */
    public void setPassengerIdNo(String passengerIdNo) {
        this.passengerIdNo = passengerIdNo;
    }

    /**
     * 
     * @return
     *     The totalTimes
     */
    public String getTotalTimes() {
        return totalTimes;
    }

    /**
     * 
     * @param totalTimes
     *     The total_times
     */
    public void setTotalTimes(String totalTimes) {
        this.totalTimes = totalTimes;
    }

}
