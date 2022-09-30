using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Elevator
{
    class Program
    {
        static void Main(string[] args)
        {
            var e1 = new Elevator("e1")
            {
                ElevatorColor = "Blue",
                MaxWeight = 500,
                LastInspector = "Bob",
                Song = "Girl from Ipanema",
                ElevatorBrand = "ACME",
                LastInspectionDate = new DateTime(1971, 7, 17),
                MaxFloors = 20
            };

            var e2 = new Elevator("e2")
            {
                ElevatorColor = "Red",
                MaxWeight = 500,
                LastInspector = "Ricky",
                Song = "Don't Tell Ashley",
                ElevatorBrand = "Fender",
                LastInspectionDate = new DateTime(2013, 4, 5),
                MaxFloors = 8
            };

            Console.WriteLine(e1.ToString());
            Console.WriteLine(e2.ToString());

            e1.MoveElevator(5);
            e1.MoveElevator(5);
            e2.MoveElevator(5);
            e1.MoveElevator(3);
            e2.MoveElevator(2);
            e1.MoveElevator(25);
            e2.MoveElevator(2);
            e1.MoveElevator(18);


        }
    }
}