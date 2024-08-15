package WorkerServers;

import MasterServers.MasterInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author moutasem
 */
public class WorkerA extends UnicastRemoteObject implements MasterInterface {

   private Database database;

    public WorkerA(Database db) throws RemoteException {
        this.database = db;
    }

    @Override
    public synchronized double checkBalance(String clientID) throws RemoteException {
        return database.checkBalance(clientID);
    }

    @Override
    public synchronized String withdraw(String clientID, double amount) throws RemoteException {
        double balance = database.checkBalance(clientID);
        if (balance >= amount) {
            database.updateBalance(clientID, balance - amount);
            return "Withdrawal successful. New balance for client " + clientID + ": " + (balance - amount);
        } else {
            return "Insufficient funds";
        }
    }

    @Override
    public synchronized String deposit(String clientID, double amount) throws RemoteException {
        double balance = database.checkBalance(clientID);
        database.updateBalance(clientID, balance + amount);
        return "Deposit successful. New balance for client " + clientID + ": " + (balance + amount);
    }



}
