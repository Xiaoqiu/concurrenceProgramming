package chapter5;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author kate
 * @create 2019/7/25
 * @since 1.0.0
 */
public class Section5_6 {
  class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
      Integer.parseInt("a");
    //  Thread.sleep(10000);
      System.out.println("sleep 10 s");
      return "anyString";
    }
  }

  @Test
  public void test() throws InterruptedException{
    MyCallable myCallable = new MyCallable();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,5L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    Future<String> future = pool.submit(myCallable);
    try {
      // 5 秒没有返回值就抛出异常TimeoutException
      System.out.println("返回值： " + future.get(5,TimeUnit.SECONDS));
    } catch (TimeoutException e) {
      System.out.println("进入TimeoutException");
      e.printStackTrace();
      //ExecutionException 可以捕获从callable抛出的异常
    } catch (ExecutionException e) {
      System.out.println("进入ExecutionException");
      e.printStackTrace();
    }


  }
}