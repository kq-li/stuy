import java.util.ArrayList;

public class DuckDuckGoose {

  private CLinkedList _circle;

  // *** Question 4 ****
  // Transfer the names from the ArrayList into a CLinkedList.
  public DuckDuckGoose(ArrayList<String> names) {
    _circle = new CLinkedList();

    for (String name : names)
      _circle.add(name);
  }

  // *** Question 5 ****
  // Returns a randomly selected child to be it.
  // The child is removed from the circle and its node is returned.
  public CNode chooseIt() {
    int r = (int) (Math.random() * _circle.size());

    for (int i = 0; i < r; i++)
      _circle.advance();

    return _circle.remove();
  }

  public String toString() {
    return _circle.toString();
  }
  
  public static void main(String [] args){
    String[] n = {"Abe", "Cal", "Jen", "Eve", "Pam", "Vern", "Joe", "Quin", "Yuki"};

    // *** Question 1 *** 
    // Declare and instantiate an ArrayList of strings.
    ArrayList<String> L = new ArrayList<String>();
    
    // *** Question 2 ***
    // Copy the items of the String [] into an ArrayList of Strings.
    for (String s : n)
      L.add(s);
    
    // *** Question 3 ****
    // Declare and instantiate a DuckDuckGoose object.
    // The constructor's argument will be an ArrayList of Strings.
    DuckDuckGoose d = new DuckDuckGoose(L);
    System.out.println(d);
    System.out.println(d.chooseIt());
    System.out.println(d);
  }
}
