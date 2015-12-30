
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData {

    @SerializedName("otherMsg")
    @Expose
    private String otherMsg;
    @SerializedName("loginCheck")
    @Expose
    private String loginCheck;

    /**
     * 
     * @return
     *     The otherMsg
     */
    public String getOtherMsg() {
        return otherMsg;
    }

    /**
     * 
     * @param otherMsg
     *     The otherMsg
     */
    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    /**
     * 
     * @return
     *     The loginCheck
     */
    public String getLoginCheck() {
        return loginCheck;
    }

    /**
     * 
     * @param loginCheck
     *     The loginCheck
     */
    public void setLoginCheck(String loginCheck) {
        this.loginCheck = loginCheck;
    }

}
