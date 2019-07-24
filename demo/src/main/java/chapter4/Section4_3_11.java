package chapter4;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author kate
 * @create 2019/7/24
 * @since 1.0.0
 */
public class Section4_3_11 {

  class MyRunnable implements Runnable {
    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName()
            + " begin " + System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()
            + " end " + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * - LinkedBlockingDeque ： 指定队列存储元素多少, 超过核心线程数的任务入队，超过队列长度，创建线程执行，超过最大线程数的抛出异常RejectedExecutionException
   * - max值被参考
   *
   * @throws InterruptedException
   */
  @Test
  public void test() throws InterruptedException{
    // 有指定队列长度：
   // LinkedBlockingDeque deque = new LinkedBlockingDeque<Runnable>(2);
    // 不指定队列长度： (就是入队的任务数是Integer.MAX_VALUE)
    LinkedBlockingDeque deque = new LinkedBlockingDeque<Runnable>();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,deque);
    for (int i = 1; i <= 5; i++) {
      pool.execute(new MyRunnable());
    }
    // 入队任务，执行任务
    System.out.println("线程池任务数：" + pool.getPoolSize() + "队列大小：" +  deque.size());
  }

  /**
   * - ArrayBlockingQueue : 指定队列存储元素多少（必须指定）， 超过核心线程数的任务入队，超过队列长度，创建线程执行，超过最大线程数的抛出异常RejectedExecutionException
   */
  @Test
  public void test2() {
    ArrayBlockingQueue deque = new ArrayBlockingQueue<Runnable>(2);

    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,deque);
    for (int i = 1; i <= 6; i++) {
      pool.execute(new MyRunnable());
    }
    // 入队任务，执行任务
    System.out.println("线程池任务数：" + pool.getPoolSize() + "队列大小：" +  deque.size());
  }

  /**
   * - ArrayBlockingQueue : 指定队列存储元素多少（必须指定）， 超过核心线程数的任务入队，超过队列长度，创建线程执行，超过最大线程数的抛出异常RejectedExecutionException
   */
  @Test
  public void test3() {
    SynchronousQueue deque = new SynchronousQueue<Runnable>();

    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,5, TimeUnit.SECONDS,deque);
    for (int i = 1; i <= 3 ; i++) {
      pool.execute(new MyRunnable());
    }
    // 入队任务，执行任务
    System.out.println("线程池任务数：" + pool.getPoolSize() + "队列大小：" +  deque.size());
  }
}