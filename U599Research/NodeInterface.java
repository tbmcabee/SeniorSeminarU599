package NetworkTesting;

public interface NetworkNode 
{
	public void updateRoutingTable(Object graph);
	
	public Object getRoutingTable();
	
	public void setRoutingTable();
	
	public void setNodeName();
	
	public String getNodeName();

}
