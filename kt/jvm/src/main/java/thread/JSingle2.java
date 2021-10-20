package thread;

public class JSingle2 {

    private JSingle2() {

    }

    private static JSingle2 INSTANCE = null;

    public static JSingle2 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new JSingle2();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i=0; i<100; i++) {
            new Thread(() -> {
                int ins = JSingle2.getInstance().hashCode();
                System.out.println("instance " + ins);
            }).start();
        }
    }

}
