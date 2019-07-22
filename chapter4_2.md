chapter4 Executor接口介绍
## 4.3 ThreadPoolExecutor的使用
### 4.3.1 构造方法的测试
- ThreadPoolExecutor最常用的构造方法：
- ThreadPoolExecutor(int corePoolSize, int maximmPoolSize, long keepAliveTime, TimeUnit unit,BlockingQueue<Runnable> workQueue)
- corePoolSize: 池中保存的线程数，包括空闲线程，也是核心池的大小
- maximmPoolSize: 池中允许的最大线程数
- keepAliveTime: 当线程数量大于corePoolSize值时，在没有超过指定的时间内是不从线程池中将空闲线程删除的，如果超出此单位时间，则删除
- unit: keepAliveTime参数的时间单位
- workQueue: 执行前用于保持任务的队列。这个队列保存由execute方法提交的Runnable任务

- 1）A : execute(runnable)欲执行的runnable的数量
- 2）B : corePoolSize
- 3) C : maximumPoolSize
- 4) D : 代表A-B(假设A>=B)
- 5）E : new LinkedBlockingDeque<Runnable>() : 队列，无构造参数 （超过核心线程数，考虑先放入队列）
- 6）F ：SynchronousQueue队列 （超过核心线程数，考虑马上创建线程执行超过核心线程数的任务，执行完成任务后，按照超时时间清楚这部分超出核心线程数的线程）
- 7）G ：keepAliveTime

- 构造方法的5个参数之间的关系，有一下5中情况：
- 1 欲执行的runnable数量小于或者等于核心线程数： 马上创建线程执行任务，并不用放入队列中，其他参数功能忽略。
- 2 欲执行runnable数量大于核心线程数，并且小于或等于最大线程数，并且是linkedBlockingQueue: 最大线程数和keepAliveTime参数忽略，并把超出核心线程数的任务放入队列。
- 3 欲执行runnable数量大于核心线程数，并且小于或等于最大线程数，并且是SynchronousQueue队列: 最大线程数和keepAliveTime参数有效，把超过核心线程数的任务，马上创建线程执行，执行完成后，按照超时时间清理超过核心线程数这部分线程。
- 4 欲执行runnable数量大于核心线程数，并且大于最大线程数，并且是linkedBlockingQueue： 最大线程数和keepAliveTime参数忽略，并把超出核心线程数的任务放入队列。
- 5 欲执行runnable数量大于核心线程数，并且大于最大线程数，并且是SynchronousQueue队列: 把超过核心线程数的任务，马上创建线程执行，其他超过最大线程数的任务不再处理抛出异常。











