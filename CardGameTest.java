//Programmer: Dean Serrano
//Class: CS 145
//Date: 10/03/2023
//Lab 2: Card Shuffling and Dealing
//Purpose: Runs entire program
//Extra Credit: use of inheritance
//Issues in the code: None detected


public class CardGameTest extends DeckOfCards
{
   public static void main(String[] args) 
   {
      DeckOfCards myDeckOfCards = new DeckOfCards();
      myDeckOfCards.shuffle(); // place Cards in random order
        
      // Deals a fivecard hand
      for (int i = 1; i <= 5; i++) 
      {

         System.out.printf("%-19s", myDeckOfCards.dealCard());

         if (i % 5 == 0) 
         { 
            System.out.println();
         }
      }
      myDeckOfCards.checkHand(); // Checks user hand fordifferent matches 
   }
}