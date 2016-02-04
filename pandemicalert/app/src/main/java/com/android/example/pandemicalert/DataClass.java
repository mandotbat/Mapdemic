package com.android.example.pandemicalert;

import com.google.gson.annotations.SerializedName;

/**
 * Created by naman_000 on 04-02-16.
 */

public class DataClass {

    @SerializedName("hospitalName")
    String hospitalName;

    @SerializedName("disease")
    String disease;

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;

    @SerializedName("type")
    String type;

    public DataClass(String hospitalName, String disease, double latitude, double longitude, String type) {
        this.hospitalName = hospitalName;
        this.disease = disease;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
