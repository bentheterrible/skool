
import java.io.*;
import java.util.*;

public class Test {
	
	public static void main(String args[]) throws IOException {
		String n = "11111";
		String m = "11";
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
