public class Ex1_6{

    // 1 2 2 1 5 1 1 7 7 7 7 1 1 1 1 1 1 1 1 1
    // 1 2 1 5 1 7 1

    public static void main(String [] args){
	int current, next;
	String ans = "";

	current = StdIn.readInt();
	while (!StdIn.isEmpty()){
		next = StdIn.readInt();
	    if (current != next){
		ans += current + " ";
		current = next;
	    }
	}
	ans += current;
	System.out.println("\n" + ans);
      


    }


}
