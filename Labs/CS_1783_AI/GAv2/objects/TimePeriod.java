package objects;

import java.time.DayOfWeek;
import java.util.List;

public class TimePeriod {
    private int timePeriodId;
    private final List<DayOfWeek> days = List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.FRIDAY
    );

    private int startTime;

    public TimePeriod(int timePeriodId, int startTime) {
        this.timePeriodId = timePeriodId;
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getTimePeriodId() {
        return timePeriodId;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setTimePeriodId(int timePeriodId) {
        this.timePeriodId = timePeriodId;
    }

    @Override
    public String toString() {
        return String.format("TimePeriod { %d } Start Time: %s | ", timePeriodId, startTime);
    }

    public TimePeriod deepCopy() {
        return new TimePeriod(timePeriodId, startTime);
    }
}
