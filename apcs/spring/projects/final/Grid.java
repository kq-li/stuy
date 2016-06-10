import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Grid {
  private int _radius, _hexCount;
  private double _centerX, _centerY, _hexRadius;
  private Hex[][][] _grid;
  private ArrayList<Hex> _list;

  public Grid(int radius, int width, int height) {
    _radius = radius;
    _hexCount = numHexes(radius);
    _grid = new Hex[2 * radius + 1][2 * radius + 1][2 * radius + 1];
    _list = new ArrayList<Hex>();
    _centerX = width / 2.0;
    _centerY = height / 2.0;
    _hexRadius = (double) (Math.min(width, height) * 0.75) / (4 * radius);
    
    for (int x = 0; x < 2 * radius + 1; x++) {
      for (int y = 0; y < 2 * radius + 1; y++) { 
        for (int z = 0; z < 2 * radius + 1; z++) { 
          if (isHex(x, y, z)) {
            double hexCenterX = _centerX + (x - radius) * 1.5 * _hexRadius;
            double hexCenterY = _centerY + (y - z) * Math.sqrt(3) / 2 * _hexRadius;
            _grid[x][y][z] = new Hex(x - _radius, y - _radius, z - _radius,
                                     hexCenterX, hexCenterY, _hexRadius);
            _list.add(_grid[x][y][z]);
          }
        }
      }
    }
  }

  public int numHexes(int r) {
    if (r == 1)
      return 1;

    return 6 * r - 6 + numHexes(r - 1);
  }

  public boolean isHex(int x, int y, int z) {
    return ((x >= 0 && x < 2 * _radius + 1) &&
            (y >= 0 && y < 2 * _radius + 1) &&
            (z >= 0 && z < 2 * _radius + 1)) && x + y + z == 3 * _radius;
  }

  public int getRadius() {
    return _radius;
  }

  public Hex getHex(int x, int y, int z) {
    return _grid[x][y][z];
  }

  public double getHexRadius() {
    return _hexRadius;
  }
  
  public double[] cubeToPixel(int x, int y, int z) {
    double[] ret = new double[2];
    ret[0] = _centerX + x * 1.5 * _hexRadius;
    ret[1] = _centerY + (y - z) * Math.sqrt(3) / 2 * _hexRadius;
    return ret;
  }

  public int[] pixelToCube(double xcor, double ycor) {
    int[] ret = new int[3];
    ret[0] = (int) ((xcor - _centerX) / (1.5 * _hexRadius));
    ret[1] = (int) ((ycor - _centerY) * (2 * _hexRadius / Math.sqrt(3)) - 2 * ret[0]);
    ret[2] = -ret[0] - ret[1];
    return ret;
  }
  
  public Hex whichHex(double xcor, double ycor) {
    for (Hex hex : _list)
      if (hex.contains(xcor, ycor))
        return hex;

    return null;
  }

  public ArrayList<Hex> gridToAL() {
    return _list;
  }

  public PriorityQueue<Hex> gridToPQ(Hex center) {
    Comparator<Hex> c = new HexComparator(center);
    PriorityQueue<Hex> ret = new PriorityQueue<Hex>(_hexCount, c);

    for (Hex hex : _list) 
      ret.offer(hex);

    return ret;
  }
}
