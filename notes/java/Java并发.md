<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [什么是线程安全，怎么保证线程安全？](#%E4%BB%80%E4%B9%88%E6%98%AF%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E6%80%8E%E4%B9%88%E4%BF%9D%E8%AF%81%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8)
- [如何保证线程安全](#%E5%A6%82%E4%BD%95%E4%BF%9D%E8%AF%81%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8)
- [JAVA 线程状态转换图示](#java-%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E5%9B%BE%E7%A4%BA)
  - [线程为什么调用start()而不是直接调用run()](#%E7%BA%BF%E7%A8%8B%E4%B8%BA%E4%BB%80%E4%B9%88%E8%B0%83%E7%94%A8start%E8%80%8C%E4%B8%8D%E6%98%AF%E7%9B%B4%E6%8E%A5%E8%B0%83%E7%94%A8run)
  - [阻塞，等待，挂起，休眠的区别](#%E9%98%BB%E5%A1%9E%E7%AD%89%E5%BE%85%E6%8C%82%E8%B5%B7%E4%BC%91%E7%9C%A0%E7%9A%84%E5%8C%BA%E5%88%AB)
- [多线程上下文切换的影响](#%E5%A4%9A%E7%BA%BF%E7%A8%8B%E4%B8%8A%E4%B8%8B%E6%96%87%E5%88%87%E6%8D%A2%E7%9A%84%E5%BD%B1%E5%93%8D)
- [synchronized 的底层怎么实现](#synchronized-%E7%9A%84%E5%BA%95%E5%B1%82%E6%80%8E%E4%B9%88%E5%AE%9E%E7%8E%B0)
  - [jdk1.6以后对synchronized锁做了哪些优化](#jdk16%E4%BB%A5%E5%90%8E%E5%AF%B9synchronized%E9%94%81%E5%81%9A%E4%BA%86%E5%93%AA%E4%BA%9B%E4%BC%98%E5%8C%96)
- [Java有哪些锁？](#java%E6%9C%89%E5%93%AA%E4%BA%9B%E9%94%81)
- [讲一下CAS](#%E8%AE%B2%E4%B8%80%E4%B8%8Bcas)
  - [CAS到底最后加没加锁](#cas%E5%88%B0%E5%BA%95%E6%9C%80%E5%90%8E%E5%8A%A0%E6%B2%A1%E5%8A%A0%E9%94%81)
- [CountDownLatch与CyclicBarrier的比较](#countdownlatch%E4%B8%8Ecyclicbarrier%E7%9A%84%E6%AF%94%E8%BE%83)
  - [源码上的区别](#%E6%BA%90%E7%A0%81%E4%B8%8A%E7%9A%84%E5%8C%BA%E5%88%AB)
- [对象锁和静态锁之间的区别](#%E5%AF%B9%E8%B1%A1%E9%94%81%E5%92%8C%E9%9D%99%E6%80%81%E9%94%81%E4%B9%8B%E9%97%B4%E7%9A%84%E5%8C%BA%E5%88%AB)
- [简述volatile字](#%E7%AE%80%E8%BF%B0volatile%E5%AD%97)
  - [volatile为什么不能保证原子性？](#volatile%E4%B8%BA%E4%BB%80%E4%B9%88%E4%B8%8D%E8%83%BD%E4%BF%9D%E8%AF%81%E5%8E%9F%E5%AD%90%E6%80%A7)
  - [synchronized 和 volatile 的区别是什么？](#synchronized-%E5%92%8C-volatile-%E7%9A%84%E5%8C%BA%E5%88%AB%E6%98%AF%E4%BB%80%E4%B9%88)
- [happens-before 原则（先行发生原则）](#happens-before-%E5%8E%9F%E5%88%99%E5%85%88%E8%A1%8C%E5%8F%91%E7%94%9F%E5%8E%9F%E5%88%99)
  - [as-if-serial规则和happens-before规则的区别](#as-if-serial%E8%A7%84%E5%88%99%E5%92%8Chappens-before%E8%A7%84%E5%88%99%E7%9A%84%E5%8C%BA%E5%88%AB)
- [Lock 和synchronized 的区别](#lock-%E5%92%8Csynchronized-%E7%9A%84%E5%8C%BA%E5%88%AB)
  - [什么情况下可以使用 ReentrantLock](#%E4%BB%80%E4%B9%88%E6%83%85%E5%86%B5%E4%B8%8B%E5%8F%AF%E4%BB%A5%E4%BD%BF%E7%94%A8-reentrantlock)
- [什么时候用重入锁，什么时候用非重入锁？](#%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E7%94%A8%E9%87%8D%E5%85%A5%E9%94%81%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E7%94%A8%E9%9D%9E%E9%87%8D%E5%85%A5%E9%94%81)
- [AQS是如何唤醒下一个线程的？](#aqs%E6%98%AF%E5%A6%82%E4%BD%95%E5%94%A4%E9%86%92%E4%B8%8B%E4%B8%80%E4%B8%AA%E7%BA%BF%E7%A8%8B%E7%9A%84)
- [ThreadLocal(线程变量副本)](#threadlocal%E7%BA%BF%E7%A8%8B%E5%8F%98%E9%87%8F%E5%89%AF%E6%9C%AC)
  - [Threadlocal和run方法的局部变量的区别](#threadlocal%E5%92%8Crun%E6%96%B9%E6%B3%95%E7%9A%84%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F%E7%9A%84%E5%8C%BA%E5%88%AB)
  - [ThreadLocal 适用于如下两种场景](#threadlocal-%E9%80%82%E7%94%A8%E4%BA%8E%E5%A6%82%E4%B8%8B%E4%B8%A4%E7%A7%8D%E5%9C%BA%E6%99%AF)
  - [ThreadLocal内存泄露](#threadlocal%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2)
  - [父子线程传递Threadlcoal值的问题](#%E7%88%B6%E5%AD%90%E7%BA%BF%E7%A8%8B%E4%BC%A0%E9%80%92threadlcoal%E5%80%BC%E7%9A%84%E9%97%AE%E9%A2%98)
- [通过Callable和Future创建线程](#%E9%80%9A%E8%BF%87callable%E5%92%8Cfuture%E5%88%9B%E5%BB%BA%E7%BA%BF%E7%A8%8B)
- [什么叫守护线程，用什么方法实现守护线程（Thread.setDeamon()的含义）](#%E4%BB%80%E4%B9%88%E5%8F%AB%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E7%94%A8%E4%BB%80%E4%B9%88%E6%96%B9%E6%B3%95%E5%AE%9E%E7%8E%B0%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8Bthreadsetdeamon%E7%9A%84%E5%90%AB%E4%B9%89)
- [如何停止一个线程？](#%E5%A6%82%E4%BD%95%E5%81%9C%E6%AD%A2%E4%B8%80%E4%B8%AA%E7%BA%BF%E7%A8%8B)
- [Java 中 interrupted 和 isInterrupted 方法的区别？](#java-%E4%B8%AD-interrupted-%E5%92%8C-isinterrupted-%E6%96%B9%E6%B3%95%E7%9A%84%E5%8C%BA%E5%88%AB)
- [线程的 sleep()方法和 yield()方法有什么区别？](#%E7%BA%BF%E7%A8%8B%E7%9A%84-sleep%E6%96%B9%E6%B3%95%E5%92%8C-yield%E6%96%B9%E6%B3%95%E6%9C%89%E4%BB%80%E4%B9%88%E5%8C%BA%E5%88%AB)
- [final域的内存语义](#final%E5%9F%9F%E7%9A%84%E5%86%85%E5%AD%98%E8%AF%AD%E4%B9%89)
  - [写final域的重排序规则](#%E5%86%99final%E5%9F%9F%E7%9A%84%E9%87%8D%E6%8E%92%E5%BA%8F%E8%A7%84%E5%88%99)
  - [读final域的重排序规则](#%E8%AF%BBfinal%E5%9F%9F%E7%9A%84%E9%87%8D%E6%8E%92%E5%BA%8F%E8%A7%84%E5%88%99)
- [notify和notifyAll的区别](#notify%E5%92%8Cnotifyall%E7%9A%84%E5%8C%BA%E5%88%AB)
- [ConcurrentHashMap是如何在保证并发安全的同时提高性能](#concurrenthashmap%E6%98%AF%E5%A6%82%E4%BD%95%E5%9C%A8%E4%BF%9D%E8%AF%81%E5%B9%B6%E5%8F%91%E5%AE%89%E5%85%A8%E7%9A%84%E5%90%8C%E6%97%B6%E6%8F%90%E9%AB%98%E6%80%A7%E8%83%BD)
- [为什么java.util.concurrent 包里没有并发的ArrayList实现？](#%E4%B8%BA%E4%BB%80%E4%B9%88javautilconcurrent-%E5%8C%85%E9%87%8C%E6%B2%A1%E6%9C%89%E5%B9%B6%E5%8F%91%E7%9A%84arraylist%E5%AE%9E%E7%8E%B0)
- [比AtomicLong更高性能的LongAdder](#%E6%AF%94atomiclong%E6%9B%B4%E9%AB%98%E6%80%A7%E8%83%BD%E7%9A%84longadder)
- [两个线程同时执行i++100次,结果是多少](#%E4%B8%A4%E4%B8%AA%E7%BA%BF%E7%A8%8B%E5%90%8C%E6%97%B6%E6%89%A7%E8%A1%8Ci100%E6%AC%A1%E7%BB%93%E6%9E%9C%E6%98%AF%E5%A4%9A%E5%B0%91)
- [如何排查死锁？](#%E5%A6%82%E4%BD%95%E6%8E%92%E6%9F%A5%E6%AD%BB%E9%94%81)
- [线程池](#%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [ThreadPoolExecutor执行的策略](#threadpoolexecutor%E6%89%A7%E8%A1%8C%E7%9A%84%E7%AD%96%E7%95%A5)
  - [常见四种线程池](#%E5%B8%B8%E8%A7%81%E5%9B%9B%E7%A7%8D%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [四种线程池使用场景](#%E5%9B%9B%E7%A7%8D%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
  - [四种拒绝策略](#%E5%9B%9B%E7%A7%8D%E6%8B%92%E7%BB%9D%E7%AD%96%E7%95%A5)
  - [为什么要用线程池](#%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E7%94%A8%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [线程池的非核心线程什么时候会被释放](#%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E9%9D%9E%E6%A0%B8%E5%BF%83%E7%BA%BF%E7%A8%8B%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E4%BC%9A%E8%A2%AB%E9%87%8A%E6%94%BE)
  - [Executorshe和ThreaPoolExecutor创建线程池的区别](#executorshe%E5%92%8Cthreapoolexecutor%E5%88%9B%E5%BB%BA%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E5%8C%BA%E5%88%AB)
  - [线程池ThreadPoolExecutor参数设置](#%E7%BA%BF%E7%A8%8B%E6%B1%A0threadpoolexecutor%E5%8F%82%E6%95%B0%E8%AE%BE%E7%BD%AE)
    - [corePoolSize](#corepoolsize)
    - [maxPoolSize](#maxpoolsize)
    - [queueCapacity](#queuecapacity)
    - [keepAliveTime](#keepalivetime)
    - [allowCoreThreadTimeout](#allowcorethreadtimeout)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 什么是线程安全，怎么保证线程安全？
线程安全可以简单理解为一个方法或者一个实例可以在多线程环境中使用而不会出现问题
   
## 如何保证线程安全
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-3.jpg)


## JAVA 线程状态转换图示
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-19.png)

线程共包括以下5种状态。
1. 新建状态(New)         : 线程对象被创建后，就进入了新建状态。例如，Thread thread = new Thread()。

2. 就绪状态(Runnable): 也被称为“可执行状态”。线程对象被创建后，其它线程调用了该对象的start()方法，从而来启动该线程。例如，thread.start()。处于就绪状态的线程，随时可能被CPU调度执行。

3. 运行状态(Running) : 线程获取CPU权限进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。

4. 阻塞状态(Blocked)  : 阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：

    1、等待阻塞 -- 通过调用线程的wait()方法，让线程等待某工作的完成。
    
    2、同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态。
    
    3、 其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。

5. 死亡状态(Dead)    : 线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

    1、run()或者call()方法执行完成，线程正常结束；
    
    2、线程抛出一个未捕获的Exception或Error；
    
    3、直接调用该线程的stop()方法来结束该线程；
    
    
线程进入等待状态有三种方式：
 1. cpu调度给优先级更高的线程
 2. 线程要等待获得资源或者信号
 3. 时间片的轮转，时间片到了，进入等待状态

### 线程为什么调用start()而不是直接调用run()
1. run()方法只是一个类中的普通方法，直接执行和普通的方法没有设么两样
2. start()方法则不同，它首先做了创建线程等一系列工作，然后调用行的run()方法

所以：start() 创建新进程 ，run() 并没有

新建线程
```java
Thread thread = new Thread();
thread.start();
```
这样就开启了一个线程。
有一点需要注意的是
```java
Thread thread = new Thread();
thread.run();
```
直接调用run方法是无法开启一个新线程的。直接调用run其实就是一个普通的函数调用而已，并没有达到多线程的作用


start方法其实是在一个新的操作系统线程上面去调用run方法。换句话说，直接调用run方法而不是调用start方法的话，它并不会开启新的线程，而是在调用run的当前的线程当中执行你的操作。

### 阻塞，等待，挂起，休眠的区别
阻塞是线程的状态，等待、挂起和休眠是让线程进入阻塞状态的不同行为。等待是线程因为需要等待外部某个条件而进入阻塞，等条件满足后再继续运行(比如等待IO信号)。挂起线程主动让出CPU，等别的线程去唤醒它（比如如join）。休眠是线程主动让出CPU一段时间而进入阻塞状态，等时间到之后再继续运行(比如sleep(time))。


## 多线程上下文切换的影响
**多线程上下文切换的影响**
- 切换带来的性能损耗

**引起上下文切换的原因**
1. 时间片用完，CPU正常调度下一个任务
2. 被其他优先级更高的任务抢占
3. 执行任务碰到IO阻塞，调度器挂起当前任务，切换执行下一个任务
4. 用户代码主动挂起当前任务让出CPU时间
5. 多任务抢占资源，由于没有抢到被挂起
6. 硬件中断

**如何减少上下文切换**
1. 无锁并发编程。多线程竞争时，会引起上下文切换，所以多线程处理数据时，可以用一些办法来避免使用锁，如将数据的ID按照Hash取模分段，不同的线程处理不同段的数据，队列实现异步串型无锁化。
2. CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁
3. 使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态
4. 协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换


## synchronized 的底层怎么实现
1. **同步代码块**(Synchronization)基于进入和退出管程(Monitor)对象实现。每个对象有一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，过程如下：

- 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。

- 如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.

- 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。
2. **被 synchronized 修饰的同步方法**并没有通过指令monitorenter和monitorexit来完成（理论上其实也可以通过这两条指令来实现），不过相对于普通方法，其常量池中多了ACC_SYNCHRONIZED标示符。JVM就是根据该标示符来实现方法的同步的：当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，获取成功之后才能执行方法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对象。 其实本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来完成



### jdk1.6以后对synchronized锁做了哪些优化
**锁的级别从低到高：**
无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-18.png)
**锁分级别原因：**

没有优化以前，sychronized是重量级锁（悲观锁），使用 wait 和 notify、notifyAll 来切换线程状态非常消耗系统资源；线程的挂起和唤醒间隔很短暂，这样很浪费资源，影响性能。所以 JVM 对 sychronized 关键字进行了优化，把锁分为 无锁、偏向锁、轻量级锁、重量级锁 状态。

**无锁**：没有对资源进行锁定，所有的线程都能访问并修改同一个资源，但同时只有一个线程能修改成功，其他修改失败的线程会不断重试直到修改成功。

**偏向锁**：对象的代码一直被同一线程执行，不存在多个线程竞争，该线程在后续的执行中自动获取锁，降低获取锁带来的性能开销。偏向锁，指的就是偏向第一个加锁线程，该线程是不会主动释放偏向锁的，只有当其他线程尝试竞争偏向锁才会被释放。

**偏向锁的撤销**，需要在某个时间点上没有字节码正在执行时，先暂停拥有偏向锁的线程，然后判断锁对象是否处于被锁定状态。如果线程不处于活动状态，则将对象头设置成无锁状态，并撤销偏向锁；

如果线程处于活动状态，升级为轻量级锁的状态。

**轻量级锁**：轻量级锁是指当锁是偏向锁的时候，被第二个线程 B 所访问，此时偏向锁就会升级为轻量级锁，线程 B 会通过自旋的形式尝试获取锁，线程不会阻塞，从而提高性能。

当前只有一个等待线程，则该线程将通过自旋进行等待。
**两种情况轻量锁会升级到重量锁：**
1. 当自旋超过一定的次数时
2. 第三个线程来访时

**重量级锁**：指当有一个线程获取锁之后，其余所有等待获取该锁的线程都会处于阻塞状态。

重量级锁通过对象内部的监视器（monitor）实现，而其中 monitor 的本质是依赖于底层操作系统的 **Mutex Lock**实现，操作系统实现线程之间的切换需要从用户态切换到内核态，切换成本非常高。




## Java有哪些锁？
- 公平锁/非公平锁
- 可重入锁
- 独享锁/共享锁
- 互斥锁/读写锁
- 乐观锁/悲观锁
- 分段锁
- 偏向锁/轻量级锁/重量级锁
- 自旋锁




## 讲一下CAS
CAS,compare and swap的缩写，中文翻译成比较并交换。乐观锁用到的机制就是CAS，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试。

原理：

1. CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。

JDK文档说cas同时具有volatile读和volatile写的内存语义。

缺点：

1. ABA问题。
因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，又变成了A，那么使用CAS进行检查时会发现它的值没有发生变化

2. 循环时间长开销大。
自旋CAS如果长时间不成功，会给CPU带来非常大的执行开销。
3.  只能保证一个共享变量的原子操作。
对多个共享变量操作时，循环CAS就无法保证操作的原子性，这个时候就可以用锁，或者有一个取巧的办法，就是把多个共享变量合并成一个共享变量来操作。比如有两个共享变量i＝2,j=a，合并一下ij=2a，然后用CAS来操作ij。从Java1.5开始JDK提供了AtomicReference类来保证引用对象之间的原子性，你可以把多个变量放在一个对象里来进行CAS操作。

###  CAS到底最后加没加锁
首先使用Unsafe类中的compareAndSwapInt方法实现。
LOCK_IF_MP根据当前系统是否为多核处理器决定是否为cmpxchg指令添加lock前缀
1. 如果是多处理器，为cmpxchg指令添加lock前缀。
2. 反之，就省略lock前缀。（单处理器会不需要lock前缀提供的内存屏障效果）



## CountDownLatch与CyclicBarrier的比较
CountDownLatch与CyclicBarrier都是用于控制并发的工具类，都可以理解成维护的就是一个计数器，但是这两者还是各有不同侧重点的：

1. CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；CountDownLatch强调一个线程等多个线程完成某件事情。CyclicBarrier是多个线程互等，等大家都完成，再携手共进。
2. 调用CountDownLatch的countDown方法后，当前线程并不会阻塞，会继续往下执行；而调用CyclicBarrier的await方法，会阻塞当前线程，直到CyclicBarrier指定的线程全部都到达了指定点的时候，才能继续往下执行；
3. CountDownLatch方法比较少，操作比较简单，而CyclicBarrier提供的方法更多，比如能够通过getNumberWaiting()，isBroken()这些方法获取当前多个线程的状态，并且CyclicBarrier的构造方法可以传入barrierAction，指定当所有线程都到达时执行的业务功能；
4. CountDownLatch是不能复用的，而CyclicLatch是可以复用的。


### 源码上的区别

**CountDownLatch底层是使用AQS**
- 当我们调用CountDownLatch countDownLatch=new CountDownLatch(4) 时候，此时会创建一个AQS的同步队列，并把创建CountDownLatch 传进来的计数器赋值给AQS队列的 state，所以state的值也代表CountDownLatch所剩余的计数次数；（state：同步状态，多少线程获取锁）
- 当我们调用countDownLatch.wait()的时候，会创建一个节点，加入到AQS阻塞队列，并同时把当前线程挂起。
- 当执行 CountDownLatch 的 countDown（）方法，将计数器减一，也就是state减一，当减到0的时候，等待队列中的线程被释放。是调用 AQS 的 releaseShared 方法来实现的。（tryreleaseshared：通过设置同步状态尝试释放资源，如果释放后允许唤醒后续等待结点返回true，否则返回false）
- 因为这是共享型的，当计数器为 0 后，会唤醒等待队列里的所有线程，所有调用了 await() 方法的线程都被唤醒，并发执行。这种情况对应到的场景是，有多个线程需要等待一些动作完成。

**CyclicBarrier底层是使用ReentrantLock(独占锁)和Condition**
- 每当线程执行await，内部变量count减1，如果count！= 0，说明有线程还未到屏障处，则在锁条件变量trip上等待。
- 当count == 0时，说明所有线程都已经到屏障处，执行条件变量的signalAll方法唤醒等待的线程。
- 其中 nextGeneration方法可以实现屏障的循环使用：重新生成Generation对象，恢复count值，如果generation.broken为true的话，说明这个屏障已经损坏，当某个线程await的时候，直接抛出异常
- 在CyclicBarrier中，同一批的线程属于同一代，即同一个Generation；CyclicBarrier中通过generation对象，记录属于哪一代。当有parties个线程到达barrier，generation就会被更新换代。达到了循环使用









## 对象锁和静态锁之间的区别
1. 对象锁用于对象实例方法，
2. 类锁用于类的静态方法或一个类的class对象。
3. 类的对象实例可以有很多，不同对象实例的对象锁互不干扰，而每个类只有一个类锁 




##  简述volatile字
两个特性

1.  保证了不同线程对这个变量进行 读取 时的可见性，即一个线程修改
了某个变量的值 ， 这新值对其他线程来说是立即可见的 。(volatile 解决了
线程间 共享变量
2. 禁止进行指令重排序 ，阻止编译器对代码的优化

**要想并发程序正确地执行，必须要保证原子性、可见性以及有序性，锁保证了原子性，而volatile保证可见性和有序性**

### volatile为什么不能保证原子性？
修改volatile变量分为四步：
1. 读取volatile变量到local
2. 修改变量值
3. local值写回
4. 插入内存屏障，即lock指令，让其他线程可见这样就很容易看出来

前三步都是不安全的，取值和写回之间，不能保证没有其他线程修改。原子性需要锁来保证。

并发编程中得了解的三个问题，可见性，原子性，有序性。volatile 原本的语义是禁用cpu缓存,也就是导致可见性的源头。原子性一般通过锁机制解决。

volatile 关键字通过内存屏障禁止了指令的重排序，并在单个核心中，强制数据的更新及时更新到缓存。在此基础上，依靠多核心处理器的缓存一致性协议等机制，保证了变量的可见性。

这里介绍几个状态协议，先从最简单的开始，MESI协议，这个协议跟那个著名的足球运动员梅西没什么关系，其主要表示缓存数据有四个状态：Modified（已修改）, Exclusive（独占的）,Shared（共享的），Invalid（无效的）。

MESI 这种协议在数据更新后，会标记其它共享的CPU缓存的数据拷贝为Invalid状态，然后当其它CPU再次read的时候，就会出现 cache miss 的问题，此时再从内存中更新数据。


### synchronized 和 volatile 的区别是什么？
- volatile 是变量修饰符；synchronized 可以修饰类、方法、变量。
- volatile 仅能实现变量的修改可见性，不能保证原子性；而 synchronized 则可以保证变量的修改可见性和原子性。
- volatile 不会造成线程的阻塞；synchronized 可能会造成线程的阻塞。
- volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化。


## happens-before 原则（先行发生原则）
我们无法就所有场景来规定某个线程修改的变量何时对其他线程可见，但是我们可以指定某些规则，这规则就是happens-before。特别关注在多线程之间的内存可见性。

它是判断数据是否存在竞争、线程是否安全的主要依据，依靠这个原则，我们解决在并发环境下两操作之间是否可能存在冲突的所有问题。

1.  程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先行发生于书写在
后面的操作
2. 锁定规则：一个 unLock 操作先行发生于后面对同一个锁的 lock 操作
3. volatile 变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
4.  传递规则：如果操作 A 先行发生于操作 B，而操作 B 又先行发生于操作 C，则可以
得出操作 A 先行发生于操作 C
5.  线程启动规则：Thread 对象的 start()方法先行发生于此线程的每个一个动作
6.  线程中断规则：对线程 interrupt()方法的调用先行发生于被中断线程的代码检测
到中断事件的发生
7.  线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过 T
hread.join()方法结束、Thread.isAlive()的返回值手段检测到线程已经终止执行
8. 对象终结规则：一个对象的初始化完成先行发生于他的 finalize()方法的开始

### as-if-serial规则和happens-before规则的区别
as-if-serial语义保证单线程内程序的执行结果不被改变，happens-before关系保证正确同步的多线程程序的执行结果不被改变。

as-if-serial语义给编写单线程程序的程序员创造了一个幻境：单线程程序是按程序的顺序来执行的。happens-before关系给编写正确同步的多线程程序的程序员创造了一个幻境：正确同步的多线程程序是按happens-before指定的顺序来执行的。

as-if-serial语义和happens-before这么做的目的，都是为了在不改变程序执行结果的前提下，尽可能地提高程序执行的并行度。

## Lock 和synchronized 的区别
- Synchronized是关键字，是JVM层面的底层实现，而Lock是个接口，是JDK层面的有丰富API
- Synchronized会自动释放锁，而Lock必须手动释放锁
- Synchronized不可中断，Synchronized可以中断也可以不中断。
- 通过Lock可以知道线程没有拿到锁，而Synchronized不可以
- Synchronized可以锁住方法和代码块，而Lock只能锁住代码块
- Synchronized是非公平锁，Lock是可以控制是否公平锁


### 什么情况下可以使用 ReentrantLock
- 使用synchronized 的一些限制：
- 无法中断正在等候获取一个锁的线程；
- 无法通过投票得到一个锁；
- 释放锁的操作只能与获得锁所在的代码块中进行，无法在别的代码块中释放锁；
- ReentrantLock 没有以上的这些限制，且必须是手工释放锁。

## 什么时候用重入锁，什么时候用非重入锁？
可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。

不可重入锁，也可以叫非递归锁，就是拿不到锁的情况会不停自旋循环检测来等待，不进入内核态沉睡，而是在用户态自旋尝试。
-  可重入锁的作用就是为了避免死锁
-  非重入锁（自旋锁）比较适用于锁使用者保持锁时间比较短的情况，这种情况下自旋锁的效率要远高于互斥锁



## AQS是如何唤醒下一个线程的？
看出当前线程是否需要阻塞：
1. 如果当前线程节点的前驱节点为SINGAL状态，则表明当前线程处于等待状态，返回true，当前线程阻塞
2. 如果当前线程节点的前驱节点状态为CANCELLED（值为1），则表明前驱节点线程已经等待超时或者被中断，此时需要将该节点从同步队列中移除掉。最后返回false
3. 如果当前节点节点前驱节点非SINGAL，CANCELLED状态，则通过CAS将其前驱节点的等待状态设置为SINGAL，返回false。

当线程释放同步状态后，则需要唤醒该线程的后继节点：

可能会存在当前线程的后继节点为null，超时、被中断的情况，如果遇到这种情况了，则需要跳过该节点，但是为何是从tail尾节点开始，而不是从node.next开始呢？原因在于node.next仍然可能会存在null或者取消了，所以采用tail回溯办法找第一个可用的线程。最后调用LockSupport的unpark(Thread thread)方法唤醒该线程。






## ThreadLocal(线程变量副本)
Synchronized实现内存共享，ThreadLocal为每个线程维护一个本地变量。

采用空间换时间，它用于线程间的数据隔离，为每一个使用该变量的线程提供一个副本，每个线程都可以独立地改变自己的副本，而不会和其他线程的副本冲突。

ThreadLocal类中维护一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值为对应线程的变量副本。

注意：跟多线程并发问题没关系！！！

ThreadLocal 适用于每个线程需要自己独立的实例且该实例需要在多个方法中被使用，也即变量在线程间隔离而在方法或类间共享的场景。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-10.png)


### Threadlocal和run方法的局部变量的区别
1. ThreadLocal可以跨方法共享变量，ThreadLocal减少同一个线程多个方法函数或者组件之间一些公共变量的传递的复杂度
2. run局部变量只能在单个方法



### ThreadLocal 适用于如下两种场景
1. 每个线程需要有自己单独的实例
2. 实例需要在多个方法中共享，但不希望被多线程共享

比如：
- ThreadLocal在Spring中发挥着巨大的作用，在管理Request作用域中的Bean、事务管理、任务调度、AOP等模块都出现了它的身影。
- Spring中绝大部分Bean都可以声明成Singleton作用域，采用ThreadLocal进行封装，因此有状态的Bean就能够以singleton的方式在多线程中正常工作了。


### ThreadLocal内存泄露
ThreadLocal.ThreadLocalMap.Entry中的key是弱引用的，也即是当某个ThreadLocal对象不存在强引用时，就会被GC回收，但是value是基于强引用的，所以当key被回收，但是value还存在其他强引用时，就会出现内存的泄露情况，在最新的ThreadLocal中已经做出了修改，即在调用set、get、remove方法时，会清除key为null的Entry，但是如果不调用这些方法，仍然还是会出现内存泄漏 ：），所以要养成用完ThreadLocal对象之后及时remove的习惯。


### 父子线程传递Threadlcoal值的问题
InheritableThreadLocal为什么能解决父子线程传递Threadlcoal值的问题。
- 在创建InheritableThreadLocal对象的时候赋值给线程的t.inheritableThreadLocals变量
- 在创建新线程的时候会check父线程中t.inheritableThreadLocals变量是否为null，如果不为null则copy一份ThradLocalMap到子线程的t.inheritableThreadLocals成员变量中去
- 因为复写了getMap(Thread)和CreateMap()方法,所以get值得时候，就可以在getMap(t)的时候就会从t.inheritableThreadLocals中拿到map对象，从而实现了可以拿到父线程ThreadLocal中的值

```
public class TestInheritableThreadLocal implements Runnable {
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        System.out.println("----主线程设置值为\"主线程\"");
        threadLocal.set("主线程");
        System.out.println("----主线程设置后获取值：" + threadLocal.get());
        Thread tt = new Thread(new TestInheritableThreadLocal());
        tt.start();
        System.out.println("----主线程结束");

    }

    @Override
    public void run() {
        System.out.println("----子线程设置值前获取：" + threadLocal.get());
        System.out.println("----新开线程设置值为\"子线程\"");
        threadLocal.set("子线程");
        System.out.println("----新开的线程设置值后获取：" + threadLocal.get());
    }
}
```

- InheritableThreadLocal的源码非常简单，继承自ThreadLocal，重写其中三个方法。
- InheritableThreadLocal本身并没做什么操作，唯一的可能就是Thread里做了手脚。**目前的需求是要求将当前线程里的ThreadLocalMap共享到新开的线程**，那么，因为不知道用户何时使用这个数据，所以**新开的线程创建好后就必须能访问到这些数据**。
- 如果当前线程的inheritableThreadLocals != null，新线程：this.inheritableThreadLocals=ThreadLocal.createInheritedMap(parent.inheritableThreadLocals)
传入当前线程的inheritableThreadLocals 。




## 通过Callable和Future创建线程

Java 5在concurrency包中引入了java.util.concurrent.Callable 接口，它和Runnable接口很相似，但它可以返回一个对象或者抛出一个异常。

Callable接口**使用泛型去定义它的返回类型**。Executors类提供了**一些有用的方法去在线程池中执行Callable内的任务**。由于Callable任务是并行的，我们必须等待它返回的结果。java.util.concurrent.Future对象为我们解决了这个问题。在线程池**提交Callable任务**后**返回了一个Future对象**，使用它我们可以知道Callable任务的状态和得到Callable返回的执行结果。**Future提供了get()方法让我们可以等待Callable结束并获取它的执行结果**。

1. 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
2. 创建Callable实现类的实例，**使用FutureTask类来包装Callable对象**，该FutureTask对象封装了该Callable对象的call()方法的返回值。
3. **使用FutureTask对象作为Thread对象的target创建并启动新线程**。
4. 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值





## 什么叫守护线程，用什么方法实现守护线程（Thread.setDeamon()的含义）
在Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程) 
用个比较通俗的比如，任何一个守护线程都是整个JVM中所有非守护线程的保姆：
只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
JVM内部的实现是如果运行的程序只剩下守护线程的话，程序将终止运行，直接结束。</font>所以守护线程是作为辅助线程存在的，主要的作用是提供计数等等辅助的功能。



## 如何停止一个线程？
终止线程的三种方法：
1. 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
在定义退出标志exit时，使用了一个Java关键字volatile，这个关键字的目的是使exit同步，也就是说在同一时刻只能由一个线程来修改exit的值， 

```java
  thread.exit = true;  // 终止线程thread 
```

2. 使用stop方法强行终止线程（这个方法不推荐使用，因为stop和suspend、resume一样，也可能发生不可预料的结果）。
使用stop方法可以强行终止正在运行或挂起的线程。我们可以使用如下的代码来终止线程： 
thread.stop(); 
虽然使用上面的代码可以终止线程，但使用stop方法是很危险的，就象突然关闭计算机电源，而不是按正常程序关机一样，可能会产生不可预料的结果，因此，并不推荐使用stop方法来终止线程。 




3. 使用interrupt方法中断线程，使用interrupt方法来终端线程可分为两种情况： 

- 线程处于阻塞状态，如使用了sleep方法。 
- 使用while（！isInterrupted（））{……}来判断线程是否被中断。 
    在第一种情况下使用interrupt方法，sleep方法将抛出一个InterruptedException例外，而在第二种情况下线程将直接退出。


注意：在Thread类中有两个方法可以判断线程是否通过interrupt方法被终止。一个是静态的方法interrupted（），一个是非静态的方法isInterrupted（），这两个方法的区别是interrupted用来判断当前线是否被中断，而isInterrupted可以用来判断其他线程是否被中断。因此，while （！isInterrupted（））也可以换成while （！Thread.interrupted（））。


## Java 中 interrupted 和 isInterrupted 方法的区别？
- interrupt：将被置为”中断”状态

注意：线程中断仅仅是置线程的中断状态位，不会停止线程。需要用户自己去监视线程的状态为并做处。**支持线程中断的方法（也就是线程中断后会抛出interruptedException 的方法）就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。

- interrupted：是静态方法，查看当前中断信号是true还是false并且清除中断信号。如果一个线程被中断了，第一次调用 interrupted 则返回 true，第二次和后面的就返回 false 了。

- isInterrupted：查看当前中断信号是true还是false





## 线程的 sleep()方法和 yield()方法有什么区别？
1. sleep()方法给其他线程运行机会时不考虑线程的优先级，因此会给低优先级的线程以运行的机会；yield()方法只会给相同优先级或更高优先级的线程以运行的机会；
2. 线程执行 sleep()方法后转入阻塞（blocked）状态，而执行 yield()方法后转入就绪（ready）状态；
3. sleep()方法声明抛出 InterruptedException，而 yield()方法没有声明任何异常；
4. sleep()方法比 yield()方法（跟操作系统 CPU 调度相关）具有更好的可移植性，通常不建议使用yield()方法来控制并发线程的执行。



## final域的内存语义

1. JMM禁止编译器把final域的写重排序到构造函数之外。
2. 编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障
禁止处理器把final域的写重排序到构造函数之外。

### 写final域的重排序规则

对于 final 域，编译器和处理器要遵守两个重排序规则：

1. JMM 禁止编译器把 final 域的写重排序到构造函数之外
2. 编译器会在 final 域的写之后，构造函数 return 之前，插入一个 StoreStore 屏障，这个屏障禁止处理器把 final 域的写重排序到构造函数之外。

### 读final域的重排序规则

在一个线程中,初次读对象引用与初次读该对象包含的 final 域,JMM 禁止处理器重排序这两个操作(注意,这个规则仅仅针对处理器)。编译器会在读 final 域操作的前面插入一个 LoadLoad 屏障。
reader() 方法包含三个操作:

1. 初次读引用变量 obj;
2. 初次读引用变量 obj 指向对象的普通域 j。
3. 初次读引用变量 obj 指向对象的 final 域 i。


## notify和notifyAll的区别
-  如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。
-  当有线程调用了对象的 notifyAll()方法（唤醒所有 wait 线程）或 notify()方法（只随机唤醒一个 wait 
线程），被唤醒的的线程便会进入该对象的锁池中，锁池中的线程会去竞争该对象锁。也就是说，调用了notify后只要一个线程会由等待池进入锁池，而notifyAll会将该对象等待池内的所有线程移动到锁池中，等待锁竞争
-  优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用 wait()方法，它才会重新回到等待池中。而竞争到对象锁的线程则继续往下执行，直到执行完了 synchronized 
代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。
- 尽量使用 notifyAll()，notify()可能会导致死锁

## ConcurrentHashMap是如何在保证并发安全的同时提高性能
其实就是要控制锁的粒度，尽量避免锁的发生

ConcurrentHashMap使用了一些技巧来获取高的并发性能，同时避免了锁。这些技巧包括：
1. 使用CAS乐观锁和volatile代替RentrantLock
2. spread二次哈希进行segment分段。
3. stream提高并行处理能力。



## 为什么java.util.concurrent 包里没有并发的ArrayList实现？

我认为在java.util.concurrent包中没有加入并发的ArrayList实现的主要原因是：**很难去开发一个通用并且没有并发瓶颈的线程安全的List。**

像ConcurrentHashMap这样的类的真正价值（The real point / value of classes）并不是它们保证了线程安全。而在于它们在**保证线程安全的同时不存在并发瓶颈**。举个例子，ConcurrentHashMap采用了锁分段技术和弱一致性的Map迭代器去规避并发瓶颈。

所以问题在于，像“Array List”这样的数据结构，你不知道如何去规避并发的瓶颈。拿contains() 这样一个操作来说，当你进行搜索的时候如何避免锁住整个list？

另一方面，Queue 和Deque (基于Linked List)有并发的实现是因为他们的接口相比List的接口有更多的限制，这些限制使得实现并发成为可能。

CopyOnWriteArrayList是一个有趣的例子，它规避了只读操作（如get/contains）并发的瓶颈，但是它为了做到这点，在修改操作中做了很多工作和修改可见性规则。 此外，修改操作还会锁住整个List，因此这也是一个并发瓶颈。所以从理论上来说，CopyOnWriteArrayList并不算是一个通用的并发List。


## 比AtomicLong更高性能的LongAdder
LongAdder在高并发的场景下会比它的前辈————AtomicLong 具有更好的性能，代价是消耗更多的内存空间

AtomicLong在并发量较低的环境下，线程冲突的概率比较小，自旋的次数不会很多。但是，高并发环境下，N个线程同时进行自旋操作，会出现大量失败并不断自旋的情况。

LongAdder的基本思路就是**分散热点**，将value值分散到一个数组中，不同线程会命中到数组的不同槽中**，各个线程只对自己槽中的那个值进行CAS操作**，这样热点就被分散了，冲突的概率就小很多。如果要获取真正的long值，只要将各个槽中的变量值累加返回。

ConcurrentHashMap中的“分段锁”其实就是类似的思路。




## 两个线程同时执行i++100次,结果是多少
可能的结果：最小为2，最大为200

i++这种操作并不是原子性的, 实际上它的操作是首先从内存中取出数据放在cpu寄存器中进行计算, 然后再将计算好的结果返回到内存中。

**最小值2的分析：**
- 假设两个线程a,b
- 首先a执行99次，i为99，在未被写入内存时，b取i=0时执行1次，写入内存后i=1,此时覆盖掉了i=99的值；
- 然后a取i=1执行1次，b取i=1执行99次，当a比b后写入内存时，a覆盖掉b，此时i=2




## 如何排查死锁？
使用 jps + jstack
- jps -l
- jstack -l 12316







## 线程池
Executor线程池框架是一个根据一组**执行策略调用，调度，执行和控制**的异步任务的框架。

### ThreadPoolExecutor执行的策略

1. 线程数量未达到corePoolSize，则新建一个线程(核心线程)执行任务
2. 线程数量达到了corePools，则将任务移入队列等待
3. 队列已满，新建线程(非核心线程)执行任务
4. 队列已满，总线程数又达到了maximumPoolSize，就会由(RejectedExecutionHandler)抛出异常

新建线程 -> 达到核心数 -> 加入队列 -> 新建线程（非核心） -> 达到最大数 -> 触发拒绝策略


### 常见四种线程池


1. CachedThreadPool()：可缓存线程池。
 - 线程数无限制
 - 有空闲线程则复用空闲线程，若无空闲线程则新建线程
 - 一定程序减少频繁创建/销毁线程，减少系统开销

2. FixedThreadPool()：定长线程池。
- 可控制线程最大并发数（同时执行的线程数）
- 超出的线程会在队列中等待

3. ScheduledThreadPool()：定时线程池。
- 支持定时及周期性任务执行。

4. SingleThreadExecutor()：单线程化的线程池。
- 有且仅有一个工作线程执行任务
- 所有任务按照指定顺序执行，即遵循队列的入队出队规则

### 四种线程池使用场景

1. newSingleThreadExecutor：适用于串行执行任务的场景
2. newFixedThreadExecutor：适用于处理CPU密集型的任务，确保CPU在长期被工作线程使用的情况下，尽可能的少的分配线程即可。一般Ncpu + 1
3. newCachedThreadExecutor：适用于北方执行大量短期的小任务
4. newScheduledThreadExecutor：适用于需要多个后台线程执行周期任务，同时需要限制线程数量的场景



### 四种拒绝策略

1. AbortPolicy：不执行新任务，直接抛出异常，提示线程池已满，线程池默认策略 
2. DiscardPolicy：不执行新任务，也不抛出异常，基本上为静默模式。
3. DisCardOldSetPolicy：将消息队列中的第一个任务替换为当前新进来的任务执行
4. CallerRunPolicy：拒绝新任务进入，如果该线程池还没有被关闭，那么这个新的任务在执行线程中被调用 



### 为什么要用线程池

1. 减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
2. 运用线程池能有效的控制线程最大并发数，可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。 
3. 对线程进行一些简单的管理，比如：延时执行、定时循环执行的策略等，运用线程池都能进行很好的实现

### 线程池的非核心线程什么时候会被释放
当线程池中的线程数量大于 corePoolSize 的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过 keepAliveTime。


### Executorshe和ThreaPoolExecutor创建线程池的区别
- Executors 各个方法的弊端：
1.  newFixedThreadPool 和 newSingleThreadExecutor:
主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至 OOM。
2. newCachedThreadPool 和 newScheduledThreadPool:
 主要问题是线程数最大数是 Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至 OOM。

- ThreaPoolExecutor
1. 创建线程池方式只有一种，就是走它的构造函数，参数自己指定

### 线程池ThreadPoolExecutor参数设置
参数的设置跟系统的负载有直接的关系，下面为系统负载的相关参数：
- tasks，每秒需要处理的的任务数
- tasktime，处理每个任务花费的时间
- responsetime，系统允许任务最大的响应时间，比如每个任务的响应时间不得超过2秒。

#### corePoolSize
每个任务需要tasktime秒处理，则每个线程每钞可处理1/tasktime个任务。系统每秒有tasks个任务需要处理，则需要的线程数为：tasks/(1/tasktime)，即tasks*tasktime个线程数。

假设系统每秒任务数为100 ~ 1000，每个任务耗时0.1秒，则需要100 * 0.1至1000 * 0.1，即10 ~ 100个线程。那么corePoolSize应该设置为大于10，具体数字最好根据8020原则，即80%情况下系统每秒任务数小于200，最多时为1000，则corePoolSize可设置为20。

#### maxPoolSize

当系统负载达到最大值时，核心线程数已无法按时处理完所有任务，这时就需要增加线程。每秒200个任务需要20个线程，那么当每秒达到1000个任务时，则需要(1000-queueCapacity)*(20/200)，即60个线程，可将maxPoolSize设置为60。

#### queueCapacity

任务队列的长度要根据核心线程数，以及系统对任务响应时间的要求有关。队列长度可以设置为(corePoolSize/tasktime)*responsetime： (20/0.1)*2=400，即队列长度可设置为400。

队列长度设置过大，会导致任务响应时间过长，切忌以下写法：
```java
LinkedBlockingQueue queue = new LinkedBlockingQueue();
```
这实际上是将队列长度设置为Integer.MAX_VALUE，将会导致线程数量永远为corePoolSize，再也不会增加，当任务数量陡增时，任务响应时间也将随之陡增。


#### keepAliveTime

当负载降低时，可减少线程数量，当线程的空闲时间超过keepAliveTime，会自动释放线程资源。默认情况下线程池停止多余的线程并最少会保持corePoolSize个线程。


#### allowCoreThreadTimeout

默认情况下核心线程不会退出，可通过将该参数设置为true，让核心线程也退出。


如果涉及到有突发流量的场景，又该如何设置？
















