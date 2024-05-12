package domain;

import java.util.ArrayList;
import java.util.Iterator;

public class Offer {

	private ArrayList<Excursion> excursions = new ArrayList<Excursion>();
	private int totalPrice;

	public Offer(ArrayList<Excursion> excursions) {
		this.excursions = excursions;
	}

	public void addExcursion(Excursion excursion) {
		excursions.add(excursion);
	}

	public int calculatePriceOffer() {
		int totalPrice = 0;
		Iterator<Excursion> itExcursions = excursions.iterator();
		while(itExcursions.hasNext()) {
			totalPrice += itExcursions.next().calculatePriceExcursion();
		}
		return totalPrice;
	}

	public double calculateScoreOffer() {
		double score = 0;
		Iterator<Excursion> itExcursions = excursions.iterator();
		while(itExcursions.hasNext()) {
			score += itExcursions.next().calculateScoreExcursion();
		}
		return score;
	}
	
	public ArrayList<Excursion> getExcursions() {
		return excursions;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setExcursions(ArrayList<Excursion> excursions) {
		this.excursions = excursions;
	}
	
    private static void displayOffers(ArrayList<Offer> offers) {
        // Affichez les détails des offres générées
        for (int i = 0; i < offers.size(); i++) {
            System.out.println("Jour " + (i + 1) + ":");
            Offer offer = offers.get(i);
            for (int j = 0; j < offer.getExcursions().size(); j++) {
                System.out.println("Excursion " + (j + 1) + ":");
                System.out.println(offer.getExcursions().get(j));
            }
            System.out.println("Prix total de l'offre: " + offer.getTotalPrice());
            System.out.println("Score total de l'offre: " + offer.calculateScoreOffer());
            System.out.println("-----");
        }
    }


}
