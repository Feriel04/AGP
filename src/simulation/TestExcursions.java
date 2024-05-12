package simulation;

public class TestExcursions {
	 public static void main(String[] args) {

	        int durationOfStay = 3; // Durée du séjour en jours
	        String intensityOfStay = "intense"; // Intensité du séjour
	        int desiredPriceRange = 2000; // Fourchette de prix (1 à 5)

	        ExcursionCalculator calculator = new ExcursionCalculator();
	        int numberOfExcursions = calculator.calculateNumberOfExcursions(durationOfStay, intensityOfStay, desiredPriceRange);

	        System.out.println("Pour une durée de séjour de " + durationOfStay + " jours, une intensité " +
	                           "de séjour '" + intensityOfStay + "' et une fourchette de prix de " +
	                           desiredPriceRange + ", le nombre d'excursions calculé est : " + numberOfExcursions);
	    }

}
