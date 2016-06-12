import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class HexComparator implements Comparator {
  protected Hex _center;

  public HexComparator(Hex center) {
    _center = center;
  }

  public int compare(Object o1, Object o2) throws ClassCastException {
    return (_center.distanceTo((Hex) o1) - _center.distanceTo((Hex) o2));
  }
}
