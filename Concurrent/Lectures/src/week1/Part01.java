package week1;

public class Part01 {
  public static void main(String[] args) {
    Thread t1 = new Thread(() -> System.out.println("I could print first or second"));

    // could print either first
    t1.start();
    try {
      Thread.sleep(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("I could print first or second too");

    try {
      t1.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("I should print third");
  }
}
