package com.example.unmesh.locateme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tanvi on 10/27/2016.
 */
public class MapActivityLayout extends View {

    float drawX, drawY;
    static boolean isSearch = false;
    static boolean isOnCampus = true;
    float searchX,searchY;
    MapScreenActivity mapScreenActivity = new MapScreenActivity();
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude;
    double longitude;

    public MapActivityLayout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.campusmap);


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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isOnCampus) {
            float x = (float) 953.6133;
            float y = (float) 1406.5625;
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x, y, 30, paint);
            invalidate();

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


    public void setCoordinates(double lat,double lng){

        this.latitude = lat;
        this.longitude = lng;
        System.out.println("The Co:ordinates: "+latitude+" "+longitude);




    }


}