package U599Research;

import java.util.*;

public class graphCreator 
{
	static int numberOfNodes;
	public int numberOfEdges;
	static Random r = new Random();
	ArrayList<NetworkNode> graphList = new ArrayList<>(numberOfNodes);
	
	public graphCreator(int n)
	{
		//constructor that creates a random graph with random edge weights based upon the arguement feed into the method
		//may need to have separate class
		numberOfNodes = n;
		numberOfEdges = r.nextInt(numberOfNodes*(numberOfNodes-1))+1; //Try to implement the geeks for geeks checks!
		
		for (int i = 0; i < numberOfNodes; i++)
		{
			NetworkNode node = new NetworkNode(i);
			graphList.add(node);
		}
		
		initalGraph(graphList, numberOfNodes);
		
		printNodeConnections(graphList, numberOfNodes);
		
		for (int i = 0; i < (3*numberOfNodes); i++) //This will create random connections between each of the nodes, until there are 4 connections per node
		{
			int[] howMannyConnect = new int[numberOfNodes];
			int edgeWeight = r.nextInt(100);
			randomPair randPair;
			
			for (int b = 0; b < numberOfNodes; b++)
			{
				howMannyConnect[b] = graphList.get(i).getArraySize();
			}
			
			int node1Rand = r.nextInt(numberOfNodes); //Grabs a random int value between 0 and numberOfNodes, this will allow for a random node to be chosen 
			int node2Rand = r.nextInt(numberOfNodes);
			
			randPair = checkRandNumbers(howMannyConnect, node1Rand, node2Rand, numberOfNodes);
			
			node1Rand = randPair.getRandOne();
			node2Rand = randPair.getRandTwo();
			
			NetworkNode node1 = graphList.get(node1Rand);
			NetworkNode node2 = graphList.get(node2Rand);
			
			while(node1.checkConnection(node2))
			{
				randPair = checkRandNumbers(howMannyConnect, node1Rand, node2Rand, numberOfNodes);
				
				node2Rand = randPair.getRandTwo();
				
				node2 = graphList.get(node2Rand);
			}
			
			node1.addEdge(node2, edgeWeight);
			node2.addEdge(node1, edgeWeight);
			
			howMannyConnect[node1Rand] += 1;
			howMannyConnect[node2Rand] += 1;
			
		}
		
	}
	
	public static void initalGraph(ArrayList<NetworkNode> graphList, int numNodes)
	{
		for (int i = 0; i < numNodes-1; i++) //Creates the base network, where we will know that all nodes are connected in some way
		{
			int randomEdgeWeight = r.nextInt(100);
				
			graphList.get(i).addEdge(graphList.get(i+1), randomEdgeWeight);
			graphList.get(i+1).addEdge(graphList.get(i), randomEdgeWeight);
		}
		
		printNodeConnections(graphList, numNodes);
		
	}
	
	public randomPair checkRandNumbers(int[] intArray, int r1, int r2, int numNode)
	{
		while((intArray[r1] == 3 || intArray[r2] == 3) || r1 == r2) //Checks if the nodes have greater then 4 connections a piece or if it is the same node
		{
			if (intArray[r1] == 3)
			{
				r1 = r.nextInt(numNode);
			}
			else if (intArray[r2] == 3)
			{
				r2 = r.nextInt(numNode);
			}
			else
			{	
//				r1 = r.nextInt(numNode);
				r2 = r.nextInt(numNode);
			}
		}
		
		randomPair pair = new randomPair(r1, r2);
		
		return pair;
	}
	
	public static void printNodeConnections(ArrayList<NetworkNode> graphList, int numNodes)
	{
		for (int i = 0; i < numNodes; i++)
		{
			System.out.println(graphList.get(i).toString());
		}
	}
	
	public NetworkNode grabNode(int index)
	{
		return graphList.get(index);
	}
	
}
