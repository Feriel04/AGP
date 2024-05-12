package domain;

public class Hostel extends Position {

	private String type;
	public Hostel() {
		
	}
	
	public boolean isHotel() {
		return true;
	}
	
	public boolean isSite() {
		return false;
	}

	
	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	
	
}
