package com.example.unmesh.locateme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by tanvi on 10/27/2016.
 */
public class MapActivityLayout extends View {

    float drawX, drawY;
    static boolean isSearch = false;
    static boolean isOnCampus = true;
    double posX,posY;
    static double LEFTLAT = 37.337209;
    static double LEFTLONG = -121.887048;
    static double RIGHTLAT = 37.334530;

    static double RIGHTLONG =-121.876384;
    int IMAGEX = 660;
    int IMAGEY = 694;

    float searchX,searchY;
    MapScreenActivity mapScreenActivity = new MapScreenActivity();
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude;
    double longitude;

    public MapActivityLayout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.campusmap);
    //    getWindowSize();
    }
    public MapActivityLayout(Context context,float searchX, float searchY,boolean isSearch){
        super(context);
        this.searchX = searchX;
        this.searchY = searchY;
        this.isSearch = isSearch;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xCoordinate = event.getX();
        float yCoordinate = event.getY();


        Log.i("Coord", "X : " + xCoordinate + " Y : " + yCoordinate);

        if((xCoordinate>=741.48926&&xCoordinate<=938.584)&&(yCoordinate>=590.3125&&yCoordinate<=899.375)) {
            Log.i("", "EB");
            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","EB");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }else if((xCoordinate>=144.05273&&xCoordinate<=282.17285)&&(yCoordinate>=589.2969&&yCoordinate<=848.3594)){
            Log.i("", "MLK");


            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","MLK");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }else if((xCoordinate>=1155.7167&&xCoordinate<=1287.8174)&&(yCoordinate>=1089.4531&&yCoordinate<=1229.6776)){
            Log.i("", "BBC");


            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","BBC");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }else if((xCoordinate>=435.27832&&xCoordinate<=692.4463)&&(yCoordinate>=1727.6563&&yCoordinate<=1947.7344)){
            Log.i("", "SPG");


            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","SPG");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }else if((xCoordinate>=113.07129&&xCoordinate<=269.16504)&&(yCoordinate>=1231.4844&&yCoordinate<=1448.5938)){
            Log.i("", "YUH");


            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","YUH");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }else if((xCoordinate>=725.4492&&xCoordinate<=920.61035)&&(yCoordinate>=929.375&&yCoordinate<=1048.4375)){
            Log.i("", "SU");


            Intent intent = new Intent(getContext(), BuildingDetailActivity.class);
            intent.putExtra("bldgName","SU");
            intent.putExtra("latitude",Double.toString(latitude));
            intent.putExtra("longitude",Double.toString(longitude));
            getContext().startActivity(intent);
        }



        return true;

    }

    /**************************************************************************************************
     On Draw Method to plot a dot for circle and marking the search pin
     **************************************************************************************************/


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getXYOfCurrentLocation();
        if (latitude<37&&longitude<-121){
            isOnCampus = false;
        }else if(isOnCampus){
          try {

          //    Log.i("X&Y in onDraw method","xxxxxx:"+posX+"yyyyy:"+posY);
              Paint paint = new Paint();
              paint.setColor(Color.RED);
              paint.setStyle(Paint.Style.FILL);
              canvas.drawCircle((float) posX,(float) posY,10, paint);
              invalidate();
          }catch (Exception ex){
              ex.printStackTrace();
          }
        }
        if(isSearch){
            try{

                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.pin);
                Paint paint = new Paint();
                paint.setColor(Color.CYAN);
                canvas.drawBitmap(bitmap,searchX, searchY,paint);
                invalidate();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }

    }

    /**************************************************************************************************
     Converting the lat long to current position on image
     **************************************************************************************************/



    public void getXYOfCurrentLocation(){
//double userLat,double userLong
        double userLat =37.333980;//=latitude;
        double userLong =-121.884035;//= longitude;
//        double bottom = RIGHTLAT * (Math.PI / 180);
//        double top = LEFTLAT * (Math.PI / 180);
//        double left = LEFTLONG * (Math.PI / 180);
//        double right = RIGHTLONG * (Math.PI / 180);
////        userLat = userLat * (Math.PI / 180);
//        userLong = userLong * (Math.PI / 180);
//        double ymin = Math.log(Math.tan((bottom / 2) + (Math.PI / 4)));
//        double ymax = Math.log(Math.tan((top / 2) + (Math.PI / 4)));
//        double xFactor=IMAGEX/(right-left);
//        double yFactor = IMAGEY/(ymax-ymin);
//
//        posX = (userLong-left)*xFactor;
//        posY = (ymax - Math.log(Math.tan((userLat / 2) + (Math.PI / 4)))) * yFactor;
        //posX = (userLong/360) + 0.5 ;
        //posX = posX * 1440;
        //posY = Math.abs((Math.asin(Math.tan(Math.toRadians(userLat)))/(Math.PI/2))-0.5);
        //posY = posY * 2100;
        posX = (IMAGEX * (userLong - LEFTLONG)) /(RIGHTLONG - LEFTLONG) ;
//        posY =  (IMAGEY - (IMAGEY *(userLat-RIGHTLAT)/(LEFTLAT-RIGHTLAT))) ;
     //   posX = posX * Math.sin(Math.toRadians(15));
      //  posY = posY * Math.sin(Math.toRadians(10));
        double xCenter = 660/2;
        double yCenter = 694/2;
        double yScale = yCenter/90;
        double xScale = xCenter/180;
      //      posX = xCenter + userLong * xScale;
        posY = yCenter - userLat * yScale;
        posX = posX * 2.181;
        posY = posY * 3.026;

        Log.i("PIXEL VALUES MAPPED","xx: "+posX+" "+"yy: "+posY);
        invalidate();
    }

    /**************************************************************************************************
     Converting the lat long to current position on image
     **************************************************************************************************/
    public void getWindowSize(){
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        IMAGEX= display.getWidth();
        IMAGEY = display.getHeight();
        Log.i("The Size is","xx: "+IMAGEX+"yy: "+IMAGEY);
    }
    /**************************************************************************************************
     Setting the coordinates
     **************************************************************************************************/
    public void setCoordinates(double lat,double lng){
        this.latitude = lat;
        this.longitude = lng;



    }


}