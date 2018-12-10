package com.afterbyte.battleship_coldwar;

import java.io.Serializable;

public class ListNode implements Serializable {
    private int rate;
    private ListNode next;

    public int getRate(){
        return rate;
    }

    public void setRate(int rate){
        this.rate=rate;
    }

    public ListNode getNext(){
        return next;
    }

    public void setNext(ListNode next){
        this.next=next;
    }

    public ListNode(int rate,ListNode next){
        this.rate=rate;
        this.next=next;
    }
}