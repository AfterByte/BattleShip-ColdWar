package com.afterbyte.battleship_coldwar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        /*
        SharedPreferences firstRun =getSharedPreferences("firstRun",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=firstRun.edit();
        editor.putBoolean("firstRun",true);
        editor.commit();
        */

    }
    public void localmode(View v){
        Intent intent = new Intent(MainActivity.this, TeamSelection.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
