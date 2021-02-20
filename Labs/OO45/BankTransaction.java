package OO45;

import java.awt.print.Book;
import java.util.Objects;
public class BankTransaction implements Comparable<BankTransaction> {

    private int sender, recipient;
    private double amount;

    public BankTransaction(int sender, int recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public int getRecipient() {
        return recipient;
    }

    public int getSender() {
        return sender;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    @Override
    public int compareTo(BankTransaction bankTransaction) {
        if (this.amount == bankTransaction.amount) {
            return 0;
        } else if (this.amount < bankTransaction.amount) {
                return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Amount ($" + amount + ") sent to account #" + recipient + " by account #" + sender;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null)
            return  false;
        if (getClass() != o.getClass())
            return false;

        BankTransaction trans = (BankTransaction)o;

        if (!Objects.equals(this.sender, trans.sender))
            return false;
        if (!Objects.equals(this.amount, trans.amount))
            return  false;
        if (!Objects.equals(this.recipient, trans.recipient))
            return  false;

        return  true;
    }
}
