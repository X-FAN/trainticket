
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedOrder {

    @SerializedName("validateMessagesShowId")
    @Expose
    private String validateMessagesShowId;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("httpstatus")
    @Expose
    private Integer httpstatus;
    @SerializedName("data")
    @Expose
    private UnfinishedData unfinishedData;
    @SerializedName("messages")
    @Expose
    private List<Object> messages = new ArrayList<Object>();
    @SerializedName("validateMessages")
    @Expose
    private ValidateMessages validateMessages;

    /**
     * 
     * @return
     *     The validateMessagesShowId
     */
    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    /**
     * 
     * @param validateMessagesShowId
     *     The validateMessagesShowId
     */
    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The httpstatus
     */
    public Integer getHttpstatus() {
        return httpstatus;
    }

    /**
     * 
     * @param httpstatus
     *     The httpstatus
     */
    public void setHttpstatus(Integer httpstatus) {
        this.httpstatus = httpstatus;
    }

    public UnfinishedData getUnfinishedData() {
        return unfinishedData;
    }

    public void setUnfinishedData(UnfinishedData unfinishedData) {
        this.unfinishedData = unfinishedData;
    }

    /**
     * 
     * @return
     *     The messages
     */
    public List<Object> getMessages() {
        return messages;
    }

    /**
     * 
     * @param messages
     *     The messages
     */
    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }

    /**
     * 
     * @return
     *     The validateMessages
     */
    public ValidateMessages getValidateMessages() {
        return validateMessages;
    }

    /**
     * 
     * @param validateMessages
     *     The validateMessages
     */
    public void setValidateMessages(ValidateMessages validateMessages) {
        this.validateMessages = validateMessages;
    }

}
