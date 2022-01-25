package jvm.genertic;


import generic.GenericExtend;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestGeneric {

    @Test
    public void testNormal() {
        Object obj = new Object();
        String str = new String();
        obj = str;

        List<String> strs = new ArrayList<>();
//        List<Object> objs = strs;
        List<Object> objs = Collections.singletonList(strs);
        objs.add(1);
        String str1 = (String) objs.get(1);
    }

    @Test
    public void testGE() {
        GenericExtend ge = new GenericExtend();
        List<Object> to = new ArrayList();
        to.add("to");
        List<String> from = new ArrayList();
        from.add("from");

        ge.copyAll(to, from);
        Assert.assertEquals("from", to.get(1));
    }
}
