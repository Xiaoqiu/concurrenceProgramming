package chapter2;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author kate
 * @create 2019/7/13
 * @since 1.0.0
 */
public class Section2_1_2 {
  class MyThread extends Thread{
    private CountDownLatch maxRuner;

    public MyThread( CountDownLatch maxRuner) {
      this.maxRuner = maxRuner;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName());
        // 计数器减一， 如果计数器减少为0，呈现wait的主线程都继续往下执行
        maxRuner.countDown();
        System.out.println("我是" + Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void test() throws InterruptedException{
    CountDownLatch maxRunner = new CountDownLatch(10);
    // 获取当前计数器的数量，启动这个数量的线程
    MyThread[] tArray = new MyThread[Integer.parseInt("" + maxRunner.getCount())];
    for (int i = 0; i < tArray.length - 1; i++) {
      // 没启动一个线程就会在run方法里面把计数器减1，如果计数器减少为0，呈现wait状态的主线程都继续往下执行，解除阻塞。
      tArray[i] = new MyThread(maxRunner);
      tArray[i].setName("线程" + (i + 1));
      tArray[i].start();
    }
    // 判断当前计数器的数量，如果不为0，就把当前使用CountDownLatch类的线程呈现阻塞状态。
    // 线程必须都到达同步点后才可以继续向下运行。
    maxRunner.await();
    System.out.println("都回来了");
  }
}