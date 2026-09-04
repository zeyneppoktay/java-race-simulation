
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Race race = new Race();
        Scanner scn = new Scanner(System.in);

        try {
            race.loadTrack("racetrack.txt");
            System.out.print("Enter number of cars: ");
            int n = scn.nextInt();
            race.createCars(n);
            race.startRace();

            System.out.print("\nCheck path (a b): ");
            int a = scn.nextInt();
            int b = scn.nextInt();

            try {
                boolean exists = race.hasPath(a, b);
                System.out.println("Exists: " + exists);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } catch (FileNotFoundException e) {
            System.out.println("racetrack.txt not found (nooo :0 )!");
        } catch (Exception e) {
            System.out.println("Unexpected error :( : " + e.getMessage());
        }

        scn.close();
    }
}


