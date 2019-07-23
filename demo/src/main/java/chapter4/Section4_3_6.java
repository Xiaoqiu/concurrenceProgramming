package chapter4;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kate
 * @create 2019/7/23
 * @since 1.0.0
 */
public class Section4_3_6 {

  class MyRunnable implements Runnable {
    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName()
            + " " + System.currentTimeMillis());
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()
            + " " + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  class MyThreadFactoryA implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
      Thread newThread = new Thread(r);
      newThread.setName("kate : " + new Date());
      return newThread;
    }
  }

  @Test
  public void test() {
    MyRunnable myRunnable = new MyRunnable();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,99999,9999L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(), new MyThreadFactoryA());
    // pool.setThreadFactory(new MyThreadFactoryA());
    pool.execute(myRunnable);


  }
}