import java.util.*;
import java.io.*;

class Greeting {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("GreetingIN.txt"));
		while (sc.hasNext()) {
			String name = sc.nextLine();
			greet(name);
		}
	}

	/**
	 *	name: the name of the person we want to greet
	 *	You must fill this out. 
	 */ 
	public static void greet(String name) {
		
	}
}