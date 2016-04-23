import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;


public class PurePazaak {
  static class Map<A, B> {
    ArrayList<A> aList;
    ArrayList<B> bList;

    public Map() {
      this.aList = new ArrayList<A>();
      this.bList = new ArrayList<B>();
    }

    public static int index(Object key, ArrayList a) {
      for (int i = 0; i < a.size(); i++)
        if (a.get(i).equals(key))
          return i;

      return -1;
    }          
    
    public B atob(A a) {
      return bList.get(index(a, this.aList));
    }

    public A btoa(B b) {
      return aList.get(index(b, this.bList));
    }

    public boolean hasA(A a) {
      return index(a, this.aList) != -1;
    }

    public boolean hasB(B b) {
      return index(b, this.bList) != -1;
    }

    public void add(A a, B b) {
      this.aList.add(a);
      this.bList.add(b);
    }
  }    

  static Map<ArrayList<Integer>, Integer> map;
  
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("PurePazaakIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int n = Integer.parseInt(data[0]);
      int[] cards = new int[n];
      for (int i = 0; i < n; i++) {
        cards[i] = Integer.parseInt(data[i+1]);
      }
      System.out.println(score(cards));
    }
    br.close();
  }

  //Fill out the body of this method
  public static int score(int[] cards) {
    ArrayList<Integer> a = new ArrayList<Integer>();
    map = new Map<ArrayList<Integer>, Integer>();

    for (int i : cards)
      a.add(i);
    
    int ans = score(a);
    System.out.println(map.aList + "\n" + map.bList);
    return ans;
  }

  public static int score(ArrayList<Integer> cards) {
    System.out.println(cards);
    
    if (cards.isEmpty())
      return 0;

    if (cards.size() == 2)
      return Math.max(cards.get(0), cards.get(1));
    
    ArrayList<Integer> a = new ArrayList<Integer>();
    ArrayList<Integer> b = new ArrayList<Integer>();
    ArrayList<Integer> c = new ArrayList<Integer>();
    ArrayList<Integer> d = new ArrayList<Integer>();
    

    for (int i : cards) {
      a.add(i);
      b.add(i);
      c.add(i);
      d.add(i);
    }

    int tempA = a.remove(0);
    int tempB = b.remove(b.size() - 1);
    int tempC = c.remove(0);
    int tempD = d.remove(b.size() - 1);

    a.remove(0);
    b.remove(0);
    c.remove(c.size() - 1);
    d.remove(d.size() - 1);
    
    return Math.max(Math.max(Math.max(tempA + score(a),
                                      tempB + score(b)),
                             tempC + score(c)),
                    tempD + score(d));
    
    /*
    ArrayList<Integer> a = new ArrayList<Integer>();
    ArrayList<Integer> b = new ArrayList<Integer>();

    for (int i : cards) {
      a.add(i);
      b.add(i);
    }

    int scoreA, scoreB;
    int tempA = a.remove(0);
    int tempB = b.remove(b.size() - 1);
    
    
    if (map.hasA(a)) {
      scoreA = map.atob(a);
    } else {
      scoreA = tempA + sum(a) - score(a);
      map.aList.add(a);
      map.bList.add(scoreA);
    }
    
    if (map.hasA(b)) {
      scoreB = map.atob(b);
    } else {
      scoreB = tempB + sum(b) - score(b);
      map.aList.add(b);
      map.bList.add(scoreB);
    }
    
    return Math.max(scoreA, scoreB);
    */
  }

  private static int sum(ArrayList<Integer> a) {
    int ret = 0;
    
    for (int i : a)
      ret += i;

    return ret;
  }
}
