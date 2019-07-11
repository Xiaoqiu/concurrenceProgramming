chapter1 Semaphore和Exchanger的使用
## 1.1 Semaphore的使用
- synchronized的升级版
- 控制线程并发数量，synchronized做不到

### 1.1.1 类Semaphore的同步性
- 构造函数里面的permits是许可个数，代表可以执行acquire()和release()的线程个数

### 1.1.2 类Semaphore构造方法permit参数作用
- 设置许可个数
- private Semaphore semaphore = new Semaphore(1)
- permit > 1: 不再保证线程安全。

### 1.1.3 方法acquire(int permits)参数作用以及动态添加permit许可数量
- semaphore.acquire(2);
- 同一时间允许2个线程执行acquire()和release()之间的代码。
- 多次调用release() 或者release(int)方法，可以动态增加permits

### 1.1.4 方法acquireUninterruptibly()的使用
- 使得等待进入acquire()的方法的线程不允许被中断
- 
## 1.2 Exchanger的使用
- 两个线程之间方便通信
