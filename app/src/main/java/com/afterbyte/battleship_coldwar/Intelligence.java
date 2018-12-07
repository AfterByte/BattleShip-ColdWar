package com.afterbyte.battleship_coldwar;

import javax.microedition.khronos.egl.EGL10;

public class Intelligence {

    private int board[][];

    public Intelligence(int board[][]){
        this.board=board;
        evaluate();
    }

    private void evaluate(){
        //F0 - F2
        for(int i=0;i<3;i++){
            evaluateFile(i);
        }

        //C0 - C2
        for(int i=0;i<3;i++){
            evaluateColumn(i);
        }

        //E0
        evaluateDiagonal();

        //E1
        int cont=0;
        int x1=0,y1=0; //VARS TO OBTAIN AN AVAILABLE POSITION FOR DO CPU MOVE
        for(int x=0;x<3;x++){
            cont+=board[x][2-x];
            if(board[x][2-x]==0){
                x1=x;
                y1=x-2;
            }
        }
        if(isGoodMove(cont)){
            //addGoodMoveNode();
        }
        else if(isBestMove(cont)){
            //addBestMoveNode();
        }
    }

    private void evaluateDiagonal(){
        int cont=0;
        for(int i=0;i<3;i++){
            cont+=board[i][i];
        }
        if(isGoodMove(cont)){
            //addGoodMoveNode();
        }
        else if(isBestMove(cont)){
            //addBestMoveNode();
        }
    }

    private void evaluateColumn(int x){
        int cont=0;
        int x1=0,y1=0; //VARS TO OBTAIN AN AVAILABLE POSITION FOR DO CPU MOVE
        for(int y=0;y<3;y++){
            cont+=board[x][y];
            if(board[x][y]==0){
                x1=x;
                y1=y;
            }
        }
        if(isGoodMove(cont)){
            //addGoodMoveNode();
        }
        else if(isBestMove(cont)){
            //addBestMove
        }
    }

    private void evaluateFile(int y){
        int cont=0;
        int x1=0,y1=0; //VARS TO OBTAIN AN AVAILABLE POSITION FOR DO CPU MOVE
        for(int x=0;x<3;x++){
            cont+=board[x][y];
            if(board[x][y]==0){
                x1=x;
                y1=y;
            }
        }
        if(isGoodMove(cont)){
            //addGoodMoveNode();
        }
        else if(isBestMove(cont)){
            //addBestMove
        }
    }

    private boolean isGoodMove(int n){
        if(n==10 || n== 1 || n==0){
            return true;
        }
        return false;
    }

    private boolean isBestMove(int n){
        if(n==20 || n== 2 || n==11){
            return true;
        }
        return false;
    }
}
