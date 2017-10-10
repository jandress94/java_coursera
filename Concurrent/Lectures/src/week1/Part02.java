package week1;

import java.util.Random;

public class Part02 {
  private static class Data {
    private int count = 0;
    private double waitProb;
    private Random rand = new Random();

    public Data(double waitProb) {
      this.waitProb = waitProb;
    }

    public void incr() {
      int oldVal = count;
      if (rand.nextDouble() < waitProb) {
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      count = oldVal + 1;
    }

    public int getCount() {
      return count;
    }
  }

  public static void main(String[] args) {

    // a race condition.  can get either 1 or 2 for the final value of the count
    Data a = new Data(0.5);

    Thread t1 = new Thread(a::incr);
    Thread t2 = new Thread(a::incr);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(a.getCount());

    // no race condition b/c have a structured lock on the object before modifying it
    Data b = new Data(0.5);

    Thread t3 = new Thread(() -> {
      synchronized (b) {
        b.incr();
      }
    });
    Thread t4 = new Thread(() -> {
      synchronized (b) {
        b.incr();
      }
    });
    t3.start();
    t4.start();

    try {
      t3.join();
      t4.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(b.getCount());
  }
}
