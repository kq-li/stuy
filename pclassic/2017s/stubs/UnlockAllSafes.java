import java.util.*;
import java.io.*;

public class UnlockAllSafes {
    
    /**
     * This is for you to fill out. 
     * @param matrix, a 2D array of integers
     * @return a list of integers in the described order. 
     * 
     */
    public static int[] spiralOrder(int[][] matrix) {
        int[] result = new int[matrix.length * matrix.length];

        return result;
    }

    // Do not modify main code
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("UnlockAllSafesIN.txt"));
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int[][] matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
            int[] result = spiralOrder(matrix);
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }

}