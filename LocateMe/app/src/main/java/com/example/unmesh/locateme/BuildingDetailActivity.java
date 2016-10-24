package com.example.unmesh.locateme;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.location.LocationManager.GPS_PROVIDER;

public class BuildingDetailActivity extends AppCompatActivity {

    String bldgName;
    String address;
    LocationManager locationManager;
    LocationListener locationListener;
    protected boolean setEnabledFlag = false;
    protected double latitude, longitude;
    Button locationButton;

    /**************************************************************************************************
     //                       Dialog Box for user settings                                         //
     **************************************************************************************************/

    public void viewAlert() {
        final AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);
        dialogBox.setTitle("Location Enable")
                .setMessage("Location Settings are off.\n Please Allow the settings to Enable location for this app").setPositiveButton("Location Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(BuildingDetailActivity.this, MapScreenActivity.class);
                        startActivity(intent);
                    }
                });
        dialogBox.show();
    }


    /**************************************************************************************************
     //                       Function For On Create Method                                          //
     **************************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_detail);
        Intent intent = getIntent();
        bldgName = intent.getStringExtra("bldgName");
        address = getAddress(bldgName);
        locationButton = (Button) findViewById(R.id.getLocation);
        final TextView txtView = (TextView) findViewById(R.id.locDetails);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txtView.append("\n Location Co-ordinates\n Latitude: " + location.getLatitude()+"\nLongitude: "+location.getLatitude());
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
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);


        //displayDetails(bldgName);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getLocation();
            }
                return;
        }
    }


    public void getLocation(){
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,0,locationListener);
            }
        });

    }

    /**************************************************************************************************
    //                       Function For Getting The Address of building                             //
     **************************************************************************************************/

    public String getAddress(String bldgName){
        String address;
        switch (bldgName){
            case "EB":  address = "San José State University Charles W. Davidson College of Engineering, 1 Washington Square, San Jose, CA 95112";
                        break;
            case "SPG": address = "San Jose State University South Garage, 330 South 7th Street, San Jose, CA 95112";
                        break;
            case "BBC": address = "Boccardo Business Complex, San Jose, CA 95112";
                        break;
            case "MLK": address = "Dr. Martin Luther King, Jr. Library, 150 East San Fernando Street, San Jose, CA 95112";
                        break;
            case "SU": address = "Student Union Building, San Jose, CA 95112";
                        break;
            case "YUH": address = "Yoshihiro Uchida Hall, San Jose, CA 95112";
                        break;
            default:
                address = bldgName;
                break;
        }
        return address;
    }

    public void displayDetails(String destinationAddress){
        if(setEnabledFlag){

        }
    }


}
