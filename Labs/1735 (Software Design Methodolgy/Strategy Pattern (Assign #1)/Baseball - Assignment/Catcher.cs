using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class Catcher : BaseballPlayer
    {
        public Catcher()
        {
            batBehavior = new SwingNothing();
            catchBehavior = new CatchWithCatchersMitt();
            throwBehavior = new ThrowPitcher();
        }

        public override void Display()
        {
            Console.WriteLine($"The Catcher's name is: {Name}");
        }
    }
}
