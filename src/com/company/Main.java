package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        boolean play = true;
        while (play) {
            ArrayList computerNumbers = new ArrayList();
            ArrayList userNumbers = new ArrayList();
            for (int i = 0; i < 2; i++) {
                addNumber(computerNumbers);
                addNumber(userNumbers);
            }
            printUserNumbers(userNumbers);

            boolean hit = true;
            while(hit) {
                int userTotal = sumNumbers(userNumbers);
                int computerTotal = sumNumbers(computerNumbers);
                String hitOrStick = input.next();
                if(hitOrStick.equalsIgnoreCase("stick")) {
                    hit = false;
                    if(over21(computerNumbers)) {
                        play = playAgain("My numbers add up to more than 21, so you win!");
                    } else {
                        if(userTotal > computerTotal) {
                            play = playAgain("You got more than me, so you win! You got " + userTotal + ", whereas I got " + computerTotal + ".");
                        } else {
                            play = playAgain("I got more than you, so you lose! I got " + computerTotal + ", whereas you got " +userTotal + ".");
                        }
                    }
                } else if(hitOrStick.equalsIgnoreCase("hit")) {
                    addNumber(userNumbers);
                    if(over21(userNumbers)) {
                        hit = false;
                        play = playAgain("Your numbers add up to " + userTotal + " which is more than 21, so you lose!");
                    } else {
                        addNumber(computerNumbers);
                        printUserNumbers(userNumbers);
                    }
                } else {
                    System.out.println("Not a valid input");
                }
            }
        }
    }

    public static void printUserNumbers(ArrayList userNumbers) {
        System.out.println("Your numbers are " + userNumbers + ". They add to " + sumNumbers(userNumbers) + ". Would you like to hit or stick?");
    }

    public static void addNumber(ArrayList numbers) {
        Random random = new Random();
        numbers.add(random.nextInt(11) + 1);
    }

    public static int sumNumbers(ArrayList numbers) {
        return numbers.stream().mapToInt(i -> (int) i).sum();
    }

    public static boolean over21(ArrayList numbers) {
        return sumNumbers(numbers) > 21;
    }

    public static boolean playAgain(String msg) {
        Scanner input = new Scanner(System.in);
        System.out.println(msg + " Would you like to play again? (yes/no)");
        boolean validInput = false;
        boolean play = true;
        while(!validInput) {
            String playAgain = input.next();
            if(playAgain.equalsIgnoreCase("no")) {
                play = false;
                validInput = true;
                System.out.println("Exiting the game. Thanks for playing!");
            } else if(playAgain.equalsIgnoreCase("yes")) {
                validInput = true;
                System.out.println("Restarting the game. GLHF!");
            } else {
                System.out.println("Not a valid input");
            }
        }
        return play;
    }
}
