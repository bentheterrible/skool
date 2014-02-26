/*
 * Name: Ben Feistmann
 * Course Number: COP 3502
 * Section Number: 0001
 * Title: TopoSort.java (Assignment 3)
 * Date: 2/19/14
 * 
 */

import java.io.*;
import java.util.*;

public class TopoSort {

	public static void main(String args[]) throws IOException   {

		int i, j, k, numGraphs, vertex = 0, numNodes;

		// Read in the file.
		Scanner in = new Scanner( new File("graphs.txt") );

		numGraphs = in.nextInt();

		// loop through numGraph times
		for (i = 0; i < numGraphs; i++) {
			numNodes = in.nextInt();
			int[][] adjMatrix = new int[numNodes][numNodes]; 

			// Build the adjacency matrix
			for (j = 0; j <	numNodes; j++) {
				for (k = 0; k <	numNodes; k++) {
					adjMatrix[j][k] = in.nextInt();
				}
			}
			if (DFS(vertex, numNodes, adjMatrix) != null) {
				System.out.print("TS(" + i + ",DFS): ");
				printResult(TS_DFS(DFS(vertex, numNodes, adjMatrix)));
				System.out.print("TS(" + i + ",DBO): ");
				printResult(DBO(vertex, numNodes, adjMatrix));
			}
			else {
				System.out.print("TS(" + i + ",DFS): ");
				System.out.println("NO TOPOLOGICAL SORT");
				System.out.print("TS(" + i + ",DBO): ");
				System.out.println("NO TOPOLOGICAL SORT");
			}
		}
	}

	public static int[] TS_DFS(int[] array) {
		int i, temp;
		
		for (i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;	
		}

		return array;
	}

	public static int[] DFS(int vertex, int numNodes, int[][] adjMatrix) { 
		int[] visited = new int[numNodes];
		int[] result = new int[numNodes];
		int[] onTheStack = new int[numNodes];
		Deque<Integer> stack = new ArrayDeque<Integer>();
		int x, i, j, k = 0;

		// Initialize every node to unvisited and nodes on the stack.
		for (i = 0; i < numNodes; i++)	{
			visited[i] = 0;
			onTheStack[i] = 0;
		}

		// Mark as visited
		visited[vertex] = 1;	

		// Push the starting vertex on to the stack.
		stack.push(vertex);

		// mark vertex as being on the stack.
		onTheStack[vertex] = 1;

		// Main Loop for traversing the directed graph.
		for(i = 0; i < numNodes; i++) {
			for(j = 0; j < numNodes; j++) {
				
				// Check for cycles.	
				if (adjMatrix[i][j] == 1 && visited[j] == 1 && onTheStack[j] == 1) {
					return null;
				}

				// Check there is a connection from current vertex to next node
				// and that the node has not been visited.
				if (adjMatrix[i][j] == 1 && visited[j] == 0) {
					vertex = j;							// update the vertex
					stack.push(vertex);					// push the vertex on the stack
					onTheStack[vertex] = 1;				// mark vertex as being on the stack
					visited[vertex] = 1;				// mark the vertex as visited
					i = vertex;							// update the outer loop to the new vertex
					j = -1;								// reset the inner loop to 'zero' --> really -1
				}									
				
				// Current vertex has no children (dead end).
				if (j == numNodes-1) {
					
					// Check if the stack is not empty	
					if (stack.peek() != null) {
						result[k++] = stack.peek();			// add node to be popped to the result array.
						onTheStack[stack.peek()] = 0;		// mark vertex as being on the stack
						stack.pop();						// pop the current node (dead end).
						j = -1;								// reset the inner loop to 'zero' --> really -1

						if (stack.peek() != null) {
							vertex = stack.peek();			// update the vertex with the node on top of the stack.	
						}
						else {
							// Find the next unvisited node to be the new vertex.
							for (x = 0; x < numNodes; x++) {
								if (visited[x] == 0) {
									vertex = x;				// update the new vertex.
									visited[x] = 1;			// mark the vertex as visited
									stack.push(vertex);		// push new vertex on the stack
									onTheStack[vertex] = 1;	// mark vertex as being on the stack
								}								
							}
						}
					}
				}
			} // end inner-loop [j]
		} // end out-loop [i] 

		return result;
	} // end DFS()

	public static int[] DBO(int vertex, int numNodes, int[][] adjMatrix) { 
		Queue <Integer> nodes = new LinkedList<Integer>();	
		int[] result = new int[numNodes];
		int[] visited = new int[numNodes];
		int i, j, k = 0, counter = 0;

		do  {	
			// Nested for loop for traversing adjacency matrix
			for (i = 0; i < numNodes; i++) {
				for (j = 0; j < numNodes; j++) {
					
					// Checks if node has an incoming edge
					// Moves to next vertex if so
					if (adjMatrix[j][i] == 1 || visited[i] == 1) {
						break;
					}
					
					// Checks if node does not have incoming edges
					// Adds to the queue if so
					else if (adjMatrix[j][i] == 0 && j == numNodes-1) {
						nodes.offer(i);				// Enqueue vertex to the queue
						visited[i] = 1;				// Mark vertex as visited
					}
				} // end inner-loop
			} // end outer-loop
			
			while (nodes.peek() != null) {
				vertex = nodes.poll();				// Dequeue vertex from the queue
				result[k++] = vertex;				// Add vertex to topological sort array
				counter++;							// Update the counter

				// Delete the outgoing edges of the vertex.
				for (i = 0; i < numNodes; i++) {
					adjMatrix[vertex][i] = 0;	
				} 
			} 
		} while (counter != numNodes);

		return result;

	} // end DBO()

	public static void printResult(int[] arr) {
		int len = arr.length;

		for (int i = 0; i < len; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}	

} // end class 
