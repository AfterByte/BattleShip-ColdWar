package com.afterbyte.battleship_coldwar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class winnerScreen extends AppCompatActivity {

    String winner,country;
    int mode;
    MediaPlayer winnertheme;
    TextView message;
    ImageView face,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner_screen);
        message=(TextView) findViewById(R.id.message);
        face=(ImageView) findViewById(R.id.face);
        flag=(ImageView) findViewById(R.id.flag);
        winner=getIntent().getExtras().getString("winner");
        mode=getIntent().getExtras().getInt("mode");
        country=getIntent().getExtras().getString("country");

        if(winner.equals("USA")){
            winnertheme= MediaPlayer.create(winnerScreen.this,R.raw.usa);
            message.setText("USA WINS");
        }
        else{
            face.setImageResource(R.drawable.putin );
            flag.setImageResource(R.drawable.russia);
            winnertheme= MediaPlayer.create(winnerScreen.this,R.raw.urss);
            message.setText("RUSSIA WINS");
        }
        winnertheme.start();
    }

    public void BtnResetAction(View v){
        winnertheme.stop();
        Intent intent = new Intent(winnerScreen.this, Locate.class);
        intent.putExtra("country",country);
        intent.putExtra("mode",mode);
        startActivity(intent);
        this.finish();
    }

    public void BtnHomeAction(View v){
        winnertheme.stop();
        Intent intent = new Intent(winnerScreen.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed(){

    }
}
