package Labs.AlgorithimAnaylsis.RSA;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class RSA
{
      private final File pubKey = new File("pubkey.txt");
      private final File privKey = new File("privkey.txt");
      private final File big = new File("Big.txt");
      private final Random random = new Random();


      public RSA()
      {
            try
            {
                  privKey.createNewFile();
                  privKey.createNewFile();
                  big.createNewFile();
            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }
      }


      public void mK() throws IOException
      {
            var x = BigInteger.probablePrime(500, random);
            var y = BigInteger.probablePrime(500, random);
            var p = BigInteger.probablePrime(500, random);
            var N = x.multiply(y);
            var PHI = x.subtract(BigInteger.ONE).multiply(y.subtract(BigInteger.ONE));

            while (p.equals(PHI))
            {
                  p = generateNumber();
            }
            var s = p.modInverse(PHI);


            if (s.compareTo(PHI) < 0)
            {
                  s = s.add(PHI);
            }
            else if (s.compareTo(PHI) > 0)
            {
                  s = s.subtract(PHI);
            }


            System.out.printf("""
                    X - %s
                    Y - %s
                    P - %s
                    %n""", x, p, y);

            writeFile(pubKey, p, N);
            writeFile(privKey, s, N);
      }


      public String e(File in, File out) throws IOException, ClassNotFoundException
      {
            if (!in.exists())
            {
                  in.createNewFile();
            }

            out.createNewFile();

            var bytes = new byte[32];
            var size = bytes.length;

            for (int i = 0; i < size; i++)
            {
                  bytes[i] = (byte) random.nextInt(128);
            }

            var oos = new ObjectInputStream(new FileInputStream(pubKey));
            var p = (BigInteger) oos.readObject();
            var N = (BigInteger) oos.readObject();
            oos.close();

            var horners = BigInteger.valueOf(bytes[0]);

            for (var i = 1; i < size; i++)
            {
                  var element = bytes[i];
                  horners = horners.multiply(BigInteger.valueOf(128)).add(BigInteger.valueOf(element));
            }
            var encrypt = horners.modPow(p, N);

            writeFile(big, encrypt);


            var charArray = getText(in).toCharArray();
            var charArraySize = charArray.length;
            var formatter = new Formatter(out);
            var builder = new StringBuilder();

            for (int i = 0; i < charArraySize; i++)
            {
                  var element = (int) charArray[i];
                  charArray[i] += bytes[i % size] % 128;
                  builder.append(element);
                  formatter.format("%d%n", (int) charArray[i]);
            }
            formatter.close();
            return builder.toString();
      }

      public String d(File in, File out) throws IOException, ClassNotFoundException
      {
            out.createNewFile();

            var o = new ObjectInputStream(new FileInputStream(big));
            var oos2 = new ObjectInputStream(new FileInputStream(privKey));

            var k = (BigInteger) o.readObject();
            var s = (BigInteger) oos2.readObject();
            var N = (BigInteger) oos2.readObject();
            o.close();
            oos2.close();

            var hornerAgain = k.modPow(s, N);

            var b = new byte[32];
            var bSize = b.length;

            for (int i = 0; i < bSize; i++)
            {
                  var index = 32 - 1 - i;
                  b[index] = (byte) hornerAgain.mod(BigInteger.valueOf(128)).intValue();
                  hornerAgain = hornerAgain.subtract(BigInteger.valueOf(b[index])).divide(BigInteger.valueOf(128));
            }

            var scanner = new Scanner(in);
            var formatter = new Formatter(out);
            var builder = new StringBuilder();

            for (var c = 0; scanner.hasNextLine(); c++)
            {
                  var ln = scanner.nextLine();

                  if (!ln.isEmpty())
                  {
                        var parsed = Integer.parseInt(ln);
                        var rev = b[c % bSize] % 128;
                        var result = (char) (parsed - rev);
                        builder.append(result);
                        formatter.format("%c", c);
                  }
                  else
                  {
                        c--;
                  }
            }


            scanner.close();
            formatter.close();
            return builder.toString();
      }


      private BigInteger generateNumber()
      {
            return BigInteger.probablePrime(500, new Random());
      }


      private String getText(File in) throws IOException
      {
            return Files.lines(Path.of(in.getAbsolutePath()))
                    .map(Objects::toString)
                    .collect(Collectors.joining(""));

      }

      private void writeFile(File file, Object... stuff)
      {
            try
            {
                  var fos = new FileOutputStream(file);
                  var oos = new ObjectOutputStream(fos);

                  for (var obj : stuff)
                  {
                        oos.writeObject(obj);
                  }
                  oos.close();
                  fos.close();
            }
            catch (Exception e)
            {
                  System.err.printf("Error occurred: %s", e.getMessage());
                  e.printStackTrace();
            }
      }


}

