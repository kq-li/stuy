public class SLinkedList{

  private Node _head, _tail;
  private int _size;

  public SLinkedList(){
    _head = _tail = null;
    _size = 0;
  }
  // O(1)
  public void addFirst(String value){
    _head = new Node(value,_head);
    if (_size == 0) _tail = _head;
    _size++;
  }
  // O(1)
  public void addLast(String value){
    Node newLast = new Node(value,null);
    if (_size == 0) _head = _tail = newLast;
    else{
	    _tail.setNext(newLast);
	    _tail = newLast;
    }
    _size++;
  }

  public int size(){
    return _size;
  }
  // O(1)
  public String   removeFirst(){
    if (_size == 0) throw new IllegalStateException(); 
    String ans = _head.getValue();
    _head = _head.getNext();
    _size--;
    if (_size == 0) _tail = null;
    return ans;
  }

  public String removeLast(){
    if (_size <= 1) return removeFirst();
    String ans = _tail.getValue();
    Node current = _head;
    while (current.getNext() != _tail) 
	    current = current.getNext();
    current.setNext(null);
    _tail = current;
    _size--;
    return ans;

  }

  // O(n)
  public String toString(){
    String ans = "";
    Node current = _head;
    while (current != null){
	    ans += current.getValue() + " ";
	    current = current.getNext();
    }
    return ans;
  }
  // O(1)
  // postcondition: throws an  Illegal State Exception if the list is empty
  //                otherwise returns the first value.
  public String getFirst(){   
    if (size() == 0) throw new IllegalStateException();
    return _head.getValue();
  }

  // postcondition: throws an Illegal State Exception if the list is empty
  //                otherwise returns the last value.
  public String getLast(){
    if (size() == 0) throw new IllegalStateException();
    return _tail.getValue();
  }

  //O(n)
  // postcondition: throw an exception if i < 0 or i >= size.
  //                if L = [a,b,c,d]
  //                L.get(0) returns a
  //                L.get(1) returns b
  //                L.get(2) returns c
  //                L.get(3) returns d
  public String get(int i){ 
    if (i < 0 || i >= size()) throw new IllegalStateException();
    Node t = _head;
    for (int a = 0; a < i; a++){
	    t = t.getNext();
    }
    return t.getValue();
  }
  // O(n)
  /* Write the method append(SLinkedList L) that concatenates two lists.
     Here's an example, 
     S = [ a,b]
     M = [ c,d,e]
     S.append(M) -> [a,b,c,d,e]
     M.append(S) -> [c,d,e,a,b]
     Neither S nor M should be modified (no side effects). 
  */

  public SLinkedList append(SLinkedList L){
    SLinkedList r = new SLinkedList();
    Node current = this._head;
    int i = 0;
    while (i < size()){
	    r.addLast(current.getValue()); // O(1)
	    i++;
	    current = current.getNext();
    }
    i = 0;
    current = L._head;
    while (i < L.size()){
	    r.addLast(current.getValue()); // O(1)
	    i++;
	    current = current.getNext();
    }
    return r;
  }
  // version #1
  // O(n)
  public void reverse(){
    reverseNode(_head, _head.getNext());
    Node temp = _head;
    _head = _tail;
    _tail = temp;
  }

  private void reverseNode(Node a, Node b){
    if ( b == null) return;
    if ( a == _head) a.setNext(null);
    Node bNext = b.getNext();
    b.setNext(a);
    reverseNode(b,bNext);
  }
  // version #2
  // O(n)
  public void reverse2(){
    if (size() > 1){
	    Node node = _head;
	    _head = _head.getNext();
	    _size--;
	    reverse2();
	    _tail.setNext(node);
	    _tail = node;
	    _tail.setNext(null);
	    _size++;
    }
  }

  // version #3:
  // This version destroys nodes and creates new ones.
  // The previous 2 versions preserve the original nodes.
  public void reverse3(){
    if (size() > 1){
	    String v  = removeFirst() ;  // removeLast();
	    reverse3();
	    addLast(v);  // addFirst(v)
    }

  }
    



  // O(n) 
  // postcondition: returns null if key is not in the list.
  public Node search(String key){   
    Node current = _head;
    while (current != null){
	    if (current.getValue().equals(key))
        return current;
	    current = current.getNext();
    }
    return current;
  }


  // precondition: x and y refer to Nodes in the list
  // postcondition: swaps the nodes (not the just the values)
  public void swap(Node x, Node y){
    if (x == y) return;
    Node prevX = new Node(null,_head);
    while (prevX.getNext() != x && prevX.getNext() != y)
	    prevX = prevX.getNext();
    if ( prevX.getNext() == y){
	    Node t = x;
	    x = y;
	    y = t;
    }
    // x precedes y
    Node prevY = prevX.getNext();
    while ( prevY.getNext() != y)
	    prevY = prevY.getNext();
    if (_head == x) _head = y;
    if (_tail == y) _tail = x;
    prevX.setNext(y);
    prevY.setNext(x);
    Node afterY = y.getNext();
    y.setNext(x.getNext());
    x.setNext(afterY);
  }


  public static void main(String [] args){
    SLinkedList L = new SLinkedList();
    System.out.println(L); // " "
    L.addLast("Bill");
    System.out.println(L); // Bill
    L.addLast("Mary");
    System.out.println(L); // Bill Mary
    L.addLast("Sue");
    System.out.println(L); // Bill Mary Sue  
    /*	while (L.size() > 0){
        System.out.println("removed : " + L.removeLast());
        System.out.println(L);
        }
    */
    L.swap(L.search("Bill"), L.search("Mary"));
    System.out.println("swap Bill and Mary: " + L);
    L.swap(L.search("Sue"), L.search("Mary"));
    System.out.println("swap Sue and Mary: " + L);
    L.swap(L.search("Bill"), L.search("Mary"));
    System.out.println("swap Bill and Mary: " + L);
  }


}
