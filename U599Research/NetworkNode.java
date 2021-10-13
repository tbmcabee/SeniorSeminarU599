package U599Research;

import java.util.*;

//This class is for acting as the node object within the graph, it stores the node's number reference, the node's array of references to its neighbors, its total distance value
//from initial node and the boolean value for recording if the node has been visited. 
public class NetworkNode 
{
	private int nodeNumber; //The int value of the node's reference within the generated graph
	private int arraySize; //The int value for recording the size of the node's array
	private int totalWeight; //The totalWeight int value for recording the current shortest distance from the initial node
	private boolean visited; //The boolean value for recording if the node has been visited or not
	
	ArrayList<ArrayNode> nodeArray = new ArrayList<>(graphGenerator.numberOfNodes); //This creates an array of ArrayNode objects for storing the references to the neighbor nodes
	
	//The constructor for the node object, it simply needs an int value for node reference within the graph and then it sets its current array size to 0 since no neighbors have been connected yet 
	public NetworkNode(int i)
	{
		nodeNumber = i;
		arraySize = 0;
	}
	
	//This method adds in an edge between the node and its neighbor. It will simply add the node to the node's arraylist and store the edge weight between itself and the neighbor. 
	public void addEdge(NetworkNode n, int w)
	{
		int edgeWeight = w; //This is the edge weight between the current node and its neighbor

		ArrayNode nodeObject = new ArrayNode(n, edgeWeight); //This will create an ArrayNode object for referencing the neighbor within the node's arrayList
		this.nodeArray.add(nodeObject); //This will add the ArrayNode object into the node's arrayList
		arraySize += 1;	//Increases the node's arrayList size 

	}
	
	//This method returns a boolean value. It is a debugging method for checking if a node is "connected" to a specific node reference. 
	public Boolean checkConnection(NetworkNode n)
	{
		Boolean alreadyAdded = false;
		
		for (int i = 0; i < arraySize; i++) //This for loop searches through the node's arrayList for the correct node reference. If the node is found, then the method returns a true value.
		{
			if (n == nodeArray.get(i).getStoredNode())
			{
				alreadyAdded = true;
			}
		}
		
		return alreadyAdded;
	}
	
	//This is a toString method for showing the current nodes within the NetworNode Object's arrayList. This specific toString method is the beginning of a recursive method.
	public String toString()
	{
		 return "Connections for Node " + nodeNumber + ": " + toString(0); //This begins return the start of the toString string value and then calls upon the next resursive iteration
	}
	
	public String toString(int index)
	{
		if (index == arraySize) //The recursive method's nase case, since if the index is equal to the arraySize, then the array has been fully searched
		{
			return " ";
		}
		else //This else statement will return the node int value for the neighbor node and the in value for the weight between the neighbor and the current node and then it calls upon the next iteration 
		{
			return "N: (" + nodeArray.get(index).getStoredNode().getNodeNumber()  + ") W: (" + nodeArray.get(index).getEdgeWeight() + ") " + toString(index+1);
		}
	}

	public ArrayList<ArrayNode> getNodeArray() //getter for the node's neighbor arrayList
	{
		return nodeArray;
	}

	public void setNodeArray(ArrayList<ArrayNode> nodeArray) //setter for the node's neighbor arrayList
	{
		this.nodeArray = nodeArray;
	}

	public int getNodeNumber() //getter for the node's int value 
	{
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) //setter for the node's int value 
	{
		this.nodeNumber = nodeNumber;
	}

	public int getArraySize() //getter for the node's arrayList size int value
	{
		return arraySize;
	}

	public void setArraySize(int arraySize) //setter for the node's arrayList size int value
	{
		this.arraySize = arraySize;
	}
	
	public int getTotalWeight() //getter for the node's total weight value
	{
		return this.totalWeight;
	}
	
	public void setTotalWeight(int totalWeight) //setter for the node's total weight value
	{
		this.totalWeight = totalWeight;
	}

	public boolean isVisited() //getter for the node's visited boolean value
	{
		return visited;
	}

	public void setVisited(boolean visited) //setter for the node's visited boolean value
	{
		this.visited = visited;
	}
	
	
	
}
