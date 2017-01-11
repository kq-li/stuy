import java.io.*;
import java.util.*;

public class Haybales {
	public static int max(int[] a) {
		int max = 0;

		for (int i : a) {
			if (i > max) {
				max = i;
			}
		}

		return max;
	}
	
	public static int[] cumSum(int[] a) {
		int total = 0;
		int[] ret = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			total += a[i];
			ret[i] = total;
		}

		return ret;
	}

	public static void printArray(int[] a) {
		System.out.print("[");
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);

			if (i != a.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println("]");
	}
	
	public static int countHaybales(int[] chaybales, int start, int end) {
		if (start > chaybales.length - 1 || end < 0) {
			return 0;
		}

		if (end > chaybales.length - 1) {
			end = chaybales.length - 1;
		}

		if (start < 0) {
			start = 0;
		}

		return chaybales[end] - chaybales[start - 1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter("haybales.out");

		String[] first = in.readLine().split(" ");
		int N = Integer.parseInt(first[0]);
		int Q = Integer.parseInt(first[1]);

		String[] second = in.readLine().split(" ");
		int[] haybaleLocs = new int[N];

		for (int i = 0; i < N; i++) {
			haybaleLocs[i] = Integer.parseInt(second[i]);
		}

		int[] haybales = new int[max(haybaleLocs) + 1];

		for (int loc : haybaleLocs) {
			haybales[loc] = 1;
		}

		int[] chaybales = cumSum(haybales);
			
		printArray(haybales);
		printArray(chaybales);

		for (int i = 0; i < Q; i++) {
			String[] line = in.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			int count = countHaybales(chaybales, start, end);
			System.out.println("Count: " + count);
			out.println(count);
		}	
		
		in.close();
		out.close();
	}
}
