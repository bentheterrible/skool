
import java.io.*;
import java.util.*;

public class Test {
	
	public static void main(String args[]) throws IOException {
		String n = "1234";
		String m = "11";
		String a, b;

		a = n.substring(0,2);
		System.out.print("test "
		System.out.println(a);
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

} // end class
