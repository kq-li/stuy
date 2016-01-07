import wheels.users.*;

public class TalkativeBlob extends Blob{
  private ConversationBubble _bubble;
  
  public TalkativeBlob(int x, int y){
    super(x,y);
    _bubble = new ConversationBubble("Hello!", 
                                       ConversationBubble.TAIL_DIR_LEFT);
    _bubble.setLocation(x + 20,y - _bubble.getHeight());
    _bubble.hide();
  }

  public void setBubbleText(String text) {
    _bubble.setText(text);
  }

  public void mousePressed(java.awt.event.MouseEvent e){
    super.mousePressed(e);
    _bubble.show();
  }

  public void mouseReleased(java.awt.event.MouseEvent e){
    super.mouseReleased(e);
    _bubble.hide();
  } 
}
