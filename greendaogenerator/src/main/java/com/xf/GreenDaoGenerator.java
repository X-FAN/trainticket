package com.xf;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.xf.greendao");
        schema.enableKeepSectionsByDefault();
        addStation(schema);
        new DaoGenerator().generateAll(schema, "E:/trainticket/trainticket/app/src/main/java-gen");
    }

    private static void addStation(Schema schema) {
        Entity station = schema.addEntity("Station");
        station.addStringProperty("stationName");
        station.addStringProperty("stationCode");
        station.addStringProperty("fullPY");
        station.addStringProperty("shortPY");
        station.addStringProperty("section");
        station.addBooleanProperty("isShow");
    }

}

