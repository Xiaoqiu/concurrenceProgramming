package chapter1;

import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @author kate
 * @create 2019/7/11
 * @since 1.0.0
 */
public class Section1_1_3 {
  class Service implements Runnable {
    private Semaphore semaphore = new Semaphore(4);
    @Override
    public void run() {
      try {
        System.out.println("A可用许可： " + semaphore.availablePermits());
        semaphore.acquire(2);
        System.out.println("B可用许可： " + semaphore.availablePermits());
        System.out.println(Thread.currentThread().getName() +
            " begin time= " + System.currentTimeMillis());
        //int sleepValue = (int)(Math.random() * 10000);
       // System.out.println(Thread.currentThread().getName() +
       //     " 停止了 " + (sleepValue / 1000) + "秒");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() +
            " end time= " + System.currentTimeMillis());
        semaphore.release(2);
        System.out.println("C可用许可： " + semaphore.availablePermits());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void mythreads() throws InterruptedException{
    Service service = new Service();
    Thread[] a = new Thread[10];
    for (int i = 0; i < 4; i++) {
      a[i] = new Thread(service);
      a[i].start();
    }

    Thread.sleep(20000);
  }

  // 测试动态修改许可个数
  @Test
  public void test() throws InterruptedException{
    Semaphore semaphore = new Semaphore(5);
    semaphore.acquire();
    semaphore.acquire();
    semaphore.acquire();
    semaphore.acquire();
    semaphore.acquire();
    System.out.println("可用许可= " + semaphore.availablePermits());
    semaphore.release();
    semaphore.release();
    semaphore.release();
    semaphore.release();
    semaphore.release();
    semaphore.release();
    System.out.println("可用许可= " + semaphore.availablePermits());
    semaphore.release(4);
    System.out.println("可用许可= " + semaphore.availablePermits());

  }
}