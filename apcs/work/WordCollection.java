import java.util.ArrayList;

public class WordCollection {
  private ArrayList<String> _collection;

  public WordCollection() {
    this._collection = new ArrayList<String>();
  }

  public WordCollection(String[] words) {
    this();
    for (String word : words)
      this.insert(word);
  }

  public int size() {
    return this._collection.size();
  }

  public String get(int k) {
    return this._collection.get(k);
  }
  
  public void insert(String word) {
    for (int i = 0; i < this.size(); i++) {
      if (word.compareTo(this.get(i)) < 0) {
        this._collection.add(i, word);
        return;
      }
    }
    this._collection.add(word);
  }

  public int indexOf(String word) {
    for (int i = 0; i < this.size(); i++)
      if (word.equals(this.get(i)))
        return i;
    return -1;
  }

  public void remove(String word) {
    this._collection.remove(word);
  }

  public String toString() {
    return this._collection.toString();
  }

  public static int occurrences(WordCollection wc, String word) {
    int ret = 0;
    for (int i = 0; i < wc.size(); i++)
      if (wc.get(i).equals(word))
        ret++;
    return ret;
  }

  public static void removeDuplicates(WordCollection wc, String word) {
    while (occurrences(wc, word) > 1)
      wc.remove(word);
  }     

  public static String mostCommon(WordCollection wc) {
    int maxPos = 0;
    int max = 0;
    for (int i = 0; i < wc.size(); i++) {
      if (occurrences(wc, wc.get(i)) > max) {
        maxPos = i;
        max = occurrences(wc, wc.get(i));
      }
    }
    return wc.get(maxPos);
  }
  
  public static void main(String[] args) {
    String[] words = {"cat", "bat", "dog", "ape", "zebra", "dog", "cat"};
    WordCollection wc1 = new WordCollection();
    WordCollection wc2 = new WordCollection(words);
    System.out.println(wc1);
    System.out.println(wc2);
    wc2.insert("whale");
    System.out.println(wc2);
    System.out.println(occurrences(wc2, "ape"));
    System.out.println(occurrences(wc2, "dog"));
    wc2.remove("cat");
    System.out.println(wc2);
    removeDuplicates(wc2, "dog");
    System.out.println(wc2);
    wc2.insert("dog");
    wc2.insert("dingo");
    wc2.insert("dingo");
    System.out.println(wc2);
    System.out.println(mostCommon(wc2));
  }
}
        
