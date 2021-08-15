package nl.marisabel.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));

    }
}
