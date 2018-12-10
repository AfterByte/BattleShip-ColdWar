package com.afterbyte.battleship_coldwar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Locate extends AppCompatActivity {

    MediaPlayer theme, winnertheme,ring,ring2;


    private String playerOneCountry, playerTwoCountry;
    private int playerTurn,winCount;
    private int[][] board=new int[3][3];
    private boolean p1Win=false;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_locate);

        ring= MediaPlayer.create(Locate.this,R.raw.shoot);
        ring2= MediaPlayer.create(Locate.this,R.raw.shoot);

        theme= MediaPlayer.create(Locate.this,R.raw.gametheme);
        theme.start();

        restartBackgroud();

        mode=getIntent().getExtras().getInt("mode");

        winCount=getIntent().getExtras().getInt("winCount");


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
        btn00.setEnabled(false);
        if(playerTurn==1){
            makeMove(0,0,1);
            if(mode==1){
                computerMove();
            }
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
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(0,1,10);
        }

        btn.setEnabled(false);
    }

    public void Btn02Action(View v){
        Button btn=(Button) findViewById(R.id.Btn02);
        setFace(btn);
        if(playerTurn==1){
            makeMove(0,2,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(0,2,10);
        }
        btn.setEnabled(false);
    }

    public void Btn10Action(View v){
        Button btn=(Button) findViewById(R.id.Btn10);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,0,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(1,0,10);
        }
        btn.setEnabled(false);
    }

    public void Btn11Action(View v){
        Button btn=(Button) findViewById(R.id.Btn11);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,1,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(1,1,10);
        }
        btn.setEnabled(false);
    }

    public void Btn12Action(View v){
        Button btn=(Button) findViewById(R.id.Btn12);
        setFace(btn);
        if(playerTurn==1){
            makeMove(1,2,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(1,2,10);
        }
        btn.setEnabled(false);
    }

    public void Btn20Action(View v){
        Button btn=(Button) findViewById(R.id.Btn20);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,0,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(2,0,10);
        }
        btn.setEnabled(false);
    }

    public void Btn21Action(View v){
        Button btn=(Button) findViewById(R.id.Btn21);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,1,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(2,1,10);
        }
        btn.setEnabled(false);
    }

    public void Btn22Action(View v){
        Button btn=(Button) findViewById(R.id.Btn22);
        setFace(btn);
        if(playerTurn==1){
            makeMove(2,2,1);
            if(mode==1){
                computerMove();
            }
        }
        else if(playerTurn==2){
            makeMove(2,2,10);
        }
        btn.setEnabled(false);
    }

    public void BtnResetAction(View v){
        winnertheme.stop();
        theme=MediaPlayer.create(Locate.this,R.raw.gametheme);
        theme.start();
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
        if(playerTurn==1){
            ring.start();
        }
        else{
            ring2.start();
        }
        board[x][y]=value;
        verifyGame();
    }
    public void verifyGame(){
        int winner=winCheck();
        if(winner==0){
            if(isComplete()){
                TextView turnMessageView=(TextView) findViewById(R.id.turnMessage);
                turnMessageView.setText("TIED GAME");
                winnertheme= MediaPlayer.create(Locate.this,R.raw.tied);
                theme.stop();
                winnertheme.start();
                resetMode();
            }
            else{
                changeTurn();
            }
        }
        else if(winner==1){
            setWinnerMessage(1);
        }
        else{
            setWinnerMessage(2);
        }
    }

    public int winCheck(){
        //RETURN 0 IF NOBODY WINS, 1 IF PLAYER 1 WINS, 2 IF PLAYER 2 WINS
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
        theme.stop();
        Intent intent = new Intent(Locate.this, winnerScreen.class);
        if(player==1){
            intent.putExtra("winner",playerOneCountry);
            p1Win=true;
            winCount++;
        }
        else{
            intent.putExtra("winner",playerTwoCountry);
        }
        intent.putExtra("mode",mode);
        intent.putExtra("country",playerOneCountry);
        intent.putExtra("winCount",winCount);
        intent.putExtra("p1Win",p1Win);
        startActivity(intent);
        this.finish();
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
        Button Btn=(Button) findViewById(R.id.btn00);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn01);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn02);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn10);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn11);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn12);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn20);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn21);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.Btn22);
        Btn.setBackground(null);

        Btn=(Button) findViewById(R.id.BtnReset);
        Btn.setVisibility(View.INVISIBLE);
        Btn.setEnabled(false);
    }

    public void computerMove(){
        //FIRST ALGORYMTH
        /*
        boolean flag=false;
        int x,y, cont=0;
        do{
            cont++;
            x=roundNumber(Math.random());
            y=roundNumber(Math.random());
            if(valideCell(x,y)){
                flag=true;
            }
        }while(!flag && cont<100);
        if(cont ==100){
            changeTurn();
            return;
        }
        else{
            activateButton(x,y);
        }
        */

        //NEW ALGORYMTH
        try {
            Intelligence i=new Intelligence(board);
            activateButton(i.getxMove(),i.getyMove());
        }
        catch (Exception e){}
    }

    public int roundNumber(double d){
        if(d >= 0 && d<=0.32){
            return 0;
        }
        else if( d>0.32 && d<=0.65){
            return 1;
        }
        else if(d>0.66 && d<=1){
            return 2;
        }
        else{
            return 0;
        }
    }

    public boolean valideCell(int x,int y){
        return board[x][y]==0;
    }

    public void activateButton(int x, int y){
        Button btn=null;
        if(x==0 && y==0){
            btn=(Button) findViewById(R.id.btn00);
        }
        else if(x==0 && y==1){
            btn=(Button) findViewById(R.id.Btn01);
        }
        else if(x==0 && y==2){
            btn=(Button) findViewById(R.id.Btn02);
        }
        else if(x==1 && y==0){
            btn=(Button) findViewById(R.id.Btn10);
        }
        else if(x==1 && y==1){
            btn=(Button) findViewById(R.id.Btn11);
        }
        else if(x==1 && y==2){
            btn=(Button) findViewById(R.id.Btn12);
        }
        else if(x==2 && y==0){
            btn=(Button) findViewById(R.id.Btn20);
        }
        else if(x==2 && y==1){
            btn=(Button) findViewById(R.id.Btn21);
        }
        else if(x==2 && y==2){
            btn=(Button) findViewById(R.id.Btn22);
        }
        btn.performClick();
    }

    @Override
    public void onBackPressed(){

    }
}