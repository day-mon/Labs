package Labs.CS_1760_AOOP.Assign1;

public class Volume1 extends Volume implements Comparable<Volume1>
{
      private int barrels;
      private int gallons;

      public Volume1()
      {

      }

      public Volume1(int barrels, int gallons)
      {
            this.barrels = barrels;
            this.gallons = gallons;
      }

      public Volume1(Volume1 v)
      {
            this.barrels = v.getBarrels();
            this.gallons = v.getGallons();
      }

      public void setBarrels(int b)
      {
            this.barrels = b;
      }

      public void setGallons(int g)
      {
            this.gallons = g;
      }

      public int getBarrels()
      {
            return barrels;
      }

      public int getGallons()
      {
            return gallons;
      }

      public Volume1 add(Volume1 v)
      {
            var _1 = new Volume1(
                    this.barrels + v.getBarrels(),
                    this.gallons + v.getGallons());
            _1.convert();
            return _1;
      }

      public Volume1 subtract(Volume1 v)
      {
            var _1 =
                    new Volume1(0,
                            (this.barrels * 42 + this.gallons) - (v.getBarrels() * 42 + v.getGallons())
                    );
            _1.convert();
            return _1;
      }

      @Override
      public int toPints()
      {
            return ((this.barrels * 42) + this.gallons) * 8;
      }

      @Override
      public void convert()
      {
            this.barrels += this.gallons / 42;
            this.gallons %= 42;
      }



      @Override
      public int compareTo(Volume1 v)
      {
            return Integer.compare(this.toPints(), v.toPints());
      }

      @Override
      public String toString()
      {
            return "Volume1 { barrels = %d | gallons = %d }".formatted(barrels, gallons);
      }
}