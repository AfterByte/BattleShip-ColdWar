package com.afterbyte.battleship_coldwar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class TeamSelection extends AppCompatActivity {


    //
    int mode;
    MediaPlayer theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_team_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        theme= MediaPlayer.create(TeamSelection.this,R.raw.teamselector);
        theme.start();

        setMode();

    }
    public void transitionUsa(View v){
        MediaPlayer ring= MediaPlayer.create(TeamSelection.this,R.raw.btnfx);
        ring.start();

        theme.stop();


        Intent intent = new Intent(TeamSelection.this, Locate.class);
        intent.putExtra("country","USA");

        intent.putExtra("mode",mode);

        startActivity(intent);
        this.finish();
    }
    public void transitionRussia(View v){
        MediaPlayer ring= MediaPlayer.create(TeamSelection.this,R.raw.btnfx);
        ring.start();
        theme.stop();

        Intent intent = new Intent(TeamSelection.this, Locate.class);
        intent.putExtra("country","Russia");

        intent.putExtra("mode",mode);

        startActivity(intent);
        this.finish();
    }

    public void setMode(){
        mode=getIntent().getExtras().getInt("mode");
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(TeamSelection.this, MainActivity.class);
        theme.stop();
        startActivity(intent);
        this.finish();
    }


}
