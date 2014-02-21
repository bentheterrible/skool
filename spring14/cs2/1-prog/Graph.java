/*
 * Name: Ben Feistmann
 * Course Number: COP 3502
 * Section Number: 0001
 * Title: Graph.java (Assignment 1)
 * Date: 1/28/14
 * 
 */

import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Graph {

    // DFS function
	private int[] DFS(int vertex, int numNodes, int[][] adjMatrix) {
			
		int[] visited = new int[numNodes];
		int[] result = new int[numNodes];
		int[] stack = new int[numNodes];
		int i,			// index for main loop 
			j = 0,		// index for stack
		   	k = 0;		// index for visited nodes
		
		// Initialize every node to unvisited for start of search.
		for (i = 0; i < numNodes; i++)	
			visited[i] = 0;

		// Push the intial value (vertex) on to the stack.
		stack[j] = vertex;		

		// Mark as visited.
		visited[vertex] = 1;	
		result[k] = vertex;

		for (i = 0; i < numNodes; i++)	
		{
			// Push to the Stack.
			if (visited[i] == 0 && adjMatrix[vertex][i] == 1)	   
			{
				stack[++j] = i;		// push to stack
				visited[i] = 1;		// mark as visited
				result[++k] = i;	// add visited node to result list
				vertex = stack[j];	// update the new vertex	
				i = -1;				// reset loop
			}
		
			// Pop the Stack.	
			if (i == (numNodes-1))
			{
				j--;
				
				// The last node was popped of the stack; thus, every node has been reached.
				if (j == -1)
					break;

				vertex = stack[j];
				i = -1;
			}	
		}

		return result;
	}	
    
    // Add BFS function //
	private int[] BFS(int vertex, int numNodes, int[][] adjMatrix) {
		
		int[] visited = new int[numNodes];
		int[] result = new int[numNodes];
		int i,			// index for main loop 
		   	k = 0;		// index for visited nodes
		
		//// Declare a Queue object ///
		Queue <Integer> nodes = new LinkedList<Integer>();	

		// Initialize every node to unvisited for start of search.
		for (i = 0; i < numNodes; i++)	
			visited[i] = 0;

		// Mark as visited.
		visited[vertex] = 1;	
		result[k] = vertex;

		for (i = 0; i < numNodes; i++)
		{
			// Enqueue
			if (visited[i] == 0 && adjMatrix[vertex][i] == 1)
			{
				visited[i] = 1;
				result[++k] = i;
				nodes.offer(i);
			}

			// Dequeue
			if (i == (numNodes-1))
			{
				// Queue is empty (terminate the search).
				if (nodes.peek() == null)
					break;
	
				vertex = nodes.peek();	// update the new vertex
				nodes.poll();			// remove node from queue
				i = -1;					// update the index	
			}

		}
		return result;
	}
	
	private void printResult(int[] arr)
	{
		int len = arr.length;

		for (int i = 0; i < len; i++)
			System.out.print(arr[i]);
		System.out.println();
	}	

        
    public static void main (String args[]) throws IOException  {

		int i, j, k, numGraphs, vertex, numNodes; 

		// create a Graph object
		Graph graph = new Graph();

		// Read in the file.
		Scanner in = new Scanner( new File("graphs.txt") );

		numGraphs = in.nextInt();
		
		// loop through numGraph times
		for (i = 0; i < numGraphs; i++) 
		{
			vertex = in.nextInt();
			numNodes = in.nextInt();
			int[][] adjMatrix = new int[numNodes][numNodes]; 

			// Build the adjacency matrix
			for (j = 0; j <	numNodes; j++) 
				for (k = 0; k <	numNodes; k++)
					adjMatrix[j][k] = in.nextInt();

			// Call search funcitons.
			// BFS
			System.out.print("BFS(" + i + "," + vertex + "): ");
			graph.printResult( graph.BFS(vertex, numNodes, adjMatrix) );
	
			// DFS			
			System.out.print("DFS(" + i + "," + vertex + "): ");
			graph.printResult( graph.DFS(vertex, numNodes, adjMatrix) );
		}

	}

} // end class Graphs