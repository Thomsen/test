package aop;

public class AopProxy implements IAop {
    private IAop aop;

    public AopProxy(IAop aop) {
        this.aop = aop;
    }

    public void execute(String arg) {
        System.out.println("before proxy execute ...");
        aop.execute(arg);
        System.out.println("after proxy execute ...");
    }
}
