package process;
import java.util.Scanner;

public class PreferencesCollector {

    private Preferences preferences;

    public PreferencesCollector() {
        preferences = new Preferences();
    }

    public void collectPreferences() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your preferences:");

        System.out.print("Keyword: ");
        preferences.setKeyword(scanner.nextLine());
        
        System.out.print("intensity(léger, modéré, intense): ");
        preferences.setIntensity(scanner.nextLine());
        
        System.out.print("Duration (in days): ");
        preferences.setDuration(scanner.nextInt());
        scanner.nextLine(); 
  

        System.out.print("Comfort (bas, moyen, elevé): ");
        preferences.setComfort(scanner.nextLine());

        System.out.print("Minimum Price (per day): ");
        preferences.setMinPrice(scanner.nextInt());

        System.out.print("Maximum Price(per day): ");
        preferences.setMaxPrice(scanner.nextInt());
        scanner.nextLine(); 

        System.out.print("Activity (loisir, historique): ");
        preferences.setActivity(scanner.nextLine());

        System.out.print("Type of Hostel (Standard, Intermédiaire, Luxe): ");
        preferences.setTypeHostel(scanner.nextLine());

        System.out.print("Type of Transport (bateaux, marche, bus): ");
        preferences.setTypeTransport(scanner.nextLine());

        // scanner.close();
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void displayPreferences() {
        System.out.println("Your Preferences:");
        System.out.println("Keyword: " + preferences.getKeyword());
        System.out.println("Duration: " + preferences.getDuration());
        System.out.println("Comfort: " + preferences.getComfort());
        System.out.println("Intensity: " + preferences.getIntensity());
        System.out.println("Minimum Price: " + preferences.getMinPrice());
        System.out.println("Maximum Price: " + preferences.getMaxPrice());
        System.out.println("Activity: " + preferences.getActivity());
        System.out.println("Type of Hostel: " + preferences.getTypeHostel());
        System.out.println("Type of Transport: " + preferences.getTypeTransport());
    }
}