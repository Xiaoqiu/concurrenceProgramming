package chapter2;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kate
 * @create 2019/7/15
 * @since 1.0.0
 */
public class Section2_2_3 {
  class MyService {
    private CyclicBarrier cyclicBarrier;

    public MyService(CyclicBarrier cyclicBarrier) {
      this.cyclicBarrier = cyclicBarrier;
    }

    public void beginRun() {
      try {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " "
        + System.currentTimeMillis() + " begin跑第1阶段, 等待的线程数："
        + (cyclicBarrier.getNumberWaiting() + 1));
        // 等待设定数量的线程调用await, 所有await线程继续执行。
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " "
            + System.currentTimeMillis() + " end跑第1阶段, 等待的线程数："
            + cyclicBarrier.getNumberWaiting());

        System.out.println(Thread.currentThread().getName() + " "
            + System.currentTimeMillis() + " begin跑第2阶段, 等待的线程数："
            + (cyclicBarrier.getNumberWaiting() + 1));
        // 等待设定数量的线程调用await, 所有await线程继续执行。
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " "
            + System.currentTimeMillis() + " end跑第2阶段, 等待的线程数："
            + cyclicBarrier.getNumberWaiting());

        } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

  class MyThread extends Thread {
    private MyService service;

    public MyThread(MyService service) {
      this.service = service;
    }

    @Override
    public void run() {
      service.beginRun();
    }
  }

  @Test
  public void test() throws InterruptedException{
      CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
      MyService service = new MyService(cyclicBarrier);
    for (int i = 0; i < 2; i++) {
      MyThread thread = new MyThread(service);
      thread.setName("线程" + i);
      thread.start();
    }
    Thread.sleep(20000);
  }
}