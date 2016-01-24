import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Tile extends Rectangle {
	
	private boolean _isFaceUp;
   private boolean _isFlagged;
	private boolean _isMine;

    private int _adjacentMines;
    private int _x;
    private int _y;
    private TileImage _image;

	 private boolean _isFirstClick;
	
    //Constructor for mines, no adjacentMines value needed
    
	 public Tile(int x, int y) {
		super(x,y);
     
     	int sideLength = MinesweeperApp._currentBoard._sl;
		setSize(sideLength,sideLength);
		setLocation(x,y);
      setColor(Color.white);
        
     	_x = x;
     	_y = y;
		_isMine = true;
     	_isFaceUp = false;

		_isFlagged = false;
        
      _image = new TileImage("./images/tile.png", _x, _y);
        
    }
    
    // Constructor for other tiles
    
    public Tile(int x, int y, int adjacentMines) {
    	super(x,y);
        
      int sideLength = MinesweeperApp._currentBoard._sl;
      setSize(sideLength,sideLength);
      setLocation(x,y);
      setColor(Color.white);
        
      _x = x;
      _y = y;
      _isMine = false;
      _isFaceUp = false;

		_isFlagged = false;

     	_adjacentMines = adjacentMines;
		_isFirstClick = MinesweeperApp._currentBoard._isFirstClick;
        
      _image = new TileImage("./images/tile.png", _x, _y);
    }
    
	//accessor methods

    public int getX() {
        return _x;
    }
    
    public int getY() {
        return _y;
    }
    
    public int getAdjMines() {
        return _adjacentMines;
    }

	public void setTileImage(String s) {
		_image = null;
		_image = new TileImage(s, getX(), getY());
    }


    // This function "flips" tiles by changing the image property. Also checks if tile is flagged.
	// If it is, do nothing. 
    public void flipTile() {
		 
		 if (!_isFaceUp && !_isFlagged){
			
			 //ends the game if true
      	if (_isMine) {
         	_image.hide();
         	setTileImage("./images/mine.png");
				Rectangle coverScreen = new Rectangle(0,100);
				int xSize= MinesweeperApp._currentBoard._x * 50;
				int ySize= MinesweeperApp._currentBoard._y * 50;
				coverScreen.setSize(xSize,ySize);
	 			coverScreen.setFillColor(null);
				coverScreen.setFrameColor(null);
       	} 
			
			else if(_adjacentMines == 0){
	   		setTileImage("./images/" + _adjacentMines + ".png");
				_isFaceUp = true;
				MinesweeperApp._currentBoard._nonMineTiles--;
				revealEight(_x,_y);  
			}
				
			else {
	   		setTileImage("./images/" + _adjacentMines + ".png");
				MinesweeperApp._currentBoard._nonMineTiles--;
				
	     	}
			
         _isFaceUp = true;
			System.out.println(MinesweeperApp._currentBoard._nonMineTiles);

    	}
  	}

	
    
    // Same for flagging, except you can toggle.
    public void flagTile() {
        if (!_isFlagged && !_isFaceUp) {
            setTileImage("./images/flag.png");
            _isFlagged = true;
			MinesweeperApp._currentBoard._flagged++;
				if (_isMine)
					MinesweeperApp._currentCounter._flagged++;

		  }
		  else if (!_isFaceUp){
            setTileImage("./images/tile.png");
            _isFlagged = false;
			MinesweeperApp._currentBoard._flagged--;
				if (_isMine)
					MinesweeperApp._currentCounter._flagged--;
			}
		System.out.println(MinesweeperApp._currentBoard._flagged);
   }
		 

	
	public void invTile() {
		if (!_isFaceUp && !_isFlagged)	
			setTileImage("./images/tile_inv.png");
		
		if (_isFirstClick) {
			int x = _x/MinesweeperApp._currentBoard._sl;
         int y = (_y-100)/MinesweeperApp._currentBoard._sl;
			MinesweeperApp._currentBoard.layout(x,y);
		}
	}
	


	//Given tile with value 0 is selected and its coordinates, we can uncover all tiles around it. 
	//We want to run this in a loop (or recursively) if any surrounding tiles also has the value of 0.
	public void revealEight(int x,int y){
      x = x/MinesweeperApp._currentBoard._sl;
      y = (y-100)/MinesweeperApp._currentBoard._sl;
		for (int i = Math.max(x-1,0); i <= Math.min(x+1,(MinesweeperApp._currentBoard._x)-1); i++) {
      	for (int j = Math.max(y-1,0); j <= Math.min(y+1,(MinesweeperApp._currentBoard._y)-1); j++) {
         	MinesweeperApp._currentBoard._tiles[i][j]._isFlagged = false;
				MinesweeperApp._currentBoard._tiles[i][j].flipTile();
         }
      }
	}

}
		
		
