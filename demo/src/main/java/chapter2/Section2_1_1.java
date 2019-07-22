package chapter2;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author kate
 * @create 2019/7/13
 * @since 1.0.0
 */
public class Section2_1_1 {


   class MyService {
     // 计数器，为1
     private CountDownLatch downLatch = new CountDownLatch(1);
     public void testMethod() {
       try {
         System.out.println("A");
         // 判断计数器，如果不为0，线程阻塞不往下执行。如果为0，可以继续往下执行
         downLatch.await();
         System.out.println("B");
       } catch (InterruptedException e) {
         e.printStackTrace();
       }
     }
     public void downMethod() {
       System.out.println("X");
       // 计数器减1， 如果计数器减为0，其他呈现等待的线程继续往下执行。
       downLatch.countDown();
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
  public void test()  throws InterruptedException{
    MyService myService = new MyService();
    MyThread myThread = new MyThread(myService);
    myThread.start();
    Thread.sleep(5000);
    myService.downMethod();
  }
}