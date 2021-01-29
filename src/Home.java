import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Home {


    public static void main(String[] args) throws ClassNotFoundException {

        SQLite journal = new SQLite("","","");

        journal.addEntry("Title Two",today(),"blah blah blah");

        System.out.println("WELCOME TO THE JOURNAL!\n♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ ♥ \n> > > ALL ENTRIES:");

        journal.getEntries();

    }


    /*date GENERATOR*/

    public static String today() {

        LocalDateTime dateTime = LocalDateTime.now();
        String todayTime = dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu @ HH:mm"));
        return todayTime;

    }
}
