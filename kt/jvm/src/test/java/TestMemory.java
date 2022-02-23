import memory.Memory;
import memory.SubMemory;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class TestMemory {
    @Test
    public void test() {
        Memory m = new Memory();
    }

    public void testMemory() {
        Memory m = new Memory();
//        Instrumentation i = new Instrumentation();
//        long size = i.getObjectSize(m);
//        Assert.assertEquals(24, size);
    }

    @Test
    public void testSubMemory() {
        SubMemory sm = new SubMemory();

        System.out.println(ClassLayout.parseClass(SubMemory.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(sm).toPrintable());
        System.out.println(GraphLayout.parseInstance(sm).toPrintable());
    }
}
