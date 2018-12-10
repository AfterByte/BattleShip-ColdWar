package com.afterbyte.battleship_coldwar;

import javax.microedition.khronos.egl.EGL10;

public class Intelligence {

    private int board[][];
    private Node root= new Node();
    private int xMove=0,yMove=0;

    public Intelligence(int board[][]){
        this.board=board;
        root.setLeft(null);
        root.setRight(null);
        root.setX(0);
        root.setY(0);
        root.setValue(0);
        evaluate();
    }

    public int getxMove(){
        return xMove;
    }

    public int getyMove() {
        return yMove;
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
            addGoodMove(x1,y1,cont,root);
        }
        else if(isBestMove(cont)){
            addBestMove(x1,y1,cont,root);
        }

        createMove();
    }

    private void evaluateDiagonal(){
        int cont=0;
        int x1=0,y1=0;
        for(int i=0;i<3;i++){
            cont+=board[i][i];
            if(board[i][i]==0){
                x1=i;
                y1=i;
            }
        }
        if(isGoodMove(cont)){
            addGoodMove(x1,y1,cont,root);
        }
        else if(isBestMove(cont)){
            addBestMove(x1,y1,cont,root);
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
            addGoodMove(x1,y1,cont,root);
        }
        else if(isBestMove(cont)){
            addBestMove(x1,y1,cont,root);
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
            addGoodMove(x1,y1,cont,root);
        }
        else if(isBestMove(cont)){
            addBestMove(x1,y1,cont,root);
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

    private void addGoodMove(int x, int y, int value, Node ra){
        Node p = ra;
        //ROOT CASE
        if(p==root){
            if(ra.getLeft()==null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setLeft(n);
            }
            else{
                addGoodMove(x,y,value,p.getLeft());
            }
        }

        else if(value <= p.getValue()){
            if(p.getLeft() == null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setLeft(n);
            }else{
                addGoodMove(x,y,value,p.getLeft());
            }
        }else if(value > p.getValue()){
            if(p.getRight() == null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setRight(n);
            }else{
                addGoodMove(x,y,value,p.getRight());
            }
        }
    }

    private void addBestMove(int x, int y, int value, Node ra){
        Node p = ra;
        //ROOT CASE
        if(p==root){
            if(ra.getRight()==null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setRight(n);
            }
            else{
                addBestMove(x,y,value,p.getRight());
            }
        }

        else if(value <= p.getValue()){
            if(p.getLeft() == null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setLeft(n);
            }else{
                addBestMove(x,y,value,p.getLeft());
            }
        }else if(value > p.getValue()){
            if(p.getRight() == null){
                Node n = new Node();
                n.setRight(null);
                n.setLeft(null);
                n.setX(x);
                n.setY(y);
                n.setValue(value);
                p.setRight(n);
            }else{
                addBestMove(x,y,value,p.getRight());
            }
        }
    }

    public void createMove() {
        Node move=null;
        if(root.getRight()!=null){
            bestMove(root.getRight());
        }
        else{
            goodMove(root.getLeft());
        }
    }

    public void bestMove(Node r){
        if(r.getLeft() == null){
            xMove=r.getX();
            yMove=r.getY();
        }
        else{
            bestMove(r.getLeft());
        }
    }

    public void goodMove(Node r){
        if(r.getRight() == null){
            xMove=r.getX();
            yMove=r.getY();
        }
        else{
            goodMove(r.getRight());
        }
    }

}
