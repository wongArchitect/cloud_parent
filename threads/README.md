# 工程简介

网址：
    
    https://www.cnblogs.com/toria/p/11234323.html

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
            
            
            
            
            
三、术语
        
            线程和进程 、上下文切换 、生命周期和状态 、 并发与并行 、线程死锁、 
            
            
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
                                     