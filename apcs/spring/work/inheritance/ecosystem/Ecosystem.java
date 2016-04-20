import java.util.List;
import java.util.ArrayList;

public class Ecosystem {
  protected Animal[] _river;
  protected List<Integer> _empty;

  public Ecosystem(int size) {
    _river = new Animal[size];
    _empty = new ArrayList<Integer>();

    for (int i = 0; i < size; i++)
      _empty.add(i);
  }
  
  public Ecosystem() {
    this(100);
  }

  protected int emptyEmpty(int value) {
    for (int i = 0; i < _empty.size(); i++)
      if (_empty.get(i) == value)
        return _empty.remove(i);

    return -1;
  }

  protected void fillEmpty(int value) {
    _empty.add(value);
  }    
  
  public void populate(int bears, int fishes) {
    for (int i = 0; i < bears; i++) 
      addBear();

    for (int i = 0; i < fishes; i++) 
      addFish();
  }

  public void move() {
    for (int i = 0; i < _river.length; i++) {
      if (_river[i] != null && !_river[i]._moved) {
        int r = (int) (Math.random() * 3) - 1;
        _river[i].move(r);
      }
    }

    for (int i = 0; i < _river.length; i++) 
      if (_river[i] != null)
        _river[i]._moved = false;
  }

  public Bear addBear() {
    int r = (int) (Math.random() * _empty.size());
    return (Bear) add(new Bear(this, 0), r);
  }

  public Fish addFish() {
    int r = (int) (Math.random() * _empty.size());
    return (Fish) add(new Fish(this, 0), r);
  }

  private Animal add(Animal a, int index) {
    if (_empty.isEmpty() || _river[_empty.get(index)] != null)
      return null;

    a._position = emptyEmpty(_empty.get(index));
    _river[a._position] = a;
    return a;
  }

  public String toString() {
    String ret = "";

    for (int i = 0; i < _river.length; i++) {
      if (_river[i] == null)
        ret += "____ ";
      else
        ret += _river[i].toString() + " ";
    }

    return ret.substring(0, ret.length() - 1);
  }

  public static void main(String[] args) {
    int size, bears, fish, turns;

    if (args.length == 4) {
      size = Integer.parseInt(args[0]);
      bears = Integer.parseInt(args[1]);
      fish = Integer.parseInt(args[2]);
      turns = Integer.parseInt(args[3]);
    } else {
      size = 20;
      bears = 2;
      fish = 5;
      turns = 100;
    }
    
    Ecosystem ecosystem = new Ecosystem(size);
    ecosystem.populate(bears, fish);
    
    for (int i = 0; i < turns; i++)
      ecosystem.move();

    System.out.println(ecosystem);
  }
}


  
