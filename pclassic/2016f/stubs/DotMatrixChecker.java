import java.util.*;
import java.io.*;


public class DotMatrixChecker {
    
  // do not modify main code
  public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException
  {
    Scanner sc = new Scanner(new FileReader("DotMatrixCheckerIN.txt"));
        
    while (sc.hasNext()) {
      System.out.println(makeLogo(Integer.parseInt(sc.nextLine())));
    }
        
        
  }

  // Fill out the body of this method
  // n is an integer indicatind the desired height
  public static String makeLogo(int n)
  {
    // remember to add newline characters at the end of each row the dotmatrix printer prints
    return ""; 
  }
}
