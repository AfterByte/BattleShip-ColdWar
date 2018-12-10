package com.afterbyte.battleship_coldwar;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ProfileActivitie extends AppCompatActivity {

    TextView usaRate,russiaRate,table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activitie);
        usaRate=(TextView) findViewById(R.id.bUSA);
        russiaRate=(TextView) findViewById(R.id.bRussia);
        table=(TextView) findViewById(R.id.scoreTableID);

        Scores s=new Scores();
        usaRate.setText(Integer.toString(s.getScore(s.bestScore("USA"))));
        russiaRate.setText(Integer.toString(s.getScore(s.bestScore("Russia"))));
        s.sort();
        String t="";
        for(int x=9; x>=0; x--){
            t+=Integer.toString(s.getScore(x))+"    "+s.getCountry(x)+"\n";
            //t.concat(Integer.toString(s.getScore(x)).concat("  "+s.getCountry(x)+"\n"));
        }
        table.setText(t);

        /*
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
        catch (Exception e){
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage(e.getMessage());
            alert.show();
        }*/

    }
/*
    public void start(){
        totalGames.setText(ud.getTotalGames());
        usaRate.setText(ud.getBestRate(ud.getUsaRates()));
        russiaRate.setText(ud.getBestRate(ud.getRussiaRates()));
    }
*/

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ProfileActivitie.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }




}
