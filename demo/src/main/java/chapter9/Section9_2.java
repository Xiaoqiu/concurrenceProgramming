package chapter9;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author kate
 * @create 2019/7/25
 * @since 1.0.0
 */
public class Section9_2 {
   class MyRecursiveAction extends RecursiveAction {
     @Override
     protected void compute() {
       System.out.println("compute run");
     }
   }

   @Test
   public void test() throws InterruptedException{
     ForkJoinPool pool = new ForkJoinPool();
     pool.submit(new MyRecursiveAction());
     Thread.sleep(5000);
   }
}