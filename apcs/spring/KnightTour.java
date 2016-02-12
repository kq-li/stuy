public class KnightTour {

  private int[][] _board;
  private int _n;

  // Creates a 8x8 board.
  public KnightTour() {
    this(8);
  }

  // Creates a n x n board
  public KnightTour(int n) {
    _n = n;
    _board = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        _board[i][j] = 0;
  }

  // Attempts to solve the knight's tour starting at position 0,0.
  // Calls solve(int,int).
  public void solve() {
    for (int i = 0; i < _n; i++) {
      for (int j = 0; j < _n; j++) {
        _board[i][j] = 1;
        if (solve(i, j))
          System.out.println(this);

        for (int a = 0; a < _n; a++)
          for (int b = 0; b < _n; b++)
            _board[a][b] = 0;
        
      }
    }
  }
  
  // postcondition: returns true if the knight's tour is completed.
  //                false otherwise
  private boolean solve(int x, int y) {
    /*try {
	    Thread.sleep(100);                 //1000 milliseconds is one second.
    } catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
      }*/
    
    boolean solved = true;

    for (int[] row : _board) {
      for (int square : row) {
        if (square == 0) {
          solved = false;
          break;
        }
      }
    }

    if (solved)
      return true;
    
    int[][] newPos = {{x - 2, y - 1},
                      {x - 1, y - 2},
                      {x + 1, y - 2},
                      {x + 2, y - 1},
                      {x + 2, y + 1},
                      {x + 1, y + 2},
                      {x - 1, y + 2},
                      {x - 2, y + 1}};
    for (int[] pos : newPos) {
      if (pos[0] >= 0 && pos[0] < _n && pos[1] >= 0 && pos[1] < _n && _board[pos[0]][pos[1]] == 0) {
        _board[pos[0]][pos[1]] = _board[x][y] + 1;

        if (solve(pos[0], pos[1]))
          solved = true;
        else
          _board[pos[0]][pos[1]] = 0;
      }
    }
    
    return solved;
  }

  public String toString() {
    String ans = "";
    for (int i = 0; i < _board.length; i++) {
	    for (int j = 0; j < _board[i].length; j++) {
        ans += _board[i][j] + " ";
	    }
	    ans += "\n";
    }
    return ans;
  }

  public static void main(String [] args) {
    int n = Integer.parseInt(args[0]);
    KnightTour t = new KnightTour(n);
    t.solve();
  }
}
