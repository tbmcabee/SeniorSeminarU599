package U599Research;

import java.util.*;
import java.io.*;

public class graphCreator 
{
	static int numberOfNodes = 0;
	private int numberOfEdges = 0;
	
	@SuppressWarnings("unchecked")
	public graphCreator(int n)
	{
		//constructor that creates a random graph with random edge weights based upon the arguement feed into the method
		//may need to have separate class
		numberOfNodes = n;
		
		Random r = new Random();
		
		numberOfEdges = r.nextInt(numberOfNodes) + 1;
		
		ArrayList<NetworkNode> graphList = new ArrayList<>(numberOfNodes);
		
		for (int i = 0; i < numberOfNodes; i++)
		{
			NetworkNode node = new NetworkNode(i);
			graphList.add(node);
		}
		
		for (int i = 0; i < numberOfEdges; i++)
		{
			int[] checkConnect = new int[numberOfNodes];
			
			for (int b = 0; b < numberOfNodes; b++)
			{
				checkConnect[b] = 0;
			}
			
			int node1Rand = r.nextInt(numberOfNodes);
			int node2Rand = r.nextInt(numberOfNodes);
			
			while((checkConnect[node1Rand] >= 4 && checkConnect[node2Rand] >= 4) || node1Rand == node2Rand)
			{
				node1Rand = r.nextInt(numberOfNodes);
				node2Rand = r.nextInt(numberOfNodes);
			}
			
			//Need to check if the node has been added yet!!
			
			NetworkNode node1 = graphList.get(node1Rand);
			NetworkNode node2 = graphList.get(node2Rand);
			
			while(node1.addEdge(node2, r.nextInt(100)))
			{
				//Runs loop until AlreadyAdded is not True
				node2 = graphList.get(r.nextInt(numberOfNodes));
			}
			
			node2.addEdge(node1, r.nextInt(100));
			
		}
		
	}
	
}
