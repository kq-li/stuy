import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class JediAcademy {
  //Fill out the body of this method
  public static int scoreSummary(int[] scores, int g){		
    return 0;
  }

  public static void main(String[] args) throws IOException {
		
    BufferedReader b = new BufferedReader(new FileReader("JediAcademyIN.txt"));

    while(b.ready()){			
      String in = b.readLine();
      String[] inBreakLast = in.split("--");
			
      int last = Integer.parseInt(inBreakLast[1]);
			
      String[] inArr = inBreakLast[0].split(",");
      int[] scores = new int[inArr.length];
			
      for (int i = 0; i < inArr.length; i++){
        scores[i] = Integer.parseInt(inArr[i]);
      }
			
      System.out.println(scoreSummary(scores, last));
    }
		
    b.close();
				
  }

}
