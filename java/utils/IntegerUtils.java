public class IntegerUtils {

    public static void main(String[] args) {
        String numStr = "101";

        System.out.println(numStr + " is " + Integer.parseInt(numStr, 10));
        System.out.println(numStr + " is " + Integer.parseInt(numStr, 8));
        System.out.println(numStr + " is " + Integer.parseInt(numStr, 2));

        System.out.println(numStr + " is " + Integer.valueOf(numStr, 2));

        int num = 65;

        System.out.println(num + " hex string is " + Integer.toHexString(num));
        System.out.println(num + " octal string is " + Integer.toOctalString(num));
        System.out.println(num + " binary string is " + Integer.toBinaryString(num));

    }
}
