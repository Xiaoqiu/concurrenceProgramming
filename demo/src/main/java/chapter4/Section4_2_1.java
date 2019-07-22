package chapter4;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kate
 * @create 2019/7/19
 * @since 1.0.0
 */
public class Section4_2_1 {
  @Test
  public void test() throws InterruptedException{
    ExecutorService executorService = Executors.newCachedThreadPool();

    executorService.execute(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println("Runnable1 begin " + System.currentTimeMillis());
          Thread.sleep(1000);
          System.out.println("A");
          System.out.println("Runnable1 end" +  System.currentTimeMillis());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    executorService.execute(new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println("Runnable2 begin " + System.currentTimeMillis());
          Thread.sleep(1000);
          System.out.println("B");
          System.out.println("Runnable2 end" +  System.currentTimeMillis());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread.sleep(5000);
  }

  @Test
  public void test2() {
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("run!");
        }
      });
    }
  }

}