import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LightsaberLength {

	public static void main(String[] args) throws FileNotFoundException {
		
		 Scanner fileInput = new Scanner(new File("LightsaberLengthIN.txt"));
         
	        while(fileInput.hasNext()) {
	 
	            int power = fileInput.nextInt();
	            int radius = fileInput.nextInt();
	             
	            System.out.println(measure(power, radius));
	             
	        }
	        fileInput.close();
	}
  //Fill out the body of this method
  public static int measure(int power, int radius) {
    int length = 0;
    return length;
  }
}
