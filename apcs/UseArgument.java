public class UseArgument {
  public static void main(String[] args) {
    for (int i = args.length - 1; i >= 0; i--) {
      System.out.print(args[i]);
      if (i == 0)
        System.out.println();
      else
        System.out.print(" ");
    }
  }
}
