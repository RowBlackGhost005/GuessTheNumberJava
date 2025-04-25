package guessthenumber;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        //Game variables
        int maxNumber = 100;
        int maxAttempts = 1;
        Random numberGenerator = new Random();
        int numberToGuess;
        Scanner userInput = new Scanner(System.in);
        boolean gameOn = true;
        int userGuess;
        boolean stillInGame = true;
        int attempts = 1;
        int lowestHighGuess = maxNumber + 1;
        int higherLowGuess = -1;
        int difficulty;

        do {

            System.out.println("Welcome to Guess The Number Game!");
            System.out.println("You have to guess the number I am thinking.");
            System.out.println("I will answer high if your guess is higher than the number");
            System.out.println("Or low if your guess is lower than the number");
            System.out.println("You win if you guess the number!\n");


            boolean setup = false;

            do{
                System.out.println("Select the difficulty of the game :");
                System.out.println("1 - Easy (Guess from 0 to 64) (16 attempts)");
                System.out.println("2 - Normal (Guess from 0 to 100) (15 attempts)");
                System.out.println("3 - Hard (Guess from 0 to 256) (15 attempts)");
                System.out.println("4 - Hardest (Guess from 0 to 512) (12 attempts)");
                System.out.println("5 - OneShot (Guess from 0 to 100) (1 attempts)");

                System.out.print("Difficulty: ");
                difficulty = userInput.nextInt();
                System.out.println();

                if(difficulty <= 5 && difficulty >= 1){
                    switch(difficulty){
                        case 1:
                            maxNumber = 64;
                            maxAttempts = 16;
                            break;
                        case 2:
                            maxNumber = 100;
                            maxAttempts = 15;
                            break;
                        case 3:
                            maxNumber = 256;
                            maxAttempts = 12;
                            break;
                        case 4:
                            maxNumber = 512;
                            maxAttempts = 12;
                            break;
                        case 5:
                            maxNumber = 100;
                            maxAttempts = 1;
                            break;
                        default:
                            System.out.println("Please select a difficulty from 1 to 5");
                            break;
                    }
                    System.out.println("You selected difficulty " + difficulty);
                    lowestHighGuess = maxNumber + 1;
                    higherLowGuess = -1;
                    setup = true;
                }

            }while(!setup);


            numberToGuess = numberGenerator.nextInt(maxNumber);

            do{
                boolean guessInRange = false;

                do{
                    System.out.print("Your guess: ");
                    userGuess = userInput.nextInt();
                    System.out.println();

                    if(userGuess < lowestHighGuess && userGuess > higherLowGuess){
                        guessInRange = true;
                    }else{
                        System.out.printf("The number you're looking for is between %d and %d\n", higherLowGuess + 1 , lowestHighGuess - 1);
                    }
                }while(!guessInRange);

                if(userGuess == numberToGuess){
                    stillInGame = false;
                    System.out.println("You guess it! it was " + numberToGuess);
                    System.out.printf("It took you %d attempts!\n" , attempts);

                }else if(userGuess > numberToGuess){
                    System.out.println("Too High");
                    lowestHighGuess = userGuess;
                    attempts++;

                }else{
                    System.out.println("Too Low");
                    higherLowGuess = userGuess;
                    attempts++;
                }

                if(attempts > maxAttempts){
                    stillInGame = false;
                    System.out.println("You ran out of attempts, you lose :( , the number was " + numberToGuess);
                }

            }while(stillInGame);

            System.out.println("Wanna play again? (Y / N)");
            //Clearing buffer of Scanner
            userInput.nextLine();
            String continueResponse = userInput.nextLine();

            if(!continueResponse.equalsIgnoreCase("y")){
                gameOn = false;
            }else{
                attempts = 1;
                stillInGame = true;
            }
        }while(gameOn);

        System.out.println("Thanks for playing!");
    }
}
