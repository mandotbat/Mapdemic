package com.android.example.pandemicalert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private TileOverlay heatmapOverlay;
    final private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pandemic");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_search);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence diseases[] = new CharSequence[] {"Dengue", "Malaria", "Typhoid", "STDs"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pick a Disease");
                builder.setItems(diseases, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createHeatMap(diseases[which]);
                    }
                });
                builder.show();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_center);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map!=null) {
                    // Add a marker in Delhi and move the camera
                    LatLng delhi = new LatLng(28.6139, 77.2090);
                    //map.addMarker(new MarkerOptions().position(delhi).title("Marker in Sydney"));
                    map.animateCamera(CameraUpdateFactory.newLatLng(delhi));
                }
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        // Add a marker in Delhi and move the camera
        LatLng delhi = new LatLng(28.6139, 77.2090);
        map.addMarker(new MarkerOptions().position(delhi).title("Marker in Sydney"));
        map.animateCamera(CameraUpdateFactory.newLatLng(delhi));
        createHeatMap("hi");
    }

    private void createHeatMap(CharSequence disease) {

        if(heatmapOverlay!=null){
            heatmapOverlay.remove();
        }
        ArrayList<LatLng> locations = new ArrayList<>();
        Random r = new Random();

        for(int i=0;i<2000;i++) {
            LatLng temp = new LatLng(r.nextFloat()*30+10, r.nextFloat()*25+65);
            locations.add(temp);
        }

        HeatmapTileProvider heatmapProvider;
        heatmapProvider = new HeatmapTileProvider.Builder().data(locations).build();
        heatmapOverlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(heatmapProvider));
    }

}
