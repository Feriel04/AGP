package process;

import java.util.ArrayList;
import java.util.List;

import domain.*;

public class OfferBuilder {

	    public static ArrayList<Offer> buildOffers(ArrayList<Excursion> excursions, int duration) {
	        ArrayList<Offer> offers = new ArrayList<>();

	        int maxExcursionsPerDay = 2;

	        // Distribuer les excursions sur les jours du voyage
	        for (int day = 1; day <= duration; day++) {
	            List<Excursion> dailyExcursions = new ArrayList<>();

	            // Déterminer le nombre d'excursions pour le jour actuel (entre 0 et 2)
	            int excursionsForDay = (int) (Math.random() * (maxExcursionsPerDay + 1));

	            for (int i = 0; i < excursionsForDay && !excursions.isEmpty(); i++) {
	                // Sélectionner une excursion au hasard parmi celles disponibles
	                int randomIndex = (int) (Math.random() * excursions.size());
	                Excursion selectedExcursion = excursions.get(randomIndex);

	                // Ajouter l'excursion sélectionnée à la liste des excursions du jour
	                dailyExcursions.add(selectedExcursion);

	                // Supprimer l'excursion sélectionnée de la liste des excursions disponibles
	                excursions.remove(randomIndex);
	            }

	            // Créer une offre pour le jour avec les excursions sélectionnées
	            Offer offer = new Offer(new ArrayList<>(dailyExcursions));
	            offers.add(offer);
	        }
			return offers;

	    }    
}
