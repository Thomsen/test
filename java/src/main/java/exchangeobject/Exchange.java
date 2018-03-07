package exchangeobject;

/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-28
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class Exchange {
    
   private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static void swap(Exchange exOne, Exchange exTwo, boolean isAssign) {

        System.out.println("\n================swap before==============\n");
        print(exOne, exTwo);
        
        Exchange exTemp;
        exTemp = exOne;
        exOne = exTwo;
        exTwo = exTemp;


        System.out.println("\n================swap after==============\n");
        print(exOne, exTwo);

        if (isAssign) {

            System.out.println("\n===========assignment===============\n");
            // 此时，是在对象中重新赋值了
            exOne.setName("one in swap");
            exTwo.setName("two in swap");
            
            print(exOne, exTwo);
        }
        

             
    }

    private static void print(Exchange exOne, Exchange exTwo) {
        System.out.println("one object is : " + exOne);
        System.out.println("two object is : " + exTwo);

        System.out.println("one is : " + exOne.getName() + "\t two is : " + exTwo.getName());
    }


    public static void main(String[] args) {
        Exchange one = new Exchange();
        Exchange two = new Exchange();
        
        one.setName("one");
        two.setName("two");
        
        swap(one, two, false);

        System.out.println("\n===============main=============== \n");
        
        print(one, two);

        swap(one , two, true);

        System.out.println("\n===============main=============== \n");

        print(one, two);


                
    }
}
