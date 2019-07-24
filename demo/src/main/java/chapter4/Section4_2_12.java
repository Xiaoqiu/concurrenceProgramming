package chapter4;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kate
 * @create 2019/7/24
 * @since 1.0.0
 */
public class Section4_2_12 {
  /**
   * AbortPolicy - 超出核心线程数的，先入队，超出队列长度的再创建线程处理，超出最大线程数的任务就抛异常
   * @throws InterruptedException
   */
  @Test
  public void test() throws InterruptedException{
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
          System.out.println(Thread.currentThread().getName() + " run end!");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
        2,3,5, TimeUnit.SECONDS
        ,new ArrayBlockingQueue<Runnable>(2)
    , new ThreadPoolExecutor.CallerRunsPolicy());
    for (int i = 1; i <= 6; i++) {
      pool.execute(runnable);
    }

    System.out.println("poolSize: " + pool.getPoolSize());
    System.out.println("队列长度: " + pool.getQueue().size());

    Thread.sleep(10000);

  }
}