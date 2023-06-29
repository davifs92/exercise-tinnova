package org.example;

public class Main {

    public static int calculateFactorial(int number){
        if(number == 0){
            return 1;
        }
        return number * (calculateFactorial(number - 1));

    }
    public static void main(String[] args) {
        System.out.println(calculateFactorial(0));
        System.out.println(calculateFactorial(1));
        System.out.println(calculateFactorial(2));
        System.out.println(calculateFactorial(3));
        System.out.println(calculateFactorial(4));
        System.out.println(calculateFactorial(5));
        System.out.println(calculateFactorial(6));


    }
}