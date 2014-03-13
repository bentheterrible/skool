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
		String binNum1, binNum2;

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
				binNum1 = in.next(); 
				
				// Convert the binary number from a String to an int array.
				for (j = 0; j < len1; j++) {
					a = binNum1.charAt(j);		// grabs a specific character in the string
					b = a-48;					// converts that character into an integer
					bin1[j] = b;				// place each converters char->int in respective index
				}
				
				// Read in length of the second binary number and initialize length of array.
				len2 = in.nextInt();
				int[] bin2 = new int[len2];

				// Read in the binary number.
				binNum2 = in.next(); 
				
				// Convert the binary number from a String to an int array.
				for (j = 0; j < len2; j++) {
					a = binNum2.charAt(j);		// grabs a specific character in the string
					b = a-48;					// converts that character into an integer
					bin2[j] = b;				// place each converters char->int in respective index
				}

				//////////////// 
				// Algorithms //
				//////////////// 
				
				// Standard Multiplication Algorithm.
				printArray( multiply(bin1, bin2, len1, len2) );
				
				// Karatsuba's divide and conquer algorithm.
				//karatsuba(binNum1, binNum2);
				
			} // end main for-loop
		} // end main

	public static int[] multiply(int[] bin1, int[] bin2, int len1, int len2) {
		int i, len,
			carry = 0,
			j = 0, 
			n = 2 * len2;
		int[][] num_to_add = new int[len2][n];
		int[] binary1 = new int[len1];
		int[] binary2 = new int[len2];
		int[] result = new int[n];
		int[] array = new int[n];

		binary1 = reverseArray(bin1);
		binary2 = reverseArray(bin2);

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
		int i;
		int len1 = n.length();
		int len2 = m.length();
		int nextBinSize = findNextBinSize(n,m);
		
		for (i = 0; i < (nextBinSize - len1); i++) {
			n = "0" + n;
		}	

		for (i = 0; i < (nextBinSize - len2); i++) {
			m = "0" + m;
		}	
		
		
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
