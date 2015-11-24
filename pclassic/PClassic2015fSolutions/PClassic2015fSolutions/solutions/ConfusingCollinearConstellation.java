import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;


public class ConfusingCollinearConstellation {
	private static final int size =  40001;
	private static final int xmin = -20000;
	private static final int ymin = -20000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"ConstellationIN.txt"));
		while (br.ready()) {
			String[] data = br.readLine().split(" ");
			int[] pts;
			pts = new int[data.length];
			for (int i = 0; i < data.length; i++) {
				pts[i] = Integer.parseInt(data[i]);
			}
			System.out.println(hasCollinear(pts));
		}
		br.close();
	}
	private static int getSlopeId(int y, int x) {
		int gcf = gcf(Math.abs(y), Math.abs(x));
		y = y/gcf-ymin;
		x = x/gcf-xmin;
		
		return y*size + x;
	}
	private static int gcf(int a, int b) {
		if (b > a) return gcf(b, a);
		if (b == 0) return a;
		return gcf(b, a%b);
	}
	private static boolean hasCollinear(int[] pts) {
		int[] x = new int[pts.length/2], y = new int[pts.length/2];
		for (int i = 0; i < x.length; i++) {
			x[i] = pts[2*i];
			y[i] = pts[2*i+1];
		}
		TreeSet<Integer> slopes = new TreeSet<Integer>();
		for (int i = 0; i < x.length; i++) {
			slopes.clear();
			for (int j = i+1; j < x.length; j++) {
				if (j != i) {
					int slope = getSlopeId(y[i] - y[j],
										   x[i] - x[j]);
					if (slopes.contains(slope)) return true;
					slopes.add(slope);
				}
			}
		}
		return false;
	}

}

