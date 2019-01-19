<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1、Dubbo的架构原理](#1dubbo%E7%9A%84%E6%9E%B6%E6%9E%84%E5%8E%9F%E7%90%86)
  - [Dubbo架构图](#dubbo%E6%9E%B6%E6%9E%84%E5%9B%BE)
  - [节点角色说明](#%E8%8A%82%E7%82%B9%E8%A7%92%E8%89%B2%E8%AF%B4%E6%98%8E)
  - [调用关系说明](#%E8%B0%83%E7%94%A8%E5%85%B3%E7%B3%BB%E8%AF%B4%E6%98%8E)
  - [Dubbo架构具有连通性、健壮性、伸缩性、升级性四个特点](#dubbo%E6%9E%B6%E6%9E%84%E5%85%B7%E6%9C%89%E8%BF%9E%E9%80%9A%E6%80%A7%E5%81%A5%E5%A3%AE%E6%80%A7%E4%BC%B8%E7%BC%A9%E6%80%A7%E5%8D%87%E7%BA%A7%E6%80%A7%E5%9B%9B%E4%B8%AA%E7%89%B9%E7%82%B9)
- [2、Dubbo自己的SPI实现](#2dubbo%E8%87%AA%E5%B7%B1%E7%9A%84spi%E5%AE%9E%E7%8E%B0)
  - [SPI的设计目标](#spi%E7%9A%84%E8%AE%BE%E8%AE%A1%E7%9B%AE%E6%A0%87)
  - [SPI的具体约定](#spi%E7%9A%84%E5%85%B7%E4%BD%93%E7%BA%A6%E5%AE%9A)
  - [dubbo为什么不直接使用JDK的SPI？](#dubbo%E4%B8%BA%E4%BB%80%E4%B9%88%E4%B8%8D%E7%9B%B4%E6%8E%A5%E4%BD%BF%E7%94%A8jdk%E7%9A%84spi)
  - [dubbo的SPI目的：获取一个实现类的对象](#dubbo%E7%9A%84spi%E7%9B%AE%E7%9A%84%E8%8E%B7%E5%8F%96%E4%B8%80%E4%B8%AA%E5%AE%9E%E7%8E%B0%E7%B1%BB%E7%9A%84%E5%AF%B9%E8%B1%A1)
  - [ExtensionLoader](#extensionloader)
  - [关于objectFactory的一些细节](#%E5%85%B3%E4%BA%8Eobjectfactory%E7%9A%84%E4%B8%80%E4%BA%9B%E7%BB%86%E8%8A%82)
- [3、SPI 与 API的区别](#3spi-%E4%B8%8E-api%E7%9A%84%E5%8C%BA%E5%88%AB)
  - [1. 接口位于【调用方】所在的包中](#1-%E6%8E%A5%E5%8F%A3%E4%BD%8D%E4%BA%8E%E8%B0%83%E7%94%A8%E6%96%B9%E6%89%80%E5%9C%A8%E7%9A%84%E5%8C%85%E4%B8%AD)
  - [2. 接口位于【实现方】所在的包中](#2-%E6%8E%A5%E5%8F%A3%E4%BD%8D%E4%BA%8E%E5%AE%9E%E7%8E%B0%E6%96%B9%E6%89%80%E5%9C%A8%E7%9A%84%E5%8C%85%E4%B8%AD)
  - [3. 接口位于独立的包中](#3-%E6%8E%A5%E5%8F%A3%E4%BD%8D%E4%BA%8E%E7%8B%AC%E7%AB%8B%E7%9A%84%E5%8C%85%E4%B8%AD)
- [4、SPI机制的adaptive原理](#4spi%E6%9C%BA%E5%88%B6%E7%9A%84adaptive%E5%8E%9F%E7%90%86)
  - [@adaptive注解在类和方法上的区别](#adaptive%E6%B3%A8%E8%A7%A3%E5%9C%A8%E7%B1%BB%E5%92%8C%E6%96%B9%E6%B3%95%E4%B8%8A%E7%9A%84%E5%8C%BA%E5%88%AB)
  - [loadFile()的目的](#loadfile%E7%9A%84%E7%9B%AE%E7%9A%84)
  - [总结](#%E6%80%BB%E7%BB%93)
- [5、Dubbo的动态编译](#5dubbo%E7%9A%84%E5%8A%A8%E6%80%81%E7%BC%96%E8%AF%91)
- [6、dubbo和spring完美融合](#6dubbo%E5%92%8Cspring%E5%AE%8C%E7%BE%8E%E8%9E%8D%E5%90%88)
  - [1. 设计配置属性和JavaBean](#1-%E8%AE%BE%E8%AE%A1%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%92%8Cjavabean)
  - [2. 编写XSD文件 全称就是 XML Schema  它就是校验XML，定义了一些列的语法来规范XML](#2-%E7%BC%96%E5%86%99xsd%E6%96%87%E4%BB%B6-%E5%85%A8%E7%A7%B0%E5%B0%B1%E6%98%AF-xml-schema--%E5%AE%83%E5%B0%B1%E6%98%AF%E6%A0%A1%E9%AA%8Cxml%E5%AE%9A%E4%B9%89%E4%BA%86%E4%B8%80%E4%BA%9B%E5%88%97%E7%9A%84%E8%AF%AD%E6%B3%95%E6%9D%A5%E8%A7%84%E8%8C%83xml)
  - [3. 编写NamespaceHandler和BeanDefinitionParser完成解析工作](#3-%E7%BC%96%E5%86%99namespacehandler%E5%92%8Cbeandefinitionparser%E5%AE%8C%E6%88%90%E8%A7%A3%E6%9E%90%E5%B7%A5%E4%BD%9C)
  - [4. 编写两个类spring.handlers和spring.schemas串联起所有部件](#4-%E7%BC%96%E5%86%99%E4%B8%A4%E4%B8%AA%E7%B1%BBspringhandlers%E5%92%8Cspringschemas%E4%B8%B2%E8%81%94%E8%B5%B7%E6%89%80%E6%9C%89%E9%83%A8%E4%BB%B6)
  - [5. 在Bean文件中应用](#5-%E5%9C%A8bean%E6%96%87%E4%BB%B6%E4%B8%AD%E5%BA%94%E7%94%A8)
- [7、服务发现设计原理](#7%E6%9C%8D%E5%8A%A1%E5%8F%91%E7%8E%B0%E8%AE%BE%E8%AE%A1%E5%8E%9F%E7%90%86)
  - [暴露本地服务和暴露远程服务的区别是什么？](#%E6%9A%B4%E9%9C%B2%E6%9C%AC%E5%9C%B0%E6%9C%8D%E5%8A%A1%E5%92%8C%E6%9A%B4%E9%9C%B2%E8%BF%9C%E7%A8%8B%E6%9C%8D%E5%8A%A1%E7%9A%84%E5%8C%BA%E5%88%AB%E6%98%AF%E4%BB%80%E4%B9%88)
  - [服务发布整体架构设计图](#%E6%9C%8D%E5%8A%A1%E5%8F%91%E5%B8%83%E6%95%B4%E4%BD%93%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%9B%BE)
  - [重要概念](#%E9%87%8D%E8%A6%81%E6%A6%82%E5%BF%B5)
- [8、服务引用设计原理](#8%E6%9C%8D%E5%8A%A1%E5%BC%95%E7%94%A8%E8%AE%BE%E8%AE%A1%E5%8E%9F%E7%90%86)
  - [服务引用整体架构设计图](#%E6%9C%8D%E5%8A%A1%E5%BC%95%E7%94%A8%E6%95%B4%E4%BD%93%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1%E5%9B%BE)
  - [directory目录](#directory%E7%9B%AE%E5%BD%95)
  - [router路由规则](#router%E8%B7%AF%E7%94%B1%E8%A7%84%E5%88%99)
  - [cluster集群](#cluster%E9%9B%86%E7%BE%A4)
  - [loadbalance负载均衡](#loadbalance%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1)
  - [dubbo实现SOA的服务降级](#dubbo%E5%AE%9E%E7%8E%B0soa%E7%9A%84%E6%9C%8D%E5%8A%A1%E9%99%8D%E7%BA%A7)
- [9、Dubbo把网络通信的IO异步变同步](#9dubbo%E6%8A%8A%E7%BD%91%E7%BB%9C%E9%80%9A%E4%BF%A1%E7%9A%84io%E5%BC%82%E6%AD%A5%E5%8F%98%E5%90%8C%E6%AD%A5)
  - [1. 异步，无返回值](#1-%E5%BC%82%E6%AD%A5%E6%97%A0%E8%BF%94%E5%9B%9E%E5%80%BC)
  - [2. 异步，有返回值](#2-%E5%BC%82%E6%AD%A5%E6%9C%89%E8%BF%94%E5%9B%9E%E5%80%BC)
  - [3. 异步，变同步（默认的通信方式）](#3-%E5%BC%82%E6%AD%A5%E5%8F%98%E5%90%8C%E6%AD%A5%E9%BB%98%E8%AE%A4%E7%9A%84%E9%80%9A%E4%BF%A1%E6%96%B9%E5%BC%8F)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->




## 1、Dubbo的架构原理

### Dubbo架构图
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-1.png)

### 节点角色说明

|节点|	角色说明|
| ------- | ---- |
|Provider|	暴露服务的服务提供方|
|Consumer	|调用远程服务的服务消费方|
|Registry |	服务注册与发现的注册中心|
| Monitor	|  统计服务的调用次数和调用时间的监控中心|
|Container	| 服务运行容器 |

### 调用关系说明

1.  provider启动时，会把所有接口注册到注册中心，并且订阅动态配置configurators
2.  consumer启动时，向注册中心订阅自己所需的providers，configurators，routers
3.  订阅内容变更时，registry将基于长连接推送变更数据给consumer，包括providers，configurators，routers
4.  consumer启动时，从provider地址列表中，基于软负载均衡算法，选一台provider进行调用，如果调用失败，再选另一台调用，建立长连接，然后进行数据通信（consumer->provider）
5.  consumer、provider启动后，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到monitor

### Dubbo架构具有连通性、健壮性、伸缩性、升级性四个特点

**连通性**

1.  注册中心：负责服务地址的注册与查找，相当于目录服务，服务提供者和消费者只在启动时与注册中心交互，注册中心不转发请求，压力较小
2.  监控中心：负责统计各服务调用次数，调用时间等，统计先在内存汇总后每分钟一次发送到监控中心服务器，并以报表展示
3.  服务提供者：向注册中心注册其提供的服务，并汇报调用时间到监控中心，此时间不包含网络开销
4. 服务消费者：向注册中心获取服务提供者地址列表，并根据负载算法直接调用提供者，同时汇报调用时间到监控中心，此时间包含网络开销
5. 注册中心，服务提供者，服务消费者三者之间均为：长连接，监控中心除外
6. 注册中心：通过长连接感知服务提供者的存在，服务提供者宕机，注册中心将立即推送事件通知消费者
7. 注册中心和监控中心全部宕机，不影响已运行的提供者和消费者，消费者在本地缓存了提供者列表
8. 注册中心和监控中心都是可选的，服务消费者可以直连服务提供者



**健壮性**

1. 监控中心宕掉不影响使用，只是丢失部分采样数据
2. 数据库宕掉后，注册中心仍能通过缓存提供服务列表查询，但不能注册新服务
3. 注册中心对等集群，任意一台宕掉后，将自动切换到另一台
4. 注册中心全部宕掉后，服务提供者和服务消费者仍能通过本地缓存通讯
5. 服务提供者无状态，任意一台宕掉后，不影响使用
6. 服务提供者全部宕掉后，服务消费者应用将无法使用，并无限次重连等待服务提供者恢复

**伸缩性**

1. 注册中心为对等集群，可动态增加机器部署实例，所有客户端将自动发现新的注册中心
2. 服务提供者无状态，可动态增加机器部署实例，注册中心将推送新的服务提供者信息给消费者

**升级性**

1. 当服务集群规模进一步扩大，带动治理结构进一步升级 
2. 需要实现动态部署,进行流量计算，现有分布式服务架构不会带来阻力










## 2、Dubbo自己的SPI实现

Dubbo内核包括四个：SPI（模仿JDK的SPI）、AOP（模仿Spring）、IOC（模仿Spring）、compiler（动态编译）



### SPI的设计目标

1. 面向对象的设计里，模块之间基于接口编程，模块之间不对实现类进行硬编码（硬编码：数据直接嵌入到程序）
2. 一旦代码涉及具体的实现类，就违反了可拔插的原则，如果需要替换一种实现，就需要修改代码
3. 为了实现在模块中装配的时候，不在模块里写死代码，这就需要一种服务发现机制
4. 为某个接口寻找服务实现的机制，有点类似IOC的思想，就是将装配的控制权转移到代码之外


### SPI的具体约定

1. 当服务的提供者（provide），提供了一个接口多种实现时，一般会在jar包的META_INF/services/目录下，创建该接口的同名文件，该文件里面的内容就是该服务接口的具体实现类的名称
2. 当外部加载这个模块的时候，就能通过jar包的META_INF/services/目录的配置文件得到具体的实现类名，并加载实例化，完成模块的装配


### dubbo为什么不直接使用JDK的SPI？

1. JDK标准的SPI会一次性实例化扩展点所有实现，如果有扩展实现初始化很耗时，但如果没用上也加载，会很浪费资源
2. dubbo的SPI增加了对扩展点IoC和AOP的支持，一个扩展点可以直接setter注入其他扩展点，JDK的SPI是没有的



### dubbo的SPI目的：获取一个实现类的对象
途径：ExtensionLoader.getExtension(String name)
实现路径：

1.  getExtensionLoader（Class< Type > type）就是为该接口new 一个ExtensionLoader，然后缓存起来。
2.  getAdaptiveExtension（） 获取一个扩展类，如果@Adaptive注解在类上就是一个装饰类；如果注解在方法上就是一个动态代理类，例如Protocol$Adaptive对象。
3.  getExtension（String name） 获取一个指定对象。


### ExtensionLoader
ExtensionLoader扩展点加载器，是扩展点的查找，校验，加载等核心逻辑的实现类，几乎所有特性都在这个类中实现

从ExtensionLoader.getExtensionLoader（Class< Type > type）讲起

```
-----------------------ExtensionLoader.getExtensionLoader(Class<T> type)
ExtensionLoader.getExtensionLoader(Container.class)
  -->this.type = type;
  -->objectFactory = (type == ExtensionFactory.class ? null : ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension());
     -->ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension()
       -->this.type = type;
       -->objectFactory =null;
```
执行以上代码完成了2个属性的初始化，是每个一个ExtensionLoader都包含了的2个值type和objectFactory

- Class< ? > type: 构造器 初始化要得到的接口名

- ExtensionFactory objectFactory ：

  1、构造器 初始化AdaptiveExtensionFactory[ SpiExtensionFactory, SpringExtensionFactory] 

  2、new一个ExtensionLoader存储在ConcurrentMap< Class< ? >, ExtensionLoader< ? > > EXTENSION_LOADERS

### 关于objectFactory的一些细节

1. objectFactory 就是ExtensionFactory ，也是通过ExtensionLoader.getExtensionLoader(ExtensionFactory.class)来实现，但objectFactory  = null;
2. objectFactory 的作用就是为dubbo的IOC提供所有对象












## 3、SPI 与 API的区别

API  （Application Programming Interface）

- 大多数情况下，都是实现方来制定接口并完成对接口的不同实现，调用方仅仅依赖却无权选择不同实现。


SPI (Service Provider Interface)

- 而如果是调用方来制定接口，实现方来针对接口来实现不同的实现。调用方来选择自己需要的实现方。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-2.png)

当我们选择在调用方和实现方中间引入接口。我们有三种选择：

1. 接口位于实现方所在的包中
2. 接口位于调用方所在的包中
3. 接口位于独立的包中


### 1. 接口位于【调用方】所在的包中
对于类似这种情况下接口，我们将其称为 SPI, SPI的规则如下：

- 概念上更依赖调用方。
- 组织上位于调用方所在的包中。
- 实现位于独立的包中。


常见的例子是：插件模式的插件。如：

- 数据库驱动 Driver
- 日志 Log
- dubbo扩展点开发

### 2. 接口位于【实现方】所在的包中
对于类似这种情况下的接口，我们将其称作为API，API的规则如下：

- 概念上更接近实现方。
- 组织上位于实现方所在的包中。

### 3. 接口位于独立的包中
如果一个“接口”在一个上下文是API，在另一个上下文是SPI，那么你就可以这么组织

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-3.png)












## 4、SPI机制的adaptive原理
先来看一下adaptive的源码
```
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})  //只能注解在类、接口、方法上面
public @interface Adaptive {

    String[] value() default {};

}
```

### @adaptive注解在类和方法上的区别

1. 注解在类上：代表人工实现编码，即实现了一个装饰类（设计模式中的装饰模式），例如：ExtensionFactory
2. 注解在方法上：代表自动生成和编译一个动态的adpative类，例如：Protocol$adpative

再来看生成Protocol的源码
```
private static final Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class)
            .getAdaptiveExtension();
```
进入getAdaptiveExtension()，进行代码跟踪与解析
```
-----------------------getAdaptiveExtension()
 -->getAdaptiveExtension()   //目的为  cachedAdaptiveInstance赋值
   -->createAdaptiveExtension()
     -->getAdaptiveExtension()
       -->getExtensionClasses()   //目的为cachedClasses赋值
         -->loadExtensionClasses()  //加载
           -->loadFile()  //加载配置信息（主要是META_INF/services/下）
      -->createAdaptiveExtensionClass()  //下面的调用有两个分支
         // *********** 分支1 *******************  在找到的类上有Adaptive注解
        ->getExtensionClasses()
        　　　　　　->loadExtensionClasses()
        　　　　　　　　->loadFile(extensionClasses, DUBBO_INTERNAL_DIRECTORY);
          // *********** 分支2 ******************* 在找到的类上没有Adaptive注解
        -->createAdaptiveExtensionClassCode()//通过adaptive模板生成代码
          -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.compiler.Compiler.class).getAdaptiveExtension();//编译
          -->compile(String code, ClassLoader classLoader) //自动生成和编译一个动态的adpative类，这个类是个代理类
       -->injectExtension()//作用：进入IOC的反转控制模式，实现了动态入注,这是 ExtensionFactory 类的作用之所在 
```
上述的调用中使用loadFile()加载配置信息，下面关于loadFile() 的一些细节：

### loadFile()的目的
把配置文件META-INF/dubbo/internal/com.alibaba.dubbo.rpc.Protocol的内容存储在缓存变量里面,下面四种缓存变量：

1. cachedAdaptiveClass //如果这个类有Adaptive注解就赋值，而Protocol在这个环节是没有的
2. cachedWrapperClasses //只有当该class无Adaptive注解，并且构造函数包含目标接口（type），例如protocol里面的spi就只有ProtocolFilterWrapper、ProtocolListenerWrapper能命中
3. cachedActivates //剩下的类包含adaptive注解
4. cachedNames //剩下的类就存储在这里



getExtension() 方法执行逻辑：
```
-----------------------getExtension(String name)
getExtension(String name) //指定对象缓存在cachedInstances；get出来的对象wrapper对象，例如protocol就是ProtocolFilterWrapper和ProtocolListenerWrapper其中一个。
  -->createExtension(String name)
    -->getExtensionClasses()
    -->injectExtension(T instance)//dubbo的IOC反转控制，就是从spi和spring里面提取对象赋值。
      -->objectFactory.getExtension(pt, property)
        -->SpiExtensionFactory.getExtension(type, name)
          -->ExtensionLoader.getExtensionLoader(type)
          -->loader.getAdaptiveExtension()
        -->SpringExtensionFactory.getExtension(type, name)
          -->context.getBean(name)
    -->injectExtension((T) wrapperClass.getConstructor(type).newInstance(instance))//AOP的简单设计
    
```

可以根据方法的调用得出dubbo的spi流程
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-4.png)



adaptive动态编译使用的模板
```
package <扩展点接口所在包>;
 
public class <扩展点接口名>$Adpative implements <扩展点接口> {
    public <有@Adaptive注解的接口方法>(<方法参数>) {
        if(是否有URL类型方法参数?) 使用该URL参数
        else if(是否有方法类型上有URL属性) 使用该URL属性
        # <else 在加载扩展点生成自适应扩展点类时抛异常，即加载扩展点失败！>
         
        if(获取的URL == null) {
            throw new IllegalArgumentException("url == null");
        }
 
              根据@Adaptive注解上声明的Key的顺序，从URL获致Value，作为实际扩展点名。
               如URL没有Value，则使用缺省扩展点实现。如没有扩展点， throw new IllegalStateException("Fail to get extension");
 
               在扩展点实现调用该方法，并返回结果。
    }
 
    public <有@Adaptive注解的接口方法>(<方法参数>) {
        throw new UnsupportedOperationException("is not adaptive method!");
    }
}
```
例如com.alibaba.dubbo.rpc.Protocol接口的动态编译根据模板生成的扩展类Protocol$Adpative为：
```
package com.alibaba.dubbo.rpc;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Protocol$Adpative implements Protocol {

    public void destroy() {
        throw new UnsupportedOperationException("method public abstract void com.alibaba.dubbo.rpc.Protocol.destroy() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
    }

    public int getDefaultPort() {
        throw new UnsupportedOperationException("method public abstract int com.alibaba.dubbo.rpc.Protocol.getDefaultPort() of interface com.alibaba.dubbo.rpc.Protocol is not adaptive method!");
    }

    public com.alibaba.dubbo.rpc.Exporter export(Invoker invoker) throws RpcException {
        if (invoker == null) throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
        if (invoker.getUrl() == null)
            throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
        com.alibaba.dubbo.common.URL url = invoker.getUrl();
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + url.toString() + ") use keys([protocol])");
        Protocol extension = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(extName);
        return extension.export(invoker);
    }

    public com.alibaba.dubbo.rpc.Invoker refer(java.lang.Class arg0, com.alibaba.dubbo.common.URL arg1) throws RpcException {
        if (arg1 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg1;
        String extName = (url.getProtocol() == null ? "dubbo" : url.getProtocol());
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + url.toString() + ") use keys([protocol])");
        com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol) ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);
        return extension.refer(arg0, arg1);
    }
}
```

### 总结

1、获取某个SPI接口的adaptive实现类的规则是：

- 实现类的类上面有Adaptive注解的，那么这个类就是adaptive类
- 实现类的类上面没有Adaptive注解，但是在方法上有Adaptive注解，则会动态生成adaptive类

2、生成的动态类的编译类是：com.alibaba.dubbo.common.compiler.support.AdaptiveCompiler类

3、动态类的本质是可以做到一个SPI中的不同的Adaptive方法可以去调不同的SPI实现类去处理。使得程序的灵活性大大提高,这才是整套SPI设计的一个精华之所在。













## 5、Dubbo的动态编译
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-5.png)

Compile接口定义：
```
@SPI("javassist")
public interface Compiler {

    Class<?> compile(String code, ClassLoader classLoader);

}
```
- @SPI(“javassist”)：表示如果没有配置，dubbo默认选用javassist编译源代码，Javassist是一款字节码编辑工具,同时也是一个动态类库，它可以直接检查、修改以及创建 Java类。
- 接口方法compile第一个入参code，就是java的源代码
- 接口方法compile第二个入参classLoader，按理是类加载器用来加载编译后的字节码，其实没用到，都是根据当前线程或者调用方的classLoader加载的


AdaptiveCompiler是Compiler的设配类，它的作用是Compiler策略的选择，根据条件选择使用何种编译策略来编译动态生成SPI扩展 ，默认为javassist.

AbstractCompiler是一个抽象类，它通过正则表达式获取到对象的包名以及Class名称。这样就可以获取对象的全类名(包名+Class名称)。通过反射Class.forName()来判断当前ClassLoader是否有这个类，如果有就返回，如果没有就通过JdkCompiler或者JavassistCompiler通过传入的code编译这个类。

```
      com.alibaba.dubbo.common.compiler.Compiler compiler = ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.common.compiler.Compiler.class).getAdaptiveExtension();
```
- getExtensionLoader()：new一个ExtensionLoader对象，用到单例模式、工厂模式，然后换成起来。
- getAdaptiveExtension()：为了获取扩展装饰类或代理类的对像，不过有个规则：如果@Adaptive注解在类上就是一个装饰类；如果注解在方法上就是一个动态代理类。

```java
public abstract class AbstractCompiler implements Compiler {

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("package\\s+([$_a-zA-Z][$_a-zA-Z0-9\\.]*);");

    private static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s+");

    public Class<?> compile(String code, ClassLoader classLoader) {
        code = code.trim();
        Matcher matcher = PACKAGE_PATTERN.matcher(code);//包名  
        String pkg;
        if (matcher.find()) {
            pkg = matcher.group(1);
        } else {
            pkg = "";
        }
        matcher = CLASS_PATTERN.matcher(code);//类名  
        String cls;
        if (matcher.find()) {
            cls = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + code);
        }
        String className = pkg != null && pkg.length() > 0 ? pkg + "." + cls : cls;
        try {
            return Class.forName(className, true, ClassHelper.getCallerClassLoader(getClass()));//根据类全路径来查找类  
        } catch (ClassNotFoundException e) {
            if (!code.endsWith("}")) {
                throw new IllegalStateException("The java code not endsWith \"}\", code: \n" + code + "\n");
            }
            try {
                return doCompile(className, code);//调用实现类JavassistCompiler或JdkCompiler的doCompile方法来动态编译类  
            } catch (RuntimeException t) {
                throw t;
            } catch (Throwable t) {
                throw new IllegalStateException("Failed to compile class, cause: " + t.getMessage() + ", class: " + className + ", code: \n" + code + "\n, stack: " + ClassUtils.toString(t));
            }
        }
    }

    protected abstract Class<?> doCompile(String name, String source) throws Throwable;

}

```
AbstractCompiler：在公用逻辑中，利用正则表达式匹配出源代码中的包名和类名： 

- PACKAGE_PATTERN = Pattern.compile("package\\s+([$_a-zA-Z][$_a-zA-Z0-9\\.]*);"); 

- CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s+"); 



然后在JVM中查找看看是否存在：Class.forName(className, true, ClassHelper.getCallerClassLoader(getClass()));存在返回，不存在就使用JavassistCompiler或者是JdkCompiler来执行编译。 









## 6、dubbo和spring完美融合
dubbo采取通过配置文件来启动container容器，dubbo是使用spring来做容器


dubbo实现通过下面的配置schema自定义配置
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-6.png)

**完成一个spring的自定义配置一般需要以下5个步骤：**

1. 设计配置属性和JavaBean
2. 编写XSD文件 全称就是 XML Schema  它就是校验XML，定义了一些列的语法来规范XML
3. 编写NamespaceHandler和BeanDefinitionParser完成解析工作
4. 编写两个类spring.handlers和spring.schemas串联起所有部件
5. 在Bean文件中应用


### 1. 设计配置属性和JavaBean
先配置属性dubbo.xml，可以看出一个service对象，有属性包括，interface、ref等


```
<!-- 和本地bean一样实现服务 -->
<dubbo:service interface="com.alibaba.dubbo.demo.DemoService" ref="demoService"/>
```

### 2. 编写XSD文件 全称就是 XML Schema  它就是校验XML，定义了一些列的语法来规范XML
下面是dubbo.xsd文件,作用是约束interface和ref属性，比如ref只能是string类，输入其他类型就提示报错
```
 <xsd:element name="service" type="serviceType">
        <xsd:annotation>
            <xsd:documentation><![CDATA[ Export service config ]]></xsd:documentation>
        </xsd:annotation>
    </xsd:element>

  <xsd:complexType name="serviceType">
        <xsd:complexContent>
            <xsd:extension base="abstractServiceType">
                <xsd:choice minOccurs="0" maxOccurs="unbounded">
                    <xsd:element ref="method" minOccurs="0" maxOccurs="unbounded"/>
                    <xsd:element ref="parameter" minOccurs="0" maxOccurs="unbounded"/>
                    <xsd:element ref="beans:property" minOccurs="0" maxOccurs="unbounded"/>
                </xsd:choice>
                <xsd:attribute name="interface" type="xsd:token" use="required">
                    <xsd:annotation>
                        <xsd:documentation>
                            <![CDATA[ Defines the interface to advertise for this service in the service registry. ]]></xsd:documentation>
                        <xsd:appinfo>
                            <tool:annotation>
                                <tool:expected-type type="java.lang.Class"/>
                            </tool:annotation>
                        </xsd:appinfo>
                    </xsd:annotation>
                </xsd:attribute>
                <xsd:attribute name="ref" type="xsd:string" use="optional">
                    <xsd:annotation>
                        <xsd:documentation>
                            <![CDATA[ The service implementation instance bean id. ]]></xsd:documentation>
                    </xsd:annotation>
                </xsd:attribute>
            </xsd:extension>
        </xsd:complexContent>
    </xsd:complexType>
```
### 3. 编写NamespaceHandler和BeanDefinitionParser完成解析工作
DubboNamespaceHandler这个类是个处理器，初始化时将各种bean注册到解析器上，将配置文件的值赋值到bean上
```
public class DubboNamespaceHandler extends NamespaceHandlerSupport {

    static {
        Version.checkDuplicate(DubboNamespaceHandler.class);
    }

    public void init() {
        registerBeanDefinitionParser("application", new DubboBeanDefinitionParser(ApplicationConfig.class, true));
        registerBeanDefinitionParser("module", new DubboBeanDefinitionParser(ModuleConfig.class, true));
        registerBeanDefinitionParser("registry", new DubboBeanDefinitionParser(RegistryConfig.class, true));
        registerBeanDefinitionParser("monitor", new DubboBeanDefinitionParser(MonitorConfig.class, true));
        registerBeanDefinitionParser("provider", new DubboBeanDefinitionParser(ProviderConfig.class, true));
        registerBeanDefinitionParser("consumer", new DubboBeanDefinitionParser(ConsumerConfig.class, true));
        registerBeanDefinitionParser("protocol", new DubboBeanDefinitionParser(ProtocolConfig.class, true));
        registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(ServiceBean.class, true));
        registerBeanDefinitionParser("reference", new DubboBeanDefinitionParser(ReferenceBean.class, false));
        registerBeanDefinitionParser("annotation", new DubboBeanDefinitionParser(AnnotationBean.class, true));
    }

}
```
解析器DubboBeanDefinitionParser，代码太长就不贴了，解析器大概作用：从配置文件中通过element.getAttribute(String name)拿到属性的值，赋给bean。
​                                      
​                              
那么问题来了，dubbo.xsd和DubboNamespaceHandler 、DubboBeanDefinitionParser 这两个类是关联起来呢？第四步来解决这个问题


### 4. 编写两个类spring.handlers和spring.schemas串联起所有部件
最后也是通过两个类spring.handlers和spring.schemas串联起所有部件
spring.handlers
```
http\://code.alibabatech.com/schema/dubbo=com.alibaba.dubbo.config.spring.schema.DubboNamespaceHandler
```
spring.schemas

```
http\://code.alibabatech.com/schema/dubbo/dubbo.xsd=META-INF/dubbo.xsd
```
### 5. 在Bean文件中应用











## 7、服务发现设计原理
启动服务提供者的时候通过打印出来的日志知道整个服务发现流程：

第一个发布的动作：暴露本地服务

```
	Export dubbo service com.alibaba.dubbo.demo.DemoService to local registry, dubbo version: 2.0.0, current host: 127.0.0.1
```


第二个发布动作：暴露远程服务

```
	Export dubbo service com.alibaba.dubbo.demo.DemoService to url dubbo://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&owner=william&pid=8484&side=provider&timestamp=1473908495465, dubbo version: 2.0.0, current host: 127.0.0.1
	Register dubbo service com.alibaba.dubbo.demo.DemoService url dubbo://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&monitor=dubbo%3A%2F%2F192.168.48.117%3A2181%2Fcom.alibaba.dubbo.registry.RegistryService%3Fapplication%3Ddemo-provider%26backup%3D192.168.48.120%3A2181%2C192.168.48.123%3A2181%26dubbo%3D2.0.0%26owner%3Dwilliam%26pid%3D8484%26protocol%3Dregistry%26refer%3Ddubbo%253D2.0.0%2526interface%253Dcom.alibaba.dubbo.monitor.MonitorService%2526pid%253D8484%2526timestamp%253D1473908495729%26registry%3Dzookeeper%26timestamp%3D1473908495398&owner=william&pid=8484&side=provider&timestamp=1473908495465 to registry registry://192.168.48.117:2181/com.alibaba.dubbo.registry.RegistryService?application=demo-provider&backup=192.168.48.120:2181,192.168.48.123:2181&dubbo=2.0.0&owner=william&pid=8484&registry=zookeeper&timestamp=1473908495398, dubbo version: 2.0.0, current host: 127.0.0.1
```

第三个发布动作：启动netty

```
	Start NettyServer bind /0.0.0.0:20880, export /192.168.100.38:20880, dubbo version: 2.0.0, current host: 127.0.0.1
```

第四个发布动作：打开连接zk

```
	INFO zookeeper.ClientCnxn: Opening socket connection to server /192.168.48.117:2181
```

第五个发布动作：到zk注册

```
	Register: dubbo://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&owner=william&pid=8484&side=provider&timestamp=1473908495465, dubbo version: 2.0.0, current host: 127.0.0.1
```

第六个发布动作；监听zk

```
	Subscribe: provider://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&category=configurators&check=false&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&owner=william&pid=8484&side=provider&timestamp=1473908495465, dubbo version: 2.0.0, current host: 127.0.0.1
	Notify urls for subscribe url provider://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&category=configurators&check=false&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&owner=william&pid=8484&side=provider&timestamp=1473908495465, urls: [empty://192.168.100.38:20880/com.alibaba.dubbo.demo.DemoService?anyhost=true&application=demo-provider&category=configurators&check=false&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.demo.DemoService&loadbalance=roundrobin&methods=sayHello&owner=william&pid=8484&side=provider&timestamp=1473908495465], dubbo version: 2.0.0, current host: 127.0.0.1
```



### 暴露本地服务和暴露远程服务的区别是什么？

1. 暴露本地服务：指暴露在用一个JVM里面，不用通过调用zk来进行远程通信。例如：在同一个服务，自己调用自己的接口，就没必要进行网络IP连接来通信。
2. 暴露远程服务：指暴露给远程客户端的IP和端口号，通过网络来实现通信。

```
ServiceBean.onApplicationEvent
-->export()
  -->ServiceConfig.export()
    -->doExport()
      -->doExportUrls()//里面有一个for循环，代表了一个服务可以有多个通信协议，例如 tcp协议 http协议，默认是tcp协议
        -->loadRegistries(true)//从dubbo.properties里面组装registry的url信息
        -->doExportUrlsFor1Protocol(ProtocolConfig protocolConfig, List<URL> registryURLs) 
          //配置不是remote的情况下做本地暴露 (配置为remote，则表示只暴露远程服务)
          -->exportLocal(URL url)
            -->proxyFactory.getInvoker(ref, (Class) interfaceClass, local)
              -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.ProxyFactory.class).getExtension("javassist");
              -->extension.getInvoker(arg0, arg1, arg2)
                -->StubProxyFactoryWrapper.getInvoker(T proxy, Class<T> type, URL url) 
                  -->proxyFactory.getInvoker(proxy, type, url)
                    -->JavassistProxyFactory.getInvoker(T proxy, Class<T> type, URL url)
                      -->Wrapper.getWrapper(com.alibaba.dubbo.demo.provider.DemoServiceImpl)
                        -->makeWrapper(Class<?> c)
                      -->return new AbstractProxyInvoker<T>(proxy, type, url)
            -->protocol.export
              -->Protocol$Adpative.export
                -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension("injvm");
                -->extension.export(arg0)
                  -->ProtocolFilterWrapper.export
                    -->buildInvokerChain //创建8个filter
                    -->ProtocolListenerWrapper.export
                      -->InjvmProtocol.export
                        -->return new InjvmExporter<T>(invoker, invoker.getUrl().getServiceKey(), exporterMap)
                        -->目的：exporterMap.put(key, this)//key=com.alibaba.dubbo.demo.DemoService, this=InjvmExporter
          //如果配置不是local则暴露为远程服务.(配置为local，则表示只暴露本地服务)
          -->proxyFactory.getInvoker//原理和本地暴露一样都是为了获取一个Invoker对象
          -->protocol.export(invoker)
            -->Protocol$Adpative.export
              -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension("registry");
	            -->extension.export(arg0)
	              -->ProtocolFilterWrapper.export
	                -->ProtocolListenerWrapper.export
	                  -->RegistryProtocol.export
	                    -->doLocalExport(originInvoker)
	                      -->getCacheKey(originInvoker);//读取 dubbo://192.168.100.51:20880/
	                      -->rotocol.export
	                        -->Protocol$Adpative.export
	                          -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension("dubbo");
	                          -->extension.export(arg0)
	                            -->ProtocolFilterWrapper.export
	                              -->buildInvokerChain//创建8个filter
	                              -->ProtocolListenerWrapper.export
---------1.netty服务暴露的开始-------    -->DubboProtocol.export
	                                  -->serviceKey(url)//组装key=com.alibaba.dubbo.demo.DemoService:20880
	                                  -->目的：exporterMap.put(key, this)//key=com.alibaba.dubbo.demo.DemoService:20880, this=DubboExporter
	                                  -->openServer(url)
	                                    -->createServer(url)
--------2.信息交换层 exchanger 开始-------------->Exchangers.bind(url, requestHandler)//exchaanger是一个信息交换层
	                                        -->getExchanger(url)
	                                          -->getExchanger(type)
	                                            -->ExtensionLoader.getExtensionLoader(Exchanger.class).getExtension("header")
	                                        -->HeaderExchanger.bind
	                                          -->Transporters.bind(url, new DecodeHandler(new HeaderExchangeHandler(handler)))
	                                            -->new HeaderExchangeHandler(handler)//this.handler = handler
	                                            -->new DecodeHandler
	                                            	-->new AbstractChannelHandlerDelegate//this.handler = handler;
---------3.网络传输层 transporter--------------------->Transporters.bind
	                                              -->getTransporter()
	                                                -->ExtensionLoader.getExtensionLoader(Transporter.class).getAdaptiveExtension()
	                                              -->Transporter$Adpative.bind
	                                                -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.remoting.Transporter.class).getExtension("netty");
	                                                -->extension.bind(arg0, arg1)
	                                                  -->NettyTransporter.bind
	                                                    --new NettyServer(url, listener)
	                                                      -->AbstractPeer //this.url = url;    this.handler = handler;
	                                                      -->AbstractEndpoint//codec  timeout=1000  connectTimeout=3000
	                                                      -->AbstractServer //bindAddress accepts=0 idleTimeout=600000
---------4.打开断开，暴露netty服务-------------------------------->doOpen()
	                                                        -->设置 NioServerSocketChannelFactory boss worker的线程池 线程个数为3
	                                                        -->设置编解码 hander
	                                                        -->bootstrap.bind(getBindAddress())
	                                            -->new HeaderExchangeServer
	                                              -->this.server=NettyServer
	                                              -->heartbeat=60000
	                                              -->heartbeatTimeout=180000
	                                              -->startHeatbeatTimer()//这是一个心跳定时器，采用了线程池，如果断开就心跳重连。

	                    -->getRegistry(originInvoker)//zk 连接
	                      -->registryFactory.getRegistry(registryUrl)
	                        -->ExtensionLoader.getExtensionLoader(RegistryFactory.class).getExtension("zookeeper");
	                        -->extension.getRegistry(arg0)
	                          -->AbstractRegistryFactory.getRegistry//创建一个注册中心，存储在REGISTRIES
	                            -->createRegistry(url)
	                              -->new ZookeeperRegistry(url, zookeeperTransporter)
	                                -->AbstractRegistry
	                                  -->loadProperties()//目的：把C:\Users\bobo\.dubbo\dubbo-registry-192.168.48.117.cache
	                                                                                                                                                                    文件中的内容加载为properties
	                                  -->notify(url.getBackupUrls())//不做任何事             
	                                -->FailbackRegistry   
	                                  -->retryExecutor.scheduleWithFixedDelay(new Runnable()//建立线程池，检测并连接注册中心,如果失败了就重连
	                                -->ZookeeperRegistry
	                                  -->zookeeperTransporter.connect(url)
	                                    -->ZookeeperTransporter$Adpative.connect(url)
	                                      -->ExtensionLoader.getExtensionLoader(ZookeeperTransporter.class).getExtension("zkclient");
	                                      -->extension.connect(arg0)
	                                        -->ZkclientZookeeperTransporter.connect
	                                          -->new ZkclientZookeeperClient(url)
	                                            -->AbstractZookeeperClient
	                                            -->ZkclientZookeeperClient
	                                              -->new ZkClient(url.getBackupAddress());//连接ZK
	                                              -->client.subscribeStateChanges(new IZkStateListener()//订阅的目标：连接断开，重连
	                                    -->zkClient.addStateListener(new StateListener() 
	                                      -->recover //连接失败 重连
	                                      
	                    -->registry.register(registedProviderUrl)//创建节点
	                      -->AbstractRegistry.register
	                      -->FailbackRegistry.register
	                        -->doRegister(url)//向zk服务器端发送注册请求
	                          -->ZookeeperRegistry.doRegister
	                            -->zkClient.create
	                              -->AbstractZookeeperClient.create//dubbo/com.alibaba.dubbo.demo.DemoService/providers/
										                              dubbo%3A%2F%2F192.168.100.52%3A20880%2Fcom.alibaba.dubbo.demo.DemoService%3Fanyhost%3Dtrue%26
										                              application%3Ddemo-provider%26dubbo%3D2.0.0%26generic%3Dfalse%26interface%3D
										                              com.alibaba.dubbo.demo.DemoService%26loadbalance%3Droundrobin%26methods%3DsayHello%26owner%3
										                              Dwilliam%26pid%3D2416%26side%3Dprovider%26timestamp%3D1474276306353
	                                -->createEphemeral(path);//临时节点  dubbo%3A%2F%2F192.168.100.52%3A20880%2F.............
	                                -->createPersistent(path);//持久化节点 dubbo/com.alibaba.dubbo.demo.DemoService/providers
	                                    
	                                    
	                    -->registry.subscribe//订阅ZK
	                      -->AbstractRegistry.subscribe
	                      -->FailbackRegistry.subscribe
	                        -->doSubscribe(url, listener)// 向服务器端发送订阅请求
	                          -->ZookeeperRegistry.doSubscribe
	                            -->new ChildListener()
	                              -->实现了 childChanged
	                                -->实现并执行 ZookeeperRegistry.this.notify(url, listener, toUrlsWithEmpty(url, parentPath, currentChilds));
	                              //A
	                            -->zkClient.create(path, false);//第一步：先创建持久化节点/dubbo/com.alibaba.dubbo.demo.DemoService/configurators
	                            -->zkClient.addChildListener(path, zkListener)
	                              -->AbstractZookeeperClient.addChildListener
	                                //C
	                                -->createTargetChildListener(path, listener)//第三步：收到订阅后的处理，交给FailbackRegistry.notify处理
	                                  -->ZkclientZookeeperClient.createTargetChildListener
	                                    -->new IZkChildListener() 
	                                      -->实现了 handleChildChange //收到订阅后的处理
	                                      	-->listener.childChanged(parentPath, currentChilds);
	                                      	-->实现并执行ZookeeperRegistry.this.notify(url, listener, toUrlsWithEmpty(url, parentPath, currentChilds));
	                                      	-->收到订阅后处理 FailbackRegistry.notify
	                                //B      	
	                                -->addTargetChildListener(path, targetListener)////第二步
	                                  -->ZkclientZookeeperClient.addTargetChildListener
	                                    -->client.subscribeChildChanges(path, listener)//第二步：启动加入订阅/dubbo/com.alibaba.dubbo.demo.DemoService/configurators
	                    
	                    -->notify(url, listener, urls)
	                      -->FailbackRegistry.notify
	                        -->doNotify(url, listener, urls);
	                          -->AbstractRegistry.notify
	                            -->saveProperties(url);//把服务端的注册url信息更新到C:\Users\bobo\.dubbo\dubbo-registry-192.168.48.117.cache
	                              -->registryCacheExecutor.execute(new SaveProperties(version));//采用线程池来处理
	                            -->listener.notify(categoryList)
	                              -->RegistryProtocol.notify
	                                -->RegistryProtocol.this.getProviderUrl(originInvoker)//通过invoker的url 获取 providerUrl的地址
```

### 服务发布整体架构设计图

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-7.png)
### 重要概念

1、proxyFactory：为了获取一个接口的代理类，例如获取一个远程接口的代理
它有2个方法，代表2个作用：

1. getInvoker（）：针对server端，将服务对象，如DemoServiceImpl包装成一个Invoker对象
2. getProxy（）：针对client端，创建接口的代理对象，例如DemoService的接口

2、Wrapper：它类似spring的beanWrapper，它就是包装了一个接口或一个类，可以通过wrapper对实例对象进行赋值以及制定方法的调用

3、Invoker：一个可执行的对象，能够根据方法的名称、参数得到相应的执行结果。
 它里面有一个很重要的方法Result invoke（Invocation invocation），Invocation是包含了需要执行的方法和参数等重要信息，目前只有两个实现类。RpcInvocation 、MockInvocation 
它有三种类型的Invoker：

 1. 本地执行的Invoker  
 2. 远程通信的Invoker  
 3. 多个远程通信执行类的Invoker聚合成集群版的Invoker

4、Protocol

1. export:暴露远程服务（用于服务端），就是将proxyFactory.getInvoker创建的代理类invoker对象，通过协议暴露给外部
2. refer：引用远程服务（用于客户端），通过proxyFactory.getProxy来创建远程的动态代理类，例如DemoDemoService的接口

5、exporter：维护invoder的生命周期

6、exchanger：信息交换层，封装请求相应模式，同步转异步

7、transporter：网络传输层，用来抽象netty和mina的统一接口
​    
​    
​    
​    
​    
​    

## 8、服务引用设计原理
下面是服务引用详细的代码跟踪与解析
```
ReferenceBean.getObject()
  -->ReferenceConfig.get()
    -->init()
      -->createProxy(map)
        -->refprotocol.refer(interfaceClass, urls.get(0))
          -->ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("registry");
          -->extension.refer(arg0, arg1);
            -->ProtocolFilterWrapper.refer
              -->RegistryProtocol.refer
                -->registryFactory.getRegistry(url)//建立zk的连接，和服务端发布一样（省略代码）
                -->doRefer(cluster, registry, type, url)
                  -->registry.register//创建zk的节点，和服务端发布一样（省略代码）。节点名为：dubbo/com.alibaba.dubbo.demo.DemoService/consumers
                  -->registry.subscribe//订阅zk的节点，和服务端发布一样（省略代码）。   /dubbo/com.alibaba.dubbo.demo.DemoService/providers, 
                                                                        /dubbo/com.alibaba.dubbo.demo.DemoService/configurators,
                                                                         /dubbo/com.alibaba.dubbo.demo.DemoService/routers]
                    -->notify(url, listener, urls);
                      -->FailbackRegistry.notify
                        -->doNotify(url, listener, urls);
                          -->AbstractRegistry.notify
                            -->saveProperties(url);//把服务端的注册url信息更新到C:\Users\bobo\.dubbo\dubbo-registry-192.168.48.117.cache
	                          -->registryCacheExecutor.execute(new SaveProperties(version));//采用线程池来处理
	                        -->listener.notify(categoryList)
	                          -->RegistryDirectory.notify
	                            -->refreshInvoker(invokerUrls)//刷新缓存中的invoker列表
	                              -->destroyUnusedInvokers(oldUrlInvokerMap,newUrlInvokerMap); // 关闭未使用的Invoker
	                              -->最终目的：刷新Map<String, Invoker<T>> urlInvokerMap 对象
	                                                                                                                       刷新Map<String, List<Invoker<T>>> methodInvokerMap对象
                  -->cluster.join(directory)//加入集群路由
                    -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.cluster.Cluster.class).getExtension("failover");
                      -->MockClusterWrapper.join
                        -->this.cluster.join(directory)
                          -->FailoverCluster.join
                            -->return new FailoverClusterInvoker<T>(directory)
                            -->new MockClusterInvoker
        -->proxyFactory.getProxy(invoker)//创建服务代理
          -->ProxyFactory$Adpative.getProxy
            -->ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.ProxyFactory.class).getExtension("javassist");
              -->StubProxyFactoryWrapper.getProxy
                -->proxyFactory.getProxy(invoker)
                  -->AbstractProxyFactory.getProxy
                    -->getProxy(invoker, interfaces)
                      -->Proxy.getProxy(interfaces)//目前代理对象interface com.alibaba.dubbo.demo.DemoService, interface com.alibaba.dubbo.rpc.service.EchoService
                      -->InvokerInvocationHandler// 采用jdk自带的InvocationHandler，创建InvokerInvocationHandler对象。
	                          
	                          
                    
```

###  服务引用整体架构设计图

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-8.png)


另外，在Dubbo集群容错部分，给出了服务引用的各功能组件关系图：

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-9.png)

### directory目录
通过目录来查找服务，它代表多个invoker，从methodInvokerMap提取，但是他的值是动态，例如注册中心的变更
```
public interface Directory<T> extends Node {

    Class<T> getInterface();

    List<Invoker<T>> list(Invocation invocation) throws RpcException;

}
```
Directory有两个实现类，一个静态Directory（不常用）、一个注册中心Directory

Directory目录服务

1. StaticDirectory：静态目录服务，他的Invoker是固定的
2. RegistryDirectory：注册目录服务，他的Invoker集合数据来源于zk注册中心，他实现了NotifyListener接口，并且实现回调函数 notify(List< URL > urls)，整个过程有一个重要的map变量，methodInvokerMap（它是数据的来源，同时也是notify的重要操作对象；重点是写操作），注册中心有变更就刷新map变量，通过doList（）来读，通过notify(）来写

### router路由规则

在 dubbo 中路由规则决定一次服务调用的目标服务器，分为条件路由规则和脚本路由规则，并且支持可扩展(SPI)。
```
public interface Router extends Comparable<Router> {

    URL getUrl();
    
    <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException;
    
}
```
调用 route 方法，传入从目录服务获取到的 Invoke 列表，通过 URL 或者 Invocation 里面配置的条件（路由规则）筛选出满足条件的 Invoke 列表。例如应用隔离或读写分离或灰度发布等等


1、什么时候加入ConditionRouter？

要修改后台管理或注册中心改变的时候就加入ConditionRouter

2、路由规则有哪些实现类？
1. MockInvokersSelector：默认
2. ConditionRouter：条件路由，后台管理的路由配置都是条件路由，默认是MockInvokersSelector，只要修改后台管理或注册中心改变的时候就加入ConditionRouter
3. ScriptRouter：脚本路由

下面是 dubbo 路由服务的类图：
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-11.png)


dubbo 默认会在 AbstractDirectory#setRouters 自动添加 MockInvokersSelector 路由规则。

**MockInvokersSelector**
MockInvokersSelector：其实就是用于路由 Mock 服务与非 Mock 服务。

```
public <T> List<Invoker<T>> route(final List<Invoker<T>> invokers,
                                      URL url, final Invocation invocation) throws RpcException {
        if (invocation.getAttachments() == null) {
            return getNormalInvokers(invokers);
        } else {
            String value = invocation.getAttachments().get(Constants.INVOCATION_NEED_MOCK);
            if (value == null)
                return getNormalInvokers(invokers);
            else if (Boolean.TRUE.toString().equalsIgnoreCase(value)) {
                return getMockedInvokers(invokers);
            }
        }
        return invokers;
    }
```
上面的代码逻辑其实就是：

- 如果 Invocation 的扩展参数不为空 并且 Invocation 的扩展参数里面包含 invocation.need.mock 参数并且值为 true 就获取 Invoke 列表里面 protocol 为 mock 的 Invoke 列表。
- 否则获取Invoke 列表里面 protocol 为非 mock 的 Invoke 列表。

**ConditionRouter**
ConditionRouter：基于条件表达式的路由规则，它的条件规则如下：

- => 之前的为消费者匹配条件，所有参数和消费者的 URL 进行对比，当消费者满足匹配条件时，对该消费者执行后面的过滤规则。
- => 之后为提供者地址列表的过滤条件，所有参数和提供者的 URL 进行对比，消费者最终只拿到过滤后的地址列表。
- 如果匹配条件为空，表示对所有消费方应用，如：=> host != 10.20.153.11
- 如果过滤条件为空，表示禁止访问，如：host = 10.20.153.10 =>

参数支持：

- 服务调用信息，如：method, argument 等，暂不支持参数路由
- URL 本身的字段，如：protocol, host, port 等
- 以及 URL 上的所有参数，如：application, organization 等

条件支持：

- 等号 = 表示”匹配”，如：host = 10.20.153.10
- 不等号 != 表示”不匹配”，如：host != 10.20.153.10

值支持：

- 以逗号 , 分隔多个值，如：host != 10.20.153.10,10.20.153.11
- 以星号 * 结尾，表示通配，如：host != 10.20.*
- 以美元符 $ 开头，表示引用消费者参数，如：host = $host

**ScriptRouter**
ScriptRouter：脚本路由规则，脚本路由规则支持 JDK 脚本引擎的所有脚本，比如：javascript, jruby, groovy 等，通过 type=javascript 参数设置脚本类型，缺省为 javascript。

基于脚本引擎的路由规则，如：

```
（function route(invokers) {
    var result = new java.util.ArrayList(invokers.size());
    for (i = 0; i < invokers.size(); i ++) {
        if ("10.20.153.10".equals(invokers.get(i).getUrl().getHost())) {
            result.add(invokers.get(i));
        }
    }
    return result;
} (invokers)）; // 表示立即执行方法
```
**Route功能**
通过配置不同的 Route 规则，我们可以实现以下功能。

排除预发布机：

     => host != 172.22.3.91

白名单：

     host != 10.20.153.10,10.20.153.11 =>

黑名单：

     host = 10.20.153.10,10.20.153.11 =>

服务寄宿在应用上，只暴露一部分的机器，防止整个集群挂掉：

     => host = 172.22.3.1*,172.22.3.2*

为重要应用提供额外的机器：

     application != kylin => host != 172.22.3.95,172.22.3.96

读写分离：

     method = find*,list*,get*,is* => host = 172.22.3.94,172.22.3.95,172.22.3.96
     method != find*,list*,get*,is* => host = 172.22.3.97,172.22.3.98

前后台分离：

     application = bops => host = 172.22.3.91,172.22.3.92,172.22.3.93
     application != bops => host = 172.22.3.94,172.22.3.95,172.22.3.96

隔离不同机房网段：

     host != 172.22.3.* => host != 172.22.3.*

提供者与消费者部署在同集群内，本机只访问本机的服务：

     => host = $host


Router在应用隔离,读写分离,灰度发布中都发挥作用。
>灰度发布是指在黑与白之间，能够平滑过渡的一种发布方式。AB test就是一种灰度发布方式，让一部分用户继续用A，一部分用户开始用B，如果用户对B没有什么反对意见，那么逐步扩大范围，把所有用户都迁移到B上面来。灰度发布可以保证整体系统的稳定，在初始灰度的时候就可以发现、调整问题，以保证其影响度。


过程：

1. 首先在192.168.22.58和192.168.22.59两台机器上启动Provider,然后启动Consumer
2. 假设我们要升级192.168.22.58服务器上的服务,接着我们去dubbo的控制台配置路由,切断192.168.22.58的流量,配置完成并且启动之后,就看到此时只调用192.168.22.59的服务
3. 假设此时你在192.168.22.58服务器升级服务,升级完成后再次将启动服务.
4. 由于服务已经升级完成,那么我们此时我们要把刚才的禁用路由取消点,那就是去zookeeper上删除节点，然后刷新控制台的界面
5. 那么此时我们再看控制台的输出,已经恢复正常,整个灰度发布流程结束



### cluster集群
Cluster将Directory中的多个Invoker伪装成一个Invoker来容错，调用失败重试。

```
@SPI(FailoverCluster.NAME)//失败转移，当失败的时候重试其他服务器
public interface Cluster {

    @Adaptive
    <T> Invoker<T> join(Directory<T> directory) throws RpcException;

}
```
Cluster有八个实现类，也就是有八个集群算法

1. FailoverCluster：（默认）失败转移，当出现失败，重试其它服务器，通常用于读操作，但重试会带来更长延迟。 
2. FailfastCluster：快速失败，只发起一次调用，失败立即报错，通常用于非幂等性的写操作。
3. FailbackCluster：失败自动恢复，后台记录失败请求，定时重发，通常用于消息通知操作。
4. FailsafeCluster：失败安全，出现异常时，直接忽略，通常用于写入审计日志等操作。
5. ForkingCluster： 并行调用，只要一个成功即返回，通常用于实时性要求较高的操作，但需要浪费更多服务资源。
6. BroadcastCluster: 广播调用。遍历所有Invokers, 逐个调用每个调用catch住异常不影响其他invoker调用
7. MergeableCluster: 分组聚合， 按组合并返回结果，比如菜单服务，接口一样，但有多种实现，用group区分，现在消费方需从每种group中调用一次返回结果，合并结果返回，这样就可以实现聚合菜单项。
8. AvailableCluster: 获取可用的调用。遍历所有Invokers判断Invoker.isAvalible,只要一个有为true直接调用返回，不管成不成功

**失败转移源码**
失败转移和快速失败的区别，是失败转移出现异常会存储异常，而快速失败出现异常会直接抛出去
```
public class FailoverClusterInvoker<T> extends AbstractClusterInvoker<T> {

    private static final Logger logger = LoggerFactory.getLogger(FailoverClusterInvoker.class);

    public FailoverClusterInvoker(Directory<T> directory) {
        super(directory);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Result doInvoke(Invocation invocation, final List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
        List<Invoker<T>> copyinvokers = invokers;
        checkInvokers(copyinvokers, invocation);
        String methodName = RpcUtils.getMethodName(invocation);
        //获取重试的次数，默认是3
        int len = getUrl().getMethodParameter(methodName, Constants.RETRIES_KEY, Constants.DEFAULT_RETRIES) + 1;
        if (len <= 0) {
            len = 1;
        }
        // retry loop.
        RpcException le = null; // last exception.
        List<Invoker<T>> invoked = new ArrayList<Invoker<T>>(copyinvokers.size()); // invoked invokers.
        Set<String> providers = new HashSet<String>(len);
        for (int i = 0; i < len; i++) {
            //重试时，进行重新选择，避免重试时invoker列表已发生变化
            //注意：如果列表发生变化，那么invoker判断会失效，因为invoker示例已经改变
            if (i > 0) {
                checkWhetherDestroyed();
                copyinvokers = list(invocation);
                // check again
                checkInvokers(copyinvokers, invocation);
            }
            //从负载均衡获取一个invoker
            Invoker<T> invoker = select(loadbalance, invocation, copyinvokers, invoked);
            invoked.add(invoker);
            RpcContext.getContext().setInvokers((List) invoked);
            try {
                Result result = invoker.invoke(invocation);
                if (le != null && logger.isWarnEnabled()) {
                    logger.warn("Although retry the method " + methodName
                            + " in the service " + getInterface().getName()
                            + " was successful by the provider " + invoker.getUrl().getAddress()
                            + ", but there have been failed providers " + providers
                            + " (" + providers.size() + "/" + copyinvokers.size()
                            + ") from the registry " + directory.getUrl().getAddress()
                            + " on the consumer " + NetUtils.getLocalHost()
                            + " using the dubbo version " + Version.getVersion() + ". Last error is: "
                            + le.getMessage(), le);
                }
                return result;
            } catch (RpcException e) {
                if (e.isBiz()) { // biz exception.
                    throw e;
                }
                le = e;
            } catch (Throwable e) {
                le = new RpcException(e.getMessage(), e);
            } finally {
                providers.add(invoker.getUrl().getAddress());
            }
        }
        throw new RpcException(le.getCode(), "Failed to invoke the method "
                + methodName + " in the service " + getInterface().getName()
                + ". Tried " + len + " times of the providers " + providers
                + " (" + providers.size() + "/" + copyinvokers.size()
                + ") from the registry " + directory.getUrl().getAddress()
                + " on the consumer " + NetUtils.getLocalHost() + " using the dubbo version "
                + Version.getVersion() + ". Last error is: "
                + le.getMessage(), le.getCause() != null ? le.getCause() : le);
    }

}
```

**快速失败源码**
快速失败，只发起一次调用，失败立即报错，通常用于非幂等性的写操作

```
public class FailfastClusterInvoker<T> extends AbstractClusterInvoker<T> {

    public FailfastClusterInvoker(Directory<T> directory) {
        super(directory);
    }

    @Override
    public Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
        checkInvokers(invokers, invocation);
        Invoker<T> invoker = select(loadbalance, invocation, invokers, null);
        try {
            return invoker.invoke(invocation);
        } catch (Throwable e) {
            if (e instanceof RpcException && ((RpcException) e).isBiz()) { // biz exception.
                throw (RpcException) e;
            }
            //失败转移和快速失败的区别，是失败转移出现异常会存储异常，而快速失败出现异常会直接抛出去
            throw new RpcException(e instanceof RpcException ? ((RpcException) e).getCode() : 0,
                    "Failfast invoke providers " + invoker.getUrl() + " " + loadbalance.getClass().getSimpleName()
                            + " select from all providers " + invokers + " for service " + getInterface().getName()
                            + " method " + invocation.getMethodName() + " on consumer " + NetUtils.getLocalHost()
                            + " use dubbo version " + Version.getVersion()
                            + ", but no luck to perform the invocation. Last error is: " + e.getMessage(),
                    e.getCause() != null ? e.getCause() : e);
        }
    }
}
```

### loadbalance负载均衡
loadbalance负载均衡：从多个Invoker选取一个做本次调用，具体包含很多负载均衡算法

```
@SPI(RandomLoadBalance.NAME)//默认是随机
public interface LoadBalance {

    @Adaptive("loadbalance")//动态编译
    <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException;

}
```
loadbalance负载均衡有四个实现类

1. RandomLoadBalance：随机，按权重设置随机概率。在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。 
2. RoundRobin LoadBalance：轮循，按公约后的权重设置轮循比率。存在慢的提供者累积请求问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。
3. LeastActiveLoadBalance：最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
4. ConsistentHash LoadBalance：一致性Hash，相同参数的请求总是发到同一提供者。当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。

### dubbo实现SOA的服务降级

什么是服务开关？
- 先讲一下开关的由来，例如淘宝在11月11日做促销活动，在交易下单环节，可能需要调用A、B、C三个接口来完成，但是其实A和B是必须的，  C只是附加的功能（例如在下单的时候做一下推荐，或push消息），可有可无，在平时系统没有压力，容量充足的情况下，调用下没问题，但是在类似店庆之类的大促环节， 系统已经满负荷了，这时候其实完全可以不去调用C接口，怎么实现这个呢？  改代码？

什么是服务降级
- 服务降级，当服务器压力剧增的情况下，根据当前业务情况及流量对一些服务和页面有策略的降级，以此释放服务器资源以保证核心任务的正常运行。

dubbo如何实现服务降级？

1、容错：当系统出现非业务异常(比如并发数太高导致超时，网络异常等)时，不对该接口进行处理。（不可知）

```
mock=fail:return null
```

2、 屏蔽：在大促，促销活动的可预知情况下，例如双11活动。采用直接屏蔽接口访问。（可知）

```
mock=force:return null
```



## 9、Dubbo把网络通信的IO异步变同步
先讲一下单工、全双工 、半双工 区别

1. 单工：在同一时间只允许一方向另一方传送信息，而另一方不能向一方传送 
2. 全双工：是指在发送数据的同时也能够接收数据，两者同步进行，这好像我们平时打电话一样，说话的同时也能够听到对方的声音。目前的网卡一般都支持全双工。 
3. 半双工：所谓半双工就是指一个时间段内只有一个动作发生，举个简单例子，一条窄窄的马路，同时只能有一辆车通过，当目前有两量车对开，这种情况下就只能一辆先过，等到头后另一辆再开，这个例子就形象的说明了半双工的原理。



dubbo 是基于netty NIO的非阻塞 并行调用通信。 （阻塞  非阻塞  异步  同步 区别 ）
dubbo从头到脚都是异步的
dubbo 的通信方式 有3类类型：

### 1. 异步，无返回值
这种请求最简单，consumer 把请求信息发送给 provider 就行了。只是需要在 consumer 端把请求方式配置成异步请求就好了。如下：
```
<dubbo:method name="sayHello" return="false"></dubbo:method>
```

### 2. 异步，有返回值

这种情况下consumer首先把请求信息发送给provider，这个时候在consumer端不仅把请求方式配置成异步，并且需要RpcContext这个ThreadLocal对象获取到Future对象，然后通过Future#get( )阻塞式获取provider的相应，那么这个Future是如何添加到RpcContext中呢？

在第二小节讲服务发送的时候， 在 DubboInvoke 里面有三种调用方式，之前只具体请求了同步请求的发送方式而且没有异步请求的发送。异步请求发送代码如下：

> DubboInvoker#doInvoke 中的 else if (isAsync) 分支

```
    ResponseFuture future = currentClient.request(inv, timeout);
    FutureAdapter<T> futureAdapter = new FutureAdapter<>(future);
    RpcContext.getContext().setFuture(futureAdapter);
    Result result;
    if (RpcUtils.isAsyncFuture(getUrl(), inv)) {
        result = new AsyncRpcResult<>(futureAdapter);
    } else {
        result = new RpcResult();
    }
    return result;

```
上面的代码逻辑是直接发送请求到 provider 返回一个 ResponseFuture 实例，然后把这个 Future 对象保存到 RpcContext#LOCAL 这个 ThreadLocal 当前线程对象当中，并且返回一个空的 RpcResult对象。如果要获取到 provider响应的信息，需要进行以下操作：

```
// 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
Future<String> temp= RpcContext.getContext().getFuture();
// 同理等待bar返回
hello=temp.get();

```
配置为异步，有返回值
```
<dubbo:method name="sayHello" async="true"></dubbo:method>
```



### 3. 异步，变同步（默认的通信方式）

异步变同步其实原理和异步请求的通过 Future#get 等待 provider 响应返回一样，只不过异步有返回值是显示调用而默认是 dubbo 内部把这步完成了。

  A. 当前线程怎么让它 “暂停，等结果回来后，再执行”？
  B. socket是一个全双工的通信方式，那么在多线程的情况下，如何知道那个返回结果对应原先那条线程的调用？
​    	
  通过一个全局唯一的ID来做consumer 和 provider 来回传输。

  我们都知道在 consumer 发送请求的时候会调用 HeaderExchangeChannel#request 方法：
> HeaderExchangeChannel#request
```
    public ResponseFuture request(Object request, int timeout) throws RemotingException {
        if (closed) {
            throw new RemotingException(this.getLocalAddress(), null, "Failed to send request " + request + ", cause: The channel " + this + " is closed!");
        }
        // create request.
        Request req = new Request();
        req.setVersion(Version.getProtocolVersion());
        req.setTwoWay(true);
        req.setData(request);
        DefaultFuture future = new DefaultFuture(channel, req, timeout);
        try {
            channel.send(req);
        } catch (RemotingException e) {
            future.cancel();
            throw e;
        }
        return future;
    }

```
它首先会通过 dubbo 自定义的 Channel、Request 与 timeout(int) 构造一个 DefaultFuture 对象。然后再通过 NettyChannel 发送请求到 provider，最后返回这个 DefaultFuture。下面我们来看一下通过构造方法是如何创建 DefaultFuture 的。我只把主要涉及到的属性展示出来：

```
public class DefaultFuture implements ResponseFuture {
 
    private static final Map<Long, Channel> CHANNELS = new ConcurrentHashMap<Long, Channel>();
 
    private static final Map<Long, DefaultFuture> FUTURES = new ConcurrentHashMap<Long, DefaultFuture>();
 
    private final long id;
    private final Channel channel;
    private final Request request;
    private final int timeout;
 
    public DefaultFuture(Channel channel, Request request, int timeout) {
        this.channel = channel;
        this.request = request;
        this.id = request.getId();
        this.timeout = timeout > 0 ? timeout : channel.getUrl().getPositiveParameter(Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
        // put into waiting map.
        FUTURES.put(id, this);
        CHANNELS.put(id, channel);
    }
}

```
这个 id 是在创建 Request 的时候使用 AtomicLong#getAndIncrement 生成的。从 1 开始并且如果它一直增加直到生成负数也能保证这台机器这个值是唯一的，且不冲突的。符合唯一主键原则。 dubbo 默认同步变异步其实和异步调用一样，也是在 DubboInvoker#doInvoke 实现的。

> DubboInvoker#doInvoke
```
    RpcContext.getContext().setFuture(null);
    return (Result) currentClient.request(inv, timeout).get();
```
关键就在 ResponseFuture#get 方法上面，下面我们来看一下这个方法的源码：

```
    public Object get(int timeout) throws RemotingException {
        if (timeout <= 0) {
            timeout = Constants.DEFAULT_TIMEOUT;
        }
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (!isDone()) {
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            if (!isDone()) {
                throw new TimeoutException(sent > 0, channel, getTimeoutMessage(false));
            }
        }
        return returnFromResponse();
    }

```
其实就是 while 循环，利用 java 的 lock 机制判断如果在超时时间范围内 DefaultFuture#response 如果赋值成不为空就返回响应，否则抛出 TimeoutException 异常。

还记得 consumer 接收 provider 响应的最后一步吗？就是 DefaultFuture#received，在 provider 端会带回 consumer请求的 id。我们来看一下它的具体处理逻辑：

```
    public static void received(Channel channel, Response response) {
        try {
            DefaultFuture future = FUTURES.remove(response.getId());
            if (future != null) {
                future.doReceived(response);
            } else {
                logger.warn("The timeout response finally returned at "
                        + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()))
                        + ", response " + response
                        + (channel == null ? "" : ", channel: " + channel.getLocalAddress()
                        + " -> " + channel.getRemoteAddress()));
            }
        } finally {
            CHANNELS.remove(response.getId());
        }
    }

```
它会从最开始通过构造函数传进去的 DefaultFuture#FUTURES 根据请求的 id 拿到 DefaultFuture ，然后根据这个 DefaultFuture 调用 DefaultFuture#doReceived 方法。通过 Java 里面的 lock 机制把 provider 的值赋值给 DefaultFuture#response。此时 consumer 也正在调用 DefaultFuture#get 方法进行阻塞，当这个 DefaultFuture#response 被赋值后，它的值就不为空。阻塞操作完成，且根据请求号的 id 把 consumer 端的 Request以及 Provider 端返回的 Response 关联了起来。


## 10、Dubbo网络通信的编解码

### 什么是编码、解码？

1. 编码（Encode）称为序列化（serialization），它将对象序列化为字节数组，用于网络传输、数据持久化或者其它用途。
2. 解码（Decode）反序列化（deserialization）把从网络、磁盘等读取的字节数组还原成原始对象（通常是原始对象的拷贝），以方便后续的业务逻辑操作。


![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-12.png)

### tcp 为什么会出现粘包 拆包的问题？

1.  应用程序写入数据的字节大小大于套接字发送缓冲区的大小
2. 可能是IP分片传输导致的，也可能是传输过程中丢失部分包导致出现的半包，还有可能就是一个包可能被分成了两次传输，在取数据的时候，先取到了一部分（还可能与接收的缓冲区大小有关系），总之就是一个数据包被分成了多次接收。

### tcp 怎么解决粘包 拆包的问题？

1. 消息的定长，例如定1000个字节
2. 就是在包尾增加回车或空格等特殊字符作为切割，典型的FTP协议
3. 将消息分为消息头消息体。例如 dubbo

下面我们来看一下 dubbo 的协议头约定：
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/dubbo-13.png)
dubbo 使用长度为 16 的 byte 数组作为协议头。1 个 byte 对应 8 位。所以 dubbo 的协议头有 128 位 (也就是上图的从 0 到 127)。我们来看一下这 128 位协议头分别代表什么意思。

- 0 ~ 7 ： dubbo 魔数((short) 0xdabb) 高位，也就是 (short) 0xda。
- 8 ~ 15： dubbo 魔数((short) 0xdabb) 低位，也就是 (short) 0xbb。
- 16 ~ 20：序列化 id(Serialization id)，也就是 dubbo 支持的序列化中的 contentTypeId，比如 Hessian2Serialization#ID 为 2
- 21 ：是否事件(event )
- 22 ： 是否 Two way 模式(Two way)。默认是 Two-way 模式，<dubbo:method> 标签的 return 属性配置为false，则是oneway模式
- 23 ：标记是请求对象还是响应对象(Req/res)
- 24 ~ 31：response 的结果响应码 ，例如 OK=20
- 32 ~ 95：id(long)，异步变同步的全局唯一ID，用来做consumer和provider的来回通信标记。
- 96 ~ 127： data length，请求或响应数据体的数据长度也就是消息头+请求数据的长度。用于处理 dubbo 通信的粘包与拆包问题。


### 1. consumer请求编码
consumer 在请求 provider 的时候需要把 Request 对象转化成 byte 数组，所以它是一个需要编码的过程。
```
----------1------consumer请求编码----------------------
-->NettyCodecAdapter.InternalEncoder.encode
  -->DubboCountCodec.encode
    -->ExchangeCodec.encode
      -->ExchangeCodec.encodeRequest
        -->DubboCodec.encodeRequestData
```
### 2. provider 请求解码
provider 在接收 consumer 请求的时候需要把 byte 数组转化成 Request 对象，所以它是一个需要解码的过程。
```
----------2------provider 请求解码----------------------
--NettyCodecAdapter.InternalDecoder.messageReceived
  -->DubboCountCodec.decode
    -->ExchangeCodec.decode
      -->ExchangeCodec.decodeBody
```

### 3. provider响应结果编码
provider 在处理完成 consumer 请求需要响应结果的时候需要把 Response 对象转化成 byte 数组，所以它是一个需要编码的过程。
```
----------3------provider响应结果编码----------------------
-->NettyCodecAdapter.InternalEncoder.encode
  -->DubboCountCodec.encode
    -->ExchangeCodec.encode
      -->ExchangeCodec.encodeResponse
        -->DubboCodec.encodeResponseData//先写入一个字节 这个字节可能是RESPONSE_NULL_VALUE  RESPONSE_VALUE  RESPONSE_WITH_EXCEPTION
```

### 4. consumer响应结果解码
consumer 在接收 provider 响应的时候需要把 byte 数组转化成 Response 对象，所以它是一个需要解码的过程。
```
----------4------consumer响应结果解码----------------------
--NettyCodecAdapter.InternalDecoder.messageReceived
  -->DubboCountCodec.decode
    -->ExchangeCodec.decode
      -->DubboCodec.decodeBody
        -->DecodeableRpcResult.decode//根据RESPONSE_NULL_VALUE  RESPONSE_VALUE  RESPONSE_WITH_EXCEPTION进行响应的处理
```



