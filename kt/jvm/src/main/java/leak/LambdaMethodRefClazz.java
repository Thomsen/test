package leak;

public class LambdaMethodRefClazz {

    public String name;
    public static boolean isMem;

    public static void main(String[] args) {
        LambdaMethodRefClazz refClazz = new LambdaMethodRefClazz();
        refClazz.run();
    }

    public void run() {
        Runnable r = () -> {
            name = "clazz";
            isMem = false;
        };
        /*
        r = {LambdaMethodRefClazz$lambda@954}
         arg$1 = {LambdaMethodRefClazz@955}
          name = null
         */
        new Thread(r).start();
    }
}

/*
public class leak.LambdaMethodRefClazz {
  public java.lang.String name;

  public static boolean isMem;

  public leak.LambdaMethodRefClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class leak/LambdaMethodRefClazz
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #4                  // Method run:()V
      12: return

  public void run();
    Code:
       0: aload_0
       1: invokedynamic #5,  0              // InvokeDynamic #0:run:(Lleak/LambdaMethodRefClazz;)Ljava/lang/Runnable;
       6: astore_1
       7: new           #6                  // class java/lang/Thread
      10: dup
      11: aload_1
      12: invokespecial #7                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      15: invokevirtual #8                  // Method java/lang/Thread.start:()V
      18: return
}
*/
