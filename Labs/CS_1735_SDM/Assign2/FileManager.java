package Labs.CS_1735_SDM.Assign2;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements Subject
{
      private volatile static FileManager fileManger = null;
      private List<Observer> observerList;
      private List<Integer> grades;
      private final File file = new File("grades.txt");

      private FileManager()
      {
            grades = new ArrayList<>();
            observerList = new ArrayList<>();

            try
            {
                  if (!file.exists())
                  {
                        file.createNewFile();
                  }
            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }
      }

      public static FileManager getInstance()
      {
            if (fileManger == null)
            {
                  synchronized (FileManager.class)
                  {
                        return fileManger = new FileManager();
                  }
            }
            return fileManger;
      }

      public void addGrade(int grade)
      {
            grades.add(grade);
            writeInt(grade);
            notifyObserver();
      }

      @Override
      public void registerObserver(Observer observer)
      {
            observerList.add(observer);
      }

      @Override
      public void removeObserver(Observer observer)
      {
            observerList.remove(observer);
      }

      @Override
      public void notifyObserver()
      {
            observerList.forEach(Observer::update);
      }

      public List<Integer> getGrades()
      {
            return grades;
      }

      public Integer getFirstGrade()
      {
            if (grades.isEmpty())
            {
                  return null;
            }

            return grades.get(0);
      }

      public void deleteAllGrades()
      {
            if (grades.isEmpty())
            {
                  return;
            }

            clearFile();
            grades.clear();
      }


      public List<Observer> getObserverList()
      {
            return observerList;
      }

      public void writeInt(int value)
      {
            try
            {
                  var writer = new FileWriter(file, true);
                  writer.write(value + "\n");
                  writer.close();
            }
            catch (Exception e)
            {
                  System.err.println("Error occurred while writing to file");
                  e.printStackTrace();
            }
      }

      public void clearFile()
      {
            try
            {
                  if (file.exists())
                  {
                        var deletion = file.delete();
                        if (deletion)
                        {
                              System.out.println("File deletion occurred successfully");
                        }
                        else
                        {
                              System.err.println("There was an error while trying to delete the file");
                        }
                  }
            }
            catch (Exception e)
            {
                  System.err.println("Error occurred while writing to file");
                  e.printStackTrace();
            }
      }

}
