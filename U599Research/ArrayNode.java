package U599Research;

public class ArrayNode 
{
	private NetworkNode storedNode; //Maybe we can store just the nodeNumber instead? When test cases grow this could be a performance issue!
	private int edgeWeight;
	
	public ArrayNode(NetworkNode s, int w)
	{
		this.storedNode = s;
		this.edgeWeight = w;
	}

	public NetworkNode getStoredNode() 
	{
		return storedNode;
	}

	public void setStoredNode(NetworkNode storedNode) 
	{
		this.storedNode = storedNode;
	}

	public int getEdgeWeight() 
	{
		return edgeWeight;
	}

	public void setEdgeWeight(int edgeWeight) 
	{
		this.edgeWeight = edgeWeight;
	}
	
	

}
