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
			}
			else
			{
				graph.grabNode(i).setTotalWeight(-1);; //Sets the other nodes to "infinity", in this program -1 weight is equal to infinity
			}
		}
		
		startTime = System.nanoTime();
		algorithmRun(graph, initalNode, finalNode, spSet, spSetSize);
		endTime = System.nanoTime();
		
		elapsedTime = endTime - startTime;
		
		//For now results are printed out for total distance, however I need to still add a method for recording results!
		
	}
	
	public int checkMinDistance(NetworkNode node, int[] spSet, int spSetSize)
	{
		int arraySize = node.getArraySize();
		int minWeight = 101; //Max possible weight for a connection is 100
		int currentWeightCheck;
		int currentNodeCheck;
		int minNodeNumber = -1;
		boolean alreadyAdded = false;
		
		for (int i =  0; i < arraySize; i++)
		{
			currentWeightCheck = node.getNodeArray().get(i).getEdgeWeight();
			currentNodeCheck = node.getNodeArray().get(i).getStoredNode().getNodeNumber();
			
			if(currentWeightCheck <= minWeight)
			{
				for (int b = 0; b < spSetSize; b++)
				{
					if (currentNodeCheck == spSet[b])
					{
						alreadyAdded = true;
					}
				}
				if (!alreadyAdded)
				{
					minNodeNumber = currentNodeCheck;
					minWeight = currentWeightCheck;
				}
			}
			
		}
		
		//If minNodeNumber == -1, then there were no available connections that were not already added to the spSet
		if (minNodeNumber == -1)
		{
			System.out.println("No avaiable connections!");
		}
		
		return minNodeNumber;
	}
	
	//use recursion to enter the nodes and constantly check for new nodes
	
	public void algorithmRun(graphGenerator graph, NetworkNode inital, NetworkNode destination, int[] spSet, int spSetSize)
	{
		int currentSpPosition = 0;
		int nextNodeNumber = checkMinDistance(inital, spSet, spSetSize);
		
		if (nextNodeNumber == -1)
		{
			System.out.println("Distance: " + inital.getTotalWeight());
		}
		else
		{
			NetworkNode nextNode = graph.grabNode(nextNodeNumber);
			
			spSet[currentSpPosition] = inital.getNodeNumber();
			
			nextNode.setTotalWeight(nextNode.getNodeArray().get(inital.getNodeNumber()).getEdgeWeight());
			
			algorithmRun(graph, nextNode, destination, spSet, spSetSize, currentSpPosition+1);
		}
		
	}
	
	public void algorithmRun(graphGenerator graph, NetworkNode currentNode, NetworkNode destination, int[] spSet, int spSetSize, int currentspPosition)
	{
		int nextNodeNumber = checkMinDistance(currentNode, spSet, spSetSize);
		NetworkNode nextNode = graph.grabNode(nextNodeNumber);
		
		spSet[currentspPosition] = currentNode.getNodeNumber();
		
		if (nextNodeNumber == -1)
		{
			System.out.println("Total Distance until stop: " + currentNode.getTotalWeight());
		}
		else
		{
			if (spSet[currentspPosition] == destination.getNodeNumber())
			{
				System.out.println("Distance to Destination: " + currentNode.getTotalWeight());
			}
			else
			{
				nextNode.setTotalWeight(nextNode.getNodeArray().get(currentNode.getNodeNumber()).getEdgeWeight());
				
				algorithmRun(graph, nextNode, destination, spSet, spSetSize, currentspPosition+1);
			}
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
