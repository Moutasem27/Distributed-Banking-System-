
package MasterServers;
import java.io.*;
import WorkerServers.Database;
import WorkerServers.WorkerA;
import WorkerServers.WorkerB;
import WorkerServers.WorkerC;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author moutasem
 */
public class Master1 {

    
    public static void main(String[] args) throws IOException {
        Database db =   new Database();
        try {
            WorkerA a = new WorkerA(db);
            WorkerB b = new WorkerB(db);
            WorkerC c = new WorkerC(db);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("A", a);
            registry.bind("B", b);
            registry.bind("C", c);
            

            System.out.println("Local Master Server is ready.");
        } catch (Exception e) {
            System.err.println("Local Master Server exception: " + e.toString());
            e.printStackTrace();
        }
            
    }
}
