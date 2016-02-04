import java.util.ArrayList;
import java.util.List;


/*
  A FooList is a class that defines a list of strings and a string length.
  All strings in a FooList have the same length. There is no preset number
  of strings that a FooList can hold.

  For example,

  The FooList list1 may contain the strings aa,bb,cc and dd (all strings of 
  length 2).

  The Foolist list2 may contain the strings cat,dog,pig,fox,bat, and eel (all
  strings of length 3).

  An incomplete implementation of the FooList class appears below.
*/


public class FooList {

  // private instance variables declared here
  private int _fooLength;
  private ArrayList<String> _availableFoos;

  // Constructor implementation initializes 
  // _fooLength (the length of the strings in FooList's list) and
  // _availableFoos (FooList's list of strings).
  public FooList(String[] foos,int len) {
    this._fooLength = len;
    this._availableFoos = new ArrayList<String>();
    this.fillFooList(foos);
  }
       


  // PostCondition: Returns true if the string key, is found in the 
  // FooList's list of strings, false otherwise.
  public boolean found(String key) {
    for(String foo : this._availableFoos)
	    if(foo.equals(key))
        return true;
    return false;
  }

  // Adds the string, entry, to FooList's list implementation if
  // it is the correct length and not already in the list. If the
  // string is already in the list or if the string is not the 
  // correct length, it is not added and false is returned.
  public boolean addFoo(String entry) {
    if (this.found(entry) || entry.length() != this._fooLength)
      return false;
    return this._availableFoos.add(entry);
  }

  // Removes and returns a random string entry from FooList's
  // list of strings.
  public String removeRandomFoo() {
    int r = (int)(Math.random() * _availableFoos.size());
    return this._availableFoos.remove(r);
  }

  // Returns the string in position i of FooList's list
  // implementation. The first string is in position 0.
  public String getFoo(int i) {
    return this._availableFoos.get(i);
  }

  // Returns length of a foo.
  public int getLength() {
    return this._fooLength;
  }

  // Fills the FooList's list with the strings in foos
  public void fillFooList(String[] foos) {
    for (int i = 0; i < foos.length; i++)
      this.addFoo(foos[i]);
  }

  // Returns size of FooList.
  public int size() {
    return this._availableFoos.size();
  }

  // Returns true if FooList empty, otherwise false.
  public boolean isEmpty() {
    return size() == 0;
  }

  // Returns true if 2 FooLists have same size and same objects in same order, otherwise false.
  public boolean equals(Object other) {
    return (this == other) || ((other instanceof FooList) &&
                               (((FooList) other).getLength() == this.getLength()) &&
                               (((FooList) other).size() == this.size()) &&
                               (this._availableFoos.equals(((FooList) other)._availableFoos)));
  }

  // Returns string of availableFoos.
  public String toString() {
    return this._availableFoos.toString();
  }



  public static void main(String[] args) {
    String[] foos = {"cat", "bat", "eel", "dog", "cow", "pig", "cat", "crow"};
    FooList fooList = new FooList(foos, 3);
    System.out.println(fooList);
    System.out.println(fooList.size());
    System.out.println(fooList.isEmpty());
    System.out.println(fooList.equals(new FooList(foos, 4)));
  }
    
}



