import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class EscapeVelocity {

	public static void main(String args[]) throws FileNotFoundException {
		
		Scanner fileInput = new Scanner(new File("EscapeVelocityIN.txt"));
		
		while(fileInput.hasNext()) {

			double mass = fileInput.nextDouble();
			double radius = fileInput.nextDouble();
			
			System.out.println(findEscapeVelocity(mass, radius));
			
		}
		fileInput.close();
	}

	public static int findEscapeVelocity(double m, double r) {
		
		double velocity = Math.sqrt((2*m)/r);
		int intVelocity = (int)velocity;
		
		return intVelocity;
	}
}

