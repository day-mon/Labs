package Drivers;


import Labs.CS_0045_DSAINFO.BankTransaction;

public class BankTransactionDriver
{
    public static void main(String[] args)
    {
        long START_TIME = System.currentTimeMillis();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        BankTransaction b = new BankTransaction(1, 2, 100.0);
        BankTransaction b2 = new BankTransaction(3, 4, 200.0);

        System.out.println("Sender of b1 (Should be 1):" + b.getSender());
        System.out.println("Sender of b2 (Should be 3):" + b2.getSender());

        System.out.println("Recipient of b1 (Should be 2):" + b.getRecipient());
        System.out.println("Recipient of b2 (Should be 4):" + b2.getRecipient());


        System.out.println("Amount of b1 (Should be 100):" + b.getAmount());
        System.out.println("Amount of b2 (Should be 200):" + b2.getAmount());

        System.out.println("Compare b1 to b2 (Should be -1):" + b.compareTo(b2));
        System.out.println("Compare b2 to b1 (Should be 1):" + b2.compareTo(b));

        System.out.println("b1 equals b2 (Should be false):" + b.equals(b2));

        b.setAmount(200);
        b.setSender(3);
        b.setRecipient(4);

        System.out.println("b1 equals b2 (Should be true):" + b.equals(b2));

        System.out.println("b1.toString(): " + b.toString());

        long DELTA_TIME = System.currentTimeMillis() - START_TIME;
        double afterUsedMem=(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1048576;
        String usage = afterUsedMem > 1 ?  " bytes" : " byte"; 
        System.out.println("Memory usage (bytes): "+afterUsedMem+usage);
        System.out.println("Run-time (ms): "+DELTA_TIME+" ms");
        }
    }