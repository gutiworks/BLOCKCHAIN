import java.util.ArrayList;

public class BlockChain {
    
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 2;
    
    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

    public static boolean isChainValid() {
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

    public static void printChain() {
        for (Block block : blockchain) {
            System.out.println("Current block : " + block.hash);
            System.out.println("Previous block : " + block.previousHash);
            System.out.println("Current transactions : " + Transaction.transactionToString(block.transactions));
        }
    }
}
