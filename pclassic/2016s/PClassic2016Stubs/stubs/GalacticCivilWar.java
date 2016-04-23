import java.util.*;
import java.io.*;
class GalacticCivilWar {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new FileReader("GalacticCivilWarIN.txt"));
    while (sc.hasNext()) {
      int N = sc.nextInt(), K = sc.nextInt();
      int a[] = new int[N];
      for (int i = 0; i < N; i++) {
        a[i] = sc.nextInt();
      }
      int ans = galactic(a, K);
      System.out.println(ans);
    }
  }

  //Fill out the body of this method
  public static int galactic(int[] a, int K) {
    return 0;
  }
}
