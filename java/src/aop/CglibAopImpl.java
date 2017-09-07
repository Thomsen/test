package aop;

public class CglibAopImpl {
    public void execute(String arg) {
        System.out.println(arg + " cglib execute");
    }
}
