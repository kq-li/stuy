public class Sorts{


    public static void shuffle(Object [] x ){
	int N = x.length;
	for (int i = 0; i < N; i++){
	    int r = (int) (Math.random() *(N - i)) + i;
	    Object temp = x[i];
	    x[i] = x[r];
	    x[r] = temp;
	}

    }



}
