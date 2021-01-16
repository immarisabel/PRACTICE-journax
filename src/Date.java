import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {


    public String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        String todayTime = dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));
        return todayTime;

    }
}