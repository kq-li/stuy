import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

public class GUI {
  protected double _xcor, _ycor, _buttonSize;
  protected String _message;
  protected Button[] _buttons;

  public static final String[] BUTTON_IDS = {"check", "move", "attack"};

  public GUI(double xcor, double ycor, double buttonSize) {
    _xcor = xcor;
    _ycor = ycor;
    _buttonSize = buttonSize;
    _buttons = new Button[3];
    _message = "";

    for (int i = 0; i < 3; i++) 
      _buttons[i] = new Button(xcor,
                               ycor + 1.5 * (i - 1) * buttonSize,
                               buttonSize,
                               0.1 * buttonSize,
                               BUTTON_IDS[i]);
  }

  // Finds button containing the given coordinates
  public Button whichButton(double xcor, double ycor) {
    for (Button b : _buttons)
      if (b.contains(xcor, ycor))
        return b;

    return null;
  }

  public Button getButton(String id) {
    for (Button b : _buttons) 
      if (b.getID().equals(id))
        return b;

    return null;
  }

  public Button[] getButtons() {
    return _buttons;
  }

  public String getMessage() {
    return _message;
  }

  public void appendMessage(String s) {
    _message += s + "\n";
  }

  public void clearMessage() {
    _message = "";
  }

  public void setMessage(String s) {
    _message = s + "\n";
  }
}

