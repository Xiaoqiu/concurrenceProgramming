# chapter2 CountDownLatch和CyclicBarrier的使用
- 线程组团做任务
## 2.1 CountDownLatch的使用
- 门没有打开的时候，n个线程是不能继续向下运行的。控制线程执行任务的时机。
- 可以使线程组团的方式执行任务
- 使用CountDownLatch这个类的线程判断计数器不为0时，则这个线程呈现为wait状态，如果为0时则继续运行。
- await(): 判断计数器是否为0，如果不为0, 当前线程呈现等待wait状态。
- countDown() : 其他线程可以调用这个方法，将计数器减1，当减到0时，呈现wait状态的线程继续运行。
- getCount() : 获得当前计数个数
- 计数器无法重置，需要重置，考虑使用CyclicBarrier类
- CountDownLatch类的计数是减法操作
### 2.1.1 初步使用

### 2.1.2 裁判在等全部的运动员到来
- 线程必须到达同步点后才可以继续向下运行

### 2.1.3 各就各位准备比赛
- 裁判要等待所有运行员各就各位后，全部准备完毕，再开始比赛的效果

### 2.1.4 完整的比赛流程
- 所有线程呈现await后，再统一唤醒的效果。
- 实现业务要求的同步效果


### 2.1.5 await(long timeout,TimeUnit unit) 
### 2.1.6 getCount()的使用

## 2.2 CyclicBarrier的使用
### 2.2.1 初步使用
- 设定数量（5个）的线程执行了await方法才能往下执行，否则彼此等待，阻塞状态。

### 2.2.2 验证屏障重置性getNumberWaiting() 方法的使用

### 2.2.3 用CyclicBarrier类实现阶段跑步比赛
### 2.2.4 方法isBroken()的使用
- 查询此屏障是否处于损坏状态

### 2.2.5 方法await(long timeout,TimeUnit unit) 超时出现异常的测试
### 2.2.6 getNumberWaiting(), getParties()
### 2.2.7 reset()

## 2.3 本章总结
