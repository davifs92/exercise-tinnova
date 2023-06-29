package org.example;

public class Main {

    public static int multipleSum(int num){
        int result = 0;

        if(num < 0){
            return result;
        }

        for(int i = num - 1; i > 0; i--){
            if(i % 3 == 0  || i % 5 == 0){
                result += i;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(multipleSum(10));

    }
}