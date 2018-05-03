##**volatile特性**
把对volatile变量的单个读、写，看出是使用同一个锁对这些单个读、写做了同步，比如：

```
public class VolatileFeaturesExample {

	volatile long vl = 0L;

	public void set(long l) {
		vl = l;
	}

	public void getAndIncrement() {
		vl++;// 复合（多个）volatile变量的读/写
	}

	public long get() {
		return vl;// 单个volatile变量的读
	}

}
```
等价于

```
class VolatileFeaturesExample1 {
	long vl = 0L;

	public synchronized void set(long l) {
		vl = l;
	}

	public synchronized long get() {
		return vl;
	}

	public void getAndIncrement() {
		long temp = get();
		temp += 1L;
		set(temp);

	}
}
```
因为锁happens-before规则保证释放锁和获取锁两个线程之间的内存可见性，由可以推出，volatile变量的读总能看到对这个volatile变量最后的写入，而锁也决定了临界区代码的执行具有原子性，也就说，volatile变量同样对读写具有原子性

由上得出，volatile变量具有下列特性：
1、可见性，volatile变量的读总能看到对这个volatile变量最后的写入
2、原子性，对任意单个volatile变量的读写具有原子性，但volatile++复合操作是不具有原则性的
##**volatile写/读建立的happens before关系**
从内存的角度来说，volatile的写-读与锁的释放-获取有相同的内存效果

```
public class VolatileExample {
	int a=0;
	volatile boolean flag=false;
	
	public void writer(){
		a=1;   //1
		flag=true;//2
	}
	public void reader(){
		if(flag){//3
			int i=a;//4
			
		}
	}
}
```
1、根据程序次序规则，1 happens before 2，3 happens before 4
2、根据volatile规则，2 happens before 3
3、根据happens before的传递规则，1 happens before 4
![这里写图片描述](http://img.blog.csdn.net/20170809005605309?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanl4bXVzdA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
（1）当执行写入volatile时，也就是2，JMM会将该线程A对应本地内存更新过的共享变量刷新到主内存，那到共享变量a对其他线程是可见的，也就读到a就是想要的1，而不是0
（2）当读一个volatile变量时，JMM会把该线程B对应的本地内存置为无效，线程直接从主内存读取共享变量，同时该读操作会把本地内存的值更为与主内存的值统一


##volatile内存语义的实现
下面看看JMM如何实现volatile写/读的内存语义
重排序分为编译器重排序和处理器重排序，JMM会限制这两种类型的重排序类型来保证volatile的内存语义
![这里写图片描述](http://img.blog.csdn.net/20170809064111483?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanl4bXVzdA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
1、第二个操作是volatile写时，第一个操作不管是什么，都不能重排序
2、第一个操作是volatile读时，第二个操作不管是什么，都不能重排序
3、第一个操作是volatile是写，第二个操作是volatile是读，不能重排序


**JMM内存屏障插入策略：（Load：加载（读）、Store：保存（写），屏障名称就可以看出读写的先后顺序）**
1、在每个volatile写操作前插入StroreStore屏障
2、在每个volatile写操作前插入StroreLoad屏障
3、在每个volatile读操作前插入LoadLoad屏障
4、在每个volatile读操作前插入LoadStore屏障

<font size=5>volatile写操作</font>
![这里写图片描述](http://img.blog.csdn.net/20170809011911598?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanl4bXVzdA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
上面的StroreStore屏障保证了在volatile写之前，其前面的所有普通写操作对任意处理器都是可见的，因为StroreStore屏障保障所有的普通写在本地内存的数据在voltile写之前刷新到主内存

而volatile写后面的StoreLoad屏障，作用是避免
volatile写与后面可能有的volatile读/写操作重排序
<font size=5>volatile读操作</font>

![这里写图片描述](http://img.blog.csdn.net/20170809071121175?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanl4bXVzdA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面为代码示例

```
public class VolatileBarrierExample {
	int a;
	volatile int v1 = 1;
	volatile int v2 = 2;

	void readAndWrite() {
		int i = v1;// 第一个volatile读
		int j = v2;// 第二个volatile读
		a = i + j;// 普通写
		v1 = i + 1;// 第一个volatile写
		v2 = j * 2;// 第二个volatile写
	}
}
```
编译器生成字节码过程
![这里写图片描述](http://img.blog.csdn.net/20170809072054181?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanl4bXVzdA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
最后的StoreLoad屏障不能省略，因为编译器无法确定第二个volatile写后是否会有volatile读或写，保守起见，都会在该处加一个StoreLoad屏障


参考《深入理解Java内存模型》



