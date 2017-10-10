package week1.part03;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SearchSetArray {

  private int[] arr;
  private int maxVal;
  private Random rand = new Random();
  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public SearchSetArray(int arrLen, int maxVal) {
    this.maxVal = maxVal;
    arr = new int[arrLen];

    for (int i = 0; i < arrLen; i++) {
      arr[i] = rand.nextInt(maxVal);
    }
  }

  public void setVal(int val) {
    int idx = rand.nextInt(arr.length);

    lock.writeLock().lock();
    arr[idx] = val;
    lock.writeLock().unlock();
  }

  public boolean contains(int val) {
    lock.readLock().lock();
    try {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == val) {
          return true;
        }
      }
      return false;
    } finally {
      lock.readLock().unlock();
    }
  }
}
