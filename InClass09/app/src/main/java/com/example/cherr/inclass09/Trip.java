package com.example.cherr.inclass09;

/**
 * Created by cherr on 27-03-2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trip {
    public Trip(List<points> pointsList, String title) {
        this.points = pointsList;
        this.title = title;
    }
    @SerializedName("points")
    List<points> points;

    String title;

    public List<points> getPointsList() {
        return points;
    }

    public void setPointsList(List<points> pointsList) {
        this.points = pointsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Trip{" + "pointsList=" + points + ", title='" + title + '\'' + '}';
    }
}
