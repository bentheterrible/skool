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
		
		int i, j, k, len, b;
		char a;
		String binNum;

		// Read in the file.
		Scanner in = new Scanner( new File("mult.txt") );
		
		// Number of multiplications.
		k = in.nextInt();
		
		// Loop through the file.	
		for (i = 0; i < k; i++) {

			// Read in length of the first binary number and initialize length of array.
			len = in.nextInt();
			int[] bin1 = new int[len];

			// Read in the binary number as a String.
			binNum = in.next(); 
			
			// Convert the binary number from a String to an int array.
			for (j = 0; j < len; j++) {
				a = binNum.charAt(j);		// grabs a specific character in the string
				b = a-48;					// converts that character into an integer
				bin1[j] = b;					// place each converters char->int in respective index
			}
			
			// Read in length of the second binary number and initialize length of array.
			len = in.nextInt();
			int[] bin2 = new int[len];

			// Read in the binary number.
			binNum = in.next(); 
			
			// Convert the binary number from a String to an int array.
			for (j = 0; j < len; j++) {
				a = binNum.charAt(j);		// grabs a specific character in the string
				b = a-48;					// converts that character into an integer
				bin2[j] = b;					// place each converters char->int in respective index
			}

		}
	} // end main

} // end Multiplication class
