/*
 * Name: Ben Feistmann
 * Course Number: COP 3502
 * Section Number: 0001
 * Title: Hopscotch.java (Assignment 6)
 * Date: 4/09/14
 * 
 */

import java.io.*;
import java.util.*;

public class Hopscotch {

	public static void main(String args[]) throws IOException  {
		/*
		// Read in the file.
		Scanner in = new Scanner( new File("hopscotch.in") );

		int i, var = in.nextInt();
		
		for (i = 0; i < var; i++) { 
			 System.out.println( "Game #" + (i+1) + ": " +  score(in.nextInt()) );
		}
		*/
		
		int n = 7;

		System.out.println(score(n));

	} // end main

	public static int score(int n) { 

		int i, temp;
		int[] arr = new int[n+1];
		arr[0] = 0;

		for (i = 1; i <= n; i++) { 
				
			temp = 1 + arr[i-1];
		
			if (i%7 == 0) {
				temp = min(temp, 2 + arr[i-4]);
			}
			
			if (i > 10 && isPrime(i)) {
				temp = min(temp, 3 + arr[i-i%10]);
			}

			if (i%11 == 0) {
				temp = min(temp, 4 + arr[i-digisum(i)]);
			}

			arr[i] = temp;
		}	

		return arr[n];
	}

	public static int min(int n, int k) {
		return n < k ? n : k;
	}

	public static boolean isPrime(int n) {
		return true;	
	}

	public static int digisum(int n) {
		
		if (n < 10) {
			return n;
		}		
		
		return n%10 + digisum(n/10);
	}
} // end class
