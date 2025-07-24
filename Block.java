import java.security.MessageDigest;
import java.util.Date;
import java.util.ArrayList;

public class Block {

    public String hash;
    public String previousHash;
    public ArrayList<Transaction> transactions;
    private long timeStamp;
    private int nonce;

    public Block(String previousHash) {
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + Transaction.transactionToString(transactions);
        return applySha256(input);
    }

    private String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getTargetString(int difficulty) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < difficulty; i++) {
            builder.append("0");
        }
        return builder.toString();
    }

    //So we need to add Prof of Work, this adds rewards to the users who want to participate in the blockchain
    public void mineBlock(int difficulty) {
        String target = getTargetString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            System.out.println("Trying nonce: " + nonce + " -> " + hash);
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined " + hash);
    }
}