import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        BlockChain blockChain = BlockChain.getInstance();

        while (running) {
            System.out.println("\n==== Simple Java Blockchain CLI ====");
            System.out.println("1. Add a new transaction");
            System.out.println("2. Mine block");
            System.out.println("3. Print blockchain");
            System.out.println("4. Validate blockchain");
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

                    blockChain.addPendingTransaction(new Transaction(sender, receiver, amount));
                    break;

                case 2:
                    blockChain.addBlock(new Block(blockChain.getChain().get(blockChain.getChain().size() - 1).hash));
                    System.out.println("Block added.");
                    break;

                case 3:
                    blockChain.printChain();
                    break;

                case 4:
                    boolean valid = blockChain.isChainValid();
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
