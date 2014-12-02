
public class PersonStack {

    private Person top;

    /**
     * 出栈
     * @param person
     */
    synchronized void enstack(Person person) {
        if (null != top && null != person) {
            person.setNext(top); // 新对象的下一个地址指向top指定的内存区
            top = person;  // top指向新对象的内存区
        } else if (null == top) {
            top = person;  // 初始化top指向内存区
        } else {
            throw new UnsupportedOperationException("top present, but no person");
        }
        notifyAll();
    }

    /**
     * 出栈
     * @return
     */
    synchronized Person pop() {
        Person per = top;
        if (null != top) {
            top = top.getNext();
        }
        return per;
    }

}
