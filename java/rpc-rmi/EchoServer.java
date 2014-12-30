

import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoServer extends UnicastRemoteObject implements Echo {

    public EchoServer() throws RemoteException {
        super();
    }

    public String echo(String msg) throws RemoteException {
        return "Echo: " + msg;
    }

    public static void main(String [] args) {
        System.setSecurityManager(new RMISecurityManager());

        try {
            EchoServer es = new EchoServer();
            Registry reg = LocateRegistry.createRegistry(8010);
            reg.rebind("EchoServer", es);
            //Naming.rebind("EchoServer", es);
            System.out.println("Ready to provide echo service...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// javac EchoServer.java Echo.java
// rmic EchoServer (_Stub class)
// rmiregistry (optinal)
// java -Djava.security.policy=policy.txt EchoServer