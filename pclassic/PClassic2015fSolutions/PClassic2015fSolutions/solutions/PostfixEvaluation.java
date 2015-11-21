import java.util.*;
import java.io.File;
import java.io.IOException;
public class PostfixEvaluation{
		
	public static void main(String[] args) throws IOException{
		String expression = ""; 
	    Scanner in = new Scanner(new File("PostfixEvaluationIN.txt"));
		while(in.hasNext()){
			expression = in.nextLine();
			
			System.out.printf("%.1f\n", solve(expression));
		}
	}
	
	public static double solve (String expression){
		Stack<Double> pancakes= new Stack<Double>();
		
		for (int i=0; i<expression.length(); i++){
			
			String syrup= expression.substring(i, i+1);		
			if (syrup.equals("1") || syrup.equals("2") || 
					syrup.equals("3") || syrup.equals("4") || 
					syrup.equals("5") || syrup.equals("6") ||
					syrup.equals("7") || syrup.equals("8") || 
					syrup.equals("9") || syrup.equals("0")){
				double butter = Integer.parseInt(syrup);
				pancakes.push(butter);
			}
			
			else if (syrup.equals("+"))
				pancakes.push(pancakes.pop()+pancakes.pop());
			
			
			else if (syrup.equals("-"))
				pancakes.push(-(pancakes.pop()-pancakes.pop()));
			
			else if (syrup.equals("*"))
				pancakes.push(pancakes.pop() * pancakes.pop());
			
			else if (syrup.equals("/"))
				pancakes.push(((double)(1/pancakes.pop()) * (pancakes.pop())) );
		}
		
		return pancakes.pop();
	}
}
