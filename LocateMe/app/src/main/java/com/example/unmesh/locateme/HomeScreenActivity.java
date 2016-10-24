package com.example.unmesh.locateme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {
    LocationTracker currentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button mapScreenButton = (Button) findViewById(R.id.mapscreenbutton);



        mapScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this,MapScreenActivity.class);
                //intent.putExtra("quizType","+");
                startActivity(intent);
//                finish();
            }
        });

        Button quitButton = (Button) findViewById(R.id.quitbutton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }


}
