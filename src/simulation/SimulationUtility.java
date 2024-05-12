package simulation;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import domain.Activity;
import domain.Excursion;
import domain.Offer;
import domain.Position;
import domain.Transport;
import domain.Site;


public class SimulationUtility {

	public static Iterator<ArrayList<String>> RSTOIt(ResultSet rs, int rowCount) {
		Set<ArrayList<String>> set = new LinkedHashSet<ArrayList<String>>();
		try {
			while(rs.next()) {
				ArrayList<String> al = new ArrayList<String>();
				for(int i=1;i<=rowCount;i++) {
					al.add(rs.getString(i));
				}
			    set.add(al);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Iterator<ArrayList<String>> it = set.iterator();
		it.toString();
		return it;
	}


	public static int calculComfort(Transport transport, Activity activity, Position position) {
	    int comfortScore = 0;


	    comfortScore += transport.calculatePriceDistance();
	    if (comfortScore>100) {
	    	comfortScore=comfortScore/10;
	    }

	    comfortScore += position.getScore();

	    if (position.isSite()) {
	        comfortScore += position.getScore();
	    }
	    if (position.isHotel()) {
	        comfortScore += position.getScore();
	    }


	    comfortScore += activity.calculatePriceActivity()/10;
	    comfortScore = comfortScore/4;
	    return comfortScore;
	}

	public static float offerPrize(ArrayList<Offer> offers) {
        float prize=0;
        for(Offer offer: offers) {
            ArrayList<Excursion> excursions=offer.getExcursions();
            for(Excursion excursion: excursions ) {
                ArrayList<Site> site=excursion.getActivities();
                for (Site sites: site) {
                    prize+= sites.getPrice();
                }
            prize+= excursion.getHotelfin().getPrice();

        }

		
		
        }
		return prize;
	}
}