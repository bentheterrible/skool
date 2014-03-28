/*
 * Name: Ben Feistmann
 * Course Number: COP 3502
 * Section Number: 0001
 * Title: Heap.java (Assignment 5)
 * Date: 3/25/14
 * 
 */

import java.io.*;
import java.util.*;

public class Heap {

	public static void main (String args[]) throws IOException  {
		
		// Read in the file.
		Scanner in = new Scanner( new File("heapops.txt") );
		
		// Read through the file.
		do {	
			switch(in.next()) {
				case "load":
					print_array(load(in));
					break;
				case "print":
					print();
					break;
				case "build-heap":
					build_heap();
					break;
				case "delete-max":
					delete_max();
					break;
				case "insert":
					insert();
					break;
				case "heapsort":
					heapsort();
					break;
			}
		} while(in.hasNext() == true);

	} // end main


	public static void print_array(int[] arr) {
		int i, len;

		len	= arr.length;
		for (i = 0; i < len; i++)
			System.out.print(arr[i]);
		System.out.println();

	}
	
	// Converts an ArrayList to an primative int array (int[]).
	public static int[] convert_list(ArrayList<Integer> list) {
		int[] ret = new int[list.size()];

		for (int i = 0; i < ret.length; i++) {
			ret[i] = list.get(i).intValue();
		}
	
		return ret;
	}

	public static int[] load(Scanner in) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		do {	
			list.add(in.nextInt());
		} while (in.hasNextInt() == true);
		
		return convert_list(list);	
	}

	public static void print() {
	}

	public static void build_heap() {
	}
	
	public static void delete_max() {
	}
	
	public static void insert() {
	}
	
	public static void heapsort() {
	}
} // end class
