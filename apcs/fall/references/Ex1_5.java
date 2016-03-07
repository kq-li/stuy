public class Ex1_5{

    // 1 2 2 1 5 1 1 7 7 7 7 1 
    // Longest Run: 4 consecutive 7s

    public static void main(String [] args){
	int len, maxLen, val, current, next;
	len = maxLen = 0;

	val = current = StdIn.readInt();
	len++;
	while (!StdIn.isEmpty()){
	    next = StdIn.readInt();
	    if (current != next){
		if (len > maxLen){
		    val = current;
		    maxLen = len;
		}
		len = 0;
		current = next;
	    }
	    len++;
	}
	System.out.println();
	System.out.println("Longest Run: " + maxLen + " consecutive " + val + "s");
    }
}
