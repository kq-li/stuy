public class HotPotato {
  public static void main(String[] args) {
    Queue<String> Q = new NodeQueue<String>();
    String[] kids = {"Abe", "Ben", "Carl", "Dean"};

    for (String kid : kids)
      Q.enqueue(kid);
    
    System.out.println(Q);
    
    while (Q.size() > 1) {
      String loser = null;
      double probability = 1.0 / Q.size();

      while (loser == null) {
        String kid = Q.dequeue();

        if (Math.random() < probability)
          loser = kid;
        else
          Q.enqueue(kid);
      }
      
      System.out.println(loser + " lost!");
    }

    System.out.println(Q.front() + " won!");
  }
}
