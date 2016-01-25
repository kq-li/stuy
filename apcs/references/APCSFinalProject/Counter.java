import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Counter {

	protected int _flagged;

    private ConversationBubble _bubble;

	private Image _onesPlace;
    private Image _tensPlace;
    private Image _hundredsPlace;

    public Counter(){
    	Rectangle bk = new Rectangle (0,0);
    	bk.setSize(300,100);
    	bk.setColor(Color.blue);

    	_bubble = new ConversationBubble((""+_flagged), ConversationBubble.TAIL_DIR_LEFT);
		_bubble.setLocation(10,10);
    	/*
    	_onesPlace = new Image("./"+ (_flagged % 10) "/tile.png",150,10);
        _tensPlace = new Image("./"+ (_flagged % 100)  "/tile.png",250,10);
        _hundredsPlace = new Image("./"+ (_flagged % 1000)  "/tile.png",350,10);
        */
    }

    
}
