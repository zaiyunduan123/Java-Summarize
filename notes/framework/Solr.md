<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [solr原理](#solr%E5%8E%9F%E7%90%86)
  - [索引](#%E7%B4%A2%E5%BC%95)
  - [索引创建](#%E7%B4%A2%E5%BC%95%E5%88%9B%E5%BB%BA)
  - [搜索步骤](#%E6%90%9C%E7%B4%A2%E6%AD%A5%E9%AA%A4)
- [空间搜索原理](#%E7%A9%BA%E9%97%B4%E6%90%9C%E7%B4%A2%E5%8E%9F%E7%90%86)
  - [GeoHash算法](#geohash%E7%AE%97%E6%B3%95)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## solr原理

### 索引
Solr/Lucene采用的是一种反向索引（倒排索引），所谓反向索引：就是从关键字到文档的映射过程，保存这种映射这种信息的索引称为反向索引

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/solr-1.jpg)
- 左边保存的是字符串序列
- 右边是字符串的文档（Document）编号链表，称为倒排表（Posting List）

### 索引创建
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/solr-2.jpg)

一、把原始文档交给分词组件(Tokenizer)
1. 将文档分成一个一个单独的单词
2. 去除标点符号
3. 去除停词(stop word)
    
二、词汇单元(Token)传给语言处理组件(Linguistic Processor)
1. 变为小写(Lowercase)。
2. 将单词缩减为词根形式，如”cars”到”car”等。这种操作称为：stemming。
3. 将单词转变为词根形式，如”drove”到”drive”等。这种操作称为：lemmatization

三、 得到的词(Term)传递给索引组件(Indexer)
1. 利用得到的词(Term)创建一个字典
2. 对字典按字母顺序排序
3. 合并相同的词(Term)成为文档倒排(Posting List)链表
![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/solr-3.jpg)

### 搜索步骤
1. 对查询内容进行词法分析、语法分析、语言处理
2. 搜索索引，得到符合语法树的文档集合
3. 根据查询语句与文档的相关性，对结果进行排序

## 空间搜索原理
空间搜索，又名Spatial Search(Spatial Query)，基于空间搜索技术，可以做到：
1. 对Point（经纬度）和其他的几何图形建索引
2. 根据距离排序
3. 根据矩形，圆形或者其他的几何形状过滤搜索结果

在Solr中，空间搜索主要基于GeoHash来实现：
### GeoHash算法
通过GeoHash算法，可以将经纬度的二维坐标变成一个可排序、可比较的的字符串编码。
在编码中的每个字符代表一个区域，并且前面的字符是后面字符的父区域。其算法的过程如下：
根据经纬度计算GeoHash二进制编码
地球纬度区间是[-90,90]， 如某纬度是39.92324，可以通过下面算法对39.92324进行逼近编码:
1. 区间[-90,90]进行二分为[-90,0),[0,90]，称为左右区间，可以确定39.92324属于右区间[0,90]，给标记为1；
2. 接着将区间[0,90]进行二分为 [0,45),[45,90]，可以确定39.92324属于左区间 [0,45)，给标记为0；
3. 递归上述过程39.92324总是属于某个区间[a,b]。随着每次迭代区间[a,b]总在缩小，并越来越逼近39.928167；
4. 如果给定的纬度（39.92324）属于左区间，则记录0，如果属于右区间则记录1，这样随着算法的进行会产生一个序列1011 1000 1100 0111 1001，序列的长度跟给定的区间划分次数有关。

![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/solr-4.jpg)

同理，地球经度区间是[-180,180]，对经度116.3906进行编码的过程也类似：


![](https://github.com/zaiyunduan123/Java-Interview/blob/master/image/solr-5.jpg)
