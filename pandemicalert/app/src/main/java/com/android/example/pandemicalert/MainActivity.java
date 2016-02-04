package com.android.example.pandemicalert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.gson.Gson;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private TileOverlay heatmapOverlay;
    final private Context context = this;
    String jsoninput;
    DataClass[] data;
    String curDisease="All";
    ArrayList<Marker> markers = new ArrayList<>();
    int state=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar aBar = getSupportActionBar();

        aBar.setTitle("Overall Disease Watch");

        jsoninput = "[{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.335848859179688,\"longitude\":77.38499744322014,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.627536943782044,\"longitude\":77.05826754953576,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"AIIMS\",\"latitude\":28.503183306412126,\"longitude\":77.31951710753441,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.681892873005296,\"longitude\":76.00274819307327,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.651148668158722,\"longitude\":77.17067550208283,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.449234484495545,\"longitude\":76.94685982778626,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.328501819433594,\"longitude\":77.10087820008354,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.685409000743103,\"longitude\":77.43368341114235,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Fortis\",\"latitude\":28.316560267271424,\"longitude\":76.95682357862549,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.699590562643433,\"longitude\":77.22961767628746,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.66353594386406,\"longitude\":76.99348991309434,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.679577272653198,\"longitude\":77.36478419824181,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.491766354678344,\"longitude\":77.46196942721635,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.512396128134156,\"longitude\":75.95789221100807,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.39396624886818,\"longitude\":76.98651283179551,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.586304437636947,\"longitude\":77.18451883121071,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.525432359695053,\"longitude\":77.29777415319023,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.379869581417466,\"longitude\":76.06282484703064,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.614053658328245,\"longitude\":77.20671384908181,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.58605825787964,\"longitude\":77.24665718391495,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Fortis\",\"latitude\":28.314802526296997,\"longitude\":77.0111679048832,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.733587753295517,\"longitude\":77.31745080872116,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.34617950641937,\"longitude\":77.18010377084046,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.71424315524063,\"longitude\":77.51659502311287,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.58812557780571,\"longitude\":77.47797756587296,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.70067485295601,\"longitude\":77.42312966372681,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.741176557063675,\"longitude\":77.0456867985302,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.438948095993425,\"longitude\":77.10295269064903,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.752032996372606,\"longitude\":76.33160075716972,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.827777586064528,\"longitude\":77.02551632501107,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.603079340089035,\"longitude\":77.37423645045472,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Fortis\",\"latitude\":28.54785337931099,\"longitude\":77.03460644796448,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.31936227403946,\"longitude\":77.32360588099671,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"AIIMS\",\"latitude\":28.708223820881273,\"longitude\":77.38725757770538,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.70223835225525,\"longitude\":76.07029172949791,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.63096185647316,\"longitude\":77.13190637614441,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.36094647760811,\"longitude\":77.11055094175339,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.621699344634628,\"longitude\":77.38669650478897,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.365880003798676,\"longitude\":77.47411657955361,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.485927223028565,\"longitude\":77.19277276709633,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.707865875482177,\"longitude\":77.41045434995232,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.35706719870987,\"longitude\":76.36765587501526,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"AIIMS\",\"latitude\":28.368074179844285,\"longitude\":77.29901948742867,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.733951649306487,\"longitude\":77.22529005204468,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.580448358120346,\"longitude\":77.11206116393166,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.359442262710953,\"longitude\":76.960889062434,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.507687358917618,\"longitude\":77.18966876581268,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.78498049744148,\"longitude\":77.16457253076058,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.37808771085701,\"longitude\":77.46832682414589,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.678726326942062,\"longitude\":77.15753911657868,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.844994983754347,\"longitude\":77.1506180701397,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.377979935364152,\"longitude\":77.19838735632896,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"AIIMS\",\"latitude\":28.582141430334474,\"longitude\":77.3637858944416,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.629642716727446,\"longitude\":76.89275824405175,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.329982500791168,\"longitude\":77.33910982533035,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.362460425723267,\"longitude\":77.211923913023,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.494967332709503,\"longitude\":77.36520038630677,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.64641570218582,\"longitude\":77.05451726294976,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.574388852942658,\"longitude\":77.11007098939133,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.508005104146193,\"longitude\":76.8915529189251,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.76659213626213,\"longitude\":77.0297395506981,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.75306815023842,\"longitude\":76.21263149552345,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.77521081888504,\"longitude\":77.10190283452302,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.36464863144226,\"longitude\":77.17987378027154,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"AIIMS\",\"latitude\":28.4423615049881,\"longitude\":77.45325741343498,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"AIIMS\",\"latitude\":28.57754117365303,\"longitude\":77.0832623260498,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.48907647681198,\"longitude\":77.4897332064682,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.343645444749068,\"longitude\":77.44828080331116,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.48485346758194,\"longitude\":77.35062574540406,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"AIIMS\",\"latitude\":28.43479991074028,\"longitude\":77.12878352575302,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.570509554104234,\"longitude\":76.02266585998535,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.477897913412477,\"longitude\":76.43970206551552,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Fortis\",\"latitude\":28.507383285822296,\"longitude\":77.02826382830696,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.595418332876587,\"longitude\":77.16636886313515,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.478181472657393,\"longitude\":77.22490387355118,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.600665203414152,\"longitude\":77.08583336450081,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.60391905032463,\"longitude\":77.47854183699799,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.704341432679367,\"longitude\":77.43039046313477,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.327584108937454,\"longitude\":77.31357567097855,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.864423445544432,\"longitude\":77.18426706768494,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.33115148431244,\"longitude\":77.24408679321365,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.654154619811248,\"longitude\":77.11830820833474,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.734489799694444,\"longitude\":76.41557645969391,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.55601370449486,\"longitude\":76.33725860886574,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.739233554558183,\"longitude\":76.09677773885727,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.38486503684349,\"longitude\":77.25860270057946,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.52261547398529,\"longitude\":77.48325313849983,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.749056469916916,\"longitude\":77.39873026533661,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.433626712517167,\"longitude\":76.1774670498848,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.445900143341447,\"longitude\":77.39577731661797,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.66373129808731,\"longitude\":77.04865112100869,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.55349542820282,\"longitude\":77.37605386172562,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.429912439665983,\"longitude\":77.08018501974564,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.802825561392975,\"longitude\":77.20807369138909,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.612028679488372,\"longitude\":77.26864530836373,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.484227887023163,\"longitude\":77.32889239575577,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.651067864417648,\"longitude\":77.25022967382012,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.710644286393737,\"longitude\":77.10363118572769,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.323214201988602,\"longitude\":76.91162969782906,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.31502947098198,\"longitude\":77.19414140894966,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Fortis\",\"latitude\":28.462240814031983,\"longitude\":77.25851335242348,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.70990328144989,\"longitude\":77.40411429329453,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"AIIMS\",\"latitude\":28.373828680471803,\"longitude\":77.0482064502716,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"AIIMS\",\"latitude\":28.435255439237977,\"longitude\":77.04781606965065,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.43775249671898,\"longitude\":77.44480021520195,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.744416446642305,\"longitude\":76.42045352034569,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.635692647776793,\"longitude\":76.88987406469803,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.79149701365013,\"longitude\":76.80285435296517,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.6294206197258,\"longitude\":77.38318674488602,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.371099811315155,\"longitude\":77.19566403551636,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"AIIMS\",\"latitude\":28.736357839064027,\"longitude\":76.99249524049759,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.770972610668565,\"longitude\":76.2812261598587,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.55969200682602,\"longitude\":77.0931175820404,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.453886261819076,\"longitude\":77.13942181264191,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.560318809270477,\"longitude\":77.21744899673996,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.39391294236603,\"longitude\":75.98933908514977,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Fortis\",\"latitude\":28.339149682583237,\"longitude\":77.29048918798523,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.51072201247635,\"longitude\":76.14948067240715,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.74191488944359,\"longitude\":77.46828039076043,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"AIIMS\",\"latitude\":28.358975441412355,\"longitude\":77.0144410150528,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.342915265621567,\"longitude\":77.35508733108597,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.74291721101227,\"longitude\":76.00952568821907,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.38006773477974,\"longitude\":77.32053863123016,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.36633654558487,\"longitude\":77.08646669183999,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Fortis\",\"latitude\":28.644867657484436,\"longitude\":77.07198639824944,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.535429707406234,\"longitude\":77.00749307071,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.613126140355682,\"longitude\":77.14935340209541,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Fortis\",\"latitude\":28.29445412641945,\"longitude\":77.36538611963348,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.4733539136631,\"longitude\":77.44198062216073,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.662304938988115,\"longitude\":76.2000548558712,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.461936681332016,\"longitude\":77.03870808199005,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.628610036013793,\"longitude\":77.41014503632813,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.476887277445982,\"longitude\":77.17487668372613,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.486370316384505,\"longitude\":77.08722340260773,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.563181779465864,\"longitude\":77.13418462254029,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.482597779750442,\"longitude\":77.11580048723755,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.461820335344697,\"longitude\":76.3303523378849,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.414645007489394,\"longitude\":77.11628573333054,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.63698073350258,\"longitude\":77.18508984234047,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.548051616776657,\"longitude\":77.13521878506852,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.63469134889908,\"longitude\":77.42167274739457,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.34788992129631,\"longitude\":77.1025020637722,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"AIIMS\",\"latitude\":28.803210766271974,\"longitude\":77.08735022120476,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.77300730462494,\"longitude\":76.03346643023491,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.34218456469841,\"longitude\":77.3159038582058,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.66361332054558,\"longitude\":76.04073214702606,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.51326783665199,\"longitude\":76.88686867929917,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.59103739376488,\"longitude\":76.41923099927902,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.375339201770018,\"longitude\":76.93460985399705,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.57732107364006,\"longitude\":77.43983383562279,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.608070067248534,\"longitude\":77.14440720654946,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.757273516295623,\"longitude\":77.29293843303948,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.369770488370133,\"longitude\":77.03432934071732,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.3976590151783,\"longitude\":77.34995974822579,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"AIIMS\",\"latitude\":28.623883606151963,\"longitude\":77.09567007594109,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.770200472592926,\"longitude\":77.1984566621357,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.669296524165343,\"longitude\":77.47268789206773,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Fortis\",\"latitude\":28.701034216942215,\"longitude\":77.04910733416634,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.449493796781923,\"longitude\":76.38221126966477,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.667801142887498,\"longitude\":76.37700802259445,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.559380433200072,\"longitude\":77.25758143459588,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"AIIMS\",\"latitude\":28.398827196316148,\"longitude\":77.33259946279526,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.519813400029754,\"longitude\":77.19470505638657,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.621585618972397,\"longitude\":77.16487170978127,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"AIIMS\",\"latitude\":28.709188522056962,\"longitude\":77.10307464175224,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.7061260255558,\"longitude\":77.03907611166268,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.484308204291533,\"longitude\":77.2123303929451,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.761761398271943,\"longitude\":77.25747332148552,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.64627606821022,\"longitude\":77.47879024072228,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.776077888093184,\"longitude\":77.17913221694451,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.40857968878708,\"longitude\":77.52688239737091,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.575776307644272,\"longitude\":77.15898223832207,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.48443931456032,\"longitude\":76.18909290604591,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.474250506877517,\"longitude\":77.09317438526688,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"AIIMS\",\"latitude\":28.59457418437424,\"longitude\":77.28211802415848,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.41365535211525,\"longitude\":77.09006093664703,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.461183082580185,\"longitude\":77.28987972660599,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.272686748566056,\"longitude\":77.23101513698654,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.71849270784683,\"longitude\":77.27084558402329,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.57276385196228,\"longitude\":76.87469225503426,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.55951607342186,\"longitude\":75.97079923920631,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.762248090146254,\"longitude\":77.42191450988084,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.395480773530196,\"longitude\":76.89146327353936,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"AIIMS\",\"latitude\":28.670686573938752,\"longitude\":77.07766473464966,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.304381727041626,\"longitude\":77.01250799611168,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.601680141210174,\"longitude\":77.15358026547966,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.809295377371978,\"longitude\":77.2133890906456,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.718077092232132,\"longitude\":77.29718499496536,\"type\":\"point\"},{\"disease\":\"Malaria\",\"hospitalName\":\"AIIMS\",\"latitude\":28.596269668297197,\"longitude\":76.97898879818916,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.760804048857878,\"longitude\":77.13853066541176,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"National Heart Institute\",\"latitude\":28.542340120900345,\"longitude\":77.05135221864892,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Dwarka Hospital\",\"latitude\":28.83593284257431,\"longitude\":77.1995600102089,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Moolchand Hospital\",\"latitude\":28.517035952447127,\"longitude\":77.11373030339509,\"type\":\"point\"},{\"disease\":\"Swine Flu\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.343575489043808,\"longitude\":77.08179854078827,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.421823174909974,\"longitude\":76.21499219708443,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Fortis\",\"latitude\":28.69383686667862,\"longitude\":77.34502964332657,\"type\":\"point\"},{\"disease\":\"STDs\",\"hospitalName\":\"Fortis\",\"latitude\":28.708794026436234,\"longitude\":77.17519949987488,\"type\":\"point\"},{\"disease\":\"Chikunguniya\",\"hospitalName\":\"Sharma Nuring Home\",\"latitude\":29.32913392897072,\"longitude\":75.97471583061218,\"type\":\"point\"},{\"disease\":\"Dengue\",\"hospitalName\":\"Fortis\",\"latitude\":28.46347996479454,\"longitude\":76.93767782166557,\"type\":\"point\"},{\"disease\":\"Cholera\",\"hospitalName\":\"Apollo Hospitals\",\"latitude\":28.76365627002678,\"longitude\":77.21484332008896,\"type\":\"point\"}]";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_search);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence diseases[] = new CharSequence[] {"All", "Dengue", "Malaria", "STDs", "Chikunguniya", "Cholera", "Swine Flu"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pick a Disease");
                builder.setItems(diseases, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(aBar!=null) {
                            aBar.setTitle(diseases[which]);
                        }
                        curDisease = diseases[which].toString();
                        createOnDiseaseChange();
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
        //map.addMarker(new MarkerOptions().position(delhi).title("Marker in Sydney"));
        Gson g = new Gson();
        data = g.fromJson(jsoninput, DataClass[].class);

        coord[] coords = new coord[] {new coord(28.5285546,77.2883731),new coord(28.5665994,77.207425),new coord(29.5665994,76.207425),
                new coord(28.5653707,77.2309153),new coord(28.6166827,77.0294173),new coord(28.5193969,77.1254208),new coord(28.5573607,77.243497)};

        for(int i=0;i<coords.length;i++) {
            LatLng temp = new LatLng(coords[i].getLatitude(), coords[i].getLongitude());
            markers.add(map.addMarker(new MarkerOptions().position(temp)));
        }
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                createMap();
            }
        });
        map.animateCamera(CameraUpdateFactory.newLatLng(delhi));
    }

    private void createMap() {
        CameraPosition cameraPosition = map.getCameraPosition();
        if(cameraPosition.zoom <= 8) {
            if(heatmapOverlay!=null){
                heatmapOverlay.setVisible(false);
                heatmapOverlay=null;
            }
           /* for(Marker marker: markers) {
                marker.setVisible(true);
            }*/
        }
        else {
            /*for(Marker marker: markers) {
                marker.setVisible(false);
            }*/
            if(heatmapOverlay==null) {
                createHeatMap();
            }
            else {
                heatmapOverlay.setVisible(true);
            }
        }
    }

    void createOnDiseaseChange() {
        if(heatmapOverlay!=null) {
            heatmapOverlay.remove();
            heatmapOverlay = null;
        }
        createMap();
    }

    private void createHeatMap() {

        ActionBar aBar = getSupportActionBar();
        if(aBar!=null) {
            if (curDisease.equals("All"))
                aBar.setTitle("Overall Disease Watch");
            else
                aBar.setTitle(curDisease);
        }

        if(heatmapOverlay!=null)
            heatmapOverlay.remove();

        ArrayList<LatLng> locations;
        locations = new ArrayList<>();

        for(int i=0;i<data.length;i++) {
            LatLng temp;
            if(curDisease.equals("All") | data[i].disease.equals(curDisease)) {
                temp = new LatLng(data[i].getLatitude(), data[i].getLongitude());
                locations.add(temp);
            }
        }

        HeatmapTileProvider heatmapProvider;
        heatmapProvider = new HeatmapTileProvider.Builder().data(locations).build();
        heatmapProvider.setRadius(30);
        heatmapOverlay = map.addTileOverlay(new TileOverlayOptions().tileProvider(heatmapProvider));
    }

}
