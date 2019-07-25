<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Spring IOC](#spring-ioc)
  - [IOC原理](#ioc%E5%8E%9F%E7%90%86)
  - [Spring单例Bean与单例模式的区别](#spring%E5%8D%95%E4%BE%8Bbean%E4%B8%8E%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F%E7%9A%84%E5%8C%BA%E5%88%AB)
- [Spring AOP](#spring-aop)
  - [实现AOP的技术](#%E5%AE%9E%E7%8E%B0aop%E7%9A%84%E6%8A%80%E6%9C%AF)
  - [Spring实现AOP](#spring%E5%AE%9E%E7%8E%B0aop)
  - [AOP使用场景](#aop%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
- [Spring Bean生命周期](#spring-bean%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F)
- [Spring @Transactional工作原理](#spring-transactional%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
- [SpringMVC的工作原理](#springmvc%E7%9A%84%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
  - [两种bean的实例化](#%E4%B8%A4%E7%A7%8Dbean%E7%9A%84%E5%AE%9E%E4%BE%8B%E5%8C%96)
- [SpringMVC拦截器](#springmvc%E6%8B%A6%E6%88%AA%E5%99%A8)
  - [常见应用场景](#%E5%B8%B8%E8%A7%81%E5%BA%94%E7%94%A8%E5%9C%BA%E6%99%AF)
  - [拦截器接口](#%E6%8B%A6%E6%88%AA%E5%99%A8%E6%8E%A5%E5%8F%A3)
  - [拦截器和过滤器什么区别](#%E6%8B%A6%E6%88%AA%E5%99%A8%E5%92%8C%E8%BF%87%E6%BB%A4%E5%99%A8%E4%BB%80%E4%B9%88%E5%8C%BA%E5%88%AB)
- [Spring源码分析](#spring%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
  - [容器的基本实现](#%E5%AE%B9%E5%99%A8%E7%9A%84%E5%9F%BA%E6%9C%AC%E5%AE%9E%E7%8E%B0)
    - [DefaultListableBeanFactory](#defaultlistablebeanfactory)
    - [XmlBeanDefinitionReader](#xmlbeandefinitionreader)
  - [Bean的加载](#bean%E7%9A%84%E5%8A%A0%E8%BD%BD)
    - [ClassPathXmlApplicationContext构造函数](#classpathxmlapplicationcontext%E6%9E%84%E9%80%A0%E5%87%BD%E6%95%B0)
    - [refresh()](#refresh)
    - [getBean()](#getbean)
      - [1、转换对应beanName](#1%E8%BD%AC%E6%8D%A2%E5%AF%B9%E5%BA%94beanname)
      - [2、尝试从缓存中加载单例](#2%E5%B0%9D%E8%AF%95%E4%BB%8E%E7%BC%93%E5%AD%98%E4%B8%AD%E5%8A%A0%E8%BD%BD%E5%8D%95%E4%BE%8B)
      - [3、bean的实例化](#3bean%E7%9A%84%E5%AE%9E%E4%BE%8B%E5%8C%96)
      - [4、原型模式的依赖检查](#4%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F%E7%9A%84%E4%BE%9D%E8%B5%96%E6%A3%80%E6%9F%A5)
      - [5、检测parentBeanFactory](#5%E6%A3%80%E6%B5%8Bparentbeanfactory)
      - [6、将存储XML配置文件的GernericBeanDefinition转换为RootBeanDefinition](#6%E5%B0%86%E5%AD%98%E5%82%A8xml%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E7%9A%84gernericbeandefinition%E8%BD%AC%E6%8D%A2%E4%B8%BArootbeandefinition)
      - [7、寻找依赖](#7%E5%AF%BB%E6%89%BE%E4%BE%9D%E8%B5%96)
      - [8、针对不同的scope进行bean的创建](#8%E9%92%88%E5%AF%B9%E4%B8%8D%E5%90%8C%E7%9A%84scope%E8%BF%9B%E8%A1%8Cbean%E7%9A%84%E5%88%9B%E5%BB%BA)
      - [9、类型转换](#9%E7%B1%BB%E5%9E%8B%E8%BD%AC%E6%8D%A2)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->




# Spring IOC
java程序中的每个业务逻辑至少需要两个或以上的对象来协作完成。通常，每个对象在使用他的合作对象时，自己均要使用像new object（） 这样的语法来完成合作对象的申请工作。这样对象间的耦合度高了。

IOC的思想是：IoC的核心思想在于资源统一管理,你所持有的资源全部放入到IoC容器中,而你也只需要依赖IoC容器,该容器会自动为你装配所需要的具体依赖. 对于spring框架来说，就是由spring来负责控制对象的生命周期和对象间的关系。

## IOC原理

IOC:

1 Spring的bean容器也叫beanfactory，我们常用的applicationcontext实际上内部有一个listablebeanfactory实际存储bean的map。

2 bean加载过程：spring容器加载时先读取配置文件，一般是xml，然后解析xml，找到其中所有bean，依次解析，然后生成每个bean的beandefinition，存在一个map中，根据beanid映射实际bean的map。

3 bean初始化：加载完以后，如果不启用懒加载模式，则默认使用单例加载，在注册完bean以后，可以获取到beandefinition信息，然后根据该信息首先先检查依赖关系，如果依赖其他bean则先加载其他bean，然后通过反射的方式即newinstance创建一个单例bean。

为什么要用反射呢，因为实现类可以通过配置改变，但接口是一致的，使用反射可以避免实现类改变时无法自动进行实例化。

当然，bean也可以使用原型方式加载，使用原型的话，每次创建bean都会是全新的。

## Spring单例Bean与单例模式的区别

单例模式是指在一个JVM进程中仅有一个实例，而Spring单例是指一个Spring Bean容器(ApplicationContext)中仅有一个实例。

首先看单例模式，在一个JVM进程中（理论上，一个运行的JAVA程序就必定有自己一个独立的JVM）仅有一个实例，于是无论在程序中的何处获取实例，始终都返回同一个对象。

与此相比，Spring的单例Bean是与其容器（ApplicationContext）密切相关的，所以在一个JVM进程中，如果有多个Spring容器，即使是单例bean，也一定会创建多个实例

#  Spring AOP
1. AOP利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了 多个类的公共行为封装到一个可重用模块，并将其名为“Aspect”，即方面。所谓“方面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的 逻辑或责任封装起来，比如日志记录，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。

## 实现AOP的技术
主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行； 二是采用静态织入的方式，引入特定的语法创建“方面”，从而使得编译器可以在编译期间织入有关“方面”的代码。

## Spring实现AOP
1. JDK动态代理：其代理对象必须是某个接口的实现，它是通过在运行期间创建一个接口的实现类来完成对目标对象的代理；其核心的两个类是InvocationHandler和Proxy。
2. CGLIB代理：实现原理类似于JDK动态代理，只是它在运行期间生成的代理对象是针对目标类扩展的子类。CGLIB是高效的代码生成包，底层是依靠ASM（开源的java字节码编辑类库）操作字节码实现的，性能比JDK强；需要引入包asm.jar和cglib.jar。


AOP:

AOP的切面，切点，增强器一般也是配置在xml文件中的，所以bean容器在解析xml时会找到这些内容，并且首先创建增强器bean的实例。

基于上面创建bean的过程，AOP起到了什么作用呢，或者是是否有参与到其中呢，答案是有的。

在获得beandefinition的时候，spring容器会检查该bean是否有aop切面所修饰，是否有能够匹配切点表达式的方法，如果有的话，在创建bean之前，会将bean重新封装成一个动态代理的对象。

代理类会为bean增加切面中配置的advisor增强器，然后返回bean的时候实际上返回的是一个动态代理对象。

所以我们在调用bean的方法时，会自动织入切面的增强器，当然，动态代理既可以选择jdk增强器，也可以选择cglib增强器。

Spring事务：

spring事务其实是一种特殊的aop方式。在spring配置文件中配置好事务管理器和声明式事务注解后，就可以使用@transactional进行事务方法的处理了。

事务管理器的bean中会配置基本的信息，然后需要配置事务的增强器，不同方法使用不同的增强器。当然如果使用注解的话就不用这么麻烦了。

然后和aop的动态代理方式类似，当Spring容器为bean生成代理时，会注入事务的增强器，其中实际上实现了事务中的begin和commit，所以执行方法的过程实际上就是在事务中进行的。

##  AOP使用场景
1. Authentication 权限检查        
2. Caching 缓存        
3. Context passing 内容传递        
4. Error handling 错误处理        
5. Lazy loading　延迟加载        
6. Debugging　　调试      
7. logging, tracing, profiling and monitoring　日志记录，跟踪，优化，校准        
8. Performance optimization　性能优化，效率检查        
9. Persistence　　持久化        
10. Resource pooling　资源池        
11. Synchronization　同步        
12. Transactions 事务管理  

# Spring Bean生命周期
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/spring-4.png)
- Bean的建立， 由BeanFactory读取Bean定义文件，并生成各个实例
- Setter注入，执行Bean的属性依赖注入
- BeanNameAware的setBeanName(), 如果实现该接口，则执行其setBeanName方法
- BeanFactoryAware的setBeanFactory()，如果实现该接口，则执行其setBeanFactory方法
- BeanPostProcessor的processBeforeInitialization()，如果有关联的processor，则在Bean初始化之前都会执行这个实例的processBeforeInitialization()方法
- InitializingBean的afterPropertiesSet()，如果实现了该接口，则执行其afterPropertiesSet()方法
- Bean定义文件中定义init-method
- BeanPostProcessors的processAfterInitialization()，如果有关联的processor，则在Bean初始化之前都会执行这个实例的processAfterInitialization()方法
- DisposableBean的destroy()，在容器关闭时，如果Bean类实现了该接口，则执行它的destroy()方法
- Bean定义文件中定义destroy-method，在容器关闭时，可以在Bean定义文件中使用“destory-method”定义的方法


# Spring @Transactional工作原理
1. 当spring遍历容器中所有的切面，查找与当前实例化bean匹配的切面，这里就是获取事务属性切面，查找@Transactional注解及其属性值，然后根据得到的切面进入createProxy方法，创建一个AOP代理。
2. 默认是使用JDK动态代理创建代理，如果目标类是接口，则使用JDK动态代理，否则使用Cglib。
3. 获取的是当前目标方法对应的拦截器，里面是根据之前获取到的切面来获取相对应拦截器，这时候会得到TransactionInterceptor实例。如果获取不到拦截器，则不会创建MethodInvocation，直接调用目标方法。
4. 在需要进行事务操作的时候，Spring会在调用目标类的目标方法之前进行开启事务、调用异常回滚事务、调用完成会提交事务。是否需要开启新事务，是根据@Transactional注解上配置的参数值来判断的。如果需要开启新事务，获取Connection连接，然后将连接的自动提交事务改为false，改为手动提交
5. Spring并不会对所有类型异常都进行事务回滚操作，默认是只对Unchecked Exception(Error和RuntimeException)进行事务回滚操作。

从上面的分析可以看到，Spring使用AOP实现事务的统一管理,基本都是下面这两种情况：
1. A类的a1方法没有标注@Transactional，a2方法标注@Transactional，在a1里面调用a2。a1方法是目标类A的原生方法，调用a1的时候即直接进入目标类A进行调用，在目标类A里面只有a2的原生方法，在a1里调用a2，即直接执行a2的原生方法，并不通过创建代理对象进行调用，所以并不会进入TransactionInterceptor的invoke方法，不会开启事务。
2. 将@Transactional注解标注在非public方法上。内部使用AOP，所以必须是public修饰的方法才可以被代理



# SpringMVC的工作原理
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/frame-1.jpg)
SpringMVC流程
1.  用户发送请求至前端控制器DispatcherServlet。
2.  DispatcherServlet收到请求调用HandlerMapping处理器映射器。
3.   处理器映射器找到具体的处理器(可以根据xml配置、注解进行查找)，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。
4.  DispatcherServlet调用HandlerAdapter处理器适配器。
5.  HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。
6.   Controller执行完成返回ModelAndView。
7.   HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。
8.   DispatcherServlet将ModelAndView传给ViewReslover视图解析器。
9.   ViewReslover解析后返回具体View。
10. DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。
11.  DispatcherServlet响应用户。

请求 ---> DispatcherServlet（前端控制器）---> 调用HandlerMapping（处理器映射器）---> DispatcherServlet调用 HandlerAdapter（处理器适配器）---> 适配调用具体的Controller ---> 返回ModelAndView  --->  传给ViewReslover视图解析器  --->  解析后返回具体View ---> 根据View进行渲染视图响应用户

## 两种bean的实例化
1、 懒汉式：BeanFactory

只有当客户端调用BeanFactory的getBean()方法来请求某个实例对象的时候，才会触发相应bean的实例化进程（ 当然对于 BeanFactory 容器而言并不是所有的 getBean() 方法都会触发实例化进程，比如 signleton 类型的 bean，该类型的 bean 只会在第一次调用 getBean() 的时候才会触发，而后续的调用则会直接返回容器缓存中的实例对象）

2、 饿汉式：ApplicationContext

使用ApplicationContext容器启动的时候立刻调用注册到该容器所有bean定义的实例化方法

Spring提供了两种类型的IOC容器实现（两种类型的配置方式是一样）
1. BeanFactory：是Spring框架的基础设施，面向Spring本身
2. ApplicationContext： 面向使用 Spring 框架的开发者，几乎所有的应用场合都直接使用 ApplicationContext 而非底层的


# SpringMVC拦截器
## 常见应用场景
1. 日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等
2. 权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面
3. 性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间（如果有反向代理，如apache可以自动记录）
4. 通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现。
5. OpenSessionInView：如Hibernate，在进入处理器打开Session，在完成后关闭Session。

## 拦截器接口
```java
public interface HandlerInterceptor {  
    /**
    * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器（如我们上一章的Controller实现）
    * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；
    * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应
    */
    boolean preHandle(  
            HttpServletRequest request, HttpServletResponse response,   
            Object handler)   
            throws Exception;  
    /**
    * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
    */
    void postHandle(  
            HttpServletRequest request, HttpServletResponse response,   
            Object handler, ModelAndView modelAndView)   
            throws Exception;  
    /**
    * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间 ，
    * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion。
    */
    void afterCompletion(  
            HttpServletRequest request, HttpServletResponse response,   
            Object handler, Exception ex)  
            throws Exception;  
}   
```

## 拦截器和过滤器什么区别
Spring的拦截器与Servlet的过滤器Filter有很多相似之处，比如两者都是AOP编程思想的体现，都能实现权限检查、日志记录等，不同的是：
1. 使用范围不同：Filter是Servlet规范规定的，只能用于Web程序中，而拦截器既可以用于Web程序，也可以用于Application、Swing程序中
2. 规范不同：Filter是Servlet规范中定义的，是Servlet容器支持的。而拦截器是在Spring容器内的，是Spring框架支持的
3. 使用的资源不同：拦截器是一个Spring的组件，归Spring管理，配置在Spring文件中，因此能使用Spring里的任何资源、对象，例如Service对象、数据源、事务管理等，通过IoC注入到拦截器即可，而Filter则不能
4. 深度不同：Filter只在Servlet前后起作用。而拦截器能够深入到方法前后、异常抛出前后等，因此拦截器的使用具有更大的弹性。所以在Spring架构的程序中，要优先使用拦截器。
5. 实现原理不同：拦截器是基于动态代理来实现的，而过滤器是基于函数回调来实现的。
6. 作用域不同：拦截器只对Action起作用，过滤器可以对所有请求起作用。
7. 调用次序不同：在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次。


# Spring源码分析
## 容器的基本实现

### DefaultListableBeanFactory
spring Ioc容器的实现，从根源上是beanfactory，但真正可以作为一个可以独立使用的ioc容器还是DefaultListableBeanFactory，因此可以这么说，DefaultListableBeanFactory 是整个spring ioc的始祖

**作用**
默认实现了ListableBeanFactory和BeanDefinitionRegistry接口，基于bean definition对象，是一个成熟的bean factroy。

最典型的应用是：在访问bean前，先注册所有的definition（可能从bean definition配置文件中）。使用预先建立的bean定义元数据对象，从本地的bean definition表中查询bean definition因而将不会花费太多成本。

DefaultListableBeanFactory既可以作为一个单独的beanFactory，也可以作为自定义beanFactory的父类。

注意：特定格式bean definition的解析器可以自己实现，也可以使用原有的解析器，如：PropertiesBeanDefinitionReader和XmLBeanDefinitionReader。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/spring-1.png)

**1、继承自AbstractAutowireCapableBeanFactory的方法**
AbstractAutowireCapableBeanFactory的作用：提供bean的创建 (有construct方法), 属性注值, 绑定 (包括自动绑定)和初始化，处理运行时bean引用, 解析管理的集合, 调用初始化方法。

**2、继承自ListableBeanFactory接口的方法**
ListableBeanFactory是beanFactory接口的扩展接口，它可以枚举所有的bean实例，而不是客户端通过名称一个一个的查询得出所有的实例。要预加载所有的bean定义的beanfactory可以实现这个接口来。该 接口定义了访问容器中Bean基本信息的若干方法，如查看Bean的个数、获取某一类型Bean的配置名、查看容器中是否包括某一Bean等方法

**3、继承自ConfigurableListableBeanFactory接口的方法**
ConfigurableListableBeanFactory 它同时继承了ListableBeanFactory，AutowireCapableBeanFactory和ConfigurableBeanFactory，提供了对bean定义的分析和修改的便利方法，同时也提供了对单例的预实例化

**4、继承自BeanDefinitionRegistry接口的方法**
BeanDefinitionRegistry：Spring配置文件中每一个<bean>节点元素在Spring容器里都通过一个BeanDefinition对象表示，它描述了Bean的配置信息。而BeanDefinition Registry接口提供了向容器手工注册BeanDefinition对象的方法
### XmlBeanDefinitionReader
XML 配置文件的读取时Spring中重要的功能，因为Spring的大部分功能都是以配置作为切入点的，那么我们可以从XMLBeanDefinitionReader中梳理一下资源文件读取、解析即注册的大致脉络。

BeanDefinition 的加载、解析、注册过程

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/spring-2.png)

## Bean的加载
对于加载bean的功能，在Spring中的调用方式为：
```java
ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
MyTestBean bean = (MyTestBean)ac.getBean("myTestBean");
```
ClassPathXmlApplicationContext用于加载CLASSPATH下的Spring配置文件，可以看到，第二行就已经可以获取到Bean的实例了，那么必然第一行就已经完成了对所有Bean实例的加载

### ClassPathXmlApplicationContext构造函数

```java
   public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
        super(parent);
        this.setConfigLocations(configLocations);
        if (refresh) {
            this.refresh();
        }
    }
```
this.setConfigLocations(configLocations);
1. 将指定的Spring配置文件的路径存储到本地
2. 解析Spring配置文件路径中的${PlaceHolder}占位符，替换为系统变量中PlaceHolder对应的Value值，System本身就自带一些系统变量比如class.path、os.name、user.dir等，也可以通过System.setProperty()方法设置自己需要的系统变量

### refresh()
1. 这个就是整个Spring Bean加载的核心了，用于刷新整个Spring上下文信息，定义了整个Spring上下文加载的流程。
```java
    @Override
	public void refresh() throws BeansException, IllegalStateException {
       //方法是加锁的，避免多线程同时刷新Spring上下文
		synchronized (this.startupShutdownMonitor) {
			//准备工作包括设置启动时间，是否激活标识位，初始化属性源(property source)配置
			prepareRefresh();

			//创建beanFactory（过程是根据xml为每个bean生成BeanDefinition并注册到生成的beanFactory）
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			//准备创建好的beanFactory（给beanFactory设置ClassLoader，设置SpEL表达式解析器，设置类型转化器【能将xml String类型转成相应对象】，增加内置ApplicationContextAwareProcessor对象，忽略各种Aware对象，注册各种内置的对账对象【BeanFactory，ApplicationContext】等，注册AOP相关的一些东西，注册环境相关的一些bean
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				//实例化并调用BeanFactory中扩展了BeanFactoryPostProcessor的Bean的postProcessBeanFactory方法
				invokeBeanFactoryPostProcessors(beanFactory);

				//实例化和注册beanFactory中扩展了BeanPostProcessor的bean
				registerBeanPostProcessors(beanFactory);

				//实例化，注册和设置国际化工具类MessageSource
				initMessageSource();

				//实例化，注册和设置消息广播类（如果没有自己定义使用默认的SimpleApplicationEventMulticaster实现，此广播使用同步的通知方式）
				initApplicationEventMulticaster();

				//设置样式工具ThemeSource
				onRefresh();

				//添加用户定义的消息接收器到上面设置的消息广播ApplicationEventMulticaster
				registerListeners();

				//设置自定义的类型转化器ConversionService，设置自定义AOP相关的类LoadTimeWeaverAware，清除临时的ClassLoader，冻结配置（没看明白干什么的），实例化所有的类（懒加载的类除外）
				finishBeanFactoryInitialization(beanFactory);

				//注册和设置跟bean生命周期相关的类（默认使用DefaultLifecycleProcessor），调用扩展了SmartLifecycle接口的start方法，使用上注册的广播类消息广播类ApplicationEventMulticaster广播ContextRefreshedEvent事件 
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```
1、方法是加锁的，这么做的原因是避免多线程同时刷新Spring上下文

2、尽管加锁可以看到是针对整个方法体的，但是没有在方法前加synchronized关键字，而使用了对象锁startUpShutdownMonitor，这样做有两个好处：
1. refresh()方法和close()方法都使用了startUpShutdownMonitor对象锁加锁，这就保证了在调用refresh()方法的时候无法调用close()方法，反之亦然，避免了冲突
2. 另外一个好处不在这个方法中体现，但是提一下，使用对象锁可以减小了同步的范围，只对不能并发的代码块进行加锁，提高了整体代码运行的效率


ApplicationContext 获取Bean的过程：

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/spring-3.png)


### getBean()
```java
public <T> T getBean(String name, Class<T> requiredType, Object... args) throws BeansException {
    return this.doGetBean(name, requiredType, args, false);
}

protected <T> T doGetBean(String name, Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws BeansException {
    //提取对应的beanName
    final String beanName = this.transformedBeanName(name);
    /*
     *检查缓存中或者实例工厂中是否有对应的实例
     *为什么首先会使用这段代码呢，因为在创建单例bean的时候会存在依赖注入的情况，而在创建依赖的时候为了避免循环依赖，
     *Spring创建bean的原则是不等bean创建完成就会将创建bean的ObjectFactory提早曝光，
     *也就是将ObjectFactory加入到缓存中，一旦下个bean创建时候需要依赖上个bean则直接使用ObjectFactory
     */
    //直接尝试从缓存获取或者singletonFactories中的ObjectFactory中获取
    Object sharedInstance = this.getSingleton(beanName);
    Object bean;
    if(sharedInstance != null && args == null) {
        if(this.logger.isDebugEnabled()) {
            if(this.isSingletonCurrentlyInCreation(beanName)) {
                this.logger.debug("Returning eagerly cached instance of singleton bean \'" + beanName + "\' that is not fully initialized yet - a consequence of a circular reference");
            } else {
                this.logger.debug("Returning cached instance of singleton bean \'" + beanName + "\'");
            }
        }

        //返回对应的实例，有时候存在诸如BeanFactory的情况并不是直接返回实例本身而是返回指定方法返回的实例
        bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, (RootBeanDefinition)null);
    } else {
        //只有在单例情况下才会尝试解决循环依赖，原型模式情况下，如果存在
        //A中有B的属性，B中有A的属性，那么当依赖注入的时候，就会产生当A还未创建完的时候因为
        //对于B的创建再次返回创建A，造成循环依赖，也就是下面的情况
        //isPrototypeCurrentlyInCreation(beanName)为true
        if(this.isPrototypeCurrentlyInCreation(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }

        BeanFactory parentBeanFactory = this.getParentBeanFactory();
        //如果BeanDefinitionMap中也就是在所有已经加载的类中不包括beanName则尝试从parentBeanFactory中检测
        if(parentBeanFactory != null && !this.containsBeanDefinition(beanName)) {
            String var19 = this.originalBeanName(name);
            //递归到BeanFactory中寻找
            if(args != null) {
                return parentBeanFactory.getBean(var19, args);
            }

            return parentBeanFactory.getBean(var19, requiredType);
        }

        //如果不是仅仅做类型检查则是创建bean，这里要进行记录
        if(!typeCheckOnly) {
            this.markBeanAsCreated(beanName);
        }

        //将存储XML配置文件的GernericBeanDefinition转换为RootBeanDefinition，如果指定BeanName是子Bean的话同时会合并父类的相关属性
        final RootBeanDefinition mbd = this.getMergedLocalBeanDefinition(beanName);
        this.checkMergedBeanDefinition(mbd, beanName, args);
        String[] dependsOn = mbd.getDependsOn();
        String scopeName;
        //若存在依赖则需要递归实例化依赖的bean
        if(dependsOn != null) {
            String[] var14 = dependsOn;
            int ex = dependsOn.length;

            for(int scope = 0; scope < ex; ++scope) {
                scopeName = var14[scope];
                this.getBean(scopeName);
                //缓存依赖调用
                 this.registerDependentBean(scopeName, beanName);
            }
        }

        //实例化依赖的bean后便可以实例化mbd本身了
        //singleton模式的创建
        if(mbd.isSingleton()) {
            sharedInstance = this.getSingleton(beanName, new ObjectFactory() {
                public Object getObject() throws BeansException {
                    try {
                        return AbstractBeanFactory.this.createBean(beanName, mbd, args);
                    } catch (BeansException var2) {
                        AbstractBeanFactory.this.destroySingleton(beanName);
                        throw var2;
                    }
                }
            });
            bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
        } else if(mbd.isPrototype()) {
            //prototype模式的创建(new)
            scopeName = null;

            Object var20;
            try {
                this.beforePrototypeCreation(beanName);
                var20 = this.createBean(beanName, mbd, args);
            } finally {
                this.afterPrototypeCreation(beanName);
            }

            bean = this.getObjectForBeanInstance(var20, name, beanName, mbd);
        } else {
            //指定的scope上实例化bean
            scopeName = mbd.getScope();
            Scope var21 = (Scope)this.scopes.get(scopeName);
            if(var21 == null) {
                throw new IllegalStateException("No Scope registered for scope \'" + scopeName + "\'");
            }

            try {
                Object var22 = var21.get(beanName, new ObjectFactory() {
                    public Object getObject() throws BeansException {
                        AbstractBeanFactory.this.beforePrototypeCreation(beanName);

                        Object var2;
                        try {
                            var2 = AbstractBeanFactory.this.createBean(beanName, mbd, args);
                        } finally {
                            AbstractBeanFactory.this.afterPrototypeCreation(beanName);
                        }

                        return var2;
                    }
                });
                bean = this.getObjectForBeanInstance(var22, name, beanName, mbd);
            } catch (IllegalStateException var18) {
                throw new BeanCreationException(beanName, "Scope \'" + scopeName + "\' is not active for the current thread; " + "consider defining a scoped proxy for this bean if you intend to refer to it from a singleton", var18);
            }
        }
    }

    //检查需要的类型是否符合bean的实际类型
    if(requiredType != null && bean != null && !requiredType.isAssignableFrom(bean.getClass())) {
        throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
    } else {
        return bean;
    }
}

```

对于Spring加载bean的过程，大致分为以下几步：

#### 1、转换对应beanName

这里传入的参数name不一定就是beanName，有可能是别名或FactoryBean，所以需要进行一系列的解析，这些解析内容包括如下内容
- 去除FactoryBean的修饰符，也就是如果name=”&aa”，那么会首先去除&而使name=”aa”
- 取指定alias所表示的最终beanName，例如别名A指向名称为B的bean则返回B；若别名A指向别名B，别名B又指向名称为C的bean则返回C

#### 2、尝试从缓存中加载单例

单例在Spring的同一个容器内只会被创建一次，后续再获取bean，就直接从单例缓存中获取了。这里只是尝试加载，首先尝试从缓存中加载，如果加载不成功则再次尝试从singletonFactories中加载，因为在创建单例bean的时候会存在依赖注入的情况，而在创建依赖的时候为了避免循环依赖，在Spring中创建bean的原则是不等bean创建完成就会将创建bean的ObjectFactory提早曝光加入到缓存中，一旦下一个bean创建时候需要依赖上一个bean则直接使用ObjectFactory

#### 3、bean的实例化

如果从缓存中得到了bean的原始状态，则需要对bean进行实例化，这里有必要强调一下，在缓存中记录的只是最原始的bean状态，并不一定是我们最终想要的bean

#### 4、原型模式的依赖检查

只有在单例情况下才会尝试解决循环依赖，如果存在A中有B的属性，B中有A的属性，那么当依赖注入的时候，就会产生当A还未创建完的时候因为对于B的创建再次返回创建A，造成循环依赖，也就是情况：isPrototypeCurrentlyInCreation(beanName)判断true

#### 5、检测parentBeanFactory

从代码上来看，如果缓存没有数据的话直接转到父类工厂上去加载，!this.containsBeanDefinition(beanName检测如果当前加载的XML配置文件中不包含beanName所对应的配置，就只能到parentBeanFactory去尝试，然后再去递归的调用getBean方法

#### 6、将存储XML配置文件的GernericBeanDefinition转换为RootBeanDefinition

因为从XML配置文件中读取到的Bean信息是存储在GernericBeanDefinition中的，但是所有的Bean后续处理都是针对于RootBeanDefinition的，所以这里需要进行一个转换，转换的同时如果父类bean不为空的话，则会一并合并父类属性

#### 7、寻找依赖

因为bean的初始化过程很可能会用到某些属性，而某些属性很可能是动态配置的，并且配置成依赖于其他的bean，那么这个时候就有必要先加载依赖的bean，所以，在Spring的加载顺寻中，在初始化某一个bean的时候首先会初始化这个bean所对应的依赖

#### 8、针对不同的scope进行bean的创建

在Spring中存在着不同的scope，其中默认的是singleton，但是还有些其他的配置诸如prototype、request之类的，在这个步骤中，Spring会根据不同的配置进行不同的初始化策略

#### 9、类型转换

程序到这里返回bean后已经基本结束了，通常对该方法的调用参数requiredType是为空的，但是可能会存在这样的情况，返回的bean其实是个Spring，但是requiredType却传入Integer类型，那么这时候本步骤就会起作用了，它的功能是将返回的bean转换为requiredType所指定的类型，当然，Spring转换为Integer是最简单的一种转换，在Spring中提供了各种各样的转换器，用户也可以自己扩展转换器来满足需求

