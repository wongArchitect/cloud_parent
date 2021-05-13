
一、泛型：
    
   1、博文网址
   
        泛型的基本原理
   
            https://www.cnblogs.com/yangming1996/p/9199557.html
    
        java基础-泛型举例详解
        
            https://www.cnblogs.com/Mykebai/p/9123749.html
            
        三分钟学习Java泛型中T、E、K、V、？的含义
        
            https://blog.csdn.net/u010648555/article/details/103858693
            
   2、 类型擦除：
                  
          1） 语法糖： 本身只是一个语法糖，所以对于 JVM 运行时的性能是没有任何影响的。
          
          2）存在的意义：
              类型安全检查，过滤掉大部分因为类型不符而导致的运行时异常。
              泛型可以让程序代码的可读性更高。
                      
            
   3、   T、E、K、V、？的含义         

        •E - Element (在集合中使用，因为集合中存放的是元素)，E是对各方法中的泛型类型进行限制，以保证同一个对象调用不同的方法时，操作的类型必定是相同的。E可以用其它任意字母代替
        
        T - Type（Java 类），T代表在调用时的指定类型。会进行类型推断。
        
        •K - Key（键）•V - Value（值）•N - Number（数值类型）
        
        ？- 表示不确定的java类型，是类型通配符，代表所有类型。？不会进行类型推断
        
        List<? extends T>可以接受任何继承自T的类型的List，
        List<? super T>可以接受任何T的父类构成的List。
        例如List<? extends Number>可以接受List<Integer>或List<Float>。