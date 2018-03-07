package ds;

import ds.Person;

public class PersonQueue {

    private Person head;

    private Person tail;

    /**
     *  进队列
     * @param person
     */
    synchronized void enqueue(Person person) {
        if (null != tail) {  // tail: one person: two
            tail.setNext(person);  // two -> one(head) 内存中自定义对象指向下一个
            tail = person;  // tail: two tail指向下一个内存区
        } else if (null == head) {
            head = tail = person; // head tail同时指向一个内存区
        } else {
            throw new UnsupportedOperationException("head present, but no tail");
        }
        notifyAll();
    }

    /**
     * 出队列
     * @return
     */
    synchronized Person poll() {
        Person per = head;
        if (null != head) {
            head = head.getNext(); // header重新指向head的下一个内存区，原本的head内存区会自动回收
            if (null == head) {
                tail = null;
            }
        } else {
            // "queue empty!";
        }
        return per;
    }
}
