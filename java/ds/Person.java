
public class Person {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 指向下一个用户
     */
    private Person next;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getNext() {
        return next;
    }

    public void setNext(Person next) {
        this.next = next;
    }

}
