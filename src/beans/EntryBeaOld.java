package beans;

import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class EntryBeaOld implements Serializable {

    private static final long serialVersionUID = 6955508471291131930L;

    private String keyword;
    private int duration;
    private String comfort;
    private int minPrice;
    private int maxPrice;
    private String Activity;
    private String typeHostel;
    private String typeTransport;

    public String search() {
        String results;
        if ("error".equals(keyword)) {
            results = "problem";
        } else {
            results = "testForm";
        }

        return results;
    }

    // Getter et Setter pour keyword
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // Getter et Setter pour duration
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Getter et Setter pour comfort
    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    // Getter et Setter pour minPrice
    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    // Getter et Setter pour maxPrice
    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    // Getter et Setter pour Activity
    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    // Getter et Setter pour hostel
    public String getTypeHostel() {
        return typeHostel;
    }

    public void setTypeHostel(String typeHostel) {
        this.typeHostel = typeHostel;
    }

    // Getter et Setter pour transport
    public String getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }


    // Ajoutez d'autres getters et setters au besoin
}
