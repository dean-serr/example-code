//Programmer: Dean Serrano
//Class: CS& 141
//Date: 05/20/2023
//Lab 4: Guessing Game
//Purpose: This program allows the user to play multiple guessing games of a random number between 1 and 100 as well as keep track of the user’s overall play statistics.
//Extra Credit: N/A
//Issues in the code: None detected

import java.util.Scanner;
import java.util.Random;


public class GuessingGame
{
    //Start of main method
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        //Initialized game statistics 
        int totalGames = 0;
        int totalGuess = 0;
        int lowestGuess = 0;

        intro();

        //Loop to allow use to play more than one game
        int i = 1;
        while(i == 1)
        {
            int lastGuessNum = runGame(0);

            totalGuess += lastGuessNum;
            totalGames++;

            //Determines which game had the best play or least amount of guesses 
            if(lowestGuess == 0) //Initalizes first guess
            {
                lowestGuess = lastGuessNum;
            }
            else if(lowestGuess <= lastGuessNum && lowestGuess != 0) //If the new guessNum is more than the previous nothing changes
            {
                lowestGuess = lowestGuess;
            }
            else if(lowestGuess > lastGuessNum) //If the new guessNum is less then the previous lowestGuess change to the new value
            {
                lowestGuess = lastGuessNum;
            }

            //Asks user if they would like to play another round
            System.out.print("Do you want to play again(Yes or No)? ");
            String a = input.next();

            //Converts user input to either 'y' or 'n'
            String b = a.toLowerCase();
            String playAgain = b.substring(0,1);

            if(playAgain.equals("y")) //Will loop back to "game" method
            {
                System.out.println();
            } 
            else if(playAgain.equals("n")) //Ends while loop
            {
                System.out.println();
                i++;
            }
        }

        results(totalGuess, totalGames, lowestGuess);

    } //End of main method



    //Start of method to introduce user to how the game works
    public static void intro()
    {
        System.out.println("This program allows you to play a guessing game.\n");
        System.out.println("- I will think of a number between 1 and 100, ");
        System.out.println("and you will guess until you get it.");
        System.out.println("- For each guess, I will tell you whether the ");
        System.out.println("right answer is higher or lower than your guess.\n");
    } //End of intro method

    //Start of method that creates one game of guessing
    public static int runGame(int x)
    {
        //Randomizes number from 1 - 100
        Random rand = new Random();
        int answer = rand.nextInt(100) + 1;
        Scanner input = new Scanner(System.in);

        System.out.println("I am thinking of a number between 1 and 100...");
        
        int totalGuess = x;

        int guessNum = 0;
        int j = 0;
        while(j < 1)
        {
            System.out.print("Your guess: ");
            int userGuess = input.nextInt();
            guessNum++;
            
            //Determines and tells the user whether their guess is higher, lower, or matches answer
            if(userGuess > answer)
            {
                System.out.println("It's lower.");
            }
            else if(userGuess < answer)
            {
                System.out.println("It's higher.");        
            }
            else if(userGuess == answer && guessNum == 1) //Used if user guesses correct in one guess. Ends while loop.
            {
                System.out.printf("You got it right in %d guess!\n", guessNum);
                totalGuess += guessNum;
                j++;
            }
            else if(userGuess == answer) //Ends while loop
            {
                System.out.printf("You got it right in %d guesses!\n", guessNum);
                totalGuess += guessNum;
                j++;
            }
        }
        return totalGuess;
    } //End of game method

    //Start of method to calculate and print game statistics
    public static void results(float x, float y, int z)
    {
        Float guessAverage = x / y;
        
        System.out.println("Overall results:\n");
        System.out.printf("Total games played: %.0f\n", y);
        System.out.printf("Total guesses made: %.0f\n", x);
        System.out.printf("Average guesses per game: %.1f\n", guessAverage);
        System.out.println("Best game: " + z);
    } //End of results method
} //End of class