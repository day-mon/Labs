using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class Fielder : BaseballPlayer
    {
        public Fielder()
        {
            batBehavior = new SwingNothing();
            throwBehavior = new ThrowFielder();
            catchBehavior = new CatchGlove();
        }

        public override void Display()
        {
            Console.WriteLine($"The Fielders name is: {Name}");
        }
    }
}
