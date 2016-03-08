
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("stationTrainDTO")
    @Expose
    private StationTrainDTO stationTrainDTO;
    @SerializedName("passengerDTO")
    @Expose
    private PassengerDTO passengerDTO;
    @SerializedName("ticket_no")
    @Expose
    private String ticketNo;
    @SerializedName("sequence_no")
    @Expose
    private String sequenceNo;
    @SerializedName("batch_no")
    @Expose
    private String batchNo;
    @SerializedName("train_date")
    @Expose
    private String trainDate;
    @SerializedName("coach_no")
    @Expose
    private String coachNo;
    @SerializedName("coach_name")
    @Expose
    private String coachName;
    @SerializedName("seat_no")
    @Expose
    private String seatNo;
    @SerializedName("seat_name")
    @Expose
    private String seatName;
    @SerializedName("seat_flag")
    @Expose
    private String seatFlag;
    @SerializedName("seat_type_code")
    @Expose
    private String seatTypeCode;
    @SerializedName("seat_type_name")
    @Expose
    private String seatTypeName;
    @SerializedName("ticket_type_code")
    @Expose
    private String ticketTypeCode;
    @SerializedName("ticket_type_name")
    @Expose
    private String ticketTypeName;
    @SerializedName("reserve_time")
    @Expose
    private String reserveTime;
    @SerializedName("limit_time")
    @Expose
    private String limitTime;
    @SerializedName("lose_time")
    @Expose
    private String loseTime;
    @SerializedName("pay_limit_time")
    @Expose
    private String payLimitTime;
    @SerializedName("ticket_price")
    @Expose
    private Integer ticketPrice;
    @SerializedName("print_eticket_flag")
    @Expose
    private String printEticketFlag;
    @SerializedName("resign_flag")
    @Expose
    private String resignFlag;
    @SerializedName("return_flag")
    @Expose
    private String returnFlag;
    @SerializedName("confirm_flag")
    @Expose
    private String confirmFlag;
    @SerializedName("pay_mode_code")
    @Expose
    private String payModeCode;
    @SerializedName("ticket_status_code")
    @Expose
    private String ticketStatusCode;
    @SerializedName("ticket_status_name")
    @Expose
    private String ticketStatusName;
    @SerializedName("cancel_flag")
    @Expose
    private String cancelFlag;
    @SerializedName("amount_char")
    @Expose
    private Integer amountChar;
    @SerializedName("trade_mode")
    @Expose
    private String tradeMode;
    @SerializedName("start_train_date_page")
    @Expose
    private String startTrainDatePage;
    @SerializedName("str_ticket_price_page")
    @Expose
    private String strTicketPricePage;
    @SerializedName("come_go_traveller_ticket_page")
    @Expose
    private String comeGoTravellerTicketPage;
    @SerializedName("return_deliver_flag")
    @Expose
    private String returnDeliverFlag;
    @SerializedName("deliver_fee_char")
    @Expose
    private String deliverFeeChar;
    @SerializedName("is_need_alert_flag")
    @Expose
    private Boolean isNeedAlertFlag;
    @SerializedName("is_deliver")
    @Expose
    private String isDeliver;
    @SerializedName("dynamicProp")
    @Expose
    private String dynamicProp;
    @SerializedName("fee_char")
    @Expose
    private String feeChar;
    @SerializedName("insure_query_no")
    @Expose
    private String insureQueryNo;

    /**
     * 
     * @return
     *     The stationTrainDTO
     */
    public StationTrainDTO getStationTrainDTO() {
        return stationTrainDTO;
    }

    /**
     * 
     * @param stationTrainDTO
     *     The stationTrainDTO
     */
    public void setStationTrainDTO(StationTrainDTO stationTrainDTO) {
        this.stationTrainDTO = stationTrainDTO;
    }

    /**
     * 
     * @return
     *     The passengerDTO
     */
    public PassengerDTO getPassengerDTO() {
        return passengerDTO;
    }

    /**
     * 
     * @param passengerDTO
     *     The passengerDTO
     */
    public void setPassengerDTO(PassengerDTO passengerDTO) {
        this.passengerDTO = passengerDTO;
    }

    /**
     * 
     * @return
     *     The ticketNo
     */
    public String getTicketNo() {
        return ticketNo;
    }

    /**
     * 
     * @param ticketNo
     *     The ticket_no
     */
    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    /**
     * 
     * @return
     *     The sequenceNo
     */
    public String getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 
     * @param sequenceNo
     *     The sequence_no
     */
    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 
     * @return
     *     The batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 
     * @param batchNo
     *     The batch_no
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 
     * @return
     *     The trainDate
     */
    public String getTrainDate() {
        return trainDate;
    }

    /**
     * 
     * @param trainDate
     *     The train_date
     */
    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    /**
     * 
     * @return
     *     The coachNo
     */
    public String getCoachNo() {
        return coachNo;
    }

    /**
     * 
     * @param coachNo
     *     The coach_no
     */
    public void setCoachNo(String coachNo) {
        this.coachNo = coachNo;
    }

    /**
     * 
     * @return
     *     The coachName
     */
    public String getCoachName() {
        return coachName;
    }

    /**
     * 
     * @param coachName
     *     The coach_name
     */
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    /**
     * 
     * @return
     *     The seatNo
     */
    public String getSeatNo() {
        return seatNo;
    }

    /**
     * 
     * @param seatNo
     *     The seat_no
     */
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    /**
     * 
     * @return
     *     The seatName
     */
    public String getSeatName() {
        return seatName;
    }

    /**
     * 
     * @param seatName
     *     The seat_name
     */
    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    /**
     * 
     * @return
     *     The seatFlag
     */
    public String getSeatFlag() {
        return seatFlag;
    }

    /**
     * 
     * @param seatFlag
     *     The seat_flag
     */
    public void setSeatFlag(String seatFlag) {
        this.seatFlag = seatFlag;
    }

    /**
     * 
     * @return
     *     The seatTypeCode
     */
    public String getSeatTypeCode() {
        return seatTypeCode;
    }

    /**
     * 
     * @param seatTypeCode
     *     The seat_type_code
     */
    public void setSeatTypeCode(String seatTypeCode) {
        this.seatTypeCode = seatTypeCode;
    }

    /**
     * 
     * @return
     *     The seatTypeName
     */
    public String getSeatTypeName() {
        return seatTypeName;
    }

    /**
     * 
     * @param seatTypeName
     *     The seat_type_name
     */
    public void setSeatTypeName(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }

    /**
     * 
     * @return
     *     The ticketTypeCode
     */
    public String getTicketTypeCode() {
        return ticketTypeCode;
    }

    /**
     * 
     * @param ticketTypeCode
     *     The ticket_type_code
     */
    public void setTicketTypeCode(String ticketTypeCode) {
        this.ticketTypeCode = ticketTypeCode;
    }

    /**
     * 
     * @return
     *     The ticketTypeName
     */
    public String getTicketTypeName() {
        return ticketTypeName;
    }

    /**
     * 
     * @param ticketTypeName
     *     The ticket_type_name
     */
    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    /**
     * 
     * @return
     *     The reserveTime
     */
    public String getReserveTime() {
        return reserveTime;
    }

    /**
     * 
     * @param reserveTime
     *     The reserve_time
     */
    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    /**
     * 
     * @return
     *     The limitTime
     */
    public String getLimitTime() {
        return limitTime;
    }

    /**
     * 
     * @param limitTime
     *     The limit_time
     */
    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    /**
     * 
     * @return
     *     The loseTime
     */
    public String getLoseTime() {
        return loseTime;
    }

    /**
     * 
     * @param loseTime
     *     The lose_time
     */
    public void setLoseTime(String loseTime) {
        this.loseTime = loseTime;
    }

    /**
     * 
     * @return
     *     The payLimitTime
     */
    public String getPayLimitTime() {
        return payLimitTime;
    }

    /**
     * 
     * @param payLimitTime
     *     The pay_limit_time
     */
    public void setPayLimitTime(String payLimitTime) {
        this.payLimitTime = payLimitTime;
    }

    /**
     * 
     * @return
     *     The ticketPrice
     */
    public Integer getTicketPrice() {
        return ticketPrice;
    }

    /**
     * 
     * @param ticketPrice
     *     The ticket_price
     */
    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * 
     * @return
     *     The printEticketFlag
     */
    public String getPrintEticketFlag() {
        return printEticketFlag;
    }

    /**
     * 
     * @param printEticketFlag
     *     The print_eticket_flag
     */
    public void setPrintEticketFlag(String printEticketFlag) {
        this.printEticketFlag = printEticketFlag;
    }

    /**
     * 
     * @return
     *     The resignFlag
     */
    public String getResignFlag() {
        return resignFlag;
    }

    /**
     * 
     * @param resignFlag
     *     The resign_flag
     */
    public void setResignFlag(String resignFlag) {
        this.resignFlag = resignFlag;
    }

    /**
     * 
     * @return
     *     The returnFlag
     */
    public String getReturnFlag() {
        return returnFlag;
    }

    /**
     * 
     * @param returnFlag
     *     The return_flag
     */
    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    /**
     * 
     * @return
     *     The confirmFlag
     */
    public String getConfirmFlag() {
        return confirmFlag;
    }

    /**
     * 
     * @param confirmFlag
     *     The confirm_flag
     */
    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    /**
     * 
     * @return
     *     The payModeCode
     */
    public String getPayModeCode() {
        return payModeCode;
    }

    /**
     * 
     * @param payModeCode
     *     The pay_mode_code
     */
    public void setPayModeCode(String payModeCode) {
        this.payModeCode = payModeCode;
    }

    /**
     * 
     * @return
     *     The ticketStatusCode
     */
    public String getTicketStatusCode() {
        return ticketStatusCode;
    }

    /**
     * 
     * @param ticketStatusCode
     *     The ticket_status_code
     */
    public void setTicketStatusCode(String ticketStatusCode) {
        this.ticketStatusCode = ticketStatusCode;
    }

    /**
     * 
     * @return
     *     The ticketStatusName
     */
    public String getTicketStatusName() {
        return ticketStatusName;
    }

    /**
     * 
     * @param ticketStatusName
     *     The ticket_status_name
     */
    public void setTicketStatusName(String ticketStatusName) {
        this.ticketStatusName = ticketStatusName;
    }

    /**
     * 
     * @return
     *     The cancelFlag
     */
    public String getCancelFlag() {
        return cancelFlag;
    }

    /**
     * 
     * @param cancelFlag
     *     The cancel_flag
     */
    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    /**
     * 
     * @return
     *     The amountChar
     */
    public Integer getAmountChar() {
        return amountChar;
    }

    /**
     * 
     * @param amountChar
     *     The amount_char
     */
    public void setAmountChar(Integer amountChar) {
        this.amountChar = amountChar;
    }

    /**
     * 
     * @return
     *     The tradeMode
     */
    public String getTradeMode() {
        return tradeMode;
    }

    /**
     * 
     * @param tradeMode
     *     The trade_mode
     */
    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    /**
     * 
     * @return
     *     The startTrainDatePage
     */
    public String getStartTrainDatePage() {
        return startTrainDatePage;
    }

    /**
     * 
     * @param startTrainDatePage
     *     The start_train_date_page
     */
    public void setStartTrainDatePage(String startTrainDatePage) {
        this.startTrainDatePage = startTrainDatePage;
    }

    /**
     * 
     * @return
     *     The strTicketPricePage
     */
    public String getStrTicketPricePage() {
        return strTicketPricePage;
    }

    /**
     * 
     * @param strTicketPricePage
     *     The str_ticket_price_page
     */
    public void setStrTicketPricePage(String strTicketPricePage) {
        this.strTicketPricePage = strTicketPricePage;
    }

    /**
     * 
     * @return
     *     The comeGoTravellerTicketPage
     */
    public String getComeGoTravellerTicketPage() {
        return comeGoTravellerTicketPage;
    }

    /**
     * 
     * @param comeGoTravellerTicketPage
     *     The come_go_traveller_ticket_page
     */
    public void setComeGoTravellerTicketPage(String comeGoTravellerTicketPage) {
        this.comeGoTravellerTicketPage = comeGoTravellerTicketPage;
    }

    /**
     * 
     * @return
     *     The returnDeliverFlag
     */
    public String getReturnDeliverFlag() {
        return returnDeliverFlag;
    }

    /**
     * 
     * @param returnDeliverFlag
     *     The return_deliver_flag
     */
    public void setReturnDeliverFlag(String returnDeliverFlag) {
        this.returnDeliverFlag = returnDeliverFlag;
    }

    /**
     * 
     * @return
     *     The deliverFeeChar
     */
    public String getDeliverFeeChar() {
        return deliverFeeChar;
    }

    /**
     * 
     * @param deliverFeeChar
     *     The deliver_fee_char
     */
    public void setDeliverFeeChar(String deliverFeeChar) {
        this.deliverFeeChar = deliverFeeChar;
    }

    /**
     * 
     * @return
     *     The isNeedAlertFlag
     */
    public Boolean getIsNeedAlertFlag() {
        return isNeedAlertFlag;
    }

    /**
     * 
     * @param isNeedAlertFlag
     *     The is_need_alert_flag
     */
    public void setIsNeedAlertFlag(Boolean isNeedAlertFlag) {
        this.isNeedAlertFlag = isNeedAlertFlag;
    }

    /**
     * 
     * @return
     *     The isDeliver
     */
    public String getIsDeliver() {
        return isDeliver;
    }

    /**
     * 
     * @param isDeliver
     *     The is_deliver
     */
    public void setIsDeliver(String isDeliver) {
        this.isDeliver = isDeliver;
    }

    /**
     * 
     * @return
     *     The dynamicProp
     */
    public String getDynamicProp() {
        return dynamicProp;
    }

    /**
     * 
     * @param dynamicProp
     *     The dynamicProp
     */
    public void setDynamicProp(String dynamicProp) {
        this.dynamicProp = dynamicProp;
    }

    /**
     * 
     * @return
     *     The feeChar
     */
    public String getFeeChar() {
        return feeChar;
    }

    /**
     * 
     * @param feeChar
     *     The fee_char
     */
    public void setFeeChar(String feeChar) {
        this.feeChar = feeChar;
    }

    /**
     * 
     * @return
     *     The insureQueryNo
     */
    public String getInsureQueryNo() {
        return insureQueryNo;
    }

    /**
     * 
     * @param insureQueryNo
     *     The insure_query_no
     */
    public void setInsureQueryNo(String insureQueryNo) {
        this.insureQueryNo = insureQueryNo;
    }

}
