package chapter2;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author kate
 * @create 2019/7/13
 * @since 1.0.0
 */
public class Section2_1_3 {
  class MyService {
    private CountDownLatch down = new CountDownLatch(1);
    public void testMethod() {
      try {
        System.out.println(Thread.currentThread().getName() + "准备");
        // 判断计数器，如果为0，之前所有await的线程都往下执行。
        down.await();
        System.out.println(Thread.currentThread().getName() + "结束");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    public void downMethod() {
      System.out.println(Thread.currentThread().getName() + "开始");
      // 计数器就是1，调用一次countDown()就可以把计数器设置为0，之前await的所有线程都开始继续执行
      down.countDown();
    }
  }

  class MyThread extends Thread {
    private MyService myService;

    public MyThread(MyService myService) {
       super();
      this.myService = myService;
    }

    @Override
    public void run() {
      myService.testMethod();
    }
  }

  @Test
  public void test() throws InterruptedException {
    MyService myService = new MyService();
    MyThread[] myThreads = new MyThread[10];
    for (int i = 0; i < myThreads.length; i++) {
      myThreads[i] = new MyThread(myService);
      myThreads[i].setName("线程" + (i + 1));
      myThreads[i].start();
    }
    Thread.sleep(2000);
    myService.downMethod();
  }
}