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

public class CustomerManager 
{

    static Scanner input = new Scanner(System.in);

    public class CustomerNode
    {
        private CustomerNode left;
        private CustomerNode right;

        private String customer;
        private String name;
        private String firstName;
        private String lastName;
        private String address;
        private String cityState;
        private String zipCode;
        private String phoneNum;

        public CustomerNode(String firstName, String lastName, String address, String cityState, String zipCode, String phoneNum)
        {
            this.name = firstName + " " + lastName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.cityState = cityState;
            this.zipCode = zipCode;
            this.phoneNum = phoneNum;
            this.customer = name + " | " + address + ", " + cityState + zipCode + " | " + phoneNum;
        }
    }

    private CustomerNode root;

    //Method allows user to create a new customer ndoe
    public CustomerNode newCustomer()
    {
        //Prompts for inputs
        System.out.println("-- Add a new customer --");
        System.out.print("(First Name): ");
        String firstName = input.nextLine();
        System.out.print("(Last Name): ");
        String lastName = input.nextLine();
        System.out.print("(Address): ");
        String address = input.nextLine();
        System.out.print("(City, State): ");
        String cityState = input.nextLine();
        System.out.print("(Zip Code): ");
        String zipCode = input.nextLine();
        System.out.print("(Phone Number): ");
        String phoneNum = input.nextLine();
        System.out.println();

        CustomerNode node = new CustomerNode(firstName, lastName, address, cityState, zipCode, phoneNum);
        node.left = null;
        node.right = null;

        return node;
    }//End of newCustomer

    //Method to insert a new customer
    public void insert()
    {
        root = insertHelp(root, newCustomer());
    }//End of insert

    //Helper method for insert
    public CustomerNode insertHelp (CustomerNode root, CustomerNode node)
    {
        String lastName = node.lastName;
        CustomerNode current = root;
        CustomerNode parent;

        if(root == null) //Initializes new tree if one does not exist
        {
            root = node;
            return root;
        }
        else
        {
            while(true) //Loops til insertion is complete
            {
                parent = current; //Parent will hold the previously "pointed-to" node so once space is found the new node can be inserted

                if(lastName.compareTo(current.lastName) < 0)
                {
                    current = current.left;
                    if(current == null)
                    {
                        parent.left = node; //Attaches new node
                        return root;
                    }
                }
                else if(lastName.compareTo(current.lastName) > 0)
                {
                    current = current.right;
                    if(current == null)
                    {
                        parent.right = node; //Attaches new node
                        return root;
                    }
                }
            }
        }
    }//End of insertHelp

    //Method to display in either in order, or post order
    public void display(int command)
    {
        switch(command)
        {
            case 1:
                System.out.println();
                inDisplayHelp(root);
                break;
            case 2:
                System.out.println();
                postDisplayHelp(root);
                break; 
        }
    }//End of display

    //Helper method for in order display
    public void inDisplayHelp(CustomerNode root)
    {
        if(root != null)
        {
            inDisplayHelp(root.left);
            System.out.println(root.customer);
            inDisplayHelp(root.right);
        }
    }//End of inDisplayHelper

    //Helper method for post order display
    public void postDisplayHelp(CustomerNode root)
    {
        if(root != null)
        {
            postDisplayHelp(root.right);
            System.out.println(root.customer);
            postDisplayHelp(root.left);
        }
    }//End of postDisplayHelper

    //Method to find and display customer
    public void search(String name)
    {
        String lastName = name.substring(name.indexOf(' ') + 1);
        CustomerNode current = root;
        if(root != null)
        {
            int i = 1;
            while(i == 1)
            {
                if(current == null)
                {
                    System.out.println("It looks like we couldn't find a contact with that name.");
                    i++;
                }
                else if(lastName.compareTo(current.lastName) == 0) //For once node is found
                {
                    System.out.println("Customer Found!");
                    System.out.println(current.customer);
                    i++;
                }
                else if(lastName.compareTo(current.lastName) < 0) //Traverses left
                {
                    current = current.left;
                }
                else //Traverses right
                {
                    current = current.right;
                }
            }
        }
        else
        {
            System.out.println("It looks like the customer database is empty. Please add people using the 'insert' action.");
        }
    }//End of search


    //Method to remove a node
    public void remove(String name)
    {
        root = removeHelp(root, name);
    }//End of remove

    //Helper method for remove
    public CustomerNode removeHelp(CustomerNode root, String name)
    {
        String lastName = name.substring(name.indexOf(' ') + 1);
        CustomerNode current = root;
        CustomerNode parent;

        if(root == null) //For if tree is empty
        {
            System.out.println("There seems to be no customers in the system. Please add some using the 'insert' action");
            return root;
        }
        else
        {
            while(true)
            {
                parent = current; //Parent will hold the previously pointed to node so once space is found the new node can be inserted

                if(lastName.compareTo(current.lastName) < 0)
                {
                    current = current.left;
                    if(lastName.compareTo(current.lastName) == 0)
                    {
                        parent.left = null; //Deletes new node
                        return root;
                    }
                }
                else if(lastName.compareTo(current.lastName) > 0)
                {
                    current = current.right;
                    if(lastName.compareTo(current.lastName) == 0)
                    {
                        parent.right = null; //Deletes new node
                        return root;
                    }
                }
            }
        }
    }//End of removHelp

    //Method to edit data in a customer node
    public void editCustomer(String name)
    {
        root = editCustomerHelp(root, name);
    }//End of editCustomer

    //Helper method for editCustomer
    public CustomerNode editCustomerHelp(CustomerNode root, String name)
    {
        String lastName = name.substring(name.indexOf(' ') + 1);
        CustomerNode current = root;
        if(root != null)
        {
            int i = 1;
            while(i == 1)
            {
                if(current == null) //For if customer does not exist
                {
                    System.out.println("It looks like we couldn't find a contact with that name.");
                    i++;
                }
                else if(lastName.compareTo(current.lastName) == 0) //For once node is found
                {
                    int j = 1;
                    
                    while(j == 1)
                    {
                        System.out.println("Field Options:");
                        System.out.println("(n) - Name");
                        System.out.println("(a) - Address");
                        System.out.println("(c) - City, State");
                        System.out.println("(z) - Zip Code");
                        System.out.println("(p) - Phone Number");
                        System.out.print("Which field would you like to edit: ");
                        String editField = input.nextLine();

                        switch (editField)
                        {
                            case "n":
                                System.out.print("New First Name: ");
                                String newFirst = input.nextLine();
                                System.out.print("New Last Name: ");
                                String newLast = input.nextLine();
                                current.firstName = newFirst;
                                current.lastName = newLast;
                                j++;
                                break;
                            case "a":
                                System.out.print("New Address: ");
                                String newAddress = input.nextLine();
                                current.address = newAddress;
                                j++;
                                break;
                            case "c":
                                System.out.print("New City, State: ");
                                String newCityState = input.nextLine();
                                current.cityState = newCityState;
                                j++;
                                break;
                            case "z":
                                System.out.print("New Zip Code: ");
                                String newZipCode = input.nextLine();
                                current.zipCode = newZipCode;
                                j++;
                                break;
                            case "p":
                                System.out.print("New Phone Number: ");
                                String newPhoneNum = input.nextLine();
                                current.phoneNum = newPhoneNum;
                                j++;
                                break;
                            default:
                                System.out.print("!Invalid input!");
                                System.out.print("Please choose an action from the Field Options.");
                                break;
                        }//End of switch
                        return root;
                    }//End of while
                    i++;
                }
                else if(lastName.compareTo(current.lastName) < 0) //Traverses left
                {
                    current = current.left;
                }
                else //Traverses right
                {
                    current = current.right;
                }
            }//End of while
        }
        else //For if tree is empty
        {
            System.out.println("It looks like the customer database is empty. Please add people using the 'insert' action.");
        }
        return root;
    }//End of editCustomerHelp
}//End of class