## 堆内存：

### 何为堆内存：

堆内存是所有线程共用的内存空间。

通过new关键字创建对象都会使用堆内存。对象，对象成员与类定义，静态变量都存储在堆上。

JVM将Heap内存分为年轻代（Young Generation）和老年代（Old Generation，也叫Tenured）两部分。年轻代还划分为三个内存池，新生代（Eden Space）和存活区（Survivor Space），在大部分GC算法中有两个存活区（S0，S1），**S0和S1总有一个是空的**，但一般较小，不浪费多少空间。

### 堆内存特点：

·    它是线程共享的，堆中对象都需要考虑线程安全的问题

·    有垃圾回收机制

### 堆内存设置参数：

-Xms：设置堆内存初始值大小。如Xms4g

-Xmx：设置堆内存最大值大小。如Xmx4g

-Xmn：设置年轻代大小。等价于-XX:NewSize。使用G1垃圾回收器不应该设置该选项。官方建议设置-Xmx的1/2～1/4。

-XX:MaxPermSize=size：设置永久代最大空间大小，1.7之前使用。Java8默认允许Meta空间无限大，此参数无效。

-XX:MaxMetaspaceSize=size：Java8默认不限制Meta空间，一般不允许设置该选项。

-XX:MaxDirectMemorySize=size：系统可以使用的最大堆外内存，这个参数跟

“-Dsun.nio.MaxDirectMemorySize”效果相同。

-Xss：设置每个线程栈的字节数，影响栈道深度。例如-Xss1m指定线程栈为1MB，与-XX:ThreadStackSize=1m等价

## 各种GC：

### 串行垃圾回收器SerialGC

-XX:+UseSerialGC配置串行GC

串行GC对年轻代使用mark-copy（标记-复制）算法，对老年代使用mark-sweep-compact（标记-清除-整理）算法。

两者都是单线程的垃圾收集器，不能进行并行处理，所以会触发全线暂停（SWT），停止所有的应用线程。

因此这种GC算法不能充分利用多核CPU。因为JVM在垃圾收集时只能使用单个核心。CPU利用率高，暂停时间长，动不动就卡死。只适合几百MB堆内存的JVM，而且时单核CPU

-XX:+useParNewGC：改进版本的Serial GC，可以配合CMS使用。

### 并行垃圾回收器ParallelGC

-XX:+UseParallelGC

-XX:+UseParallelOldGC

在JDK 6，7，8中并行GC都是默认GC。JDK 9以后默认为G1。

年轻代和老年代的老年代的垃圾回收都会触发STW时间。

在年轻代使用mark-copy（标记-复制）算法，在老年代使用mark-sweep-compact（标记-清除-整理）算法。

-XX:ParallelGCThreads=N：来指定GC线程数，默认为CPU核心数。

并行GC垃圾收集器适用于多核服务器，主要目标时增加吞吐量。因为对系统资源的有效利用，能达到更高的吞吐量。

·   在GC期间，所有的CPU内核都在并行清理垃圾，所以总暂停时间更短

·   在两次GC周期的间隔期，没有GC线程在运行，所以不会消耗任何系统资源

### 响应时间优先CMSGC(Mostly Concurrent Mark and Sweep GC)

-XX:+UseConcMarkSweepGC

CMSGC对年轻代使用并行STW方式的mark-copy（标记-复制）算法，对老年代主要使用并发mark-sweep（标记-清除）算法。

CMSGC的设计目标是避免在老年代垃圾收集时出现长时间的停顿，主要通过两种手段达成此目标：

·   不对老年代进行整理，而是使用空闲列表（free-lists）来管理内存空间的回收。

·   在mark-and-sweep（标记-清除）阶段的大部分内容和应用线程一起并发执行。也就是说，在这些阶段并没有明显的应用线程暂停。但是值得注意的是，它仍然会和应用线程争抢CPU。默认情况下，CMS使用的并发线程数等于CPU核心数的1/4.

如果服务器是多核CPU，并且主要调优目标是降低GC停顿导致的系统延迟，那么使用CMS是明智的选择。进行老年代的并发（Concurrent）回收时，可能会伴随着多次年轻代的minor GC。

### G1垃圾回收器(Garbage First)

-XX:+UseG1GC

-XX:MaxGCPauseMillis=200

在JDK9中是默认的垃圾回收器，JDK9废弃了CMS垃圾回收器

G1全称Garbage-First，意为垃圾优先，哪一块的垃圾最多，就优先清理它。

G1 GC最重要的设计目标时：将STW停顿的时间和分布变成可预期且配置的。

G1 GC堆不再分成年轻代和老年代，而是划分为多个（通常为2048个）小块Region。每个小块，可能一会被定义成Eden区，一会被定义成Survivor区或Old区。

这样划分之后，使得G1 GC不必每次都去手机整个堆空间，而是以增量的方式来进行处理：每次只处理一部分内存快，称为此次GC堆回收集（collection set）。

每次GC暂停都会手机所有年轻代的内存快，但一般只包含部分老年代的内存快。

G1的另一项创新是，在并发阶段估算每个小堆块存活对象的总数。

构建回收集的原则是：垃圾最多的小块会被优先收集，这也是G1名称的由来。

### ZGC

-XX:+UnlockExpermentalVMOptions

-XX:+UseZGC

-Xmx16g

ZGC最主要的特点包括：

1) GC最大停顿时间不超过10ms

2) 堆内存支持范围广，小到几百MB到堆空间，大到4TB的超大堆内存（JDK13升至16TB）

3) 与G1相比，应用吞吐量下降不超过15%

4) 当前只支持Linux/x64位平台，JDK15后支持MacOS和Windows

### Shennandoah GC

-XX:+UnlockExperimentalVMOptions

-XX:+UseShenandoahGC

-Xmx16gShenandoah GC立项比ZGC更早，设计为GC线程与应用线程并发执行的方式，通过实现垃圾回收过程的并发处理，改善停顿时间，使得GC执行线程能够在业务处理线程运行过程中进行堆压缩、标记和整理，从而消除绝大部分的暂停时间。

Shenandoah团队对外宣称Shenandoah GC的暂停时间与堆大小无关，无论是200MB还是200GB的堆内存，都可以保障具有很低的暂停时间（注意：并不像ZGC那样保证暂停时间在10ms以内）。

### GC总结：

1. 串行GC（Serial GC）：但线程执行，应用需要暂停；

2. 并行GC（ParNew，Parallel Scavenge，Parallel Old）：多线程并行地执行垃圾回收，关注于高吞吐量；

3. CMS GC（Concurrent Mark-Sweep）：多线程并发标记和清除，关注于降低延迟；

4. G1 GC（Garbage First）：通过划分多个内存区域做增量整理和回收，进一步降低延迟；

5. ZGC（Z Garbage Collector）：通过着色和度屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展性；

6. Epsilon GC：实验性的GC，供性能分析使用；

7. Shenandoah GC：G1的改进版本，跟ZGC类似。