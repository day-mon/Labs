package Labs.CS_1735_SDM.Assign2;

import java.util.Scanner;


public class Main
{
      public static void main(String[] args)
      {
            var grade = new EachGradeScreen();
            var fileManager = FileManager.getInstance();
            AverageGradeScreen grade2 = new AverageGradeScreen();
            Scanner in = new Scanner(System.in);
            System.out.println("Deleting all grades...");
            fileManager.deleteAllGrades();
            System.out.println("Grades: " + fileManager.getGrades());
            fileManager.getFirstGrade();

            char letter;
            System.out.print("Continue? (Y)/(N):  ");
            letter = in.next().charAt(0);
            int answer;

            do
            {
                  System.out.println("""
                          1 - Add Grade
                          2 - Delete All Grades
                          3 - Quit
                          4 - Average grade calculation
                          5 - Show all Grades""");

                  answer = in.nextInt();

                  if (answer == 1)
                  {
                        System.out.println("Enter grade: ");
                        int num = in.nextInt();
                        fileManager.addGrade(num);
                  }
                  else if (answer == 2)
                  {
                        System.out.println("Deleting all grades...");
                        fileManager.deleteAllGrades();
                        System.out.println("Grades after delete: " + fileManager.getGrades());
                  }
                  else if (answer == 3)
                  {
                        System.out.println("Quitting...");
                        System.exit(0);
                  }
                  else if (answer == 4)
                  {
                        System.out.println("Average of all grades...");
                        grade2.display();
                  }
                  else if (answer == 5)
                  {
                        System.out.println("Showing all grades...");
                        grade.display();
                  }
            }
            while (Character.toLowerCase(letter) == 'y');
      }
}
