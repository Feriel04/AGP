package domain;

public class Activity {
	private Position placeToVisit;
	private Transport transportBetweenPlaces;

	public Activity() {
		
	}
	
	public Activity(Position placeToVisit, Transport transportBetweenPlaces) {
		this.placeToVisit = placeToVisit;
		this.transportBetweenPlaces = transportBetweenPlaces;	
	}
	
	public int calculatePriceActivity() {
		int price = 0;
		price = this.transportBetweenPlaces.calculatePriceDistance();
		if(placeToVisit.isSite()) {
			price += this.placeToVisit.getPrice();
		}

		return price;
	}
	
	public int calculateTimeActivity() {
		int timeSpend= 0;
		timeSpend = this.transportBetweenPlaces.calculatetimeTravel();
		if(placeToVisit.isSite()) {
			timeSpend += this.getPositionToVisit().getDuration();
		}
		return timeSpend;
	}
	
	public int getTimeTransport() {
		return this.transportBetweenPlaces.calculatetimeTravel();
	}
	
	public double getScoreSite() {
		return this.placeToVisit.getScore();
	}

	public Position getPositionToVisit() {
		return placeToVisit;
	}

	public void setPositionToVisit(Position placeToVisit) {
		this.placeToVisit = placeToVisit;
	}

	public Transport getTransportBetweenPlaces() {
		return transportBetweenPlaces;
	}

	public void setTransportBetweenPlaces(Transport transportBetweenPlaces) {
		this.transportBetweenPlaces = transportBetweenPlaces;
	}

	@Override
	public String toString() {
		return "Activity [placeToVisit=" + placeToVisit.getName()+ "]";
	}
}
