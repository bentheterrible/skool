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
		
		int i, j, k, len, binNum;

		// Read in the file.
		Scanner in = new Scanner( new File("mult.txt") );
		
		// Number of multiplications.
		k = in.nextInt();
		
		// Loop through the file.	
		for (i = 0; i < k; i++) {

			// Read in length of binary number and initialize length of array.
			len = in.nextInt();
			int[] bin1 = new int[len];

			// Read in the binary number.
			binNum = in.nextInt(); 
		
			// Convert binary int to array.	
			for (j = len-1; j >= 0; j--) {
				bin1[j] = binNum%10;
				binNum /= 10;	
			}

		}
	} // end main

} // end Multiplication class