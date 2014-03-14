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
		
		int i, j, k, len1, len2;
		String binNum1, binNum2;

		// Read in the file.
		Scanner in = new Scanner( new File("test.txt") );
		
			// Number of multiplications.
			k = in.nextInt();
			
			// Loop through the file.	
			for (i = 0; i < k; i++) {
			
				// Read in inputs	
				len1 = in.nextInt();
				binNum1 = in.next(); 
				len2 = in.nextInt();
				binNum2 = in.next(); 

				//////////////// 
				// Algorithms //
				//////////////// 
				
				// Standard Multiplication Algorithm.
				//printArray( multiply(binNum1, binNum2) );
					
				len1 = binNum1.length();
				len2 = binNum2.length();
				int nextBinSize = findNextBinSize(binNum1,binNum2);
		
				// Add Padding to both strings.	
				for (j = 0; j < (nextBinSize - len1); j++) {
					binNum1 = "0" + binNum1;
				}	

				for (j = 0; j < (nextBinSize - len2); j++) {
					binNum2 = "0" + binNum2;
				}	

				// Karatsuba's divide and conquer algorithm.
				// karatsuba(binNum1, binNum2);
				String a1 = "01", a0 = "11", b1 = "10", b0 = "01";
				int[] hello = new int[3];
				int[] meow = new int[3];

				hello = binAdd(a1,a0);
				meow = binAdd(b1,b0);
			
				System.out.print("a1 + a0 = ");
				printArray(hello);
				System.out.print("b1 + b0 = ");
				printArray(meow);

				System.out.print("mult: ");
				printArray( multiply( arrayToString(hello), arrayToString(meow)));
				

			} // end main for-loop
		} // end main

	public static String arrayToString(int[] a) {
		String s = ""; 
		int i, k, len = a.length;
		for (i = 0; i < len; i++) {
			k = a[i];
			s = s + ""+ k;	
		}
		return s;
	}

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
		int[][] addMat = new int[len2][n];
		int[] binary1 = new int[len1];
		int[] binary2 = new int[len2];
		int[] result = new int[n];
		int[] array = new int[n];
		
		// Convert String to Integer array.
		binary1 = stringToArray(bin1); 
		binary2 = stringToArray(bin2); 

		binary1 = reverseArray(binary1);
		binary2 = reverseArray(binary2);

		// Populate the add matrix.	
		for (i = 0; i < len2; i++) {
			for (j = 0; j < len1; j++) {
				addMat[i][i+j] = binary1[j] * binary2[i];
			}
		}
		
		// Add up the results.	
		for (i = 0; i < n; i++) {
			for (j = 0; j < len2; j++) {
				result[i] += addMat[j][i];
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

	// Post condition: the string are the same size.
	public static int[] binAdd(String a, String b) {
		int i, j;
		int len = a.length(); 
		int[] arrA = new int[len];
		int[] arrB = new int[len];
		int[] result = new int[2*len];	

		// Converts binary numbers to int arrays.
		arrA = stringToArray(a);
		arrB = stringToArray(b);
		
		// Reverse array in preparation for looping 
		// and adding the binary numbers together.	
		arrA = reverseArray(arrA);
		arrB = reverseArray(arrB);
		
		// Add the binary numbers
		for (i = 0; i < len; i++) {
			result[i] = arrA[i] + arrB[i];
		}
	
		int carry = 0;	
		len = result.length;
		int[] array = new int[len];
		for (i = 0; i < len; i++) {
			array[i] = (carry + result[i]) % 2;
			carry = (carry + result[i])	/ 2;
		}
		// Reverse result to correct direction.
		array = reverseArray(array);

		return array;		
	}

	// c = c2 * 2^n + c1 * 2^(n/2) + c0,
	// where c2 = a1 * b1, c0 = a0 * b0,
	// and c1 = (a1 + a0) * (b1 + b0) - (c2 + c0).	
	public static void karatsuba(String a, String b) {
		int i, len = a.length();
		int[] c2 = new int[len/2];
		int[] c0 = new int[len/2];
		int[] c1 = new int[len];
		String a0 = "",
			   a1 = "",
			   b0 = "",
			   b1 = "";

		if (len == 1);
			//return multiply(a,b);		
		
		// Split the binary numbers in half.
		for (i = 0; i < len/2; i++) {
			a1 = a1 + a.charAt(i);	
			b1 = b1 + b.charAt(i);
		}
		
		for (i = len/2; i < len; i++) {
			a0 = a0 + a.charAt(i);	
			b0 = b0 + b.charAt(i);
		}
		
		c2 = multiply(a1,b1);	
		c0 = multiply(a0,b0);	
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
