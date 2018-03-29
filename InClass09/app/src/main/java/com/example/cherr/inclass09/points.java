package com.example.cherr.inclass09;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cherr on 27-03-2018.
 */

public class points {
    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public points(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Points{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
}

