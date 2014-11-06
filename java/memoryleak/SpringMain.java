
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Memory Leak Code...");
		new ClassPathXmlApplicationContext("classpath:context.xml");
		System.out.println("Main Ending...");
	}
}