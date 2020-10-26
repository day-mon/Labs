import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class listEveryFile {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<String> files = new LinkedList<>();
        String currentDirectory = System.getProperty("user.dir");

        System.out.print("Path: ");
        String path = in.next();
        if(path.equals(".")) path = currentDirectory;
        try {
        final File folder = new File(path);
        
        listFilesForFolder(folder, files);

        FileWriter src = new FileWriter(path + "\\src.txt");
        for (String str : files){
          if(str.contains("\\"))
            str = str.replaceAll("\\\\", "\\\\\\\\");
          src.write("\""+str+"\"" + "\n");
        }
        src.close();
        } catch (IOException e) {};

        try {
            Runtime.getRuntime().exec("cmd /c start \"Building all files...\" compileall.bat");
        } catch (IOException e) {
            System.out.println("Failed to build all. Either CMD does not exist on this operating system or \"compileall.bat\" does not exist.");
            e.printStackTrace();
        }
    }

    public static void listFilesForFolder(final File folder, LinkedList<String> s) {
        // System.out.println(folder.listFiles() != null ? folder.listFiles()[0] : "null");
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
           listFilesForFolder(fileEntry, s);
        } else {
            if (fileEntry.getName().contains(".java")) {
            s.add(fileEntry.getAbsolutePath());
            }
        }
    }
}}


