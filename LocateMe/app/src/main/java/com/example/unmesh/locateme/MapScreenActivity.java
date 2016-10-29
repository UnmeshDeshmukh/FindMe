package com.example.unmesh.locateme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
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
    static double LEFTLAT = 37.335797;
    static double LEFTLONG = -121.885934;
    static double RIGHTLAT = 37.334568;
    static double RIGHTLONG = -121.876514;
    static int IMAGEX = 660;
    static int IMAGEY = 694;
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

        mapActivityLayoutview = new MapActivityLayout(this);
        setContentView(mapActivityLayoutview);


        Intent intentDetails = getIntent();
        latitude = intentDetails.getStringExtra("latitude");
        longitude = intentDetails.getStringExtra("longitude");
      //  int [] location = new int[2];




//        campusMap.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//
//            xCoordinate =  (int)motionEvent.getX();
//            yCoordinate =   (int)motionEvent.getY();
//            int touchedColor = getColorOnTouch(R.id.invisibleImage, xCoordinate, yCoordinate);
//            String buildingName = getBuildingName(touchedColor);
//            if(!buildingName.equals("Nope")){
//                Intent intent = new Intent(MapScreenActivity.this,BuildingDetailActivity.class);
//                intent.putExtra("bldgName",buildingName);
//                intent.putExtra("latitude",latitude);
//                intent.putExtra("longitude",longitude);
//                startActivity(intent);
//            }else{
//                //System.out.println("Not the mentioned building");
//            }
//
//            return false;
//
//        }
//    });


      //  showCurrentLoc(Double.parseDouble(latitude),Double.parseDouble(longitude),campusMap);



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria,false);
//         Location location = locationManager.getLastKnownLocation(provider);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lng = location.getLongitude();
                mapActivityLayoutview.setCoordinates(lat,lng);
                System.out.println(lat+"at the map screen\n"+lng);
                //setCoordinates(lat,lng);
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








    }

/**************************************************************************************************
                                  Function For Getting Color
 **************************************************************************************************/
    public int getColorOnTouch(int imageId, int X, int Y){
        ImageView newImg = (ImageView) findViewById(imageId);
        newImg.setDrawingCacheEnabled(true);
        Bitmap colorSpots = Bitmap.createBitmap(newImg.getDrawingCache());
        newImg.setDrawingCacheEnabled(false);
        return colorSpots.getPixel(X,Y);
    }
/**************************************************************************************************
                                 Function For Matching the color
 **************************************************************************************************/
    public boolean matchColor(int colorValue,int touchedColor){


        if(Math.abs(Color.red(touchedColor)-Color.red(colorValue))>30){
            return false;
        }else if(Math.abs(Color.green(touchedColor)-Color.green(colorValue))>30){
            return false;
        }else if(Math.abs(Color.blue(touchedColor)-Color.blue(colorValue))>30)
            return false;

        return true;
    }
    /**************************************************************************************************
                                Function For Getting the Building Name
     **************************************************************************************************/
    public String getBuildingName(int colorValue) {
        if(matchColor(colorValue,Color.RED)){
            return "EB";
        }else if(matchColor(colorValue,Color.MAGENTA)){
            return "SPG";
        }else if(matchColor(colorValue,Color.GREEN)){
            return "BBC";
        }else if(matchColor(colorValue,Color.YELLOW)){
            return "MLK";
        }else if(matchColor(colorValue,Color.GRAY)){
            return "SU";
        }else if(matchColor(colorValue,Color.CYAN)){
            return "YUH";
        }
        return "Nope";
    }



    /**************************************************************************************************
     Shows Current location On Map
     **************************************************************************************************/

    public void showCurrentLoc(double userLat, double userLong,ImageView campusMap){

        double posX = Math.abs(IMAGEX * (userLong-LEFTLONG)/(RIGHTLONG - LEFTLONG));
        double posY = Math.abs(IMAGEY-(IMAGEY * (userLat - RIGHTLAT))/(LEFTLAT-RIGHTLAT));
        Log.i("Coord","X: "+posX+" Y: "+posY);
//      Drawingview dv = new Drawingview(this.getApplicationContext());
////        Canvas canvas = new Canvas();
////        canvas.setBitmap(bitmap);
//        dv.setCoords(posX,posY);
//        setContentView(dv);
////        dv.draw(canvas);

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
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, SearchableActivity.class)));
        searchView.setIconifiedByDefault(false);*/

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

        if(query.equalsIgnoreCase("King Library"))
        {

            Log.i("Place","Kings Library selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)183.12012,(float)631.3281,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;
        }
        else if(query.equalsIgnoreCase("Engineering Building")){

            Log.i("Place","Engineering Building selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)852.53906,(float)706.3281,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }
        else if(query.equalsIgnoreCase("Yoshihiro Uchida Hall")){
            Log.i("Place","Yoshihiro Uchida Hall selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)169.10156,(float)1361.5625,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }
        else if(query.equalsIgnoreCase("Student Union")){


            Log.i("Place","Student Union selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)852.53906,(float)980.3906,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }
        else if(query.equalsIgnoreCase("BBC")){


            Log.i("Place","BBC selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)1246.8164,(float)1177.5,true);
            setContentView(mapActivityLayoutview);
            mapActivityLayoutview.setBackgroundResource(R.drawable.campusmap);
            return true;

        }else if(query.equalsIgnoreCase("South Parking Garage")){


            Log.i("Place","South Parking Garage selected!!!!");
            mapActivityLayoutview = new MapActivityLayout(this,(float)539.3408,(float)1869.7656,true);
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



    /**************************************************************************************************
     Setting the coordinates
     **************************************************************************************************/



//    public void setCoordinates(Location location){
//
//        coordinates[0] = location.getLatitude();
//        coordinates[1] = location.getLongitude();
//        System.out.println("Setting them:"+coordinates[0]+coordinates[1]);
//
//
//    }

    /**************************************************************************************************/
    //                         Get the Location coordinates                                           //
    /**************************************************************************************************/
//
//    public double[] getCurrentCoordinates() {
//        System.out.println("Printing the coordinates before setting them: " +coordinates[0] +"  " + coordinates[1]);
//        Log.i("","Coordinates : "+coordinates[0]+" "+coordinates[1]);
//      //  coordinates[0] = lat;
//       // coordinates[1] = lng;
//
//        return  coordinates;
//
//
//    }
}
