package com.example.unmesh.locateme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreenActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    double latitude;
    double longitude;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,85,162)));
        Button quitButton = (Button) findViewById(R.id.quitbutton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });


//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria,false);
////         Location location = locationManager.getLastKnownLocation(provider);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                latitude = location.getLatitude();
//                longitude = location.getLongitude();
//                System.out.println(latitude+"\n"+longitude);
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//            }
//        };

        Button mapScreenButton = (Button) findViewById(R.id.mapscreenbutton);
        mapScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this,MapScreenActivity.class);
//                intent.putExtra("latitude",Double.toString(latitude));
//                intent.putExtra("longitude",Double.toString(longitude));

                startActivity(intent);
//                finish();
            }
        });

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},1);
//            return;
//        }
//
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER , 100, 0, locationListener);


    }


    /**************************************************************************************************/
    //                        To Access the Request Permission                                         //
    /**************************************************************************************************/
//    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        switch (requestCode){
////            case 1: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
////                getLocation();
////            }
////                return;
////        }
////    }
//
//    /**************************************************************************************************/
//    //                         Gives Location Updates                                                //
//     /**************************************************************************************************/
//
//    public void getLocation(){
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,0,locationListener);
//    }



}
