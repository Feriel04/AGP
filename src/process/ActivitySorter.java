package process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import domain.Activity;
import domain.Site;

public class ActivitySorter {

    private ArrayList<Site> activities = new ArrayList<Site>();

    // Constructeur
    public ActivitySorter(ArrayList<Site> activities) {
        this.activities = activities;
    }
    
    public ArrayList<Site> sortSitesByMinimumDistance(ArrayList<Site> activities) {
        if (activities == null || activities.size() == 0) {
            return new ArrayList<>();
        }

        ArrayList<Site> sortedSites = new ArrayList<>();
        ArrayList<Site> activitiesCopy = new ArrayList<>(activities);
        Site currentSite = activitiesCopy.remove(0);
        sortedSites.add(currentSite);

        while (!activitiesCopy.isEmpty()) {
            Site nearestSite = null;
            int minDistance = Integer.MAX_VALUE;

            for (Site site : activitiesCopy) {
                int distance = calculateDistance(currentSite, site);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestSite = site;
                }
            }

            if (nearestSite != null) {
                sortedSites.add(nearestSite);
                activitiesCopy.remove(nearestSite);
                currentSite = nearestSite;
            }
        }

        return sortedSites;
    }


    private int calculateDistance(Site site1, Site site2) {
        int dx = site1.getPositionx() - site2.getPositionx();
        int dy = site1.getPositiony() - site2.getPositiony();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    
    public ArrayList<Site> getActivities(){
		return activities;
    	
    }
    
    public void setActivities(ArrayList<Site> activitie){
		this.activities=activitie;
    	
    }
 
}
