package chapter1;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TransferQueue;

/**
 * @author kate
 * @create 2019/7/11
 * @since 1.0.0
 */
public class Section1_1_1 {

  class Service implements Runnable {
    // 许可个数，代表可以执行acquire()和release()
    private Semaphore semaphore = new Semaphore(1);

    @Override
    public void run() {
      try {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() +
            " begin timer= " + System.currentTimeMillis());
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() +
            " end timer= " + System.currentTimeMillis());
        semaphore.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }


  @Test
  public void mythreads() throws InterruptedException{
    Service service = new Service();
    Thread threadA = new Thread(service);
    threadA.setName("A");
    Thread threadB = new Thread(service);
    threadB.setName("B");
    Thread threadC = new Thread(service);
    threadC.setName("C");
    threadA.start();
    threadB.start();
    threadC.start();

    Thread.sleep(20000);
  }
}