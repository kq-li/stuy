public class Cult {
  public static void main(String[] args) {
    int M = Integer.parseInt(args[0]);
    int k = Integer.parseInt(args[1]);
    CLinkedList cult = new CLinkedList();

    for (int i = 1; i <= M; i++) {
      cult.add(i + "");
      cult.advance();
    }

    System.out.println(cult);
    
    while (cult.size() > 1) {
      for (int i = 0; i < k - 1; i++)
        cult.advance();
      
      System.out.println("poisoned: " + cult.remove());
    }

    System.out.println("survivor: " + cult.getCursor());
  }
}
