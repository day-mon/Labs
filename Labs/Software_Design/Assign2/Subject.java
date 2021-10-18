package Labs.Software_Design.Assign2;

public interface Subject
{
      void registerObserver(Observer observer);
      void removeObserver(Observer observer);
      void notifyObserver();
}
