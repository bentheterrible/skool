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
		
		int i, k, len1, len2;
		String binNum1, binNum2;

		// Read in the file.
		Scanner in = new Scanner( new File("mult.txt") );
		
			// Number of multiplications.
			k = in.nextInt();
			
			// Loop through the file.	
			for (i = 0; i < k; i++) {

				// Read in length of the first binary number and initialize length of array.
				len1 = in.nextInt();

				// Read in the first binary number as a String.
				binNum1 = in.next(); 
				
				// Read in length of the second binary number and initialize length of array.
				len2 = in.nextInt();

				// Read in the second binary number as a String.
				binNum2 = in.next(); 

				//////////////// 
				// Algorithms //
				//////////////// 
				
				// Standard Multiplication Algorithm.
				printArray( multiply(binNum1, binNum2) );
				
				// Karatsuba's divide and conquer algorithm.
				//karatsuba(binNum1, binNum2);
				
			} // end main for-loop
		} // end main

	public static int[] stringToArray(String s) {
		int b, j, len = s.length();
		int[] array = new int[len];
		char a;	

		// Convert the binary number from a String to an int array.
		for (j = 0; j < len; j++) {
			a = s.charAt(j);			// grabs a specific character in the string
			b = a-48;					// converts that character into an integer
			array[j] = b;				// place each converters char->int in respective index
		}
		return array;
	}

	public static int[] multiply(String bin1, String bin2) {
		int len1 = bin1.length(),
			len2 = bin2.length();
		int i, len,
			carry = 0,
			j = 0, 
			n = 2 * len2;
		int[][] num_to_add = new int[len2][n];
		int[] binary1 = new int[len1];
		int[] binary2 = new int[len2];
		int[] result = new int[n];
		int[] array = new int[n];
		
		// Convert String to Integer array.
		binary1 = stringToArray(bin1); 
		binary2 = stringToArray(bin2); 

		binary1 = reverseArray(binary1);
		binary2 = reverseArray(binary2);

		for (i = 0; i < len2; i++) {
			for (j = 0; j < len1; j++) {
				num_to_add[i][i+j] = binary1[j] * binary2[i];
			}
		}
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < len2; j++) {
				result[i] += num_to_add[j][i];
			}
		}

		// Reverse the results back to proper direction.
		result = reverseArray(result);	

		// Carry over all the numbers if any when adding binary numbers.
		len = (result.length) - 1;
		for (i = len; i >= 0; i--) {
			array[i] = (carry + result[i]) % 2;	
			carry = (carry + result[i]) / 2;
		}
		
		array = reverseArray(array);	
	
		// Trim the leading zeros, if any.	
		len = array.length;
		for (i = len-1; i >= 0; i--) {
			if (array[i] == 0) {
				len--;
			}
			else
				break;
		}
		
		// Transfer trimmed array into a new one.	
		int[] finished = new int[len];
		for (i = 0; i < len; i++) {
			finished[i] = array[i];
		}
	
		// Reverse the finished array to correct direction.
		finished = reverseArray(finished);
			
		// Print the results of the finished multiplication
		// of two binary numbers.
		return finished;
	}

	public static int[] reverseArray(int[] array) {
		int i, temp;
		
		for (i = 0; i < array.length / 2; i++) {
			temp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = temp;	
		}

		return array;
	}
	
	public static void karatsuba(String n, String m) {
		int i, len;
		int len1 = n.length();
		int len2 = m.length();
		int nextBinSize = findNextBinSize(n,m);
		
		// Add Padding to both strings.	
		for (i = 0; i < (nextBinSize - len1); i++) {
			n = "0" + n;
		}	

		for (i = 0; i < (nextBinSize - len2); i++) {
			m = "0" + m;
		}	
		
		// Get new length of string for splitting.	
		len = n.length();

	}

	public static void printArray(int[] arr) {
		int i, len;

		len	= arr.length;
		for (i = 0; i < len; i++)
			System.out.print(arr[i]);
		System.out.println();

	}

	public static int powOfTwo(int n) {
		int i, x = 1;

		for (i = 0; i < n; i++) {
			x *= 2;
		}

		return x;
	}
	
	public static int findNextBinSize(String n, String m) {
		int len1 = n.length();
		int len2 = m.length();
		int max = Math.max(len1,len2);
		int i = 1;

		while (powOfTwo(i) < max) {
			i++;
		}	

		return powOfTwo(i);
	}

} // end Multiplication class
