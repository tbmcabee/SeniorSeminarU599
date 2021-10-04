package U599Research;

import java.util.*;

public class NetworkNode 
{
	private int nodeNumber;
	private int arraySize;
	private int totalWeight;
	ArrayList<ArrayNode> nodeArray = new ArrayList<>(graphGenerator.numberOfNodes); //Watch to see if it works correctly or not
	
	public NetworkNode(int i)
	{
		nodeNumber = i;
		arraySize = 0;
	}
	
	public void addEdge(NetworkNode n, int w)
	{
		int edgeWeight = w;

		ArrayNode nodeObject = new ArrayNode(n, edgeWeight);
		this.nodeArray.add(nodeObject);
		arraySize += 1;	

	}
	
	public Boolean checkConnection(NetworkNode n)
	{
		Boolean alreadyAdded = false;
		
		for (int i = 0; i < arraySize; i++)
		{
			if (n == nodeArray.get(i).getStoredNode())
			{
				alreadyAdded = true;
			}
		}
		
		return alreadyAdded;
	}
	
	public String toString()
	{
		 return "Connections for Node " + nodeNumber + ": " + toString(0);
	}
	
	public String toString(int index)
	{
		if (index == arraySize)
		{
			return " ";
		}
		else
		{
			return "N: (" + nodeArray.get(index).getStoredNode().getNodeNumber()  + ") W: (" + nodeArray.get(index).getEdgeWeight() + ") " + toString(index+1);
		}
	}

	public ArrayList<ArrayNode> getNodeArray() 
	{
		return nodeArray;
	}

	public void setNodeArray(ArrayList<ArrayNode> nodeArray) 
	{
		this.nodeArray = nodeArray;
	}

	public int getNodeNumber() 
	{
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) 
	{
		this.nodeNumber = nodeNumber;
	}

	public int getArraySize() 
	{
		return arraySize;
	}

	public void setArraySize(int arraySize) 
	{
		this.arraySize = arraySize;
	}
	
	public int getTotalWeight()
	{
		return this.totalWeight;
	}
	
	public void setTotalWeight(int totalWeight)
	{
		this.totalWeight = totalWeight;
	}
	
	
}
