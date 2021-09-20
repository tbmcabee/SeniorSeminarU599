package U599Research;

public interface NetworkInterface 
{
	public void graphGenerator();
	
	public Object graphToMatrix(Object graph);
	
	public Object matrixToGraph(Object matrix);
	
	public Object nodePicker(Object graph);
	
	public double recordTime();
	
	public void saveMatrix(Object matrix);

}
