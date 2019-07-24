### 4.3.6 工厂ThreadFactory + execute() + UncaughtExceptionHandler处理异常
- 子线程可以通过throw的方式抛出异常到主线程
- ThreadFactory ： 定制线程池中的线程， 并可以自定义异常捕获
-

### 4.3.11 常见的3中队列结合max值的因果效果
- ArrayBlockingQueue : 指定队列存储元素多少（必须指定）， 超过核心线程数的任务入队，超过队列长度，创建线程执行，超过最大线程数的抛出异常RejectedExecutionException
- LinkedBlockingDeque ： 指定队列存储元素多少（可以不指定，默认为Integer.MAX_VALUE）, 超过核心线程数的任务入队，超过队列长度，创建线程执行，超过最大线程数的抛出异常RejectedExecutionException
    - 一般不指定大小吧，这样就超过核心线程数的任务可以一直入队。
- SynchronousQueue ：队列长度一直是0，任务直接创建线程执行，超过最大线程数直接抛异常RejectedExecutionException
 
### 4.3.12 线程池ThreadPoolExecutor的拒绝策略--4种
- 1 AbortPolicy: 当任务被线程池拒绝时，它将抛出RejectedExecutionException
- 2 CallerRunsPolicy: 。。。，调用线程池的Thread线程处理被拒绝的任务。
- 3 DiscardOldestPolicy: 线程池会放弃等待队列中最旧的未处理任务，然后被拒绝的任务添加大盘队列
- 4 DiscardPolicy: 线程池将丢弃被拒绝的任务。



 