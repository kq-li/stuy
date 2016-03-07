import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Toolbar {

	private int _time;
	private int _flagged;
	private int _crctFlagged;
    private Board _board;
    private ConversationBubble _bubble;
    private Image _onesPlace;
    private Image _tensPlace;
    private Image _hundredsPlace;

	public Toolbar() {
        Rectangle bg = new Rectangle(0, 0);
        bg.setSize(700, 100);
        bg.setColor(Color.red);
        /*
        _onesPlace = new Image("./"+ (_flagged % 10) "/tile.png",150,10);
        _tensPlace = new Image("./"+ (_flagged % 100)  "/tile.png",250,10);
        _hundredsPlace = new Image("./"+ (_flagged % 1000)  "/tile.png",350,10);
        */
        
    }

	public void CrctFlagged() {
		_crctFlagged = MinesweeperApp._currentBoard._crctFlags;
	}
	public void Flagged() {
		_flagged  = MinesweeperApp._currentBoard._flagged;
	}
}
