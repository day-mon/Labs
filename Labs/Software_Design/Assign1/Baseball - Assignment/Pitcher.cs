using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class Pitcher : BaseballPlayer
    {
        public Pitcher()
        {
            batBehavior = new SwingNothing();
            throwBehavior = new ThrowPitch();
            catchBehavior = new CatchGlove();
        }

        public override void Display()
        {
            Console.WriteLine($"The Pitcher's name is {Name}");
        }
    }
}
