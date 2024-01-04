package leak;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InnerStaticInvokeClazz {

    public static void main(String[] args) {
        Class<?> outerClass = InnerStaticInvokeClazz.class;
        Class<?> innerClass = InnerStaticInvokeClazz.InnerRun.class;

        try {
            Method method = outerClass.getDeclaredMethod("run", Runnable.class);
            method.setAccessible(true);

            Object innerObject = innerClass.newInstance();
            method.invoke(outerClass.newInstance(), innerObject);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    //
    public void run(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static class InnerRun implements Runnable {

        @Override
        public void run() {
           // System.out.println("inner run invoke");
        }
    }
}

/*
public class leak.InnerStaticInvokeClazz {
  public leak.InnerStaticInvokeClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: ldc           #2                  // class leak/InnerStaticInvokeClazz
       2: astore_1
       3: ldc           #3                  // class leak/InnerStaticInvokeClazz$InnerRun
       5: astore_2
       6: aload_1
       7: ldc           #4                  // String run
       9: iconst_1
      10: anewarray     #5                  // class java/lang/Class
      13: dup
      14: iconst_0
      15: ldc           #6                  // class java/lang/Runnable
      17: aastore
      18: invokevirtual #7                  // Method java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      21: astore_3
      22: aload_3
      23: iconst_1
      24: invokevirtual #8                  // Method java/lang/reflect/Method.setAccessible:(Z)V
      27: aload_2
      28: invokevirtual #9                  // Method java/lang/Class.newInstance:()Ljava/lang/Object;
      31: astore        4
      33: aload_3
      34: aload_1
      35: invokevirtual #9                  // Method java/lang/Class.newInstance:()Ljava/lang/Object;
      38: iconst_1
      39: anewarray     #10                 // class java/lang/Object
      42: dup
      43: iconst_0
      44: aload         4
      46: aastore
      47: invokevirtual #11                 // Method java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      50: pop
      51: goto          94
      54: astore_3
      55: new           #13                 // class java/lang/RuntimeException
      58: dup
      59: aload_3
      60: invokespecial #14                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/Throwable;)V
      63: athrow
      64: astore_3
      65: new           #13                 // class java/lang/RuntimeException
      68: dup
      69: aload_3
      70: invokespecial #14                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/Throwable;)V
      73: athrow
      74: astore_3
      75: new           #13                 // class java/lang/RuntimeException
      78: dup
      79: aload_3
      80: invokespecial #14                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/Throwable;)V
      83: athrow
      84: astore_3
      85: new           #13                 // class java/lang/RuntimeException
      88: dup
      89: aload_3
      90: invokespecial #14                 // Method java/lang/RuntimeException."<init>":(Ljava/lang/Throwable;)V
      93: athrow
      94: return
    Exception table:
       from    to  target type
           6    51    54   Class java/lang/NoSuchMethodException
           6    51    64   Class java/lang/InstantiationException
           6    51    74   Class java/lang/IllegalAccessException
           6    51    84   Class java/lang/reflect/InvocationTargetException

  public void run(java.lang.Runnable);
    Code:
       0: new           #18                 // class java/lang/Thread
       3: dup
       4: aload_1
       5: invokespecial #19                 // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
       8: invokevirtual #20                 // Method java/lang/Thread.start:()V
      11: return
}
*/

// ldc: 将常量加载到操作数栈
// astore: 将引用类型存储到局部变量表

