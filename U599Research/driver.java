package U599Research;

import java.util.*;

public class driver
{
	public static void main(String [] args)
	{
		//An infinite loop for letting the user input multiple test cases without constantly running the program 
		//More of a convience then anything
		while(true)
		{
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			
			System.out.println("Enter the amount of Nodes to be generated: ");
			String inputNodes = input.nextLine(); //These statements take in a string value that will represent the amount of nodes the user wants to generate and test
			
			//This if statement allows the user to input the word stop to break out of the infinite loop
			if (inputNodes.equalsIgnoreCase("stop"))
			{
				System.exit(0);
			}
			
			int amountOfNodes = Integer.parseInt(inputNodes); //A conversion value that changes the user's input into a usable int value for deciding on the amount of generated nodes
			
			//The below commented out code will allow the user to input a string value, depending on that value the program will run either Bellman Ford algorithm or Dijkstra algorithm
			
//			System.out.println("Enter the algorithm to be tested: ");
//			String algorithmChoice = input.nextLine();
//			
//			if (inputNodes.equalsIgnoreCase("stop"))
//			{
//				System.exit(0);
//			}
			
			//Constructor that will create a network object, this object takes in an int value that will decide on the amount of generated nodes within the graph 
			//and the specific algorithm that will run. Normally it takes in an input string from above, however for the current prototype it is set to a default value
			//of Dijkstra so that it automatically runs the Dijkstra test
			Network test = new Network("both", amountOfNodes);
			
			//This statement calls upon the simulationRun method from the network class that will run the specifically decided algorithm through the generated test graph, and then send that data from 
			//the test into a initialized array of long values. 
			long[] simRuns = test.simulationRun();
			
			//The following group of statements simply outputs the simulated data into an easily readable format. This method will be utilized for reading computed data
			//until the txt file recording method is implemented into the program.
			System.out.println();
			System.out.println("The average computational time (ns) of the 3 tested pairs within the Dijkstra generated graph ");
			
			for (int i = 0; i < 3; i++)
			{
				System.out.println("Tested Pair #" + (i+1) + " Computational Time: " + simRuns[i]);
			}
			
			System.out.println();
			System.out.println("The average computational time (ns) of the 3 tested pairs within the Bellman Ford generated graph ");
			
			for (int i = 0; i < 3; i++)
			{
				System.out.println("Tested Pair #" + (i+3) + " Computational Time: " + simRuns[i+3]);
			}
			
			System.out.println("Would you like to show the generated graph and its connections? (y/n)");
			inputNodes = input.nextLine();
			
			if (inputNodes.equalsIgnoreCase("y"))
			{
				test.getNetworkGraph().printNodeConnections(test.getNetworkGraph().getGraphList(), amountOfNodes);
				
			}
			
			
		}
		
	}
	
}
