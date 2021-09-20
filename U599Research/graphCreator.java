package U599Research;

import java.util.*;
import java.io.*;

public class graphCreator 
{
	private int numberOfNodes = 0;
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
			for (int b = 0; b < numberOfNodes; b++)
			{
				
			}
		}
		
	}
	
	public void addEdge(NetworkNode a, NetworkNode b)
	{
		a.addEdge(b);
		b.addEdge(a);
	}
	
}
