package com.example.unmesh.locateme;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BuildingDetailActivity extends AppCompatActivity implements JsonDisplayInterface{

    String bldgName;
    String address;
    LocationManager locationManager;
    LocationListener locationListener;

    protected double latitudes, longitudes,streetViewLat,streetViewlong;

    Button locationButton;

    private final static String API_KEY = "AIzaSyBVKQHFs6mED_wYT2U9fP-TlVbPjoQcdgg";
    private final static  String base_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";

    /**************************************************************************************************
     //                       Function For On Create Method                                          //
     **************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        Intent intent = getIntent();
        bldgName = intent.getStringExtra("bldgName");
        final String latitude = intent.getStringExtra("latitude");
        final String longitude = intent.getStringExtra("longitude");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,85,162)));
        address = getAddress(bldgName);
        locationButton = (Button) findViewById(R.id.getLocation);


        Button locationButton = (Button) findViewById(R.id.getLocation);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation(latitude,longitude);
            }
        });
        getRequest(address,latitude,longitude);


    }




    /**************************************************************************************************
     //                         Gives Location Updates                                                //
     **************************************************************************************************/


    public void getLocation(String latitude,String longitude){
        Intent streetView = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll="+ streetViewLat+","+streetViewlong+"&cbp=1,99.56,,1,-5.27&mz=21"));
        startActivity(streetView);
    }

    /**************************************************************************************************
    //                       Function For Getting The Address of building                             //
     **************************************************************************************************/

    public String getAddress(String bldgName){
        String address;
        ImageView builingPhoto = (ImageView) findViewById(R.id.buildingImage);
        switch (bldgName){
            case "EB":  address = "San Jose State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.eb);
                        setTitle("Engineering Building");
                        streetViewLat = 37.337114;
                        streetViewlong = -121.880583;
                        break;
            case "SPG": address = "San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.spg);
                        setTitle("South Parking Garage");
                        streetViewLat = 37.332715;
                        streetViewlong = -121.880981;
                        break;
            case "BBC": address = "Boccardo Business Complex, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.bbc);
                        streetViewLat = 37.336351;
                        streetViewlong = -121.879128;
                        setTitle("Boccardo Business Complex");

                        break;
            case "MLK": address = "Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.mlk);
                        streetViewLat = 37.335193;
                        streetViewlong = -121.884366;
                        setTitle("King Library");

                        break;
            case "SU": address = "Student Union Building, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.su);
                        streetViewLat = 37.336262;
                        streetViewlong = -121.880570;
                        setTitle("Student Union Building");

                        break;
            case "YUH": address = "Yoshihiro Uchida Hall, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.yuh);
                        streetViewLat = 37.333799;
                        streetViewlong = -121.884102;
                        setTitle("Yoshihiro Uchinda Hall");
                        break;
            default:
                address = bldgName;
                break;
        }

        return address;
    }



    public void getRequest(String destinationAddress, String latitude, String longitude) {
        StringBuilder result = new StringBuilder();
        destinationAddress = destinationAddress.replaceAll(" ","+");
        System.out.println(latitude+longitude);
       // String hitURL = base_URL+"37.3366265,-121.8875235"+"&destinations="+destinationAddress+"&mode=walking&key="+API_KEY;
        String hitURL = base_URL+latitude+","+longitude+"&destinations="+destinationAddress+"&mode=walking&key="+API_KEY;
        new getDistanceAsyncTask(BuildingDetailActivity.this).execute(hitURL);
    }

    @Override
    public void display(String jsonDetails) {
        try{
            String resultArray [] = jsonDetails.split("/");
            String destinationAddress = resultArray[0];
            String originAddress = resultArray[1];
            String timeDuration = resultArray[2];
            String distance = resultArray[3];

            TextView destination= (TextView) findViewById(R.id.destAddr);
            destination.append(destinationAddress);


            TextView distanceField = (TextView) findViewById(R.id.travelDistanceField);
            distanceField.append(distance);


            TextView timeField = (TextView) findViewById(R.id.timeDurationField);
            timeField.append(timeDuration);


        }catch (Exception ex){
            ex.printStackTrace();
        }



    }



}


