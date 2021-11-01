package U599Research;

import java.util.*;
import java.lang.Math;

///This class handles the generation of the dataset graph based upon the inputed value of the amount of nodes to be generated, as well as a debugger method, random pair checker method and the inital graph connection method 
//for ensuring a continuous connection throughout the generated graph
public class graphGenerator 
{
	static int numberOfNodes; //This int value will store the amount of nodes to be generated within the generated graph
	
	static Random r = new Random(); //This statement creates a random object for generating random int values
	
	
	ArrayList<NetworkNode> graphList = new ArrayList<>(numberOfNodes); //This statement creates a ArrayList that will store the node reference to each NetworkNode object within the generated graph
	
	//This method constructs a graphGenerator object that will store an ArrayList object that will store a list of NetworkNode object with varying weighted distances between each other 
	//as well as being completely connected without the chance of an isolated sphere of the network being generated. It also takes in an int value n that will be used to generated n nodes within the graph. 
	public graphGenerator(int n)
	{
		numberOfNodes = n; //sets the numberOfNodes value based upon n
		
		int[][] accuracyCheck = new int[numberOfNodes][numberOfNodes];
		
		for (int i = 0; i < numberOfNodes; i++) //This for loop generates the initial blank NetworkNode object based upon how many nodes need to generated and add the node to the graph's ArrayList
		{
			NetworkNode node = new NetworkNode(i);
			graphList.add(node);
		}
		
		initalGraph(graphList, numberOfNodes); //This statement calls upon the initalGraph method to generate an initial graph that is fully connected (This ensures no disconnected spheres) 
		
		int[] howMannyConnect = new int[numberOfNodes]; //This statement creates an int array that will store the current amount of connections for each specific node within the graph. 
		
		for (int i = 0; i < numberOfNodes; i++) //This for loop will grab the initial amount of connections from each node to record within the int array
		{
			howMannyConnect[i] = graphList.get(i).getArraySize();
		}
		
		for (int i = 0; i < (numberOfNodes/2); i++) //This for loop will create random connections between each of the nodes, until there are at most 4 connections per node or numberOfNodes/2 connections have been generated
		{
			
			int edgeWeight = r.nextInt(100); //This statement will initialize an edgeWeight value for the current random connection that will range from 0 to 100
			randomPair randPair; //Creates an initial randomPair object for storing the chosen nodes for a new connection
			
			int node1Rand = r.nextInt(numberOfNodes); //Grabs a random int value between 0 and numberOfNodes, this will allow for a random node to be chosen 
			int node2Rand = r.nextInt(numberOfNodes); //^^^^
			
			randPair = checkRandNumbers(howMannyConnect, node1Rand, node2Rand, numberOfNodes); //This will call upon the checkRandNumbers method which will return a randomPair object 
			
			node1Rand = randPair.getRandOne(); //These two statements will take out the random node values that were generated from the randomPair object
			node2Rand = randPair.getRandTwo();
			
			NetworkNode node1 = graphList.get(node1Rand); //These two statements will create node references based upon the generated node values and will be used for creating the new node connection
			NetworkNode node2 = graphList.get(node2Rand);
			
			while(node1.checkConnection(node2)) //This will loop will check if the first node and second node already have a connection, if so it will generate another node2 value until the it reaches an unconnected node
			{
				node2Rand = r.nextInt(numberOfNodes); 
				
				randPair = checkRandNumbers(howMannyConnect, node1Rand, node2Rand, numberOfNodes); //This will validate the newly chosen random int value for node2 and return a new RandomPair object
				
				node2Rand = randPair.getRandTwo();
				
				node2 = graphList.get(node2Rand); //This will grab out a new node reference for node2 based upon the new random int value. 
			}
			
			node1.addEdge(node2, edgeWeight); //These two statements will add the nodes together as neighbors within their own ArrayLists, utilizing the other node's reference and the newly generated edge weight
			node2.addEdge(node1, edgeWeight);
			
			howMannyConnect[node1Rand] += 1; //These two statements will index the new connections within the howMannyConnect int array 
			howMannyConnect[node2Rand] += 1;
			
		}
		
	}
	
	//This method will create a linear connection across the ArrayList graph to ensure the network is continuous and there are no isolated portions not connected to the other parts of the graph
	//This method takes in the Graph's ArrayList and the amount of nodes to be created based upon the numberOfNodes value
	public static void initalGraph(ArrayList<NetworkNode> graphList, int numNodes)
	{
		for (int i = 0; i < numNodes-1; i++) //This for loop will add each of the linear connections going one by one down the graph ArrayList
		{
			int randomEdgeWeight = r.nextInt(100); //This statement will initialize an edgeWeight value for the current random connection that will range from 0 to 100
				
			graphList.get(i).addEdge(graphList.get(i+1), randomEdgeWeight);  //These two statements will add the nodes toether as neighbors within their own ArrayLists, utilizing the other node's reference and the newly generated edge weight
			graphList.get(i+1).addEdge(graphList.get(i), randomEdgeWeight);
		}

	}

	//This method will take in the howManyConnect int array, the first random generated int value, the second random generated int value and the amount of nodes to be generated within the graph 
	//This method will return a randomPair object
	public randomPair checkRandNumbers(int[] intArray, int r1, int r2, int numNode)
	{
		while((intArray[r1] == 4 || intArray[r2] == 4) || r1 == r2) //This for loop checks if the nodes have greater then 4 connections a piece or if it is the same node, if any of the conditions are flagged then a new second int value will be generated
		{
			if (intArray[r1] == 4)
			{
				r1 = r.nextInt(numNode);
			}
			else if (intArray[r2] == 4)
			{
				r2 = r.nextInt(numNode);
			}
			else
			{	
				r2 = r.nextInt(numNode);
			}
		}
		
		randomPair pair = new randomPair(r1, r2); //This creates a randomPair object to return the newly generated random in values 
		
		return pair;
	}
	
	//This method takes in the graph's ArrayList and the int value representing the amount of nodes to be generated
	//It does not return a direct value. This method is specifically for debugging. 
	public String printNodeConnections(ArrayList<NetworkNode> graphList, int numNodes)
	{
		if (numNodes == 0)
		{
			return "\n";
		}
		else
		{
			return graphList.get(Math.abs(numNodes - numberOfNodes)).toString() + "\n" + printNodeConnections(graphList, numNodes-1);
		}
	}
	
	public NetworkNode grabNode(int index) //getter for a specific NetworkNode object within the graph
	{
		return graphList.get(index);
	}
	
	public int getNumOfNodes() //getter for the numberOfNodes int value
	{
		return numberOfNodes; 
	}

	public static void setNumberOfNodes(int numberOfNodes) //setter for the numberOfNodes int value
	{
		graphGenerator.numberOfNodes = numberOfNodes;
	}

	public static Random getR() //getter for the random object r
	{
		return r;
	}

	public static void setR(Random r) //setter for the random object r
	{
		graphGenerator.r = r;
	}

	public ArrayList<NetworkNode> getGraphList() //getter for the graph's ArrayList
	{
		return graphList;
	}

	public void setGraphList(ArrayList<NetworkNode> graphList) //setter for the graph's ArrayList
	{
		this.graphList = graphList;
	}
	
	
}
