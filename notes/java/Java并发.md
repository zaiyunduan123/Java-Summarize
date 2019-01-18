<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [JAVA 线程状态转换图示](#java-%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E5%9B%BE%E7%A4%BA)
- [synchronized 的底层怎么实现](#synchronized-%E7%9A%84%E5%BA%95%E5%B1%82%E6%80%8E%E4%B9%88%E5%AE%9E%E7%8E%B0)
- [讲一下CAS](#%E8%AE%B2%E4%B8%80%E4%B8%8Bcas)
- [线程池](#%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [ThreadPoolExecutor执行的策略](#threadpoolexecutor%E6%89%A7%E8%A1%8C%E7%9A%84%E7%AD%96%E7%95%A5)
  - [常见四种线程池](#%E5%B8%B8%E8%A7%81%E5%9B%9B%E7%A7%8D%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [四种拒绝策略](#%E5%9B%9B%E7%A7%8D%E6%8B%92%E7%BB%9D%E7%AD%96%E7%95%A5)
  - [为什么要用线程池](#%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E7%94%A8%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [如何设计线程池中的线程数量](#%E5%A6%82%E4%BD%95%E8%AE%BE%E8%AE%A1%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%B8%AD%E7%9A%84%E7%BA%BF%E7%A8%8B%E6%95%B0%E9%87%8F)
- [Executorshe和ThreaPoolExecutor创建线程池的区别](#executorshe%E5%92%8Cthreapoolexecutor%E5%88%9B%E5%BB%BA%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E5%8C%BA%E5%88%AB)
- [CountDownLatch与CyclicBarrier的比较](#countdownlatch%E4%B8%8Ecyclicbarrier%E7%9A%84%E6%AF%94%E8%BE%83)
- [对象锁和静态锁之间的区别](#%E5%AF%B9%E8%B1%A1%E9%94%81%E5%92%8C%E9%9D%99%E6%80%81%E9%94%81%E4%B9%8B%E9%97%B4%E7%9A%84%E5%8C%BA%E5%88%AB)
- [简述volatile字](#%E7%AE%80%E8%BF%B0volatile%E5%AD%97)
- [happens-before 原则（先行发生原则）](#happens-before-%E5%8E%9F%E5%88%99%E5%85%88%E8%A1%8C%E5%8F%91%E7%94%9F%E5%8E%9F%E5%88%99)
- [Lock 和synchronized 的区别](#lock-%E5%92%8Csynchronized-%E7%9A%84%E5%8C%BA%E5%88%AB)
- [ThreadLocal(线程变量副本)](#threadlocal%E7%BA%BF%E7%A8%8B%E5%8F%98%E9%87%8F%E5%89%AF%E6%9C%AC)
- [通过Callable和Future创建线程](#%E9%80%9A%E8%BF%87callable%E5%92%8Cfuture%E5%88%9B%E5%BB%BA%E7%BA%BF%E7%A8%8B)
- [什么叫守护线程，用什么方法实现守护线程（Thread.setDeamon()的含义）](#%E4%BB%80%E4%B9%88%E5%8F%AB%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E7%94%A8%E4%BB%80%E4%B9%88%E6%96%B9%E6%B3%95%E5%AE%9E%E7%8E%B0%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8Bthreadsetdeamon%E7%9A%84%E5%90%AB%E4%B9%89)
- [如何停止一个线程？](#%E5%A6%82%E4%BD%95%E5%81%9C%E6%AD%A2%E4%B8%80%E4%B8%AA%E7%BA%BF%E7%A8%8B)
- [什么是线程安全？什么是线程不安全？](#%E4%BB%80%E4%B9%88%E6%98%AF%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E4%BB%80%E4%B9%88%E6%98%AF%E7%BA%BF%E7%A8%8B%E4%B8%8D%E5%AE%89%E5%85%A8)
- [I/O多路复用](#io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8)
- [讲一下netty](#%E8%AE%B2%E4%B8%80%E4%B8%8Bnetty)
- [Nio的原理（同步非阻塞）](#nio%E7%9A%84%E5%8E%9F%E7%90%86%E5%90%8C%E6%AD%A5%E9%9D%9E%E9%98%BB%E5%A1%9E)
- [缓冲区Buffer、通道Channel、选择器Selector](#%E7%BC%93%E5%86%B2%E5%8C%BAbuffer%E9%80%9A%E9%81%93channel%E9%80%89%E6%8B%A9%E5%99%A8selector)
- [BIO和NIO的区别](#bio%E5%92%8Cnio%E7%9A%84%E5%8C%BA%E5%88%AB)
- [NIO的selector作用](#nio%E7%9A%84selector%E4%BD%9C%E7%94%A8)
- [final域的内存语义](#final%E5%9F%9F%E7%9A%84%E5%86%85%E5%AD%98%E8%AF%AD%E4%B9%89)
  - [写final域的重排序规则](#%E5%86%99final%E5%9F%9F%E7%9A%84%E9%87%8D%E6%8E%92%E5%BA%8F%E8%A7%84%E5%88%99)
  - [读final域的重排序规则](#%E8%AF%BBfinal%E5%9F%9F%E7%9A%84%E9%87%8D%E6%8E%92%E5%BA%8F%E8%A7%84%E5%88%99)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## JAVA 线程状态转换图示
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-3.jpg)
## synchronized 的底层怎么实现
1. **同步代码块**(Synchronization)基于进入和退出管程(Monitor)对象实现。每个对象有一个监视器锁（monitor）。当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，过程如下：

- 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者。

- 如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1.

- 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权。
2. **被 synchronized 修饰的同步方法**并没有通过指令monitorenter和monitorexit来完成（理论上其实也可以通过这两条指令来实现），不过相对于普通方法，其常量池中多了ACC_SYNCHRONIZED标示符。JVM就是根据该标示符来实现方法的同步的：当方法调用时，调用指令将会检查方法的 ACC_SYNCHRONIZED 访问标志是否被设置，如果设置了，执行线程将先获取monitor，获取成功之后才能执行方法体，方法执行完后再释放monitor。在方法执行期间，其他任何线程都无法再获得同一个monitor对象。 其实本质上没有区别，只是方法的同步是一种隐式的方式来实现，无需通过字节码来完成
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


### 四种拒绝策略
 
1. AbortPolicy：不执行新任务，直接抛出异常，提示线程池已满，线程池默认策略 
2. DiscardPolicy：不执行新任务，也不抛出异常，基本上为静默模式。
3. DisCardOldSetPolicy：将消息队列中的第一个任务替换为当前新进来的任务执行
4. CallerRunPolicy：拒绝新任务进入，如果该线程池还没有被关闭，那么这个新的任务在执行线程中被调用 



### 为什么要用线程池

1. 减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
2. 运用线程池能有效的控制线程最大并发数，可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。 
3. 对线程进行一些简单的管理，比如：延时执行、定时循环执行的策略等，运用线程池都能进行很好的实现

### 如何设计线程池中的线程数量
线程池的最大线程数目根据CPU核心数来确定

1. 如果你是CPU密集型运算，那么线程数量和CPU核心数相同就好，避免了大量无用的切换线程上下文
2. 如果你是IO密集型的话，需要大量等待，那么线程数可以设置的多一些，比如CPU核心乘以2

至于如何获取 CPU 核心数，Java 提供了一个方法：
```java
Runtime.getRuntime().availableProcessors();
```
返回了CPU的核心数量。

## Executorshe和ThreaPoolExecutor创建线程池的区别
- Executors 各个方法的弊端：
1.  newFixedThreadPool 和 newSingleThreadExecutor:
主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至 OOM。
2. newCachedThreadPool 和 newScheduledThreadPool:
 主要问题是线程数最大数是 Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至 OOM。

- ThreaPoolExecutor
1. 创建线程池方式只有一种，就是走它的构造函数，参数自己指定


## CountDownLatch与CyclicBarrier的比较
CountDownLatch与CyclicBarrier都是用于控制并发的工具类，都可以理解成维护的就是一个计数器，但是这两者还是各有不同侧重点的：
   
1. CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；CountDownLatch强调一个线程等多个线程完成某件事情。CyclicBarrier是多个线程互等，等大家都完成，再携手共进。
2. 调用CountDownLatch的countDown方法后，当前线程并不会阻塞，会继续往下执行；而调用CyclicBarrier的await方法，会阻塞当前线程，直到CyclicBarrier指定的线程全部都到达了指定点的时候，才能继续往下执行；
3. CountDownLatch方法比较少，操作比较简单，而CyclicBarrier提供的方法更多，比如能够通过getNumberWaiting()，isBroken()这些方法获取当前多个线程的状态，并且CyclicBarrier的构造方法可以传入barrierAction，指定当所有线程都到达时执行的业务功能；
4. CountDownLatch是不能复用的，而CyclicLatch是可以复用的。

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



## Lock 和synchronized 的区别
1. Lock 是一个 接口，而 synchronized 是 Java 中的 关键字，
synchronized 是 内置的语言实现；
2. synchronized 在 发生异常时，会 自动释放线程占有的锁，因此 不会导
致死锁现象发生；而 Lock 在发生异常时，如果没有主动通过 unLock()去释放
锁，则很 可能造成死锁现象，因此用 使用 Lock 时需要在 finally 块中释放锁；

3. Lock 可以让 等待锁的线程响应中断 （可中断锁），而 synchronized
却不行，使用 synchronized 时，等待的线程会一直等待下去， 不能够响应中
断 （不可中断锁）；
4. 通过 Lock 可以知道 有没有成功获取锁 （tryLock （ ） 方法 ： 如果获取
了锁 ，回 则返回 true ；回 否则返回 false e, , 也就说这个方法无论如何都会立即返回 。
在拿不到锁时不会一直在那等待。 ），而 synchronized 却无法办到。
5. Lock 可以提高 多个线程进行读操作的效率（ 读写锁）。
6. Lock 可以实现 公平锁，synchronized 不保证公平性。
在性能上来说，如果线程竞争资源不激烈时，两者的性能是差不多的，而
当竞争资源非常激烈时（即有大量线程同时竞争），此时 Lock 的性能要远远优
于 synchronized。所以说，在具体使用时要根据适当情况选择。





## ThreadLocal(线程变量副本)
Synchronized实现内存共享，ThreadLocal为每个线程维护一个本地变量。
采用空间换时间，它用于线程间的数据隔离，为每一个使用该变量的线程提供一个副本，每个线程都可以独立地改变自己的副本，而不会和其他线程的副本冲突。
ThreadLocal类中维护一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值为对应线程的变量副本。
ThreadLocal在Spring中发挥着巨大的作用，在管理Request作用域中的Bean、事务管理、任务调度、AOP等模块都出现了它的身影。
Spring中绝大部分Bean都可以声明成Singleton作用域，采用ThreadLocal进行封装，因此有状态的Bean就能够以singleton的方式在多线程中正常工作了。



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



## 什么是线程安全？什么是线程不安全？
1. 线程安全就是多线程访问时，采用了加锁机制，当一个线程访问该类的某个数据时，进行保护，其他线程不能进行访问直到该线程读取完，其他线程才可使用。不会出现数据不一致或者数据污染。
2. 线程不安全就是不提供数据访问保护，有可能出现多个线程先后更改数据造成所得到的数据是脏数据
在多线程的情况下，由于同一进程的多个线程共享同一片存储空间，在带来方便的同时，也带来了访问冲突这个严重的问题。Java语言提供了专门机制以解决这种冲突，有效避免了同一个数据对象被多个线程同时访问。

## I/O多路复用 
单个线程，通过记录跟踪每个I/O流(sock)的状态，来同时管理多个I/O流 。尽量多的提高服务器的吞吐能力

select, poll, epoll 都是I/O多路复用的具体的实现


## 讲一下netty
netty通过Reactor模型基于多路复用器接收并处理用户请求，内部实现了两个线程池，boss线程和work线程池，其中boss线程池的线程负责处理请求的accept事件，当接收到accept事件的请求，把对应的socket封装到一个NioSocketChannel中，并交给work线程池，其中work线程池负责请求的read和write事件


## Nio的原理（同步非阻塞）
服务端和客户端各自维护一个管理通道的对象，我们称之为 selector，该对
象能检测一个或多个通道（channel）上的事件。我们以服务端为例，如果服务
端的 selector 上注册了读事件，某时刻客户端给服务端送了一些数据，阻塞 I/O
这时会调用 read()方法阻塞地读取数据，而 NIO 的服务端会在 selector 中添加
一个读事件。服务端的处理线程会轮询地访问 selector，如果访问 selector 时发
现有感兴趣的事件到达，则处理这些事件，如果没有感兴趣的事件到达，则处
理线程会一直阻塞直到感兴趣的事件到达为止。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-4.jpg)

## 缓冲区Buffer、通道Channel、选择器Selector 
缓冲区Buffer

- 缓冲区实际上是一个容器对象，更直接的说，其实就是一个数组，在NIO库中，所有数据都是用缓冲区处理的。在读取数据时，它是直接读到缓冲区中的； 在写入数据时，它也是写入到缓冲区中的；任何时候访问 NIO 中的数据，都是将它放到缓冲区中。而在面向流I/O系统中，所有数据都是直接写入或者直接将数据读取到Stream对象中。

通道Channel

- 通道是一个对象，通过它可以读取和写入数据，当然了所有数据都通过Buffer对象来处理。我们永远不会将字节直接写入通道中，相反是将数据写入包含一个或者多个字节的缓冲区。同样不会直接从通道中读取字节，而是将数据从通道读入缓冲区，再从缓冲区获取这个字节。通道与流的不同之处在于 通道是双向
的。而流只是在一个方向上移动(一个流必须是 InputStream 或者
OutputStream 的子类，比如 InputStream 只能进行读取操作，OutputStream
只能进行写操作)，而通道是双向的，可以用于读、写或者同时用于读写。

选择器（Selector ）

- NIO 有一个主要的类 Selector,这个类似一个观察者，只要我们把需要探知
的 socketchannel 告诉 Selector,我们接着做别的事情， 当有事件发生时，他会
通知我们，传回一组 SelectionKey, 我们读取这些 Key, 就会获得我们刚刚注册
过的 socketchannel, 然后，我们从这个 Channel 中读取数据，放心，包准能
够读到，接着我们可以处理这些数据。
-  Selector 内部原理实际是在做一个 对所注册的 channel 的轮询访问，不断
地轮询，一旦轮询到一个 channel 有所注册的事情发生，比如数据来了，他就
会站起来报告， 交出一把钥匙，让我们 通过这把钥匙来读取这个 channel 的内
容。

## BIO和NIO的区别
1. BIO：同步阻塞式IO，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。 
2. NIO：同步非阻塞式IO，服务器实现模式为一个请求一个线程，即客户端发送的连接请求都会注册到多路复用器上，多路复用器轮询到连接有I/O请求时才启动一个线程进行处理。 

## NIO的selector作用
Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。

为了实现Selector管理多个SocketChannel，必须将具体的SocketChannel对象注册到Selector，并声明需要监听的事件（这样Selector才知道需要记录什么数据），一共有4种事件：

1. connect：客户端连接服务端事件，对应值为SelectionKey.OP_CONNECT(8)
2. accept：服务端接收客户端连接事件，对应值为SelectionKey.OP_ACCEPT(16)
3. read：读事件，对应值为SelectionKey.OP_READ(1)
4. write：写事件，对应值为SelectionKey.OP_WRITE(4)

每次请求到达服务器，都是从connect开始，connect成功后，服务端开始准备accept，准备就绪，开始读数据，并处理，最后写回数据返回。

所以，当SocketChannel有对应的事件发生时，Selector都可以观察到，并进行相应的处理。


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









