<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [solr原理](#solr%E5%8E%9F%E7%90%86)
  - [索引](#%E7%B4%A2%E5%BC%95)
  - [索引创建](#%E7%B4%A2%E5%BC%95%E5%88%9B%E5%BB%BA)
  - [搜索步骤](#%E6%90%9C%E7%B4%A2%E6%AD%A5%E9%AA%A4)

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
