/**
 * Describe class AnnoMain here.
 *
 *
 * Created: Fri Jun 16 10:31:15 2017
 *
 * @author <a href="mailto:Thomsen@PC-20170515DRHG"></a>
 * @version 1.0
 */
public class AnnoMain {

    public AnnoMain() {

    }

    public static final void main(final String[] args) {
        User user = new User();
        Class userClass = user.getClass();
        Entity entity = (Entity) userClass.getAnnotation(Entity.class);
        System.out.println(entity.table());

    }


}
