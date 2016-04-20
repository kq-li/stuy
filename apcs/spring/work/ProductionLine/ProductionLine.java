public class ProductionLine {
  private Queue<Disk> _assemblyLineIn;
  private Queue<Tower> _assemblyLineOut;
  private Tower _robotArm;

  public ProductionLine(int nDisks, int maxRadius) {
    _assemblyLineIn = new NodeQueue<Disk>();
    _assemblyLineOut = new NodeQueue<Tower>();
    _robotArm = new Tower;

    for (int i = 0; i < nDisks; i++)
      _assemblyLineIn.enqueue(new Disk((int) (Math.random() * maxRadius) + 1));
  }

  private void unloadRobot() {
    Tower t = new Tower();
  }

  public void process() {

  }

  public String toString() {
    String ret = "";
    return ret;
  }
}
