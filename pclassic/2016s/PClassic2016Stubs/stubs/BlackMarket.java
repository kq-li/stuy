import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BlackMarket {
  //Fill out the body of this method
  public static boolean freeMoney(double[][] prices){
    return false;
  }
	
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader b = new BufferedReader(new FileReader("BlackMarketIN.txt"));

    while(b.ready()){
      String in = b.readLine();
      String[] inArr = in.split(",");
      double[][] prices = new double[inArr.length][inArr.length];
			
      for (int i = 0; i < inArr.length; i++){
        String[] row = inArr[i].split(" ");
        for(int j = 0; j < inArr.length; j++){
          prices[i][j] = Double.parseDouble(row[j].trim());
        }
      }
      System.out.println(freeMoney(prices));

    }
		
  }

}
