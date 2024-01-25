package leak;

public class InnerClazz {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}

// > cd build/classes/java/main/leak
// > ls
// InnerClazz$1.class InnerClazz.class   LambdaClazz.class

// > javap -c InnerClazz
/*
public class leak.InnerClazz {
  public leak.InnerClazz();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class java/lang/Thread               非内部类
       3: dup
       4: new           #3                  // class leak/InnerClazz$1
       7: dup
       8: invokespecial #4                  // Method leak/InnerClazz$1."<init>":()V 调用了匿名类的构造方法，持有了外部引用
      11: invokespecial #5                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      14: invokevirtual #6                  // Method java/lang/Thread.start:()V
      17: return
}
*/


// aload: 从局部变量表中加载引用类型到操作数栈
// invokespecial: 调用私有方法 实例初始化方法 以及超类中定义方法
// dup: 复制栈顶一个字长的数值，并将复制品压入栈顶
// invokedynamic: 运行时动态解析并绑定方法调用 （反射机制）
// invokevirtual: 调用实例方法

// 匿名内部类持有外部类引用
