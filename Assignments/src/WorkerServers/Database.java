/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WorkerServers;

import java.io.BufferedReader;
import java.io.*;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author moutasem
 */
public class Database {

    private final String FILE_PATH = "client_balances.txt";
    private Map<String, Double> clientBalances;
    private static final String BACKUP = "backup.txt";

    public Database() {
        clientBalances = new HashMap<>();
        readFromFile();
    }


    private void readFromFile() {
        if (!readFile(FILE_PATH)) {
            System.err.println("Failed to read ");
            if (!readFile(BACKUP)) {
                System.err.println("Failed to read from backup files");
            }
        }
    }

    private boolean readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return false;
        }
        try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            clientBalances.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    clientBalances.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + "; " + e.getMessage());
            return false;
        }
    }

    private void writeToFile() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Double> entry : clientBalances.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Double readBalanceFromFile(String clientID) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(clientID)) {
                    return Double.parseDouble(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public synchronized double checkBalance(String clientID) {
        return readBalanceFromFile(clientID);
    }

    public synchronized void updateBalance(String clientID, double newBalance) {
        clientBalances.put(clientID, newBalance);
        writeToFile();
        backupDatabase();
    }

    public synchronized boolean clientExists(String clientID) {
        return clientBalances.containsKey(clientID);
    }

    public void initializeClients(Map<String, Double> initialClientBalances) {
        clientBalances.putAll(initialClientBalances);
        writeToFile();
    }

    public void backupDatabase() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(BACKUP))) {
            for (Map.Entry<String, Double> entry : clientBalances.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


