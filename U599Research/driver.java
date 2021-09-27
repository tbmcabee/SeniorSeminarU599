package U599Research;

public class driver
{
	public static void main(String [] args)
	{
		int n1 = 10;
		int n2 = 20;
		int n3 = 40;
		int n4 = 100;
		int n5 = 10000;
		
		graphCreator testGraph = new graphCreator(n5);
		
		for (int i = 0; i < n5; i++)
		{
			System.out.println(testGraph.grabNode(i).toString());
		}
	}
	
}
