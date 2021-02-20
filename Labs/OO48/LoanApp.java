package OO48;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class LoanApp {
    public static void main(String[] args) {
        File binaryFile = new File("dependencies/loan.dat");
        Scanner in = new Scanner(System.in);

        if (!binaryFile.exists()) {
            try {
                binaryFile.createNewFile();
            } catch (IOException e) {
                e.getLocalizedMessage();
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(binaryFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Loan existingLoan = (Loan)ois.readObject();
                System.out.println(existingLoan.toString());
                
                System.out.println("Do you want to continue?: ");
                if (!in.nextLine().equalsIgnoreCase("yes")) { 
                    System.exit(1);
                } 

            } catch (ClassNotFoundException e) {
                System.err.println("Class of a serialized object cannot be found.");
            } catch (IOException e) {
                System.err.println("Error occurs while reading stream header");
            }
        }
        

        System.out.println("Annual Intrest Rate (AIR): ");
        double AIR = in.nextDouble();
        System.out.println("Number of years of loan: ");
        int years = in.nextInt();
        System.out.println("Loan amount: ");
        double loanAmount = in.nextDouble();


        Loan newLoan = new Loan(AIR, years, loanAmount);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(binaryFile);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(newLoan);
            oos.close();
            fileOutputStream.close();        
        } catch (IOException e) {
            System.out.println("Error occurs while writing stream header");
        }
    }
    
}
