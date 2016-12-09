import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Minimizing {

	// Fill out the body of this method
  // stops is the maximum index of any stop
  // routs[i][j] is the jth stop of the ith ship
  // start is the index of the starting stop
  // end is the index of the ending stop
	public static int shortestRoute(int stops, int[][] routes, int start, int end){
		return 0;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader b = new BufferedReader(new FileReader("MinimizingIN.txt"));

		while(b.ready()){
			String[] data = b.readLine().split(" ");
			int N = Integer.parseInt(data[0]);
			int M = Integer.parseInt(data[1]);
			int start = Integer.parseInt(data[2]);
			int end = Integer.parseInt(data[3]);
			int[][] routes = new int[N][];
			for (int i = 0; i < N; i++) {
				data = b.readLine().split(" ");
				routes[i] = new int[data.length];
				for (int j = 0; j < data.length; j++) {
					routes[i][j] = Integer.parseInt(data[j]);
				}
			}
			
			System.out.println(shortestRoute(M, routes, start, end) - 1);
		}
				
	}

}
