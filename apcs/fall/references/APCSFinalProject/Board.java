import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends Rectangle {
    
  	private boolean[][] _board;
  	public Tile[][] _tiles;
    
    public int _sl;
  	public int _x;
    public int _y;

	public int _crctFlags; //number of mines and flagged
	public int _nonMineTiles; //tiles that aren't mines
	public int _mines; //number of mines on the board
    public int _flagged;//number of flags placed
    

	public boolean _isFirstClick;
    
    public Board(String level) {
        super(0, 100);
        System.out.println(level);
        if (level.equals("easy")) {
            _sl = 50;
            _x = 14;
            _y = 8;
        } else if (level.equals("med")) {
            _sl = 25;
            _x = 28;
            _y = 16;
        } 
		//by default, it should be "hard" if it doesn't pass the two conditions above
		else {
            _sl = 20;
            _x = 35;
            _y = 20;
        }	
		
		_isFirstClick = true;
        
        // The board is defined as a matrix of 14x10, with booleans defining if the tile at that position is a mine or not.
        _board = new boolean[_x][_y];
        _tiles = new Tile[_x][_y];
        
	}
	
	
	public void fakeLayout(){      	 
   	for (int i = 0; i < _x; i++) {  
			for (int j = 0; j < _y; j++) {            
				Tile newTile = new Tile(i*_sl, j*_sl+100, 0);
        	 	_tiles[i][j] = newTile;
     		}	 
		}
	}
	
	public void layout(int x, int y) {
		
		//Declare which x and y are restricted so that we don't place mines on them
		ArrayList<Integer> surroundX = new ArrayList<Integer>();
		surroundX.add(x-1);
		surroundX.add(x);
		surroundX.add(x+1);
		
		ArrayList<Integer> surroundY = new ArrayList<Integer>();
		surroundY.add(y-1);
		surroundY.add(y);
		surroundY.add(y+1);
		
		//prevents invTile() from calling this function again
	 	_isFirstClick = false;
		
		_crctFlags = 0; //correct flags, meaning if the user flags a tile that has a mine,it's a correct flag.
		_nonMineTiles = 0; //initialize
        _flagged = 0;
			
        // This nested loop creates the board with mines in random places, with the percent chance of a tile being a mine at 20%
        for (int i = 0; i < _x; i++) {
            for (int j = 0; j < _y; j++) {
                double r = Math.random();
                boolean isMine = (r < .2);
					 if (!(surroundX.contains(i) && surroundY.contains(j)))
                	_board[i][j] = isMine;
            }
        }
       
        // Now, with the boolean grid laid out, tiles are instantiated with the proper # of adjacent mines.
        for (int i = 0; i < _x; i++) {
            for (int j = 0; j < _y; j++) {
                // The tiles get instantiated and added to the tile matrix
                if (getBV(i,j)) {
                    Tile newTile = new Tile(i*_sl, j*_sl+100);
                    _tiles[i][j] = newTile;
						  _mines++;
                } 
					 else {
                    Tile newTile = new Tile(i*_sl, j*_sl+100, getAdj(i,j));
                    _tiles[i][j] = newTile;
						  _nonMineTiles++; //counts number of non-mine tiles
                }
            }
        }		  
    }
    
    public boolean getBV(int x, int y) {
        return _board[x][y];
    }
    
    // This function returns the number of adjacent mines for any given tile.
    public int getAdj(int x, int y) {
        int total = 0;
        
        // These loops get the surrounding values, accounting for edge cases.
        for (int i = Math.max(x-1,0); i <= Math.min(x+1,_x-1); i++) {
            for (int j = Math.max(y-1,0); j <= Math.min(y+1,_y-1); j++) {
                if (getBV(i,j))
                    total += 1;
            }
        }
        return total;
    }
}
