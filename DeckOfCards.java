//Programmer: Dean Serrano
//Class: CS 145
//Date: 10/03/2023
//Lab 2: Card Shuffling and Dealing
//Purpose: Creates a deck of cards, shuffles and deals deck, and cheks hand for different types of matches
//Extra Credit: use of package and enums
//Issues in the code: None detected

import java.security.SecureRandom;
import java.util.*;

public class DeckOfCards 
{
    private static final SecureRandom randomNumbers = new SecureRandom();
    
    private static final int NUMBER_OF_CARDS = 52; // Deck size
    private int currentCard = 0;
    // Variables for finding matches
    private int pairs = 0;
    private int threeOfKind = 0;
    private int fourOfKind = 0;

    private Card[] mainDeck = new Card[NUMBER_OF_CARDS]; // Deck array
    private ArrayList<Card> userHand = new ArrayList<Card>(); // Array list for the user's hand

    // Enum for card suits
    enum Suit
    {
        HEARTS("Hearts"), DIAMONDS("Diamonds"), SPADES("Spades"), CLUBS("Clubs");
 
        final String suit;
 
        Suit(String suitName)
        {
             this.suit = suitName;
        }
 
        // Returns suit name as String
        public String getSuit()
        {
            return suit;
        }
    } // End of Suit enum

    // Enum for the card faces
    enum Face
    {
        ACE("Ace"), TWO("Two"), 
        THREE("Three"), FOUR("Four"), 
        FIVE("Five"), SIX("Six"), 
        SEVEN("Seven"), EIGHT("Eight"), 
        NINE("Nine"), TEN("Ten"), JACK("Jack"), 
        QUEEN("Queen"), KING("King");

        final String face;

        Face(String faceName)
        {
            this.face = faceName;
        }

        // Returns face as string
        public String getFace()
        {
            return face;
        }
    } // End of Face enum

    // Constructor fills deck of Cards
    public DeckOfCards() 
    {
        // Array lists for face and suit strings
        ArrayList<String> faces = new ArrayList<String>();
        ArrayList<String> suits = new ArrayList<String>();

        // Adds each face to corresponding ArrayList
        for(Face f : Face.values())
        {
            String face = f.getFace();
            faces.add(face);
        }

        // Adds each suit to corresponding ArrayList
        for(Suit s : Suit.values())
        {
            String suit = s.getSuit();
            suits.add(suit);
        }
        
        // Creates deck with Card objects               
        for (int count = 0; count < mainDeck.length; count++)
        {
            mainDeck[count] = new Card(faces.get(count % 13), suits.get(count / 13));
        }                                                 
    } // End of CardDeck

    // Method to shuffle deck into random order
    public void shuffle()
    {
        // For each Card, pick another random Card (0-51) and swap them
        for (int first = 0; first < mainDeck.length; first++) 
        {
            // Select a random number between 0 and 51 
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // Swap current Card with randomly selected Card
            Card temp = mainDeck[first];   
            mainDeck[first] = mainDeck[second];
            mainDeck[second] = temp;
        }
    } // End of shuffle

    // Method to deal cards
    public Card dealCard() 
    {
        // Determine whether Cards remain to be dealt
        if (currentCard < mainDeck.length) 
        {
            userHand.add(mainDeck[currentCard]); // Adds card to user's hand
            return mainDeck[currentCard++]; // Return current Card in array
        }
        else
        {
           return null; // Return null to indicate that all Cards were dealt
        }
    }

    // Method checks user hand for matches and prints results
    public void checkHand()
    {
        // ArrayList to sort hand alphabetically
        ArrayList<String> sortedHand = new ArrayList<String>(); 
        
        // Variables for counting
        int matchCount = 1;
        int i = 0;

        // Puts userHand in sortedHand 
        for(int a = 0; a < userHand.size(); a++)
        {
            String cardFace = userHand.get(a).toString();
            String face = cardFace.substring(0, cardFace.indexOf(' '));
            sortedHand.add(face);
        }

        //Sorts sortedHand alphabetically
        Collections.sort(sortedHand);

        // Loop to check each card for a match
        while(i < sortedHand.size())
        {
            // Gets first card to compare
            String first = sortedHand.get(i);

            // Loop to check remaining cards against first
            for(int j = i + 1; j < sortedHand.size(); j++)
            {
                String second = sortedHand.get(j);

                // Checks for match
                if(first.equals(second))
                {
                    matchCount++; // Moves "first" card position
                }
            }
            i += matchCount;
            matchCheck(matchCount, pairs, threeOfKind, fourOfKind);
            matchCount = 1;
        }
        printCheck(pairs, threeOfKind, fourOfKind);
    } // End of checkHand

    // Method checks type of matches
    public void matchCheck(int m, int p, int t, int f)
    {
        if(m == 2)
        {
            pairs++;
        }
        else if(m == 3)
        {
            threeOfKind++;
        }
        else if(m == 4)
        {
            fourOfKind = 1;
        }
    } // End of matchCeck

    // Method prints card results
    public void printCheck(int p, int t, int f)
    {
        if(f == 1)
        {
            System.out.println("You have a four of a kind!");
        }
        else if(t == 1)
        {
            System.out.println("You have a three of a kind!");
        }
        else if(p == 1 && t == 1)
        {
            System.out.println("You have a full house!");
        }
        else if(p == 2)
        {
            System.out.println("You have two pairs!");
        }
        else if(p == 1)
        {
            System.out.println("You have a pair!");
        }
        else
        {
            System.out.println("Nothing better than a high card.");
        }
    } // End of printCheck
}