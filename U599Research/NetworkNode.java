package U599Research;

import java.util.*;

public class NetworkNode 
{
	private ArrayList node;
	private int nodeNumber;
	private int arraySize;
	
	public NetworkNode(int i)
	{
		node = new ArrayList<>();
		nodeNumber = i;
		arraySize = 0;
	}
	
	public Boolean addEdge(NetworkNode n)
	{
		boolean alreadyAdded = false;
		for (int i = 0; i < arraySize; i++)
		{
			if (n == node.get(i))
			{
				alreadyAdded = true;
			}
		}
		
		if (alreadyAdded)
		{
			System.out.println("Connection already exists");
		}
		else
		{
			this.node.add(n);
			arraySize += 1;
		}
		
		return alreadyAdded;
	}

	public ArrayList getNode() 
	{
		return node;
	}

	public void setNode(ArrayList node) 
	{
		this.node = node;
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
