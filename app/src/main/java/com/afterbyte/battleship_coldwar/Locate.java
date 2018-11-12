package com.afterbyte.battleship_coldwar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Locate extends AppCompatActivity {

    private String playerOneCountry, playerTwoCountry;
    private String turnMessage;
    private int playerTurn;
    private int[][] board=new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_locate);

        restartBackgroud();

        playerOneCountry=getIntent().getExtras().getString("country");
        TextView turnMessageView= (TextView) findViewById(R.id.turnMessage);
        turnMessageView.setText(playerOneCountry.concat(" turn"));
        playerTurn=1;
        if(playerOneCountry.equals("USA")){
            playerTwoCountry="Russia";
        }
        else{
            playerTwoCountry="USA";
        }

    }

    public void setFace(Button btn){
        if(playerTurn==1){
            if(playerOneCountry.equals("USA")){
                btn.setBackgroundResource(R.drawable.trump);
            }
            else{
                btn.setBackgroundResource(R.drawable.putin);
            }

        }
        else{
            if(playerTwoCountry.equals("USA")){
                btn.setBackgroundResource(R.drawable.trump);
            }
            else{
                btn.setBackgroundResource(R.drawable.putin);
            }
        }
    }


    public void Btn00Action(View v){
        Button btn00=(Button) findViewById(R.id.btn00);
        setFace(btn00);
        if(playerTurn==1){
            makeMove(0,0,1);
        }
        else if(playerTurn==2){
            makeMove(0,0,10);
        }
    }

    public void Btn01Action(View v){
        Button btn=(Button) findViewById(R.id.Btn01);
        setFace(btn);
        if(playerTurn==1){
            makeMove(0,1,1);
        }
        else if(playerTurn==2){
            makeMove(0,1,10);
        }
    }

    public void Btn02Action(View v){
        Button btn=(Button) findViewById(R.id.Btn02);
        setFace(btn);
        if(playerTurn==1){
            makeMove(0,2,1);
        }
        else if(playerTurn==2){
            makeMove(0,2,10);
        }
    }

    public void Btn10Action(View v){
        Button btn=(Button) findViewById(R.id.Btn10);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,0,1);
        }
        else if(playerTurn==2){
            makeMove(1,0,10);
        }
    }

    public void Btn11Action(View v){
        Button btn=(Button) findViewById(R.id.Btn11);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,1,1);
        }
        else if(playerTurn==2){
            makeMove(1,1,10);
        }
    }

    public void Btn12Action(View v){
        Button btn=(Button) findViewById(R.id.Btn12);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,2,1);
        }
        else if(playerTurn==2){
            makeMove(1,2,10);
        }
    }

    public void Btn20Action(View v){
        Button btn=(Button) findViewById(R.id.Btn20);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,0,1);
        }
        else if(playerTurn==2){
            makeMove(2,0,10);
        }
    }

    public void Btn21Action(View v){
        Button btn=(Button) findViewById(R.id.Btn21);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,1,1);
        }
        else if(playerTurn==2){
            makeMove(2,1,10);
        }
    }

    public void Btn22Action(View v){
        Button btn=(Button) findViewById(R.id.Btn22);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,2,1);
        }
        else if(playerTurn==2){
            makeMove(2,2,10);
        }
    }

    public void BtnResetAction(View v){
        playerTurn=1;
        TextView turnMessageView=(TextView) findViewById(R.id.turnMessage);
        turnMessageView.setText(playerOneCountry.concat(" turn"));

        //make board to 0
        for(int x=0;x<3;x++){
            for(int y=0; y<3;y++){
                board[x][y]=0;
            }
        }

        setAllButtonsEnabled(true);
        restartBackgroud();
    }

    public void makeMove(int x, int y, int value){
        board[x][y]=value;
        verifyGame();
    }
    public void verifyGame(){
        int winner=winCheck();
        if(winner==0){
            if(isComplete()){
                TextView turnMessageView=(TextView) findViewById(R.id.turnMessage);
                turnMessageView.setText("TIED GAME");
            }
            else{
                changeTurn();
            }
        }
        else if(winner==1){
            setAllButtonsEnabled(false);
            setWinnerMessage(1);
            resetMode();
        }
        else{
            setAllButtonsEnabled(false);
            setWinnerMessage(2);
            resetMode();
        }
    }

    public int winCheck(){
        //RETURN 0 IF NOBODY WINS, 1 IF PLAER 1 WINS, 2 IF PLAYER 2 WINS
        int sum=0;
        //Check file per file
        for(int x=0;x<3;x++){
            for(int y=0;y<3;y++){
                sum+=board[x][y];
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
                sum+=board[x][y];
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
            sum+=board[i][i];
        }
        if(sum==3){
            return 1;
        }
        else if(sum==30) {
            return 2;
        }
        //Check inverted main diagonal
        sum=0;
        sum+=board[0][2];
        sum+=board[1][1];
        sum+=board[2][0];
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

    public boolean isComplete(){
        //RETURNS TRUE IF THERE ARE NO CELLS AVAILABLE, FALSE IF STILL THERE ARE CELL AVAILABLE
        int cont=0;
        for(int x=0;x<3;x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == 0) {
                    cont++;
                }
            }
        }
        return cont==0;
    }

    public void changeTurn(){
        TextView turnMessage=(TextView) findViewById(R.id.turnMessage);
        if(playerTurn==1){
            playerTurn=2;
            turnMessage.setText(playerTwoCountry + " turn");
        }
        else{
            playerTurn=1;
            turnMessage.setText(playerOneCountry + " turn");
        }
    }

    public void setWinnerMessage(int player){
        TextView turnMessageView=(TextView) findViewById(R.id.turnMessage);
        if(player == 1){
            turnMessageView.setText(playerOneCountry+" wins");
        }
        else{
            turnMessageView.setText(playerTwoCountry+" wins");
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

    public void resetMode(){
        Button resetBtn=(Button) findViewById(R.id.BtnReset);
        resetBtn.setVisibility(View.VISIBLE);
        resetBtn.setEnabled(true);
    }

    public void restartBackgroud(){
        Button Btn00=(Button) findViewById(R.id.btn00);
        Btn00.setBackground(null);

        Button Btn01=(Button) findViewById(R.id.Btn01);
        Btn01.setBackground(null);

        Button Btn02=(Button) findViewById(R.id.Btn02);
        Btn02.setBackground(null);

        Button Btn10=(Button) findViewById(R.id.Btn10);
        Btn10.setBackground(null);

        Button Btn11=(Button) findViewById(R.id.Btn11);
        Btn11.setBackground(null);

        Button Btn12=(Button) findViewById(R.id.Btn12);
        Btn12.setBackground(null);

        Button Btn20=(Button) findViewById(R.id.Btn20);
        Btn20.setBackground(null);

        Button Btn21=(Button) findViewById(R.id.Btn21);
        Btn21.setBackground(null);

        Button Btn22=(Button) findViewById(R.id.Btn22);
        Btn22.setBackground(null);
    }
}