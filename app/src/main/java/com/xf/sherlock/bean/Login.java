
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Login {

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
    private LoginData data;
    @SerializedName("messages")
    @Expose
    private List<String> messages = new ArrayList<>();
    @SerializedName("validateMessages")
    @Expose
    private ValidateMessages validateMessages;

    /**
     * @return The validateMessagesShowId
     */
    public String getValidateMessagesShowId() {
        return validateMessagesShowId;
    }

    /**
     * @param validateMessagesShowId The validateMessagesShowId
     */
    public void setValidateMessagesShowId(String validateMessagesShowId) {
        this.validateMessagesShowId = validateMessagesShowId;
    }

    /**
     * @return The status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return The httpstatus
     */
    public Integer getHttpstatus() {
        return httpstatus;
    }

    /**
     * @param httpstatus The httpstatus
     */
    public void setHttpstatus(Integer httpstatus) {
        this.httpstatus = httpstatus;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    /**
     * @return The validateMessages
     */
    public ValidateMessages getValidateMessages() {
        return validateMessages;
    }

    /**
     * @param validateMessages The validateMessages
     */
    public void setValidateMessages(ValidateMessages validateMessages) {
        this.validateMessages = validateMessages;
    }

}
