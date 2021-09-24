package U599Research;

public class driver
{
	public static void main(String [] args)
	{
		graphCreator testGraph = new graphCreator(10);
		
		for (int i = 0; i < 10; i++)
		{
			System.out.println(testGraph.grabNode(i).toString());
		}
	}
	
}
