package domain;


public class AutoBus implements Transport {

	public static final float PRICE_PER_KILOMETER_AUTOBUS = (float)1.60;
	private int distance;
	private int type = 2;
	
	public AutoBus() {
	}
	
	public AutoBus(int distance) {
		this.distance = distance;
	}
	
	public int calculatePriceDistance() {
		return Math.round(PRICE_PER_KILOMETER_AUTOBUS * (this.distance));
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
		return 0;
	}
	
}
