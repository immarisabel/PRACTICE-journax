public class Home {

static String name = null;
static String date = null;
static int PIN = 0000;
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        System.out.println("\nWelcome to the journal of " + name +"!\n");


        System.out.println("To unlock, please type in your PIN number.\n");

        while(PIN==TRUE) {
            MainScreen.Hello();
        }
        {
            System.out.println("Sorry, wrong PIN.");
            //LOOP BACK (see game loop)
        }



    }
    }
