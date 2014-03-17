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
		String bin1, bin2;


		// Read in the file.
		Scanner in = new Scanner( new File("mult.txt") );
		
			// Number of multiplications.
			k = in.nextInt();
			
			// Loop through the file.	
			for (i = 0; i < k; i++) {
			
				// Read in inputs	
				len1 = in.nextInt();
				bin1 = in.next(); 
				len2 = in.nextInt();
				bin2 = in.next(); 

				//////////////// 
				// Algorithms //
				//////////////// 
				
				// Standard Multiplication Algorithm.
				System.out.println(multiply(bin1, bin2));
					
				len1 = bin1.length();
				len2 = bin2.length();
				int nextBinSize = findNextBinSize(bin1,bin2);
				
				// Karastuba Algorithm
				//karatsuba(bin1, bin2);
				
			} // end main for-loop
			
		} // end main
		
		public static String bitFlip(String s) {
			int i;
			int[] array = new int[s.length()];
			
			array = stringToArray(s);	

			for (i = 0; i < s.length(); i++) {
				if (array[i] == 1) {
					array[i] = 0;
				}
				else {
					array[i] = 1;
				}
			}

			return new String(arrayToString(array));
		}

	public static String twosComp(String s) {
		
		// Flip the bits.
		s = bitFlip(s);

		return new String(s);
	}

	public static String reverseString(String s) {
		int i, len = s.length();
		char[] temp = new char[len];	

		for (i = 0; i < len/2; i++) {
			temp[i] = s.charAt(s.length()-1-i);
			temp[s.length()-1-i] = s.charAt(i);
		}

		return String.valueOf(temp);
	}

	public static String unpad(String s) {
		int i, len = s.length();
		int[] a = new int[len];	
		
		a = reverseArray( stringToArray(s) );
		
		// Trim leading zeros, if any.
		for (i = len-1; i >= 0; i--) {
			if (a[i] == 0) {
				len--;
			}
			else {
				break;
			}
		}
		
		int[] b = new int[len];
		for (i = 0; i < len; i++) {
			b[i] = a[i];
		}	

		return new String(arrayToString(b));	
	}	

	public static String arrayToString(int[] a) {
		String s = ""; 
		int i, k, len = a.length;
		for (i = 0; i < len; i++) {
			k = a[i];
			s = s + "" + k;	
		}
		return new String(s);
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


	public static String multiply(String bin1, String bin2) {
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
		return arrayToString(finished);
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
	
	public static String makeSameSize(String s, int n) {
		int i, len = s.length();

		if (len >= n)
			return new String(s);
		for (i = 0; i < len; i++) {
			s = "0" + s;
		}

		return new String(s);
	}

	public static char xor(char a, char b) {
		if (a != b)
			return '1';
		else
			return '0';
	}
	
	public static char or(char a, char b) {
		if (a == '1' || b == '1')
			return '1';
		else
			return '0';
	}

	public static char and(char a, char b) {
		if (a == '1' &&  b == '1')
			return '1';
		else
			return '0';
	}

	public static String binAdd(String a, String b) {
		int i, j;
		int len = a.length(); 
		
		// Make sure the string are the
		// same size before adding them 
		a = makeSameSize(a, b.length());	
		b = makeSameSize(b, a.length());	

		int[] arrA = new int[len];
		int[] arrB = new int[len];
		int[] result = new int[len];	

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

		return new String(arrayToString(array));		
	}

	public static String shiftLeft(String s, int n) {
		String str = new String(s);
		int i;

		for (i = 0; i < n; i++) {
			str = str.concat("0");
		}	

		return new String(str);

	}

	public static void karatsuba(String bin1, String bin2) {
		int i, len = bin1.length();
		int m, m2;
		String high1, high2, low1, low2;

		if (len == 2);
			//return multiply(bin1,bin2);		
		
		m = Math.max(bin1.length(), bin2.length());
		m2 = m/2;
			
		// Split the binary numbers in half.
		high1 = bin1.substring(0, len/2);
		low1 = bin1.substring(len/2, len);
		high2 = bin2.substring(0, len/2);
		low2 = bin2.substring(len/2, len);

		String z0, z1, z2;
		//z0 = karatsuba(low1,low2);
		//z1 = karatsuba( (low1+high1), (low2+high2) );
		//z2 = karatsuba(high1, high2);

		//return binAdd( shiftLeft(z2,2*m2), binAdd( shiftLeft( subtract(z1,binAdd(z2,z0)),m2),z0) );
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
