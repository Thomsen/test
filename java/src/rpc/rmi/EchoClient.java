package rpc.rmi;

import rpc.rmi.Echo;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoClient {
    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());

        try {
            //Echo t = (Echo) Naming.lookup("EchoServer");
            Registry reg = LocateRegistry.getRegistry("localhost", 8010);
            Echo t = (Echo) reg.lookup("EchoServer");
            for (int i=0; i<10; i++) {
                System.out.println(t.echo(String.valueOf(i)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// java -Djava.security.policy=policy