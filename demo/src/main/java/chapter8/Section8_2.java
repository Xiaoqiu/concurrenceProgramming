package chapter8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author kate
 * @create 2019/7/25
 * @since 1.0.0
 */
public class Section8_2 {
  class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
      try {
        System.out.println("callA begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        Thread.sleep(3000);
        System.out.println("callA end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return "returen A";
    }
  }

  class MyCallableB implements Callable<String> {
    @Override
    public String call() throws Exception {
      try {
        System.out.println("callb begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        System.out.println("callB end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return "returen B";
    }
  }

  @Test
  public void test() {
    try {
      List<Callable> callables = new ArrayList<>();
      callables.add(new MyCallableA());
      callables.add(new MyCallableB());
      ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
      ScheduledFuture<String> futureA = pool.schedule(callables.get(0),4L,TimeUnit.SECONDS);
      ScheduledFuture<String> futureB = pool.schedule(callables.get(1),4L,TimeUnit.SECONDS);
      System.out.println(" begin " + System.currentTimeMillis());
      System.out.println("return a: " + futureA.get());
      System.out.println("return b: " + futureB.get());
      System.out.println(" end " + System.currentTimeMillis());

    } catch (InterruptedException e) {
      e.printStackTrace();
    }catch (ExecutionException e) {
      e.printStackTrace();
    }

  }
}