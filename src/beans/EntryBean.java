package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.logging.Logger;


import process.Preferences;
import spring.SpringIoC;

@ManagedBean
@SessionScoped
public class EntryBean implements Serializable {

    private static final long serialVersionUID = 6955508471291131930L;
    private static final Logger LOGGER = Logger.getLogger(EntryBean.class.getName());
	private Preferences Preferences = new Preferences();

	public EntryBean() {
	}
	
	@PostConstruct
    public void init() {
		Preferences = (Preferences) SpringIoC.getBean("Preferences");
    }

    public String search() {
        String results = "testForm";
        return results;
    }

    // Getter et Setter pour keyword
    public String getKeyword() {
        return Preferences.getKeyword();
    }

    public void setKeyword(String keyword) {
    	this.Preferences.setKeyword(keyword);
        LOGGER.info("Keyword set to: " + keyword);
    }

    // Getter et Setter pour duration
    public int getDuration() {
        return Preferences.getDuration();
    }

    public void setDuration(int duration) {
    	this.Preferences.setDuration(duration);
    	LOGGER.info("Duration set to: " + duration);
    }

    // Getter et Setter pour comfort
    public String getComfort() {
        return Preferences.getComfort();
    }

    public void setComfort(String comfort) {
    	this.Preferences.setComfort(comfort);
    	LOGGER.info("Comfort set to: " + comfort);
    }

    // Getter et Setter pour minPrice
    public int getMinPrice() {
        return Preferences.getMinPrice();
    }

    public void setMinPrice(int minPrice) {
    	this.Preferences.setMinPrice(minPrice);
    	LOGGER.info("MinPrice set to: " + minPrice);
    }

    // Getter et Setter pour maxPrice
    public int getMaxPrice() {
        return Preferences.getMaxPrice();
    }

    public void setMaxPrice(int maxPrice) {
    	this.Preferences.setMaxPrice(maxPrice);
    	LOGGER.info("MaxPrice set to: " + maxPrice);
    }

    // Getter et Setter pour Activity
    public String getActivity() {
        return Preferences.getActivity();
    }

    public void setActivity(String activity) {
    	this.Preferences.setActivity(activity);
    	LOGGER.info("Activity set to: " + activity);
    }

    // Getter et Setter pour hostel
    public String getTypeHostel() {
        return Preferences.getTypeHostel();
    }

    public void setTypeHostel(String typeHostel) {
    	this.Preferences.setTypeHostel(typeHostel);
    	LOGGER.info("TypeHostel set to: " + typeHostel);
    }

    // Getter et Setter pour transport
    public String getTypeTransport() {
        return Preferences.getTypeTransport();
    }

    public void setTypeTransport(String typeTransport) {
    	this.Preferences.setTypeTransport(typeTransport);
    	LOGGER.info("TypeTransport set to: " + typeTransport);
    }

    public String getIntensity() {
        return Preferences.getIntensity();
    }

    public void setIntensity(String intensity) {
    	this.Preferences.setIntensity(intensity);
    	LOGGER.info("MaxPrice set to: " + intensity);
    }


}
