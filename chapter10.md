chapter 10 并发集合框架
## 10.1 集合框架结构简要
- 接口Iterable
- 接口Collection

## 10.2 非阻塞队列
- 队列空，出现异常，或者返回null,不具有等待，阻塞的特色。
- 类ConcurrentHashMap
- ConcurrentSkipListMap
- ConcurrentSkipListSet
- ConcurrentLinkedQueue
- ConcurrentLinkedDeque
- CopyOnWriteArrayList
- CopyOnWriteArraySet



## 10.3 阻塞队列
- 如果队列空，获取方法阻塞
- 如果队列满，添加方法阻塞
### 10.3.1 类ArrayBlockingQueue的使用
- 有界的
- 如果队列是满，put()方法阻塞;
- 如果队列是空，take()方法阻塞

### 10.3.2 类PriorityBlockingQueue的使用
- 支持并发情况下优先级
### 10.3.3 类LinkedBlockingQueue的使用
- 无界的，也可以定义有界的
- 只支持对队列头的操作
### 10.3.4 类LinkedBlockingDeque的使用
- 队列两端都可以操作

### 10.3.5 类SynchronousQueue的使用
- 同步队列，没有容量。
- 每个插入操作都要等待另一个线程的移除操作
- 经常用在多个线程之间传递数据使用

### 10.3.6 类DelayQueue的使用
- 延迟执行任务的队列

### 10.3.7 类LinkedTransferQueue的使用
- 和同步队列类似，但是可以尝试性添加数据

