using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class Batter : BaseballPlayer
    {
        public Batter()
        {
            batBehavior = new SwingForContact();
            throwBehavior = new ThrowNothing();
            catchBehavior = new CatchBarehands();
        }

        public override void Display()
        {
            Console.WriteLine($"The Batters Name is: {Name}");
        }
    }
}
