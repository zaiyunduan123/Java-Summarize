<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Linux 系统的顶层目录结构](#linux-%E7%B3%BB%E7%BB%9F%E7%9A%84%E9%A1%B6%E5%B1%82%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84)
- [linux内核map图](#linux%E5%86%85%E6%A0%B8map%E5%9B%BE)
- [Linux中软链接和硬链接的区别](#linux%E4%B8%AD%E8%BD%AF%E9%93%BE%E6%8E%A5%E5%92%8C%E7%A1%AC%E9%93%BE%E6%8E%A5%E7%9A%84%E5%8C%BA%E5%88%AB)
- [kill进程杀不掉的原因](#kill%E8%BF%9B%E7%A8%8B%E6%9D%80%E4%B8%8D%E6%8E%89%E7%9A%84%E5%8E%9F%E5%9B%A0)
- [swap分区的作用](#swap%E5%88%86%E5%8C%BA%E7%9A%84%E4%BD%9C%E7%94%A8)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Linux 系统的顶层目录结构
```java
/   根目录
├── bin 存放用户二进制文件
├── boot 存放内核引导配置文件
├── dev 存放设备文件
├── etc 存放系统配置文件
├── home 用户主目录
├── lib 动态共享库
├── lost+found 文件系统恢复时的恢复文件
├── media 可卸载存储介质挂载点
├── mnt 文件系统临时挂载点
├── opt 附加的应用程序包
├── proc 系统内存的映射目录，提供内核与进程信息
├── root root 用户主目录
├── sbin 存放系统二进制文件
├── srv 存放服务相关数据
├── sys sys 虚拟文件系统挂载点
├── tmp 存放临时文件
├── usr 存放用户应用程序
└── var 存放邮件、系统日志等变化文件
```
Linux 与其他类 UNIX 系统一样并不区分文件与目录:目录是记录了其他文件名的文件。使用命 令 mkdir 创建目录时，若期望创建的目录的名称与现有的文件名(或目录名)重复，则会创建失败。


## linux内核map图

http://makelinux.net/kernel_map/
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/os-2.png)

## Linux中软链接和硬链接的区别
我们知道文件都有文件名与数据，这在 Linux 上被分成两个部分:用户数据 (user data) 与元数据 (metadata)。
- 用户数据，即文件数据块 (data block)，数据块是记录文件真实内容的地方; 
- 元数据,则是文件的附加属性，如文件大小、创建时间、所有者等信息;

在 Linux 中，元数据中的 inode 号(inode 是文件元数据的一部分但其并不包含文件名，inode 号即索引节点号)才是 \文件的唯一标识而非文件名。文件名仅是为了方便人们的记忆和使用，系统或程序通过 inode 号寻找正确的文件数据块

为解决文件的共享使用，Linux 系统引入了两种链接:硬链接 (hard link) 与软链接(又称符号链接，即 soft link 或 symbolic link)。链接为 Linux 系统解决了文件的共享使用，还带来了隐藏文件路径、增加权限安全及节省存储等好处。若一个 inode 号对应多个文件名，则称这些文件为 硬链接。换言之，硬链接就是同一个文件使用了多个别名。


由于硬链接是有着相同 inode 号仅文件名不同的文件，因此硬链接存在以下几点特性:
- 文件有相同的 inode 及 data block;
- 只能对已存在的文件进行创建;
- 不能交叉文件系统进行硬链接的创建;
- 不能对目录进行创建，只可对文件创建;
- 删除一个硬链接文件并不影响其他有相同 inode 号的文件。

ln -s source     dist    #  建立软连接
ln    source     dist    #  建立硬连接

- 硬链接: 与普通文件没什么不同，inode 都指向同一个文件在硬盘中的区块。建立硬链接时，链接文件和被链接文件必须位于同一个文件系统中，并且不能建立指向目录的硬链接
- 软链接: 保存了其代表的文件的绝对路径，是另外一种文件，在硬盘上有独立的区块，访问时替换自身路径。(简单地理解为 Windows 中常见的快捷方式)



## kill进程杀不掉的原因 
1. 进程已经成为僵死进程，当它的父进程将它回收或将它的父进程kill掉即可在ps输出看不到了; 
2. 进程正处在内核状态中，Linux进程运行时分内核和用户两种状态，当进程进入内核状态后，会屏蔽所有信号，包括SIGKIL，所以这个时候kill -9也变得无效了


## swap分区的作用
Swap分区在系统的物理内存不够用的时候，把硬盘空间中的一部分空间释放出来，以供当前运行的程序使用。那些被释放的空间可能来自一些很长时间没有什么操作的程序，这些被释放的空间被临时保存到Swap分区中，等到那些程序要运行时，再从Swap分区中恢复保存的数据到内存中。
比如将内存想象成一个杯子，程序运行时产生的数据作为水放进杯子中，当我们运行的程序过多，或是处理的数据量过大时，杯子慢慢变满，导致乘不下了，此时如果有swap分区的话，就如同将过多的水倒入swap分区这个杯子中。

硬盘分区为主分区+扩展分区 所有的逻辑分区都在扩展分区内;

Swap分区，即交换区，系统在物理内存不够时，与Swap进行交换。 其实，Swap的调整对Linux服务器，特别是Web服务器的性能至关重要。通过调整Swap，有时可以越过系统性能瓶颈，节省系统升级费用。

建立Swap分区有两种方法
1. 新建磁盘分区作为swap分区
2. 用文件作为swap分区

Swap配置对性能的影响
1. 分配太多的Swap空间会浪费磁盘空间，而Swap空间太少，则系统会发生错误
2. Swap空间应大于或等于物理内存的大小，最小不应小于64M，通常Swap空间的大小应是物理内存的2-2.5倍
3. 如果有多个Swap交换区，Swap空间的分配会以轮流的方式操作于所有的Swap，这样会大大均衡IO的负载，加快Swap交换的速度。如果只有一个交换区，所有的交换操作会使交换区变得很忙，使系统大多数时间处于等待状态，效率很低。