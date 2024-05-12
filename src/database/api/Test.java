package database.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.orsoncharts.util.json.parser.ParseException;

import domain.Hostel;
import domain.Site;

public class Test {
    public static void main(String[] args) throws IOException, ParseException {
        Connection connection = null;

        try {
            // Établissez la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost/sejours?useSSL=false&serverTimezone=UTC", "root", "");

            // Instanciez la classe Db_API en passant la connexion
            Db_API instance = new Db_API(connection);

            // Test de la fonction get_lucene_Query()
            testGetLuceneQuery(instance);

            
            //test POUR HOSTEL AVEC WHERE
            
            Iterator<Hostel> it_hostel = instance.get_hostel("price>='230'");

            while (it_hostel.hasNext()) {
                Hostel hostel = it_hostel.next();
                System.out.println("Name: " + hostel.getName());
                System.out.println("adress: " + hostel.getAddress());
                System.out.println("UrlPicture: " + hostel.getUrlPicture());
                System.out.println("Type: " + hostel.getType());
                System.out.println("Price: " + hostel.getPrice());
                System.out.println("PosX: " + hostel.getPositionx());
                System.out.println("PosY: " + hostel.getPositiony());
                System.out.println("------------------------------");
            }
            
            
            //Test POUR SITE avec WHERE
           
            Iterator<Site> it_site = instance.get_site("typeSite='historique'");

            while (it_site.hasNext()) {
                Site site = it_site.next();
                System.out.println("Name: " + site.getName());
                System.out.println("TextDesc: " + site.getDescription());
                System.out.println("adress: " + site.getAddress());
                System.out.println("UrlPicture: " + site.getUrlPicture());
                System.out.println("Type: " + site.getType());
                System.out.println("PosX: " + site.getPositionx());
                System.out.println("PosY: " + site.getPositiony());
                System.out.println("------------------------------");
            }
          
			
            // Fermez la connexion après utilisation
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
         
         
        
    }

 
    
    public static void testGetLuceneQuery(Db_API instance) {
        try {
            System.out.println("Testing get_lucene_Query...");

            // Test de la fonction avec une requête qui combine Lucene et JDBC
            List<Map<String, String>> resultCombined = instance.getLuceneQuery("SELECT * FROM SiteTouristic INNER JOIN Coordinates ON SiteTouristic.idCoordinate = Coordinates.idCoordinate where typeSite ='loisir' WITH sable;");

            for (Map<String, String> resultEntry : resultCombined) {
                System.out.println("LuceneKey: " + resultEntry.get("LuceneKey"));
                System.out.println("Score: " + resultEntry.get("Score"));
                System.out.println("Description: " + resultEntry.get("JDBCInfo3"));
                System.out.println("Image URL: " + resultEntry.get("JDBCInfo4"));
                System.out.println("Type de site: " + resultEntry.get("JDBCInfo5"));
                System.out.println("---------");
            }

            System.out.println("Test get_lucene_Query complete.");
         
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
