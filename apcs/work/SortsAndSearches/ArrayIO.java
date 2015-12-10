public class ArrayIO {
  public static void printArray(Object[] a) {
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i].toString() + " ");
    System.out.println();
  }

  public static void printArray(Object[][] a) {
    for (int i = 0; i < a.length; i++)
      printArray(a[i]);
    System.out.println();
  }

  public static Integer[] intArray(int n) {
    Integer[] a = new Integer[n];
    for (int i = 0; i < n; i++)
      a[i] = i;
    return a;
  }
  
  public static Object[] asObjArray(Object[] a) {
    return a;
  }
  
  public static Integer[] asObjArray(int[] a) {
    Integer[] b = new Integer[a.length];
    for (int i = 0; i < a.length; i++)
      b[i] = Integer.valueOf(a[i]);
    return b;
  }

  public static Double[] asObjArray(double[] a) {
    Double[] b = new Double[a.length];
    for (int i = 0; i < a.length; i++)
      b[i] = Double.valueOf(a[i]);
    return b;
  }

  public static Character[] asObjArray(char[] a) {
    Character[] b = new Character[a.length];
    for (int i = 0; i < a.length; i++)
      b[i] = Character.valueOf(a[i]);
    return b;
  }

  public static Boolean[] asObjArray(boolean[] a) {
    Boolean[] b = new Boolean[a.length];
    for (int i = 0; i < a.length; i++)
      b[i] = Boolean.valueOf(a[i]);
    return b;
  }
}
