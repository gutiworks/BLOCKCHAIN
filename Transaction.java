import java.util.ArrayList;

public class Transaction {

    public String sender;
    public String reciver;
    public float amount;


    public Transaction(String sender, String reciver, float amount)
    {
        this.sender = sender;
        this.reciver = reciver;
        this.amount = amount;
    }

    public static String transactionToString(ArrayList<Transaction> transactions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Transaction transaction : transactions) {
            stringBuilder.append(transaction.sender + " -> " + transaction.reciver + ": " + transaction.amount);
        }
        return stringBuilder.toString();
    }
}