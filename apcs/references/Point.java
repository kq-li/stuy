//Create the data type Point that represents a pair of integers (x,y).
public class Point{
    // instance variables
    private int _x, _y;

    // default constructor
    //  Requirements
    //  - no return type
    //  - has the same name as the class
    //  - defines the instance variables
    //  - parameter list is empty
    public Point(){
	_x = _y = 0;
    }
    public Point (int x, int y){
	move(x,y);
    }

    public Point (Point other){
	this(other.getX(), other.getY());
    }

    // accessor methods : provies read only access to the state of the 
    //                   object
    public int getX(){
	return _x;
    }


    public int getY(){
	return _y;
    }

    //postcondition: returns a copy of the point at the same location
    public Point getLocation(){
	return new Point(this);
	//return new Point(getX(), getY());
	//return new Point(_x, _y);
    }
    // postcondition: use StdDraw.java to draw the point. 
    //                Use a black circle to represent the point.
    public void draw(){
	StdDraw.filledCircle(getX(), getY(), 0.25);
	//StdDraw.filledCircle(_x, _y, 0.25);

    }

  
    // modifier methods : changes the state of an object.
    public void move(int x, int y){
	_x = x;
	_y = y;
    }
    // postcondtion: translates the point by dx and dy
    //              dx is the change in x
    //              dy is the change in y
    public void translate(int dx, int dy){
	// version 1
	//	_x += dx;
	//     _y += dy;
	// version 2
	//     move(_x + dx, _y + dy);
	// version 3
	move(getX() + dx, getY() + dy);
    }
    // preconditon: other != null
    // postcondition: sets the location of the point to the specified 
    //                location
    public void setLocation(Point other){
	move(other.getX(), other.getY());
	// move(other._x, other._y);
    }


    // overwritten methods: changes the behavior of the inherited methods
    public String toString(){
	return "(" + _x + ", " + _y + ")"; 
        // return "(" + getX() + ", " + getY() + ")";
    }
    // postconditon: return true if the points
    // have equal x and y coordinates.
    public boolean equals(Object other){
	/*
	  if (other instanceof Point){
	  Point other2 = (Point)other;
	  return getX() == other2.getX() && getY() == other2.getY();
	  }
	  return false;
	*/


	return this == other || (other instanceof Point) &&
	    getX() == ((Point)other).getX() &&
	    getY() == ((Point)other).getY();

    }

   
    public static void main(String [] args){
	Point a = new Point();
	Point b = new Point(3,5);
	Point c = new Point();
	/*
	StdDraw.setXscale(-10,10);
	StdDraw.setYscale(-10,10);
	System.out.println(a);
	System.out.println(b);
	System.out.println(c);
	a.draw();
	b.draw();
	c.draw();
	*/
	System.out.println(a.equals(a)); // true
	System.out.println(a.equals(c)); // true
	System.out.println(a.equals(b)); // false
	System.out.println(a.equals(null)); // false
	System.out.println(a.equals("hi")); // false


	
    }

}

