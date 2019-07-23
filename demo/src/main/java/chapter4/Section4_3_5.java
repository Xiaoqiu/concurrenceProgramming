package chapter4;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kate
 * @create 2019/7/23
 * @since 1.0.0
 */
public class Section4_3_5 {
  class MyRunable implements Runnable {
    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void test() throws InterruptedException{
    MyRunable myRunable = new MyRunable();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,10,9999L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    pool.execute(myRunable);
    pool.shutdown();
    System.out.println("main begin ! " + System.currentTimeMillis());
    // awaitTermination() 如果有任务被执行，出现阻塞性, 阻塞3s,两秒还是没有完成任务就返回结果。
    // 如果没有任务就不阻塞。
    // shutdown与awaitTermination结合可以实现"等待执行完毕"的效果

   // System.out.println(pool.isTerminating()); // true 不阻塞
   // System.out.println(pool.isTerminated()); // 不阻塞
    System.out.println(pool.awaitTermination(13,TimeUnit.SECONDS)); //有任务就阻塞
   // System.out.println(pool.isTerminating());
   // System.out.println(pool.isTerminated());
    System.out.println("main end ! " + System.currentTimeMillis());

  }
}