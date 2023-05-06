package instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceMain {

    public static void main(String[] args) {
        try {
            Class clz = Class.forName("instance.Outlet"); // CNFE
//            Outlet o1 = (Outlet) clz.newInstance(); // IE IAE
            Outlet o1 = (Outlet) clz.getDeclaredConstructor().newInstance();

            Constructor c1 = clz.getDeclaredConstructor(String.class); // NSME
            c1.setAccessible(true);
            Outlet o2 = (Outlet) c1.newInstance("const"); // ITE

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
