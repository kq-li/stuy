import java.util.*;
import java.io.*;
public class LaserBeamVault {
    public static void main(String[] args) throws FileNotFoundException {
        // Before submitting, make sure the main method hasn't been changed!
        Scanner sc = new Scanner(new FileReader("LaserBeamVaultIN.txt"));
        while(sc.hasNext()) {
            int N = sc.nextInt();
            int[] xCoords = new int[N];
            int[] yCoords = new int[N];
            for (int i = 0; i < N; i++) {
              xCoords[i] = sc.nextInt();
              yCoords[i] = sc.nextInt();
            }
            System.out.printf("%d\n", countParallelograms(xCoords, yCoords));
        }
        sc.close();
    }

  // Fill out the body of this method
    public static int countParallelograms(int[] xCoords, int[] yCoords) {
        int parallelograms = 0;
        HashMap<String, Integer> sumCounts = new HashMap<String, Integer>();
        
        for(int i = 0; i < xCoords.length; i++)
        {
        	for(int j = i + 1; j < xCoords.length; j++)
        	{
        		int xSum = xCoords[i] + xCoords[j];
        		int ySum = yCoords[i] + yCoords[j];
        		String s = "(" + xSum + "," + ySum + ")";
        		if(sumCounts.containsKey(s)) sumCounts.put(s, sumCounts.get(s) + 1);
        		else sumCounts.put(s, 1);
        	}
        }
        
        for(String s : sumCounts.keySet())
        {
        	int c = sumCounts.get(s);
        	parallelograms += c * (c - 1)/2;
        }
        
        return parallelograms;
    }
}
