using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Elevator
{
    class Elevator
    {
       public String ElevatorName { get; set; }
       public String LastInspector { get; set; }
       public String Song { get; set; }
       public String ElevatorColor { get; set; }
       public String ElevatorBrand { get; set; }
       public int MaxWeight { get; set; }
       public int CurrentFloor { get; private set; }
       public int MaxFloors { get; set; }
       public DateTime LastInspectionDate { get; set; }
      

        public Elevator(String name)
        {
            this.ElevatorName = name;
            this.CurrentFloor = 1;
        }

        public void MoveElevator(int FloorToMoveTo)
        {
            if (FloorToMoveTo > MaxFloors)
            {
                Console.WriteLine("You cannnot move pass the max floor");
                return;
            } 
            else if (FloorToMoveTo < 1)
            {
                Console.WriteLine("You cannot move below floor 1");
                return; 
            } 
            else if (FloorToMoveTo == CurrentFloor)
            {
                Console.WriteLine("You are already on this floor");
                return;
            }

            Console.WriteLine("Get ready! You are moving to Floor #" + FloorToMoveTo);

            for (int i = CurrentFloor; i < FloorToMoveTo; i++)
            {
                Console.WriteLine("You are moving " + (FloorToMoveTo > CurrentFloor ? "down" : "up") + " to Floor #" + i);
            }

            CurrentFloor = FloorToMoveTo;

            Console.WriteLine("You are now on Floor (#" + CurrentFloor + ")");
        }

        public override string ToString()
        {
            return "Elevator Name: " + this.ElevatorName + " Elevator Color: " + this.ElevatorColor +
                " Current floor: " + this.CurrentFloor + " Max floors: " + this.MaxFloors + " Last Inspection Date: " + this.LastInspectionDate.ToString() +
                 " Last inspector: " + this.LastInspector + " Song: " + this.Song + " Max weight: " + this.MaxWeight + " Elevator Brand: " + this.ElevatorBrand;
        }
    }
}
