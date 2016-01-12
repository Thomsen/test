public class AopTest {

    public static final void main(final String[] args) {
        System.out.println("--- java proxy ---");
        IAop aop = new AopProxy(new AopImpl());
        aop.execute("proxy");

        System.out.println("\n--- java dynamic proxy ---");
        IAop daop = (IAop) new DynamicProxy().bind(new AopImpl());  // only interface
        daop.execute("dynamic proxy");

        // AopImpl daop = (AopImpl) new DynamicProxy().bind(new AopImpl());
        // Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to AopImpl
        //     at AopTest.main(AopTest.java:9)

        System.out.println("\n--- cglib proxy ---");
        CglibAopImpl cglib = (CglibAopImpl) new CglibProxy().bind(new CglibAopImpl());
        cglib.execute("cglib proxy");

        // javac -classpath cglib-(nodep)-3.2.0.jar *.java
        // java -classpath cglib-nodep-3.2.0.jar AopTest
    }

}
