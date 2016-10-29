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

    protected double latitudes, longitudes;

    Button locationButton;

    private final static String API_KEY = "AIzaSyBVKQHFs6mED_wYT2U9fP-TlVbPjoQcdgg";
    private final static  String base_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";

    /**************************************************************************************************
     //                       Function For On Create Method                                          //
     **************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        Intent intent = getIntent();
        bldgName = intent.getStringExtra("bldgName");
        final String latitude = intent.getStringExtra("latitude");
        final String longitude = intent.getStringExtra("longitude");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(229,168,35)));
        address = getAddress(bldgName);
        locationButton = (Button) findViewById(R.id.getLocation);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                latitudes = location.getLatitude();
                longitudes = location.getLongitude();


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

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

        Button locationButton = (Button) findViewById(R.id.getLocation);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation(latitude,longitude);
            }
        });
        getRequest(address,latitude,longitude);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //getLocation();
            }
                return;
        }
    }


    /**************************************************************************************************
     //                         Gives Location Updates                                                //
     **************************************************************************************************/


    public void getLocation(String latitude,String longitude){
        Intent streetView = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll="+ latitude+","+longitude+"&cbp=1,99.56,,1,-5.27&mz=21"));
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


                        break;
            case "SPG": address = " San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.spg);

                        setTitle("South Parking Garage");
                        break;
            case "BBC": address = " Boccardo Business Complex, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.bbc);

                        setTitle("Boccardo Business Complex");

                        break;
            case "MLK": address = "Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.mlk);

                        setTitle("King Library");

                        break;
            case "SU": address = "Student Union Building, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.su);

                        setTitle("Student Union Building");

                        break;
            case "YUH": address = "Yoshihiro Uchida Hall, San Jose, CA 95112";
                        builingPhoto.setBackgroundResource(R.drawable.yuh);

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


