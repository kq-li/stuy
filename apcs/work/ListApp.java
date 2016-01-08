public class ListApp {
  public static void main(String[] args) {
    List<String> names = new ArrayList<String>();
    names.add("Bob");
    names.add("Mary");
    names.add("Joe");
    System.out.println(names);
    System.out.println(names.size());
    names.set(0, "Moe");
    System.out.println(names);
    System.out.println(names.get(1));
    names.add(1, "Carl");
    System.out.println(names);
  }
}
