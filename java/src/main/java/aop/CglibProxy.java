package aop;

//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodProxy;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("record method: " + method.getName());
        System.out.println("record object: " + obj.getClass());
        System.out.println("record target: " + target.getClass());
        return method.invoke(target, args);
    }
}
