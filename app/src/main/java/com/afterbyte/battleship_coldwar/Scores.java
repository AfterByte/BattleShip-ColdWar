package com.afterbyte.battleship_coldwar;

public class Scores {
    int score[] = {1000,600,1000,900,500,700,800,900,500,700};
    String countries[]={"Russia","USA","USA","Russia","Russia","USA","Russia","USA","USA","Russia"};

    public Scores(){
    }

    public void sort() {
        if (score == null || score.length == 0) {
            return;
        }
        int length = score.length;
        quickSort(0, length - 1);
    }

    private void quickSort(int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        int pivot = score[lowerIndex+(higherIndex-lowerIndex)/2];
        while (i <= j) {
            while (score[i] < pivot) {
                i++;
            }
            while (score[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }

    private void exchangeNumbers(int i, int j) {
        int temp = score[i];
        String tempC = countries[i];
        score[i] = score[j];
        countries[i]=countries[j];
        score[j] = temp;
        countries[j]= tempC;
    }
    public void reverseScore(){
        int tempScore[]=new int[10];
        String tempCountry[]=new String[10];
        int y = score.length;
        for(int x =0;x < score.length;x++){
            tempScore[x] = score[y-x];
            tempCountry[x] =  countries[y-x];
        }
        for(int x = 0;x < score.length;x++){
            score[x ]= tempScore[x];
            countries[x] = tempCountry[x];
        }
    }

    public int getScore(int i){
        return score[i];
    }
    public String getCountry(int i){
        return countries[i];
    }

    public int bestScore(String country){
        int i=0,cont=0;
        for(int x=0;x<10;x++){
            if(score[x]>cont && countries[x].equals(country)){
                i=x;
                cont=score[x];
            }
        }
        return i;
    }
}
