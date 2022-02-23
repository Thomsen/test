package memory;

public class SubMemory extends Memory {

    private long l;

    private SubInner si = new SubInner();

    class SubInner {
        private long lo;
    }
}
