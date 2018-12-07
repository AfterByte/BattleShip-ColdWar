package com.afterbyte.battleship_coldwar;

public class Node {

    private int x,y,value;
    private Node left,right;

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setValue(int value){
        this.value=value;
    }

    public void setRight(Node right){
        this.right=right;
    }

    public void setLeft(Node left){
        this.left=left;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getValue(){
        return value;
    }

    public Node getRight(){
        return right;
    }

    public Node getLeft(){
        return left;
    }
}
