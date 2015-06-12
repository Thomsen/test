
public class Main {
    public static void main(String[] args) {

        Author auone = new Author("jim", "jamis", "1892", "1933");
        Book bkone = new Book("java", "2015-06-12 20:00:00", 999, auone);

        System.out.println(bkone.getTitle());
        System.out.println(bkone.getAuthor().getName());

    }
}
