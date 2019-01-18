<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [抽象类和接口的对比](#%E6%8A%BD%E8%B1%A1%E7%B1%BB%E5%92%8C%E6%8E%A5%E5%8F%A3%E7%9A%84%E5%AF%B9%E6%AF%94)
- [equals()](#equals)
- [java中double和float精度丢失问题及解决方法](#java%E4%B8%ADdouble%E5%92%8Cfloat%E7%B2%BE%E5%BA%A6%E4%B8%A2%E5%A4%B1%E9%97%AE%E9%A2%98%E5%8F%8A%E8%A7%A3%E5%86%B3%E6%96%B9%E6%B3%95)
  - [BigDecimal](#bigdecimal)
- [注解](#%E6%B3%A8%E8%A7%A3)
  - [元注解（4个）](#%E5%85%83%E6%B3%A8%E8%A7%A34%E4%B8%AA)
  - [自定义注解](#%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B3%A8%E8%A7%A3)
- [Arrays.sort()原理分析](#arrayssort%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90)
  - [源码中的快速排序，主要做了以下几个方面的优化](#%E6%BA%90%E7%A0%81%E4%B8%AD%E7%9A%84%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E4%B8%BB%E8%A6%81%E5%81%9A%E4%BA%86%E4%BB%A5%E4%B8%8B%E5%87%A0%E4%B8%AA%E6%96%B9%E9%9D%A2%E7%9A%84%E4%BC%98%E5%8C%96)
- [foreach和while的区别(编译之后)](#foreach%E5%92%8Cwhile%E7%9A%84%E5%8C%BA%E5%88%AB%E7%BC%96%E8%AF%91%E4%B9%8B%E5%90%8E)
- [创建一个类的几种方法?](#%E5%88%9B%E5%BB%BA%E4%B8%80%E4%B8%AA%E7%B1%BB%E7%9A%84%E5%87%A0%E7%A7%8D%E6%96%B9%E6%B3%95)
- [Redirect和forward](#redirect%E5%92%8Cforward)
- [什么是泛型，为什么要使用以及类型擦除](#%E4%BB%80%E4%B9%88%E6%98%AF%E6%B3%9B%E5%9E%8B%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E4%BD%BF%E7%94%A8%E4%BB%A5%E5%8F%8A%E7%B1%BB%E5%9E%8B%E6%93%A6%E9%99%A4)
- [Object跟这些标记符代表的java类型有啥区别呢？](#object%E8%B7%9F%E8%BF%99%E4%BA%9B%E6%A0%87%E8%AE%B0%E7%AC%A6%E4%BB%A3%E8%A1%A8%E7%9A%84java%E7%B1%BB%E5%9E%8B%E6%9C%89%E5%95%A5%E5%8C%BA%E5%88%AB%E5%91%A2)
- [Java 异常的体系结构](#java-%E5%BC%82%E5%B8%B8%E7%9A%84%E4%BD%93%E7%B3%BB%E7%BB%93%E6%9E%84)
- [throw和throws区别](#throw%E5%92%8Cthrows%E5%8C%BA%E5%88%AB)
- [.class 文件是什么类型文件](#class-%E6%96%87%E4%BB%B6%E6%98%AF%E4%BB%80%E4%B9%88%E7%B1%BB%E5%9E%8B%E6%96%87%E4%BB%B6)
- [java中序列化之子类继承父类序列化](#java%E4%B8%AD%E5%BA%8F%E5%88%97%E5%8C%96%E4%B9%8B%E5%AD%90%E7%B1%BB%E7%BB%A7%E6%89%BF%E7%88%B6%E7%B1%BB%E5%BA%8F%E5%88%97%E5%8C%96)
- [标识符](#%E6%A0%87%E8%AF%86%E7%AC%A6)
- [Integer i=new Integer(127);和Integer i=127;的区别](#integer-inew-integer127%E5%92%8Cinteger-i127%E7%9A%84%E5%8C%BA%E5%88%AB)
- [手写单例模式](#%E6%89%8B%E5%86%99%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F)
- [为什么线程通信的方法wait(), notify()和notifyAll()被定义在Object类里？](#%E4%B8%BA%E4%BB%80%E4%B9%88%E7%BA%BF%E7%A8%8B%E9%80%9A%E4%BF%A1%E7%9A%84%E6%96%B9%E6%B3%95wait-notify%E5%92%8Cnotifyall%E8%A2%AB%E5%AE%9A%E4%B9%89%E5%9C%A8object%E7%B1%BB%E9%87%8C)
- [Java中wait 和sleep 方法比较](#java%E4%B8%ADwait-%E5%92%8Csleep-%E6%96%B9%E6%B3%95%E6%AF%94%E8%BE%83)
- [hashCode和equals方法的关系](#hashcode%E5%92%8Cequals%E6%96%B9%E6%B3%95%E7%9A%84%E5%85%B3%E7%B3%BB)
- [Object类中有哪些方法](#object%E7%B1%BB%E4%B8%AD%E6%9C%89%E5%93%AA%E4%BA%9B%E6%96%B9%E6%B3%95)
- [String s=new String("xyz")究竟创建String Object分为两种情况](#string-snew-stringxyz%E7%A9%B6%E7%AB%9F%E5%88%9B%E5%BB%BAstring-object%E5%88%86%E4%B8%BA%E4%B8%A4%E7%A7%8D%E6%83%85%E5%86%B5)
- [什么是值传递和引用传递](#%E4%BB%80%E4%B9%88%E6%98%AF%E5%80%BC%E4%BC%A0%E9%80%92%E5%92%8C%E5%BC%95%E7%94%A8%E4%BC%A0%E9%80%92)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## 抽象类和接口的对比
| 参数        | 抽象类   |  接口  |
| --------   | -----:   | :----: |
|默认的方法实现	|它可以有默认的方法实现	|接口完全是抽象的。它根本不存在方法的实现|
|实现	|子类使用extends关键字来继承抽象类。如果子类不是抽象类的话，它需要提供抽象类中所有声明的方法的实现。|	子类使用关键字implements来实现接口。它需要提供接口中所有声明的方法的实现|
|构造器	|抽象类可以有构造器|	接口不能有构造器|
|与正常Java类的区别	|除了你不能实例化抽象类之外，它和普通Java类没有任何区别	|接口是完全不同的类型|
|访问修饰符	|抽象方法可以有public、protected和default这些修饰符|	接口方法默认修饰符是public。你不可以使用其它修饰符。|
|main方法|	抽象方法可以有main方法并且我们可以运行它	|接口没有main方法，因此我们不能运行它。|
|多继承	|抽象方法可以继承一个类和实现多个接口	|接口只可以继承一个或多个其它接口|
|速度|	它比接口速度要快|	接口是稍微有点慢的，因为它需要时间去寻找在类中实现的方法。|
|添加新方法	|如果你往抽象类中添加新的方法，你可以给它提供默认的实现。因此你不需要改变你现在的代码。|	如果你往接口中添加方法，那么你必须改变实现该接口的类。|


## equals()
1. Obejct的equals()源码
```java
public boolean equals(Object obj) {
        return (this == obj);
    }

```
从代码可知，Object类的equals方法是比较的地址，所以最初的equals方法和==的作用是一致的

像String、Double、Integer、Date、Point这些不变类都重写了equals()，重写都是为判断的根据是值，而不地址

比如String的equals()源码
```java
public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```
比如Integer的equals()源码
```java
public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return value == ((Integer)obj).intValue();
        }
        return false;
    }
```
compareTo()
```java
  public int compareTo(Integer anotherInteger) {
        return compare(this.value, anotherInteger.value);
    }

   public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
   }
```

## java中double和float精度丢失问题及解决方法
```java
 System.out.println(0.11+2001299.32);
```
控制台输出2001299.4300000002

在需要精确的表示两位小数时我们需要把他们转换为BigDecimal对象，然后再进行运算。

另外需要注意

使用BigDecimal(double val)构造函数时仍会存在精度丢失问题，建议使用BigDecimal(String val)


### BigDecimal
```java
public BigDecimal(double val)
```
将 double 转换为 BigDecimal，后者是 double 的二进制浮点值准确的十进制表示形式。返回的 BigDecimal 的标度是使 (10scale × val) 为整数的最小值。
注：

1. 此构造方法的结果有一定的不可预知性。有人可能认为在 Java 中写入 new BigDecimal(0.1) 所创建的 BigDecimal 正好等于 0.1（非标度值 1，其标度为 1），但是它实际上等于 0.1000000000000000055511151231257827021181583404541015625。这是因为 0.1 无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。这样，传入 到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
2. 另一方面，String 构造方法是完全可预知的：写入 new BigDecimal("0.1") 将创建一个 BigDecimal，它正好 等于预期的 0.1。因此，比较而言，通常建议优先使用 String 构造方法。
3. 当 double 必须用作 BigDecimal 的源时，请注意，此构造方法提供了一个准确转换；它不提供与以下操作相同的结果：先使用 Double.toString(double) 方法，然后使用 BigDecimal(String) 构造方法，将 double 转换为 String。要获取该结果，请使用 static valueOf(double) 方法。

## 注解

### 元注解（4个）

1. @Target – 作用域
- ElementType.TYPE 用于描述类、接口或enum声明
- ElementType.FIELD 用于描述实例变量
- ElementType.METHOD 方法声明
- ElementType.PARAMETER 参数
- ElementType.CONSTRUCTOR 构造器
- ElementType.LOCAL_VARIABLE 局部变量
- ElementType.ANNOTATION_TYPE 另一个注释
- ElementType.PACKAGE  包

2. @Retention 生命周期,定义了该Annotation被保留的时间长短
- RetentionPolicy.SOURCE – 在源文件中有效（即源文件保留）
- RetentionPolicy.CLASS – 在class文件中有效（即class保留）
- RetentionPolicy.RUNTIME– 在运行时有效（即运行时保留）

3.  @Documented  是否生成javadoc文档。

4. @Inherited  是否被子类继承

### 自定义注解
使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。在定义注解时，不能继承其他的注解或接口。
@interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。可以通过default来声明参数的默认值。

格式：public @interface 注解名 {定义体}

```java
/**
 * 水果名称注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
```
```java
public class Apple {
    @FruitName("Apple")
    private String appleName;
    }
```

## Arrays.sort()原理分析
首先说一下，Collections.sort方法底层也是调用的Arrays.sort方法。

Java Arrays中提供了对所有类型的排序。其中主要分为Primitive(8种基本类型)和Object两大类。

1. 基本类型：采用调优的快速排序；

2. 对象类型：采用改进的归并排序。既快速（nlog(n)）又稳定，对象数组中保存的只是对象的引用，这样多次移位并不会造成额外的开销，但是，对象数组对比较次数一般比较敏感，有可能对象的比较比单纯数的比较开销大很多。归并排序在这方面比快速排序做得更好，这也是选择它作为对象排序的一个重要原因之一。

排序优化：实现中快排和归并都采用递归方式，而在递归的底层，也就是待排序的数组长度小于7时，直接使用冒泡排序，而不再递归下去.

分析: 长度为6的数组冒泡排序总比较次数最多也就1+2+3+4+5+6=21次，最好情况下只有6次比较。而快排或归并涉及到递归调用等的开销，其时间效率在n较小时劣势就凸显了，因此这里采用了冒泡排序，这也是对快速排序极重要的优化。

### 源码中的快速排序，主要做了以下几个方面的优化
1. 当待排序的数组中的元素个数较少时，源码中的阀值为7，采用的是插入排序。尽管插入排序的时间复杂度为0(n^2)，但是当数组元素较少时，插入排序优于快速排序，因为这时快速排序的递归操作影响性能。

2. 较好的选择了划分元（基准元素）。能够将数组分成大致两个相等的部分，避免出现最坏的情况。例如当数组有序的的情况下，选择第一个元素作为划分元，将使得算法的时间复杂度达到O(n^2).

　　源码中选择划分元的方法:

- 当数组大小为 size=7 时 ，取数组中间元素作为划分元。int n=m>>1;(此方法值得借鉴)

- 当数组大小 7<size<=40时，取首、中、末三个元素中间大小的元素作为划分元。

- 当数组大小 size>40 时 ，从待排数组中较均匀的选择9个元素，选出一个伪中数做为划分元。

3.　普通的快速排序算法，经过一次划分后，将划分元排到素组较中间的位置，左边的元素小于划分元，右边的元素大于划分元，而没有将与划分元相等的元素放在其附近，这一点，在Arrays.sort()中得到了较大的优化，将与划分元相等的元素移到数组中间来


jdk1.7后底层实现都是TimeSort实现的。TimSort是优化后的归并排序，TimSort算法就是找到已经排好序数据的子序列，然后对剩余部分排序，然后合并起来.


## foreach和while的区别(编译之后) 
1. 在while循环里，会读入一行输入，把它存入某个变量并且执行循环主体。然后，它再回头去找其他的输入行。
2. 在foreach循环中，整行输入操作符会在列表上下文中执行（因为foreach需要逐行处理列表的内容）。在循环开始执行之前，它必须先将输入全部读进来。
3. 当输入大容量的文件时，使用foreach会占用大量的内存。两者的差异会十分明显。因此，最好的做法，通常是尽量使用while循环的简写，让它每次处理一行。

foreach 在编译的时候编译器会自动将对for这个关键字的使用转化为对目标的迭代器的使用，这就是foreach循环的原理

## 创建一个类的几种方法?

1. 使用new关键字 → 调用了构造函数
2. 使用Class类的newInstance方法  → 调用了构造函数
```java
Employee emp2 = (Employee)Class.forName("org.programming.mitra.exercises.Employee").newInstance();
```
3. 使用Constructor类的newInstance方法  → 调用了构造函数
```java
Constructor<Employee> constructor = Employee.class.getConstructor();
Employee emp3 = constructor.newInstance();
```
4. 使用clone方法   → 没有调用构造函数
5. 使用反序列化  → 没有调用构造函数

```java
ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
Employee emp5 = (Employee) in.readObject();
```

## Redirect和forward

1. 上图所示的间接转发请求的过程如下：
浏览器向Servlet1发出访问请求；
Servlet1调用sendRedirect()方法，将浏览器重定向到Servlet2；
浏览器向servlet2发出请求；
最终由Servlet2做出响应。 

2.  上图所示的直接转发请求的过程如下：
浏览器向Servlet1发出访问请求；
Servlet1调用forward()方法，在服务器端将请求转发给Servlet2；
最终由Servlet2做出响应。


##  什么是泛型，为什么要使用以及类型擦除
1. 泛型的本质就是“参数化类型”，也就是说所操作的数据类型被指定为一个参数。
创建集合时就指定集合元素的数据类型，该集合只能保存其指定类型的元素，
避免使用强制类型转换。
2. Java 编译器生成的字节码是不包含泛型信息的，泛型类型信息将在 编译处理 时
被擦除，这个过程即 类型擦除。类型擦除可以简单的理解为将泛型 java 代码转
换为普通 java 代码，只不过编译器更直接点，将泛型 java 代码直接转换成普通
java 字节码。

类型擦除的主要过程如下：

1. 将所有的泛型参数用其最左边界（最顶级的父类型）类型替换。
2. 移除所有的类型参数。





## Object跟这些标记符代表的java类型有啥区别呢？  
Object是所有类的根类，任何类的对象都可以设置给该Object引用变量，使用的时候可能需要类型强制转换，但是用使用了泛型T、E等这些标识符后，在实际用之前类型就已经确定了，不需要再进行类型强制转换。



## Java 异常的体系结构
Java把异常当作对象来处理，并定义一个基类java.lang.Throwable作为所有异常的超类。

在Java API中已经定义了许多异常类，这些异常类分为两大类，错误Error和异常Exception。

Java异常层次结构图如下图所示：

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Java-1.jpg)


Error：Error类对象由 Java 虚拟机生成并抛出，Error表示编译时和系统错误，通常不能预期和恢复，比如硬件故障、JVM崩溃、内存不足等 。例如，Java虚拟机运行错误（Virtual MachineError），当JVM不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。这些异常发生时，Java虚拟机（JVM）一般会选择线程终止；还有发生在虚拟机试图执行应用时，如类定义错误（NoClassDefFoundError）、链接错误（LinkageError）。这些错误是不可查的，因为它们在应用程序的控制和处理能力之 外，而且绝大多数是程序运行时不允许出现的状况。对于设计合理的应用程序来说，即使确实发生了错误，本质上也不应该试图去处理它所引起的异常状况。在Java中，错误通常是使用Error的子类描述。

Exception：在Exception分支中有一个重要的子类RuntimeException（运行时异常），该类型的异常自动为你所编写的程序定义ArrayIndexOutOfBoundsException（数组下标越界）、NullPointerException（空指针异常）、ArithmeticException（算术异常）、MissingResourceException（丢失资源）、ClassNotFoundException（找不到类）等异常，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生；而RuntimeException之外的异常我们统称为非运行时异常，类型上属于Exception类及其子类，从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。
## throw和throws区别
**throw：（针对对象的做法）**
 抛出一个异常，可以是系统定义的，也可以是自己定义的
 

```java
public void yichang(){
    NumberFormatException e = new NumberFormatException();
    throw e;
}
```
**throws：（针对一个方法抛出的异常）**
抛出一个异常，可以是系统定义的，也可以是自己定义的。

```java
public void yichang() throws NumberFormatException{
    int a = Integer.parseInt("10L");
}
```
1. throws出现在方法函数头；而throw出现在函数体。
2. throws表示出现异常的一种可能性，并不一定会发生这些异常；throw则是抛出了异常，执行throw则一定抛出了某种异常。
3. 两者都是消极处理异常的方式（这里的消极并不是说这种方式不好），只是抛出或者可能抛出异常，但是不会由函数去处理异常，真正的处理异常由函数的上层调用处理。


##  .class 文件是什么类型文件
class文件是一种8位字节的二进制流文件



## java中序列化之子类继承父类序列化
父类实现了Serializable，子类不需要实现Serializable

相关注意事项

1. 序列化时，只对对象的状态进行保存，而不管对象的方法；
2. 当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口；
3. 当一个对象的实例变量引用其他对象，序列化该对象时也把引用对象进行序列化；

并非所有的对象都可以序列化，至于为什么不可以，有很多原因了,比如：

1.安全方面的原因，比如一个对象拥有private，public等field，对于一个要传输的对象，比如写到文件，或者进行rmi传输等等，在序列化进行传输的过程中，这个对象的private等域是不受保护的。
2. 资源分配方面的原因，比如socket，thread类，如果可以序列化，进行传输或者保存，也无法对他们进行重新的资源分配，而且，也是没有必要这样实现。
2,反过来父类未实现Serializable，子类实现了，序列化子类实例的时候，父类的属性是直接被跳过不保存，还是能保存但不能还原？（答案：值不保存）

 

解：父类实现接口后，所有派生类的属性都会被序列化。子类实现接口的话，父类的属性值丢失。

java中序列化之子类继承父类序列化



## 标识符
标识符可以包括这4种字符：字母、下划线、$、数字；开头不能是数字；不能是关键字





## Integer i=new Integer(127);和Integer i=127;的区别
Integer i = 127的时候，使用Java常量池技术，是为了方便快捷地创建某些对象，当你需要一个对象时候，就去这个池子里面找，找不到就在池子里面创建一个。但是必须注意 如果对象是用new 创建的。那么不管是什么对像，它是不会放到池子里的，而是向堆申请新的空间存储。Byte,Short,Integer,Long,Character这5种整型的包装类也只是在对应值在-128到127之间的数时才可使用对象池。超过了就要申请空间创建对象了

```java
    int i1=128;
    Integer i2=128;
    Integer i3=new Integer(128);//自动拆箱
    
    System.out.println(i1==i2);//true
    System.out.println(i1==i3);//true
    
    Integer i5=127;
    Integer i6=127;
    System.out.println(i5==i6);//true
    
    
    Integer i5=127;
    Integer ii5=new Integer(127);
    System.out.println(i5==ii5);//false
    
    Integer i7=new Integer(127);
    Integer i8=new Integer(127);
    System.out.println(i7==i8);//false
```



## 手写单例模式
最好的单例模式是静态内部类，不要写双重检验
```java
private static class LazySomethingHolder {
  public static Something something = new Something();
}

public static Something getInstance() {
  return LazySomethingHolder.something;
}
```





## 为什么线程通信的方法wait(), notify()和notifyAll()被定义在Object类里？

Java的每个对象中都有一个锁(monitor，也可以成为监视器) 并且wait()，notify()等方法用于等待对象的锁或者通知其他线程对象的监视器可用。在Java的线程中并没有可供任何对象使用的锁和同步器。这就是为什么这些方法是Object类的一部分，这样Java的每一个类都有用于线程间通信的基本方法

## Java中wait 和sleep 方法比较

1. 这两个方法来自不同的类分别是Thread和Object  
2. 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。  
3. wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)  
4. sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常  
  
5. sleep方法属于Thread类中方法，表示让一个线程进入睡眠状态，等待一定的时间之后，自动醒来进入到可运行状态，不会马上进入运行状态，因为线程调度机制恢复线程的运行也需要时间，一个线程对象调用了sleep方法之后，并不会释放他所持有的所有对象锁，所以也就不会影响其他进程对象的运行。但在sleep的过程中过程中有可能被其他对象调用它的interrupt(),产生InterruptedException异常，如果你的程序不捕获这个异常，线程就会异常终止，进入TERMINATED状态，如果你的程序捕获了这个异常，那么程序就会继续执行catch语句块(可能还有finally语句块)以及以后的代码。  
  
- 注意sleep()方法是一个静态方法，也就是说他只对当前对象有效，通过t.sleep()让t对象进入sleep，这样的做法是错误的，它只会是使当前线程被sleep 而不是t线程  
  
7.  wait属于Object的成员方法，一旦一个对象调用了wait方法，必须要采用notify()和notifyAll()方法唤醒该进程;如果线程拥有某个或某些对象的同步锁，那么在调用了wait()后，这个线程就会释放它持有的所有同步资源，而不限于这个被调用了wait()方法的对象。wait()方法也同样会在wait的过程中有可能被其他对象调用interrupt()方法而产生  

## hashCode和equals方法的关系
在有些情况下，程序设计者在设计一个类的时候为需要重写equals方法，比如String类，但是千万要注意，在重写equals方法的同时，必须重写hashCode方法。
也就是说对于两个对象，如果调用equals方法得到的结果为true，则两个对象的hashcode值必定相等；
如果equals方法得到的结果为false，则两个对象的hashcode值不一定不同；
如果两个对象的hashcode值不等，则equals方法得到的结果必定为false；
如果两个对象的hashcode值相等，则equals方法得到的结果未知。

## Object类中有哪些方法
Object是所有类的父类，它有很多类对象会用到的方法

Object方法：equals()、toString()、finalize()、hashCode()、getClass()、clone()、wait()、notify()、notifyAll()
```java
package java.lang;
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }
    // 返回一个对象的运行时类,获得类型的信息。
    public final native Class<?> getClass();
    // 该方法将对象的内存地址进行哈希运算,返回一个int类型的哈希值,是相等对象拥有相同的哈希码,尽量让不等的对象具有不同的哈希码。
    public native int hashCode();
    //指示某个其他对象是否与此对象"相等"。
    public boolean equals(Object obj) {
        return (this == obj);
    }
    //创建并返回此对象的一个副本(复制对象)
    protected native Object clone() throws CloneNotSupportedException;
    //返回该对象的字符串表示。以便用户能够获得一些有关对象状态的基本信息。简单说就是利用字符串来表示对象。
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    //唤醒在此对象监视器上等待的单个线程。
    public final native void notify();
    //唤醒在次对象监视器上等待的所有线程。
    public final native void notifyAll();
    //导致当前的线程等待,直到其他线程调用此对象的notify()方法或notifyAll()方法,或者超过指定的时间量。
    public final native void wait(long timeout) throws InterruptedException;
    //导致当前的线程等待,直到其他线程调用此对象的notify()方法或notifyAll方法,或者其他某个线程中断当前线程,或者已超过某个实际时间量。
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }
        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }
        if (nanos > 0) {
            timeout++;
        }
        wait(timeout);
    }
    //导致当前的线程等待,直到其他线程调用此对象的notify()方法或notifyAll()方法。
    public final void wait() throws InterruptedException {
        wait(0);
    }
    //当垃圾回收器确定不存在对该对象的更多引用时，对象的垃圾回收器调用该方法。
    protected void finalize() throws Throwable { }
}
```

## String s=new String("xyz")究竟创建String Object分为两种情况
1. 如果String常理池中，已经创建"xyz"，则不会继续创建，此时只创建了一个对象new String("xyz")；
2. 如果String常理池中，没有创建"xyz"，则会创建两个对象，一个对象的值是"xyz"，一个对象new String("xyz")。



## 什么是值传递和引用传递
值传递
```java
public class TempTest {

  private void test1(int a) {
    a = 5;
    System.out.println("test1方法中的a=" + a);
  }

  public static void main(String[] args) {
    TempTest t = new TempTest();
    int a = 3;
    t.test1(11);
    System.out.println("main方法中a=" + a);
  }

}
```
test1方法中的a=5
main方法中a=3
值传递：传递的是值的拷贝，传递后就互不相关了
引用传递：传递的是变量所对应的内存空间的地址

```java
public class TempTest {
  private void test1(A a) {
    a.age = 20;
    System.out.println("test1方法中a=" + a.age);
  }

  public static void main(String[] args) {
    TempTest t = new TempTest();
    A a = new A();
    a.age = 10;
    t.test1(a);
    System.out.println("main方法中a=" + a.age);
  }
}

class A {
  public int age = 0;
}
```
test1方法中a=20
main方法中a=20
传递前和传递后都指向同一个引用（同一个内存空间）
如果不互相影响，方法是在test1方法里面新new一个实例就可以了





