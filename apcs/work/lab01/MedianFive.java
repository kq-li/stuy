public class MedianFive {
  public static void main(String[] args) {
    int[] n = new int[5];
    for (int i = 0; i < 5; i++) {
      n[i] = Integer.parseInt(args[i]);
    }
    for (int i = 0; i < 4; i++) {
      for (int j = i + 1; j < 5; j++) {
        if (n[i] > n[j]) {
          int temp = n[i];
          n[i] = n[j];
          n[j] = temp;
        }
      }
    }
    System.out.println(n[2]);
  }
}
