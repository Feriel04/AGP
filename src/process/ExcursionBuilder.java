package process;

import java.util.ArrayList;
import java.util.Collections;

import domain.Activity;
import domain.Excursion;
import domain.Hostel;
import domain.Site;
import process.ExcursionBuilder;
import simulation.ExcursionCalculator;

public class ExcursionBuilder {
    // Nombre maximum de sites par excursion
    static int MAX_SITES_PER_EXCURSION = 2;

    // Méthode pour créer une seule excursion
    public static Excursion ExcursionCreator(ArrayList<Site> site, ArrayList<Hostel> hotel, Hostel depart, int indexcursion) {

        // Initialiser le trieur pour trier les sites en fonction de certains critères
        ActivitySorter sorter = new ActivitySorter(site);
        
        // Obtenir une liste triée d'activités/sites
        ArrayList<Site> siteListe = sorter.getActivities();
        ArrayList<Site> siteSousListe = new ArrayList<Site>();
        
        // Trier les sites par distance minimale
        siteListe = sorter.sortSitesByMinimumDistance(siteListe);

        // Si ce n'est pas la première excursion, mélanger la liste des sites pour plus de variété
        if (indexcursion > 1) {
            Collections.shuffle(siteListe);
        }

        // Trouver l'hôtel le plus proche du premier site
        Hostel hostelnear = findNearestHotel(siteListe.get(0), hotel, null);
        
        // Si ce n'est pas la première excursion, définir l'hôtel de départ comme l'hôtel de fin de la dernière excursion
        if (indexcursion >= 1) {
            hostelnear = depart;
        }

        // Trouver l'hôtel le plus proche du dernier site, en excluant l'hôtel de départ
        Hostel hostelnear2 = findNearestHotel(siteListe.get(siteListe.size() - 1), hotel, hostelnear);

        // Ajouter les sites à l'excursion, sans dépasser la limite maximale
        for (int i = 0; i < siteListe.size() && i < MAX_SITES_PER_EXCURSION; i++) {
            siteSousListe.add(siteListe.get(i));
        }

        // Créer un nouvel objet Excursion avec les sites et hôtels sélectionnés
        Excursion excursion = new Excursion(siteSousListe, hostelnear, hostelnear2);

        return excursion;
    }

    // Méthode pour créer plusieurs excursions en fonction des préférences de l'utilisateur
    public static ArrayList<Excursion> Excursionscreator(ArrayList<Site> site, ArrayList<Hostel> hotel, Preferences preference) {
        ArrayList<Excursion> excursions = new ArrayList<Excursion>();
        ExcursionCalculator calculator = new ExcursionCalculator();

        // Obtenir les préférences de l'utilisateur
        int durationOfStay = preference.getDuration();
        String intensityOfStay = preference.getIntensity();
        int desiredPriceRange = (preference.getMaxPrice() + preference.getMinPrice() / 2);
        int numberOfExcursions = 2;

        // Déterminer le nombre d'excursions en fonction de l'intensité du séjour
        switch (intensityOfStay) {
            case "léger":
                numberOfExcursions = calculator.calculateNumberOfExcursions(durationOfStay, intensityOfStay, desiredPriceRange / 2);
                break;
            case "modéré":
                numberOfExcursions = calculator.calculateNumberOfExcursions(durationOfStay, intensityOfStay, desiredPriceRange);
                break;
            case "intense":
                numberOfExcursions = calculator.calculateNumberOfExcursions(durationOfStay, intensityOfStay, desiredPriceRange * 2);
        }

        // Initialiser l'hôtel de départ
        Hostel depart = new Hostel();

        // Créer des excursions et les ajouter à la liste
        for (int i = 0; i < numberOfExcursions; i++) {
            Excursion newExcursion = ExcursionCreator(site, hotel, depart, i);
            depart = newExcursion.getHotelfin();
            excursions.add(newExcursion);
        }

        return excursions;
    }

    // Méthode pour trouver l'hôtel le plus proche d'un site donné
    private static Hostel findNearestHotel(Site site, ArrayList<Hostel> hotels, Hostel excludeHotel) {
        Hostel nearestHotel = null;
        int minDistance = Integer.MAX_VALUE;
        for (Hostel hotel : hotels) {
            // Exclure l'hôtel spécifié s'il n'est pas nul
            if (excludeHotel != null && hotel.equals(excludeHotel)) {
                continue;
            }
            // Calculer la distance du site à l'hôtel
            int distance = calculateDistanceSiteHostel(site, hotel);
            // Mettre à jour l'hôtel le plus proche si cet hôtel est plus près
            if (distance < minDistance) {
                minDistance = distance;
                nearestHotel = hotel;
            }
        }
        return nearestHotel;
    }

    // Méthode pour calculer la distance entre un site et un hôtel
    private static int calculateDistanceSiteHostel(Site site1, Hostel site2) {
        int dx = site1.getPositionx() - site2.getPositionx();
        int dy = site1.getPositiony() - site2.getPositiony();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}