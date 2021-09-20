package U599Research;

public class Network implements NetworkInterface
{	
	public Network(graphCreator g, Object a, int num)
	{
		//constructor takes in the created graph and the algorithm and then simulates it "num" times
		//Will use nodePicker, recordTime, etc
	}
	
	public Object nodePicker(Object graph) 
	{
		return graph;
		//picks the two specific nodes that will be used in the SPP calculation 
	}
	
	
	public double recordTime()
	{
		return 0;
		//method records the time per simulation, may need to have seperate class
	}
	
	
	public void saveMatrix(Object matrix)
	{
		//saves the generated graph to a txt file, will need to also have a readMatrix method
	}
	
	
	public Object readMatrix(String fileName)
	{
		return fileName;
		//reads the saved graph from a txt file and reloads the graph for testing
	}

	@Override
	public void graphGenerator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object graphToMatrix(Object graph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object matrixToGraph(Object matrix) {
		// TODO Auto-generated method stub
		return null;
	}

}
