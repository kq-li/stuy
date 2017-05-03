import java.util.*;
import java.io.*;
 
public class MakingProfit {
    // Do not modify main code.
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("MakingProfitIN.txt"));
        while(sc.hasNextLine()) {
            int row = Integer.parseInt(sc.nextLine());
            int col = Integer.parseInt(sc.nextLine());
            int[][] matrix = new int[row][col];
            for(int i = 0; i < matrix.length; i++) {
                String line = sc.nextLine();
                String[] elements = line.split(" ");
                for(int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = Integer.parseInt(elements[j]);
                }
            }
            String output = findMaxProfit(matrix);
         System.out.println(output);
        }
        sc.close();
    }
    /** Fill out this method.
     *  @param a is an 2D array where the rows represent the days of the week, the columns represent the number of heists 
     *  for each day and the value represents the amount of money that was stolen that day
    **/
    public static String findMaxProfit(int[][] a) {
        return null;
    }
}