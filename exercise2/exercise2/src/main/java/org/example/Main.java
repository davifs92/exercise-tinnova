package org.example;

import java.util.Arrays;

public class Main {
    public static int[] BubbleSort(int[] arrayToBeSorted){
        int size = arrayToBeSorted.length;
        int j = 0;

        if(arrayToBeSorted.length == 0) {
            return arrayToBeSorted;
        }

        boolean sortedAll = true;

        while(j < size - 1 && sortedAll){
            sortedAll = false;
            for(int i = 0; i < size - j - 1; i++){
                if(arrayToBeSorted[i] > arrayToBeSorted[i + 1]){
                    sortedAll = true;
                    int temp = arrayToBeSorted[i];
                    arrayToBeSorted[i] = arrayToBeSorted[i + 1];
                    arrayToBeSorted[i + 1] = temp;
                }
            }
            j++;
        }
        return arrayToBeSorted;
    }

    public static void main(String[] args) {


        System.out.println(Arrays.toString(BubbleSort(new int[]{5, 6, 8, 1, 4})));
        System.out.println(Arrays.toString(BubbleSort(new int[]{6, 5, 4, 3, 2, 1})));

    }
}