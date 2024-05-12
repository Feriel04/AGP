package domain;

import java.util.ArrayList;
import java.util.Iterator;

public class Excursion {

	private ArrayList<Site> activities = new ArrayList<Site>();
	private Hostel hotel;
	private Hostel hotelfin;
	
	
	public Excursion() {
		
	}
	
    public Excursion(ArrayList<Site> activities, Hostel hotel,Hostel hotelfin) {
        this.activities = activities;
        this.hotel = hotel;
        this.setHotelfin(hotelfin);
    }
	
	public Excursion(Hostel hotel) {
		this.hotel = hotel;
	}
	
	public void addActivities(Site Site) {
		activities.add(Site);
	}
	
	public int calculatePriceExcursion() {
		int totalPrice = 0;
		Iterator<Site> itActivities = activities.iterator();
		while (itActivities.hasNext()) {
			totalPrice += itActivities.next().getPrice();
		}
		return totalPrice;
	}
	
	
	public double calculateScoreExcursion() {
		double score = 0;
		Iterator<Site> itActivities = activities.iterator();
		while (itActivities.hasNext()) {
			score += itActivities.next().getScore();
		}
		return score;
	}
	
	

	public ArrayList<Site> getActivities() {
		return activities;
	}


	public Hostel getHotel() {
		return hotel;
	}

	public void setHotel(Hostel hotel) {
		this.hotel = hotel;
	}
	
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---Excursion details:\n");
        result.append("------Hotel: ").append(hotel.getName()).append(" \n" + "------Hotel de fin :").append(hotelfin.getName());
        result.append("\n" + "------Number of Activities: ").append(activities.size()).append("\n");
        for (Site activity : activities) {
            result.append("------Activity: ").append(activity.getName()).append(", Type: ").append(activity.getType())
                    .append("\n ");
        }
        return result.toString();
    }

	public Hostel getHotelfin() {
		return hotelfin;
	}

	public void setHotelfin(Hostel hotelfin) {
		this.hotelfin = hotelfin;
	}
	
}