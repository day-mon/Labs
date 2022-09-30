using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class ThrowPitch : ThrowBehavior
    {
        public void PerformThrow()
        {
            Console.WriteLine("Player only throws pitches");
        }
    }
}
