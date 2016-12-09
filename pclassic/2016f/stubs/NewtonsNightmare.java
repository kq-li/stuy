import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NewtonsNightmare {

    public static void main(String[] args) throws FileNotFoundException {
        // Before submitting, make sure the main method hasn't been changed!
        Scanner sc = new Scanner(new FileReader("NewtonsNightmareIN.txt"));
        while(sc.hasNext()) {
            String[] firstLine = sc.nextLine().split(" ");
            int numLines = Integer.parseInt(firstLine[0]);
            double targetX = Double.parseDouble(firstLine[1]);
            
            int[] heights = new int[numLines];
            double[] indexes = new double[numLines];
            
            for(int i = 0; i < numLines; i++) {
            	String[] curLine = sc.nextLine().split(" ");
            	heights[i] = Integer.parseInt(curLine[0]);
            	indexes[i] = Double.parseDouble(curLine[1]);
            }
            
            System.out.println(findAngle(heights, indexes, targetX));
        }
        sc.close();
    }
    

    // fill out the body of this method:
    // heights and indexes are arrays of heights and corresponding refractive indices 
    // the target x value is given by the argument targetX and the target y value is
    // given by the sum of the heights, as described in the problem.
    // Find the value for the double variable angle which directs the light to the
    // target_x, target_y position
    public static double findAngle(int[] heights, double[] indexes, double targetX) {
        double angle = 0.0;
        return Math.round(angle * 10000.0)/10000.0;
    }
}
