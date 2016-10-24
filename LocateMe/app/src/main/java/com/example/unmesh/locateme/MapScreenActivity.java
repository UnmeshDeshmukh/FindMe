package com.example.unmesh.locateme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MapScreenActivity extends AppCompatActivity {


    int xCoordinate;
    int yCoordinate;
    String buildingName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        final ImageView campusMap = (ImageView) findViewById(R.id.campusImage);
        final ImageView invisibleImage = (ImageView) findViewById(R.id.invisibleImage);

    campusMap.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            xCoordinate =  (int)motionEvent.getX();
            yCoordinate =   (int)motionEvent.getY();
            int touchedColor = getColorOnTouch(R.id.invisibleImage, xCoordinate, yCoordinate);
            String buildingName = getBuildingName(touchedColor);
            if(!buildingName.equals("Nope")){
                Intent intent = new Intent(MapScreenActivity.this,BuildingDetailActivity.class);
                intent.putExtra("bldgName",buildingName);
                startActivity(intent);
            }else{
                System.out.println("Not the mentioned building");
            }

            return false;
        }
    });

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

}
