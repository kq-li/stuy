import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class CarChase {

	public static void main(String[] args) throws IOException{
		String plate = "";

		Scanner in = new Scanner(new File("CarChaseIN.txt"));
		while(in.hasNext()){
			plate = in.nextLine();
			System.out.println(validPlate(plate));
		} 
	      
	}
	
	public static int validPlate(String license){
		int [] plateNum = new int[10];
		int numCounter = 0;
		int total = 0;
		
		for(int i = 0; i < license.length(); i++) {
			String temp = license.substring(i, i + 1);
			if (!temp.equals("-")){
				numCounter++;
				plateNum[numCounter - 1] = Integer.parseInt(temp);
			}	
		}

		int mult = 10;
		for (int i = 0; i < 9; i++) {
			total += (plateNum[i] * mult);
			mult--;
		}
		int last = 11 - total%11;
    if (last == 11) {
      last = 0;
    }
				
		if ((last) == plateNum[9])
			return -1;
		else
			return last;
	}
}
