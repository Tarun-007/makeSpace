import java.time.LocalTime;

public interface HotelService {
    public String bookRoom(Integer count, LocalTime startTime, LocalTime endTime);
    public String checkAvailability(LocalTime startTime, LocalTime endTime);
}
