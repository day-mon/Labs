package Labs.Software_Design.Assign2;


public class AverageGradeScreen implements Observer, DisplayScreen
{
      private final FileManager manager;

      public AverageGradeScreen()
      {
            manager = FileManager.getInstance();
            manager.registerObserver(this);
      }

      @Override
      public void display()
      {
            var average = manager.getGrades()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0);

            System.out.printf("The grade average is: %.2f \n", average);
      }

      @Override
      public void update()
      {
           display();
      }
}
