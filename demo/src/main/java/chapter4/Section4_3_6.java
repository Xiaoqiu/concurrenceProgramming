package chapter4;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author kate
 * @create 2019/7/23
 * @since 1.0.0
 */
public class Section4_3_6 {

  class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println(((MyRunnable)r).getUsername() + "被拒绝执行了！");
    }
  }

  class MyRunnable implements Runnable {
    private String username;

    public MyRunnable(String username) {
      this.username = username;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName()
            + " begin " + System.currentTimeMillis());
//        String s = null;
//        s.indexOf(0);
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()
            + " end " + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  class MyThreadFactoryA implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
      Thread newThread = new Thread(r);
     // newThread.setName("kate : " + new Date());
      newThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
          System.out.println("自定义异常处理" + t.getName() + " " + e.getMessage());
        }
      });
      return newThread;
    }
  }

  @Test
  public void test() throws InterruptedException {
    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,2,9999L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(), new MyThreadFactoryA());
    // pool.setThreadFactory(new MyThreadFactoryA());
    pool.setRejectedExecutionHandler(new MyRejectedExecutionHandler());
   // 允许核心线程在空闲的时候也会被回收， 参数keepAliveTime有效。
    pool.allowCoreThreadTimeOut(true);
    for (int i = 1; i <= 5; i++) {
      MyRunnable myRunnable = new MyRunnable("name" + i);
      pool.execute(myRunnable);
    }
    Thread.sleep(1000);
    System.out.println(pool.getCompletedTaskCount());
    Thread.sleep(1000);
    System.out.println(pool.getCompletedTaskCount());
    Thread.sleep(1000);
    System.out.println(pool.getCompletedTaskCount());
    Thread.sleep(1000);
    System.out.println(pool.getCompletedTaskCount());
    Thread.sleep(1000);
    System.out.println(pool.getCompletedTaskCount());
    Thread.sleep(10000);
    // System.out.println("allowCoreSizeTimeOut : " + pool.allowsCoreThreadTimeOut());
   // System.out.println("poolSize : " + pool.getPoolSize());
  }
}