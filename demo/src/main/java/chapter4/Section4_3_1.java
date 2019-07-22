package chapter4;

import org.junit.Test;

import java.util.concurrent.*;

/**
 *  4.3 ThreadPoolExecutor的使用
 * @author kate
 * @create 2019/7/22
 * @since 1.0.0
 */
public class Section4_3_1 {

  // 1 前两个参数与getCorePoolSize() 和 getMaximumPoolSize()方法
  @Test
  public void test1() {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    System.out.println(executor.getCorePoolSize());
    System.out.println(executor.getMaximumPoolSize());
    executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    System.out.println(executor.getCorePoolSize());
    System.out.println(executor.getMaximumPoolSize());
  }

  // 添加的线程数 <= corePoolSize
  // keepAliveTime忽略，不清理多出corePoolSize的空闲线程
  // 任务数和核心线程数相同，来都是直接创建线程执行，不需要入队列等待，也不需要创建超出核心线程的线程。
  @Test
  public void test2() throws InterruptedException {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    for (int i = 1; i <= 7; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);

    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 任务数 > corePoolSize and <= maximumPoolSize
  // keepAliveTime参数忽略；线程池的线程数不会超过核心线程数，
  // linkedBlockingDeque ： 使用这个队列，所以超出核心线程的，任务会加入队列。
  @Test
  public void test3() throws InterruptedException{
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    for (int i = 1; i <= 8; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);

    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 任务数 > corePoolSize and <= maximumPoolSize
  // keepAliveTime参数有效；线程池的线程数会达到最大线程数。超出核心线程数的线程，空闲时间超过这个参数会被回收。
  // SynchronusQueue ： 使用这个队列，所以超出核心线程的，会创建新线程执行。
  @Test
  public void test4() throws InterruptedException{

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    for (int i = 1; i <= 8; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);
    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 4 数量 > maximumPoolSize
  // 使用linkedBlockingDeque, > corePoolSize的任务将加入队列
  // 同一个时间只有corePoolSize的线程运行
  // keepAliveTime参数无效；（只会限制超出corePoolSize的线程运行时间）
  @Test
  public void test5() throws InterruptedException{
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    for (int i = 1; i <= 9; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);
    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 4 数量 < maximumPoolSize
  // 使用SynchronousQueue, > corePoolSize的任务，将创建新线程执行，不放入队列
  //
  // keepAliveTime参数有效；（只会限制超出corePoolSize的线程运行时间）
  @Test
  public void test6() throws InterruptedException{
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,10,5,
        TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    for (int i = 1; i <= 9; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);
    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 4 数量 > maximumPoolSize
  // 使用SynchronousQueue, > corePoolSize的任务，将创建新线程执行，不放入队列
  // 超出maximumPoolSize的任务，任务不处理，抛出异常。
  // keepAliveTime参数有效；（只会限制超出corePoolSize的线程运行时间）
  @Test
  public void test7() throws InterruptedException{
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          System.out.println(Thread.currentThread().getName() + " run! " + System.currentTimeMillis());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    ThreadPoolExecutor executor = new ThreadPoolExecutor(7,8,5,
        TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
    for (int i = 1; i <= 9; i++) {
      executor.execute(runnable);
    }
    Thread.sleep(300);

    System.out.println("A:核心线程数 " + executor.getCorePoolSize());
    System.out.println("A:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("A:线程队列大小 " + executor.getQueue().size());
    System.out.println("A:最大线程数 " + executor.getMaximumPoolSize());
    Thread.sleep(10000);
    System.out.println("B:核心线程数 " + executor.getCorePoolSize());
    System.out.println("B:线程池当前线程数 " + executor.getPoolSize());
    System.out.println("B:线程队列大小 " + executor.getQueue().size());
    System.out.println("B:最大线程数 " + executor.getMaximumPoolSize());
  }

  // 5 参数keepAliveTime=0试验
  // 超出核心线程数的线程，任务执行完毕后（空闲状态），立即从队列删除


}