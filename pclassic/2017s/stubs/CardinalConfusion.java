import java.io.File;
import java.io.IOException;
import java.util.Scanner;
 
public class CardinalConfusion {
    // Make sure this none of this code has been changed!
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("CardinalConfusionIN.txt"));
        int counter=0;
        int[] output = new int[25];
        while (sc.hasNextInt() && counter<25) {
            int input=sc.nextInt();
            output[counter]=Solutions(input);
            counter++;        
        }
        for (int i = 0; i < 25; i++) {
            System.out.println(output[i]);
        }
    }
    // Fill out this method
    public static int Solutions(int n){
        return 0;       
    }
     
}
