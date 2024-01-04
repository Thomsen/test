package leak;

public class InnerStaticClazz {

    public static void main(String[] args) {
        new Thread(new InnerRun()).start();
    }

    public static class InnerRun implements Runnable {

        @Override
        public void run() {

        }
    }
}

/*
public class leak.InnerStaticClazz {
  public leak.InnerStaticClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class java/lang/Thread
       3: dup
       4: new           #3                  // class leak/InnerStaticClazz$InnerRun  静态内部类不会隐式持有外部类引用，因为不能够直接使用外部变量
       7: dup
       8: invokespecial #4                  // Method leak/InnerStaticClazz$InnerRun."<init>":()V
      11: invokespecial #5                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      14: invokevirtual #6                  // Method java/lang/Thread.start:()V
      17: return
}
*/
