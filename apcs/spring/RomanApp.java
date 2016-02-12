import java.util.ArrayList;

public class RomanApp {
  public static ArrayList<Roman> populate(int size, int lower, int upper) {
    ArrayList<Roman> x = new ArrayList<Roman>();

    for (int i = 0; i < size; i++)
	    x.add(new Roman((int) (Math.random() * (upper-lower)) + lower));
    
    return x; 
	
  }

  public static Roman min(ArrayList<Roman> L) {
    int pos = 0;

    for (int i = 1; i < L.size(); i++)
	    if (L.get(pos).compareTo(L.get(i)) > 0) 
        pos = i;
    
    return L.get(pos);
  }

  public static Roman sum(ArrayList<Roman> L) {
    int sum = 0;

    for (Roman r : L)
      sum += r.intValue();

    return new Roman(sum);
  }

  public static ArrayList<Roman> filterEvens(ArrayList<Roman> L) {
    ArrayList<Roman> filtered = new ArrayList<Roman>();

    for (Roman r : L)
      if (r.intValue() % 2 == 0)
        filtered.add(r);

    return filtered;
  }

  public static void mapAdd(int n, ArrayList<Roman> L) {
    for (int i = 0; i < L.size(); i++)
      L.set(i, new Roman(L.get(i).intValue() + n));
  }

  public static ArrayList<Integer> toIntArray(ArrayList<Roman> L) {
    ArrayList<Integer> A = new ArrayList<Integer>();

    for (Roman r : L)
      A.add(r.intValue());

    return A;
  }
  
  public static void main(String[] args) {
    ArrayList<Roman> romans = populate(5,2,10);
    System.out.println(romans + " = " + toIntArray(romans));
    Number minValue = min(romans);
    System.out.println("min: " + minValue + " = " + minValue.intValue());
    Number total = sum(romans);
    System.out.println("sum: " + total + " = " + total.intValue());
    ArrayList<Roman> filtered = filterEvens(romans);
    System.out.println(filtered + " = " + toIntArray(filtered));
    mapAdd(1, romans);
    System.out.println(romans + " = " + toIntArray(romans));
  }
}
