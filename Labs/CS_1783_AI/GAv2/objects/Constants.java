package objects;

import java.util.List;

public final class Constants
{

    public static final List<Professor> PROFESSOR_LIST = List.of(
            new Professor(0, "Bilitski"),   // 0
            new Professor(1, "Ohl"),        // 1
            new Professor(2, "Sandro"),     // 2
            new Professor(3, "Mr xxx"),     // 3
            new Professor(4, "Frederick"),  // 4
            new Professor(5, "Peter"),      // 5
            new Professor(6, "Brian"),      // 6
            new Professor(7, "Meg"),        // 7
            new Professor(8, "Stewie"),     // 8
            new Professor(9, "Glen")      // 9
    );

    public static final List<Gene> COURSE_LIST = List.of(
            new Gene(12, "math001", PROFESSOR_LIST.get(5), 60, false),
            new Gene(15, "math002", PROFESSOR_LIST.get(6), 60, false),
            new Gene(10, "math001", PROFESSOR_LIST.get(5), 40, false),
            new Gene(11, "math001", PROFESSOR_LIST.get(5), 50, false),
            new Gene(14, "math002",  PROFESSOR_LIST.get(6), 50, false),
            new Gene(13, "math002",  PROFESSOR_LIST.get(6), 40, false),
            new Gene(1, "cs456", PROFESSOR_LIST.get(0), 20, true),
            new Gene(2, "cs456", PROFESSOR_LIST.get(0), 20, true),
            new Gene(3, "cs1783", PROFESSOR_LIST.get(0), 15, true),
            new Gene(4, "cs455", PROFESSOR_LIST.get(1), 20, true),
            new Gene(5, "cs455", PROFESSOR_LIST.get(1), 20, true),
            new Gene(6, "cs015", PROFESSOR_LIST.get(2), 35, true),
            new Gene(7, "cs015", PROFESSOR_LIST.get(3), 35, false),
            new Gene(8, "cs015", PROFESSOR_LIST.get(3), 35, false),
            new Gene(9, "cs015", PROFESSOR_LIST.get(4), 35, false),
            new Gene(16, "Soc100", PROFESSOR_LIST.get(7), 45, true),
            new Gene(17, "Soc100", PROFESSOR_LIST.get(7), 40, true),
            new Gene(18, "Soc100", PROFESSOR_LIST.get(7), 35, true),
            new Gene(19, "CS047", PROFESSOR_LIST.get(8), 15, true),
            new Gene(20, "CS047", PROFESSOR_LIST.get(8), 15, true),
            new Gene(21, "PSY200", PROFESSOR_LIST.get(9), 30, false),
            new Gene(22, "PSY200", PROFESSOR_LIST.get(9), 35, false),
            new Gene(23, "PSY200", PROFESSOR_LIST.get(9), 30, false),
            new Gene(24, "cs045", PROFESSOR_LIST.get(1), 20, true),
            new Gene(25, "cs045", PROFESSOR_LIST.get(1), 20, true),
            new Gene(26, "cs015", PROFESSOR_LIST.get(2), 20, true)
    );

    public static final List<Room> ROOM_LIST = List.of(
            new Room(0, "BL134", 30, true),
            new Room(1, "BL138", 50, true),
            new Room(2, "KR224", 40, false),
            new Room(3, "KR206", 30, true),
            new Room(4, "Biddle123", 35, false),
            new Room(5, "Biddle205", 45, false),
            new Room(6, "ES100", 100, true)
    );

    public static final List<TimePeriod> TIME_PERIODS = List.of(
            new TimePeriod(0, 9),
            new TimePeriod(1,  10),
            new TimePeriod(2, 11),
            new TimePeriod(3, 12),
            new TimePeriod(4, 1),
            new TimePeriod(5,  2),
            new TimePeriod(6,  3)
    );

}
