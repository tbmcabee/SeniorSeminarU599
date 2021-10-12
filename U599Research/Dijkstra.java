package U599Research;

public class Dijkstra 
{
	long startTime;
	long endTime;
	long elapsedTime;
	
	
	public Dijkstra(graphGenerator graph, int node1, int node2)
	{
		NetworkNode initalNode = graph.grabNode(node1);
		NetworkNode finalNode = graph.grabNode(node2);
		
		int numberOfNodes = graphGenerator.numberOfNodes;
		int spSetSize = 0;
		
		int[] spSet = new int[numberOfNodes]; //int values will be the node number
		
		//Check the initalNode's arrayList connections until we reach the final Node and update values within the spSet as a new path is found
		
		for (int i = 0; i < numberOfNodes; i++)
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
		
		startTime = System.nanoTime();
		algorithmRun(graph, initalNode, finalNode, spSet);
		endTime = System.nanoTime();
		
		System.out.println("The total shortest distance from Node " + node1 + " to Node " + node2 + " is " + finalNode.getTotalWeight());
		elapsedTime = endTime - startTime;
		
		for (int i = 0; i < numberOfNodes; i ++)
		{
			graph.grabNode(i).setVisited(false);
		}
		
		//For now results are printed out for total distance, however I need to still add a method for recording results!
		
	}
	
	public int checkMinDistance(graphGenerator graph, NetworkNode node, int[] spSet)
	{
		int arraySize = node.getArraySize();
		int minWeight = 101; //Max possible weight for a connection is 100
		int currentWeightCheck;
		int currentNodeCheck;
		int minNodeNumber = -1;
		
		for (int i =  0; i < arraySize; i++)
		{
			currentWeightCheck = node.getNodeArray().get(i).getEdgeWeight();
			currentNodeCheck = node.getNodeArray().get(i).getStoredNode().getNodeNumber();
			
			//Checks if the connection has yet to be defined
			if (spSet[currentNodeCheck] == -1)
			{
				spSet[currentNodeCheck] = spSet[node.getNodeNumber()] + currentWeightCheck;
				
				node.getNodeArray().get(i).getStoredNode().setTotalWeight(spSet[currentNodeCheck]);
			}
			
			//Checks if the current connection weighs less then the original connection
			if(spSet[currentNodeCheck] > spSet[node.getNodeNumber()] + currentWeightCheck)
			{
				spSet[currentNodeCheck] = spSet[node.getNodeNumber()] + currentWeightCheck;
				
				node.getNodeArray().get(i).getStoredNode().setTotalWeight(spSet[currentNodeCheck]);
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
	
	//use recursion to enter the nodes and constantly check for new nodes
	
	public void algorithmRun(graphGenerator graph, NetworkNode inital, NetworkNode destination, int[] spSet)
	{	
		int nextNodeNumber = checkMinDistance(graph, inital, spSet);
		
		if (nextNodeNumber == -1)
		{
			System.out.println("The inital node is disconnected");
		}
		else
		{
			inital.setVisited(true);
			
			NetworkNode nextNode = graph.grabNode(nextNodeNumber);
			
			System.out.println("The node path is: Node: " + inital.getNodeNumber() + " " + algorithmRun(graph, nextNode, destination, spSet, " "));
		}
		
	}
	
	public String algorithmRun(graphGenerator graph, NetworkNode currentNode, NetworkNode destination, int[] spSet, String toString)
	{
		int nextNodeNumber = checkMinDistance(graph, currentNode, spSet);
		
		if (nextNodeNumber == -1)
		{
			return toString + "No more connections at " + currentNode.getNodeNumber() + "!";
		}
		else
		{	
			currentNode.setVisited(true);
			
			NetworkNode nextNode = graph.grabNode(nextNodeNumber);
			
			return toString + "Node:  " + currentNode.getNodeNumber() + " " + algorithmRun(graph, nextNode, destination, spSet, " ");
		}
		
	}
	
	public long getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(long startTime) 
	{
		this.startTime = startTime;
	}

	public long getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(long endTime) 
	{
		this.endTime = endTime;
	}

	public long getElapsedTime() 
	{
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) 
	{
		this.elapsedTime = elapsedTime;
	}
	
}
