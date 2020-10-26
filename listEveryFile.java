import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class listEveryFile {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<String> files = new LinkedList<>();

        System.out.print("Path: ");
        String path = in.next();
        try {
        final File folder = new File(path);
        
        listFilesForFolder(folder, files);

        FileWriter src = new FileWriter(path + "\\src.txt");
        for (String s : files)
          src.write(s + "\n");
        src.close();
        } catch (IOException e) {};
    }

    public static void listFilesForFolder(final File folder, LinkedList<String> s) {
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


