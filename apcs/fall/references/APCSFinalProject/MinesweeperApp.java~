import wheels.users.*;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperApp extends Frame {
    
    public static Board _currentBoard;
    public static Toolbar _currentToolbar;
    
    public MinesweeperApp() {
    	super();
        
        OpeningScreen os = new OpeningScreen();
        //createBoard();
    }
    
    public static void setBoard(String s) {
        //_currentBoard = null;
        _currentBoard = new Board(s);
        //_currentToolbar = null;
        _currentToolbar = new Toolbar();
    }
    
    public static void main(String[] args) {
        MinesweeperApp app = new MinesweeperApp();
    }
    
}
