public class Gcd{

    public static void main(String [] args){
	int x = Integer.parseInt(args[0]);
	int y = Integer.parseInt(args[1]);
    
	int t = x;
	x = Math.max(x,y);
	y = Math.min(t,y);

	while (x % y != 0){
	    t = y;
	    y = x % y; 
	    x = t;
	    // x > y
	}
	System.out.println("gcd: " + y);
    }

}
