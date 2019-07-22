package chapter2;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author kate
 * @create 2019/7/13
 * @since 1.0.0
 */
public class Section2_1_4 {
  /**
   * 裁判需要等待运动员准备好，裁判await, 运动员countdown
   * 运动员需要等待裁判下命令，运动员await,裁判countdown
   * 裁判只有一个，所以裁判countdown的计数器是1
   * 运动员是n个，所以运动员的countdown的计数器是n
   *
   */
  class MyThread extends Thread {
    // 裁判等待所有运动员到来
    private CountDownLatch commingTag;
    // 运动员等待裁判说准备开始
    private CountDownLatch waitTag;
    // 裁判等待运动员做好准备姿势
    private CountDownLatch waitRunTag;
    // 运动员等待裁判说起跑
    private CountDownLatch beginTag;
    // 裁判等待所有运动员到底终点
    private CountDownLatch endTag;

    public MyThread(CountDownLatch commingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag, CountDownLatch endTag) {
      this.commingTag = commingTag;
      this.waitTag = waitTag;
      this.waitRunTag = waitRunTag;
      this.beginTag = beginTag;
      this.endTag = endTag;
    }

    @Override
    public void run() {

    }
  }

  @Test
  public void test() throws InterruptedException{

  }
}