import java.util.Scanner;
import java.lang.Exception;

public class PigLatin {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String word = scanner.nextLine();
      if (word != null && !word.equals(""))
        System.out.println(word + " -> " + pigLatin(word));
    }
  }

  public static String pigLatin(String word) {
    if (find(word, "aeiou") == 0)
      return word + "yay";
    else if (word.indexOf("y") > 0)
      return word.substring(word.indexOf("y")) +
        word.substring(0, word.indexOf("y")) + "ay";
    else if (find(word, "aeiou") == -1)
      return word.substring(1) + word.substring(0, 1) + "ay";
    else if (find(word, "qu") == 0)
      return word.substring(2) + word.substring(0, 2) + "ay";
    else {
      int index = find(word, "aeiou");
      return word.substring(index) +
        word.substring(0, index) + "ay";
    }
  }

  public static int find(String a, String b) {
    for (int i = 0; i < a.length(); i++)
      if (b.indexOf(a.substring(i, i + 1)) != -1)
        return i;
    return -1;
  }
}
