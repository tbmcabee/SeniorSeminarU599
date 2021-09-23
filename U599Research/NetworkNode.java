package U599Research;

import java.util.*;

public class NetworkNode 
{
	private int nodeNumber;
	private int arraySize;
	ArrayList<ArrayNode> nodeArray = new ArrayList<>(graphCreator.numberOfNodes); //Watch to see if it works correctly or not
	
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
				System.out.println("Connection already exists");
			}
		}
		
		return alreadyAdded;
	}

	public ArrayList getNodeArray() 
	{
		return nodeArray;
	}

	public void setNodeArray(ArrayList node) 
	{
		this.nodeArray = node;
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
	
	
}
