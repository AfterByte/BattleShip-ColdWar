package com.afterbyte.battleship_coldwar;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class winnerScreen extends AppCompatActivity {

    String winner,country;
    private int mode,winCount;
    private boolean p1Win,updatedData=false;
    MediaPlayer winnertheme;
    TextView message,rateView;
    ImageView face,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner_screen);
        message=(TextView) findViewById(R.id.message);
        rateView=(TextView) findViewById(R.id.rateID);
        face=(ImageView) findViewById(R.id.face);
        flag=(ImageView) findViewById(R.id.flag);
        winner=getIntent().getExtras().getString("winner");
        mode=getIntent().getExtras().getInt("mode");
        country=getIntent().getExtras().getString("country");
        winCount=getIntent().getExtras().getInt("winCount");
        p1Win=getIntent().getExtras().getBoolean("p1Win");
        rateView.setText("RATE: "+winCount);
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
        if(!p1Win){
            updateData();
        }
    }

    private void updateData(){
        int storage=0;
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, storage);
            }
        }
        if(!updatedData){
            String fileName=this.getFilesDir().getPath().toString()+"/ColdWarUserData.dat";
            File userData=new File(fileName, "rw");
            UserData ud=null;
            if(userData.exists()){
                //Read file
                try {
                    FileInputStream fis=new FileInputStream(fileName);
                    ObjectInputStream ois=new ObjectInputStream(fis);
                    ud=(UserData) ois.readObject();

                    //update
                    ud.setTotalGames(ud.getTotalGames() + 1);
                    if(country.equals("USA")){
                        ud.setNewRate(ud.getUsaRates(),winCount);
                    }
                    else if(country.equals("Russia")){
                        ud.setNewRate(ud.getRussiaRates(),winCount);
                    }

                    //REWRITE
                    FileOutputStream fos=new FileOutputStream(fileName);
                    ObjectOutputStream oos= new ObjectOutputStream(fos);
                    oos.writeObject(ud);
                }
                catch (Exception e){}

            }
            else {
                //WRITE DATA
                message.setText("NO EXISte");
                ud = new UserData();
                ud.setTotalGames(1);
                if (winner.equals("USA") && p1Win) {
                    ud.setNewRate(ud.getUsaRates(),winCount);
                } else if (winner.equals("Russia") && p1Win) {
                    ud.setNewRate(ud.getRussiaRates(),winCount);
                }

                try {
                    FileOutputStream fos = new FileOutputStream(fileName);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(ud);
                } catch (Exception e) {
                    AlertDialog.Builder alert=new AlertDialog.Builder(this);
                    alert.setMessage(e.getMessage());
                    alert.show();

                }
            }
        }
    }

    public void BtnResetAction(View v){
        winnertheme.stop();
        Intent intent = new Intent(winnerScreen.this, Locate.class);
        intent.putExtra("country",country);
        intent.putExtra("mode",mode);
        intent.putExtra("winCount",winCount);
        startActivity(intent);
        this.finish();
    }

    public void BtnHomeAction(View v){
        updateData();
        winnertheme.stop();
        Intent intent = new Intent(winnerScreen.this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }

    @Override
    public void onBackPressed(){

    }
}
