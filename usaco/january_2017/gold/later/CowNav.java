import java.util.*;
import java.io.*;

public class CowNav {
  public static int solve(State[] initial) {
    Queue<State[]> queue = new LinkedList<State[]>();
    Queue<State[]> current = new LinkedList<State[]>();
    
    int depth = 0;
    boolean solved = false;

    queue.add(initial);

    while (!solved) {
      while (!queue.isEmpty()) {
        State[] states = queue.remove();
        current.add(states);
      }

      while (!current.isEmpty()) {
        State[] states = current.remove();

        if (states[0].done() && states[1].done()) {
          // System.out.println("Up parent chain:");
          // System.out.println(states[0].parentChain());
          // System.out.println("Right parent chain:");
          // System.out.println(states[1].parentChain());
          solved = true;
          return depth;
        }

        State[] forward = {states[0].forward(), states[1].forward()};
        State[] left = {states[0].turnLeft(), states[1].turnLeft()};
        State[] right = {states[0].turnRight(), states[1].turnRight()};
        queue.add(forward);
        queue.add(left);
        queue.add(right);
      }

      depth++;
    }

    return -1;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
    PrintWriter out = new PrintWriter("cownav.out");

    int N = Integer.parseInt(in.readLine());
    int[][] grid = new int[N][N];
    String line;

    for (int i = 0; i < N; i++) {
      line = in.readLine();

      for (int j = 0; j < N; j++) {
        String current = line.substring(j, j + 1);

        if (current.equals("E")) {
          grid[i][j] = 0;
        } else {
          grid[i][j] = 1;
        }
      }
    }

    boolean test = false;

    if (test) {
      State state = new State(null, N, grid, N - 1, 0, 1, 0);
      String[] actions = {"forward", "right", "forward", "forward", "left", "forward", "left", "forward", "forward"};
      System.out.println(state);
    
      for (String action : actions) {
        switch (action) {
        case "forward":
          state = state.forward();
          break;

        case "left":
          state = state.turnLeft();
          break;

        case "right":
          state = state.turnRight();
          break;
        }

        System.out.println(state);
      }

      System.out.println("Parent chain:");
      System.out.println(state.parentChain());
    } else {
      State[] initial = {new State(null, N, grid, N - 1, 0, 0, -1),
                       new State(null, N, grid, N - 1, 0, 1, 0)};

      int ans = solve(initial);

      out.println(ans);
      System.out.println(ans);
    }
    
    in.close();
    out.close();
  }
}

class State {
  State parent;
  int size;
  int startX, startY, curX, curY, endX, endY;
  int dirX, dirY;
  int[][] grid;

  public State(State parent, int size, int[][] grid, int curX, int curY, int dirX, int dirY) {
    // this.parent = parent;
    this.size = size;
    this.curX = curX;
    this.curY = curY;
    this.dirX = dirX;
    this.dirY = dirY;
    this.endX = 0;
    this.endY = size - 1;
    
    this.grid = grid;
  }

  public State forward() {
    int newX = curX + dirY;
    int newY = curY + dirX;

    if (newX >= 0 && newX < size && newY >= 0 && newY < size &&
        grid[newX][newY] == 0) {
      return new State(this, size, grid, newX, newY, dirX, dirY);
    } else {
      return new State(this, size, grid, curX, curY, dirX, dirY);
    }
  }

  public State turnLeft() {
    return new State(this, size, grid, curX, curY, dirY, -dirX);
  }

  public State turnRight() {
    return new State(this, size, grid, curX, curY, -dirY, dirX);
  }

  public boolean done() {
    return curX == endX && curY == endY;
  }

  public String parentChain() {
    State state = this;
    String ret = "";

    while (state != null) {
      ret = state + "\n" + ret;
      state = state.parent;
    }

    return ret;
  }
  
  public String toString() {
    String ret = "";

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        String current = "0";
        
        if (i == curX && j == curY) {
          if (dirX > 0) {
            current = ">";
          } else if (dirX < 0) {
            current = "<";
          } else if (dirY > 0) {
            current = "v";
          } else if (dirY < 0) {
            current = "^";
          } 
        } else if (grid[i][j] == 0) {
          current = "E";
        } else {
          current = "H";
        }
        
        ret += current + " ";
      }

      ret += "\n";
    }

    return ret;
  }
}
