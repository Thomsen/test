
public class Q2QShallow {
    public static void main(String[] args) {

        Author auone = new Author("jim", "jamis", "1892", "1933");
        Book bkone = new Book("java", "2015-06-12 20:00:00", 999, auone);

        Author autwo = new Author("toli", "toline", "1992", "2008");
        Book bktwo = new Book("cpp", "2015-06-12 18:00:00", 1288, autwo);

        System.out.println("--------------- source object -----------");
        System.out.println("auone " + auone.toString());
        System.out.println("autwo " + autwo.toString());        
        System.out.println("bkone " + bkone.toString());
        System.out.println("bktwo " + bktwo.toString());

        Book x = bkone;
        Book y = bktwo;

        System.out.println("--------------- x y object -----------");
        System.out.println("x " + x.toString());
        System.out.println("y " + y.toString());
        System.out.println("auone " + auone.toString());
        System.out.println("autwo " + autwo.toString());        
        System.out.println("bkone " + bkone.toString());
        System.out.println("bktwo " + bktwo.toString());

        x = y;

        System.out.println("--------------- x := y object -----------");
        System.out.println("x " + x.toString());
        System.out.println("y " + y.toString());
        System.out.println("auone " + auone.toString());
        System.out.println("autwo " + autwo.toString());        
        System.out.println("bkone " + bkone.toString());
        System.out.println("bktwo " + bktwo.toString());

    }
}
