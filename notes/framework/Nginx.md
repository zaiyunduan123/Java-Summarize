<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Nginx是什么？](#nginx%E6%98%AF%E4%BB%80%E4%B9%88)
- [我们为什么选择Nginx？](#%E6%88%91%E4%BB%AC%E4%B8%BA%E4%BB%80%E4%B9%88%E9%80%89%E6%8B%A9nginx)
  - [1. IO多路复用epoll](#1-io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8epoll)
  - [2. 轻量级](#2-%E8%BD%BB%E9%87%8F%E7%BA%A7)
  - [3. CPU亲和](#3-cpu%E4%BA%B2%E5%92%8C)
  - [4. sendfile](#4-sendfile)
- [模块](#%E6%A8%A1%E5%9D%97)
- [使用场景](#%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF)
  - [1、静态资源WEB服务](#1%E9%9D%99%E6%80%81%E8%B5%84%E6%BA%90web%E6%9C%8D%E5%8A%A1)
  - [2、浏览器缓存](#2%E6%B5%8F%E8%A7%88%E5%99%A8%E7%BC%93%E5%AD%98)
  - [3、跨站访问](#3%E8%B7%A8%E7%AB%99%E8%AE%BF%E9%97%AE)
  - [4、防盗链](#4%E9%98%B2%E7%9B%97%E9%93%BE)
    - [基于http_refer防盗链配置模块](#%E5%9F%BA%E4%BA%8Ehttp_refer%E9%98%B2%E7%9B%97%E9%93%BE%E9%85%8D%E7%BD%AE%E6%A8%A1%E5%9D%97)
    - [valid_referers](#valid_referers)
  - [5、HTTP代理服务](#5http%E4%BB%A3%E7%90%86%E6%9C%8D%E5%8A%A1)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Nginx是什么？
Nginx是一个开源且高性能、可靠的HTTP中间件、代理服务

其他的HTTP服务：
1. HTTPD-Apache基金会
2. IIS-微软
3. GWS-Google(不对外开放)

## 我们为什么选择Nginx？
### 1. IO多路复用epoll
### 2. 轻量级
- 功能模块少 - Nginx仅保留了HTTP需要的模块，其他都用插件的方式，后天添加
- 代码模块化 - 更适合二次开发，如阿里巴巴Tengine
### 3. CPU亲和
把CPU核心和Nginx工作进程绑定，把每个worker进程固定在一个CPU上执行，减少切换CPU的cache miss，从而提高性能。
### 4. sendfile
sendfile: 设置为on表示启动高效传输文件的模式。

sendfile可以让Nginx在传输文件时直接在磁盘和tcp socket之间传输数据。如果这个参数不开启，会先在用户空间（Nginx进程空间）申请一个buffer，用read函数把数据从磁盘读到cache，再从cache读取到用户空间的buffer，再用write函数把数据从用户空间的buffer写入到内核的buffer，最后到tcp socket。开启这个参数后可以让数据不用经过用户buffer。

## 模块

Nginx由内核和模块组成，其中，内核的设计非常微小和简洁，完成的工作也非常简单，仅仅通过查找配置文件将客户端请求映射到一个location block（location是Nginx配置中的一个指令，用于URL匹配），而在这个location中所配置的每个指令将会启动不同的模块去完成相应的工作。

Nginx的模块从结构上分为核心模块、基础模块和第三方模块：
1. 核心模块：HTTP模块、EVENT模块和MAIL模块
2. 基础模块：HTTP Access模块、HTTP FastCGI模块、HTTP Proxy模块和HTTP Rewrite模块
3. 第三方模块：HTTP Upstream Request Hash模块、Notice模块和HTTP Access Key模块

用户根据自己的需要开发的模块都属于第三方模块。正是有了这么多模块的支撑，Nginx的功能才会如此强大。

Nginx的模块从功能上分为如下三类：
- Handlers（处理器模块）。此类模块直接处理请求，并进行输出内容和修改headers信息等操作。Handlers处理器模块一般只能有一个。
- Filters （过滤器模块）。此类模块主要对其他处理器模块输出的内容进行修改操作，最后由Nginx输出。
- Proxies （代理类模块）。此类模块是Nginx的HTTP Upstream之类的模块，这些模块主要与后端一些服务比如FastCGI等进行交互，实现服务代理和负载均衡等功能。


Nginx内置模块
- http_auth_basic_module HTTP基本认证
- http_stub_status_module 状态信息
- http_gzip_module 压缩资源
- http_gzip_static_module 支持.gz资源
- http_sub_module 字符串替换
- http_addition_module 追加内容
- http_realip_module 获取实际IP
- http_image_filter_module 图片处理
- http_geoip_module 支持GeoIP
- http_auth_request_module 第三方auth支持
- http_flv_module 流媒体点播

## 使用场景
### 1、静态资源WEB服务
nginx静态资源配置
 ```lombok.config
配置域：http、server、location
#文件高速读取
http {
     sendfile   on;
}
#在 sendfile 开启的情况下，开启 tcp_nopush 提高网络包传输效率
#tcp_nopush 将文件一次性一起传输给客户端，就好像你有十个包裹，快递员一次送一个，来回十趟，开启后，快递员讲等待你十个包裹都派件，一趟一起送给你
http {
     sendfile   on;
     tcp_nopush on;
}
#tcp_nodelay 开启实时传输，传输方式与 tcp_nopush 相反，追求实时性，但是它只有在长连接下才生效
http {
     sendfile   on;
     tcp_nopush on;
     tcp_nodelay on;
}

#将访问的文件压缩传输 （减少文件资源大小，提高传输速度）
#当访问内容以gif或jpg结尾的资源时
location ~ .*\.(gif|jpg)$ {
    gzip on; #开启
    gzip_http_version 1.1; #服务器传输版本
    gzip_comp_level 2; #压缩比，越高压缩越多，压缩越高可能会消耗服务器性能
    gzip_types   text/plain application/javascript application/x-javascript text/javascript text/css application/xml application/xml+rss image/jpeg image/gif image/png;     #压缩文件类型
    root /opt/app/code;     #对应目录（去该目录下寻找对应文件）
}

#直接访问已压缩文件
#当访问路径以download开头时，如www.baidu.com/download/test.img
#去/opt/app/code目录下寻找test.img.gz文件，返回到前端时已是可以浏览的img文件
location ~ load^/download {
    gzip_static on #开启;
    tcp_nopush on;
    root /opt/app/code;
}
```
### 2、浏览器缓存
HTTP协议定义的缓存机制（如：Expires; Cache-control等 ）
减少服务端的消耗，降低延迟

1.浏览器无缓存

浏览器请求 -> 无缓存 -> 请求WEB服务器 -> 请求相应 -> 呈现

在呈现阶段会根据缓存的设置在浏览器中生成缓存

2.浏览器有缓存

浏览器请求 -> 有缓存 -> 校验本地缓存时间是否过期 -> 没有过期 -> 呈现

若过期从新请求WEB服务器

3.语法配置
 ```lombok.config
location ~ .*\.(html|htm)$ {
    expires 12h;    #缓存12小时
}
```

服务器响应静态文件时，请求头信息会带上 etag 和 last_modified_since 2个标签值，浏览器下次去请求时，头信息发送这两个标签，服务器检测文件有没有发生变化，如无,直接头信息返 etag 和last_modified_since，状态码为 304 ，浏览器知道内容无改变,于是直接调用本地缓存，这个过程也请求了服务，但是传着的内容极少
### 3、跨站访问

为什么浏览器禁止跨站访问？
- 不安全，容易出现CSRF攻击

为什么还需要nginx打开跨域访问？
- nginx配置文件通过使用add_header指令来设置response header。使用ngx_http_headers_module中的add_header 指令，在响应头中添加允许跨域。

开发nginx跨站访问设置
```lombok.config
location ~ .*\.(html|htm)$ {
     add_header Access-Control-Allow-Origin *;
     add_header Access-Control-Allow-Methods GET,POST,PUT,DELETE,OPTIONS;
     #Access-Control-Allow-Credentials true #允许cookie跨域
}
```

在响应中指定 Access-Control-Allow-Credentials 为 true 时，Access-Control-Allow-Origin 不能指定为 *，需要设置为允许访问的域名，比如http://abc.com

### 4、防盗链
目的：防止服务器内的静态资源被其他网站所套用


防盗链设置思路：区别哪些请求是非正常的用户请求

#### 基于http_refer防盗链配置模块
当浏览器向web服务器发送请求的时候，一般会在头信息中带上Referer字段，告诉服务器我是从哪个页面链接过来的，服务器基此可以获得一些信息用于处理。基于头信息的Referer字段，nginx识别指定的Referer，在客户端请求时，通过匹配referer头域与配置，对于指定放行，对于其他referer视为盗链。

nginx模块ngx_http_referer_module通常用于阻挡来源非法的域名请。

#### valid_referers
valid_referers配置项是属于ngx_http_referer_module模块传送门{:target="_blank"}

>Syntax:	valid_referers none | blocked | server_names | string ...;
Default: —
Context: server, location


示例配置

```lombok.config
location ~ .*\.(jpg|gif|png)$ {
	root  /opt/app/code/images;
	gzip on;
	gzip_http_version 1.1;
	gzip_comp_level 2;
	gzip_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png;

	valid_referers none blocked 116.62.113.218 walidream.com;
	if ($invalid_referer) {
		return 403;
	}	
}

```
允许116.62.113.218 walidream.com访问服务器图片


因为HTTPReferer头信息是可以通过程序来伪装生成的，所以通过Referer信息防盗链并非100%可靠，但是，它能够限制大部分的盗链

### 5、HTTP代理服务

代理区别
- 正向代理代理的对象是客户端
- 反向代理代理的对象是服务端

反向代理
```lombok.config
语法：proxy_pass URL
默认：——
位置：loaction

#代理端口
#场景：服务器80端口开放，8080端口对外关闭，客户端需要访问到8080
#在nginx中配置proxy_pass代理转发时，如果在proxy_pass后面的url加/，表示绝对根路径；如果没有/，表示相对路径，把匹配的路径部分也给代理走
server {
    listen 80;
    location / {
        proxy_pass http://127.0.0.1:8080/;
        proxy_redirect default;

        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr; #获取客户端真实IP

        proxy_connect_timeout 30; #超时时间
        proxy_send_timeout 60;
        proxy_read_timeout 60;

        proxy_buffer_size 32k;
        proxy_buffering on; #开启缓冲区,减少磁盘io
        proxy_buffers 4 128k;
        proxy_busy_buffers_size 256k;
        proxy_max_temp_file_size 256k; #当超过内存允许储蓄大小，存到文件
    }
}
```


