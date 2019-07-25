package chapter10;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author kate
 * @create 2019/7/25
 * @since 1.0.0
 */
public class Section10_3 {


  @Test
  public void test() {
    try {
      ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
      queue.take();
      queue.put("a1");
      queue.put("a2");
      queue.put("a3");
      System.out.println(queue.size());
      System.out.println(System.currentTimeMillis());
      queue.put("a4");
      System.out.println(System.currentTimeMillis());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test2() {
    PriorityBlockingQueue queue = new PriorityBlockingQueue();
    queue.add(10);
    queue.add(3);
    queue.add(11);
    queue.add(1);
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());
    System.out.println(queue.poll());

  }
}