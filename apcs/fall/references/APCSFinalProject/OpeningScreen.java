import wheels.users.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class OpeningScreen extends Rectangle {

	public OpeningScreen() {
		setSize(700,500);
		setColor(Color.white);
        
        Image bg = new Image("./images/bg.jpg");
        bg.setSize(700, 500);
        bg.setLocation(0, 0);
        
        Rectangle darkOverlay = new Rectangle(0, 0);
        darkOverlay.setSize(700, 500);
        Color tb = new Color(0, 0, 0, 128);
        darkOverlay.setColor(tb);
        
        Image t = new Image("./images/title.png");
        t.setSize(700, 300);
        t.setLocation(0,50);
        
        Button easy = new Button("easy", "./images/easy.png");
        easy.setSize(120, 50);
        easy.setLocation(110, 370);
        
        Button med = new Button("med", "./images/med.png");
        med.setSize(120, 50);
        med.setLocation(290, 370);
        
        Button hard = new Button("hard", "./images/hard.png");
        hard.setSize(120, 50);
        hard.setLocation(470, 370);
	}
}
