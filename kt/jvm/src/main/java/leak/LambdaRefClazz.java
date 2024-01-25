package leak;

public class LambdaRefClazz {

    public String name;
    public static boolean isMem;

    public static void main(String[] args) {
        LambdaRefClazz refClazz = new LambdaRefClazz();
        Runnable r = () -> {
            refClazz.name = "clazz";
            isMem = false;
        };
        /*
        r = {LambdaRefClazz$lambda@952}
         arg$1 = {LambdaRefClazz@951}
          name = null
         */
        new Thread(r).start();
    }
}

/*
public class leak.LambdaRefClazz {
  public java.lang.String name;

  public static boolean isMem;

  public leak.LambdaRefClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class leak/LambdaRefClazz
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: invokedynamic #4,  0              // InvokeDynamic #0:run:(Lleak/LambdaRefClazz;)Ljava/lang/Runnable;
      14: astore_2
      15: new           #5                  // class java/lang/Thread
      18: dup
      19: aload_2
      20: invokespecial #6                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      23: invokevirtual #7                  // Method java/lang/Thread.start:()V
      26: return
}
*/
