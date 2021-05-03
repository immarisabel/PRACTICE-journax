package nl.marisabel.journal;

import static nl.marisabel.Colors.ANSI_GREEN;
import static nl.marisabel.Colors.ANSI_PURPLE;


public class test
{

    public static void main(String[] args)
    {
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        Thread anotherThread = new Thread();
        anotherThread.start();

        new Thread(() -> System.out.println(ANSI_GREEN + "Hello from the anonymous class thread")).start();

        System.out.println(ANSI_PURPLE + "Hello again from the main thread.");


    }
}
