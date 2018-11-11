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
        verifyGame();

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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
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
        verifyGame();
    }

    public void changeTurnMessage(){
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        setTurnString();
        turnMessage.setText(getTurnString());
    }

    public boolean isComplete(){
        //RETURNS TRUE IF THERE ARE NO CELLS AVAILABLE, FALSE IF STILL THERE ARE CELL AVAILABLE
        int cont=0;
        for(int x=0;x<3;x++) {
            for (int y = 0; y < 3; y++) {
                if (matrix[x][y] == 0) {
                    cont++;
                }
            }
        }
        return cont==0;
    }

    public int winCheck(){
        //RETURN 0 IF NOBODY WINS, 1 IF PLAER 1 WINS, 2 IF PLAYER 2 WINS
        int sum=0;
        //Check file per file
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                sum+=matrix[x][y];
            }
            if(sum==3){
                return 1;
            }
            else if(sum==30) {
                return 2;
            }
            else{
                sum=0;
            }
        }
        //Check Column per column
        sum=0;
        for(int y=0;y<3;y++){
            for(int x=0;x<3;x++){
                sum+=matrix[x][y];
            }
            if(sum==3){
                return 1;
            }
            else if(sum==30) {
                return 2;
            }
            else{
                sum=0;
            }
        }
        //Check Main Diagonal
        sum=0;
        for(int i=0;i<3;i++){
            sum+=matrix[i][i];
        }
        if(sum==3){
            return 1;
        }
        else if(sum==30) {
            return 2;
        }
        //Check inverted main diagonal
        sum=0;
        sum+=matrix[0][2];
        sum+=matrix[1][1];
        sum+=matrix[2][0];
        if(sum==3){
            return 1;
        }
        else if(sum==30) {
            return 2;
        }
        else {
            return 0;
        }
    }

    public void setWinnerMessage(int player){
        if(player==2){
            TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
            turnMessage.setText("YOU LOSE");
        }
        else if(player==1){
            TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
            turnMessage.setText("YOU WIN");
        }
    }

    public void verifyGame(){

        //CHECK IF TABLE IS FULL
        if(isComplete()){
            int winner=winCheck();
            if(winner==0){
                TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
                turnMessage.setText("TIED GAME");
            }
            else{
                setWinnerMessage(winner);
                setAllButtonsEnabled(false);
            }
        }
        else{
            int winner=winCheck();
            if(winner==0){
                changeTurnMessage();
            }
            else{
                setWinnerMessage(winner);
                setAllButtonsEnabled(false);
            }
        }
    }

    public void setAllButtonsEnabled(boolean b){
        Button Btn00=(Button) findViewById(R.id.btn00);
        Btn00.setEnabled(b);

        Button Btn01=(Button) findViewById(R.id.Btn01);
        Btn01.setEnabled(b);

        Button Btn02=(Button) findViewById(R.id.Btn02);
        Btn02.setEnabled(b);

        Button Btn10=(Button) findViewById(R.id.Btn10);
        Btn10.setEnabled(b);

        Button Btn11=(Button) findViewById(R.id.Btn11);
        Btn11.setEnabled(b);

        Button Btn12=(Button) findViewById(R.id.Btn12);
        Btn12.setEnabled(b);

        Button Btn20=(Button) findViewById(R.id.Btn20);
        Btn20.setEnabled(b);

        Button Btn21=(Button) findViewById(R.id.Btn21);
        Btn21.setEnabled(b);

        Button Btn22=(Button) findViewById(R.id.Btn22);
        Btn22.setEnabled(b);
    }
}