import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class SortData {
    public static void main(String[] args) {
        int [] numbers = new int[100];  
        File num = new File("dependencies/input.txt");
        Scanner in;
        try {
             in = new Scanner(num);
   
        //Get Input from input.txt, store values in array
        for (int i=0;i<numbers.length;i++) {
            int numberInFile = Integer.parseInt(in.nextLine());
            numbers[i] = numberInFile;
        }
       

        //Sort the array. HINT: Look at your imports.
        Arrays.sort(numbers);


        // Write output to output.txt
        File printFile = new File("dependencies/output.txt");
        PrintWriter print = new PrintWriter(printFile);
        for (int nums : numbers) {
            print.println(nums);
        }
        in.close();
        print.close();
    } catch (FileNotFoundException e) {
        System.err.println(e.getLocalizedMessage());
    } 
        
    }
}
