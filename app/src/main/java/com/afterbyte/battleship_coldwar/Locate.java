package com.afterbyte.battleship_coldwar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Locate extends AppCompatActivity {
    private String turnString="Your Turn";
    private int player=1;

    public String getTurnString(){
        return turnString;
    }
    public void setTurnString(){
        if(turnString.equals("Your Turn")){
            turnString="Enemy Turn";
            player=2;
        }
        else{
            turnString="Your Turn";
            player=1;
        }
    }

    private int matrix[][]=new int[3][3];

    public int getMatrix(int x,int y){
        return matrix[x][y];
    }

    public void setMatrix(int x,int y,int i){
        matrix[x][y]=i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_locate);


    }

    public void Btn00Action(View v){
        if(player==1){
            setMatrix(0,0,1);
            player=2;
        }
        else{
            setMatrix(0,0,10);
            player=1;
        }
        Button Btn00=(Button) findViewById(R.id.btn00);
        Btn00.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }
    public void Btn01Action(View v){
        if(player==1){
            setMatrix(0,1,1);
            player=2;
        }
        else{
            setMatrix(0,1,10);
            player=1;
        }
        Button Btn02=(Button) findViewById(R.id.Btn01);
        Btn02.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn02Action(View v){
        if(player==1){
            setMatrix(0,2,1);
            player=2;
        }
        else{
            setMatrix(0,2,10);
            player=1;
        }
        Button Btn02=(Button) findViewById(R.id.Btn02);
        Btn02.setEnabled(false);
        Btn02.setBackgroundColor(999999);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn10Action(View v){
        if(player==1){
            setMatrix(1,0,1);
            player=2;
        }
        else{
            setMatrix(1,0,10);
            player=1;
        }
        Button Btn10=(Button) findViewById(R.id.Btn10);
        Btn10.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn11Action(View v){
        if(player==1){
            setMatrix(1,1,1);
            player=2;
        }
        else{
            setMatrix(1,1,10);
            player=1;
        }
        Button Btn11=(Button) findViewById(R.id.Btn11);
        Btn11.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn12Action(View v){
        if(player==1){
            setMatrix(1,2,1);
            player=2;
        }
        else{
            setMatrix(1,2,10);
            player=1;
        }
        Button Btn12=(Button) findViewById(R.id.Btn12);
        Btn12.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn20Action(View v){
        if(player==1){
            setMatrix(2,0,1);
            player=2;
        }
        else{
            setMatrix(2,0,10);
            player=1;
        }
        Button Btn20=(Button) findViewById(R.id.Btn20);
        Btn20.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn21Action(View v){
        if(player==1){
            setMatrix(2,1,1);
            player=2;
        }
        else{
            setMatrix(2,1,10);
            player=1;
        }
        Button Btn21=(Button) findViewById(R.id.Btn21);
        Btn21.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void Btn22Action(View v){
        if(player==1){
            setMatrix(2,2,1);
            player=2;
        }
        else{
            setMatrix(2,2,10);
            player=1;
        }
        Button Btn22=(Button) findViewById(R.id.Btn22);
        Btn22.setEnabled(false);
        //Change turn Message
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public void isComplete(){
        int sum=0;
        //Check file per file
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                sum+=matrix[x][y];
            }
            if(sum==30){
                TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
                turnMessage.setText("YOU LOSE");
                return;
            }
            else if(sum==3){
                TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
                turnMessage.setText("YOU WIN");
                return;
            }
        }
        //Check Column per column
        sum=0;
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                sum+=matrix[x][y];
            }
        }

    }
}



