package Labs.CS_1783_AI.GA;

import objects.Course;
import objects.Professor;
import objects.Room;
import objects.TimePeriod;

import java.util.ArrayList;
import java.util.List;

public final class Constants
{

    private static Professor bilitski = new Professor(0, "Bilitski" );   // 0
    private static Professor ohl = new Professor(1, "Ohl");        // 1
    private static Professor sandro = new Professor(2, "Sandro");     // 2
    private static Professor mrxxx = new Professor(3, "Mr xxx");     // 3
    private static Professor frederick = new Professor(4, "Frederick");  // 4
    private static Professor peter = new Professor(5, "Peter");      // 5
    private static Professor brian = new Professor(6, "Brian");      // 6
    private static Professor meg = new Professor(7, "Meg");        // 7
    private static Professor stewie = new Professor(8, "Stewie");     // 8
    private static Professor glen = new Professor(9, "Glen");       // 9


    private static final Course[] COURSES_LIST = {
            new Course(15, "math002", brian, 60, false),
            new Course(12, "math001", peter, 60, false),
            new Course(14, "math002", brian, 50, false),
            new Course(11, "math001", peter, 50, false),
            new Course(16, "soc100", meg, 45, true),
            new Course(17, "soc100", meg, 40, true),
            new Course(13, "math002", brian, 40, false),
            new Course(10, "math001", peter, 40, false),
            new Course(6, "cs015", sandro, 35, true),
            new Course(18, "soc100", meg, 35, true),
            new Course(7, "cs015", mrxxx, 35, false),
            new Course(8, "cs015", mrxxx, 35, false),
            new Course(9, "cs015", frederick, 35, false),
            new Course(22, "PSY200", glen, 35, false),
            new Course(21, "PSY200", glen, 30, false),
            new Course(23, "PSY200", glen, 30, false),
            new Course(24, "cs045", ohl, 20, true),
            new Course(25, "cs045", ohl, 20, true),
            new Course(26, "cs015", sandro, 20, true),
            new Course(1, "cs456", bilitski, 20, true),
            new Course(2, "cs456", bilitski, 20, true),
            new Course(4, "cs455", ohl, 20, true),
            new Course(5, "cs455", ohl, 20, true),
            new Course(3, "cs1783", bilitski, 15, true),
            new Course(19, "CS047", stewie, 15, true),
            new Course(20, "CS047", stewie, 15, true)
    };


    private static final TimePeriod[] TIMEPERIOD = {
            new TimePeriod(2, 9),
            new TimePeriod(3, 10),
            new TimePeriod(4, 11),
            new TimePeriod(5, 12),
            new TimePeriod(6, 1),
            new TimePeriod(7, 2),
            new TimePeriod(8, 3),
    };

    public static final List<TimePeriod> TIME_PERIODS = List.of(TIMEPERIOD);



    public static final List<Course> COURSES = List.of(COURSES_LIST);

    private static final Room[] ROOMS_LIST = {
            new Room(0, "BL134", 30, true),
            new Room(1, "BL138", 50, true),
            new Room(2, "KR224", 40, false),
            new Room(3, "KR206", 30, true),
            new Room(4, "Biddle123", 35, false),
            new Room(5, "Biddle205", 45, false),
            new Room(6, "ES100", 100, true),
    };

    public static final List<Room> ROOMS = List.of(ROOMS_LIST);
}
