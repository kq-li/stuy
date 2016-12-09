import java.util.*;
import java.io.*;
public class Tricky {
    public static void main(String[] args) throws FileNotFoundException {
        // Before submitting, make sure the main method hasn't been changed!
        Scanner sc = new Scanner(new FileReader("TrickyIN.txt"));
        while(sc.hasNext()) {
            int length = sc.nextInt();
            Iterable<Integer> output = findTriples(length);
            String s = "[";
            boolean isFirstElement = true;
            for(Integer i : output) {
                if(isFirstElement) {
                    s += i;
                    isFirstElement = false;
                }
                else {
                    s += ", "+ i;
                }
            }
            s += "]";
            System.out.println(s);   
        }
        sc.close();
    }
    
    public static Iterable<Integer> findTriples(int hypotenuse) {
        /** Implement this method.
         
            This method returns a list containing all possible configurations of the other two
            sides that are of positive integer length. Output each
            configuration as a separate element in a list in the format a b
            where a is in ascending order and b is in descending order in
            respect to the other configurations.
             
            hypotenuse - the length of the aqueduct you want to construct.
         
        **/ 
        return null;
    }
}
