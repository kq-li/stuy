import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class TileImage extends Image {
    private int _x;
    private int _y;
    
    public TileImage(String i, int x, int y) {
        super(i);
        setSize(MinesweeperApp._currentBoard._sl,MinesweeperApp._currentBoard._sl);
        setLocation(x, y);
        _x = x/MinesweeperApp._currentBoard._sl;
        _y = (y-100)/MinesweeperApp._currentBoard._sl;
    }

	
    
	public void mousePressed(MouseEvent e) {
		MinesweeperApp._currentBoard._tiles[_x][_y].invTile();
	}

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) { // If left click
            // This accesses the tiles matrix of the app, selects the tile for this image, and calls its fliptile function
            MinesweeperApp._currentBoard._tiles[_x][_y].flipTile();
        } else if (e.getButton() == 3) {
            // Same here but for flagging
            MinesweeperApp._currentBoard._tiles[_x][_y].flagTile();
        }
    }
}
