public abstract class Animal {
  protected Ecosystem _ecosystem;
  protected int _position;
  protected boolean _moved;

  public Animal(Ecosystem ecosystem, int position) {
    _ecosystem = ecosystem;
    _position = position;
    _moved = false;
  }

  public void move(int d) {
    int dest = _position + d;
    _moved = true;
    
    if (dest < 0 || dest >= _ecosystem._river.length || dest == _position) 
      return;
    
    Animal other = _ecosystem._river[dest];
    
    if (other == null) {
      _ecosystem._river[_position] = null;
      _ecosystem.fillEmpty(_position);
      _position = dest;
      _ecosystem._river[_position] = this;
      _ecosystem.emptyEmpty(_position);
    } else {
      if (this instanceof Bear && other instanceof Bear ||
          this instanceof Fish && other instanceof Fish) {
        reproduce();
      } else if (this instanceof Fish) {
        _ecosystem.emptyEmpty(_position);
        _ecosystem._river[_position] = null;
      } else {
        _ecosystem.emptyEmpty(dest);
        _ecosystem._river[dest] = null;
      }
    }
  }

  public abstract Animal reproduce();
  
  
}
