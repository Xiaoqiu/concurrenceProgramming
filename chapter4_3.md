### 4.3.6 工厂ThreadFactory + execute() + UncaughtExceptionHandler处理异常
- 子线程可以通过throw的方式抛出异常到主线程
- ThreadFactory ： 定制线程池中的线程， 并可以自定义异常捕获
- 