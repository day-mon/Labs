using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    class SwingNothing : BatBehavior
    {
        public void PerformSwing()
        {
            Console.WriteLine("Player does not swing at all");
        }
    }
}
