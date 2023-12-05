//Programmer: Dean Serrano
//Class: CS 145
//Date: 10/03/2023
//Lab 2: Card Shuffling and Dealing
//Purpose: Creates Card
//Extra Credit: N/A
//Issues in the code: None detected

public class Card
{
    private final String face;
    private final String suit;


    public Card(String faces, String suits)
    {
        this.face = faces.toString();
        this.suit = suits.toString();
    }
    
    //Makes a string from the card's information
    public String toString()
    {
        return face + " of " + suit;
    }
}