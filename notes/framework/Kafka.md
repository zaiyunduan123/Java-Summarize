<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Kafka介绍](#kafka%E4%BB%8B%E7%BB%8D)
- [Kafka特性](#kafka%E7%89%B9%E6%80%A7)
- [Kafka使用场景](#kafka%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
- [Kafka术语](#kafka%E6%9C%AF%E8%AF%AD)
- [Kafka集群](#kafka%E9%9B%86%E7%BE%A4)
  - [线上集群部署方案](#%E7%BA%BF%E4%B8%8A%E9%9B%86%E7%BE%A4%E9%83%A8%E7%BD%B2%E6%96%B9%E6%A1%88)
    - [操作系统](#%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F)
    - [磁盘](#%E7%A3%81%E7%9B%98)
    - [磁盘容量](#%E7%A3%81%E7%9B%98%E5%AE%B9%E9%87%8F)
    - [带宽](#%E5%B8%A6%E5%AE%BD)
  - [集群参数配置](#%E9%9B%86%E7%BE%A4%E5%8F%82%E6%95%B0%E9%85%8D%E7%BD%AE)
    - [Broker 端参数](#broker-%E7%AB%AF%E5%8F%82%E6%95%B0)
    - [ZooKeeper 相关参数](#zookeeper-%E7%9B%B8%E5%85%B3%E5%8F%82%E6%95%B0)
    - [Broker连接相关](#broker%E8%BF%9E%E6%8E%A5%E7%9B%B8%E5%85%B3)
    - [Topic管理](#topic%E7%AE%A1%E7%90%86)
    - [数据留存](#%E6%95%B0%E6%8D%AE%E7%95%99%E5%AD%98)
- [Kafka分区机制](#kafka%E5%88%86%E5%8C%BA%E6%9C%BA%E5%88%B6)
  - [为什么分区？](#%E4%B8%BA%E4%BB%80%E4%B9%88%E5%88%86%E5%8C%BA)
  - [分区策略](#%E5%88%86%E5%8C%BA%E7%AD%96%E7%95%A5)
    - [轮询策略](#%E8%BD%AE%E8%AF%A2%E7%AD%96%E7%95%A5)
    - [随机策略](#%E9%9A%8F%E6%9C%BA%E7%AD%96%E7%95%A5)
    - [按消息键保序策略](#%E6%8C%89%E6%B6%88%E6%81%AF%E9%94%AE%E4%BF%9D%E5%BA%8F%E7%AD%96%E7%95%A5)
- [Kafka副本机制](#kafka%E5%89%AF%E6%9C%AC%E6%9C%BA%E5%88%B6)
  - [如何确保副本中所有的数据都是一致的呢?](#%E5%A6%82%E4%BD%95%E7%A1%AE%E4%BF%9D%E5%89%AF%E6%9C%AC%E4%B8%AD%E6%89%80%E6%9C%89%E7%9A%84%E6%95%B0%E6%8D%AE%E9%83%BD%E6%98%AF%E4%B8%80%E8%87%B4%E7%9A%84%E5%91%A2)
    - [为什么追随者副本是不对外提供服务的呢？](#%E4%B8%BA%E4%BB%80%E4%B9%88%E8%BF%BD%E9%9A%8F%E8%80%85%E5%89%AF%E6%9C%AC%E6%98%AF%E4%B8%8D%E5%AF%B9%E5%A4%96%E6%8F%90%E4%BE%9B%E6%9C%8D%E5%8A%A1%E7%9A%84%E5%91%A2)
      - [1. 方便实现“Read-your-writes”](#1-%E6%96%B9%E4%BE%BF%E5%AE%9E%E7%8E%B0read-your-writes)
      - [2. 方便实现单调读（Monotonic Reads）](#2-%E6%96%B9%E4%BE%BF%E5%AE%9E%E7%8E%B0%E5%8D%95%E8%B0%83%E8%AF%BBmonotonic-reads)
- [无消息丢失配置怎么实现？](#%E6%97%A0%E6%B6%88%E6%81%AF%E4%B8%A2%E5%A4%B1%E9%85%8D%E7%BD%AE%E6%80%8E%E4%B9%88%E5%AE%9E%E7%8E%B0)
  - [生产者程序丢失数据](#%E7%94%9F%E4%BA%A7%E8%80%85%E7%A8%8B%E5%BA%8F%E4%B8%A2%E5%A4%B1%E6%95%B0%E6%8D%AE)
  - [消费者程序丢失数据](#%E6%B6%88%E8%B4%B9%E8%80%85%E7%A8%8B%E5%BA%8F%E4%B8%A2%E5%A4%B1%E6%95%B0%E6%8D%AE)
  - [Kafka 无消息丢失的配置](#kafka-%E6%97%A0%E6%B6%88%E6%81%AF%E4%B8%A2%E5%A4%B1%E7%9A%84%E9%85%8D%E7%BD%AE)
- [Kafka的通信](#kafka%E7%9A%84%E9%80%9A%E4%BF%A1)
- [消费者组](#%E6%B6%88%E8%B4%B9%E8%80%85%E7%BB%84)
  - [Rebalance重平衡](#rebalance%E9%87%8D%E5%B9%B3%E8%A1%A1)
    - [重平衡全流程](#%E9%87%8D%E5%B9%B3%E8%A1%A1%E5%85%A8%E6%B5%81%E7%A8%8B)
      - [JoinGroup 请求的处理过程](#joingroup-%E8%AF%B7%E6%B1%82%E7%9A%84%E5%A4%84%E7%90%86%E8%BF%87%E7%A8%8B)
      - [SyncGroup 请求的处理流程](#syncgroup-%E8%AF%B7%E6%B1%82%E7%9A%84%E5%A4%84%E7%90%86%E6%B5%81%E7%A8%8B)
  - [位移提交](#%E4%BD%8D%E7%A7%BB%E6%8F%90%E4%BA%A4)
  - [消费进度监控](#%E6%B6%88%E8%B4%B9%E8%BF%9B%E5%BA%A6%E7%9B%91%E6%8E%A7)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->



## Kafka介绍
Kafka是最初由Linkedin公司开发，是一个分布式、支持分区的（partition）、多副本的（replica），基于zookeeper协调的分布式消息系统，它的最大的特性就是可以实时的处理大量数据以满足各种需求场景：比如基于hadoop的批处理系统、低延迟的实时系统、storm/Spark流式处理引擎，web/nginx日志、访问日志，消息服务等等，用scala语言编写，Linkedin于2010年贡献给了Apache基金会并成为顶级开源项目。

## Kafka特性
- 高吞吐量、低延迟：kafka每秒可以处理几十万条消息，它的延迟最低只有几毫秒，每个topic可以分多个partition, consumer group 对partition进行consume操作。
- 可扩展性：kafka集群支持热扩展
- 持久性、可靠性：消息被持久化到本地磁盘，并且支持数据备份防止数据丢失
- 容错性：允许集群中节点失败（若副本数量为n,则允许n-1个节点失败）
- 高并发：支持数千个客户端同时读写

## Kafka使用场景
- 日志收集：一个公司可以用Kafka可以收集各种服务的log，通过kafka以统一接口服务的方式开放给各种consumer，例如hadoop、Hbase、Solr等。
- 消息系统：解耦和生产者和消费者、缓存消息等。
- 用户活动跟踪：Kafka经常被用来记录web用户或者app用户的各种活动，如浏览网页、搜索、点击等活动，这些活动信息被各个服务器发布到kafka的topic中，然后订阅者通过订阅这些topic来做实时的监控分析，或者装载到hadoop、数据仓库中做离线分析和挖掘。
- 运营指标：Kafka也经常用来记录运营监控数据。包括收集各种分布式应用的数据，生产各种操作的集中反馈，比如报警和报告。
- 流式处理：比如spark streaming和storm
- 事件源

## Kafka术语
- 消息：Record。Kafka 是消息引擎嘛，这里的消息就是指 Kafka 处理的主要对象。
- 主题：Topic。主题是承载消息的逻辑容器，在实际使用中多用来区分具体的业务。
- 分区：Partition。一个有序不变的消息序列。每个主题下可以有多个分区。
- 消息位移：Offset。表示分区中每条消息的位置信息，是一个单调递增且不变的值。
- 副本：Replica。Kafka 中同一条消息能够被拷贝到多个地方以提供数据冗余，这些地方就是所谓的副本。副本还分为领导者副本和追随者副本，各自有不同的角色划分。副本是在分区层级下的，即每个分区可配置多个副本实现高可用。
- 生产者：Producer。向主题发布新消息的应用程序。
- 消费者：Consumer。从主题订阅新消息的应用程序。
- 消费者位移：Consumer Offset。表征消费者消费进度，每个消费者都有自己的消费者位移。
- 消费者组：Consumer Group。多个消费者实例共同组成的一个组，同时消费多个分区以实现高吞吐。
- 重平衡：Rebalance。消费者组内某个消费者实例挂掉后，其他消费者实例自动重新分配订阅主题分区的过程。Rebalance 是 Kafka 消费者端实现高可用的重要手段。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-1.png)

## Kafka集群
### 线上集群部署方案
从操作系统、磁盘、磁盘容量和带宽等方面来讨论一下
#### 操作系统
目前常见的操作系统有 3 种：Linux、Windows 和 macOS。应该说部署在 Linux 上的生产环境是最多的。
如果考虑操作系统与 Kafka 的适配性，Linux 系统显然要比其他两个特别是 Windows 系统更加适合部署 Kafka。

Kafka 客户端底层使用了 Java 的 selector，selector 在 Linux 上的实现机制是 epoll，而在 Windows 平台上的实现机制是 select。因此在这一点上将 Kafka 部署在 Linux 上是有优势的，因为能够获得更高效的 I/O 性能。

Linux 平台实现了零拷贝机制，就是当数据在磁盘和网络进行传输时避免昂贵的内核态数据拷贝从而实现快速地数据传输。在 Linux 部署 Kafka 能够享受到零拷贝技术所带来的快速数据传输特性。

#### 磁盘
应该选择普通的机械磁盘还是固态硬盘？前者成本低且容量大，但易损坏；后者性能优势大，不过单价高。

Kafka 大量使用磁盘，可它使用的方式多是顺序读写操作，一定程度上规避了机械磁盘最大的劣势，即随机读写操作慢。

就 Kafka 而言，一方面 Kafka 自己实现了冗余机制来提供高可靠性；另一方面通过分区的概念，所以使用机械磁盘完全能够胜任 Kafka 线上环境。

#### 磁盘容量
我们来计算一下：每天 1 亿条 1KB 大小的消息，保存两份且留存两周的时间，那么总的空间大小就等于 1 亿 * 1KB * 2 / 1000 / 1000 = 200GB。一般情况下 Kafka 集群除了消息数据还有其他类型的数据，比如索引数据等，故我们再为这些数据预留出 10% 的磁盘空间，因此总的存储容量就是 220GB。既然要保存两周，那么整体容量即为 220GB * 14，大约 3TB 左右。Kafka 支持数据的压缩，假设压缩比是 0.75，那么最后你需要规划的存储空间就是 0.75 * 3 = 2.25TB。

总之在规划磁盘容量时你需要考虑下面这几个元素：
- 新增消息数
- 消息留存时间
- 平均消息大小
- 备份数
- 是否启用压缩

#### 带宽
在 1 小时内处理 1TB 的业务数据。需要多少台 Kafka 服务器来完成这个业务呢？

通常情况下你只能假设 Kafka 会用到 70% 的带宽资源，因为总要为其他应用或进程留一些资源。

根据这个目标，我们每秒需要处理 2336Mb 的数据，除以 240，约等于 10 台服务器。如果消息还需要额外复制两份，那么总的服务器台数还要乘以 3，即 30 台。

### 集群参数配置
#### Broker 端参数
- log.dirs：指定了 Broker 需要使用的若干个文件目录路径。要知道这个参数是没有默认值的，它必须由你亲自指定。
- log.dir：注意这是 dir，结尾没有 s，说明它只能表示单个路径，它是补充上一个参数用的。

在线上生产环境中一定要为log.dirs配置多个路径。这样做有两个好处：
- 提升读写性能：比起单块磁盘，多块物理磁盘同时读写数据有更高的吞吐量。
- 能够实现故障转移：即 Failover。这是 Kafka 1.1 版本新引入的强大功能。坏掉的磁盘上的数据会自动地转移到其他正常的磁盘上，而且 Broker 还能正常工作。

#### ZooKeeper 相关参数
- zookeeper.connect

#### Broker连接相关
- listeners：监听器，告诉外部连接者要通过什么协议访问指定主机名和端口开放的 Kafka 服务。
- advertised.listeners：和 listeners 相比多了个 advertised。Advertised 的含义表示宣称的、公布的，就是说这组监听器是 Broker 用于对外发布的。

#### Topic管理
- auto.create.topics.enable：是否允许自动创建 Topic。
- unclean.leader.election.enable：是否允许 Unclean Leader 选举。
- auto.leader.rebalance.enable：是否允许定期进行 Leader 选举。


auto.create.topics.enable参数我建议最好设置成 false，即不允许自动创建 Topic。在线上环境里面有很多名字稀奇古怪的 Topic，大概都是因为该参数被设置成了 true 的缘故。

#### 数据留存
- log.retention.{hour|minutes|ms}：都是控制一条消息数据被保存多长时间。从优先级上来说 ms 设置最高、minutes 次之、hour 最低。
- log.retention.bytes：这是指定 Broker 为消息保存的总磁盘容量大小。
- message.max.bytes：控制 Broker 能够接收的最大消息大小。
- retention.ms：规定了该 Topic 消息被保存的时长。默认是 7 天，即该 Topic 只保存最近 7 天的消息。一旦设置了这个值，它会覆盖掉 Broker 端的全局参数值。
- retention.bytes：规定了要为该 Topic 预留多大的磁盘空间。和全局参数作用相似，这个值通常在多租户的 Kafka 集群中会有用武之地。当前默认值是 -1，表示可以无限使用磁盘空间。

## Kafka分区机制
### 为什么分区？
分区的作用就是提供负载均衡的能力，或者说对数据进行分区的主要原因，就是为了实现系统的高伸缩性（Scalability）。不同的分区能够被放置到不同节点的机器上，而数据的读写操作也都是针对分区这个粒度而进行的，这样每个节点的机器都能独立地执行各自分区的读写请求处理。并且，我们还可以通过添加新的节点机器来增加整体系统的吞吐量。

Kafka 的三层消息架构：
- 第一层是主题层，每个主题可以配置 M 个分区，而每个分区又可以配置 N 个副本。
- 第二层是分区层，每个分区的 N 个副本中只能有一个充当领导者角色，对外提供服务；其他 N-1 个副本是追随者副本，只是提供数据冗余之用。
- 第三层是消息层，分区中包含若干条消息，每条消息的位移从 0 开始，依次递增。
- 最后，客户端程序只能与分区的领导者副本进行交互

### 分区策略
所谓分区策略是决定生产者将消息发送到哪个分区的算法
#### 轮询策略
也称 Round-robin 策略，即顺序分配。比如一个主题下有 3 个分区，那么第一条消息被发送到分区 0，第二条被发送到分区 1，第三条被发送到分区 2，以此类推。当生产第 4 条消息时又会重新开始，即将其分配到分区 0

轮询策略是 Kafka Java 生产者 API 默认提供的分区策略。它总是能保证消息最大限度地被平均分配到所有分区上，故默认情况下它是最合理的分区策略，也是我们最常用的分区策略之一。

#### 随机策略
所谓随机就是我们随意地将消息放置到任意一个分区上
#### 按消息键保序策略
Kafka 允许为每条消息定义消息键，简称为 Key。这个 Key 的作用非常大，它可以是一个有着明确业务含义的字符串，比如客户代码、部门编号或是业务 ID 等；也可以用来表征消息元数据。

## Kafka副本机制
所谓的副本机制（Replication），也可以称之为备份机制，通常是指分布式系统在多台网络互联的机器上保存有相同的数据拷贝。副本机制有什么好处呢？
1. 提供数据冗余。即使系统部分组件失效，系统依然能够继续运转，因而增加了整体可用性以及数据持久性。
2. 提供高伸缩性。支持横向扩展，能够通过增加机器的方式来提升读性能，进而提高读操作吞吐量。
3. 改善数据局部性。允许将数据放入与用户地理位置相近的地方，从而降低系统延时。

Kafka 是有主题概念的，而每个主题又进一步划分成若干个分区。副本的概念实际上是在分区层级下定义的，每个分区配置有若干个副本。

所谓副本（Replica），本质就是一个只能追加写消息的提交日志。

### 如何确保副本中所有的数据都是一致的呢?
最常见的解决方案就是采用基于领导者（Leader-based）的副本机制。Apache Kafka 就是这样的设计。

基于领导者的副本机制的工作原理如下图所示

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-4.png)

1. 在 Kafka 中，副本分成两类：领导者副本（Leader Replica）和追随者副本（Follower Replica）。每个分区在创建时都要选举一个副本，称为领导者副本，其余的副本自动称为追随者副本。
2. Kafka 的副本机制比其他分布式系统要更严格一些。在 Kafka 中，追随者副本是不对外提供服务的。这就是说，任何一个追随者副本都不能响应消费者和生产者的读写请求。所有的请求都必须由领导者副本来处理，或者说，所有的读写请求都必须发往领导者副本所在的 Broker，由该 Broker 负责处理。追随者副本不处理客户端请求，它唯一的任务就是从领导者副本异步拉取消息，并写入到自己的提交日志中，从而实现与领导者副本的同步。
3. 当领导者副本挂掉了，或者说领导者副本所在的 Broker 宕机时，Kafka 依托于 ZooKeeper 提供的监控功能能够实时感知到，并立即开启新一轮的领导者选举，从追随者副本中选一个作为新的领导者。老 Leader 副本重启回来后，只能作为追随者副本加入到集群中。

#### 为什么追随者副本是不对外提供服务的呢？
##### 1. 方便实现“Read-your-writes”
顾名思义就是，当你使用生产者 API 向 Kafka 成功写入消息后，马上使用消费者 API 去读取刚才生产的消息。

##### 2. 方便实现单调读（Monotonic Reads）
就是对于一个消费者用户而言，在多次消费消息时，它不会看到某条消息一会儿存在一会儿不存在。


## 无消息丢失配置怎么实现？
Kafka 只对“已提交”的消息（committed message）做有限度的持久化保证。

### 生产者程序丢失数据
Producer 永远要使用带有回调通知的发送 API，也就是说不要使用 producer.send(msg)，而要使用 producer.send(msg, callback)。

它能准确地告诉你消息是否真的提交成功了。一旦出现消息提交失败的情况，你就可以有针对性地进行处理。

### 消费者程序丢失数据
Consumer 端丢失数据主要体现在 Consumer 端要消费的消息不见了。Consumer 程序有个“位移”的概念，表示的是这个 Consumer 当前消费到的 Topic 分区的位置。下面这张图来自于官网，它清晰地展示了 Consumer 端的位移数据。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-2.png)

比如对于 Consumer A 而言，它当前的位移值就是 9；Consumer B 的位移值是 11。

要对抗这种消息丢失，办法很简单：维持先消费消息，再更新位移的顺序即可。

如果是多线程异步处理消费消息，Consumer 程序不要开启自动提交位移，而是要应用程序手动提交位移。

### Kafka 无消息丢失的配置
1. 不要使用 producer.send(msg)，而要使用 producer.send(msg, callback)。记住，一定要使用带有回调通知的 send 方法。
2. 设置 acks = all。acks 是 Producer 的一个参数，代表了你对“已提交”消息的定义。如果设置成 all，则表明所有副本 Broker 都要接收到消息，该消息才算是“已提交”。这是最高等级的“已提交”定义。
3. 设置 retries 为一个较大的值。这里的 retries 同样是 Producer 的参数，对应前面提到的 Producer 自动重试。当出现网络的瞬时抖动时，消息发送可能会失败，此时配置了 retries > 0 的 Producer 能够自动重试消息发送，避免消息丢失。
4. 设置 unclean.leader.election.enable = false。这是 Broker 端的参数，它控制的是哪些 Broker 有资格竞选分区的 Leader。如果一个 Broker 落后原先的 Leader 太多，那么它一旦成为新的 Leader，必然会造成消息的丢失。故一般都要将该参数设置成 false，即不允许这种情况的发生。
5. 设置 replication.factor >= 3。这也是 Broker 端的参数。其实这里想表述的是，最好将消息多保存几份，毕竟目前防止消息丢失的主要机制就是冗余。
6. 设置 min.insync.replicas > 1。这依然是 Broker 端参数，控制的是消息至少要被写入到多少个副本才算是“已提交”。设置成大于 1 可以提升消息持久性。在实际环境中千万不要使用默认值 1。
7. 确保 replication.factor > min.insync.replicas。如果两者相等，那么只要有一个副本挂机，整个分区就无法正常工作了。我们不仅要改善消息的持久性，防止数据丢失，还要在不降低可用性的基础上完成。推荐设置成 replication.factor = min.insync.replicas + 1。
8. 确保消息消费完成再提交。Consumer 端有个参数 enable.auto.commit，最好把它设置成 false，并采用手动提交位移的方式。就像前面说的，这对于单 Consumer 多线程处理的场景而言是至关重要的。


## Kafka的通信
Apache Kafka 的所有通信都是基于 TCP 的，而不是基于 HTTP 或其他协议。无论是生产者、消费者，还是 Broker 之间的通信都是如此。

目的是利用 TCP 本身提供的一些高级功能，比如多路复用请求以及同时轮询多个连接的能力

所谓的多路复用请求，即 multiplexing request，是指将两个或多个数据流合并到底层单一物理连接中的过程。TCP 的多路复用请求会在一条物理连接上创建若干个虚拟连接，每个虚拟连接负责流转各自对应的数据流。其实严格来说，TCP 并不能多路复用，它只是提供可靠的消息交付语义保证，比如自动重传丢失的报文。

更严谨地说，作为一个基于报文的协议，TCP 能够被用于多路复用连接场景的前提是，上层的应用协议（比如 HTTP）允许发送多条消息。

## 消费者组
Consumer Group 是 Kafka 提供的可扩展且具有容错性的消费者机制。既然是一个组，那么组内必然可以有多个消费者或消费者实例（Consumer Instance），它们共享一个公共的 ID，这个 ID 被称为 Group ID。

组内的所有消费者协调在一起来消费订阅主题（Subscribed Topics）的所有分区（Partition）。当然，每个分区只能由同一个消费者组内的一个 Consumer 实例来消费。

理解 Consumer Group 记住下面这三个特性:
1. Consumer Group 下可以有一个或多个 Consumer 实例。这里的实例可以是一个单独的进程，也可以是同一进程下的线程。在实际场景中，使用进程更为常见一些。
2. Group ID 是一个字符串，在一个 Kafka 集群中，它标识唯一的一个 Consumer Group。
3. Consumer Group 下所有实例订阅的主题的单个分区，只能分配给组内的某个 Consumer 实例消费。这个分区当然也可以被其他的 Group 消费。

Kafka 仅仅使用 Consumer Group 这一种机制，却同时实现了传统消息引擎系统的两大模型：如果所有实例都属于同一个 Group，那么它实现的就是消息队列模型；如果所有实例分别属于不同的 Group，那么它实现的就是发布 / 订阅模型。

理想情况下，Consumer 实例的数量应该等于该 Group 订阅主题的分区总数。

### Rebalance重平衡
Rebalance 本质上是一种协议，规定了一个 Consumer Group 下的所有 Consumer 如何达成一致，来分配订阅 Topic 的每个分区。

Rebalance 的触发条件:
1. 组成员数发生变更。比如有新的 Consumer 实例加入组或者离开组，抑或是有 Consumer 实例崩溃被“踢出”组。
2. 订阅主题数发生变更。Consumer Group 可以使用正则表达式的方式订阅主题，比如 consumer.subscribe(Pattern.compile(“t.*c”)) 就表明该 Group 订阅所有以字母 t 开头、字母 c 结尾的主题。在 Consumer Group 的运行过程中，你新创建了一个满足这样条件的主题，那么该 Group 就会发生 Rebalance。
3. 订阅主题的分区数发生变更。Kafka 当前只能允许增加一个主题的分区数。当分区数增加时，就会触发订阅该主题的所有 Group 开启 Rebalance。

#### 重平衡全流程
消费者组状态机的各个状态流转

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-5.png)

一个消费者组最开始是 Empty 状态，当重平衡过程开启后，它会被置于 PreparingRebalance 状态等待成员加入，之后变更到 CompletingRebalance 状态等待分配方案，最后流转到 Stable 状态完成重平衡。

当有新成员加入或已有成员退出时，消费者组的状态从 Stable 直接跳到 PreparingRebalance 状态，此时，所有现存成员就必须重新申请加入组。当所有成员都退出组后，消费者组状态变更为 Empty。Kafka 定期自动删除过期位移的条件就是，组要处于 Empty 状态。因此，如果你的消费者组停掉了很长时间（超过 7 天），那么 Kafka 很可能就把该组的位移数据删除了。

在消费者端，重平衡分为两个步骤：分别是加入组和等待领导者消费者（Leader Consumer）分配方案。这两个步骤分别对应两类特定的请求：JoinGroup 请求和 SyncGroup 请求。
1. 第一个发送 JoinGroup 请求的成员自动成为领导者,领导者消费者的任务是收集所有成员的订阅信息，然后根据这些信息，制定具体的分区消费分配方案。
2. 领导者向协调者发送 SyncGroup 请求，将刚刚做出的分配方案发给协调者。

##### JoinGroup 请求的处理过程
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-6.png)

##### SyncGroup 请求的处理流程
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-7.png)

### 位移提交
Consumer 需要向 Kafka 汇报自己的位移数据，这个汇报过程被称为提交位移。因为 Consumer 能够同时消费多个分区的数据，所以位移的提交实际上是在分区粒度上进行的，即Consumer 需要为分配给它的每个分区提交各自的位移数据。

位移提交的语义保障是由你来负责的，Kafka 只会“无脑”地接受你提交的位移。你对位移提交的管理直接影响了你的 Consumer 所能提供的消息语义保障。

从用户的角度来说，位移提交分为自动提交和手动提交；从 Consumer 端的角度来说，位移提交分为同步提交和异步提交。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/Kafka-3.jpeg)

我们先来说说自动提交和手动提交。所谓自动提交，就是指 Kafka Consumer 在后台默默地为你提交位移，作为用户的你完全不必操心这些事；自动提交位移的一个问题在于，它可能会出现重复消费。而手动提交，则是指你要自己提交位移，Kafka Consumer 压根不管。

反观手动提交位移，它的好处就在于更加灵活，你完全能够把控位移提交的时机和频率。但是，它也有一个缺陷，就是在调用 commitSync() 时，Consumer 程序会处于阻塞状态，直到远端的 Broker 返回提交结果，这个状态才会结束。



### 消费进度监控
1. 使用 Kafka 自带的命令行工具 kafka-consumer-groups 脚本。
2. 使用 Kafka Java Consumer API 编程。
3. 使用 Kafka 自带的 JMX 监控指标。