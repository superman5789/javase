
参考：https://zhuanlan.zhihu.com/p/28216267

1、我们在比较两个Integer对象的值时，无论是怎么声明的，都一定要使用equals去比较，不能用==，
在Java中没有重载操作符这一说，特别是从其它语言转到Java的童鞋们要注意，
Integer这个类的内部访问，这个类在初始化的时候，会去加载JVM的配置，
如果有值，就用配置的值初始化缓存数组，否则就缓存-128到127之间的值；

2、我们在比较两个String对象内容时，无论是怎么声明的，都一定要使用equals去比较，不能用==，
在Java中没有重载操作符这一说，特别是从其它语言转到Java的童鞋们要注意
String s1 = "100" 时，会先看常量池里有没有字符串刚好是“100”这个对象，
如果没有，在常量池里创建初始化该对象，并把引用指向它
当执行到String s2 = "100" 时，发现常量池已经有了100这个值，于是不再在常量池中创建这个对象，而是把引用直接指向了该对象
这时候我们打印System.out.println(s1 == s2)时，由于==是判断两个对象是否指向同一个引用，所以这儿打印出来的就应该是true。
继续执行到Strings3 = new String("100") 这时候我们加了一个new关键字，这个关键字呢就是告诉JVM，你直接在堆内存里给我开辟一块新的内存

3、在引用类型中，"=="是比较两个引用是否指向堆内存里的同一个地址（同一个对象），
而equals是一个普通的方法，该方法返回的结果依赖于自身的实现
Integer这个类， equals的实现如下：
会判断传入的lon这个对象是否是Integer类型，这里的lon是Long类型，所以打印出来的结果当然是false了。

4、Java中一共有四类八种基本数据类型：
整型：byte、short、int、long
浮点型：float、double
字符型：char
逻辑型：boolean

除掉这四类八种基本类型，其它的都是对象，也就是引用类型，包括数组。

5、当==两边是基本数据类型时，==于比较的是两边的两个值是否相等，
当==两边是引用类型时比较的是两个内存地址，
也可以看成是看这两个引用是否指向堆内存里的同一块地址

6、ArrayList 
默认数组大小10
第一个元素，在堆内存中占了10个位置，好浪费呀，没办法，你要享受ArrayList的便利与丰富的API，就得牺牲一下空间作为代价。
数组的扩容，一般是oldCapacity + (oldCapacity >> 1)，相当于扩容1.5倍。

当我们知道元素的位置，一步到位就能访问到该元素，这个时间为K，时间复杂度用大O表示法标记为O(1)，省略了K。
而在数组中查找某元素，我们并不知道这个元素在数组的什么位置，假设数组的长度为n，
有可能该元素刚好在数组的下标为0的位置（第一个位置）循环1次就匹配到了，时间复杂度为O(1)。
也有可能在数组下标为n-1的位置（最后一个位置）我们要循环n次才能匹配到该值，时间复杂度为O(n)，
按照概率计算下来平均是n/2，即平均时间复杂度为O(n/2)，
但我们不应该只考虑平均值，我们要考虑最坏的情况，
即假设每次匹配的元素都在数组的最后一位，因为最坏情况是一种运行时间保证，运行时间不会再长了，
如果我们没特别指定，我们提到的运行时间都是最坏情况的运行时间，
即在数组中查找某元素，时间复杂度为O(n);

在长度为n数组中：
直接通过下标去访问元素，时间复杂度为O(1)。
需要循环查找元素的时候，时间复杂度为O(n)。

在ArrayList中，底层数组存/取元素效率非常的高(get/set)，时间复杂度是O(1)，
而查找，插入和删除元素效率似乎不太高，时间复杂度为O(n)。
当我们ArrayLIst里有大量数据时，这时候去频繁插入/删除元素会触发底层数组频繁拷贝，效率不高，
还会造成内存空间的浪费，这个问题在另一个类：LinkedList里有解决方案
查找元素效率不高，在HashMap里有解决方案

7、LinkedList
LinkedList是基于双向链表来实现的
三个成员变量，size、first、last
很简单，没有了底层数组，新增加了一个Node对象，
每个Node对象都持有next引用(下一个)和prev引用(上一个)，其实就是之前的双向链表
删除调用unlink()方法
ArrayList删除元素后，底层数组要往前复制一格，ArrayList底层数组删除元素时间复杂度为Ｏ(n)。
再来看LinkedList，LinkedList底层链表删除元素只是简单的修改了一下引用地址，时间复杂度为O(1)

8、vector
Vector是线程安全的，ArrayList不是线程安全的。
ArrayList在底层数组不够用时在原来的基础上扩展0.5倍，Vector是扩展1倍。
无一例外，只要是关键性的操作，方法前面都加了synchronized关键字，来保证线程的安全性。
当执行synchronized修饰的方法前，系统会对该方法加一把锁，方法执行完成后释放锁，
加锁和释放锁的这个过程，在系统中是有开销的，因此，在单线程的环境中，Vector效率要差很多。
（多线程环境不允许用ArrayList，需要做处理）。

9、这里先简单的说一下这几个Map，
TreeMap是基于树的实现，
HashMap，HashTable，ConcurrentHashMap是基于hash表的实现，
下文我们会介绍hash表。
HashTable和HashMap在代码实现上，基本上是一样的，
和Vector与Arraylist的区别大体上差不多，一个是线程安全的，一个非线程安全，
ConcurrentHashMap也是线程安全的，但性能比HashTable好很多，
HashTable是锁整个Map对象，而ConcurrentHashMap是锁Map的部分结构

10、HashMap
当放入第一个元素时，会触发resize方法，当我们放入第一个元素时，如果底层数组还是null，系统会初始化一个长度为16的Node数组，像极了ArrayList的初始化
1 << 4 其实就是相当于16
这个hash值是字符串“张三”这个对象的hashCode方法与hashMap提供hash()方法共同计算出来的结果，
其中n是数组的长度，目前数组长度为16，
不管这个hash的值是多少，经过(n - 1) & hash计算出来的i 的值一定在n-1之间。
刚好是底层数组的合法下标，用i这个下标值去底层数组里去取值，
如果为null，创建一个Node放到数组下标为i的位置。
这里的“张三”计算出来的i的值为2，继续画图
继续往里添加“孙七”，通过(n - 1) & hash计算“孙七”这个key时计算出来的下标值是1，而数组下标1这个位置目前已经被“李四”给占了，产生了冲突
p.next = newNode(hash, key, value, null);
也就是说new一个新的Node对象并把当前Node的next引用指向该对象，也就是说原来该位置上只有一个元素对象，现在转成了单向链表，继续画图

if (binCount >= TREEIFY_THRESHOLD - 1) //当binCount>=TREEIFY_THRESHOLD-1
      treeifyBin(tab, hash);//把链表转化为红黑树
当链表长度到8时，将链表转化为红黑树来处理
在JDK1.8中加入了红黑树是为了防止哈希表碰撞攻击，当链表链长度为8时，及时转成红黑树，提高map的效率

HashMap的最底层是数组来实现的，数组里的元素可能为null，也有可能是单个对象，还有可能是单向链表或是红黑树。
文中的resize在底层数组为null的时候会初始化一个数组，不为null的情况下会去扩容底层数组，并会重排底层数组里的元素。

结合上一篇内容，做一个总结，在hashMap中放入（put）元素，有以下重要步骤：
1、计算key的hash值，算出元素在底层数组中的下标位置。
2、通过下标位置定位到底层数组里的元素（也有可能是链表也有可能是树）。
3、取到元素，判断放入元素的key是否==或equals当前位置的key，成立则替换value值，返回旧值。
4、如果是树，循环树中的节点，判断放入元素的key是否==或equals节点的key，成立则替换树里的value，并返回旧值，不成立就添加到树里。
5、否则就顺着元素的链表结构循环节点，判断放入元素的key是否==或equals节点的key，成立则替换链表里value，并返回旧值，找不到就添加到链表的最后。

判断放入HashMap中的元素要不要替换当前节点的元素，key满足以下两个条件即可替换：
1、hash值相等。
2、==或equals的结果为true。

扩容的方式是：
新建一个长度为之前数组2倍的新的数组，然后将当前的Entry数组中的元素全部传输过去，扩容后的新数组长度为之前的2倍，所以扩容相对来说是个耗资源的操作。

扩容的触发条件：
阈值 = 数组默认的长度 x 负载因子（阈值=16x0.75=12）
如果负载因子为0.5甚至更低的可能的话，最后得到的临时阈值明显会很小，这样的情况就会造成分配的内存的浪费，存在多余的没用的内存空间，也不满足了哈希表均匀分布的情况。
如果负载因子达到了1的情况，也就是Entry数组存满了才发生扩容，这样会出现大量的哈希冲突的情况，出现链表过长，因此造成get查询数据的效率。
因此选择了0.5~1的折中数也就是0.75，均衡解决了上面出现的情况。

HashMap和双向链表合二为一即是LinkedHashMap

11、HashSet
boolean putFlag = map.put(e,PRESENT);
return putFlag;
原来就是调用底层HashMap的put方法，把"张三"作为key，PRESENT作为value放在hashMap里，
讲HashMap的时候讲过了，如果put时key重了，会返回被覆盖的value值（oldValue），否则返回null，
这儿的HashSet又给包装了一下，如果key没有重（oldValue == null），就返回true，否则返回false。
继续看这个PRESENT是什么鬼,很简单就是new了一个Object

HashSet就是利用HashMap来实现的。

这时候我们大胆的猜测一下，TreeSet是不是也是用TreeMap来实现的呢？迫不及待打开TreeSet的源码
也就是说，TreeSet底层实现也是利用TreeMap来实现的

HashSet底层声明了一个HashMap，HashSet做了一层包装，操作HashSet里的元素时其实是在操作HashMap里的元素。
TreeSet底层也是声明了一个TreeMap，操作TreeSet里的元素其实是操作TreeMap里的元素。

12、CurrentHashMap
jdk7
ConcurrentHashMap的get操作跟HashMap类似，
只是ConcurrentHashMap第一次需要经过一次hash定位到Segment的位置，
然后再hash定位到指定的HashEntry，遍历该HashEntry下的链表进行对比，成功就返回，不成功就返回null
jdk8
JDK1.8的实现已经摒弃了Segment的概念，而是直接用Node数组+链表+红黑树的数据结构来实现，
并发控制使用Synchronized和CAS来操作，整个看起来就像是优化过且线程安全的HashMap，
虽然在JDK1.8中还能看到Segment的数据结构，但是已经简化了属性，只是为了兼容旧版本.

其实可以看出JDK1.8版本的ConcurrentHashMap的数据结构已经接近HashMap，
相对而言，ConcurrentHashMap只是增加了同步的操作来控制并发，
从JDK1.7版本的ReentrantLock+Segment+HashEntry，
到JDK1.8版本中synchronized+CAS+HashEntry+红黑树

13、在BIO中，等待客户端发数据这个过程是阻塞的，
这样就造成了一个线程只能处理一个请求的情况，
而机器能支持的最大线程数是有限的，BIO不能支持高并发的原因就在这了。
   
但在NIO中，当一个Socket建立好之后，
Thread并不会阻塞去接受这个Socket，
而是将这个请求交给Selector，
Selector会不断的去遍历所有的Socket，一旦有一个Socket建立完成，他会通知Thread，
然后Thread处理完数据再返回给客户端——这个过程是不阻塞的，
这样就能让一个Thread处理更多的请求了。

##### 类加载的过程
java编译器将 .java 文件编译成扩展名为 .class 的文件。
.class 文件中保存着java转换后，虚拟机将要执行的指令。
当需要某个类的时候，java虚拟机会加载 .class 文件，并创建对应的class对象，将class文件加载到虚拟机的内存，这个过程被称为类的加载。

类加载器：
启动加载器(负责将JAVA_HOME/lib下面的核心类库加载到内存中)
扩展类加载器（负责将JAVA_HOME/lib/ext下面的核心类库加载到内存中）
系统类加载器(负责将用户类路径(java -classpath第三方类库的路径加载到内存中)

加载：ClassLoader通过一个类的完全限定名查找此类字节码文件，并利用字节码文件创建一个class对象。
验证：确保class文件不会危害虚拟机自身的安全，包括四种验证：文件格式的验证，元数据的验证，字节码验证，符号引用验证。
准备：为类变量（static变量）分配内存并且设置初始值，（如static int i = 5 这里只是将 i 赋值为0，在初始化的阶段再把 i 赋值为5)，
这里不包含final修饰的static ，因为final在编译的时候就已经分配了。这里不会为实例变量分配初始化，类变量会分配在方法区中，实例变量会随着对象分配到Java堆中
解析：把常量池中的符号引用替换成直接引用

类记载器的任务是根据类的全限定名来读取此类的二进制字节流到 JVM 中，然后转换成一个与目标类对象的java.lang.Class 对象的实例

##### 双亲委派模式
每个儿子都很懒，遇到类加载的活都给它爸爸干，直到爸爸说我也做不来的时候，儿子才会想办法自己去加载
优势： 避免类的重复加载 、安全

14、CountDownLatch：减计数、计数为0释放等待线程、不可重复到0为止了
CyclicBarrier：加计数、计数达到指定值释放等待线程、可重复(计数达到指定值时会重置为0)

15、ThreadLocal
#### jdk8之前，是把当前线程当作key，存储在ThreadLocal里边
#### jdk8之后，是在Thread里边声明了一个变量ThreadLocalMap(ThreadLocal的内部类)
#### 好处：不会产生大的ThreadLocal

Thread-->ThreadLocal-->ThreadLocalMap-->Entry(key:ThreadLocal,value:变量)

|  jdk   |  Thread   | 线程变量  | ThreadLocal对象数 |
|  ----  |  ----  | ----  | ----  |
| jdk8之前  | 1000  | 2 | 每个ThreadLocal有1000个Thread对象引用 |
| jdk8之后  | 1000  | 2 | 每个Thread有两个ThreadLocal引用 |

16、AtomicReference
compareAndSet方法，它可以将引用与预期值（引用）进行比较，如果它们相等，则在AtomicReference对象内设置一个新的引用
AtomicReference 主要是依赖于 sun.misc.Unsafe 提供的一些 native 方法保证操作的原子性。

17、雪花算法(按时间顺序升序排列的；且可以保证在分布式高并发环境下生成的ID不会发生重复)，64位（2的64次方，长度18~19）
符号位：最高位是符号位，为保证生成的ID是正数，故不使用，其值恒为0
时间戳：41位长度的时间戳，69年不重复
数据中心ID：5位
机器ID：5位
序列号：12位的计数序列号，支持每个节点每毫秒产生4096个ID序号

18、服务器变慢
#### 整机情况 : top 
    看cpu /memory 
    看load average: 1.6 ,0.9 ,0.4  1分钟、5分钟、15分钟系统的平均负载值

##### 查看cpu: vmstat -n 2 3    每两秒采样一次，采样三次
 vmstat -n 2 3
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 1  0      0 778108      0 4461684    0    0     0    11    0    0  1  0 99  0  0
 0  0      0 778076      0 4461720    0    0     0     0 1042 1124  0  0 99  0  0
 0  0      0 778012      0 4461784    0    0     0  2010 1150 1228  0  0 99  0  0
procs 
    r:运行等待cpu进程数
    b:等待资源进程数，比如磁盘IO、网络IO
cpu
    us:用户进程消耗cpu时间百分比，长期大于50%优化程序
    sy:内核进程消耗cpu时间百分比
    us + sy:大于80%，说明可能cpu不足
    id:处于空闲cpu时间百分比
    wa:系统等待cpu时间百分比
    st:虚拟机等待cpu时间百分比

pidstat 进程编号： 每个进程cpu用量分解信息
pidstat -p 59223 -r 5  5秒采样一次cpu信息
Linux 3.10.0-693.el7.x86_64 (test-node03-com) 	08/05/2021 	_x86_64_	(4 CPU)
04:34:42 PM   UID       PID    %usr %system  %guest    %CPU   CPU  Command
04:34:42 PM     0     59223    0.00    0.00    0.00    0.00     2  java

##### 查看内存: free -m 
##### 查看磁盘: df -h 
##### 查看磁盘IO:
iostat -xdk 2 3  每两秒采样一次，采样三次
Linux 3.10.0-693.el7.x86_64 (test-node03-com) 	08/05/2021 	_x86_64_	(4 CPU)
Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util
sda               0.00     0.22    0.00    2.71     0.07    45.07    33.29     0.01    2.00   11.88    1.99  11.02   2.99
scd0              0.00     0.00    0.00    0.00     0.00     0.00   114.22     0.00    0.28    0.28    0.00   0.28   0.00
dm-0              0.00     0.00    0.00    2.93     0.07    45.07    30.83     0.03    9.07   12.03    9.07  10.21   2.99
dm-1              0.00     0.00    0.00    0.00     0.00     0.00    15.34     0.00   31.01    0.74   73.52  12.11   0.00

rkB/s：每秒读取数据量
wkB/s：每秒写入数据量
await：请求等待时间
util：一秒钟有百分之几的时间用于IO操作，100%代表磁盘带宽跑满

pidstat -d 3 -p 59223
Linux 3.10.0-693.el7.x86_64 (test-node03-com) 	08/05/2021 	_x86_64_	(4 CPU)
04:56:56 PM   UID       PID   kB_rd/s   kB_wr/s kB_ccwr/s  Command
04:56:59 PM     0     59223      0.00      0.00      0.00  java
04:57:02 PM     0     59223      0.00     16.00      0.00  java
##### 查看网络IO: ifstat 1  每秒刷新一次

### 定位到具体代码
ps -mp 4519 -o THREAD,tid,time
USER     %CPU PRI SCNT WCHAN  USER SYSTEM   TID     TIME
root     90.3   -    - -         -      -     - 00:01:21
root      0.0  19    - futex_    -      -  4519 00:00:00
root     89.8  19    - -         -      -  4520 00:01:20

-m：显示所有线程
-p：该线程使用cpu的时间
-o：用户自定义的格式
将10进制的线程id转化成16进制的id 

#### jstack 进程id | grep tid(16进制线程ID小写英文) -A60
     jstack 4520   | grep 11a8 -A60

jstack 5884 | grep 16fd -A60
"main" #1 prio=5 os_prio=0 tid=0x00007f7450008800 nid=0x16fd runnable [0x00007f7457b79000]
   java.lang.Thread.State: RUNNABLE
	at java.io.FileOutputStream.writeBytes(Native Method)
	at java.io.FileOutputStream.write(FileOutputStream.java:326)
	at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)
	at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)
	- locked <0x00000006c560cad0> (a java.io.BufferedOutputStream)
	at java.io.PrintStream.write(PrintStream.java:482)
	- locked <0x00000006c560c000> (a java.io.PrintStream)
	at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:221)
	at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:291)
	at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:104)
	- locked <0x00000006c560cbf0> (a java.io.OutputStreamWriter)
	at java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:185)
	at java.io.PrintStream.newLine(PrintStream.java:546)
	- eliminated <0x00000006c560c000> (a java.io.PrintStream)
	at java.io.PrintStream.println(PrintStream.java:751)
	- locked <0x00000006c560c000> (a java.io.PrintStream)
	at DemoTest.main(DemoTest.java:7)

"VM Thread" os_prio=0 tid=0x00007f7450076800 nid=0x1702 runnable 
"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00007f745001d800 nid=0x16fe runnable 
"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00007f745001f800 nid=0x16ff runnable 
"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00007f7450021800 nid=0x1700 runnable





