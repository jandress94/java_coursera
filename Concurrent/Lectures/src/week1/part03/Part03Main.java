package week1.part03;

public class Part03Main {

  public static void main(String[] args) {
    LinkedList list = LinkedList.create(10);

    Thread t1 = new Thread(() -> list.traverseInPairs(1));
    Thread t2 = new Thread(() -> list.traverseInPairs(2));
    Thread t3 = new Thread(() -> list.traverseInPairs(3));

    t1.start();
    t2.start();
    t3.start();




    SearchSetArray arr = new SearchSetArray(100, 100);
  }
}
