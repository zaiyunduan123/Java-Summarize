## HashSet和TreeSet区别
**HashSet**

1. 不能保证元素的排列顺序，顺序有可能发生变化
2. 不是同步的
3. 集合元素可以是null,但只能放入一个null
当向HashSet结合中存入一个元素时，HashSet会调用该对象的hashCode()方法来得到该对象的hashCode值，然后根据 hashCode值来决定该对象在HashSet中存储位置。

**TreeSet**

1. TreeSet是SortedSet接口的唯一实现类
2. TreeSet可以确保集合元素处于排序状态。TreeSet支持两种排序方式，自然排序 和定制排序，其中自然排序为默认的排序方式。向TreeSet中加入的应该是同一个类的对象



## 讲一下LinkedHashMap
LinkedHashMap的实现就是HashMap+LinkedList的实现方式，以HashMap维护数据结构，以LinkList的方式维护数据插入顺序

LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的。
在遍历的时候会比HashMap慢TreeMap能够把它保存的记录根据键排序，默认是按升序排序，也可以指定排序的比较器

利用LinkedHashMap实现LRU算法缓存（
1. LinkedList首先它是一个Map，Map是基于K-V的，和缓存一致
2. LinkedList提供了一个boolean值可以让用户指定是否实现LRU）



## Java8 中HashMap的优化（引入红黑树的数据结构和扩容的优化）
1. if (binCount >= TREEIFY_THRESHOLD - 1) 
当符合这个条件的时候，把链表变成treemap红黑树，这样查找效率从o(n)变成了o(log n) ，在JDK1.8的实现中，优化了高位运算的算法，通过hashCode()的高16位异或低16位实现的：
2. 我们使用的是2次幂的扩展(指长度扩为原来2倍)，所以，元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置


这里的Hash算法本质上就是三步：取key的hashCode值、高位运算、取模运算。



**元素在重新计算hash之后，因为n变为2倍，那么n-1的mask范围在高位多1bit(红色)，因此新的index就会发生这样的变化：**
hashMap 1.8 哈希算法例图2
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-2.jpg)
**因此，我们在扩充HashMap的时候，不需要像JDK1.7的实现那样重新计算hash，只需要看看原来的hash值新增的那个bit是1还是0就好了，是0的话索引没变，是1的话索引变成“原索引+oldCap”**



##  Map遍历的keySet()和entrySet()性能差异原因

```java
Set<Entry<String, String>> entrySet = map.entrySet();
Set<String> set = map.keySet();` 
```
1. keySet（）循环中通过key获取对应的value的时候又会调用getEntry（）进行循环。循环两次
2. entrySet（）直接使用getEntry（）方法获取结果，循环一次
2. 所以 keySet（）的性能会比entrySet（）差点。所以遍历map的话还是用entrySet()来遍历
```java
 public V get(Object key) {
        if (key == null)
            return getForNullKey();
        Entry<K,V> entry = getEntry(key);

        return null == entry ? null : entry.getValue();
    }    
```

```java
final Entry<K,V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }

        int hash = (key == null) ? 0 : hash(key);
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
}
```
## HashMap中的indexFor方法
在HashMap的工作原理，发现它调用了 indexFor(int h, int length) 方法来计算Entry对象保存在 table中的数组索引值：

```
static int indexFor(int h, int length) {
    return h & (length-1);
}
```
HashMap的初始容量和扩容都是以2的次方来进行的，那么length-1换算成二进制的话肯定所有位都为1，就比如2的3次方为8，length-1的二进制表示就是111， 而按位与计算的原则是两位同时为“1”，结果才为“1”，否则为“0”。所以h& (length-1)运算从数值上来讲其实等价于对length取模，也就是h%length。


只有当数组长度为2的n次方时，那么length-1换算成二进制的话肯定所有位都为1,不同的key计算得出的index索引相同的几率才会较小，数据在数组上分布也比较均匀，碰撞的几率也小，相对的，查询的时候就不用遍历某个位置上的链表，这样查询效率也就较高了。


## 如何删除ArrayList里面的元素
使用以下for循环使用remove()删除是有问题的，因为每次删除一个元素，后面元素往前移，数组大小也变小，会到数组下标越界异常
```java
for (int i = 0;i< list1.size();i++){
            list1.remove(i);
        }
```
推荐两种方法：

1. 根据长度，不断删除第一个元素，
```java
for (int i = 0;i< list1.size();i++){
            list1.remove(0);
        }
```
2. 使用迭代器(推荐)，不会导致数组长度变化而抛异常
```java
   Iterator<Integer> iter = list1.iterator();
        while(iter.hasNext()){
            Integer s = iter.next();
            if(s.equals("1")){
                iter.remove();
            }
        }
```

## 并发的HashMap为什么会引起死循环？
线程不安全的HashMap, HashMap在并发执行put操作时会引起死循环，是因为多线程会导致HashMap的Entry链表形成环形数据结构，查找时会陷入死循环

在扩容resize（）方法中，调用transfer方法，把旧表中的元素添加到新表中，这也是引起死循环的根本原因所在
```java
do {
    Entry<K,V> next = e.next; // <--假设线程一执行到这里就被调度挂起了
    int i = indexFor(e.hash, newCapacity);
    e.next = newTable[i];
    newTable[i] = e;
    e = next;
} while (e != null);
```
执行一：  线程A执行到transfer函数中（1）处挂起（transfer函数代码中有标注）。此时在线程A的栈中
```java
e = 3
next = 7
```

执行二：线程B执行 transfer函数中的while循环，即会把原来的table变成新一table（线程B自己的栈中），再写入到内存,如下图（假设两个元素在新的hash函数下也会映射到同一个位置）
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-9.jpg)


执行三： 线程A解挂，接着执行（看到的仍是旧表），即从transfer代码（1）处接着执行，当前的 e = 3, next = 7, 上面已经描述。

1. 处理元素 3 ， 将 3 放入 线程A自己栈的新table中（新table是处于线程A自己栈中，是线程私有的，不肥线程2的影响），处理3后的图如下：
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-10.jpg)
2. 线程A再复制元素 7 ， **当前 e = 7 ,而next值由于线程 B 修改了它的引用，所以next 为 3** ，处理后的新表如下图
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-11.jpg)
3. 由于上面取到的next = 3, 接着while循环，即当前处理的结点为3， next就为null ，退出while循环，执行完while循环后，新表中的内容如下图：
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-12.jpg)
4. 当操作完成，执行查找时，会陷入死循环！

