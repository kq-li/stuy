import java.util.ArrayList;

public class InsertionSort {
  public static void shuffle(ArrayList<Integer> L) {
    for (int i = 0; i < L.size(); i++)
      swap(L, i, (int) (Math.random() * L.size()));
  }

  public static void swap(ArrayList<Integer> L, int i, int j) {
    L.set(i, L.set(j, L.get(i)));
  }
  
  public static void main(String[] args) {
    int size = Integer.parseInt(args[0]);
    ArrayList<Integer> L = new ArrayList<Integer>();

    for (int i = 0; i < size; i++)
      L.add(i);

    shuffle(L);
    System.out.println("Random: " + L);
    
    for (int i = 0; i < size; i++) {
      System.out.println("Start pass " + i);
      System.out.println(L);
      System.out.println(" Walk down " + L.get(i));
      for (int j = i; j > 0; j--) {
        System.out.println(" " + L);
        
        if (L.get(j) < L.get(j - 1))
          swap(L, j, j - 1);
        else
          break;
      }
    }

    System.out.println("Sorted: " + L);
  }
}
