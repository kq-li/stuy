import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FaultyCommandSystem {
	
	public static int getDisplacement(ArrayList<Integer> inputArray) {
		
		int displacement = 0;
		
		for(int numMinMax = 1; numMinMax > 0;) {
			ArrayList<Integer> newArray = new ArrayList<Integer>();
			numMinMax = 0;
			
			if(inputArray.size() >= 3) {
				newArray.add(inputArray.get(0));
				for(int i = 1; i < inputArray.size() - 1; i++) {
					if(inputArray.get(i) > inputArray.get(i-1) && inputArray.get(i) > inputArray.get(i+1)) {
						numMinMax++;
						displacement += inputArray.get(i);
					} else if(inputArray.get(i) < inputArray.get(i-1) && inputArray.get(i) < inputArray.get(i+1)) {
						numMinMax++;
						displacement -= inputArray.get(i);
					} else {
						newArray.add(inputArray.get(i));
					}
				}
				newArray.add(inputArray.get(inputArray.size() - 1));
			}
			
			inputArray = newArray;
		}
		
		return displacement;
	}

	public static void main(String args[]) throws FileNotFoundException {

		Scanner fileInput = new Scanner(new File("FaultyCommandSystemIN.txt"));
		
		while(fileInput.hasNext()) {
			String input = fileInput.nextLine();

			String[] tempArray = input.trim().split("\\s*,\\s*");
			ArrayList<Integer> inputArray = new ArrayList<Integer>();
			for(int i = 0; i < tempArray.length; i++) {
				inputArray.add(Integer.parseInt(tempArray[i]));
			}
			
			int displacement = FaultyCommandSystem.getDisplacement(inputArray);
			
			System.out.println(displacement);
		}
		fileInput.close();
	}
}
