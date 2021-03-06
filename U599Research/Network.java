package U599Research;

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter; 
import java.util.*;


//The network class acts as a transition of the testing implementation portion of the program's code. This class calls upon the data generation portion of the program to generate
//the tested graphs and to act as a way to record the computational data. This class does not run any specific simulation, rather it will start and stop simulations, record its data
//and then repeat until it has taken in sufficient data. Therefore, the main purpose of the Network class is to handle the program tests based upon the input it receives from the driver. 
public class Network
{	
	String choosenAlg; //A string value for choosing the specific algorithm to run within the simulation
	int nodeNum; //The amount of nodes to generate within the graph
	long[] avgSimTimes; //The long array that will be returned after the simulations have ran
	long[] extraavgSimTimes; 
	int[] simAcc;
	int[] extrasimAcc;
	long[] combinedArray;
	int[] combinedAcc;
	randomPair[] nodeChoices;
	
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
		int shortestDistance = 0;
		int inAccurateCounter = 0;
		int numOfPairs = 3; //This variable will store the amount of node pairs to be chosen per test
		int inital; //This simply initialized the initial int, this will store a node int value
		int finish; //This simply initialized the finish int, this will store a node int value
		
		//initializes the long array for recording the avg run time of each simulation round
		avgSimTimes = new long[numOfPairs]; 
		extraavgSimTimes = new long[numOfPairs]; 
		simAcc = new int[numOfPairs];
		extrasimAcc = new int[numOfPairs];
		nodeChoices = new randomPair[numOfPairs];
		
		if (this.choosenAlg.equalsIgnoreCase("dijkstra")) //runs the simulations for Dijkstra algorithm
		{
			for (int i = 0; i < numOfPairs; i++) //This for loop runs the amount of node pairs chosen times
			{
				//this creates a randomPair object to store the random nodes chosen for this specific pair
				randomPair currentNodePair = nodePicker(this.networkGraph);
				
				inital = currentNodePair.getRandOne();
				finish = currentNodePair.getRandTwo();
				
				totalSimRoundTime = 0;
				inAccurateCounter = 0;
				
				for (int b = 0; b < numOfSimulations; b++) //This for loop runs 1 simulation round's worth of simulations
				{
					//This will initalize a Dijkstra object for 1 simulation and run it based upon the generated graph, the initial node and the finish node
					Dijkstra test = new Dijkstra(networkGraph, inital, finish);
					//This will add the time ran for the specific simulation onto the running time total for the simulation round
					totalSimRoundTime += test.getElapsedTime(); 
					
					if ((b != 0) && (shortestDistance != test.getShortestDistance()))
					{
						inAccurateCounter += 1;
					}
					else
					{
						shortestDistance = test.getShortestDistance();
					}
				}
				
				//This ground of statements will divide out the avg time ran for the simulation round and then add it onto the long array
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				avgSimTimes[i] = avgSimRoundTime;
				simAcc[i] = inAccurateCounter;
				nodeChoices[i] = currentNodePair;
			}
			
			return avgSimTimes; //This returns the long array back out of the network after the simulation has been called
		}
		else if (this.choosenAlg.equalsIgnoreCase("bellman ford")) //runs the simulations for the Bellman Ford algorithm
		{
			for (int i = 0; i < numOfPairs; i++) //This for loop runs the amount of node pairs chosen times
			{
				//this creates a randomPair object to store the random nodes chosen for this specific pair
				randomPair currentNodePair = nodePicker(this.networkGraph);
				
				inital = currentNodePair.getRandOne();
				finish = currentNodePair.getRandTwo();
				
				totalSimRoundTime = 0;
				inAccurateCounter = 0;
				
				for (int b = 0; b < numOfSimulations; b++) //This for loop runs 1 simulation round's worth of simulations
				{
					//This will initalize a BellmanFord object for 1 simulation and run it based upon the generated graph, the initial node and the finish node
					BellmanFord test = new BellmanFord(networkGraph, inital, finish);
					//This will add the time ran for the specific simulation onto the running time total for the simulation round
					totalSimRoundTime += test.getElapsedTime(); 
					
					if ((b != 0) && (shortestDistance != test.getShortestDistance()))
					{
						inAccurateCounter += 1;
					}
					else
					{
						shortestDistance = test.getShortestDistance();
					}
				}
				
				//This ground of statements will divide out the avg time ran for the simulation round and then add it onto the long array
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				avgSimTimes[i] = avgSimRoundTime;
				simAcc[i] = inAccurateCounter;
				nodeChoices[i] = currentNodePair;
			}
			
			return avgSimTimes; //This returns the long array back out of the network after the simulation has been called
		}
		else if (this.choosenAlg.equalsIgnoreCase("both")) 
		{
			
			for (int i = 0; i < numOfPairs; i++) //This for loop runs the amount of node pairs chosen times
			{
				//this creates a randomPair object to store the random nodes chosen for this specific pair
				randomPair currentNodePair = nodePicker(this.networkGraph);
				
				inital = currentNodePair.getRandOne();
				finish = currentNodePair.getRandTwo();
				
				totalSimRoundTime = 0;
				inAccurateCounter = 0;
				
				for (int b = 0; b < numOfSimulations; b++) //This for loop runs 1 simulation round's worth of simulations
				{
					//This will initalize a Dijkstra object for 1 simulation and run it based upon the generated graph, the initial node and the finish node
					Dijkstra testDij = new Dijkstra(networkGraph, inital, finish);
					//This will add the time ran for the specific simulation onto the running time total for the simulation round
					totalSimRoundTime += testDij.getElapsedTime(); 
					
					if ((b != 0) && (shortestDistance != testDij.getShortestDistance()))
					{
						inAccurateCounter += 1;
					}
					else
					{
						shortestDistance = testDij.getShortestDistance();
					}
				}
				
				//This ground of statements will divide out the avg time ran for the simulation round and then add it onto the long array
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				avgSimTimes[i] = avgSimRoundTime;
				simAcc[i] = inAccurateCounter;
				
				totalSimRoundTime = 0;
				inAccurateCounter = 0;
				
				for (int b = 0; b < numOfSimulations; b++) //This for loop runs 1 simulation round's worth of simulations
				{
					//This will initalize a BellmanFord object for 1 simulation and run it based upon the generated graph, the initial node and the finish node
					BellmanFord testBell = new BellmanFord(networkGraph, inital, finish);
					//This will add the time ran for the specific simulation onto the running time total for the simulation round
					totalSimRoundTime += testBell.getElapsedTime(); 
					
					if ((b != 0) && (shortestDistance != testBell.getShortestDistance()))
					{
						inAccurateCounter += 1;
					}
					else
					{
						shortestDistance = testBell.getShortestDistance();
					}
				}
				
				//This ground of statements will divide out the avg time ran for the simulation round and then add it onto the long array
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				extraavgSimTimes[i] = avgSimRoundTime;
				extrasimAcc[i] = inAccurateCounter;
				nodeChoices[i] = currentNodePair;
			}
			
			combinedArray = new long[6];
			combinedAcc = new int[6];
			
			for (int i = 0; i < 3; i++)
			{
				combinedArray[i] = avgSimTimes[i];
			}
			
			for (int i = 0; i < 3; i++)
			{
				combinedArray[i+3] = extraavgSimTimes[i];
			}
			
			for (int i = 0; i < 3; i++)
			{
				combinedAcc[i] = simAcc[i];
			}
			
			for (int i = 0; i < 3; i++)
			{
				combinedAcc[i+3] = extrasimAcc[i];
			}
			
			return combinedArray; //This returns the long array back out of the network after the simulation has been called
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
	
	public void writeResults(String fileName, String AlgorithmChoice) throws IOException
	{
		try 
		{
			File results = new File(fileName + ".txt");
			
			if (results.createNewFile()) 
			{
				FileWriter writerOfFiles = new FileWriter(fileName + ".txt");
				
				fileWriter(writerOfFiles, AlgorithmChoice);
		    } 
			else 
			{
		        System.out.println("A results file already exists with that name");
		        System.out.println("Appending file now...");
		        
		        FileWriter writerOfFiles = new FileWriter(fileName + ".txt", true);
		        
		        fileWriter(writerOfFiles, AlgorithmChoice);
		    }
			
		} 
		catch (IOException exp)
		{
			System.out.println("An error has been caught!");
		    exp.printStackTrace();
		}
		
	}
	
	public void fileWriter(FileWriter writerOfFiles, String AlgorithmChoice) throws IOException
	{
		try
        {
			//This statement calls upon the simulationRun method from the network class that will run the specifically decided algorithm through the generated test graph, and then send that data from 
			//the test into a initialized array of long values. 
			long[] simRuns = simulationRun();
			int[] accRuns = getCombinedAcc();
			randomPair[] nodes = getNodeChoices();
			
			//The following group of statements simply outputs the simulated data into an easily readable format. This method will be utilized for reading computed data
			//until the txt file recording method is implemented into the program.
			
			if (AlgorithmChoice.equalsIgnoreCase("dijkstra"))
			{
				writerOfFiles.write("\n");
				writerOfFiles.write("The average computational time (ns) of the 3 tested pairs within the Dijkstra generated graph\n");
				
				for (int i = 0; i < 3; i++)
				{
					writerOfFiles.write("\n");
					writerOfFiles.write("Tested Pair #" + (i+1) + "\n");
					writerOfFiles.write("Selected Node Pair: " + nodes[i].toString() + "\n");
					writerOfFiles.write("Algorithm Ran: " + AlgorithmChoice + "\n");
					writerOfFiles.write("Average Time Elapsed: " + simRuns[i] + "\n");
					writerOfFiles.write("Accuracy Ratio: " + (((50-accRuns[i])/50)*100) + "%\n");
				}
				
				writerOfFiles.write("\n");
				writerOfFiles.write(networkGraph.printNodeConnections(networkGraph.getGraphList(), nodeNum));
				
	        	writerOfFiles.close();
	        	
	        	System.out.println("File write successful!");
			}
			else if (AlgorithmChoice.equalsIgnoreCase("bellman"))
			{
				writerOfFiles.write("\n");
				writerOfFiles.write("The average computational time (ns) of the 3 tested pairs within the Bellman Ford generated graph\n");
				
				for (int i = 0; i < 3; i++)
				{
					writerOfFiles.write("\n");
					writerOfFiles.write("Tested Pair #" + (i+1) + "\n");
					writerOfFiles.write("Selected Node Pair: " + nodes[i].toString() + "\n");
					writerOfFiles.write("Algorithm Ran: " + AlgorithmChoice + "\n");
					writerOfFiles.write("Avaerage Time Elapsed: " + simRuns[i+3] + "\n");
					writerOfFiles.write("Accuracy Ratio:" + (((50-accRuns[i+3])/50)*100) + "% \n");
				}
				
				writerOfFiles.write("\n");
				writerOfFiles.write(networkGraph.printNodeConnections(networkGraph.getGraphList(), nodeNum));
				
	        	writerOfFiles.close();
	        	
	        	System.out.println("File write successful!");
			}
			else
			{
				writerOfFiles.write("\n");
				writerOfFiles.write("The average computational time (ns) of the 3 tested pairs within the Dijkstra generated graph\n");
				
				for (int i = 0; i < 3; i++)
				{
					writerOfFiles.write("\n");
					writerOfFiles.write("Tested Pair #" + (i+1) + "\n");
					writerOfFiles.write("Selected Node Pair: " + nodes[i].toString() + "\n");
					writerOfFiles.write("Algorithm Ran: Dijkstra\n");
					writerOfFiles.write("Average Time Elapsed: " + simRuns[i] + "\n");
					writerOfFiles.write("Accuracy Ratio: " + (((50-accRuns[i])/50)*100) + "%\n");
				}
				
				writerOfFiles.write("\n");
				writerOfFiles.write("The average computational time (ns) of the 3 tested pairs within the Bellman Ford generated graph\n");
				
				for (int i = 0; i < 3; i++)
				{
					writerOfFiles.write("\n");
					writerOfFiles.write("Tested Pair #" + (i+1) + "\n");
					writerOfFiles.write("Selected Node Pair: " + nodes[i].toString() + "\n");
					writerOfFiles.write("Algorithm Ran: Bellman Ford\n");
					writerOfFiles.write("Avaerage Time Elapsed: " + simRuns[i+3] + "\n");
					writerOfFiles.write("Accuracy Ratio:" + (((50-accRuns[i+3])/50)*100) + "% \n");
				}
				
				writerOfFiles.write("\n");
				writerOfFiles.write(networkGraph.printNodeConnections(networkGraph.getGraphList(), nodeNum));
				
	        	writerOfFiles.close();
	        	
	        	System.out.println("File write successful!");
			}
        }
        catch (IOException exp)
        {
        	System.out.println("An error has occured while trying to write the file!");
        	exp.printStackTrace();
        }
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
	
	public long[] getAvgSimTimes() 
	{
		return avgSimTimes;
	}

	public void setAvgSimTimes(long[] avgSimTimes) 
	{
		this.avgSimTimes = avgSimTimes;
	}

	public long[] getExtraavgSimTimes() {
		return extraavgSimTimes;
	}

	public void setExtraavgSimTimes(long[] extraavgSimTimes) 
	{
		this.extraavgSimTimes = extraavgSimTimes;
	}

	public int[] getSimAcc() 
	{
		return simAcc;
	}

	public void setSimAcc(int[] simAcc) 
	{
		this.simAcc = simAcc;
	}

	public int[] getExtrasimAcc() 
	{
		return extrasimAcc;
	}

	public void setExtrasimAcc(int[] extrasimAcc) 
	{
		this.extrasimAcc = extrasimAcc;
	}

	public Random getR() 
	{
		return r;
	}

	public void setR(Random r) 
	{
		this.r = r;
	}
	
	public long[] getCombinedArray() 
	{
		return combinedArray;
	}

	public void setCombinedArray(long[] combinedArray) 
	{
		this.combinedArray = combinedArray;
	}

	public int[] getCombinedAcc() 
	{
		return combinedAcc;
	}

	public void setCombinedAcc(int[] combinedAcc) 
	{
		this.combinedAcc = combinedAcc;
	}
	
	public randomPair[] getNodeChoices()
	{
		return nodeChoices;
	}
}
