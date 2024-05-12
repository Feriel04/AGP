package domain;

public class Site extends Position {

	private String description;
	private String type;
	
	public Site() {
		
	}
	public Site(String description, String type, String name, String address,
            int price, int positionx, int positiony, String urlPicture, int score) {
    this.description = description;
    this.type = type;
    this.setName(name);
    this.setAddress(address);
    this.setPrice(price);
    this.setPositionx(positionx);
    this.setPositiony(positiony);
    this.setUrlPicture(urlPicture);
    this.setScore(score);
	}
	public boolean isTouristicSite() {
		return this.type == "historique";
	}
	
	public boolean isActivity() {
		return this.type == "loisir";
	}
	
	public boolean isSite() {
		return true;
	}
	
	public boolean isHotel() {
		return false;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Site [description=" + description  + ", type=" + type + ", id=" + id
				+ ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getPrice()=" + getPrice() + ", getPositionx()=" + getPositionx() +", getPositiony()=" + getPositiony() 
				+ ", getUrlPicture()=" + getUrlPicture() + ", getScore()=" + getScore() + "]";
	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Site getTransportBetweenPlaces() {
		// TODO Auto-generated method stub
		return null;
	}

	
}