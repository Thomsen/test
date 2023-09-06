package jvm.refer;

import org.junit.Assert;
import org.junit.Test;
import refer.ReferObject;

public class TestRefer {

    @Test
    public void testRefer() {
        ReferObject ro1 = new ReferObject("refer one");
        ReferObject ro2 = ro1;
        ro1 = null;
        Assert.assertEquals(ro2.name, "refer one");
    }

    @Test
    public void testRefer2() {
        ReferObject ro1 = new ReferObject("refer one");
        ReferObject ro2 = ro1;
        ro1.name = null;
        Assert.assertNull("", ro2.name);
    }
}
