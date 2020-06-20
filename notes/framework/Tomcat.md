<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Tomcat是什么？](#tomcat%E6%98%AF%E4%BB%80%E4%B9%88)
- [Tomcat 有哪几种Connector 运行模式(优化)？](#tomcat-%E6%9C%89%E5%93%AA%E5%87%A0%E7%A7%8Dconnector-%E8%BF%90%E8%A1%8C%E6%A8%A1%E5%BC%8F%E4%BC%98%E5%8C%96)
  - [BIO：同步并阻塞](#bio%E5%90%8C%E6%AD%A5%E5%B9%B6%E9%98%BB%E5%A1%9E)
  - [NIO：同步非阻塞IO](#nio%E5%90%8C%E6%AD%A5%E9%9D%9E%E9%98%BB%E5%A1%9Eio)
  - [APR：即Apache Portable Runtime](#apr%E5%8D%B3apache-portable-runtime)
- [Tomcat顶层架构](#tomcat%E9%A1%B6%E5%B1%82%E6%9E%B6%E6%9E%84)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## Tomcat是什么？
Tomcat 服务器Apache软件基金会项目中的一个核心项目，是一个免费的开放源代码的Web 应用服务器，属于轻量级应用服务器，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。
## Tomcat 有哪几种Connector 运行模式(优化)？
### BIO：同步并阻塞 
一个线程处理一个请求。缺点：并发量高时，线程数较多，浪费资源。Tomcat7或以下，在Linux系统中默认使用这种方式。
### NIO：同步非阻塞IO
利用Java的异步IO处理，可以通过少量的线程处理大量的请求，可以复用同一个线程处理多个connection(多路复用)。Tomcat8在Linux系统中默认使用这种方式。
### APR：即Apache Portable Runtime
从操作系统层面解决io阻塞问题。

Tomcat有几种部署方式？
Tomcat容器是如何创建servlet类实例？用到了什么原理？
1. 当容器启动时，会读取在webapps目录下所有的web应用中的web.xml文件，然后对 xml文件进行解析，并读取servlet注册信息。然后，将每个应用中注册的servlet类都进行加载，并通过 反射的方式实例化。（有时候也是在第一次请求时实例化）
2. 在servlet注册时加上1如果为正数，则在一开始就实例化，如果不写或为负数，则第一次请求实例化。


## Tomcat顶层架构
Tomcat中最顶层的容器是Server，代表着整个服务器，从上图中可以看出，一个Server可以包含至少一个Service，即可以包含多个Service，用于具体提供服务。

Service主要包含两个部分：Connector和Container。从上图中可以看出 Tomcat 的心脏就是这两个组件，他们的作用如下：
- Connector用于处理连接相关的事情，并提供Socket与Request请求和Response响应相关的转化;
- Container用于封装和管理Servlet，以及具体处理Request请求；

一个Tomcat中只有一个Server，一个Server可以包含多个Service，一个Service只有一个Container，但是可以有多个Connectors，这是因为一个服务可以有多个连接，如同时提供Http和Https链接，也可以提供向相同协议不同端口的连接

多个 Connector 和一个 Container 就形成了一个 Service，有了 Service 就可以对外提供服务了，但是 Service 还要一个生存的环境，必须要有人能够给她生命、掌握其生死大权，那就非 Server 莫属了！所以整个 Tomcat 的生命周期由 Server 控制。

