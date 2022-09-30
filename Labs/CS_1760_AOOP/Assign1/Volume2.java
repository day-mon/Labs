package Labs.CS_1760_AOOP.Assign1;

public class Volume2 extends Volume1
{
      private int pints;

      public Volume2()
      {
            super();
      }

      public Volume2(int barrels, int gallons, int pints)
      {
            super(barrels, gallons);
            this.pints = pints;
      }

      public Volume2(Volume2 v)
      {
            super(v);
            this.pints = v.getPints();
      }

      public void setPints(int p)
      {
            this.pints = p;
      }

      public int getPints()
      {
            return pints;
      }

      public Volume2 add(Volume2 v)
      {
            var _1 =
                    new Volume2(
                            getBarrels() + v.getBarrels(),
                            getGallons() + v.getGallons(),
                            pints + v.getPints()
                    );
            _1.convert();
            return _1;
      }

      public Volume2 subtract(Volume2 v)
      {
            var _1 =
                    new Volume2(
                            0,
                            0,
                            toPints() - v.toPints());
            _1.convert();
            return _1;
      }

      @Override
      public int toPints()
      {
            return pints + super.toPints();
      }

      @Override
      public void convert()
      {
            setGallons(getGallons() + this.pints / 8);
            this.pints %= 8;
            super.convert();
      }


      @Override
      public int compareTo(Volume1 v)
      {
            return Integer.compare(this.toPints(), v.toPints());
      }

      @Override
      public String toString()
      {
            return "Volume2 { pints = %d } %s".formatted(pints, super.toString());
      }
}