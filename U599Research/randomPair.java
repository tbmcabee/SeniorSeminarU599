package U599Research;

//This class will be utilized to store the two int values so that the methods can return two int values at once rather then having two methods returning int values  
public class randomPair 
{
	int randOne;
	int randTwo;
	
	//RandomPair object constructor
	public randomPair(int r1, int r2)
	{
		randOne = r1;
		randTwo = r2;
	}
	
	public String toString()
	{
		return "Node " + randOne + " to Node " + randTwo;
	}

	public int getRandOne() //getter for the first random object value
	{
		return randOne;
	}

	public void setRandOne(int randOne) //setter for the first random object value
	{
		this.randOne = randOne;
	}

	public int getRandTwo() //getter for the second random object value
	{
		return randTwo;
	}

	public void setRandTwo(int randTwo) //setter for the second random object value
	{
		this.randTwo = randTwo;
	}

}
