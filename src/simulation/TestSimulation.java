package simulation;

import process.PreferencesCollector;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.parser.ParseException;

import database.api.Db_API;
import database.api.Test;
import domain.Excursion;
import domain.Hostel;
import domain.Offer;
import domain.Site;
import process.ExcursionBuilder;
import process.OfferBuilder;
import process.Preferences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestSimulation {
    public static void main(String[] args) throws com.orsoncharts.util.json.parser.ParseException {
        // Initialisez la connexion à la base de données
        Connection connection = null;
        try {
        	 // Établissez la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost/sejours?useSSL=false&serverTimezone=UTC", "root", "");

            // Chargez le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Instanciez la classe PreferencesCollector
        PreferencesCollector preferencesCollector = new PreferencesCollector();

        // Collectez les préférences du client
        preferencesCollector.collectPreferences();

        // Récupérez les préférences
        Preferences preferences = preferencesCollector.getPreferences();

        // Affichez les préférences
        preferencesCollector.displayPreferences();
        
        System.out.println("--------------------");

        // Utilisez Db_API pour obtenir la liste des hôtels selon les préférences
        Db_API instance = new Db_API(connection);
        

     // Obtenez les valeurs de minPrice, maxPrice, et typeHostel à partir des préférences
        int minPrice = preferences.getMinPrice();
        int maxPrice = preferences.getMaxPrice();
        String typeHostel = preferences.getTypeHostel();

        // Construisez la requête en fonction des préférences
        String query = "price BETWEEN " + minPrice + " AND " + maxPrice;
        query += " AND scale = '" + typeHostel + "'";
      
        ArrayList<Hostel> hotels = new ArrayList<>();
        try {
            // Obtenez l'itérateur des hôtels en fonction des préférences
            Iterator<Hostel> hostelIterator = instance.get_hostel(query);

            	

            // Parcourez et affichez la liste des hôtels
            while (hostelIterator.hasNext()) {
                Hostel hostel = hostelIterator.next();
                /*
                System.out.println("Hotel Name: " + hostel.getName());
                System.out.println("Hotel adress: " + hostel.getAddress());
                System.out.println("Hotel url_picture: " + hostel.getUrlPicture());
                System.out.println("Hotel type: " + hostel.getType());
                System.out.println("Hotel price: " + hostel.getPrice());
                System.out.println("Hotel position x: " + hostel.getPositionx());
                System.out.println("Hotel position y: " + hostel.getPositiony());
                */
                hotels.add(hostel);
                
            }
                    
                
        } catch (IOException e) {
            e.printStackTrace();
        }

        String Activity = preferences.getActivity();
        String keyword = preferences.getKeyword();

        // Construisez la requête en fonction des préférences
        /*
        String lucenequery = "SELECT * FROM SiteTouristic INNER JOIN Coordinates ON SiteTouristic.idCoordinate = Coordinates.idCoordinate where " ;
        lucenequery		+= "typeSite = '" + Activity + "' WITH " + keyword + ";";
        System.out.println("query : " + lucenequery );
		*/

        ArrayList<Site> sites = new ArrayList<>();
        try {

            
            List<Map<String, String>> resultCombined = instance.getLuceneQuery("SELECT * FROM SiteTouristic INNER JOIN Coordinates ON "
            		+ "SiteTouristic.idCoordinate = Coordinates.idCoordinate where typeSite = '" + Activity + "' WITH " + keyword + ";");

            // Parcourez et affichez la liste des sites
            for (Map<String, String> resultEntry : resultCombined) {
            	/*
            	System.out.println("~~~~~~");
                System.out.println("LuceneKey: " + resultEntry.get("LuceneKey"));
                System.out.println("Score: " + resultEntry.get("Score"));
                System.out.println("Site Name: " + resultEntry.get("JDBCInfo1"));
                System.out.println("Site adress: " + resultEntry.get("JDBCInfo2"));
                System.out.println("Site description: " + resultEntry.get("JDBCInfo3"));
                System.out.println("Site URL_picture: " + resultEntry.get("JDBCInfo4"));
                System.out.println("Site type: " + resultEntry.get("JDBCInfo5"));
                */
                //sites.add(resultEntry);
                Site site = new Site();
                site.setName(resultEntry.get("JDBCInfo1"));
                site.setAddress(resultEntry.get("JDBCInfo2"));
                site.setDescription(resultEntry.get("JDBCInfo3"));
                site.setUrlPicture(resultEntry.get("JDBCInfo4"));
                site.setType(resultEntry.get("JDBCInfo5"));

                // Ajoutez le site à la liste
                sites.add(site);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("\n" + "Partie affichage excursions -------------" + "\n");

        // Appelez Excursionscreator
        /*
         ArrayList<Excursion> excursions = ExcursionBuilder.Excursionscreator(sites, hotels, preferences);

        // Affichez les détails des excursions générées
        for (Excursion excursion : excursions) {
            System.out.println("---Excursion details:");
            System.out.println("~~~~~~Hotel de départ: " + excursion.getHotel().getName());
            System.out.println("~~~~~~Hotel de fin: " + excursion.getHotelfin().getName());
            System.out.println("~~~~~~Number of Activities: " + excursion.getActivities().size());
            for (Site site : excursion.getActivities()) {
                //System.out.println("~~~~~~Activity: " + site.getName() + ", Type: " + site.getType());
            }
            System.out.println();
        }
    	*/
        
        System.out.println("\n" + "Partie affichage offres -------------" + "\n");
        
        for (int cpt = 1; cpt < 4 ; cpt++) {
        	System.out.println("\n" + "------------Offre numéro " + cpt + "--------------\n");
        	ArrayList<Excursion> excursions = ExcursionBuilder.Excursionscreator(sites, hotels, preferences);
	        ArrayList<Offer> offers = OfferBuilder.buildOffers(excursions, preferences.getDuration());
	        
	        // Affichez les détails des offres générées
	        for (int i = 0; i < offers.size(); i++) {
	            System.out.println("-Jour " + (i + 1) + ":");
	            Offer offer = offers.get(i);
	            for (int j = 0; j < offer.getExcursions().size(); j++) {
	                System.out.println("~~Excursion " + (j + 1) + ":");
	                System.out.println(offer.getExcursions().get(j));
	            }
	            //System.out.println("Prix total de l'offre: " + SimulationUtility.offerPrize(offer));
	        }
	       System.out.println("Prix total de l'offre: " + SimulationUtility.offerPrize(offers));
	        
        }

     }
}