using System;

namespace Baseball___Assignment
{
    class baseball
    {
        static void Main(string[] args)
        {
            Pitcher kent = new Pitcher();
            kent.Name = "Kent T";
            kent.Display();
            kent.throwBehavior.PerformThrow();
            kent.catchBehavior.PerformCatch();
            kent.batBehavior.PerformSwing();
            Batter pedro = new Batter();
            pedro.Name = "Pedro A";
            pedro.Display();
            pedro.throwBehavior.PerformThrow();
            pedro.catchBehavior.PerformCatch();
            pedro.batBehavior.PerformSwing();
            Fielder max = new Fielder();
            max.Name = "Max M";
            max.Display();
            max.throwBehavior.PerformThrow();
            max.catchBehavior.PerformCatch();
            max.batBehavior.PerformSwing();
            Catcher chris = new Catcher();
            chris.Name = "Chris S";
            chris.Display();
            chris.throwBehavior.PerformThrow();
            chris.catchBehavior.PerformCatch();
            chris.batBehavior.PerformSwing();
            Console.WriteLine("Kent the pitcher will not bat any more because we have a pinch hitter.");
            kent.batBehavior = new SwingNothing();
            kent.Display();
            kent.batBehavior.PerformSwing();

            PinchHitter bobcat = new PinchHitter();
            bobcat.Name = "Bobcat";
            bobcat.Display();
            bobcat.throwBehavior.PerformThrow();
            bobcat.catchBehavior.PerformCatch();
            bobcat.batBehavior.PerformSwing();
        }
    }
}
