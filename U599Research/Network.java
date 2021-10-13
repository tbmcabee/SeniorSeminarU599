package U599Research;

import java.util.*;


//The network class acts as a transition of the testing implementation portion of the program's code. This class calls upon the data generation portion of the program to generate
//the tested graphs and to act as a way to record the computational data. This class does not run any specific simulation, rather it will start and stop simulations, record its data
//and then repeat until it has taken in sufficient data. Therefore, the main purpose of the Network class is to handle the program tests based upon the input it receives from the driver. 
public class Network
{	
	String choosenAlg; //A string value for choosing the specific algorithm to run within the simulation
	int nodeNum; //The amount of nodes to generate within the graph
	long[] avgSimTimes; //The long array that will be returned after the simulations have ran
	
	graphGenerator networkGraph; //The reference to the generated graph. It has not been generated yet, however this acts as a reference for the rest of the program to refer to if the network class
								//needs it in other portions of the code.
	
	Random r = new Random(); //This constructor will be utilized within the nodePicker method to generate the random int values for choosing node pairs. 
	
	//This constructor takes in the algorithm string for choosing the algorithm to run within the simulations, the amount of nodes to be generated, and then based upon the nodeNum, the constructor will
	//initialize the graph to be generated. 
	public Network(String a, int num)
	{
		this.choosenAlg = a;
		this.nodeNum = num;
		this.networkGraph = new graphGenerator(nodeNum);
	}
	
	//This method will handle the specific recording of the simulated data and the initiation of the recorded data. It will also handle which algorithm will be ran in the simulation based upon the initialized
	//choosenAlg value. 
	public long[] simulationRun()
	{
		long avgSimRoundTime = 0; //This is the final avg value used to store the value of the avg running time between 50 simulations
		long totalSimRoundTime = 0; //This is a running value used to keep the sum of the runs, it will be used to calculate the avg run time (ns)
		int numOfSimulations = 50; //This variable will store the amount of simulations to be ran in a simulation round
		int numOfPairs = 3; //This variable will store the amount of node pairs to be chosen per test
		int inital; //This simply initialized the initial int, this will store a node int value
		int finish; //This simply initialized the finish int, this will store a node int value
		
		//initializes the long array for recording the avg run time of each simulation round
		avgSimTimes = new long[numOfPairs]; 
		
		
		if (this.choosenAlg.equalsIgnoreCase("dijkstra")) //runs the simulations for Dijkstra algorithm
		{
			for (int i = 0; i < numOfPairs; i++) //This for loop runs the amount of node pairs chosen times
			{
				//this creates a randomPair object to store the random nodes chosen for this specific pair
				randomPair currentNodePair = nodePicker(this.networkGraph);
				
				inital = currentNodePair.getRandOne();
				finish = currentNodePair.getRandTwo();
				
				for (int b = 0; b < numOfSimulations; b++) //This for loop runs 1 simulation round's worth of simulations
				{
					//This will initalize a Dijkstra object for 1 simulation and run it based upon the generated graph, the initial node and the finish node
					Dijkstra test = new Dijkstra(networkGraph, inital, finish);
					//This will add the time ran for the specific simulation onto the running time total for the simulation round
					totalSimRoundTime += test.getElapsedTime(); 
				}
				
				//This ground of statements will divide out the avg time ran for the simulation round and then add it onto the long array
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				avgSimTimes[i] = avgSimRoundTime;
			}
			
			return avgSimTimes; //This returns the long array back out of the network after the simulation has been called
		}
		else if (this.choosenAlg.equalsIgnoreCase("bellman ford")) //runs the simulations for the Bellman Ford algorithm
		{
//			for (int i = 0; i < numOfPairs; i++)
//			{
//				randomPair currentNodePair = nodePicker(this.networkGraph);
//				
//				inital = currentNodePair.getRandOne();
//				finish = currentNodePair.getRandTwo();
//				
//				for (int b = 0; b < numOfSimulations; b++)
//				{
//					
//					BellmanFord test = new BellmanFord(networkGraph, inital, finish);
//					totalSimRoundTime += test.getElapsedTime();
//				}
//				
//				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
//				avgSimTimes[i] = avgSimRoundTime;
//			}
//			
			return avgSimTimes;
		}
		else //This else conditional will return a console output to inform the user they choose an algorithm that is not included within the tests
		{
			System.out.println("Invalid Algorithm Choice");
			
			return avgSimTimes;
		}
		
	}
	
	//This method will create a randomPair object to store a pair of picked nodes within the graph. It will generate 2 random int values between 0 and the amount of nodes generated within the graph 
	//(Without a redundant choice) from the random constructor object initialized earlier in the program. 
	public randomPair nodePicker(graphGenerator graph) 
	{
		int graphSize = graph.getNumOfNodes(); //an int value for tracking the amount of nodes generated
		int node1 = r.nextInt(graphSize); //the initial node 1 int random value chosen
		int node2 = r.nextInt(graphSize); //the inital node 2 int random value chosen
		
		while(node1 == node2) //Checks for redundant random values (Cannot have two node choices being the same int value)
		{
			node2 = r.nextInt(graphSize); //if it is the same value it will constantly choose a new node2 value
		}
		
		//creates a randomPair object to store the newly generated int values
		randomPair newNodePair = new randomPair(node1, node2);
		
		return newNodePair; //returns the randomPair object
	}

	public String getChoosenAlg() //getter for the alg string
	{
		return choosenAlg;
	}

	public void setChoosenAlg(String choosenAlg) //setter for the alg string
	{
		this.choosenAlg = choosenAlg;
	}

	public int getNodeNum() //getter for the number of nodes value
	{
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) //setter for the number of nodes value
	{
		this.nodeNum = nodeNum;
	}

	public graphGenerator getNetworkGraph() //getter for the generated graph
	{
		return networkGraph;
	}

	public void setNetworkGraph(graphGenerator networkGraph) //setter for the generated graph
	{
		this.networkGraph = networkGraph;
	}
	
}
