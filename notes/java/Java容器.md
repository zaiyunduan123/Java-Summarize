<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [HashSet和TreeSet区别](#hashset%E5%92%8Ctreeset%E5%8C%BA%E5%88%AB)
- [讲一下LinkedHashMap](#%E8%AE%B2%E4%B8%80%E4%B8%8Blinkedhashmap)
- [Java8 中HashMap的优化（引入红黑树的数据结构和扩容的优化）](#java8-%E4%B8%ADhashmap%E7%9A%84%E4%BC%98%E5%8C%96%E5%BC%95%E5%85%A5%E7%BA%A2%E9%BB%91%E6%A0%91%E7%9A%84%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E5%92%8C%E6%89%A9%E5%AE%B9%E7%9A%84%E4%BC%98%E5%8C%96)
- [Map遍历的keySet()和entrySet()性能差异原因](#map%E9%81%8D%E5%8E%86%E7%9A%84keyset%E5%92%8Centryset%E6%80%A7%E8%83%BD%E5%B7%AE%E5%BC%82%E5%8E%9F%E5%9B%A0)
- [HashMap中的indexFor方法](#hashmap%E4%B8%AD%E7%9A%84indexfor%E6%96%B9%E6%B3%95)
- [如何删除ArrayList里面的元素](#%E5%A6%82%E4%BD%95%E5%88%A0%E9%99%A4arraylist%E9%87%8C%E9%9D%A2%E7%9A%84%E5%85%83%E7%B4%A0)
- [并发的HashMap为什么会引起死循环？](#%E5%B9%B6%E5%8F%91%E7%9A%84hashmap%E4%B8%BA%E4%BB%80%E4%B9%88%E4%BC%9A%E5%BC%95%E8%B5%B7%E6%AD%BB%E5%BE%AA%E7%8E%AF)
- [ConcurrentHashMap原理](#concurrenthashmap%E5%8E%9F%E7%90%86)
- [HashMap原理](#hashmap%E5%8E%9F%E7%90%86)
  - [HashMap特性](#hashmap%E7%89%B9%E6%80%A7)
  - [HashMap的原理，内部数据结构](#hashmap%E7%9A%84%E5%8E%9F%E7%90%86%E5%86%85%E9%83%A8%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84)
  - [讲一下 HashMap 中 put 方法过程](#%E8%AE%B2%E4%B8%80%E4%B8%8B-hashmap-%E4%B8%AD-put-%E6%96%B9%E6%B3%95%E8%BF%87%E7%A8%8B)
  - [get()方法的工作原理](#get%E6%96%B9%E6%B3%95%E7%9A%84%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
  - [HashMap中hash函数怎么是是实现的？还有哪些 hash 的实现方式？](#hashmap%E4%B8%ADhash%E5%87%BD%E6%95%B0%E6%80%8E%E4%B9%88%E6%98%AF%E6%98%AF%E5%AE%9E%E7%8E%B0%E7%9A%84%E8%BF%98%E6%9C%89%E5%93%AA%E4%BA%9B-hash-%E7%9A%84%E5%AE%9E%E7%8E%B0%E6%96%B9%E5%BC%8F)
  - [HashMap 怎样解决冲突？](#hashmap-%E6%80%8E%E6%A0%B7%E8%A7%A3%E5%86%B3%E5%86%B2%E7%AA%81)
    - [扩展问题1：当两个对象的hashcode相同会发生什么？](#%E6%89%A9%E5%B1%95%E9%97%AE%E9%A2%981%E5%BD%93%E4%B8%A4%E4%B8%AA%E5%AF%B9%E8%B1%A1%E7%9A%84hashcode%E7%9B%B8%E5%90%8C%E4%BC%9A%E5%8F%91%E7%94%9F%E4%BB%80%E4%B9%88)
    - [扩展问题2：抛开 HashMap，hash 冲突有那些解决办法？](#%E6%89%A9%E5%B1%95%E9%97%AE%E9%A2%982%E6%8A%9B%E5%BC%80-hashmaphash-%E5%86%B2%E7%AA%81%E6%9C%89%E9%82%A3%E4%BA%9B%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95)
  - [如果两个键的hashcode相同，你如何获取值对象？](#%E5%A6%82%E6%9E%9C%E4%B8%A4%E4%B8%AA%E9%94%AE%E7%9A%84hashcode%E7%9B%B8%E5%90%8C%E4%BD%A0%E5%A6%82%E4%BD%95%E8%8E%B7%E5%8F%96%E5%80%BC%E5%AF%B9%E8%B1%A1)
  - [针对 HashMap 中某个 Entry 链太长，查找的时间复杂度可能达到 O(n)，怎么优化？](#%E9%92%88%E5%AF%B9-hashmap-%E4%B8%AD%E6%9F%90%E4%B8%AA-entry-%E9%93%BE%E5%A4%AA%E9%95%BF%E6%9F%A5%E6%89%BE%E7%9A%84%E6%97%B6%E9%97%B4%E5%A4%8D%E6%9D%82%E5%BA%A6%E5%8F%AF%E8%83%BD%E8%BE%BE%E5%88%B0-on%E6%80%8E%E4%B9%88%E4%BC%98%E5%8C%96)
  - [如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？](#%E5%A6%82%E6%9E%9Chashmap%E7%9A%84%E5%A4%A7%E5%B0%8F%E8%B6%85%E8%BF%87%E4%BA%86%E8%B4%9F%E8%BD%BD%E5%9B%A0%E5%AD%90load-factor%E5%AE%9A%E4%B9%89%E7%9A%84%E5%AE%B9%E9%87%8F%E6%80%8E%E4%B9%88%E5%8A%9E)
  - [为什么String, Interger这样的类适合作为键？](#%E4%B8%BA%E4%BB%80%E4%B9%88string-interger%E8%BF%99%E6%A0%B7%E7%9A%84%E7%B1%BB%E9%80%82%E5%90%88%E4%BD%9C%E4%B8%BA%E9%94%AE)
- [HashMap与HashTable区别](#hashmap%E4%B8%8Ehashtable%E5%8C%BA%E5%88%AB)
  - [区别](#%E5%8C%BA%E5%88%AB)
  - [能否让HashMap同步？](#%E8%83%BD%E5%90%A6%E8%AE%A9hashmap%E5%90%8C%E6%AD%A5)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

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


总结：多线程PUT操作时可能会覆盖刚PUT进去的值,扩容操作会让链表形成环形数据结构，形成死循环


## ConcurrentHashMap原理

HashTable 在每次同步执行时都要锁住整个结构。ConcurrentHashMap 锁的方式是稍微细粒度的。 ConcurrentHashMap 将 hash 表分为 16 个桶（默认值）

Java7
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-13.jpg)

ConcurrentHashMap 类中包含两个静态内部类 HashEntry 和 Segment。HashEntry 用来封装映射表的键 / 值对；Segment 用来充当锁的角色，每个 Segment 对象守护整个散列映射表的若干个桶。每个桶是由若干个 HashEntry 对象链接起来的链表。一个 ConcurrentHashMap 实例中包含由若干个 Segment 对象组成的数组。

Java8
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-14.jpg)
Java 8为进一步提高并发性，摒弃了分段锁的方案，而是直接使用一个大的数组。同时为了提高哈希碰撞下的寻址性能，Java 8在链表长度超过一定阈值（8）时将链表（寻址时间复杂度为O(N)）转换为红黑树（寻址时间复杂度为O(long(N))

Java 8的ConcurrentHashMap同样是通过Key的哈希值与数组长度取模确定该Key在数组中的索引。

对于put操作，如果Key对应的数组元素为null，则通过CAS操作将其设置为当前值。如果Key对应的数组元素（也即链表表头或者树的根元素）不为null，则对该元素使用synchronized关键字申请锁，然后进行操作。如果该put操作使得当前链表长度超过一定阈值，则将该链表转换为树，从而提高寻址效率。



## HashMap原理
### HashMap特性
　　HashMap的特性：HashMap存储键值对，实现快速存取数据；允许null键/值；非同步；不保证有序(比如插入的顺序)。实现map接口。

### HashMap的原理，内部数据结构
　　HashMap是基于hashing的原理，底层使用哈希表（数组 + 链表）实现。里边最重要的两个方法put、get，使用put(key, value)存储对象到HashMap中，使用get(key)从HashMap中获取对象。 
　　存储对象时，我们将K/V传给put方法时，它调用hashCode计算hash从而得到bucket位置，进一步存储，HashMap会根据当前bucket的占用情况自动调整容量(超过Load Facotr则resize为原来的2倍)。获取对象时，我们将K传给get，它调用hashCode计算hash从而得到bucket位置，并进一步调用equals()方法确定键值对。如果发生碰撞的时候，Hashmap通过链表将产生碰撞冲突的元素组织起来，在Java 8中，如果一个bucket中碰撞冲突的元素超过某个限制(默认是8)，则使用红黑树来替换链表，从而提高速度。

### 讲一下 HashMap 中 put 方法过程
1.对key的hashCode做hash操作，然后再计算在bucket中的index（1.5 HashMap的哈希函数）； 
2.如果没碰撞直接放到bucket里； 
3.如果碰撞了，以链表的形式存在buckets后； 
4.如果节点已经存在就替换old value(保证key的唯一性) 
5.如果bucket满了(超过阈值，阈值=loadfactor*current capacity，load factor默认0.75)，就要resize。

### get()方法的工作原理
　　通过对key的hashCode()进行hashing，并计算下标( n-1 & hash)，从而获得buckets的位置。如果产生碰撞，则利用key.equals()方法去链表中查找对应的节点。

### HashMap中hash函数怎么是是实现的？还有哪些 hash 的实现方式？
　　1. 对key的hashCode做hash操作（高16bit不变，低16bit和高16bit做了一个异或）； 
　　2. h & (length-1); //通过位操作得到下标index。

　　还有数字分析法、平方取中法、分段叠加法、 除留余数法、 伪随机数法。

### HashMap 怎样解决冲突？
　　HashMap中处理冲突的方法实际就是链地址法，内部数据结构是数组+单链表。

#### 扩展问题1：当两个对象的hashcode相同会发生什么？
　　因为两个对象的Hashcode相同，所以它们的bucket位置相同，会发生“碰撞”。HashMap使用链表存储对象，这个Entry(包含有键值对的Map.Entry对象)会存储在链表中。

#### 扩展问题2：抛开 HashMap，hash 冲突有那些解决办法？
　　开放定址法、链地址法、再哈希法。

### 如果两个键的hashcode相同，你如何获取值对象？
　　重点在于理解hashCode()与equals()。 
　　通过对key的hashCode()进行hashing，并计算下标( n-1 & hash)，从而获得buckets的位置。两个键的hashcode相同会产生碰撞，则利用key.equals()方法去链表或树（java1.8）中去查找对应的节点。

### 针对 HashMap 中某个 Entry 链太长，查找的时间复杂度可能达到 O(n)，怎么优化？
　　将链表转为红黑树，实现 O(logn) 时间复杂度内查找。JDK1.8 已经实现了。

### 如果HashMap的大小超过了负载因子(load factor)定义的容量，怎么办？
　　扩容。这个过程也叫作rehashing，因为它重建内部数据结构，并调用hash方法找到新的bucket位置。 
　　大致分两步： 
　　1.扩容：容量扩充为原来的两倍（2 * table.length）； 
　　2.移动：对每个节点重新计算哈希值，重新计算每个元素在数组中的位置，将原来的元素移动到新的哈希表中。 
　　 
补充： 
1. loadFactor：加载因子。默认值DEFAULT_LOAD_FACTOR = 0.75f； 
2. capacity：容量； 
3. threshold：阈值=capacity*loadFactor。当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍（capacity*2）； 
4. size：HashMap的大小，它是HashMap保存的键值对的数量。

###  为什么String, Interger这样的类适合作为键？
　　String, Interger这样的类作为HashMap的键是再适合不过了，而且String最为常用。 
　　因为String对象是不可变的，而且已经重写了equals()和hashCode()方法了。 
　　1.不可变性是必要的，因为为了要计算hashCode()，就要防止键值改变，如果键值在放入时和获取时返回不同的hashcode的话，那么就不能从HashMap中找到你想要的对象。不可变性还有其他的优点如线程安全。 
　　注：String的不可变性可以看这篇文章《【java基础】浅析String》。 
　　2.因为获取对象的时候要用到equals()和hashCode()方法，那么键对象正确的重写这两个方法是非常重要的。如果两个不相等的对象返回不同的hashcode的话，那么碰撞的几率就会小些，这样就能提高HashMap的性能。

## HashMap与HashTable区别
　　Hashtable可以看做是线程安全版的HashMap，两者几乎“等价”（当然还是有很多不同）。Hashtable几乎在每个方法上都加上synchronized（同步锁），实现线程安全。

###  区别
　　1.HashMap继承于AbstractMap，而Hashtable继承于Dictionary； 
　　2.线程安全不同。Hashtable的几乎所有函数都是同步的，即它是线程安全的，支持多线程。而HashMap的函数则是非同步的，它不是线程安全的。若要在多线程中使用HashMap，需要我们额外的进行同步处理； 
　　3.null值。HashMap的key、value都可以为null。Hashtable的key、value都不可以为null； 
　　4.迭代器(Iterator)。HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException。 
　　5.容量的初始值和增加方式都不一样：HashMap默认的容量大小是16；增加容量时，每次将容量变为“原始容量x2”。Hashtable默认的容量大小是11；增加容量时，每次将容量变为“原始容量x2 + 1”； 
　　6.添加key-value时的hash值算法不同：HashMap添加元素时，是使用自定义的哈希算法。Hashtable没有自定义哈希算法，而直接采用的key的hashCode()。 
　　7.速度。由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。

### 能否让HashMap同步？
　　HashMap可以通过下面的语句进行同步：Map m = Collections.synchronizeMap(hashMap);