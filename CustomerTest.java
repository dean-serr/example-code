/* Programmer: Dean Serrano
 * Class: CS 145
 * Date: 11/30/2023
 * Assignment: Binary Search Tree Dictionary
 * Purpose: Create a Binary Tree of customer nodes with command functionality
 * Extra Credit: N/A
 * Issues in Code: Removal cannot handle removal of root node, or node with children, 
 * edit method will store node data change, but program is not displaying the change,
 * edit method also does not account for the reorganization of the tree if the user choose to 
 * edit the customer's name
 */
import java.util.Scanner;

public class CustomerTest 
{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) 
    {
        CustomerManager cust = new CustomerManager();

        System.out.println("Welcome to the Customer Database Program!");
        
        int i = 1;
        while(i == 1)
        {
            System.out.println("\n--List of commands--");
            System.out.println("(i) Insert New Customer");
            System.out.println("(r) Remove Customer");
            System.out.println("(d) Display List of Customers");
            System.out.println("(s) Search for Customer");
            System.out.println("(e) Edit Existing Customer");
            System.out.println("(q) Quit Program");

            System.out.print("\nPlease enter a command: ");
            String command = input.next();
            input.nextLine();

            switch(command)
            {
                case "i":
                    cust.insert();
                    break;
                case "r":
                    System.out.print("Which Customer would you like to remove (Full Name): ");
                    String custRemove = input.nextLine();
                    cust.remove(custRemove);
                    break;
                case "d":
                    System.out.println("\n--Display Commands--");
                    System.out.println("Enter (1) for A-Z");
                    System.out.println("Enter (2) for Z-A");
                    System.out.print("How would you like to display your customers: ");
                    int displayCommand = input.nextInt();
                    cust.display(displayCommand);
                    break;
                case "s":
                    System.out.print("Who would you like to search for (Full Name): ");
                    String custSearch = input.nextLine();
                    cust.search(custSearch);
                    break;
                case "e":
                    System.out.print("Which customer's information would you like to edit (Full Name): ");
                    String custEdit  = input.nextLine();
                    cust.editCustomer(custEdit);
                    break;
                case "q":
                    i++;
                default:
                    System.out.println("Whoops! That does not seem to be a valid input. Please ensure you entered\nthe right command in lowercase.");
                    break;
            }//End of switch
        }//End of while loop
    }//End of main
}//End of class
