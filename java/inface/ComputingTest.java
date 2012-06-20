
/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-26
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public class ComputingTest {
    
    public static void main(String[] args) {

        int sum = 1;

         sum = compute(3, 2, new IComputingListener() {
            @Override
            public int add(int a, int b) {
                int sum = a + b;
                
                System.out.println("add result " + sum);

                return sum;
            }
        });
        
        
        System.out.println("main result " + sum);
    }
    
    private static int compute(int a, int b, IComputingListener listener ) {

//        IComputingListener listen = listener;

//        listen.add(a, b);

        int sum = 0;
        sum = listener.add(a, b);  // 在这里将listener和a b关联起来，其他方法就可调用

        
        System.out.println("compute result " + sum);
        
        return sum;
        

    }
}
