package leak;

public class InnerNameClazz {

    public static void main(String[] args) {
        InnerNameClazz nameClazz = new InnerNameClazz();
        nameClazz.execute();
    }

    public void execute() {
        /*
        result = {InnerNameClazz$InnerRun@963}
         this$0 = {InnerNameClazz@951}
          Class has no fields
         */
        new Thread(new InnerRun()).start();
    }

    public class InnerRun implements Runnable {

        @Override
        public void run() {

        }
    }
}

/*
public class leak.InnerNameClazz {
  public leak.InnerNameClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class leak/InnerNameClazz
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #4                  // Method execute:()V
      12: return

  public void execute();
    Code:
       0: new           #5                  // class java/lang/Thread
       3: dup
       4: new           #6                  // class leak/InnerNameClazz$InnerRun
       7: dup
       8: aload_0
       9: invokespecial #7                  // Method leak/InnerNameClazz$InnerRun."<init>":(Lleak/InnerNameClazz;)V
      12: invokespecial #8                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      15: invokevirtual #9                  // Method java/lang/Thread.start:()V
      18: return
}
*/
