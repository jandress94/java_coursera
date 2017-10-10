package week1.part03;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedList {
  private static class ListNode {
    private static int numNodesCreated = 0;


    private Optional<ListNode> next;

    private int id;
    private ReentrantLock lock = new ReentrantLock();

    public ListNode(ListNode next) {
      this.next = Optional.ofNullable(next);
      this.id = numNodesCreated;
      numNodesCreated++;
    }

    public ListNode acquire() {
      lock.lock();
      return this;
    }

    public void release() {
      lock.unlock();
    }

    public ListNode acquireNext() {
      return next.isPresent() ? next.get().acquire() : null;
    }

    public ListNode getNext() {
      return next.get();
    }

    public int getId() {
      return id;
    }
  }

  public static LinkedList create(int numNodes) {
    if (numNodes <= 0) return null;

    ListNode tail = new ListNode(null);

    for (int i = 0; i < numNodes - 1; i++) {
      tail = new ListNode(tail);
    }

    return new LinkedList(tail);
  }

  private ListNode head;

  public LinkedList(ListNode head) {
    this.head = head;
  }

  public void traverseInPairs(int threadId) {
    ListNode first = head.acquire();
    while (true) {
      ListNode second = first.acquireNext();
      if (second == null) break;

      System.out.printf("Thread %d Got Nodes %d %d\n", threadId, first.getId(), second.getId());
      first.release();
      first = second;
    }
    first.release();
  }
}
