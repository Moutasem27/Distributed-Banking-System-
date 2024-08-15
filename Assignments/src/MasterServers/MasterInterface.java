package MasterServers;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
/**
 *
 * @author moutasem
 */
public interface MasterInterface extends Remote {

    double checkBalance(String clientID) throws RemoteException;
    String withdraw(String clientID, double amount) throws RemoteException;
    String deposit(String clientID, double amount) throws RemoteException;
}
