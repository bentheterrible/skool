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
					load(in);
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
	
	public static void load(Scanner in) {
		System.out.println("Made it to load()");
	}

	public static void print() {
		System.out.println("Made it to print()");
	}

	public static void build_heap() {
		System.out.println("Made it to build_heap()");
	}
	
	public static void delete_max() {
		System.out.println("Made it to delete_max()");
	}
	
	public static void insert() {
		System.out.println("Made it to insert()");
	}
	
	public static void heapsort() {
		System.out.println("Made it to heapsort()");
	}
} // end class
