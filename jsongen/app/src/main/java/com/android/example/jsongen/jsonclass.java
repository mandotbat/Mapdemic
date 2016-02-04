package com.android.example.jsongen;

/**
 * Created by naman_000 on 04-02-16.
 */
public class jsonclass {

    String hospitalName;
    String disease;
    double latitude, longitude;
    String type;

    public jsonclass(String hospitalName, String disease, double latitude, double longitude) {
        this.hospitalName = hospitalName;
        this.disease = disease;
        this.latitude = latitude;
        this.longitude = longitude;
        type = "point";
    }
}
