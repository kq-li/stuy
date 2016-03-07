import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class Button extends Image {
    
    private String _type;
    
    public Button(String type, String imagePath) {
        super(imagePath);
        _type = type;
    }
    
    public void mouseClicked(MouseEvent e) {
        if (_type.equals("easy") || _type.equals("med") || _type.equals("hard")) 
			MinesweeperApp.setBoard(_type);
    }
}
