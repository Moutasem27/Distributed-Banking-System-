//
//
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import MasterServers.*;
//import java.util.Scanner;
//
///**
// *
// * @author maste
// */
//public class ClientA1 {
//
//    public static void main(String[] args) {
//        try {
//            // Create a scanner for user input
//            Scanner scanner = new Scanner(System.in);
//
//            while (true) {
//                System.out.println("Choose an area, A-F");
//                String server = scanner.nextLine();
//                Registry registry = LocateRegistry.getRegistry(1099);
//                if ("A".equals(server) || "B".equals(server) || "C".equals(server)) {
//                    registry = LocateRegistry.getRegistry(1099);
//                } else if ("D".equals(server) || "E".equals(server) || "F".equals(server)) {
//                    registry = LocateRegistry.getRegistry(1098);
//                }
//
//                MasterInterface s = (MasterInterface) registry.lookup(server);
//                System.out.print("Enter client ID: ");
//                String clientID = scanner.nextLine();
//                System.out.println("Choose an option:");
//                System.out.println("1. Check Balance");
//                System.out.println("2. Withdraw");
//                System.out.println("3. Deposit");
//                System.out.println("4. Exit");
//                System.out.print("Enter your choice: ");
//
//                // Get user choice
//                int choice = scanner.nextInt();
//                scanner.nextLine(); // Consume newline
//
//                // Perform action based on user choice
//                switch (choice) {
//                    case 1:
//                        double balance = s.checkBalance(clientID);
//                        System.out.println("Balance for client " + clientID + ": " + balance);
//                        break;
//                    case 2:
//
//                        System.out.print("Enter amount to withdraw: ");
//                        double withdrawAmount = scanner.nextDouble();
//                        scanner.nextLine();
//                        String withdrawalResult = s.withdraw(clientID, withdrawAmount);
//                        System.out.println(withdrawalResult);
//                        break;
//                    case 3:
//                        System.out.print("Enter amount to deposit: ");
//                        double depositAmount = scanner.nextDouble();
//                        scanner.nextLine();
//                        String depositResult = s.deposit(clientID, depositAmount);
//                        System.out.println(depositResult);
//                        break;
//                    case 4:
//                        System.out.println("Exiting.");
//                        double p = s.checkBalance(clientID);
//                        s.update(clientID, p);
//                    default:
//                        System.out.println("Invalid choice. Please try again.");
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Client exception: " + e.toString());
//            e.printStackTrace();
//        }
//    }
//
//}
