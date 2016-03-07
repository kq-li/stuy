public class NQueens {
  private int _n;
  private int _board[][]; // 0 for closed, 1 for open, 2 for queen

  public NQueens() {
    this(8);
  }

  public NQueens(int n) {
    _n = n;
    _board = new int[8][8];

    for (int i = 0; i < _board.length; i++)
      for (int j = 0; j < _board.length; j++)
        _board[i][j] = 1;
  }

  private void resetQueens() {
    for (int i = 0; i < _board.length; i++)
      for (int j = 0; j < _board.length; j++)
        if (_board[i][j] == 2)
          _board[i][j] = 1;
  }

  private void resetSpaces() {
    for (int i = 0; i < _board.length; i++)
      for (int j = 0; j < _board.length; j++)
        if (_board[i][j] != 2)
          _board[i][j] = 1;
  }
  
  private void markBoard() {
    resetSpaces();
    
    for (int i = 0; i < _board.length; i++) 
      for (int j = 0; j < _board.length; j++) 
        if (_board[i][j] == 2)
          for (int k = 0; k < _board.length; k++) 
            for (int l = 0; l < _board.length; l++) 
              if (_board[k][l] == 1)
                if (k == i || l == j || Math.abs(k - i) == Math.abs(l - j))
                  _board[k][l] = 0;
  }
  
  public void solve() {
    for (int i = 0; i < _board.length; i++) {
      for (int j = 0; j < _board.length; j++) {
        solve(i, j, _n - 1);
        
        resetSpaces();
        resetQueens();
      }
    }
  }

  public boolean solve(int x, int y, int n) {
    _board[x][y] = 2;
    markBoard();

    if (n == 0) {
      System.out.println(this);
      return true;
    }
    
    boolean solved = false;

    for (int i = x + 1; i < _board.length; i++) {
      for (int j = 0; j < _board.length; j++) { 
        if (_board[i][j] == 1) {          
          if (solve(i, j, n - 1))
            solved = true;

          _board[i][j] = 1;
          markBoard();
        }
      }
    }

    return solved;
  }

  public String toString() {
    String ret = "";

    for (int i = 0; i < _board.length; i++) {
      for (int j = 0; j < _board.length; j++) 
        if (_board[i][j] == 2)
          ret += "q ";
        else if (_board[i][j] == 1)
          ret += "_ ";
        else
          ret += "x ";

      ret += "\n";
    }

    return ret;
  }

  public static void main(String[] args) {
    NQueens nq = new NQueens(Integer.parseInt(args[0]));
    nq.solve();
  }
}
    
