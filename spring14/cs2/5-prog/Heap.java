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
	
	// Variables //
	private int[] array;
	
	// Constructors //	
	public Heap() {
	}

	public Heap(int[] array) {
		this.array = array;
	}
	
	// Main Methods //
	public static void main (String args[]) throws IOException  {

		// Create a Heap object.
		Heap heap = new Heap(); 
		//System.out.println(heap.test);

		// Read in the file.
		Scanner in = new Scanner( new File("test.txt") );
		
		// Read through the file.
		do {	
			switch(in.next()) {
				case "load":
					ArrayList<Integer> list = new ArrayList<Integer>(heap.load(in));
					int[] array = new int[list.size()];

					heap.array = heap.convert_list(list); // converts array list to array
					break;
				case "print":
					heap.print_array(heap.array);
					break;
				case "build-heap":
					heap.build_heap(heap.array);
					break;
				case "delete-max":
					heap.delete_max();
					break;
				case "insert":
					heap.insert();
					break;
				case "heapsort":
					heap.heapsort();
					break;
			}
		} while(in.hasNext() == true);
	} // end main

	public ArrayList<Integer> load(Scanner in) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		do {	
			list.add(in.nextInt());
		} while (in.hasNextInt() == true);
		
		return list;	
	}

	public void print(int[] array) {
		if (array.length == 0) {
			System.out.println("(emtpy)");
		}
		
		print_array(array);
	}

	public int[] build_heap(int[] array) {
		int len = array.length;	
		
		return array;
	}
	
	public void delete_max() {
	}
	
	public void insert() {
	}
	
	public void heapsort() {
	}
	


	//////////////////////////////////		
	// 		Helper Methods          //
	//////////////////////////////////
		
	public static int get_parent(int[] array, int i) {
		return array[(i-1)/2];
	}

	public static int get_lc(int[] array, int i) {
		return array[2*i+1];
	}

	public static int get_rc(int[] array, int i) {
		return array[2*i+2];
	}

	public static int[] swap(int[] array, int index1, int index2) {
		int temp;
		temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;

		return array;
	}

	public static void print_array(int[] arr) {
		int i, len;

		len	= arr.length;
		for (i = 0; i < len; i++)
			System.out.print(arr[i]);
		System.out.println();

	}
	
	// Converts an ArrayList to an primative int array (int[]).
	public int[] convert_list(ArrayList<Integer> list) {
		int[] ret = new int[list.size()];

		for (int i = 0; i < ret.length; i++) {
			ret[i] = list.get(i).intValue();
		}

	return ret;
	}

} // end class
