package domain;



public class Walk implements Transport {

	private int distance;
	private int type = 1;
	
	public Walk() {
	}
	
	public Walk(int distance) {
		this.distance = distance;
	}
	
	public int calculatePriceDistance() {
		return 0;
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
