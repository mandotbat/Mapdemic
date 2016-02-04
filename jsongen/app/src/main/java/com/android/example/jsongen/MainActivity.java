package com.android.example.jsongen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText json, times;
    String[] hosp = new String[] {"Apollo Hospitals", "AIIMS", "Sharma Nuring Home",
            "Moolchand Hospital", "Dwarka Hospital", "Fortis", "National Heart Institute"};
    coord[] coords = new coord[] {new coord(28.5285546,77.2883731),new coord(28.5665994,77.207425),new coord(29.5665994,76.207425),
            new coord(28.5653707,77.2309153),new coord(28.6166827,77.0294173),new coord(28.5193969,77.1254208),new coord(28.5573607,77.243497)};
    String[] disease = new String[] {"Dengue", "Malaria", "STDs", "Chikunguniya", "Cholera", "Swine Flu"};
    Random r = new Random();
    Gson x = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json = (EditText) findViewById(R.id.json);
        times = (EditText) findViewById(R.id.times);

    }

    public void generate(View view) {
        ArrayList<jsonclass> list = new ArrayList<>();
        for(int i=0;i<Integer.valueOf(times.getText().toString());i++) {
            int h, d;
            h = r.nextInt()%7;
            if(h<0)
                h=h*(-1);
            d = r.nextInt()%6;
            if(d<0)
                d=d*(-1);
            list.add(new jsonclass(hosp[h],disease[d], coords[h].getLatitude(), coords[h].getLongitude()));
        }

        json.setText(x.toJson(list));
    }
}
