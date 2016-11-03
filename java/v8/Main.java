import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
/**
 * Describe class Main here.
 *
 *
 * Created: Wed Nov  2 20:59:56 2016
 *
 * @author <a href="mailto:thom@anyuaning">Thomsen Wang</a>
 * @version 1.0
 */
public class Main {
    public static final void main(final String[] args) {
        System.out.println("java version 8 learn");

       Arrays.asList("c", "java", "cpp")
            .sort((e1, e2) -> {
                    return e1.compareTo(e2);
                });
       Arrays.asList("c", "java", "cpp")
           .sort((e1, e2) -> e1.compareTo(e2));
       Arrays.asList("c", "java", "cpp")
           .forEach( e -> System.out.println(e));

       Human human = HumanFactory.create(Man::new);
       System.out.println(human.name());

       human = HumanFactory.create(Woman::new);
       System.out.println(human.name());



    }

    private interface Human {
    default String name() {
        return "default human name";
    }
    }

    private interface HumanFactory {
        static Human create(Supplier<Human> supplier) {
            return supplier.get();
        }
    }

    private static class Man implements Human {

    }

    private static class Woman implements Human {
        @Override
        public String name() {
            return "woman name";
        }
    }

}
