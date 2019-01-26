<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [JVM内存结构 VS Java内存模型 VS Java对象模型](#jvm%E5%86%85%E5%AD%98%E7%BB%93%E6%9E%84-vs-java%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B-vs-java%E5%AF%B9%E8%B1%A1%E6%A8%A1%E5%9E%8B)
  - [JVM内存结构](#jvm%E5%86%85%E5%AD%98%E7%BB%93%E6%9E%84)
    - [程序计数器](#%E7%A8%8B%E5%BA%8F%E8%AE%A1%E6%95%B0%E5%99%A8)
    - [Java虚拟机栈](#java%E8%99%9A%E6%8B%9F%E6%9C%BA%E6%A0%88)
    - [堆](#%E5%A0%86)
    - [方法区](#%E6%96%B9%E6%B3%95%E5%8C%BA)
  - [Java内存模型](#java%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B)
  - [Java对象模型](#java%E5%AF%B9%E8%B1%A1%E6%A8%A1%E5%9E%8B)
  - [三者区别](#%E4%B8%89%E8%80%85%E5%8C%BA%E5%88%AB)
- [垃圾回收](#%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6)
  - [Major GC和Full GC的区别是什么？触发条件呢？](#major-gc%E5%92%8Cfull-gc%E7%9A%84%E5%8C%BA%E5%88%AB%E6%98%AF%E4%BB%80%E4%B9%88%E8%A7%A6%E5%8F%91%E6%9D%A1%E4%BB%B6%E5%91%A2)
  - [什么时候会触发full gc](#%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E4%BC%9A%E8%A7%A6%E5%8F%91full-gc)
  - [可以作为root的对象](#%E5%8F%AF%E4%BB%A5%E4%BD%9C%E4%B8%BAroot%E7%9A%84%E5%AF%B9%E8%B1%A1)
  - [新生代转移到老年代的触发条件](#%E6%96%B0%E7%94%9F%E4%BB%A3%E8%BD%AC%E7%A7%BB%E5%88%B0%E8%80%81%E5%B9%B4%E4%BB%A3%E7%9A%84%E8%A7%A6%E5%8F%91%E6%9D%A1%E4%BB%B6)
  - [G1和CMS的区别](#g1%E5%92%8Ccms%E7%9A%84%E5%8C%BA%E5%88%AB)
- [类加载](#%E7%B1%BB%E5%8A%A0%E8%BD%BD)
  - [双亲委派模型中有哪些方法。用户如何自定义类加载器 。怎么打破双亲委托机制](#%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%B4%BE%E6%A8%A1%E5%9E%8B%E4%B8%AD%E6%9C%89%E5%93%AA%E4%BA%9B%E6%96%B9%E6%B3%95%E7%94%A8%E6%88%B7%E5%A6%82%E4%BD%95%E8%87%AA%E5%AE%9A%E4%B9%89%E7%B1%BB%E5%8A%A0%E8%BD%BD%E5%99%A8-%E6%80%8E%E4%B9%88%E6%89%93%E7%A0%B4%E5%8F%8C%E4%BA%B2%E5%A7%94%E6%89%98%E6%9C%BA%E5%88%B6)
- [内存溢出](#%E5%86%85%E5%AD%98%E6%BA%A2%E5%87%BA)
  - [原因](#%E5%8E%9F%E5%9B%A0)
  - [解决方法](#%E8%A7%A3%E5%86%B3%E6%96%B9%E6%B3%95)
- [栈溢出](#%E6%A0%88%E6%BA%A2%E5%87%BA)
  - [解决办法](#%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95)
- [java应用系统运行速度慢的解决方法](#java%E5%BA%94%E7%94%A8%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E9%80%9F%E5%BA%A6%E6%85%A2%E7%9A%84%E8%A7%A3%E5%86%B3%E6%96%B9%E6%B3%95)
- [逃逸分析](#%E9%80%83%E9%80%B8%E5%88%86%E6%9E%90)
- [编译](#%E7%BC%96%E8%AF%91)
  - [即时编译器的优化方法](#%E5%8D%B3%E6%97%B6%E7%BC%96%E8%AF%91%E5%99%A8%E7%9A%84%E4%BC%98%E5%8C%96%E6%96%B9%E6%B3%95)
  - [编译过程的五个阶段](#%E7%BC%96%E8%AF%91%E8%BF%87%E7%A8%8B%E7%9A%84%E4%BA%94%E4%B8%AA%E9%98%B6%E6%AE%B5)
  - [JVM、Java编译器和Java解释器](#jvmjava%E7%BC%96%E8%AF%91%E5%99%A8%E5%92%8Cjava%E8%A7%A3%E9%87%8A%E5%99%A8)
  - [JIT 编译过程](#jit-%E7%BC%96%E8%AF%91%E8%BF%87%E7%A8%8B)
  - [Graal 的实现](#graal-%E7%9A%84%E5%AE%9E%E7%8E%B0)
  - [GraalVM 中的 Ahead-Of-Time（AOT）](#graalvm-%E4%B8%AD%E7%9A%84-ahead-of-timeaot)
- [JVM的Intrinsics方法](#jvm%E7%9A%84intrinsics%E6%96%B9%E6%B3%95)
- [JVM的invokedynamic方法](#jvm%E7%9A%84invokedynamic%E6%96%B9%E6%B3%95)
- [方法句柄](#%E6%96%B9%E6%B3%95%E5%8F%A5%E6%9F%84)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


# JVM内存结构 VS Java内存模型 VS Java对象模型
## JVM内存结构
Java代码是要运行在虚拟机上的，而虚拟机在执行Java程序的过程中会把所管理的内存划分为若干个不同的数据区域，这些区域都有各自的用途，其中有些区域随着虚拟机进程的启动而存在。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-5.jpg)

### 程序计数器

概述：较小的内存空间，为当前线程执行的字节码的行号指示器</br>
作用：通过改变计数器的值来指定下一条需要执行的字节码指令，来恢复中断前程序运行的位置</br>
特点：
1. 线程私有化，每个线程都有独立的程序计数器   
2. 无内存溢出

### Java虚拟机栈

概述：每个方法从调用直到执行的过程，对应着一个栈帧在虚拟机栈的入栈和出栈的过程</br>
作用：每个方法执行都创建一个“栈帧”来存储局部变量表、操作数栈、动态链接、方法出口等信息</br>
特点：
1. 线程私有化   
2. 生命周期与线程执行结束相同

### 堆

创建时间：JVM启动时创建该区域</br>
占用空间：Java虚拟机管理内存最大的一块区域</br>
作用：用于存放对象实例及数组（所有new的对象）</br>

特点：
1. 垃圾收集器作用该区域，回收不使用的对象的内存空间
2. 各个线程共享的内存区域
3. 该区域的大小可通过参数设置

### 方法区

作用：用于存储类信息、常量、静态变量、是各个线程共享的内存区域


## Java内存模型
Java内存模型是根据英文Java Memory Model（JMM）翻译过来的。其实JMM并不像JVM内存结构一样是真实存在的。他只是一个抽象的概念，JMM是和多线程相关的，这个规范定义了一个线程对共享变量的写入时对另一个线程是可见的。

在JMM中，我们把多个线程间通信的共享内存称之为主内存，而在并发编程中多个线程都维护了一个自己的本地内存（这是个抽象概念），其中保存的数据是主内存中的数据拷贝。而JMM主要是控制本地内存和主内存之间的数据交互的。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-6.jpg)

## Java对象模型
Java是一种面向对象的语言，而Java对象在JVM中的存储也是有一定的结构的。而这个关于Java对象自身的存储模型称之为Java对象模型。

每一个Java类，在被JVM加载的时候，JVM会给这个类创建一个instanceKlass，保存在方法区，用来在JVM层表示该Java类。当我们在Java代码中，使用new创建一个对象的时候，JVM会创建一个instanceOopDesc对象，这个对象中包含了对象头以及实例数据。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-7.jpg)

## 三者区别
1. JVM内存结构，和Java虚拟机的运行时区域有关。
2. Java内存模型，和Java的并发编程有关。
3. Java对象模型，和Java对象在虚拟机中的表现形式有关。

# 垃圾回收

## Major GC和Full GC的区别是什么？触发条件呢？

针对HotSpot VM的实现，它里面的GC其实准确分类只有两大种：
Partial GC：并不收集整个GC堆的模式
 - Young GC：只收集young gen的GC
 - Old GC：只收集old gen的GC。只有CMS的concurrent collection是这个模式
 - Mixed GC：收集整个young gen以及部分old gen的GC。只有G1有这个模式

Full GC：收集整个堆，包括young gen、old gen、perm gen（如果存在的话）等所有部分的模式。


## 什么时候会触发full gc
1. System.gc()方法的调用
2. 老年代空间不足
3. 永生区空间不足（JVM规范中运行时数据区域中的方法区，在HotSpot虚拟机中又被习惯称为永生代或者永生区，Permanet Generation中存放的为一些class的信息、常量、静态变量等数据）
4.  GC时出现promotion failed和concurrent mode failure
5. 统计得到的Minor GC晋升到旧生代平均大小大于老年代剩余空间
6. 堆中分配很大的对象


## 可以作为root的对象
1. 类中的静态变量，当它持有一个指向一个对象的引用时，它就作为root
2. 活动着的线程，可以作为root
3. 一个Java方法的参数或者该方法中的局部变量，这两种对象可以作为root
4. JNI方法中的局部变量或者参数，这两种对象可以作为root


例子：下述的Something和Apple都可以作为root对象。

```java
public AClass{
 
  public static Something;
  public static final Apple;
   ''''''
}
```

 Java方法的参数和方法中的局部变量，可以作为root.

```java
public Aclass{

public void doSomething(Object A){
    ObjectB b = new ObjectB; 
    }
 }
```
## 新生代转移到老年代的触发条件
1. 长期存活的对象
2. 大对象直接进入老年代
3. minor gc后，survivor仍然放不下
4. 动态年龄判断 ，大于等于某个年龄的对象超过了survivor空间一半 ，大于等于某个年龄的对象直接进入老年代

## G1和CMS的区别
1. G1同时回收老年代和年轻代，而CMS只能回收老年代，需要配合一个年轻代收集器。另外G1的分代更多是逻辑上的概念，G1将内存分成多个等大小的region，Eden/ Survivor/Old分别是一部分region的逻辑集合，物理上内存地址并不连续。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-8.jpg)
2. CMS在old gc的时候会回收整个Old区，对G1来说没有old gc的概念，而是区分Fully young gc和Mixed gc，前者对应年轻代的垃圾回收，后者混合了年轻代和部分老年代的收集，因此每次收集肯定会回收年轻代，老年代根据内存情况可以不回收或者回收部分或者全部(这种情况应该是可能出现)。


# 类加载

## 双亲委派模型中有哪些方法。用户如何自定义类加载器 。怎么打破双亲委托机制
1. 双亲委派模型中用到的方法：
- findLoadedClass(),
- loadClass()
- findBootstrapClassOrNull()
- findClass()
- defineClass()：把二进制数据转换成字节码。
- resolveClass()

自定义类加载器的方法：继承 ClassLoader 类,重写 findClass()方法 。


2. 继承ClassLoader覆盖loadClass方法
原顺序
1. findLoadedClass
2. 委托parent加载器加载（这里注意bootstrap加载器的parent为null)
3. 自行加载
打破委派机制要做的就是打乱2和3的顺序，通过类名筛选自己要加载的类，其他的委托给parent加载器。







# 内存溢出

内存溢出是指应用系统中存在无法回收的内存或使用的内存过多，最终使得程序运行要用到的内存大于虚拟机能提供的最大内存。

## 原因
引起内存溢出的原因有很多种，常见的有以下几种：

1. 内存中加载的数据量过于庞大，如一次从数据库取出过多数据；
2. 集合类中有对对象的引用，使用完后未清空，使得JVM不能回收；
3. 代码中存在死循环或循环产生过多重复的对象实体；
4. 使用的第三方软件中的BUG；
5. 启动参数内存值设定的过小；



## 解决方法
内存溢出虽然很棘手，但也有相应的解决办法，可以按照从易到难，一步步的解决。

第一步，就是修改JVM启动参数，直接增加内存。JVM默认可以使用的内存为64M，Tomcat默认可以使用的内存为128MB，对于稍复杂一点的系统就会不够用。在某项目中，就因为启动参数使用的默认值，经常报“OutOfMemory”错误。因此，-Xms，-Xmx参数一定不要忘记加。

第二步，检查错误日志，查看“OutOfMemory”错误前是否有其它异常或错误。查看日志对于分析内存溢出是非常重要的，通过仔细查看日志，分析内存溢出前做过哪些操作，可以大致定位有问题的模块。

第三步，找出可能发生内存溢出的位置。重点排查以下几点：


1. 检查代码中是否有死循环或递归调用。

2. 检查是否有大循环重复产生新对象实体。

3. 检查对数据库查询中，是否有一次获得全部数据的查询。一般来说，如果一次取十万条记录到内存，就可能引起内存溢出。这个问题比较隐蔽，在上线前，数据库中数据较少，不容易出问题，上线后，数据库中数据多了，一次查询就有可能引起内存溢出。因此对于数据库查询尽量采用分页的方式查询。

4. 检查List、MAP等集合对象是否有使用完后，未清除的问题。List、MAP等集合对象会始终存有对对象的引用，使得这些对象不能被GC回收。


第四步，使用内存查看工具动态查看内存使用情况。

内存查看工具有许多，比较有名的有：Optimizeit Profiler、JProbe Profiler、JinSight和Java1.5的Jconsole等。它们的基本工作原理大同小异，都是监测Java程序运行时所有对象的申请、释放等动作，将内存管理的所有信息进行统计、分析、可视化。开发人员可以根据这些信息判断程序是否有内存泄漏问题。一般来说，一个正常的系统在其启动完成后其内存的占用量是基本稳定的，而不应该是无限制的增长的。持续地观察系统运行时使用的内存的大小，可以看到在内存使用监控窗口中是基本规则的锯齿形的图线，如果内存的大小持续地增长，则说明系统存在内存泄漏问题。通过间隔一段时间取一次内存快照，然后对内存快照中对象的使用与引用等信息进行比对与分析，可以找出是哪个类的对象在泄漏。



# 栈溢出
1. 递归调用层次太多。递归函数在运行时会执行压栈操作，当压栈次数太多时，也会导致堆栈溢出。
2. 局部静态变量体积太大,局部数组过大。当函数内部的数组过大时，有可能导致堆栈溢出。
3. 指针或数组越界。这种情况最常见，例如进行字符串拷贝，或处理用户输入等等。

## 解决办法
1. 用栈把递归转换成非递归
2. 使用static对象替代nonstatic局部对象
3. 增大堆栈大小值



# java应用系统运行速度慢的解决方法
 问题解决思路：
 1. 查看部署应用系统的系统资源使用情况，CPU,内存，IO这几个方面去看。找到对就的进程。
 2. 使用jstack,jmap等命令查看是JVM是在在什么类型的内存空间中做GC（内存回收），和查看GC日志查看是那段代码在占用内存。
     ​    首先，调节内存的参数设置，如果还是一样的问题，就要定位到相应的代码。
 3. 定位代码，修改代码（一般是代码的逻辑问题，或者代码获取的数据量过大。）



# 逃逸分析
逃逸是指在某个方法之内创建的对象，除了在方法体之内被引用之外，还在方法体之外被其它变量引用到；这样带来的后果是在该方法执行完毕之后，该方法中创建的对象将无法被GC回收，由于其被其它变量引用。正常的方法调用中，方法体中创建的对象将在执行完毕之后，将回收其中创建的对象；故由于无法回收，即成为逃逸。

逃逸分析可以分析出某个对象是否永远只在某个方法、线程的范围内，并没有“逃逸”出这个范围，逃逸分析的一个结果就是对于某些未逃逸对象可以直接在栈上分配，由于该对象一定是局部的，所以栈上分配不会有问题。



# 编译

## 即时编译器的优化方法
字节码可以通过以下两种方式转换成合适的语言：
1.  解释器
2.  即时编译器
即时编译器把**整段字节码编译成本地代码**，执行本地代码比一条一条进行解释执行的速度快很多，因为本地代码是保存在缓存里的



## 编译过程的五个阶段
1. 第一阶段：词法分析
2. 第二阶段：语法分析
3. 第三阶段:词义分析与中间代码产生
4. 第四阶段：优化
5. 第五阶段：目标代码生成



## JVM、Java编译器和Java解释器

1. Java编译器：将Java源文件（.java文件）编译成字节码文件（.class文件，是特殊的二进制文件，二进制字节码文件），这种字节码就是JVM的“机器语言”。javac.exe可以简单看成是Java编译器。注意，它不会执行代码

   Java解释器：是JVM的一部分。Java解释器用来解释执行Java编译器编译后的程序。java.exe可以简单看成是Java解释器。注意，它会执行代码

   JVM是Java平台无关的基础。JVM负责运行字节码：JVM把每一条要执行的字节码交给解释器，翻译成对应的机器码，然后由解释器执行。JVM解释执行字节码文件就是JVM操作Java解释器进行解释执行字节码文件的过程。

2. JVM：一种能够运行Java字节码（Java bytecode）的虚拟机。

   字节码：字节码是已经经过编译，但与特定机器码无关，需要解释器转译后才能成为机器码的中间代码。

3. Java字节码：是Java虚拟机执行的一种指令格

Java字节码的执行有两种方式：
　　1. 即时编译方式：解释器先将字节码编译成机器码，然后再执行该机器码。
　　2. 解释执行方式：解释器通过每次解释并执行一小段代码来完成Java字节码程 序的所有操作。

无论是采用解释器进行解释执行，还是采用即时编译器进行编译执行，最终字节码都需要被转换为对应平台的本地机器指令。

从表象意义上看，重点就在：
解释：输入程序代码 -> 得到执行结果，从用户的角度看一步到位
编译：输入程序代码 -> 得到可执行代码
要得到执行结果还得再去执行可执行代码

疑问,解释器通过翻译将字节码转换为机器码，即时编译器通过编译将字节码转换为机器码，翻译？编译？为什么都是一样的操作？？？

1. 每次读一代码就将字节码起转换（翻译）为JVM可执行的指令，叫翻译
2. 一次性全部将字节码转换为JVM可执行的指令，叫编译

## JIT 编译过程

当 JIT 编译启用时（默认是启用的），JVM 读入.class 文件解释后，将其发给 JIT 编译器。JIT 编译器将字节码编译成本机机器代码，下图展示了该过程。


![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-15.png)


即时编译器是 Java 虚拟机中相对独立的模块，它主要负责接收 Java 字节码，并生成可以直接运行的二进制码。
即时编译器与 Java 虚拟机的交互可以分为如下三个方面。
1. 响应编译请求；
2. 获取编译所需的元数据（如类、方法、字段）和反映程序执行状态的 profile；
3. 将生成的二进制码部署至代码缓存（code cache）里。


许多开发者会觉得用 C++ 写的 C2 肯定要比 Graal 快。实际上，在充分预热的情况下，Java 程序中的热点代码早已经通过即时编译转换为二进制码，在执行速度上并不亚于静态编译的 C++ 程序。

## Graal 的实现
HotSpot集成了两个JIT compiler — C1及C2（或称为Client及Server）。两者的区别在于，前者没有应用激进的优化技术，因为这些优化往往伴随着耗时较长的代码分析。因此，C1的编译速度较快，而C2所编译的方法运行速度较快。

Java 7引入了tiered compilation的概念，综合了C1的高启动性能及C2的高峰值性能。这两个JIT compiler以及interpreter将HotSpot的执行方式划分为五个级别：
1. level 0：interpreter解释执行
2. level 1：C1编译，无profiling
3. level 2：C1编译，仅方法及循环back-edge执行次数的profiling
4. level 3：C1编译，除level 2中的profiling外还包括branch（针对分支跳转字节码）及receiver type（针对成员方法调用或类检测，如checkcast，instnaceof，aastore字节码）的profiling
5. level 4：C2编译

Graal可替换C2成为HotSpot的顶层JIT compiler，即上述level 4。与C2相比，Graal采用更加激进的优化方式，因此当程序达到稳定状态后，其执行效率（峰值性能）将更有优势。

Graal 和 C2 最为明显的一个区别是：Graal 是用 Java 写的，许多C2中实现的优化均被移植到Graal中.而 C2 是用 C++ 写的。相对来说，Graal 更加模块化，也更容易开发与维护。在充分预热的情况下，Java 程序中的热点代码早已经通过即时编译转换为二进制码，在执行速度上并不亚于静态编译的 C++ 程序。即便是解释执行 Graal，也仅是会减慢编译效率，而并不影响编译结果的性能。Graal 和 C2 另一个优化上的分歧则是方法内联算法。相对来说，Graal 的内联算法对新语法、新语言更加友好，例如 Java 8 的 lambda 表达式以及 Scala 语言。

Graal 编译器将编译过程分为前端和后端两大部分。前端用于实现平台无关的优化（如方法内联），以及小部分平台相关的优化；而后端则负责大部分的平台相关优化（如寄存器分配），以及机器码的生成。

Graal 和 C2 都采用了 Sea-of-Nodes IR。严格来说，这里指的是 Graal 的前端，而后端采用的是另一种非 Sea-of-Nodes 的 IR。通常，我们将前端的 IR 称之为 High-level IR，或者 HIR；后端的 IR 则称之为 Low-level IR，或者 LIR。

Graal 是一个用 Java 写就的、并能够将 Java 字节码转换成二进制码的即时编译器。它通过 JVMCI 与 Java 虚拟机交互，响应由后者发出的编译请求、完成编译并部署编译结果。

对 Java 程序而言，Graal 编译结果的性能略优于 OpenJDK 中的 C2；对 Scala 程序而言，它的性能优势可达到 10%（企业版甚至可以达到 20%！）。这背后离不开 Graal 所采用的激进优化方式。

这种基于假设的优化手段。在编译过程中，Graal 支持自定义假设，并且直接与去优化节点相关联。

## GraalVM 中的 Ahead-Of-Time（AOT）
GraalVM 是一个高性能的、支持多种编程语言的执行环境。它既可以在传统的 OpenJDK 上运行，也可以通过 AOT（Ahead-Of-Time）编译成可执行文件单独运行，甚至可以集成至数据库中运行。
˚
即时编译指的是在程序的运行过程中，将字节码转换为可在硬件上直接运行的机器码，并部署至托管环境中的过程。

而AOT 编译指的则是，在程序运行之前，便将字节码转换为机器码的过程。它的成果可以是需要链接至托管环境中的动态共享库，也可以是独立运行的可执行文件。

AOT 编译的优点：无须在运行过程中耗费 CPU 资源来进行即时编译，而程序也能够在启动伊始就达到理想的性能。

AOT 编译的缺点：AOT 编译无法得知程序运行时的信息，因此也（1）无法进行基于类层次分析的完全虚方法内联，或者（2）基于程序 profile 的投机性优化（并非硬性限制，我们可以通过限制运行范围，或者利用上一次运行的程序 profile 来绕开这两个限制）。这两者都会影响程序的峰值性能。

Java 9 引入了实验性 AOT 编译工具jaotc。它借助了 Graal 编译器，将所输入的 Java 类文件（class字节码文件）转换为机器码，并存放至生成的动态共享库之中

源文件就是程序员们所编写出来的文件 程序员们能看懂的文件
类文件则是利用java虚拟机生成的编译文件 是用来给机器看的机器语言



# JVM的Intrinsics方法


在hotspot jvm里会定义一些intrinsic的方法，从而可以定义自己独有的一些编译的算法，根据不同的架构使用不同的指令集，比如Math.sin,Math.cos之类.

对每个方法hotspot jvm都会定义一个instrinisics id, 这个id可以用于区分java 里自己定义的lib类的方法还是用户自己定义的java的类的方法，用户自己写的类会用 vmIntrinsics::_none 来表示.

CallGenerator是在hotspot jvm中方法调用的核心，不同运行方式是由不同的call generator决定的，而instrinsic_id又是决定不同的call generator的key.

对Java自定义的lib库的方法，jvm 用了LibraryIntrinsic 作为lib库的CallGenerator， 在generate 函数的时候，初始化了LibraryCallKit，里面inline了很多lib的方法



# JVM的invokedynamic方法
我们常用的JavaScript, Python, Ruby都可以归为动态语言，而Java, Bytecode都可以认为是静态语言。这两种语言最大的差别是变量和函数的类型是不是在程序运行中确定的。

invokedynamic 是 Java 7 引入的一条新指令，用以支持动态语言的方法调用。具体来说，它将调用点（CallSite）抽象成一个 Java 类，并且将原本由 Java 虚拟机控制的方法调用以及方法链接暴露给了应用程序。在运行过程中，每一条 invokedynamic 指令将捆绑一个调用点，并且会调用该调用点所链接的方法句柄。

在第一次执行 invokedynamic 指令时，Java 虚拟机会调用该指令所对应的启动方法（BootStrap Method），来生成前面提到的调用点，并且将之绑定至该 invokedynamic 指令中。在之后的运行过程中，Java 虚拟机则会直接调用绑定的调用点所链接的方法句柄。

在 Java 8 中，Lambda 表达式也是借助 invokedynamic 来实现的。
Lambda 表达式到函数式接口的转换是通过 invokedynamic 指令来实现的。该 invokedynamic 指令对应的启动方法将通过 ASM 生成一个适配器类。
对于没有捕获其他变量的 Lambda 表达式，该 invokedynamic 指令始终返回同一个适配器类的实例。对于捕获了其他变量的 Lambda 表达式，每次执行 invokedynamic 指令将新建一个适配器类实例。

不管是捕获型的还是未捕获型的 Lambda 表达式，它们的性能上限皆可以达到直接调用的性能。其中，捕获型 Lambda 表达式借助了即时编译器中的逃逸分析，来避免实际的新建适配器类实例的操作。

# 方法句柄
invokedynamic 底层机制的基石：方法句柄。

方法句柄是一个强类型的、能够被直接执行的引用。它仅关心所指向方法的参数类型以及返回类型，而不关心方法所在的类以及方法名。方法句柄的权限检查发生在创建过程中，相较于反射调用节省了调用时反复权限检查的开销。

方法句柄可以通过 invokeExact 以及 invoke 来调用。其中，invokeExact 要求传入的参数和所指向方法的描述符严格匹配。方法句柄还支持增删改参数的操作，这些操作是通过生成另一个充当适配器的方法句柄来实现的。

方法句柄的调用和反射调用一样，都是间接调用，同样会面临无法内联的问题。

# 栈上分配和TLAB

## 栈上分配
JVM提供了一种叫做栈上分配的概念，针对那些作用域不会逃逸出方法的对象，在分配内存时不在将对象分配在堆内存中，而是将对象属性打散后分配在栈（线程私有的，属于栈内存）上，这样，随着方法的调用结束，栈空间的回收就会随着将栈上分配的打散后的对象回收掉，不再给gc增加额外的无用负担，从而提升应用程序整体的性能

## 线程私有分配区TLAB
对象分配在堆上，而堆是一个全局共享的区域，当多个线程同一时刻操作堆内存分配对象空间时，就需要通过锁机制或者指针碰撞的方式确保不会申请到同一块内存，而这带来的效果就是对象分配效率变差（尽管JVM采用了CAS的形式处理分配失败的情况），但是对于存在竞争激烈的分配场合仍然会导致效率变差。因此，在Hotspot 1.6的实现中引入了TLAB技术。

TLAB全称ThreadLocalAllocBuffer，是线程的一块私有内存，如果设置了虚拟机参数 -XX:UseTLAB，在线程初始化时，同时也会申请一块指定大小的内存，只给当前线程使用，这样每个线程都单独拥有一个Buffer，如果需要分配内存，就在自己的Buffer上分配，这样就不存在竞争的情况，可以大大提升分配效率。

TLAB只是让每个线程有私有的分配指针，但底下存对象的内存空间还是给所有线程访问的，只是其它线程无法在这个区域分配而已。当一个TLAB用满（分配指针_top撞上分配极限_end了），就新申请一个TLAB。
```java
class ThreadLocalAllocBuffer: public CHeapObj<mtThread> {
  HeapWord* _start;                              // address of TLAB
  HeapWord* _top;                                // address after last allocation
  HeapWord* _pf_top;                             // allocation prefetch watermark
  HeapWord* _end;                                // allocation end (excluding alignment_reserve)
  size_t    _desired_size;                       // desired size   (including alignment_reserve)
  size_t    _refill_waste_limit;                 // hold onto tlab if free() is larger than this
  .....................省略......................
}
```
TLAB空间主要有3个指针：_start、_top、_end。_start指针表示TLAB空间的起始内存，_end指针表示TLAB空间的结束地址，通过_start和_end指针，表示线程管理的内存区域，每个线程都会从Eden分配一大块空间（TLAB实际上是一块Eden区中划出的线程私有的堆空间），标识出 Eden 里被这个 TLAB 所管理的区域，卡住eden里的一块空间不让其它线程来这里分配

当进行对象的内存划分的时候，就会通过移动_top指针分配内存（TLAB，Eden，To，From 区主要采用指针碰撞来分配内存（pointer bumping）），在TLAB空间为对象分配内存需要遵循下面的原则：
1. obj_size + tlab_top <= tlab_end，直接在TLAB空间分配对象
2. obj_size + tlab_top >= tlab_end  &&  tlab_free > tlab_refill_waste_limit，对象不在TLAB分配，在Eden区分配。（tlab_free：剩余的内存空间，tlab_refill_waste_limit：允许浪费的内存空间）
3. obj_size + tlab_top >= tlab_end  &&  tlab_free < _refill_waste_limit，重新分配一块TLAB空间，在新的TLAB中分配对象

## 总体流程 
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/JVM-2.png)

## 对象分配流程图
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/JVM-1.png)

