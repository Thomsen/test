
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderRecord implements Runnable { 

  private final BlockingQueue<Order> orderQueue; 

  public OrderRecord(BlockingQueue<Order> orderQueue) { 
    this.orderQueue = orderQueue; 
  } 

  public void start() { 

    Thread thread = new Thread(this, "Order Recorder"); 
    thread.start(); 
  } 

  @Override 
  public void run() { 

    while (true) { 

      try { 
        Order order = orderQueue.take(); 
        recordOrder(order); 
      } catch (InterruptedException e) { 
        e.printStackTrace(); 
      } 
    } 

  } 

  public void recordOrder(Order order) throws InterruptedException { 
    TimeUnit.SECONDS.sleep(1); 
  } 

} 