package U599Research;

public class Dijkstra 
{
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
				initalNode.setTotalWeight(0); //Sets the inital node to 0
			}
			else
			{
				graph.grabNode(i).setTotalWeight(-1);; //Sets the other nodes to "infinity", in this program -1 weight is equal to infinity
			}
		}
		
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
			
			//Need to add a check if there are no available minimum distances, 
			//basically if all the nodes have been accounted for but we still haven't reached the desired node
			//we need to backtrack to the next node with available nodes to explore
		}
		
		return minNodeNumber;
	}
	
	//use recursion to enter the nodes and constantly check for new nodes
	
	public void algorithmRun(NetworkNode inital, int[] spSet, int spSetSize)
	{
		int currentSpPosition = 0;
		int nextNodeNumber = checkMinDistance(inital, spSet, spSetSize);
		NetworkNode nextNode = inital.getNodeArray().get(nextNodeNumber).getStoredNode();
		
		spSet[currentSpPosition] = inital.getNodeNumber();
		
		nextNode.setTotalWeight(nextNode.getNodeArray().get(inital.getNodeNumber()).getEdgeWeight());
		
		algorithmRun(nextNode, spSet, spSetSize, currentSpPosition+1);
		
	}
	
	public void algorithmRun(NetworkNode currentNode, int[] spSet, int spSetSize, int currentspPosition)
	{
		
	}
	
}
