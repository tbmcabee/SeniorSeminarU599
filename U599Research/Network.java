package U599Research;

import java.util.*;


public class Network
{	
	Random r = new Random();
	
	public Network(graphGenerator g, Object a, int num)
	{
		//constructor takes in the created graph and the algorithm and then simulates it "num" times
		//Will use nodePicker, recordTime, etc
	}
	
	public randomPair nodePicker(graphGenerator graph) 
	{
		int graphSize = graph.getNumOfNodes();
		int node1 = r.nextInt(graphSize);
		int node2 = r.nextInt(graphSize);
		
		while(node1 == node2)
		{
			node2 = r.nextInt(graphSize);
		}
		
		randomPair newNodePair = new randomPair(node1, node2);
		
		return newNodePair;
		//picks the two specific nodes that will be used in the SPP calculation 
	}
	
	
	public double recordTime()
	{
		return 0;
		//method records the time per simulation, may need to have seperate class
	}
	
	
	public void saveMatrix(Object matrix)
	{
		//saves the generated graph to a txt file, will need to also have a readMatrix method
	}
	
	
	public Object readMatrix(String fileName)
	{
		return fileName;
		//reads the saved graph from a txt file and reloads the graph for testing
	}

	public void graphGenerator() 
	{
		// TODO Auto-generated method stub
		
	}

	public Object graphToMatrix(Object graph) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object matrixToGraph(Object matrix) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
