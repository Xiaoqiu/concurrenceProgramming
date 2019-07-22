package chapter4;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kate
 * @create 2019/7/19
 * @since 1.0.0
 */
public class Section4_2_2 {
  class MyRunnable implements Runnable {
    private String username;

    public MyRunnable(String username) {
      this.username = username;
    }

    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName() + " username=" + username + " begin " + System.currentTimeMillis());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " username=" + username + " end " + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  @Test
  public void test() throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 1; i <= 10; i++) {
      executorService.execute(new MyRunnable("" + i));
    }

    Thread.sleep(5000);
  }
}