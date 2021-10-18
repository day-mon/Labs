import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main
{

      public static void main(String[] args) throws IOException, ClassNotFoundException
      {
            var in = new Scanner(System.in);
            var rsa = new RSA();

            rsa.mK();

            System.out.println("""
                    Please give me the file name you want to from, to, and the output file you would like. Seperated by commas (without txt)
                    Example: file, fileEnc, fileEncOut
                    """);

            var message = in.nextLine();

            if (!message.contains(","))
            {
                  System.out.println("Your message does not contain any commas so there for we cannot parse this!");
                  return;
            }

            var array = message.split(",");


            var f1 = new File(array[0] + ".txt");
            var f2 = new File(array[1] + ".txt");
            var f3 = new File(array[2] + ".txt");

            var encrypt = rsa.e(f1, f2);
            var decrypt = rsa.d(f2, f3);
            System.out.printf("""
                    Encrypted Message: %s
                    Decrypted Message: %s
                    %n""", encrypt, decrypt);


      }


}
