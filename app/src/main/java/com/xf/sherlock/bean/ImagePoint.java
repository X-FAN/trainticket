package com.xf.sherlock.bean;

public class ImagePoint {
    private int x;
    private int y;

    public ImagePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}