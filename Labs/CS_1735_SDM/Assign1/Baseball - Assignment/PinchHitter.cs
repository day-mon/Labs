using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class PinchHitter : BaseballPlayer
    {
        public PinchHitter()
        {
            batBehavior = new SwingForPower();
            catchBehavior = new CatchNo();
            throwBehavior = new ThrowNothing();
        }

        public override void Display()
        {
            Console.WriteLine($"The PinchHitter's Name is: {Name}");
        }
    }
}
