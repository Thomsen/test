package thread;

public class ResourceContext {


    private static StringBuilder counter = new StringBuilder();

    public void addCounter(String name) {
        counter.append(name);
    }

    public void setCounter(String word) {
        counter = new StringBuilder(word);
        printCounter();
    }

    public void printCounter() {
        print(Thread.currentThread().getName(), 0,
                counter.hashCode(), counter.toString());
    }

    public void add(String name) {
        StringBuilder builder = Resource.counter.get();
        Resource.counter.set(builder.append(name));
    }

    public void set(String word) {
        Resource.counter.set(new StringBuilder(word));
        print();
    }

    public void print() {
        print(Thread.currentThread().getName(), Resource.counter.hashCode(),
                Resource.counter.get().hashCode(), Resource.counter.get().toString());
    }

    public void print(String threadName, int hashCode, int instanceCode, String value) {
        StringBuilder builder = new StringBuilder();
        builder.append("Thread name: ");
        builder.append(threadName);
        builder.append(", thread local hashCode: ");
        builder.append(hashCode);
        builder.append(", string builder instance hashCode: ");
        builder.append(instanceCode);
        builder.append(", value: ");
        builder.append(value);
        System.out.println(builder.toString());
    }


    static class Resource {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }
}
