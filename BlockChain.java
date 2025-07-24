import java.util.ArrayList;

public class BlockChain {

    private static BlockChain instance;
    
    private ArrayList<Block> blockchain = new ArrayList<>();
    private ArrayList<Transaction> pendingTransactions = new ArrayList<>();
    private int difficulty = 3;

    private BlockChain() {
        Block genesis = new Block("0");
        pendingTransactions.add(new Transaction("David", "First transaction", 10));
        addBlock(genesis);
    }

    public static BlockChain getInstance() {
        if(instance == null) {
            instance = new BlockChain();
        }
        return instance;
    }

    public ArrayList<Block> getChain() {
        return blockchain;
    }

    public void addBlock(Block newBlock) {
        if(pendingTransactions.size() > 0) {
            newBlock.transactions.addAll(pendingTransactions);
            pendingTransactions.clear();
        }
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);

            if(!current.hash.equals(current.calculateHash())) {
                System.out.println("Current hash : " + current.hash);
                System.out.println("Current calculateHash : " + current.calculateHash());
                return false;
            }

            if(!current.previousHash.equals(previous.hash)){
                return false;
            }
        }
        return true;
    }

    public void printChain() {
        for (Block block : blockchain) {
            System.out.println("--------------");
            System.out.println("Current block : " + block.hash);
            System.out.println("Previous block : " + block.previousHash);
            System.out.println("Current transactions : \n" + Transaction.transactionToString(block.transactions));
        }
    }

    public void addPendingTransaction(Transaction tx) {
        pendingTransactions.add(tx);
        System.out.println("Transaction added");
    }

    public ArrayList<Transaction> getPendingTransactions() {
        return pendingTransactions;
    }
}
