import java.util.ArrayList;
import java.util.List;

public class FooList {
  private int _fooLength;
  private ArrayList<String> _availableFoos;

  public FooList(int length, String[] foos) {
    this._fooLength = length;
    this._availableFoos = new ArrayList<String>();
    this.fillFooList(foos);
  }

  public boolean found(String key) {
    for (String foo : this._availableFoos)
      if (foo.equals(key))
        return true;
    return false;
  }

  public boolean addFoo(String entry) {
    if (entry.length() == this._fooLength && !this.found(entry)) {
      this._availableFoos.add(entry);
      return true;
    }
    return false;
  }

  public String removeRandomFoo() {
    int r = (int) (Math.random() * this._availableFoos.size());
    return this._availableFoos.remove(r);
  }
  
  public void fillFooList(String[] foos) {
    for (String foo : foos)
      if (foo.length() == this._fooLength)
        this.addFoo(foo);
  }

  public String toString() {
    return this._availableFoos.toString();
  }
  
  public static void main(String[] args) {
    String[] foos = {"aa", "bb", "cc", "dd"};
    FooList L = new FooList(2, foos);
    System.out.println(L);
    L.addFoo("ee");
    System.out.println(L);
    L.removeRandomFoo();
    System.out.println(L);
  }
}
