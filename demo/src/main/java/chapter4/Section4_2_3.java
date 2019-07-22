package chapter4;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author kate
 * @create 2019/7/19
 * @since 1.0.0
 */
public class Section4_2_3 {

  class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
      Thread thread = new Thread(r);
      thread.setName("定制池中线程对象的名称" + Math.random());
      return thread;
    }

  }

  @Test
  public void test() {
    MyThreadFactory myThreadFactory = new MyThreadFactory();
    ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("我在运行 " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
      }
    });
  }

}