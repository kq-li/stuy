import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CopsRunIntoEachOther {
	// Do not modify this method.
  public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("CopsRunIntoEachOtherIN.txt"));
        int[][] input = new int[1][1];
        long[] output = new long[10];
        int counter1 = 2;
        int counter2 = 0;
        int counter3 = 0;
        int width = 0;
        int height = 0;
        while (sc.hasNextInt()) {
            if (counter1 == 2) {
                width = sc.nextInt();
                counter1--;
            } else if (counter1 == 1) {
                height = sc.nextInt();
                counter1--;
                input = new int[width][height];
            } else {
                input[counter2 / height][counter2 % height] = sc.nextInt();
                counter2++;
                if (counter2 == width * height) {
                    output[counter3] = countEvenArrays(input);
                    counter3++;
                    counter1 = 2;
                    counter2 = 0;
                }
            }
        }
        output[counter3] = countEvenArrays(input);
        for (int i = 0; i < 10; i++) {
            System.out.println(output[i]);
        }
    }
    // Fill out this method
    public static long countEvenArrays(int[][] initialArray) {
    	return 0L;
    }

}
