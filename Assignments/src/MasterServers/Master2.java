/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MasterServers;
import WorkerServers.Database;
import WorkerServers.WorkerD;
import WorkerServers.WorkerE;
import WorkerServers.WorkerF;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
/**
 *
 * @author moutasem
 */
public class Master2 {
        private Map<String, Double> clientBalances;
        
      public static void main(String[] args) {
        try {
            Database db = new Database();
            WorkerD d = new WorkerD(db);
            WorkerE e = new WorkerE(db);
            WorkerF f = new WorkerF(db);
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.bind("D", d);
            registry.bind("E", e);
            registry.bind("F", f);

            System.out.println("Local Master Server is ready.");
        } catch (Exception e) {
            System.err.println("Local Master Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
