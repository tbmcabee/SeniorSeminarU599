package U599Research;

public class BellmanFord 
{
	long startTime; //Creates a long data value for the simulation run start time
	long endTime; //Creates a long data value for the simulation run end time
	long elapsedTime; //Creates a long data value for the simulation run total elapsed time (start time - end time)
	boolean hasSetChanged = false;
	int shortestDistance;
	
	//The program constructor that will begin the simulation run and record the running times for a single simulation
	public BellmanFord(graphGenerator graph, int node1, int node2)
	{
		NetworkNode initalNode = graph.grabNode(node1); //This will initialize a node reference to the node1 int value on the graph (The initial node in the node pair)
		NetworkNode finalNode = graph.grabNode(node2); //This will initialize a node reference to the node2 int value on the graph (The destination node in the node pair)
		
		
		int numberOfNodes = graphGenerator.numberOfNodes; //creates an int value to store the numberOfNodes generated (A local reference within the class)
		int[] spSet = new int[numberOfNodes]; //creates a int array for storing the running list of distances within the simulation
		int numberOfIter = numberOfNodes - 1;
		
		for (int i = 0; i < numberOfNodes; i++) //This for loop sets the inital distances for the entire spSet array. -1 represents an infinite value. 
		{
			if (i == node1)
			{ 
				initalNode.setTotalWeight(0); //Sets the initial node to 0
				spSet[i] = 0;
			}
			else
			{
				graph.grabNode(i).setTotalWeight(-1);; //Sets the other nodes to "infinity", in this program -1 weight is equal to infinity
				spSet[i] = -1;
			}
		}
		
		startTime = System.nanoTime(); //This will record the start time (In ns)
		algorithmRun(graph, initalNode, spSet, numberOfIter); //Runs the simulation to completion
		endTime = System.nanoTime(); //This will record the end time (In ns)
		
		shortestDistance = finalNode.getTotalWeight();
		
		System.out.println("The total shortest distance from Node " + node1 + " to Node " + node2 + " is " + shortestDistance); //A string to output the final total distance for the shortest path
		elapsedTime = endTime - startTime; //Calculates the elapsed time
		
		for (int b = 0; b <= numberOfIter; b ++) //This for loop resets the visited value within the NetworkNode variables so that the nodes can be revisited when the simulation restarts
		{
			graph.grabNode(b).setVisited(false);
		}
	}
	
	//This method checks for the minimum distance between a visited node and its direct neighbors.
	//This method will return the int value for the next node to visit, either because it is the next unvisited node to check or 
	//because it has the lowest distance between itself and the current node compared to the other neighbors.
	public int checkMinDistance(graphGenerator graph, NetworkNode node, int[] spSet)
	{
		int arraySize = node.getArraySize(); //This will get the size of the visited node's neighbor array
		int minWeight = 101; //Max possible weight for a connection is 100
		int currentWeightCheck; //Initializes the current weight value as an int
		int currentNodeCheck; //Initializes the current int value for which neighbor node is being checked
		int minNodeNumber = -1; //Sets the minimum node int value to -1
		
		for (int i =  0; i < arraySize; i++) //This for loop will update each of the neighbors values to its new shortest distance, set its inital distance and checks if the weight is less then the current minimum
		{
			currentWeightCheck = node.getNodeArray().get(i).getEdgeWeight(); //Grabs the current neighbor's edge weight between the neighbor and the current visited node
			currentNodeCheck = node.getNodeArray().get(i).getStoredNode().getNodeNumber(); //Grabs the current neighbor's int value for referencing it within the graph
			
			//Checks if the connection has yet to be defined
			if (spSet[currentNodeCheck] == -1)
			{
				spSet[currentNodeCheck] = spSet[node.getNodeNumber()] + currentWeightCheck;
				
				node.getNodeArray().get(i).getStoredNode().setTotalWeight(spSet[currentNodeCheck]);
				
				hasSetChanged = true;
			}
			
			//Checks if the current connection weighs less then the original connection
			if(spSet[currentNodeCheck] > spSet[node.getNodeNumber()] + currentWeightCheck)
			{
				spSet[currentNodeCheck] = spSet[node.getNodeNumber()] + currentWeightCheck;
				
				node.getNodeArray().get(i).getStoredNode().setTotalWeight(spSet[currentNodeCheck]);
				
				hasSetChanged = true;
			}
			
			//Checks if the current Weight connection is less then the current min connection Weight
			if(currentWeightCheck <= minWeight)
			{
				if (!(node.getNodeArray().get(i).getStoredNode().isVisited())) //Checks if the node has been visited yet
				{
					minNodeNumber = currentNodeCheck;
					minWeight = currentWeightCheck;
				}
			}
			
		}
		
		//If minNodeNumber == -1, then there were no available connections that were not already added to the spSet
		//Now the program will look for any unvisited neighbors to begin checking connections again. If there are no more unvisited nodes
		//then it will still return a -1 value. 
		if (minNodeNumber == -1)
		{
			for (int i = 0; i < graph.getNumOfNodes(); i++)
			{
				NetworkNode currentNode = graph.grabNode(i);
				
				if (!(currentNode.isVisited()))
				{
					minNodeNumber = currentNode.getNodeNumber();
				}
			}
		}
		
		return minNodeNumber;
	}
	
	//This method will start the algorithm's run and then record the total shortest distances from the initial node to the other nodes in the graph. It takes in the generated graph object,
	//the initial node object and the spSet array for recording distances. It does not return a value. 
	public void algorithmRun(graphGenerator graph, NetworkNode inital, int[] spSet, int numberOfIter)
	{	
		
		for (int i = 0; i < numberOfIter; i++)
		{
			int nextNodeNumber = checkMinDistance(graph, inital, spSet); //Checks for the minimum distance between the initial node and its neighbors. Returns the neighbor with the shortest distance. 
			
			if (nextNodeNumber == -1) //The base case for the recursive function. If it is -1 from the start, it means the initial node is disconnected from the graph. 
			{
				System.out.println("The inital node is disconnected");
			}
			else //If the value is not -1, then it will begin the recursive run. 
			{
				inital.setVisited(true); //Sets the initial node to visited
				
				NetworkNode nextNode = graph.grabNode(nextNodeNumber); //Creates a reference to the next node to visit
				
				System.out.println("The node path is: Node: " + inital.getNodeNumber() + " " + algorithmRun(graph, nextNode, spSet, " ")); //Runs the recursive function to the next iteration for the next node. This
				//recursive run will return a string for each iteration to show the path that the algorithm took. MAINLY FOR BUG TESTING
			}
			
			if (!hasSetChanged) //If the set has not changed for the iteration, then the loop will break
			{
				System.out.println("Finished on Iteration: " + i);
				break;
			}
			
			//Used numberOfIter since it is just numberOfNodes - 1, so <= numberOfIter will be the same as < numberOfNodes
			for (int b = 0; b <= numberOfIter; b ++) //This for loop resets the visited value within the NetworkNode variables so that the nodes can be revisited when the simulation restarts
			{
				graph.grabNode(b).setVisited(false);
			}
			
			hasSetChanged = false; //Resets the boolean for checking if the iteration set has changed
		}
		
	}
	
	//The recursive portion of the simulation run. It takes in the generated graph object, the currently visited node object, the spSet array for distances and the returning string value. It returns a String value.  
	public String algorithmRun(graphGenerator graph, NetworkNode currentNode, int[] spSet, String toString)
	{
		int nextNodeNumber = checkMinDistance(graph, currentNode, spSet); //Checks for the minimum distance between the initial node and its neighbors. Returns the neighbor with the shortest distance. 
		
		if (nextNodeNumber == -1) //The base case for the recursive function. If this value is -1 then that means there are no more unvisited nodes within the graph. 
		{
			return toString + "No more connections at " + currentNode.getNodeNumber() + "!";
		}
		else
		{	
			currentNode.setVisited(true); //Sets the current node to visited
			
			NetworkNode nextNode = graph.grabNode(nextNodeNumber); //Creates a reference to the next node to visit
			
			return toString + "Node:  " + currentNode.getNodeNumber() + " " + algorithmRun(graph, nextNode, spSet, " "); //Runs the recursive function to the next iteration for the next node.
		}
		
	}
	
	public long getStartTime() //getter for the start time
	{
		return startTime;
	}

	public void setStartTime(long startTime) //setter for the start time
	{
		this.startTime = startTime;
	}

	public long getEndTime() //getter for the end time
	{
		return endTime;
	}

	public void setEndTime(long endTime) //setter for the end time
	{
		this.endTime = endTime;
	}

	public long getElapsedTime() //getter for the elapsed time
	{
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) //setter for the elapsed time
	{
		this.elapsedTime = elapsedTime;
	}
	
	public int getShortestDistance() 
	{
		return shortestDistance;
	}

	public void setShortestDistance(int shortestDistance) 
	{
		this.shortestDistance = shortestDistance;
	}
	
}
