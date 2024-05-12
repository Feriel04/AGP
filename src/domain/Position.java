package domain;

public abstract class Position {
	
	int id;
	private String name;
	private String address;
	private float price;
	private int positionx;
	private int positiony;
	private String urlPicture;
	private double score;
	
	public Position() {
		
	}
	public abstract boolean isHotel();

	public abstract boolean isSite();
	
	public abstract double getDuration();
	
	
	public int compareTo(Position p) {
	        return (int) -(this.getScore() - p.getScore());
	 }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPositionx() {
		return positionx;
	}
	public int getPositiony() {
		return positiony;
	}

	public void setPositionx(int position) {
		this.positionx = position;
	}

	public void setPositiony(int position) {
		this.positiony = position;
	}

	public String getUrlPicture() {
		return urlPicture;
	}

	public void setUrlPicture(String urlPicture) {
		this.urlPicture = urlPicture;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
}
