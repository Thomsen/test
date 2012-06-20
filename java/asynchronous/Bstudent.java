
/**
 * Created by IntelliJ IDEA.
 * User: thom
 * Date: 12-3-27
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class Bstudent extends Student implements Runnable {
    
    String msg = "";
    Cstudent stu;
    ICallBack callBack
            ;

    public Bstudent(String msg, Cstudent stu, ICallBack callBack) {
        this.msg = msg;
        this.stu = stu;
        this.callBack = callBack;
    }

    public void returnMessage(String msg,  ICallBack callBack)  {
        callBack.obtain(msg);
    }



    @Override
    public void run() {

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (stu.knownMessage(msg)) {

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            returnMessage("已经成功通知到C今晚去开会", callBack);
        }

    }

}
