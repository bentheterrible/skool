/*
 * Name: Ben Feistmann
 * Course Number: COP 3502
 * Section Number: 0001
 * Title: Multiplication.java (Assignment 4)
 * Date: 3/13/14
 * 
 */

import java.io.*;
import java.util.*;

public class Multiplication {
	
	public static void main(String args[]) throws IOException {
		
		int i, j, k, len1, len2, b;
		char a;
		String binNum;

		// Read in the file.
		Scanner in = new Scanner( new File("mult.txt") );
		
		// Number of multiplications.
		k = in.nextInt();
		
		// Loop through the file.	
		for (i = 0; i < k; i++) {

			// Read in length of the first binary number and initialize length of array.
			len1 = in.nextInt();
			int[] bin1 = new int[len1];

			// Read in the binary number as a String.
			binNum = in.next(); 
			
			// Convert the binary number from a String to an int array.
			for (j = 0; j < len1; j++) {
				a = binNum.charAt(j);		// grabs a specific character in the string
				b = a-48;					// converts that character into an integer
				bin1[j] = b;					// place each converters char->int in respective index
			}
			
			// Read in length of the second binary number and initialize length of array.
			len2 = in.nextInt();
			int[] bin2 = new int[len2];

			// Read in the binary number.
			binNum = in.next(); 
			
			// Convert the binary number from a String to an int array.
			for (j = 0; j < len2; j++) {
				a = binNum.charAt(j);		// grabs a specific character in the string
				b = a-48;					// converts that character into an integer
				bin2[j] = b;					// place each converters char->int in respective index
			}

			//////////////// 
			// Algorithms //
			//////////////// 
			
			// Standard Multiplication Algorithm
			printArray(bin1);	
			printArray(bin2);	

		} // end main for-loop
	} // end main

	public static void multiply(int[] bin1, int[] bin2, int len1, int len2) {
	}

	public static void printArray(int[] arr) {
		int i, len;

		len	= arr.length;
		for (i = 0; i < len; i++)
			System.out.print(arr[i]);
		System.out.println();

	}

} // end Multiplication class
