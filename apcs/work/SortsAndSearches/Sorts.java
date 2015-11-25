public class Sorts {
  public static void shuffle(Object[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++){
	    int r = (int) (Math.random() * (N - i)) + i;
	    Object temp = a[i];
	    a[i] = a[r];
	    a[r] = temp;
    }
  }
}
