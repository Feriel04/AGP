package process;


import java.util.List;
import java.io.Serializable;

public class Preferences {

	private String keyword;
	private int duration;
	private String comfort;
	private int minPrice;
	private int maxPrice;
	private String travelIntensity;
	private String activity;
	private String typeHostel;
	private String typeTransport;

	
	public Preferences() {
		
	}
	
	public Preferences(String keyword, int duration, String comfort, int minPrice, int maxPrice, String activity,
            String typeHostel, String typeTransport,String intensity) {
        super();
        this.keyword = keyword;
        this.duration = duration;  
        this.comfort = comfort;  
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.activity = activity;
        this.typeHostel = typeHostel;
        this.typeTransport = typeTransport;
        this.travelIntensity = intensity;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTypeHostel() {
        return typeHostel;
    }

    public void setTypeHostel(String typeHostel) {
        this.typeHostel = typeHostel;
    }

    public String getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }

	public void setIntensity(String nextLine) {
		 this.travelIntensity = nextLine;	
	}
	public String getIntensity() {
        return travelIntensity;
    }
}