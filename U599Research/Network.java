package U599Research;

import java.util.*;


public class Network
{	
	String choosenAlg;
	int nodeNum;
	long[] avgSimTimes;
	
	graphGenerator networkGraph;
	
	Random r = new Random();
	
	public Network(String a, int num)
	{
		this.choosenAlg = a;
		this.nodeNum = num;
		this.networkGraph = new graphGenerator(nodeNum);
	}
	
	public long[] simulationRun(Network network)
	{
		long avgSimRoundTime = 0;
		long totalSimRoundTime = 0;
		int numOfSimulations = 50;
		int numOfPairs = 3;
		int inital;
		int finish;
		
		
		avgSimTimes = new long[numOfPairs];
		
		if (this.choosenAlg.equalsIgnoreCase("dijkstra"))
		{
			for (int i = 0; i < numOfPairs; i++)
			{
				randomPair currentNodePair = nodePicker(this.networkGraph);
				
				inital = currentNodePair.getRandOne();
				finish = currentNodePair.getRandTwo();
				
				for (int b = 0; b < numOfSimulations; b++)
				{
					
					Dijkstra test = new Dijkstra(networkGraph, inital, finish);
					totalSimRoundTime += test.getElapsedTime();
				}
				
				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
				avgSimTimes[i] = avgSimRoundTime;
			}
			
			return avgSimTimes;
		}
		else if (this.choosenAlg.equalsIgnoreCase("bellman ford"))
		{
//			for (int i = 0; i < numOfPairs; i++)
//			{
//				randomPair currentNodePair = nodePicker(this.networkGraph);
//				
//				inital = currentNodePair.getRandOne();
//				finish = currentNodePair.getRandTwo();
//				
//				for (int b = 0; b < numOfSimulations; b++)
//				{
//					
//					BellmanFord test = new BellmanFord(networkGraph, inital, finish);
//					totalSimRoundTime += test.getElapsedTime();
//				}
//				
//				avgSimRoundTime = totalSimRoundTime/numOfSimulations;
//				avgSimTimes[i] = avgSimRoundTime;
//			}
//			
			return avgSimTimes;
		}
		else
		{
			System.out.println("Invalid Algorithm Choice");
			
			return avgSimTimes;
		}
		
	}
	
	public randomPair nodePicker(graphGenerator graph) 
	{
		int graphSize = graph.getNumOfNodes();
		int node1 = r.nextInt(graphSize);
		int node2 = r.nextInt(graphSize);
		
		while(node1 == node2)
		{
			node2 = r.nextInt(graphSize);
		}
		
		randomPair newNodePair = new randomPair(node1, node2);
		
		return newNodePair;
		//picks the two specific nodes that will be used in the SPP calculation 
	}

	public String getChoosenAlg() 
	{
		return choosenAlg;
	}

	public void setChoosenAlg(String choosenAlg) 
	{
		this.choosenAlg = choosenAlg;
	}

	public int getNodeNum() 
	{
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) 
	{
		this.nodeNum = nodeNum;
	}

	public graphGenerator getNetworkGraph() 
	{
		return networkGraph;
	}

	public void setNetworkGraph(graphGenerator networkGraph) 
	{
		this.networkGraph = networkGraph;
	}
	
}
