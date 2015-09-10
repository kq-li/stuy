import java.util.Scanner;

public class UseArgument {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter your name: ");
    String line = scanner.nextLine();
    System.out.println("Hi " + line);
  }
}
