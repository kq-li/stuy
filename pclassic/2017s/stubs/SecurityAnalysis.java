import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SecurityAnalysis {
	
	//Modify this method
	public static String modifiedLcs(String letter, String string1, String string2) {
		return "";
	}

	//Do not modify anything below this
	public static void main(String[] args) throws FileNotFoundException {
		
		 Scanner fileInput = new Scanner(new File("SecurityAnalysisIN.txt"));
        
	        while(fileInput.hasNext()) {
	        	
	            String letter = fileInput.nextLine();
	            String string1 = fileInput.nextLine();
	            String string2 = fileInput.nextLine();
	            
	            System.out.println(modifiedLcs(letter, string1, string2));
	             
	        }
	        fileInput.close();
	}
	
}
