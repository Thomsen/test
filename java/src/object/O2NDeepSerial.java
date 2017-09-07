package object;

import object.Author;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class O2NDeepSerial {
    public static void main(String[] args) {

        Author auone = new Author("jim", "jamis", "1892", "1933");
        Book bkone = new Book("java", "2015-06-12 20:00:00", 999, auone);

        AuthorSerial autwo = new AuthorSerial("toli", "toline", "1992", "2008");
        BookSerial bktwo = new BookSerial("cpp", "2015-06-12 18:00:00", 1288, autwo);

        System.out.println("--------------- source object -----------");
        System.out.println("auone " + auone.toString());
        System.out.println("autwo " + autwo.toString());        
        System.out.println("bkone " + bkone.toString());
        System.out.println("bktwo " + bktwo.toString());

        BookSerial x = null;

        System.out.println("--------------- x object -----------");
        System.out.println("x " + x);
        System.out.println("auone " + auone.toString());
        System.out.println("autwo " + autwo.toString());        
        System.out.println("bkone " + bkone.toString());
        System.out.println("bktwo " + bktwo.toString());

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);

            oos.writeObject(bktwo);
            oos.flush();

            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);

            x = (BookSerial) ois.readObject();

            System.out.println("--------------- x := bktwo object -----------");
            System.out.println("x " + x.toString());
            System.out.println("auone " + auone.toString());
            System.out.println("autwo " + autwo.toString());        
            System.out.println("bkone " + bkone.toString());
            System.out.println("bktwo " + bktwo.toString());
        } catch (Exception e) {
            e.printStackTrace();  
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {

                }

            }
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {

                }

            }
        }

    }
}
