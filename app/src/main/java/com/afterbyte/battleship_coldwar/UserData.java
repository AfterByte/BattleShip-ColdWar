package com.afterbyte.battleship_coldwar;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {
    private int totalGames=0,bestRate=0;
    private ListNode usaRates= new ListNode(-1,null);
    private ListNode russiaRates= new ListNode(-1,null);

    public void setTotalGames(int totalGames){
        this.totalGames=totalGames;
    }

    public int getTotalGames(){
        return totalGames;
    }

    public ListNode getUsaRates(){
        return usaRates;
    }

    public ListNode getRussiaRates(){
        return russiaRates;
    }

    public void setNewRate(ListNode n, int rate){
        if(n.getRate()==-1){
            n.setRate(rate);
            n.setNext(null);
        }
        else if(n.getNext()!=null){
            ListNode p=new ListNode(rate,null);
        }
        else {
            setNewRate(n.getNext(),rate);
        }
    }

    public void setBestRate(int bestRate){
        this.bestRate=bestRate;
    }

    public int getBestRate(ListNode n){
        if(n.getRate()>bestRate){
            bestRate=n.getRate();
        }
        else {
            if(n.getNext()!=null){
                getBestRate(n.getNext());
            }

        }
        return bestRate;
    }



}
