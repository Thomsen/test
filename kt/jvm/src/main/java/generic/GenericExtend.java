package generic;

import java.util.Collection;

public class GenericExtend {

    public void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);
    }

    public void copyAll2(Collection<String> to, Collection<Integer> from) {
//        to.addAll(from);
        //
//        Required type:
//        Collection
//                <? extends String>
//        Provided:
//        Collection
//                <Integer>
    }

    void demo(Source<String> strs) {
        // Class Object is the root of the class hierarchy. Every class has Object as a superclass.
        // All objects, including arrays, implement the methods of this class.
//        Source<Object> objs = strs;  // compile error

        Source<? extends Object> objs = strs;  // 通配符类型 ? extends Object
    }
}

interface Source<T> {
    T next();
}




