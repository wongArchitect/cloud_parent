Lombok注解-@SneakyThrows

    1、Lombok的@SneakyThrows就是为了消除下面这样的模板代码。使用注解后不需要担心Exception的处理
        try{
        }catch(Exception e){
        throw new RuntimeException(e);
        }

    2、代码比较
      原代码
        @SneakyThrows
          public void run() {
            throw new Throwable();
          }
      实际代码
          public void run() {
              try {
                throw new Throwable();
              } catch (Throwable t) {
                throw Lombok.sneakyThrow(t);
              }
            }

    3、真相：
        利用泛型将我们传入的Throwable强转为RuntimeException。因为JVM并不关心是否是RuntimeException。泛型最后存储为字节码时并没有泛型的信息。这样写只是为了骗过javac编译器。
