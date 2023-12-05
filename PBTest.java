/* Programmer: Dean Serrano
Class: CS 145
Date: 10/31/2023
Assignment 2: Phone Book
Purpose: Create a linked list of contacts provided by the user allowing them to view and edit the created list
Extra Credit: N/A
Issues in the code: Program does not sort alphabetically. */

import java.util.Scanner;

public class PBTest 
{
    static Scanner input = new Scanner(System.in);
    // Main method
    public static void main(String[] args)
    {

        PBManager pb = new PBManager();

        pb.intro();

        int i  = 0;
        while(i < 1) // Loop to keep taking user commands
        {
            pb.commands();
            System.out.print("\nPlease enter a command: ");
            String userCommand = input.next();
            System.out.println();

            switch(userCommand.toLowerCase()) // Runs corresponding command
            {
                case "a": // Adds a contact
                    pb.add();
                    break;
                case "d": // Delete contact
                    pb.delete();
                    break;
                case "s":  // Displays entire contact list
                    pb.displayList();
                    break;
                case "l": // Searches for and displays specific contact
                    pb.displayContact();
                    break;
                case "q": // Quits entire program
                    System.out.println("Thank you for using this Phone Book program! :)");
                    i++;
                    break;
            }// End of switch case
        } // End of while loop
    } // End of main
} // End of class