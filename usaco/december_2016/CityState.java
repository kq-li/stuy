import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

class Pair {
	public String first; 
	public String second;
	// public ArrayList<Pair> matching;

	public Pair(String first, String second) {
		this.first = first;
		this.second = second;
		// this.matching = new ArrayList<Pair>();
	}

	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	public boolean equals(Object other) {
		return (this == other) || (other instanceof Pair &&
															 first.equals(((Pair) other).first) &&
															 second.equals(((Pair) other).second));
	}

	public boolean isReverse(Pair other) {
		return first.equals(other.second) && second.equals(other.first);
	}

	public Pair reverse() {
		return new Pair(second, first);
	}
}

public class CityState {
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

	public static void printArray(Object[] a) {
		System.out.print("[");
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);

			if (i != a.length - 1) {
				System.out.print(", ");
			}
		}

		System.out.println("]");
	}

	public static void printSet(HashSet a) {
		System.out.print("Set([");
		
		for (Object o : a) {
			System.out.print(o + ", ");
		}

		System.out.println("])");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter("citystate.out");

		int N = Integer.parseInt(in.readLine());

		HashMap<String, Integer> map = new HashMap<String>();
		HashMap<String, Integer> rev = new HashMap<String>();

		int count = 0;

		for (int i = 0; i < N; i++) {
			String[] line = in.readLine().split(" ");
			line[0] = line[0].substring(0, 2);
			String city = line[0] + " " + line[1];
			String ytic = line[1] + " " + line[0];

			if (map.get(city) != null) {
				map.put(city, map.get(city) + 1);
			} else {
				map.put(city, 1);
			}

			if (rev.get(ytic) != null) {
				rev.put(ytic, rev.get(ytic) + 1);
			} else {
				rev.put(ytic, 1);
			}

			count += map.get(city);

		}
		
		// HashSet<String> set = new HashSet<String>();
		// HashSet<String> rev = new HashSet<String>();
		// int count = 0;

		// for (int i = 0; i < N; i++) {
			// String[] line = in.readLine().split(" ");
			// line[0] = line[0].substring(0, 2);
			// String city = line[0] + " " + line[1];

			// if (!line[0].equals(line[1])) {
				// String ytic = line[1] + " " + line[0];
			
				// if (set.contains(ytic) || rev.contains(city)) {
					// count++;
				// } else {
					// set.add(city);
					// rev.add(ytic);
				// }
			// }
		// }

		// printSet(set);
		// System.out.println(count);
		// out.println(count);

		// Pair[] cities = new Pair[N];
		
		// for (int i = 0; i < N; i++) {
			// String[] line = in.readLine().split(" ");
			// cities[i] = new Pair(line[0].substring(0, 2), line[1]);
		// }

		// printArray(cities);

		// int special = 0;

		// for (int i = 0; i < N; i++) {
			// if (cities[i] != null) {
				// int same = 0;
				// int reverse = 0;

				// for (int j = i + 1; j < N; j++) {
					// if (cities[i] != null && cities[j] != null) {
						// if (cities[i].equals(cities[j])) {
							// System.out.println("City " + cities[i] + " is the same as " + cities[j]);
							// cities[j] = null;
							// same++;
						// } else if (cities[i].isReverse(cities[j])) {
							// System.out.println("City " + cities[i] + " is the reverse of " + cities[j]);
							// cities[j] = null;
							// reverse++;
						// }
					// }
				// }

				// System.out.println("Found " + same + " same and " + reverse + " reverse for " + cities[i]);

				// special += (same + 1) * reverse;
			// }
		// }

		// System.out.println(special);
		// out.println(special);
		
		in.close();
		out.close();
	}
}
