package U599Research;

import java.util.*;

public class driver
{
	public static void main(String [] args)
	{
		while(true)
		{
			Scanner input = new Scanner(System.in);
			
			System.out.println("Enter the amount of Nodes to be generated: ");
			String inputNodes = input.nextLine();
			
			if (inputNodes.equalsIgnoreCase("stop"))
			{
				System.exit(0);
			}
			
			int amountOfNodes = Integer.parseInt(inputNodes);
			
			System.out.println("Enter the algorithm to be tested: ");
			String algorithmChoice = input.nextLine();
			
			if (inputNodes.equalsIgnoreCase("stop"))
			{
				System.exit(0);
			}
			
//			
//			graphGenerator testGraph = new graphGenerator(amountOfNodes);
//			
//			for (int i = 0; i < amountOfNodes; i++)
//			{
//				System.out.println(testGraph.grabNode(i).toString());
//			}
			
			Network test = new Network(algorithmChoice, amountOfNodes);
			
			long[] simRuns = test.simulationRun();
		}
		
	}
	
}
