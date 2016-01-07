import wheels.users.*;

public class MinesApp extends Frame {
  private int _maxRows, _maxCols;
  private Tile[][] _tiles;

  public MinesApp(int maxRows, int maxCols) {
    super();
    _tiles = new Tile[maxRows][maxCols];
    for (int i = 0; i < maxRows; i++) {
      for (int j = 0; j < maxCols; j++) {
        System.out.println(i + " " + j);
        _tiles[i][j] = new Tile(i * 50, j * 50, Math.random() < 0.5);
        _tiles[i][j].setSize(45, 45);
      }
    }
  }

  public static void main(String[] args) {
    MinesApp app = new MinesApp(50, 50);
  }
}
