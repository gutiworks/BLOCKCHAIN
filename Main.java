import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n==== Simple Java Blockchain CLI ====");
            System.out.println("1. Add a new transaction and mine block");
            System.out.println("2. Print blockchain");
            System.out.println("3. Validate blockchain");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Sender: ");
                    String sender = scanner.nextLine();
                    System.out.print("Receiver: ");
                    String receiver = scanner.nextLine();
                    System.out.print("Amount: ");
                    float amount = scanner.nextFloat();
                    scanner.nextLine(); // consume newline

                    //BlockChain.addPendingTransaction(new Transaction(sender, receiver, amount));

                    ArrayList<Transaction> transactions = new ArrayList<>();
                    transactions.add(new Transaction(sender, receiver, amount));

                    String previousHash = BlockChain.blockchain.size() == 0 ? "0"
                        : BlockChain.blockchain.get(BlockChain.blockchain.size() - 1).hash;
                    Block newBlock = new Block(transactions, previousHash);
                    BlockChain.addBlock(newBlock);
                    System.out.println("Block added.");
                    break;

                case 2:
                    BlockChain.printChain();
                    break;

                case 3:
                    boolean valid = BlockChain.isChainValid();
                    System.out.println("Blockchain valid: " + valid);
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
