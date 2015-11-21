import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class TimeTravel {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"TimeTravelIN.txt"));
		while (br.ready()) {
			String[] data = br.readLine().split(" ");
			int N = (int)Math.sqrt(data.length);
			int[][] times = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					times[i][j] = Integer.parseInt(data[N*i+j]);
				}
			}

			System.out.println(canTimeTravel(times.clone()));
		}
		br.close();
	}
	//Here is an implementation of the Bellman-Ford algorithm. This is much
	//too slow on the big graphs because it runs in time proportional to V*E
	//where V is the number of vertices and E is the number of edges
	public static boolean canTimeTravelBF(int[][] times) {
		int N = times.length;
		int[] dists = new int[N];
		int src = 0;
		for (int i = 0; i < N; i++) {
			dists[i] = -1;
			for (int j = 0; j < N; j++) {
				if (times[i][j] < 0) {
					src = j;
				}
			}
		}
		dists[src] = 0;
		for (int n = 1; n < N; n++) {
			for (int u = 0; u < N; u++) {
				if (dists[u] >= 0) {
					for (int v = 0; v < N; v++) {
						if (u != v && times[u][v] > 0) {
							int w = times[u][v];
							if (dists[v] < 0 || dists[u] + w < dists[v]) {
								dists[v] = dists[u] + w;
							}
						}
					}
				}
			}
		}
		System.out.print("");
		for (int u = 0; u < N; u++) {
			if (dists[u] >= 0) {
				for (int v = 0; v < N; v++) {
					if (dists[v] >= 0 && times[u][v] != 0 && 
					    dists[u] + times[u][v] < dists[v]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	//This basically an implementation of Dijkstra's Algorithm
	//I just remove the negative edge, run dijkstra's then see if adding the 
	//edge back in would make a negative cycle
	public static boolean canTimeTravel(int[][] times) {
		int N = times.length;
		boolean[] discovered = new boolean[N];
		int[] dists = new int[N];
		
		PriorityQueue<Waypoint> Q = new PriorityQueue<Waypoint>();
		int src = 0, tgt = 0;
		int neg_dist = 0;
		for (int i = 0; i < N; i++) {
			discovered[i] = false;
			dists[i] = -1;
			for (int j = 0; j < N; j++) {
				if (times[i][j] < 0) {
					src = j;
					tgt = i;
					neg_dist = times[i][j];
					times[i][j] = 0;
				}
			}
		}
		dists[src] = 0;
		for (int i = 0; i < N; i++){
				Q.add(new Waypoint(i, dists[i]));
		}
		
		while (!Q.isEmpty()) {
			Waypoint u = Q.poll();
			if (discovered[u.id] || dists[u.id] < 0) {
				continue;
			}
			discovered[u.id] = true;
			if (u.id == tgt) {
				return dists[tgt] + neg_dist < 0;  
			}			
			for (int i = 0; i < N; i++) {
				if (i != u.id && !discovered[i] && times[u.id][i] > 0) {
					if (dists[i] < 0 || u.dist + times[u.id][i] < dists[i]) {
						dists[i] = u.dist + times[u.id][i];
						Q.add(new Waypoint(i, dists[i]));
					}

				}
			}
		}
		return false;
	}
}
class Waypoint implements Comparable<Waypoint> {
	int id, dist;
	public Waypoint(int id, int dist) {
		this.id = id;
		this.dist = dist;
	}
	@Override
	public int compareTo(Waypoint qe) {
		if (dist < 0) return 1;
		if (qe.dist < 0) return -1;
		return dist - qe.dist;
	}
	
}
