package com.afterbyte.battleship_coldwar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {


    MediaPlayer theme;

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
        theme= MediaPlayer.create(MainActivity.this,R.raw.maintheme);
        theme.start();
    }

    public void localOnePlayer(View v){
        theme.stop();
        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.btnfx);
        ring.start();
        Intent intent = new Intent(MainActivity.this, TeamSelection.class);
        intent.putExtra("mode",1);
        startActivity(intent);
        this.finish();
    }

    public void localmode(View v){
        theme.stop();
        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.btnfx);
        ring.start();
        Intent intent = new Intent(MainActivity.this, TeamSelection.class);
        intent.putExtra("mode",2);
        startActivity(intent);
        this.finish();
    }

    public void profile(View v){
        theme.stop();
        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.btnfx);
        ring.start();
        Intent intent = new Intent(MainActivity.this, ProfileActivitie.class);
        startActivity(intent);
        this.finish();
    }



    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
