import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Scanner;

public class Home {


    public static void main(String[] args) {


        Date journalDate = new Date();
        System.out.println("\n" + journalDate.today());

        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        String text = scanner.nextLine();


        try (InputStreamReader in = new InputStreamReader(System.in);
             BufferedReader buffer = new BufferedReader(in)) {
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] tokens = line.split("\\s");
                System.out.println(Arrays.toString(tokens));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>SAVED<<<<<");

    }


    }
