import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CipherCracking {

  public static void main(String[] args) throws Exception {
		
    Scanner fileInput = new Scanner(new File("CipherCrackingIN.txt"));
		 	
    int i = 0;
    while(fileInput.hasNext()) {
	 
      String inputStr = fileInput.nextLine().trim();
	             
      System.out.println(closestVowel(inputStr));
	             
      i++;
    }
    fileInput.close();
  }

  //Fill out the body of this method
  public static String closestVowel(String str) {
    return "";
  }
	
}
