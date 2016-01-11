
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("queryLeftNewDTO")
    @Expose
    private TicketInfo ticketInfo;
    @SerializedName("secretStr")
    @Expose
    private String secretStr;
    @SerializedName("buttonTextInfo")
    @Expose
    private String buttonTextInfo;

    /**
     * 
     * @return
     *     The ticketInfo
     */
    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    /**
     * 
     * @param ticketInfo
     *     The ticketInfo
     */
    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    /**
     * 
     * @return
     *     The secretStr
     */
    public String getSecretStr() {
        return secretStr;
    }

    /**
     * 
     * @param secretStr
     *     The secretStr
     */
    public void setSecretStr(String secretStr) {
        this.secretStr = secretStr;
    }

    /**
     * 
     * @return
     *     The buttonTextInfo
     */
    public String getButtonTextInfo() {
        return buttonTextInfo;
    }

    /**
     * 
     * @param buttonTextInfo
     *     The buttonTextInfo
     */
    public void setButtonTextInfo(String buttonTextInfo) {
        this.buttonTextInfo = buttonTextInfo;
    }

}
