1. JVM

   通俗的说，JVM就像一个翻译器。把Java语言编译后的.class文件翻译成系统能读懂的机器码，按照每个程序员的想表达的意思，翻译成系统能懂的一系列指令，让系统实现程序员想要表达的功能。

   所以这也让Java程序有了跨平台性，Java开发程序员只需要通过Java的语法编写Java程序，剩下的就是在各个平台上下载安装特定的JRE或者JDK就好了。

   JVM涉及的知识点有很多，例如：JDK、JRE、内存结构、生命周期、类加载器、垃圾回收等。

2. NIO

   NIO与原来的IO有同样的作用和目的，但是使用方法完全不同，NIO支持面向缓冲区，基于通道的IO操作。NIO能以更高效的方式进行文件的读写操作。

   NIO系统的核心在于：通道(Channel)和缓冲区(Buffer)。通道表示打开到IO设备（例如：文件，套接字）的连接。若需要使用NIO系统，需要获取用于连接IO设备的通道以及用于容纳数据的缓冲区。然后操作缓冲区对数据进行处理。

   简而言之：Channel负责传输，Buffer负责存储。

   缓冲区(Buffer)：一个用于特定基本数据类型的容器。由java.nio包定义，所有缓冲区都是Buffer抽象类的子类。

   Java NIO中的Buffer主要用于与NIO通道进行交互，数据时从通道读入缓冲区，从缓冲区写入通道中的。

3. 并发编程

   摩尔定律失效后，为了充分压榨CPU的处理能力，我们需要多线程、并发编程。

   除了基础的线程知识外，日常使用最多的就是JUC包的各种工具类，它们的用法和适用场景各不相同，它们的实现原理也能指导我们一些编写并发程序的思路。

   并发编程的另外大块知识就是线程的同步和安全，让我们知道怎么编写正确而且高性能的多线程程序。虽然知道很多，但是还是很容易出错。

4. Spring和ORM等框架

   各种框架是我们日常工作最直接运用的东西，很少人会需要从头开始造轮子了。

   引入Spring相当于为研发团队引入一种新的组织模式。

   引入Spring会自然而然地把项目拆解成多个分层。

   水平分层结构：

   如上图的展示层（Presentation Layer），业务层（Business Layer），服务层（Services Layer），持久化层（Persistentce Layer）和数据库层（Database Layer）。

   垂直分层结构：

   可以把整个业务系统按功能划分成多个竖着切的模块，如用户模块，订单模块，商品模块等。

   这样整个项目就被分割成颗粒度更小的组件。这些组件在水平的每一层中都是相似的，在垂直的一列中都是有相互的直接业务关系的。这样的好处是在开发过程中，可以通过这些小粒度的组件进行任务安排，任务进度的控制等等。所以引入Spring相当于为研发团队引入一种新相互协作的模式。

5. MySQL 数据库和 SQL

   基于我们大部分处理的是数据密集型的系统，所以数据库和SQL语句相关的知识就是每天都要用的了。一个SQL语句写得好不好，数据库的表设计合不合理，规模小的时候可能还没发现问题，但是当并发请求量大了，这方面的问题就会暴露得很明显，影响99%的响应时间。除了通用的数据库设计规范，表的索引和SQL性能也是息息相关，这几个点相辅相成，最后才能提高系统的整体性能

   SQL（Structured Query Language）结构化查询语言，是一种针对关系型数据库特殊标准化的编程语言，能够实现用户数据库查询和程序设计（不同数据库产品在自身产品设计上有一些不同，因此不能完全通用）。虽然SQL原意是结构化查询，但是实际上可以进行多种操作，针对Mysql数据库产品，SQL分为5个部分。

   DQL：Data Query Language，数据查询语言，用于查询和检索数据

   DML：Data Manipulation Language，数据操作语言，用于数据的写操作（增删改）

   DDL：Data Definition Language，数据定义语言，用于创建数据结构

   DCL：Data Control Language，数据控制语言，用于用户权限管理

   TPL：Transaction Process Language，事务处理语言，辅助DML进行事务操作（因此也归属于DML）

6. 分库分表

   主从结构解决了高可用，读扩展，但是单机容量不变，单机写性能无法解决。

   提升容量的方式：分库分表（垂直拆分、水平拆分），分布式，多个数据库，作为数据分片的集群提供服务。

   分库分表可降低单个节点的写压力，提升整个系统的数据容量上限。

   分库分表主要是解决数据库的容量和性能问题，当数据量比较大了以后，什么操作都会受到影响。数据库访问大部分是文件IO，受限于单个机器的IO上限，需要通过分库分表来把压力分摊到各个不同的机器上。一方面单个数据库实例的数据量少了，提高了性能；另一方面，多个实例累加的IO上限提高了，数据访问的吞吐量就得以提高。虽然提高了性能，但是分库分表也会带来一定的复杂性，需要引入数据库中间件框架之类的

7. RPC和微服务

   RPC是远程过程调用（Remote Procedure Call）的缩写形式。

   RPC就是“像调用本地方法一样调用远程方法”。

   RPC框架是为了解决远程调用产生，系统拆得越细，互相通信的需要就越多，对RPC框架和相关技术的依赖就越大。RPC框架的发展成熟，为微服务的出现也奠定了一定基础。微服务更多的是业务上的一种划分，它并不是因为技术的需要而出现的。系统拆细成多个微服务，服务治理引入多种不同的技术要求：服务注册、服务发现、服务调用、服务降级等等。

8. 分布式缓存

   缓存数据是对原始数据的一份拷贝，本质上是为了实现更快地数据访问。

   对于修改之类的操作，缓存是没有意义的。当数据获取的代价比较大，且数据变化频率低，读写比高时就很适合缓存。

   缓存也分近端和远端两种，近端的成本低，使用开销小，但是无法实现共用；远端需要访问网络，但是数据能共用，常说的分布式缓存就是指远端缓存。

   远程缓存目前使用最广的是redis，所以分布式缓存大部分内容就是redis的相关知识

9. 分布式消息队列

   优点：

   系统解耦、异步通信、削峰平谷，可靠通信。

   缺点：

   系统可用性降低

   系统引入的外部依赖越多，越容易挂掉。本来你就是 A 系统调用 BCD 三个系统的接口就好了，人 ABCD 四个系统好好的，没啥问题，你偏加个 MQ 进来，万一 MQ 挂了咋整，MQ 一挂，整套系统崩溃的，你不就完了？如何保证消息队列的高可用，可以点击这里查看。

   系统复杂度提高

   硬生生加个 MQ 进来，你怎么保证消息没有重复消费？怎么处理消息丢失的情况？怎么保证消息传递的顺序性？头大头大，问题一大堆，痛苦不已。

   一致性问题

   A 系统处理完了直接返回成功了，人都以为你这个请求就成功了；但是问题是，要是 BCD 三个系统那里，BD 两个系统写库成功了，结果 C 系统写库失败了，咋整？你这数据就不一致了。


![Alt text](https://github.com/rainism0329/JavaAdvancedCampHomework/blob/main/Week_15/image/brainMap.png)




