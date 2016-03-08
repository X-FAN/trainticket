package com.xf.sherlock.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedData {

    @SerializedName("orderDBList")
    @Expose
    private List<OrderDBList> orderDBList = new ArrayList<OrderDBList>();
    @SerializedName("to_page")
    @Expose
    private String toPage;

    /**
     * 
     * @return
     *     The orderDBList
     */
    public List<OrderDBList> getOrderDBList() {
        return orderDBList;
    }

    /**
     * 
     * @param orderDBList
     *     The orderDBList
     */
    public void setOrderDBList(List<OrderDBList> orderDBList) {
        this.orderDBList = orderDBList;
    }

    /**
     * 
     * @return
     *     The toPage
     */
    public String getToPage() {
        return toPage;
    }

    /**
     * 
     * @param toPage
     *     The to_page
     */
    public void setToPage(String toPage) {
        this.toPage = toPage;
    }

}
