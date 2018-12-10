package com.afterbyte.battleship_coldwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ProfileActivitie extends AppCompatActivity {

    private UserData ud=null;
    TextView totalGames,usaRate,russiaRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activitie);
        totalGames= (TextView) findViewById(R.id.GPID);
        usaRate=(TextView) findViewById(R.id.usaID);
        russiaRate=(TextView) findViewById(R.id.russiaID);

        try{
            String fileName=this.getFilesDir().getPath().toString()+"/ColdWarUserData.dat";
            File userData=new File(fileName);
            UserData ud=null;
            if(userData.exists()){
                FileInputStream fis=new FileInputStream(fileName);
                ObjectInputStream ois=new ObjectInputStream(fis);
                ud=(UserData) ois.readObject();
                start();
            }
            else{
                totalGames.setText("0");
                usaRate.setText("0");
                russiaRate.setText("0");
            }

        }
        catch (Exception e){}

    }

    public void start(){
        totalGames.setText(ud.getTotalGames());
        usaRate.setText(ud.getBestRate(ud.getUsaRates()));
        russiaRate.setText(ud.getBestRate(ud.getRussiaRates()));
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ProfileActivitie.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }




}
