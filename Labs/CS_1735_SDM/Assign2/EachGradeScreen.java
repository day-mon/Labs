package Labs.CS_1735_SDM.Assign2;

public class EachGradeScreen implements DisplayScreen, Observer
{
      private final FileManager manager = FileManager.getInstance();

      public EachGradeScreen()
      {
            manager.registerObserver(this);
      }

      public void display()
      {
            System.out.println(manager.getGrades());
      }

      public void update()
      {
            display();
      }
}
