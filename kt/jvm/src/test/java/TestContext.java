import kt.context.Context;
import kt.context.ContextKt;
import org.junit.Test;

public class TestContext {


    @Test
    public void testInvokeKt() {
        Context c = new Context();
        System.out.println(ContextKt.px2dp(c, 10));
    }
}
