
public class DsTest {

    public static void main(String[] args) {

        final PersonQueue pq = new PersonQueue();
        final PersonStack ps = new PersonStack();

        new Thread("enqueue") {
            public void run() {
                try {
                    Person per = new Person();
                    per.setName("one");
                    //					ps.enstack(per);
                    pq.enqueue(per);

                    Thread.sleep(1000);

                    Person per2 = new Person();
                    per2.setName("two");
                    //					ps.enstack(per2);
                    pq.enqueue(per2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        Person per3 = new Person();
        per3.setName("three");
        //		ps.enstack(per3);
        pq.enqueue(per3);


        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                    Person pr = ps.pop();
                    if (pr != null) {
                        System.out.println("stack poll name: " + pr.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }.start();

    new Thread("queue poll") {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                    Person pr = pq.poll();
                    if (pr != null) {
                        System.out.println("queue poll name: " + pr.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }.start();

}

}
