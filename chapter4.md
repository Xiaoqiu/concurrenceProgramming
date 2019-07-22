chapter4 Executor & ThreadPoolExecutor
## 4.1 Executor接口介绍
- 与线程池相关的大部分都是实现这个接口
- 只有一个execute()方法
- 父接口：Executor
    - 方法：execute()
    - 子接口：ExecutorService
        - 方法：
        - shutdown(), 
        - shutdownNow(),
        - isShutdown()
        - isTerminated()
        - awaitTerminated()
        - submit(...)
        - invokeAll(...)
        - invokeAny(...)
        - 唯一子类：AbstractExecutorService (抽象的也不能实例化)
            - 子类：ThreadPoolExecutor (可以实例化)
    - 子接口：ScheduledExecutorService
## 4.2 使用Executors工厂类创建线程池
- Executors仅仅3是一种规范，一种声明，一种定义，没有任何功能，
- 只是使用ThreadPoolExecutor需要参数太多，建议使用Executors工厂来创建线程池对象
### 4.2.1 使用newCachedThreadPool()方法创建无界线程池
- 池中存放的线程个数理论上是Integer.MAX_VALUE最大值


### 4.2.2 验证newCachedThreadPool()创建为Thread池
- 创建了线程池, 但是没有达到线程复用的效果

### 4.2.3 使用newCachedThreadPool(ThreadFactory)定制线程工厂
- 无界线程池中的Thread类可以自定义
- 使用线程的工厂接口：ThreadFactory

### 4.2.4 使用newFixedThreadPool(int) 方法创建有界线程池
- 创建有界线程池，指定池中线程个数醉倒数量

### 4.2.5 使用newFixedThreadPool(int, ThreadFactory)定制线程工厂
- 
### 4.2.6 使用newSingleThreadExecutor()方法创建单一线程池
- 单一线程池可以实现以队列的方式执行任务

### 4.2.7 使用newSingleThreadExecutor(ThreadFactory)定制线程工厂
## 4.3 ThreadPoolExecutor的使用
## 4.4 本章小结 
