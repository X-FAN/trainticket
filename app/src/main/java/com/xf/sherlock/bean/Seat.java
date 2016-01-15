package com.xf.sherlock.bean;

public class Seat {
    public static final String SHANG_WU_ZUO_TYPE = "商务座";
    public static final String TE_DENG_ZUO_TYPE = "特等座";
    public static final String YI_DENG_ZUO_TYPE = "一等座";
    public static final String ER_DENG_ZUO_TYPE = "二等座";
    public static final String GAO_JI_RUAN_WO_TYPE = "高级软卧";
    public static final String RUAN_WO_TYPE = "软卧";
    public static final String YING_WO_TYPE = "硬卧";
    public static final String RUAN_ZUO_TYPE = "软座";
    public static final String YING_ZUO_TYPE = "硬座";
    public static final String WU_ZUO_TYPE = "无座";
    public static final String OTHER_TYPE = "其他";

    public static final String[] SEAT_TYPE = new String[]{SHANG_WU_ZUO_TYPE,
            TE_DENG_ZUO_TYPE, YI_DENG_ZUO_TYPE, ER_DENG_ZUO_TYPE,
            GAO_JI_RUAN_WO_TYPE, RUAN_WO_TYPE, YING_WO_TYPE, RUAN_ZUO_TYPE,
            YING_ZUO_TYPE, WU_ZUO_TYPE, OTHER_TYPE};

    public static final int YING_ZUO = 1;
    public static final int YING_WO_SAHNG = 2;
    public static final int YING_WO_ZHONG = 3;
    public static final int YING_WO_XIA = 4;
    public static final int RUAN_ZUO = 5;
    public static final int RUAN_WO_SHANG = 6;
    public static final int RUAN_WO_XIA = 8;
    public static final int SHANG_WU_ZUO = 9;
    public static final int TE_DENG_ZUO = 12;
    public static final int YI_DENG_ZUO = 13;
    public static final int ER_DENG_ZUO = 14;
    public static final int GAO_JI_RUAN_WO_SHANG = 15;
    public static final int GAO_JI_RUAN_WO_XIA = 16;
    public static final int YI_DENG_RUAN_ZUO = 17;
    public static final int ER_DENG_RUAN_ZUO = 18;

    private String type;
    private float Price;
    private int count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type)
                .append("(")
                .append(count)
                .append(")");
        return builder.toString();
    }
}
