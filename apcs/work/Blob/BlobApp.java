import wheels.users.*;
import java.util.ArrayList;

public class BlobApp extends Frame {
  private ArrayList<Blob> _blobs;
  
  public BlobApp() {
    super();
    _blobs = new ArrayList<Blob>(0);
  }

  public void newBlob(int x, int y) {
    _blobs.add(new Blob(x, y));
  }

  public void addBlob(Blob b) {
    _blobs.add(b);
  }

  public static void main(String[] args) {
    BlobApp app = new BlobApp();
    app._blobs.add(new Blob(75, 125));
    app._blobs.add(new WinkingBlob(225, 125));
    app._blobs.add(new TalkativeBlob(375, 125));
    app._blobs.add(new HappyBlob(525, 125));
    app._blobs.add(new SadBlob(75, 275));
    app._blobs.add(new GreedyBlob(225, 275));
    app._blobs.add(new ShyBlob(375, 275));
    //app._blobs.add(new MeltingBlob(525, 275));
  }
}
