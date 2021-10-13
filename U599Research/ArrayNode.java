package U599Research;

//This is a class for creating an ArrayNode object. Its purpose is to store node references within the node's neighbor ArrayList. The object simply stores the weight of the neighbor's connection
//to the node and the reference to the neighbor's node object. 
public class ArrayNode 
{
	private NetworkNode storedNode; 
	private int edgeWeight;

	//Constructor for creating the ArrayNode object, it will store the weight between the node and neighbor's connection within the edgeWeight int value and store a reference to the neighbor node 
	//within the storedNode variable.
	public ArrayNode(NetworkNode s, int w)
	{
		this.storedNode = s;
		this.edgeWeight = w;
	}

	public NetworkNode getStoredNode() //getter for the storedNode reference
	{
		return storedNode;
	}

	public void setStoredNode(NetworkNode storedNode) //setter for the storedNode reference
	{
		this.storedNode = storedNode;
	}

	public int getEdgeWeight() //getter for the edge weight value
	{
		return edgeWeight;
	}

	public void setEdgeWeight(int edgeWeight) //setter for the edge weight value
	{
		this.edgeWeight = edgeWeight;
	}
	
	

}
