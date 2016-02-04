package com.android.example.jsongen;

import java.util.Random;

/**
 * Created by naman_000 on 04-02-16.
 */
public class jsonclass {

    String hospitalName;
    String disease;
    double latitude, longitude;
    String type;

    public jsonclass(String hospitalName, String disease, double latitude, double longitude, Random r) {
        this.hospitalName = hospitalName;
        this.disease = disease;
        this.latitude = latitude+(r.nextFloat()*0.5)-0.25;
        this.longitude = longitude+(r.nextFloat()*0.5)-0.25;
        type = "point";
    }
}
