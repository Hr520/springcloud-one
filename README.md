# springcloud-one

**面试准备项目**

    一、基础面试问答
        _谈谈HashMap_
         HashMap是一个用于存储Key-Value键值对的集合,每一个键值对也叫做Entry。这些个键值对（Entry）分散存储在一个数组当中，这个数组就是HashMap的主干
         HashMap 最常用的两个方法 get put 获取/添加
         HashMap数组每一个元素的初始值都是Null
         新的Entry节点插入链表时采用的是头插法 为什么不是尾插法：
         HashMap默认初始长度是多少？为什么这么规定？
         答：HashMap的默认初始化长度是16，并且每次自动或者手动扩展初始化时所指定的长度必须是2的幂。
         为什么是16？
         答：是为了服务于key映射到index的Hash算法
         HashMap是非线程安全的所有HashMap会很快
         高并发下HashMap可能会出现死锁吗？ 
         HashMap因为非线程安全假设两个线程下同时插入数据 A线程放入key A B C  B线程也同时放入 A B C 遇到容量不足的时候需要新建一个更大尺寸的HASH表，会出现 如 a->b ,b->a 形成环形链表  死锁的根本原因
         替代方案 ConcurrentHashMap进行替代，ConcurrentHashMap是一个线程安全的Hash Table。可能有人会使用HashTable,当然HashTable也是线程安全，但HashTable锁定的是整个Hash表，效率相对低下。
         而ConcurrentHashMap可以做到读取数据不加锁，并且内部结构可以让其在进行写操作的时候能够将锁粒度尽量的小。
         ConcurrentHashMap在写入数据的时候加入了细粒度的锁，并且在计算大小时采用了CAS
         示例线程安全
           //Map<Object,Object> myMap=new HashMap<>();
                  ConcurrentHashMap myMap=new ConcurrentHashMap();
                 for (int i = 0; i <10000 ; i++) {
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             double a=Math.random();
                             myMap.put(a,a);
                             myMap.remove(a);
                         }
                     }).start();
                 }
                 try {
                     Thread.sleep(15000l);//多休眠下，保证上面的线程操作完毕。
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println(myMap.size());

         
         java8中HashMap有什么样的优化？
          答：java8中与java7中的HashMap有一些区别 首先是：数组+链表 改成了数组+链表/红黑树
          第二点是插入方式的不同 头插法换成了尾插法 第三点扩容的机制不同 1.7先判断是否需要扩容，再插入，1.8先插入，再判断是否需要扩容 扩容的时候1.7需要对原数组的元素重新hash定位在新数组的位置，1.8采用索引不变加旧容量
          大小的方式因此效率上有提升。       
         头插法机制因为HashMap是非线程安全的头插法会使链表发生反转多线程下会产生环;
         A线程在插入节点B,B线程也在插入,遇到容量不够开始扩容，重新HASH,放置元素，采用头插法，后便利到B节点放到了头部就成了环
            
        HashTable 线程安全 但是效率低
       
        hashCode 哈希算法也称为散列算法 在集合查找时使用HashCode 能够大大降低对象的比较次数，提高查询效率
        可以这么讲 当一个集合要新增元素时，先调用这个元素对应的HashCode方法 定位到应该放置的物理位置  ，如果这个位置没有元素那么直接将新元素放在这个位置不用再次比较。如果已经存在则调用eqauls方法与新元素进行比较，
        这样一来就极大的减少了eqauls方法调用。
        
        List  有序的列表 可重复，允许插入多个null值 ArrayList   继承自Collection集合
        LinkList 双向链表 新增和删除速度快 
        ArrayList  查询效率比较高 
        LinkList 和 ArrayList线程都不安全
        Vector  之前说是线程安全的，现在 要带锁才能达到线程安全 感觉存在感较低。
        set   无序集合  不可重复，只允许插入一个null set 有 HashSet 与TreeSet 
        TreeSet 有序的 是SortedSet接口的唯一实现类 是二叉树实现的  不允许放入null 
        HashSet  是hash表不能保证元素的排列顺序，顺序有可能发生变化 不是同步的 集合元素可以是null,但只能放入一个null
        
      二、谈一下Spring 常用的IOC 和AOP 
         spring DI 机制
         控制反转和依赖注入是同一个概念  具体的讲：当一个角色需要另外一个角色协助完成一项工作时，在传统的程序设计中必须由 A主动获取B b=new B() 现在用到IOC机制 new 对象这个事情由spring去完成，容器会帮我们去实例化一个所需的对象
          AOP  面向切面 编程，最大的优势在于有一些业务与核心业务无关，例如 操作日志 ，记录某个操作的执行时间 通过AOP 就可以实现不修改核心代码的同时完成上述操作。
          @Aspect面向切面编程必须要了解的几个注解 @PointCut注解 表示横切点  execution：一般用于指定方法的执行，用的最多 
          @Before 前置通知  @After后置通知 @AfterReturning 用于获取方法的返回值
      三、聊聊IO 与NIO
        IO 面向流，阻塞IO 效率低 
        NIO 面向缓冲 非阻塞IO  选择器  NIO有两套 第一种是输入输出NIO  第二种是网络编程NIO 
        NIO  Selectors 
        
      四、String、StringBuffer与StringBuilder的区别
        String 适用于少量字符串操作的情况 属于字符串常量 因为是不可改变类所以线程安全
        效率上StringBuilder最高 适用于单线程下在字符串缓冲区的大量操作 线程不安全 
        StringBuffer 适用于多线程下的字符串操作，线程安全，效率稍差
      五、JVM 
        java 中也会存在内存泄漏风险，原因是可能会存在无用但可达的对象不被GC回收因此导致内存泄漏
        JVM 加载class文件原理机制 由类加载器CLassloader和其子类来实现的
        GC  垃圾回收机制 GC 可以有效的防止内存泄漏问题 由于现在JVM 采用分代收集
        新生代 用来存放新生对象占据堆内存约1/3 由于频繁的创建对象，所以新生代会频繁的触发GC  
        老年代 采用标记清除算法 
        永久代  GC 不会对永久代进行清理
        java8已经移除永久代
        引用计数法，例如有引用该对象 就标记为1 没有引用的就标记为0，则说明对象可被回收
        可达性分析 一个对象没有可达的路径在至少两次标记为不可达对象才会被回收
        标记清除法 存在内存碎片问题
        复制算法 将原先的内存容量分为两份每次只使用其中一份，内存利用率高，但是内存也压缩到原来的一半，效率会大大降低
        什么时候会触发GC  1 旧生代空间不足  剩余空间不足
        JVM 调优 
        -XMx 堆内存最大限制 
        -XX:NEWSIZE 新生代大小
        -XX:NEWRATIO新生代和老生代占比
        JVM 调优工具 自带的  jconsole
        初始内存尽量和最大保存保持一致，避免因内存不够用继续扩充内存。最大内存不要超过物理内存，例如8G内存你可以设置4/6G但是不要超过8G,否则加载类的时候就会因为没有空间而报错。
      六、java基本数据类型
       byte short int double boolean float char long 
      七、方法重写 方法重载
        继承父类 重写方法 方法名 参数列表 返回类型必须相同 
        方法重载 方法名相同 参数可以不同 返回类型也可以不相同 重载是java多态的一种表现
      八、equals 与 ==区别
        == 用来判断两个对象的地址是否相同 
        equals 比较的是值相等 也就是hashCode值
      九、java常用包
            java.lang java.util java.sql java.io java.math java.net
      十、Object类常用方法
            Equals HashCode  toString 
      十一、接口
         是一种特殊的java类弥补java但继承的缺点，其特点是多继承
      十二、常用的设计模式
           单例模式： 简单来说一个应用程序中，某个类的实例对象只有一个，你没办法去new 因为对象被private修饰 只能通过类所提供的方法获取对象
           观察者模式： 场景是当一个业务发出通知需要其他操作进行同步操作时，这个当前业务就是被观察者，举例说明
           当一个用户注册成功，需要告诉他的所有上级代理，这时候 他的所有代理收到通知消息做出自己该有的变化。
           装饰着模式： 对已有的业务逻辑，使其获得额外的功能 例如菜单类 一级菜单 子菜单 使其组成一个完整的菜单数据
           工厂模式： 
      十三、多线程
            实现多线程的几种方法：继承Thread 类  实现Runnable接口 实现Callable 接口 通过FutureTask包装器来创建THread线程 其中Callable可以实现有返回的多线程
            stop 强行终止当前线程  interrupt 中断线程 sleep 线程睡眠
            notify     notifyAll 用来唤醒某些条件下等待的线程 两者字面的理解就是 notify只会唤醒一个持锁等待的线程，但无法指定唤醒哪一个。
            notifyall 会唤醒所有的等待线程
            sleep 与 wait 区别
            sleep 是Thread类的 sleep指定了睡眠时间 让出cpu执行其他线程，时间到后自动恢复执行 
            wait 是Object类的方法 会直接放弃线程锁进入等待锁，必须由notify唤醒
           
    **二、mysql 优化**
        1、mysql 优化
          mysql有多种存储引擎 
          MyIASM  全表锁 是mysql的默认引擎，但是没有提供对数据库的事务支持 不支持外键，并发性能差，如果以查询和插入为主可以使用这个引擎
          Innodb  行级锁 具备事务安全，并发能力强，缺点占用空间大，处理效率会差一点     
          MEmonry 全表锁 采用HAsh索引，查找效率高，但不适用于精确查找，主要用于那些内容变化不频繁的表
        2、索引优化 
           主键索引（primary）  唯一索引(unique) 普通索引(index) 全文索引（fullText）
           索引并非越多越好，创建索引会增加数据库的存储空间，二是在插入和删除时要花费较多的时间维护
           索引主要是加快检索速度 
           原则： 设置索引时：为常用作查询条件的字段建立索引 为经常需要排序，分组联合操作的字段建立索引 尽量选择索引字段值短的避免影响速度，不使用的索引要及时移除
        3、数据库事务 
            原子性 ：当事务处理完毕，数据必须处于一致状态  隔离性：对数据进行修改的所有并发事务是彼此隔离，事务是独立的 永久性：事务完成后丢数据库的修改被永久保持。
        4、sql优化 
            不使用select * 
            避免使用 子查询 以 inner join left join right join 来替代
            inner join 匹配两张表的相关联记录  
            left join 匹配两张表的关联记录，还会匹配左边剩余记录  右边未匹配的字段用null表示
            right join 匹配两张表的关联记录，还会匹配右表记录，左边未匹配字段用null表示
            减少使用 not in 、in 以exists ,not exists 代替 ；or 可以用union或者unionall 替代
            避免在where 条件字段上做操作 例如null判断 ！=null 等等 否则将放弃索引全表扫描
        5、并发事务带来的问题
            脏读 多十五访问数据时由于多线程操作的问题造成其中一个事务读到不正确的数据，常见场景有去库存
            丢失修改  多个事务在修改同一个数据 第一个事务修改 A=20-1 第二个事务读取A=20 也做A=20-1 所以有一次操作算是丢失修改
            不可重复读 同一事务多次读取同一数据，第二个事务对其进行了更改 造成两次读取数据不一致
            幻读 
        6、事务隔离级别
             读取未提交 READ-UNCommitted 最低的隔离级别 可能导致脏读 幻读 
             读取已提交 REQD-Committed 允许读取并发事务已经提交的数据，可以阻止脏读 但幻读和不可重复仍有可能发生
             可重复读  Repeatable-READ    可以阻止脏读和不可重复读 ，但有可能出现幻读
             serializable 可串行化  最高的隔离级别  防止脏读  不可重复读 幻读 
        7、大数据优化 
              大表优化：当单表数据过大时,数据库的性能会明显下降 ，禁止大数据量表的直接无条件查询，控制查询总查询数据量例如控制在一个月
              如果需要查询更大的时间跨度上可以做单独接口来对非热点数据的查询，定期进行数据迁移和对数据库表进行优化
             读写分离  垂直拆分表，水平分表
             一般来说当数据量达到一定水平 那么必须最终的解决方案是分库分表，不然单一的数据库实例性能是有限的
             分库分表 对业务抽离要求较高，需要在前期做好冗余字段防止连表查询等 ，像shading-jdbc 轻量级的分库分表插件 还有mycat重量级的中间件
             分库分表 采取水平分区的策略会引起主键问题，必须采用分布式ID 
             目前比较推崇的是twitter的snowflaker 雪花算法生成唯一ID  但是有一个时钟回拔的问题
             前端传值会有一个超出长度的问题 js的number类型最大支持2 的 53次方 -1 会出现精度丢失解决办法在前端进行字符转换 后端需要用字符串接受      
                                    
     三、mybatis
            spring 很好的集成mybatis mybatis-plus等插件
            优点利于优化，可以自主书写sql相当的灵活能够写出比较高效的sql对服务调优有非常大的帮助     
            缺点：sql编写工作量的加入，对开发人员有一定的sql编写能力有一定的要求。
     四、web开发
            session共享机制 
            分布式如何实现session共享 用redis存储session数据来达到多服务之间的共享目的
            sessioniD 可以用作单点登录
            restful风格 介绍： 
            什么是restful风格 1每一个URL都代表一种资源 2客户端与服务器之间，传递这种资源的某种表现层
            3客户端通过HTTP动词 对服务器资源进行操作 GET 获取资源 POST创建资源修改资源 PUT 修改资源 delete删除资源
            jsonp跨域问题 
            xss csrf 问题 
            jquery 常用的选择器
     五、分布式 springcloud常用
            kafka:
            springcloud：完整的服务管理机制 在spring微服务下有天然优势 Eureka注册中心 EurekaAP的优势保证高可用的目的 服务调用机制还是reat api 也就是Http
              常用注解 eureka服务端 @EnableEurekaServer
                      @EnableDiscoveryClient 注册到eureka服务   
            在需要调用外部接口的服务中 创建 接口服务调用需要调用服务的接口地址 例如 
            A服务要调用B服务里面的 /user/getOne 接口  根据 Feign的调用方式 
            在 A 服务下 创建 FeignService 
            加上以下注解 
            @Component
            @FeignClient("feign-server")    feign-server 为服务名称
            里面需要调用B服务的/user/getOne 接口 示例代码如下 
             @GetMapping("/user/getOne")
             String getOne(@RequestParam("name") String name,@RequestParam("age") Integer age);        
           在A服务中的congtroller中注入  FeignService 再调用 getOne方法即可访问 B服务指定的接口
           只需要在接口调用方pom加上 
             <dependency>
                       <groupId>org.springframework.cloud</groupId>
                       <artifactId>spring-cloud-starter-openfeign</artifactId>
                       <version>2.2.0.RELEASE</version>
                   </dependency>
           B 服务 中只要需要有 user/getOne 接口即可 服务熔断和降级在本接口处理   
                       
            Dubbo:CP  用的是zookeeper  强调一致性  dubbo调用机制是 RPC方式
            例如工程里面 我们声明一个模块作为提供对外接口，如果某个业务模块需要用到这个接口 就在pom里面引入接口这个工程方法 
            RPC 调用方式  
            关键注解  @Reference 注入service服务
            
                             
     六、服务器部署：
           centos部署传统的SSM 单服务 tomcat 服务
           
           docker化部署
            
