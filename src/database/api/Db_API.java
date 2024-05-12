package database.api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.orsoncharts.util.json.parser.ParseException;

import database.lucene.LuceneConstants;
import database.lucene.LuceneIndexer;
import database.lucene.Searcher;
import domain.Hostel;
import domain.Site;
import simulation.SimulationUtility;
import spring.SpringIoC;

public class Db_API {
	
	Connection conn = null;
	

	public Db_API(Connection conn) {
		this.conn = conn;
		

		
 }
	
	private Iterator<ArrayList<String>> get_Query(String query) throws IOException, ParseException {
		Iterator<ArrayList<String>> it = null;
		if(query.contains("WITH")) {
			it =(Iterator<ArrayList<String>>) getLuceneQuery(query);
		}
		else {
			it = get_jdbc_Query(query);
		}

		return it;

	}
	
public Iterator<Site> get_site() throws IOException, ParseException{
		
		Iterator<ArrayList<String>> iterator = get_Query("SELECT * FROM SiteTouristic INNER JOIN Coordinates ON SiteTouristic.idCoordinate = Coordinates.idCoordinate ;");
		ArrayList<Site> listSites = new ArrayList<Site>();

	
		    while(iterator.hasNext()) {
		    ArrayList<String> al = iterator.next();
		    Site s = (Site) SpringIoC.getBean("siteBean");
		    s.setPositionx(Integer.valueOf(al.get(6)));
		    s.setPositiony(Integer.valueOf(al.get(7)));
		    s.setType(String.valueOf(al.get(5)));
		    s.setUrlPicture(String.valueOf(al.get(4)));
		    s.setId(Integer.valueOf(al.get(0)));
		    s.setDescription(String.valueOf(al.get(3)));
		    s.setAddress(String.valueOf(al.get(2)));
		    s.setName(String.valueOf(al.get(1)));
		    listSites.add(s);
		}	
		Iterator<Site> it_site = listSites.iterator();
		
		return it_site;
	}


public Iterator<Hostel> get_hostel() throws IOException, ParseException{
		
		Iterator<ArrayList<String>> iterator = get_Query("SELECT * FROM Hostel INNER JOIN Coordinates ON Hostel.idCoordinate = Coordinates.idCoordinate ;");
		ArrayList<Hostel> listHostel = new ArrayList<Hostel>();

	
		    while(iterator.hasNext()) {
		    ArrayList<String> al = iterator.next();
		    Hostel h = (Hostel) SpringIoC.getBean("hostelBean");
		    h.setPositionx(Integer.valueOf(al.get(8)));
		    h.setPositiony(Integer.valueOf(al.get(9)));
		    h.setPrice(Integer.valueOf(al.get(6)));
		    h.setUrlPicture(String.valueOf(al.get(3)));
		    h.setId(Integer.valueOf(al.get(0)));
		    h.setType(String.valueOf(al.get(5)));
		    h.setAddress(String.valueOf(al.get(2)));
		    h.setName(String.valueOf(al.get(1)));
		    listHostel.add(h);
		}	
		Iterator<Hostel> it_hostel = listHostel.iterator();
		
		return it_hostel;
	}
	
	

//Requete site avec WHERE
	public Iterator<Site> get_site(String query_Where) throws IOException, ParseException{
		
		Iterator<ArrayList<String>> iterator = get_Query("SELECT title,textualDescription,adress,url_picture,typeSite,positionX, positionY FROM SiteTouristic st INNER JOIN Coordinates ON st.idCoordinate = Coordinates.idCoordinate Where " + query_Where + " ;");
		ArrayList<Site> listSites = new ArrayList<Site>();

	
		    while(iterator.hasNext()) {
		    ArrayList<String> al = iterator.next();
		    Site s = (Site) SpringIoC.getBean("siteBean");
		    s.setPositionx(Integer.valueOf(al.get(5)));
		    s.setPositiony(Integer.valueOf(al.get(6)));
		    s.setType(String.valueOf(al.get(4)));
		    s.setUrlPicture(String.valueOf(al.get(3)));
		    s.setAddress(String.valueOf(al.get(2)));
		    s.setName(String.valueOf(al.get(0)));
		    s.setDescription(String.valueOf(al.get(1)));
		    listSites.add(s);
		}	
		Iterator<Site> it_site = listSites.iterator();
		
		return it_site;
	}


//requete Hotel avec WHERE
	public Iterator<Hostel> get_hostel(String query_Where) throws IOException, ParseException {
	    Iterator<ArrayList<String>> iterator = get_Query("SELECT title, adress, url_picture, scale, price, positionX, positionY FROM Hostel INNER JOIN Coordinates ON Hostel.idCoordinate = Coordinates.idCoordinate WHERE " + query_Where + " ;");
	    ArrayList<Hostel> listHostel = new ArrayList<>();

	    while (iterator.hasNext()) {
	        ArrayList<String> al = iterator.next();
	        Hostel h = (Hostel) SpringIoC.getBean("hostelBean");
	        h.setName(String.valueOf(al.get(0)));
	        h.setAddress(String.valueOf(al.get(1)));
	        h.setUrlPicture(String.valueOf(al.get(2)));
	        h.setType(String.valueOf(al.get(3)));
	        h.setPrice(Float.valueOf(al.get(4)));
	        h.setPositionx(Integer.valueOf(al.get(5)));
	        h.setPositiony(Integer.valueOf(al.get(6)));
	        listHostel.add(h);
	    }

	    Iterator<Hostel> it_hostel = listHostel.iterator();

	    return it_hostel;
	}


	
	private Iterator<ArrayList<String>> get_jdbc_Query(String query) {
		Statement statement;
		Iterator<ArrayList<String>> it = null;
		ResultSet rs = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nb_col = 0;
		if(query.contains("hostel")) {
			nb_col = 7;
		}
		else if(query.contains("SiteTouristic")) {
			nb_col = 6;
		}
		else {
			nb_col = 2;
		}
		if(query.contains("INNER JOIN Coordinates")) {
			nb_col = 7;
		}
		it = SimulationUtility.RSTOIt(rs, nb_col);

		return it;

	}
	
	public List<Map<String, String>> getLuceneQuery(String query) throws IOException, ParseException {
        String lucene = null;
        String jdbc = null;
        String split[] = query.split(" WITH ");
        jdbc = split[0];
        if (split.length > 1) {
            lucene = split[1];
        } else {
            lucene = "*:*";
        }

        // requete JDBC
        Iterator<ArrayList<String>> it_jdbc = get_Query(jdbc);

        Searcher searcher = new Searcher("Index");
        TopDocs topDocs = searcher.searchByQuery(searcher.getQueryBuilder().createBooleanQuery(LuceneConstants.CONTENTS, lucene));

        Set<ArrayList<String>> set_lucenetoIT = new LinkedHashSet<>();
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = searcher.getDocument(scoreDoc);
            ArrayList<String> le_tab = new ArrayList<>();
            le_tab.add(document.get(LuceneConstants.FILE_NAME).split(".txt")[0]);
            le_tab.add(Float.toString(scoreDoc.score));
            le_tab.add(document.get(LuceneConstants.CONTENTS));
            set_lucenetoIT.add(le_tab);
        }

        // Combinaison des deux parties de requete
        List<Map<String, String>> resultFinalList = new ArrayList<>();
        while (it_jdbc.hasNext()) {
            ArrayList<String> str_jdbc = it_jdbc.next();
            for (ArrayList<String> str_lucene : set_lucenetoIT) {
                if (str_jdbc.get(0).equals(str_lucene.get(0))) {
                    Map<String, String> resultEntry = new HashMap<>();
                    resultEntry.put("LuceneKey", str_lucene.get(0));
                    resultEntry.put("Score", str_lucene.get(1));
                    

                    for (int i = 1; i < str_jdbc.size(); i++) {
                        resultEntry.put("JDBCInfo" + i, str_jdbc.get(i));
                    }

                    resultFinalList.add(resultEntry);
                }
            }
        }

        // Triez la liste résultante par ordre décroissant des scores
        List<Map<String, String>> sortedResultList = sortResultsByScore(resultFinalList);

        return sortedResultList;
    }
	
	private List<Map<String, String>> sortResultsByScore(List<Map<String, String>> resultList) {
	    // Utilisez un Comparator pour comparer les scores
	    Comparator<Map<String, String>> scoreComparator = (result1, result2) -> {
	        float score1 = Float.parseFloat(result1.get("Score"));
	        float score2 = Float.parseFloat(result2.get("Score"));
	        return Float.compare(score2, score1); // Comparez les scores de manière décroissante
	    };

	    // Triez la liste en utilisant le Comparator
	    Collections.sort(resultList, scoreComparator);

	    return resultList;
	}
	
	
}