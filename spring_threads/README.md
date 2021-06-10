# 工程简介

网址：
    
    https://www.cnblogs.com/toria/p/11234323.html
    
       
1、分解分化：多线程（进程解分）--->微服务（功能解分）--->分布式（应用解分）
    
    多线程：进程解分，主线程（main）也是进程的入口，也是应用的执行文件，还有一般线程（自定义线程）、守护线程（垃圾回收机制）保留线程（如，AOP）。
    微服务：对某一功能的解分。
    分布式：项目分化，子项目或子模块，也可以是多个项目或应用，也可以是多个计算机或服务器。
    
2、应用进展历程（概析）    
    
    应用 ----> 进程 ----> 线程 ----> 微服务 ----> 组件 ----> 分布式 ----> 框架体系 ----> 容器 ----> 云计算！
    
3、微服务架构要解决的问题

    1、可用、可行、可持续、高效。
    可行：行指系统运行过程中的需求需要，偏于技术实现。
    可持续：持续，指系统功能可持续集成、运行可持续，偏于业务功能和市场需求。
    2、微服务架构需要考虑的不仅仅是软件架构本身，需要从参与到整个项目实施过程中的各个环节，可能的问题以及人员协同的整体情况去考虑。让整个项目做到可用（满足功能以及硬件资源的横向扩容）、可行（满足整个系统运行中的各个点的监控、排错等）、可持续（满足系统功能的可持续集成、以及系统运行的可持续性）以及高效（系统运行的高效、人员协同工作的高效、功能迭代的高效等）。
     
4、微服务部署

    蓝绿发布、滚动发布、灰度发布、金丝雀发布、功能开关发布。


5、云计算

    网络资源和网络服务，数据信息的存取、共享和展示，（数据处理）功能的复用，以及虚拟化的系统、终端、网络体系、硬件设备等用件，是通过具有商业模式的网络形式来实现。一般是按量付费的。
    
6、istio
       
       http://www.uml.org.cn/wfw/201909063.asp
       有空儿，实战下。。。

7、容器、istio、k8s

    容器技术:有效的将单个操作系统的资源划分到孤立的组中，以便更好的在孤立的组之间平衡有冲突的资源使用需求，这种技术就是容器技术。
    高性能计算(HPC)社区也在转而使用虚拟化和容器技术。一项由巴西天主教大学进行的研究已经取得了一定成果。
    “如果可以降低基本的系统开销（比如CPU、内存、硬盘和网络），那么HPC无疑会选择使用虚拟化系统，”研究人员表示。“从这个角度来说，我们发现所有基于容器的系统在CPU、内存、硬盘和网络方面都拥有接近于本地操作系统的性能表现。”


# 延伸阅读

一、创建线程的几种方式
    
        有4种方式：继承Thread类、实现Runnable接口、实现Callable接口、使用Executor框架来创建线程池。
    
   1、继承Thread类：先继承并重写，后new，再start。
   
         public class MyThreadByExtends extends Thread {//继承Thread类{
             //重写run方法
             public void run() {     }
         
             public static void main(String[] args) {
                 new MyThreadByExtends().start(); //创建并启动线程
             }
         }
   
   2、实现Runnable接口：先实现并重写，再new，再new，再start。    
   
         public class MyThreadByImplements implements Runnable {//实现Runnable接口
             //重写run方法
             public void run() { }
         
             public static void main(String[] args) {
                 //创建并启动线程
                 MyThreadByImplements myThread = new MyThreadByImplements();
                 Thread thread = new Thread(myThread);
                 thread.start();
                 //或者    new Thread(new MyThread2()).start();
             }
         }
         
   3、实现Callable接口： 先实现并重写，再new，再包装，再new，再start。 
            
        public class MyThreadByImplements2 implements Callable {//实现Callable接口
        
            @Override
            public Object call() throws Exception {
                return null;
            }
        
            public static void main(String[] args) {
        
                MyThreadByImplements2 myThreadByImplements2 = new MyThreadByImplements2();
                //使用Lambda表达式创建Callable对象
                //使用FutureTask类来包装Callable对象
                FutureTask<Integer> future = new FutureTask<Integer>(
                        (Callable<Integer>) () -> {
                            return 5;
                        }
                );
        
                new Thread(future, "有返回值的线程").start();//实质上还是以Callable对象来创建并启动线程
                try {
                    System.out.println("子线程的返回值：" + future.get());//get()方法会阻塞，直到子线程执行结束才返回
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
   
   4、使用Executor框架:
   
           Executors.newXXXX： newFixedThreadPool(int )、newSingleThreadExecutor、newCachedThreadPool、newScheduledThreadPool(int)
           通过Executors的以上四个静态工厂方法获得 ExecutorService实例，而后可以执行Runnable任务或Callable任务。
           此处用的是newCachedThreadPool方法获取。
           
           创建： executorService = Executors.newCachedThreadPool();   
           提交：
                   executorService.execute()，不返回值的任务。
                   executorService.submit() ， 返回值的任务。
   
        1)  Executor执行Runnable任务 ： execute()
            
                public class TestCachedThreadPool{   
                    public static void main(String[] args){   
                        ExecutorService executorService = Executors.newCachedThreadPool();    
                        for (int i = 0; i < 5; i++){   
                            executorService.execute(new TestRunnable());   
                            System.out.println("************* a" + i + " *************");   
                        }   
                        executorService.shutdown();   
                    }   
                }   
                class TestRunnable implements Runnable{      //重写run方法 
                public void run(){   
                        System.out.println(Thread.currentThread().getName() + "线程被调用了。");   
                    }
                
                
         2)   Executor执行Callable任务：submit() 
         
                public class CallableDemo{   
                    public static void main(String[] args){   
                        ExecutorService executorService = Executors.newCachedThreadPool();   
                        List<Future<String>> resultList = new ArrayList<Future<String>>();   
                  
                        //创建10个任务并执行   
                        for (int i = 0; i < 10; i++){   
                            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中   
                            Future<String> future = executorService.submit(new TaskWithResult(i));   
                            //将任务执行结果存储到List中   
                            resultList.add(future);   
                        }   
                  
                        //遍历任务的结果   
                        for (Future<String> fs : resultList){   
                                try{   
                                    while(!fs.isDone);//Future返回如果没有完成，则一直循环等待，直到Future返回完成  
                                    System.out.println(fs.get());     //打印各个线程（任务）执行的结果   
                                }catch(InterruptedException e){   
                                    e.printStackTrace();   
                                }catch(ExecutionException e){   
                                    e.printStackTrace();   
                                }finally{   
                                    //启动一次顺序关闭，执行以前提交的任务，但不接受新任务  
                                    executorService.shutdown();   
                                }   
                        }   
                    }   
                }   
                class TaskWithResult implements Callable<String>{   
                    private int id;   
                  
                    public TaskWithResult(int id){   
                        this.id = id;   
                    }   
                  
                    // 重写call()方法
                    public String call() throws Exception {  
                        System.out.println("call()方法被自动调用！！！    " + Thread.currentThread().getName());   
                        //该返回结果将被Future的get方法得到  
                        return "call()方法被自动调用，任务返回的结果是：" + id + "    " + Thread.currentThread().getName();   
                    }   
                } 
                
          注： 两者的区别
               Runnable接口或Callable接口实现类都可以被ThreadPoolExecutor或ScheduledThreadPoolExecutor执行。两者的区别在于 Runnable 接口不会返回结果但是 Callable 接口可以返回结果。 
      
      
二、关键字
         
            volatile（可见性打开） 、synchronized（隐式的支持重进入） 、 ReentrantLock（重入锁）、 Lock（接口）
            
   1、volatile：保证共享变量的“可见性”，并可被修改。
    
        1）只可用于变量。
        2）保证共享变量的“可见性”。可见性的意思是当一个线程修改一个共享变量时，另外一个线程能读到这个修改的值。
        3）把变量声明为volatile，这就指示 JVM每次使用它都到主存中进行读取。         
            
   2、synchronized：隐式的支持重进入；独占锁且悲观锁
   
        1）主要修饰于，实例与静态的方法和代码块
               对于普通同步方法，锁是当前实例对象。
               对于静态同步方法，锁是当前类的Class对象。
               对于同步代码块，锁是synchronized括号里配置的对象。
        2）任意时刻只能有一个线程执行。
        3）通过获取自增，释放自减的方式实现重入。
        4）发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生。
                
        注：
            翻译为“同步”
            独占锁,也是悲观锁，它假设最坏的情况。
        
   
   3、ReentrantLock：重入锁，悲观锁
   
        
        1）主要用于创建一个锁的实例对象。
        2）ReentrantLock 比 synchronized 增加了一些高级功能，主要有3点：①等待可中断；②可实现公平锁；③可实现选择性通知（锁可以绑定多个条件）。
        3）ReentrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。（公平锁就是先等待的线程先获得锁）。
        
        注：翻译为“再进入”
        
   4、 Lock：“锁”接口
   
        1）是个接口，适用于类。
        2）Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
        3）Lock可以提高多个线程进行读操作的效率
        4）其他比volatile、synchronized、ReentrantLock三个修饰词更多的功能
        
   
   
   
            
三、术语
        
            线程和进程 、上下文切换 、生命周期和状态 、 并发与并行 、线程死锁、乐观锁与悲观锁。
            
   1、进程与线程：针对的是操作系统与应用。
   
        1） 进程：操作系统中，独立运行且作为资源分配的基本单位。
        
            在操作系统中能够独立运行，并且作为资源分配的基本单位。它表示运行中的程序。系统运行一个程序就是一个进程从创建、运行到消亡的过程。
        
        2）线程：应用中，比进程更小的单位，进程中的一个功能。
        
            是一个比进程更小的执行单位，能够完成进程中的一个功能，也被称为轻量级进程。        
            
   2、上下文切换机制：针对的是CPU资源的分配
   
        CPU通过给每个线程分配CPU时间片，再通过时间片分配算法来循环执行任务，任务从保存到加载的过程就是一次上下文切换。
        
        
        注：保存时会记录状态，以便下次切换回这个任务时，可以再加载这个任务的状态
    
   3、并行与并发
   
        并发指的是多个任务交替进行，并行则是指真正意义上的“同时进行”。
        
        注：真正的并行只能出现在拥有多个CPU的系统中。
        
   4、线程的生命周期和状态
   
        6个：初始状态、运行状态、阻塞状态、等待状态、超时等待状态、终止状态    
    
   5、 线程死锁与避免：
   
        1）死锁：是一种资源申请的阻塞状态。
        
            多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。
        
        　　  假如线程 A 持有资源 2，线程 B 持有资源 1，他们同时都想申请对方的资源，所以这两个线程就会互相等待而进入死锁状态。
        
        2）避免死锁的几个常见方法：
        
            1> 避免一个线程多个锁与多个资源：
                    避免一个线程同时获取多个锁
                    避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
            2> 定时锁优于内部锁：
                    尝试使用定时锁，使用 lock.tryLock(timeout) 来代替使用内部锁机制。
            3> 一个连接里的数据库锁：
                    对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
   
   6、乐观锁与悲观锁（的区别）及使用场景
   
       1）定义：
            1>悲观锁（Pessimistic Lock）： 担心都加锁；担心数据被改，每次读取都加锁。
                
                每次获取数据的时候，都会担心数据被修改，所以每次获取数据的时候都会进行加锁，确保在自己使用的过程中数据不会被别人修改，使用完成后进行数据解锁。由于数据进行加锁，期间对该数据进行读写的其他线程都会进行等待。
       
            2>乐观锁（Optimistic Lock）： 不担心不加；担心数据被改，每次读取都加锁。
                
                每次获取数据的时候，都不会担心数据被修改，所以每次获取数据的时候都不会进行加锁，但是在更新数据的时候需要判断该数据是否被别人修改过。如果数据被其他线程修改，则不进行数据更新，如果数据没有被其他线程修改，则进行数据更新。由于数据没有进行加锁，期间该数据可以被其他线程进行读写操作。
       
       2)适用场景：
       
           悲观锁：比较适合写入操作比较频繁的场景，如果出现大量的读取操作，每次读取的时候都会进行加锁，这样会增加大量的锁的开销，降低了系统的吞吐量。
           
           乐观锁：比较适合读取操作比较频繁的场景，如果出现大量的写入操作，数据发生冲突的可能性就会增大，为了保证数据的一致性，应用层需要不断的重新获取数据，这样会增加大量的查询操作，降低了系统的吞吐量。
       
       3)总结：
            两种所各有优缺点，读取频繁使用乐观锁，写入频繁使用悲观锁。
   
   7、 CAS算法
      
        1）概念：
            即compare and swap（比较和互换），是一种有名的无锁算法。无锁编程，即不使用锁的情况下实现多线程之间的变量同步，也就是在没有线程被阻塞的情况下实现变量的同步，所以也叫非阻塞同步。
      
        2） CAS算法涉及到3个操作数，如下：
            1>需要读写的内存值V。
            2>进行比较的值A。
            3>拟写入的新值B。
        3）使用场景
            CAS基于硬件实现，不需要进入cpu内核，不需要切换线程，操作自旋几率较少，因此可以获得更高的性能。    
            
               
            
四、常用方法

            sleep() 和 wait() 、 start() 和 run() 
            
   1、sleep() 和 wait() 的区别
   
         1）相同点：
         
         　　两者都可以暂停线程的执行，都会让线程进入等待状态。
         
         2）不同点：sleep为带着锁挂起；wait为释放锁纯等待。
         
         sleep()方法没有释放锁，而 wait()方法释放了锁。
         sleep()方法属于Thread类的静态方法，作用于当前线程；而wait()方法是Object类的实例方法，作用于对象本身。
         执行sleep()方法后，可以通过超时或者调用interrupt()方法唤醒休眠中的线程；执行wait()方法后，通过调用notify()或notifyAll()方法唤醒等待线程。
         
         
   2、start() 和 run() 的区别 
   
         1) 简单讲，run() 只是（线程）类的普通方法；start() 才是执行线程的方法。
         
         2)详细讲:
         
             为什么我们调用 start() 方法时会执行 run() 方法，为什么我们不能直接调用 run() 方法？
             new 一个 Thread，线程进入初始状态；调用 start()方法，会启动一个线程并使线程进入了就绪状态，当分配到时间片后就可以开始运行了。 start() 会执行线程的相应准备工作，然后自动执行 run() 方法的内容，这是真正的多线程工作。 而直接执行 run() 方法，会把 run 方法当成一个 main 线程下的普通方法去执行，并不会在某个线程中执行它，所以这并不是多线程工作。
         
         3) 总结： 调用 start 方法可启动线程并使线程进入就绪状态，而 run 方法只是 thread 的一个普通方法调用，还是在主线程里执行。
                                     