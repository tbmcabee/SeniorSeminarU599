package U599Research;

import java.io.IOException;
import java.util.*;

public class driver
{
	public static void main(String [] args)
	{
		boolean notStop =  true;
		//An infinite loop for letting the user input multiple test cases without constantly running the program 
		//More of a convience then anything
		while(notStop)
		{
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			System.out.println("Enter the amount of Nodes to be generated: ");
			String inputNodes = input.nextLine(); //These statements take in a string value that will represent the amount of nodes the user wants to generate and test
			
			//This if statement allows the user to input the word stop to break out of the infinite loop
			if (inputNodes.equalsIgnoreCase("stop"))
			{
				notStop = false;
			}
			else
			{
				int amountOfNodes = Integer.parseInt(inputNodes); //A conversion value that changes the user's input into a usable int value for deciding on the amount of generated nodes
				
				//The below commented out code will allow the user to input a string value, depending on that value the program will run either Bellman Ford algorithm or Dijkstra algorithm
				
				System.out.println("Enter the algorithm to be tested: (\"dijkstra\", \"bellman\", or \"both\") ");
				String algorithmChoice = input.nextLine();
				
				System.out.println("Enter the name of the file to be generated with dataset result!");
				String inputFilename = input.nextLine(); //These statements take in a string value that will represent the amount of nodes the user wants to generate and test
				
				if (!(algorithmChoice.equalsIgnoreCase("dijkstra") || algorithmChoice.equalsIgnoreCase("bellman") || algorithmChoice.equalsIgnoreCase("both")))
				{
					System.out.println("Invalid Selection, must be dijkstra, bellman or both...");
					continue;
				}
				else
				{
					runTest(inputFilename, algorithmChoice, amountOfNodes);
				}
			
			}
		}
			
		
	}
	
	public static void runTest(String filename, String choice, int amtOfNodes)
	{
		//Constructor that will create a network object, this object takes in an int value that will decide on the amount of generated nodes within the graph 
		//and the specific algorithm that will run. Normally it takes in an input string from above, however for the current prototype it is set to a default value
		//of Dijkstra so that it automatically runs the Dijkstra test
		Network test = new Network(choice, amtOfNodes);
		
		try 
		{
			test.writeResults(filename, choice);
		} 
		catch (IOException exp) 
		{
			System.out.println("File write was unsuccessful!!!");
			exp.printStackTrace();
		}
	}
	
}
