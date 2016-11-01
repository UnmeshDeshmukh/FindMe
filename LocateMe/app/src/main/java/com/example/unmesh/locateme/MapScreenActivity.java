package com.example.unmesh.locateme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import static android.R.attr.defaultValue;

public class MapScreenActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    int xCoordinate;
    int yCoordinate;
    String buildingName;
    String latitude;
    String longitude;
    static double LEFTLAT = 37.337711;
    static double LEFTLONG = -121.887463;
    static double RIGHTLAT = 37.334563;
    static double RIGHTLONG = -121.876462;
    static int IMAGEX = 1440;
    static int IMAGEY = 2560;
    static ImageView campusMap, invisibleImage;
    ImageView EnggImageView, SUImageView, MLKImageView, BBCImageView, SGImageView, YCHImageView;
    LocationManager locationManager;
    LocationListener locationListener;
    double[] coordinates = new double[2];
    double lat;
    double lng;
    String provider;
    MapActivityLayout mapActivityLayoutview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,85,162)));
        mapActivityLayoutview = new MapActivityLayout(this);
        setContentView(mapActivityLayoutview);

        System.out.println("The Co-ordinates here are:=----="+lat+"   "+lng);
        Intent intentDetails = getIntent();
        latitude = intentDetails.getStringExtra("latitude");
        longitude = intentDetails.getStringExtra("longitude");







      //  showCurrentLoc(Double.parseDouble(latitude),Double.parseDouble(longitude),campusMap);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria,false);

        Location tempLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        System.out.println("Lang:::"+lng+"Lat::::"+lat);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lng = location.getLongitude();
                mapActivityLayoutview.setCoordinates(lat,lng);
                //mapActivityLayoutview.getXYOfCurrentLocation();
                System.out.println(lat+"at the map screen\n"+lng);
                //setCoordinates(lat,lng);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER , 100, 0, locationListener);
            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER , 100, 0, locationListener);

    }



    /**************************************************************************************************
     Setting Search Bar Menu
     **************************************************************************************************/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }



    /**************************************************************************************************
     Search Bar Implementation
     **************************************************************************************************/

    @Override
    public boolean onQueryTextSubmit(String query) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        if(query.equalsIgnoreCase("King Library")||query.equalsIgnoreCase("KING")||query.equalsIgnoreCase("MLK"))
        {

            Log.i("Place","Kings Library selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)183.12012,(float)631.3281,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;
        }
        else if(query.equalsIgnoreCase("Engineering Building")||query.equalsIgnoreCase("ENG")){
            Log.i("Place","Engineering Building selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)852.53906,(float)706.3281,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;
        }
        else if(query.equalsIgnoreCase("Yoshihiro Uchida Hall")||query.equalsIgnoreCase("YUH")){
            Log.i("Place","Yoshihiro Uchida Hall selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)179.07715,(float)1310.5469,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }
        else if(query.equalsIgnoreCase("Student Union")||query.equalsIgnoreCase("SU")){
            Log.i("Place","Student Union selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)832.53906,(float)930.3906,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;
        }
        else if(query.equalsIgnoreCase("BBC")){


            Log.i("Place","BBC selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)1216.8164,(float)1127.5,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }else if(query.equalsIgnoreCase("South Parking Garage")||query.equalsIgnoreCase("SPG")){
            Log.i("Place","South Parking Garage selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)539.3408,(float)1829.7656,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }

        return false;
    }


    /**************************************************************************************************
     Handling the change in search bar
     **************************************************************************************************/


    @Override
    public boolean onQueryTextChange(String newText) {
        MapActivityLayout newMap = new MapActivityLayout(this,(float)202.10449,(float)706.3281,false);
        mapActivityLayoutview = new MapActivityLayout(this);
        setContentView(mapActivityLayoutview);
        return false;
    }

    /**************************************************************************************************
     Handling Request Permissions
     **************************************************************************************************/



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getLocation();
            }
                return;
        }
    }


    /**************************************************************************************************/
    //                         Gives Location Updates                                                //
    /**************************************************************************************************/

    public void getLocation(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0,locationListener);
    }



}
