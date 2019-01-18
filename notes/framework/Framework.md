<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Spring IOC](#spring-ioc)
  - [IOC原理](#ioc%E5%8E%9F%E7%90%86)
- [Spring AOP](#spring-aop)
  - [实现AOP的技术](#%E5%AE%9E%E7%8E%B0aop%E7%9A%84%E6%8A%80%E6%9C%AF)
  - [Spring实现AOP](#spring%E5%AE%9E%E7%8E%B0aop)
  - [AOP使用场景](#aop%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
- [Spring @Transactional工作原理](#spring-transactional%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
- [SpringMVC的工作原理](#springmvc%E7%9A%84%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
- [SpringMVC拦截器](#springmvc%E6%8B%A6%E6%88%AA%E5%99%A8)
  - [常见应用场景](#%E5%B8%B8%E8%A7%81%E5%BA%94%E7%94%A8%E5%9C%BA%E6%99%AF)
  - [拦截器接口](#%E6%8B%A6%E6%88%AA%E5%99%A8%E6%8E%A5%E5%8F%A3)
- [MyBatis原理](#mybatis%E5%8E%9F%E7%90%86)
- [MyBatis缓存](#mybatis%E7%BC%93%E5%AD%98)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Spring IOC
java程序中的每个业务逻辑至少需要两个或以上的对象来协作完成。通常，每个对象在使用他的合作对象时，自己均要使用像new object（） 这样的语法来完成合作对象的申请工作。这样对象间的耦合度高了。

IOC的思想是：IoC的核心思想在于资源统一管理,你所持有的资源全部放入到IoC容器中,而你也只需要依赖IoC容器,该容器会自动为你装配所需要的具体依赖. 对于spring框架来说，就是由spring来负责控制对象的生命周期和对象间的关系。

### IOC原理

IOC:

1 Spring的bean容器也叫beanfactory，我们常用的applicationcontext实际上内部有一个listablebeanfactory实际存储bean的map。

2 bean加载过程：spring容器加载时先读取配置文件，一般是xml，然后解析xml，找到其中所有bean，依次解析，然后生成每个bean的beandefinition，存在一个map中，根据beanid映射实际bean的map。

3 bean初始化：加载完以后，如果不启用懒加载模式，则默认使用单例加载，在注册完bean以后，可以获取到beandefinition信息，然后根据该信息首先先检查依赖关系，如果依赖其他bean则先加载其他bean，然后通过反射的方式即newinstance创建一个单例bean。

为什么要用反射呢，因为实现类可以通过配置改变，但接口是一致的，使用反射可以避免实现类改变时无法自动进行实例化。

当然，bean也可以使用原型方式加载，使用原型的话，每次创建bean都会是全新的。

##  Spring AOP
1. AOP利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了 多个类的公共行为封装到一个可重用模块，并将其名为“Aspect”，即方面。所谓“方面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的 逻辑或责任封装起来，比如日志记录，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。

### 实现AOP的技术
主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行； 二是采用静态织入的方式，引入特定的语法创建“方面”，从而使得编译器可以在编译期间织入有关“方面”的代码。

### Spring实现AOP
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

###  AOP使用场景
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


## Spring @Transactional工作原理
1. 当spring遍历容器中所有的切面，查找与当前实例化bean匹配的切面，这里就是获取事务属性切面，查找@Transactional注解及其属性值，然后根据得到的切面进入createProxy方法，创建一个AOP代理。
2. 默认是使用JDK动态代理创建代理，如果目标类是接口，则使用JDK动态代理，否则使用Cglib。
3. 获取的是当前目标方法对应的拦截器，里面是根据之前获取到的切面来获取相对应拦截器，这时候会得到TransactionInterceptor实例。如果获取不到拦截器，则不会创建MethodInvocation，直接调用目标方法。
4. 在需要进行事务操作的时候，Spring会在调用目标类的目标方法之前进行开启事务、调用异常回滚事务、调用完成会提交事务。是否需要开启新事务，是根据@Transactional注解上配置的参数值来判断的。如果需要开启新事务，获取Connection连接，然后将连接的自动提交事务改为false，改为手动提交
5. Spring并不会对所有类型异常都进行事务回滚操作，默认是只对Unchecked Exception(Error和RuntimeException)进行事务回滚操作。

从上面的分析可以看到，Spring使用AOP实现事务的统一管理,基本都是下面这两种情况：
1. A类的a1方法没有标注@Transactional，a2方法标注@Transactional，在a1里面调用a2。a1方法是目标类A的原生方法，调用a1的时候即直接进入目标类A进行调用，在目标类A里面只有a2的原生方法，在a1里调用a2，即直接执行a2的原生方法，并不通过创建代理对象进行调用，所以并不会进入TransactionInterceptor的invoke方法，不会开启事务。
2. 将@Transactional注解标注在非public方法上。内部使用AOP，所以必须是public修饰的方法才可以被代理



## SpringMVC的工作原理
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


## SpringMVC拦截器
### 常见应用场景
1. 日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等
2. 权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面
3. 性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间（如果有反向代理，如apache可以自动记录）
4. 通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现。
5. OpenSessionInView：如Hibernate，在进入处理器打开Session，在完成后关闭Session。

### 拦截器接口
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


## MyBatis原理
**MyBatis完成2件事情**

1. 封装JDBC操作
2. 利用反射打通Java类与SQL语句之间的相互转换

**MyBatis的主要成员**

1. Configuration        MyBatis所有的配置信息都保存在Configuration对象之中，配置文件中的大部分配置都会存储到该类中
2. SqlSession           作为MyBatis工作的主要顶层API，表示和数据库交互时的会话，完成必要数据库增删改查功能
3. Executor             MyBatis执行器，是MyBatis 调度的核心，负责SQL语句的生成和查询缓存的维护
4. StatementHandler     封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数等
5. ParameterHandler     负责对用户传递的参数转换成JDBC Statement 所对应的数据类型
6. ResultSetHandler     负责将JDBC返回的ResultSet结果集对象转换成List类型的集合
7. TypeHandler          负责java数据类型和jdbc数据类型(也可以说是数据表列类型)之间的映射和转换
8. MappedStatement      MappedStatement维护一条select|update|delete|insert节点的封装
9. SqlSource            负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
10. BoundSql            表示动态生成的SQL语句以及相应的参数信息


![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/frame-2.jpg)


## MyBatis缓存

MyBatis提供查询缓存，用于减轻数据库压力，提高性能。MyBatis提供了一级缓存和二级缓存。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/frame-3.jpg)

1. 一级缓存是SqlSession级别的缓存，每个SqlSession对象都有一个哈希表用于缓存数据，不同SqlSession对象之间缓存不共享。同一个SqlSession对象对象执行2遍相同的SQL查询，在第一次查询执行完毕后将结果缓存起来，这样第二遍查询就不用向数据库查询了，直接返回缓存结果即可。一级缓存是MyBatis内部实现的一个特性，用户不能配置，默认情况下自动支持的缓存，用户没有定制它的权利

2.  二级缓存是Application应用级别的缓存，它的是生命周期很长，跟Application的声明周期一样，也就是说它的作用范围是整个Application应用。MyBatis默认是不开启二级缓存的，可以在配置文件中使用如下配置来开启二级缓存
```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
</settings>
```