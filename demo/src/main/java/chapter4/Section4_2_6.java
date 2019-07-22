package chapter4;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kate
 * @create 2019/7/19
 * @since 1.0.0
 */
public class Section4_2_6 {
  class MyRunable implements Runnable{
    private String username;

    public MyRunable(String username) {
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
  public void test() throws InterruptedException{
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    for (int i = 1; i <= 3; i++) {
      executorService.execute(new MyRunable("" + i));
    }
    Thread.sleep(6000);
  }
}