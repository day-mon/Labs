package Labs.CS_1735_SDM.Assign2;

public interface Subject
{
      void registerObserver(Observer observer);
      void removeObserver(Observer observer);
      void notifyObserver();
}
