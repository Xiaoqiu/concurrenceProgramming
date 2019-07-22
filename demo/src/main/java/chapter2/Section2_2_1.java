package chapter2;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kate
 * @create 2019/7/14
 * @since 1.0.0
 */
public class Section2_2_1 {
  class MyThread extends Thread {
    private CyclicBarrier cyclicBarrier;

    public MyThread(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getName() + "到了, " + System.currentTimeMillis());
        // 设定数量（5个）的线程执行了await方法才能往下执行，否则彼此等待，阻塞状态。
        System.out.println("等待的线程数： " + cyclicBarrier.getNumberWaiting());
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void test() throws InterruptedException{
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
      @Override
      public void run() {
        System.out.println("全部到了！");
      }
    });
    MyThread[] myThreads = new MyThread[5];
    for (int i = 0; i < myThreads.length; i++) {
      myThreads[i] = new MyThread(cyclicBarrier);
    }
    for (int i = 0; i < myThreads.length; i++) {
      myThreads[i].start();
      Thread.sleep(500);
    }

    Thread.sleep(20000);
  }
}