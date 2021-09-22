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
		
		List<List<NetworkNode>> graphList = new ArrayList<>(numberOfNodes);
		
		for (int i = 0; i < numberOfNodes; i++)
		{
			NetworkNode node = new NetworkNode(i);
			graphList.add((List<NetworkNode>) node);
		}
		
		for (int i = 0; i < numberOfEdges; i++)
		{
			boolean[] checkConnect = new boolean[numberOfNodes];
			
			if (checkConnect[i])
			{
				NetworkNode node1 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				//Try to grab a random int that does not equal i
				NetworkNode node2 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				
				while(node1.addEdge(node2, r.nextInt(100)))
				{
					//Runs loop until AlreadyAdded is not True
					node2 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				}
				
				node2.addEdge(node1, r.nextInt(100));
			}
			else
			{
				NetworkNode node1 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				NetworkNode node2 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				
				while(node1.addEdge(node2, r.nextInt(100)))
				{
					//Runs loop until AlreadyAdded is not True
					node2 = (NetworkNode) graphList.get(r.nextInt(numberOfNodes));
				}
				
				node2.addEdge(node1, r.nextInt(100));
			}
			
		}
		
	}
	
}
