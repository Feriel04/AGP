package domain;


public class Boat implements Transport {

	public static final float PRICE_PER_KILOMETER_BOAT = (float) 2.00;
	private int distance;
	private int type = 3;
	
	public Boat() {
	}
	
	public Boat(int distance) {
		this.distance = distance;
	}
	
	public int calculatePriceDistance() {
		return Math.round(PRICE_PER_KILOMETER_BOAT* (this.distance));
	}
	
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return this.distance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int calculatetimeTravel() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
