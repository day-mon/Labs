package objects;

public class Tuple<X, Y>
{

      public X first;
      public Y second;

      public Tuple(X first, Y second)
      {
            this.first = first;
            this.second = second;
      }

      public Tuple()
      {
            this(null, null);
      }

      public X first()
      {
            return first;
      }

      public Y second()
      {
            return second;
      }

      public void setFirst(X first)
      {
            this.first = first;
      }

      public void setSecond(Y second)
      {
            this.second = second;
      }


      public boolean isEmpty()
      {
            return !(first == null || second == null);
      }

      public boolean isFull() {
            return first != null && second != null;
      }


      public void insert(Object item)
      {
            if (first == null)
            {
                  this.first = (X) item;
            }
            else if (second == null)
            {
                  this.second = (Y) item;
            }
      }

}
