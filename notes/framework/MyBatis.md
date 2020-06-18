<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [MyBatis介绍](#mybatis%E4%BB%8B%E7%BB%8D)
  - [ORM](#orm)
  - [JPA](#jpa)
  - [MyBatis概念](#mybatis%E6%A6%82%E5%BF%B5)
  - [MyBatis优点](#mybatis%E4%BC%98%E7%82%B9)
- [MyBatis原理](#mybatis%E5%8E%9F%E7%90%86)
  - [MyBatis的设计思想](#mybatis%E7%9A%84%E8%AE%BE%E8%AE%A1%E6%80%9D%E6%83%B3)
- [一级缓存和二级缓存](#%E4%B8%80%E7%BA%A7%E7%BC%93%E5%AD%98%E5%92%8C%E4%BA%8C%E7%BA%A7%E7%BC%93%E5%AD%98)
  - [区别](#%E5%8C%BA%E5%88%AB)
  - [为什么不推荐使用二级缓存？](#%E4%B8%BA%E4%BB%80%E4%B9%88%E4%B8%8D%E6%8E%A8%E8%8D%90%E4%BD%BF%E7%94%A8%E4%BA%8C%E7%BA%A7%E7%BC%93%E5%AD%98)
  - [二级缓存的使用场景](#%E4%BA%8C%E7%BA%A7%E7%BC%93%E5%AD%98%E7%9A%84%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
- [MyBatis使用的设计模式](#mybatis%E4%BD%BF%E7%94%A8%E7%9A%84%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F)
- [MyBatis插件](#mybatis%E6%8F%92%E4%BB%B6)
  - [插件的构建](#%E6%8F%92%E4%BB%B6%E7%9A%84%E6%9E%84%E5%BB%BA)
  - [插件链是何时构建的](#%E6%8F%92%E4%BB%B6%E9%93%BE%E6%98%AF%E4%BD%95%E6%97%B6%E6%9E%84%E5%BB%BA%E7%9A%84)
  - [插件如何执行](#%E6%8F%92%E4%BB%B6%E5%A6%82%E4%BD%95%E6%89%A7%E8%A1%8C)
- [数据库预编译为什么能防止SQL注入呢？](#%E6%95%B0%E6%8D%AE%E5%BA%93%E9%A2%84%E7%BC%96%E8%AF%91%E4%B8%BA%E4%BB%80%E4%B9%88%E8%83%BD%E9%98%B2%E6%AD%A2sql%E6%B3%A8%E5%85%A5%E5%91%A2)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# MyBatis介绍
在介绍MyBatis之前先简单了解几个概念：ORM，JPA。

## ORM
ORM（Object-Relationship-Mapping）：是对象关系映射的意思，它是一种思想，是指将数据库中的每一行数据用对象的形式表现出来。

## JPA
JPA（Java-Persistence-API）：是Java持久化接口的意思，它是JavaEE关于ORM思想的一套标准接口，仅仅是一套接口，不是具体的实现。

## MyBatis概念
MyBatis是一个实现了JPA规范的用来连接数据库并对其进行增删改查操作的开源框架 （就和传统的JDBC一样，就是个连接数据库的东西），其实，它底层就是一个JDBC封装的组件。MyBatis的前身是Ibatis，Ibatis创建与2002年最初为Apache下面的一个开源项目，2010迁移到google code下面并改名为MyBatis。

MyBatis虽然实现了JPA但是它并不是一个完完全全的ORM组件，而是一个基于SQL开发的半ORM组件。

而Hibernate是一个完完全全的ORM组件，它是完全基于对象来操作数据库中的记录，并不和MyBatis一样是一个假把式。

## MyBatis优点
- 简单易学，容易上手（相比于Hibernate） ---- 基于SQL编程
- 消除了JDBC大量冗余的代码，不需要手动开关连接
- 很好的与各种数据库兼容（因为MyBatis使用JDBC来连接数据库，所以只要JDBC支持的数据库MyBatis都支持，而JDB提供了可扩展性，所以只要这个数据库有针对Java的jar包就可以就可以与MyBatis兼容），开发人员不需要考虑数据库的差异性。
- 提供了很多第三方插件（分页插件 / 逆向工程）
- 能够与Spring很好的集成

# MyBatis原理
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

## MyBatis的设计思想
如果让我们自己设计一个MyBatis，那么最核心的思想是什么呢？

答案：JDK动态代理和反射


MyBatis的作用就是**调用一个Mapper接口的方法就相当于执行一条sql**

1、MyBatis在SqlSession为给Mapper接口通过动态代理实现一个代理
2、在代理方法里面通过反射获取接口名称、方法名称、参数，拿这些数据后执行Executor的jdbc与sql交互的方法（这个才是真正去执行sql）
3、执行sql的结果集通过反射设置到Bean对象里面返回

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/frame-4.png)

注意**InvocationHandler**接口，这是proxy代理实例的调用处理程序实现的一个接口

mapper映射器其实就是一个动态代理对象，进入到MapperMethod的方法就能找到SqlSession的删除、更新、查询、选择方法，从底层实现来说：通过动态代理技术，让接口跑起来
      
```
public class MyMapperProxy implements InvocationHandler{
    private MySqlSession sqlSession;
    public MyMapperProxy(){}
    public MyMapperProxy(MySqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
//在代理方法里面通过反射获取接口名称、方法名称、参数，拿这些数据后执行Executor的jdbc与sql交互的方法（这个才是真正去执行sql）
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperClass = method.getDeclaringClass().getName();
        System.out.println("mapperClass"+mapperClass);
        if (UserMapperXML.namespace.equals(mapperClass)){
            String methodName = method.getName();
            String originsql = UserMapperXML.getMethodSql(methodName);
            String formatSql = String.format(originsql,String.valueOf(args[0]));
            return sqlSession.selectOne(formatSql);
        }
        return null;
    }
}
```
首先根据命名空间，找出与mapper接口方法名相同的sql语句，然后交给sqlSession来执行。（这里用到反射）

# 一级缓存和二级缓存

MyBatis提供查询缓存，用于减轻数据库压力，提高性能。MyBatis提供了一级缓存和二级缓存。
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/frame-3.jpg)

1. 一级缓存是SqlSession级别的缓存，每个SqlSession对象都有一个哈希表用于缓存数据，不同SqlSession对象之间缓存不共享。同一个SqlSession对象对象执行2遍相同的SQL查询，在第一次查询执行完毕后将结果缓存起来，这样第二遍查询就不用向数据库查询了，直接返回缓存结果即可。一级缓存是MyBatis内部实现的一个特性，用户不能配置，默认情况下自动支持的缓存，用户没有定制它的权利

2.  二级缓存是Application应用级别的缓存，它的是生命周期很长，跟Application的声明周期一样，也就是说它的作用范围是整个Application应用。MyBatis默认是不开启二级缓存的，可以在配置文件中使用如下配置来开启二级缓存
```xml
<settings>
    <setting name="cacheEnabled" value="true"/>
</settings>
```

## 区别
MyBatis 的一级缓存与二级缓存，是针对短时间内重复查询而做的优化：
* 一级缓存
    * Mybatis 默认只是开启一级缓存，一级缓存只是相对于同一个 SqlSession 而言。
    * 只有在参数和SQL完全一样的情况下，并且使用同一个 SqlSession 的情况下，Mybatis 才会将第一次的查询结果缓存起来，后续同一个SqlSession的再查询，就会命中缓存，而不是去直接查库
* 二级缓存
    * 一级缓存对于使用不同的 SqlSession 并不会命中缓存，即一级缓存必须 SqlSession，参数与Sql必须完全一致
    * 二级缓存需要手动配置，使得缓存在SqlSessionFactory层面上能够提供给各个Sql Session 共享
    * 二级缓存能够对同样参数，同样Sql语句，当时不同 SqlSession的查询提供命中
    
## 为什么不推荐使用二级缓存？
MyBatis 的二级缓存是和命名空间绑定的，所以通常情况下每一个 Mapper 映射文件都拥有 自己的二级缓存，不同 Mapper 的二级缓存互不影响。

在常见的数据库操作中，多表联合查询非常常见，由于关系型数据库的设计， 使得很多时候需要关联多个表才能获得想要的数据。在关联多表查询时肯定会将该查询放到某个命名空间下的映射文件中，这样一个多表的查询就会缓存在该命名空间的二级缓存中。涉及这些表的增、删、改操作通常不在一个映射文件中，它们 的命名空间不同， 因此当有数据变化时，多表查询的缓存未必会被清空，这种情况下就会产生脏数据。 

## 二级缓存的使用场景
1. 以查询为主的应用中，只有尽可能少的增、删、改操作；
2. 绝大多数以单表操作存在时，由于很少存在互相关联的情况，因此不会出现脏数据。

# MyBatis使用的设计模式
1. Builder模式，例如SqlSessionFactoryBuilder、XMLConfigBuilder、XMLMapperBuilder、XMLStatementBuilder、CacheBuilder；
2. 工厂模式，例如SqlSessionFactory、ObjectFactory、MapperProxyFactory；
3. 单例模式，例如ErrorContext和LogFactory；
4. 代理模式，Mybatis实现的核心，比如MapperProxy、ConnectionLogger，用的jdk的动态代理；还有executor.loader包使用了cglib或者javassist达到延迟加载的效果；
5. 组合模式，例如SqlNode和各个子类ChooseSqlNode等；
6. 模板方法模式，例如BaseExecutor和SimpleExecutor，还有BaseTypeHandler和所有的子类例如IntegerTypeHandler；
7. 适配器模式，例如Log的Mybatis接口和它对jdbc、log4j等各种日志框架的适配实现；
8. 装饰者模式，例如Cache包中的cache.decorators子包中等各个装饰者的实现；
9. 迭代器模式，例如迭代器模式PropertyTokenizer；


# MyBatis插件
Mybatis插件又称拦截器，Mybatis采用责任链模式，通过动态代理组织多个插件（拦截器），通过这些插件可以改变Mybatis的默认行为（诸如SQL重写之类的），MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的Mybatis四大接口方法调用包括：
```java
Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)

ParameterHandler (getParameterObject, setParameters)

ResultSetHandler (handleResultSets, handleOutputParameters)

StatementHandler (prepare, parameterize, batch, update, query)
```
总体概括为：
1. 拦截执行器的方法
2. 拦截参数的处理
3. 拦截结果集的处理
4. 拦截SQL语法构建的处理

## 插件的构建
谈原理首先要知道StatementHandler，ParameterHandler，Result Handler都是代理，他们是Configuration创建，在创建过程中会调用interceptorChain.pluginAll()方法，为四大组件组装插件（再底层是通过Plugin.wrap(target,XX, new Plugin( interceptor))来来创建的）。

## 插件链是何时构建的
在执行SqlSession的query或者update方法时，SqlSession会通过Configuration创建Executor代理，在创建过程中就调用interceptor的pluginAll方法组装插件。然后executor在调用doQuery（）方法的时候，也会调用Configuration的newStatementHandler方法创建StatemenHandler（和上面描述的一样，这个handler就是个代理，也是通过interceptorChain的pluginAll方法构建插件）

## 插件如何执行
以statementhandler的prepare方法的插件为例，正如前面所说，statementhandler是一个proxy，执行他的prepare方法，将调用invokeHandler的invoke方法，而invokeHandler就是Plugin.wrap(target, xxx, new Plugin(interceptor))中的第三个参数，所以很自然invokeHanlder的invoke的方法最终就会调用interceptor对象的intercept方法。

PageHelper分页的实现原来是在我们执行SQL语句之前动态的将SQL语句拼接了分页的语句，从而实现了从数据库中分页获取的过程。

# 数据库预编译为什么能防止SQL注入呢？
所谓SQL注入，它是利用现有应用程序，将（恶意）的SQL命令注入到后台数据库引擎执行的能力。
  
因为SQL语句在程序运行前已经进行了预编译，在程序运行时第一次操作数据库之前，SQL语句已经被数据库分析，编译和优化，对应的执行计划也会缓存下来并允许数据库已参数化的形式进行查询，当运行时动态地把参数传给PreprareStatement时，**即使参数里有敏感字符如 or '1=1’也数据库会作为一个参数一个字段的属性值来处理而不会作为一个SQL指令**，如此，就起到了SQL注入的作用了！
  
如果我们想防止SQL注入，理所当然地要在输入参数上下功夫。上面代码中黄色高亮即输入参数在SQL中拼接的部分，传入参数后，打印出执行的SQL语句，会看到SQL是这样的：
```
SELECT id,title,author,content FROM blog WHERE id = ?
```
不管输入什么参数，打印出的SQL都是这样的。这是因为MyBatis启用了预编译功能，在SQL执行前，会先将上面的SQL发送给数据库进行编译；执行时，直接使用编译好的SQL，替换占位符“?”就可以了。因为SQL注入只能对编译过程起作用，所以这样的方式就很好地避免了SQL注入的问题。

