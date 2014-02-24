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

		int i, j, k, numGraphs, vertex = 0, numNodes; /////////// Change the vertex ==> 'vertex' after all function are written //
		Deque<Integer> stack = new ArrayDeque<Integer>();

		// create a Graph object
		TopoSort graph = new TopoSort();

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
			
			/*
			// Perform Topological Sort	
			if (graph.isSortable(adjMatrix)) {
			}
			
			// Not Sortable
			else {
			}
			*/

		}

	}

	public int[] DFS(int vertex, int numNodes, int[][] adjMatrix) {

		int[] visited = new int[numNodes];
		int[] result = new int[numNodes];
		Deque<Integer> stack = new ArrayDeque<Integer>();
		int x, i, j, k = 0;

		// Initialize every node to unvisited for start of search.
		for (i = 0; i < numNodes; i++)	
			visited[i] = 0;
		
		// Push the starting vertex on to the stack.
		stack.push(vertex);

		// Mark as visited.
		visited[vertex] = 1;	

		// Main Loop for traversing the directed graph.
		for(i = 0; i < numNodes; i++) {
			for(j = 0; j < numNodes; j++) {

				// Check there is a connection from current vertex to next node
				// and that the node has not been visited.
				if (adjMatrix[i][j] == 1 && visited[ adjMatrix[i][j] ] == 0) {
					vertex = adjMatrix[i][j];		// update the vertex
					stack.push(vertex);				// push the vertex on the stack
					visited[vertex] = 1;			// mark the vertex as visited
					i = vertex;						// update the outer loop to the new vertex
					j = -1;							// reset the inner loop to 'zero' --> really -1
				}									// because the for-loop will interate the j-value to zero.
				
				// Current vertex has no children (dead end).
				if (j == numNodes-1) {
					result[k++] = stack.peek();		// add node to be popped to the result array.
					stack.pop();					// pop the current node (dead end).
					
					// Check if the stack is not empty	
					if (stack.peek() != null) {
						vertex = stack.peek();			// update the vertex with the node on top of the stack.	
					}

					// The stack is empty.
					else {
						// Find the next unvisited node to be the new vertex.
						for (x = 0; x < numNodes; x++) {
							if (visited[i] = 0) {
								vertex = visited[i];		// update the new vertex.
								stack.push(vertex);			// push new vertex on the stack;
								j = -1;						// reset the inner loop to 'zero' --> really -1
							}								// because the for-loop will interate the j-value to zero.
						}
					}
				}
			} // end inner-loop
		} // end out-loop 

		return result;
	} // end DFS()

} // end class 






