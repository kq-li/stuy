import java.util.*;
import java.io.*;

public class SharingSpoils
{   
    /** Fill out the body of this method
     * @param S is an integer array, where each integer denotes 
     *         the possible amounts the machine can manufacture
     * @param n is an integer indicating the total amount of gold to be distributed
     */
    public static long countWays(int S[], int n)
    {
        return -1;
    }

    // Do not modify main code
    public static void main(String args[]) throws FileNotFoundException
    {
    	Scanner sc = new Scanner(new FileReader("SharingSpoilsIN.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int arr[] = new int[m];
            for (int i = 0; i < m; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(countWays(arr, n));
        }
    }
}