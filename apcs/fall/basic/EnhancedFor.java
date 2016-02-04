import java.util.ArrayList;
import java.util.List;

public class EnhancedFor {
  public static String longestWord(List<String> L) {
    String longest = "";
    for (String s : L)
      if (s.length() > longest.length())
        longest = s;
    return longest;
  }

  public static void main(String[] args) {
    List<String> L = new ArrayList<String>();
    L.add("hi");
    L.add("bye");
    L.add("kdj");
    System.out.println(L);
    System.out.println(longestWord(L));
  }
}
