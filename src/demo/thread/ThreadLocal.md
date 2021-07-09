#### jdk8之前，是把当前线程当作key，存储在ThreadLocal里边
#### jdk8之后，是在Thread里边声明了一个变量ThreadLocalMap(ThreadLocal的内部类)
#### 这样做的好处：不会产生大的ThreadLocal

Thread-->ThreadLocal-->ThreadLocalMap-->Entry(key:ThreadLocal,value:变量)

|  jdk   |  Thread   | 线程变量  | ThreadLocal对象数 |
|  ----  |  ----  | ----  | ----  |
| jdk8之前  | 1000  | 2 | 每个ThreadLocal有1000个Thread对象引用 |
| jdk8之后  | 1000  | 2 | 每个Thread有两个ThreadLocal引用 |

