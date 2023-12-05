/* Programmer: Dean Serrano
Class: CS 145
Date: 10/31/2023
Assignment 2: Phone Book
Purpose: Create a linked list of contacts provided by the user allowing them to view and edit the created list
Extra Credit: N/A
Issues in the code: Program does not sort alphabetically. */

import java.util.Scanner;

public class PBManager
{
    static Scanner input = new Scanner(System.in);

    private int length; // Keeps track of the number of objects in list

    // Class for making node
    public class PBNode 
    {
        private PBNode next;

        private String name;
        private String address;
        private String cityState;
        private String phoneNum;

        // Constructor method
        public PBNode(String firstName, String lastName, String address, String cityState, String phoneNum)
        {
            this.name = firstName + " " + lastName;
            this.address = address;
            this.cityState = cityState;
            this.phoneNum = phoneNum;
        } // End of PBNode

        // Concatenates contact information together
        public String makeContact()
        {
            return name + " | " + address + ", " + cityState + " | " + phoneNum; 
        } // End of make Contact

    } // End of contructor class

    private PBNode front;
    
    // Method add new entry to list
    public void add()
    {
        // Obtains contact information from user
        System.out.println("-- Add a new contact --");
        System.out.print("(First Name): ");
        String firstName = input.nextLine();
        System.out.print("(Last Name): ");
        String lastName = input.nextLine();
        System.out.print("(Address): ");
        String address = input.nextLine();
        System.out.print("(City, State): ");
        String cityState = input.nextLine();
        System.out.print("(Phone Number): ");
        String phoneNum = input.nextLine();

        PBNode node = new PBNode(firstName, lastName, address, cityState, phoneNum);
        node.next = null;

        if(front == null) // Adds new node to empty list
        {
            front = node; // sets front to new node
            length++;
        }
        else // Adds a node to the end of the list
        {
            PBNode current = front;
            while(current.next != null) // Checks for the end of the list
            {
                current = current.next; 
            }
            current.next = node;
            length++;
        }
    } // End of add

    // Method deletes a chosen entry
    public void delete()
    {
        System.out.println("-- Delete a Contact --");
        System.out.print("Which number entry would you like to remove: ");
        int index = input.nextInt();
        index = index - 1;

        if(index == 0)
        {
            front = front.next;
        }
        else
        {
            PBNode current = front;
            PBNode n1 = null;
            for(int i = 0; i < index - 1; i++) // Moves through list til defined contact is found
            {
                current = current.next; // Sets current to the found entry
            }
            // Removes desired contact and reconnects the surrounding contacts
            n1 = current.next;
            current.next = n1.next;
            System.out.println("The contact \"" + n1.makeContact() +"\" was removed");
        }
    } // End of delete

    //Method displays entire list
    public void displayList()
    {
        PBNode node = front;
        
        if(front == null) // If list is empty
        {
            System.out.println("Your phone book is empty.");
        }
        else
        {
            int i = 1;

            System.out.println("-- Your Contact List --");
            
            while(node.next != null) // Stops at last element in list
            {
                System.out.println(i + ") " + node.makeContact()); // Prints each entry on its own line
                node = node.next; // Moves to next contact
                i++;
            }
            System.out.println(i + ") " + node.makeContact()); // Prints last element
        }
    } // End of displayList

    // Method to display specifc contact by first and last name
    public void displayContact()
    {
        System.out.println("-- Search for Contact --");
        System.out.println("(look for a specific contact by entering their first and last name)");
        System.out.print("Who are you looking for?: ");
        String contactName = input.nextLine();

        PBNode node = front;
        
        if(front == null) // If list is empty
        {
            System.out.println("Your phone book is empty.");
        }
        else
        {
            try
            {
                int i = 0;
                while(i <= length) // Goes through entire list 
                {
                    if(contactName.equals(node.name)) // If matching contact is found
                    {
                        System.out.println("\n" + node.makeContact());
                        i = length + 1; // Ends loop
                    }
                    else // If contact not found, moves to next object
                    {
                        node = node.next;
                    }
                    i++;
                }
            }
            catch(Exception e)
            {
                System.out.println("\nIt looks like a contact by that name does not exist :/");
            }
        }
    } // End of displayContact

    // Method to introduce game
    public void intro()
    {
        System.out.println("Welcome to your Phone Book!");
        System.out.println("Here you can browse through, add, delete, and edit all your contacts.");
    } // End of intro

    // Methods to display command list
    public void commands()
    {
        System.out.println("\n-- List of Commands --");
        System.out.println("| (a) add a contact");
        System.out.println("| (d) delete a contact");
        System.out.println("| (s) show contact list");
        System.out.println("| (l) look for contact by name");
        System.out.println("| (q) quit program");
    } // End of commands
} // End of manager class
