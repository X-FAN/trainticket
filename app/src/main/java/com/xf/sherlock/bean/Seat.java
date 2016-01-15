package com.xf.sherlock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Seat implements Parcelable {
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
    /**
     * 可能存在上中下的，价格不一样，保存最高价格和最低价格
     */
    private float minPrice;
    /**
     * 可能存在上中下的，价格不一样，保存最高价格和最低价格
     */
    private float maxPrice;
    private int count;
    private boolean selected;
    private String seatTypeC2T;

    public String getSeatTypeC2T() {
        return seatTypeC2T;
    }

    public void setSeatTypeC2T(String seatTypeC2T) {
        this.seatTypeC2T = seatTypeC2T;
    }

    public Seat() {

    }

    public Seat(Parcel in) {
        type = in.readString();
        minPrice = in.readFloat();
        maxPrice = in.readFloat();
        count = in.readInt();
        selected = in.readByte() != 0;
        seatTypeC2T = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) {
            count = 0;
        }
        this.count = count;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static final Parcelable.Creator<Seat> CREATOR = new Parcelable.Creator<Seat>() {

        @Override
        public Seat createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Seat(source);
        }

        @Override
        public Seat[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Seat[size];
        }
    };

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(type);
        dest.writeFloat(minPrice);
        dest.writeFloat(maxPrice);
        dest.writeInt(count);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeString(seatTypeC2T);
    }

    /**
     * 获得车票类型
     *
     * @param list
     * @return
     */
    public static Set<String> getTypeList(List<SeatAssist> list) {
        Set<String> typeSet = new TreeSet<String>();
        for (SeatAssist seatAssist : list) {
            switch (seatAssist.getId()) {
                case SHANG_WU_ZUO:
                    typeSet.add(SHANG_WU_ZUO_TYPE);
                    break;
                case TE_DENG_ZUO:
                    typeSet.add(TE_DENG_ZUO_TYPE);
                    break;
                case YI_DENG_RUAN_ZUO:
                case YI_DENG_ZUO:
                    typeSet.add(YI_DENG_ZUO_TYPE);
                    break;
                case ER_DENG_RUAN_ZUO:
                case ER_DENG_ZUO:
                    typeSet.add(ER_DENG_ZUO_TYPE);
                    break;
                case YING_ZUO:
                    typeSet.add(YING_ZUO_TYPE);
                    break;
                case YING_WO_SAHNG:
                case YING_WO_ZHONG:
                case YING_WO_XIA:
                    typeSet.add(YING_WO_TYPE);
                    break;
                case RUAN_ZUO:
                    typeSet.add(RUAN_ZUO_TYPE);
                    break;
                case RUAN_WO_SHANG:
                case RUAN_WO_XIA:
                    typeSet.add(RUAN_WO_TYPE);
                    break;
                case GAO_JI_RUAN_WO_SHANG:
                case GAO_JI_RUAN_WO_XIA:
                    typeSet.add(GAO_JI_RUAN_WO_TYPE);
                    break;
                default:
                    break;
            }
        }
        return typeSet;
    }

    /**
     * 获得给定类型的车票的最高价格
     *
     * @param type
     * @param list
     * @return
     */
    public static float getMaxPrice(String type, List<SeatAssist> list) {
        if (type.equals(GAO_JI_RUAN_WO_TYPE) || type.equals(RUAN_WO_TYPE)
                || type.equals(YING_WO_TYPE)) {
            if (type.equals(GAO_JI_RUAN_WO_TYPE)) {
                String[] s = new String[]{"高级软卧上", "高级软卧下"};
                return getMax(getPrice(s[0], list), getPrice(s[1], list), 0);
            } else if (type.equals(RUAN_WO_TYPE)) {
                String[] s = new String[]{"软卧上", "软卧下"};
                return getMax(getPrice(s[0], list), getPrice(s[1], list), 0);
            } else {
                String[] s = new String[]{"硬卧上", "硬卧中", "硬卧下"};
                return getMax(getPrice(s[0], list), getPrice(s[1], list),
                        getPrice(s[2], list));
            }
        } else {
            return getPrice(type, list);
        }
    }

    /**
     * 获得给定类型的车票的最低价格
     *
     * @param type
     * @param list
     * @return
     */
    public static float getMinPrice(String type, List<SeatAssist> list) {
        if (type.equals(GAO_JI_RUAN_WO_TYPE) || type.equals(RUAN_WO_TYPE)
                || type.equals(YING_WO_TYPE)) {
            if (type.equals(GAO_JI_RUAN_WO_TYPE)) {
                String[] s = new String[]{"高级软卧上", "高级软卧下"};
                return getMin(getPrice(s[0], list), getPrice(s[1], list),
                        Float.MAX_VALUE);
            } else if (type.equals(RUAN_WO_TYPE)) {
                String[] s = new String[]{"软卧上", "软卧下"};
                return getMin(getPrice(s[0], list), getPrice(s[1], list),
                        Float.MAX_VALUE);
            } else {
                String[] s = new String[]{"硬卧上", "硬卧中", "硬卧下"};
                return getMin(getPrice(s[0], list), getPrice(s[1], list),
                        getPrice(s[2], list));
            }
        } else {
            return getPrice(type, list);
        }
    }

    public static float getMax(float num1, float num2, float num3) {
        return Math.max(Math.max(num1, num2), num3);
    }

    public static float getMin(float num1, float num2, float num3) {
        return Math.min(Math.min(num1, num2), num3);
    }

    public static float getPrice(String type, List<SeatAssist> list) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (type.equals(list.get(i).getName())) {
                return list.get(i).getPrice();
            }
        }
        return -1;
    }

    public static class SeatAssist {
        private int id;
        private String name;
        private float price;

        public SeatAssist() {
            super();
            // TODO Auto-generated constructor stub
        }

        public SeatAssist(int id, String name, float price) {
            super();
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

    }
}
