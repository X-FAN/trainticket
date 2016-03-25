
package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderDBList {

    @SerializedName("sequence_no")
    @Expose
    private String sequenceNo;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("ticket_totalnum")
    @Expose
    private Integer ticketTotalnum;
    @SerializedName("ticket_price_all")
    @Expose
    private Integer ticketPriceAll;
    @SerializedName("cancel_flag")
    @Expose
    private String cancelFlag;
    @SerializedName("resign_flag")
    @Expose
    private String resignFlag;
    @SerializedName("return_flag")
    @Expose
    private String returnFlag;
    @SerializedName("print_eticket_flag")
    @Expose
    private String printEticketFlag;
    @SerializedName("pay_flag")
    @Expose
    private String payFlag;
    @SerializedName("pay_resign_flag")
    @Expose
    private String payResignFlag;
    @SerializedName("confirm_flag")
    @Expose
    private String confirmFlag;
    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets = new ArrayList<Ticket>();
    @SerializedName("reserve_flag_query")
    @Expose
    private String reserveFlagQuery;
    @SerializedName("if_show_resigning_info")
    @Expose
    private String ifShowResigningInfo;
    @SerializedName("recordCount")
    @Expose
    private String recordCount;
    @SerializedName("isNeedSendMailAndMsg")
    @Expose
    private String isNeedSendMailAndMsg;
    @SerializedName("array_passser_name_page")
    @Expose
    private List<String> arrayPassserNamePage = new ArrayList<String>();
    @SerializedName("from_station_name_page")
    @Expose
    private List<String> fromStationNamePage = new ArrayList<String>();
    @SerializedName("to_station_name_page")
    @Expose
    private List<String> toStationNamePage = new ArrayList<String>();
    @SerializedName("start_train_date_page")
    @Expose
    private String startTrainDatePage;
    @SerializedName("start_time_page")
    @Expose
    private String startTimePage;
    @SerializedName("arrive_time_page")
    @Expose
    private String arriveTimePage;
    @SerializedName("train_code_page")
    @Expose
    private String trainCodePage;
    @SerializedName("ticket_total_price_page")
    @Expose
    private String ticketTotalPricePage;
    @SerializedName("come_go_traveller_order_page")
    @Expose
    private String comeGoTravellerOrderPage;
    @SerializedName("canOffLinePay")
    @Expose
    private String canOffLinePay;
    @SerializedName("if_deliver")
    @Expose
    private String ifDeliver;
    @SerializedName("insure_query_no")
    @Expose
    private String insureQueryNo;

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
     *     The orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * 
     * @param orderDate
     *     The order_date
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 
     * @return
     *     The ticketTotalnum
     */
    public Integer getTicketTotalnum() {
        return ticketTotalnum;
    }

    /**
     * 
     * @param ticketTotalnum
     *     The ticket_totalnum
     */
    public void setTicketTotalnum(Integer ticketTotalnum) {
        this.ticketTotalnum = ticketTotalnum;
    }

    /**
     * 
     * @return
     *     The ticketPriceAll
     */
    public Integer getTicketPriceAll() {
        return ticketPriceAll;
    }

    /**
     * 
     * @param ticketPriceAll
     *     The ticket_price_all
     */
    public void setTicketPriceAll(Integer ticketPriceAll) {
        this.ticketPriceAll = ticketPriceAll;
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
     *     The payFlag
     */
    public String getPayFlag() {
        return payFlag;
    }

    /**
     * 
     * @param payFlag
     *     The pay_flag
     */
    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    /**
     * 
     * @return
     *     The payResignFlag
     */
    public String getPayResignFlag() {
        return payResignFlag;
    }

    /**
     * 
     * @param payResignFlag
     *     The pay_resign_flag
     */
    public void setPayResignFlag(String payResignFlag) {
        this.payResignFlag = payResignFlag;
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
     *     The tickets
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * 
     * @param tickets
     *     The tickets
     */
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * 
     * @return
     *     The reserveFlagQuery
     */
    public String getReserveFlagQuery() {
        return reserveFlagQuery;
    }

    /**
     * 
     * @param reserveFlagQuery
     *     The reserve_flag_query
     */
    public void setReserveFlagQuery(String reserveFlagQuery) {
        this.reserveFlagQuery = reserveFlagQuery;
    }

    /**
     * 
     * @return
     *     The ifShowResigningInfo
     */
    public String getIfShowResigningInfo() {
        return ifShowResigningInfo;
    }

    /**
     * 
     * @param ifShowResigningInfo
     *     The if_show_resigning_info
     */
    public void setIfShowResigningInfo(String ifShowResigningInfo) {
        this.ifShowResigningInfo = ifShowResigningInfo;
    }

    /**
     * 
     * @return
     *     The recordCount
     */
    public String getRecordCount() {
        return recordCount;
    }

    /**
     * 
     * @param recordCount
     *     The recordCount
     */
    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * 
     * @return
     *     The isNeedSendMailAndMsg
     */
    public String getIsNeedSendMailAndMsg() {
        return isNeedSendMailAndMsg;
    }

    /**
     * 
     * @param isNeedSendMailAndMsg
     *     The isNeedSendMailAndMsg
     */
    public void setIsNeedSendMailAndMsg(String isNeedSendMailAndMsg) {
        this.isNeedSendMailAndMsg = isNeedSendMailAndMsg;
    }

    /**
     * 
     * @return
     *     The arrayPassserNamePage
     */
    public List<String> getArrayPassserNamePage() {
        return arrayPassserNamePage;
    }

    /**
     * 
     * @param arrayPassserNamePage
     *     The array_passser_name_page
     */
    public void setArrayPassserNamePage(List<String> arrayPassserNamePage) {
        this.arrayPassserNamePage = arrayPassserNamePage;
    }

    /**
     * 
     * @return
     *     The fromStationNamePage
     */
    public List<String> getFromStationNamePage() {
        return fromStationNamePage;
    }

    /**
     * 
     * @param fromStationNamePage
     *     The from_station_name_page
     */
    public void setFromStationNamePage(List<String> fromStationNamePage) {
        this.fromStationNamePage = fromStationNamePage;
    }

    /**
     * 
     * @return
     *     The toStationNamePage
     */
    public List<String> getToStationNamePage() {
        return toStationNamePage;
    }

    /**
     * 
     * @param toStationNamePage
     *     The to_station_name_page
     */
    public void setToStationNamePage(List<String> toStationNamePage) {
        this.toStationNamePage = toStationNamePage;
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
     *     The startTimePage
     */
    public String getStartTimePage() {
        return startTimePage;
    }

    /**
     * 
     * @param startTimePage
     *     The start_time_page
     */
    public void setStartTimePage(String startTimePage) {
        this.startTimePage = startTimePage;
    }

    /**
     * 
     * @return
     *     The arriveTimePage
     */
    public String getArriveTimePage() {
        return arriveTimePage;
    }

    /**
     * 
     * @param arriveTimePage
     *     The arrive_time_page
     */
    public void setArriveTimePage(String arriveTimePage) {
        this.arriveTimePage = arriveTimePage;
    }

    /**
     * 
     * @return
     *     The trainCodePage
     */
    public String getTrainCodePage() {
        return trainCodePage;
    }

    /**
     * 
     * @param trainCodePage
     *     The train_code_page
     */
    public void setTrainCodePage(String trainCodePage) {
        this.trainCodePage = trainCodePage;
    }

    /**
     * 
     * @return
     *     The ticketTotalPricePage
     */
    public String getTicketTotalPricePage() {
        return ticketTotalPricePage;
    }

    /**
     * 
     * @param ticketTotalPricePage
     *     The ticket_total_price_page
     */
    public void setTicketTotalPricePage(String ticketTotalPricePage) {
        this.ticketTotalPricePage = ticketTotalPricePage;
    }

    /**
     * 
     * @return
     *     The comeGoTravellerOrderPage
     */
    public String getComeGoTravellerOrderPage() {
        return comeGoTravellerOrderPage;
    }

    /**
     * 
     * @param comeGoTravellerOrderPage
     *     The come_go_traveller_order_page
     */
    public void setComeGoTravellerOrderPage(String comeGoTravellerOrderPage) {
        this.comeGoTravellerOrderPage = comeGoTravellerOrderPage;
    }

    /**
     * 
     * @return
     *     The canOffLinePay
     */
    public String getCanOffLinePay() {
        return canOffLinePay;
    }

    /**
     * 
     * @param canOffLinePay
     *     The canOffLinePay
     */
    public void setCanOffLinePay(String canOffLinePay) {
        this.canOffLinePay = canOffLinePay;
    }

    /**
     * 
     * @return
     *     The ifDeliver
     */
    public String getIfDeliver() {
        return ifDeliver;
    }

    /**
     * 
     * @param ifDeliver
     *     The if_deliver
     */
    public void setIfDeliver(String ifDeliver) {
        this.ifDeliver = ifDeliver;
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
