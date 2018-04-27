import java.lang.annotation.*;

/**
 * Describe class Entity here.
 *
 *
 * Created: Fri Jun 16 10:13:30 2017
 *
 * @author <a href="mailto:Thomsen@PC-20170515DRHG"></a>
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Entity {

    String TABLE = "table";

    String table() default TABLE;
}
