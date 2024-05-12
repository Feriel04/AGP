package simulation;
public class ExcursionCalculator {

    public int calculateNumberOfExcursions(int durationOfStay, String intensityOfStay, int desiredPriceRange) {
        // Étape 1 : Définir le nombre maximal d'excursions (initialement 2)
        int maxExcursions = 2;

        // Étape 2 : Ajuster le nombre d'excursions en fonction de l'intensité du séjour
        maxExcursions = adjustExcursionsByIntensity(maxExcursions, durationOfStay, intensityOfStay);

        // Étape 3 : Ajuster le nombre d'excursions en fonction de la fourchette de prix
        maxExcursions = adjustExcursionsByPriceRange(maxExcursions, desiredPriceRange);

        // Étape 4 : Assurer que le nombre d'excursions reste dans les limites raisonnables
        return maxExcursions;
    }

    private int adjustExcursionsByIntensity(int maxExcursions, int durationOfStay, String intensityOfStay) {
        // Ajustements en fonction de l'intensité du séjour
        switch (intensityOfStay.toLowerCase()) {
            case "léger":
                maxExcursions = Math.min(maxExcursions, durationOfStay);
                break;
            case "modéré":
                maxExcursions = Math.min(maxExcursions+2, durationOfStay);
                break;
            case "intense":
                // Augmenter le nombre d'excursions pour les séjours intensifs
                maxExcursions = Math.max(maxExcursions, durationOfStay + 1);
                break;
            default:
                break;
        }
        return maxExcursions;
    }

    private int adjustExcursionsByPriceRange(int maxExcursions, int desiredPriceRange) {
        // Ajustements en fonction de la fourchette de prix (500 à 2000)
        int priceRangeAdjustment = (desiredPriceRange - 1000) / 500; // Plus grand budget donne un ajustement positif

        // Augmenter ou réduire le nombre d'excursions en fonction de l'ajustement de la fourchette de prix
        maxExcursions += priceRangeAdjustment;

        // S'assurer que le nombre d'excursions reste positif
        return Math.max(1, maxExcursions);
    }
}
