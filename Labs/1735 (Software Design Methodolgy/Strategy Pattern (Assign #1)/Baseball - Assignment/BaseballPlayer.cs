using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Baseball___Assignment
{
    abstract class BaseballPlayer
    {
        public CatchBehavior catchBehavior { get; set; }
        public BatBehavior batBehavior { get; set; }
        public ThrowBehavior throwBehavior { get; set; }
        public String Name { get; set; }
        public abstract void Display();
    }
}
