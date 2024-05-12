package database.lucene;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import com.orsoncharts.util.json.parser.ParseException;

public class LuceneTest {
    public static void main(String[] args) {
        // Spécifiez le chemin vers le répertoire d'index
        String indexDirectoryPath = "index";

        // Spécifiez le chemin vers le répertoire de données
        String dataDirPath = "Description";

        // Créer l'index
        createIndex(indexDirectoryPath, dataDirPath);

        // Effectuer une recherche
        searchIndex(indexDirectoryPath, "sable plage randonnée culture montagne");
    }

    private static void createIndex(String indexDirectoryPath, String dataDirPath) {
        try {
            LuceneIndexer luceneIndexer = new LuceneIndexer(indexDirectoryPath);
            int numIndexed = luceneIndexer.createIndex(dataDirPath, new TextFileFilter());
            luceneIndexer.close();
            System.out.println(numIndexed + " fichiers ont été indexés avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchIndex(String indexDirectoryPath, String searchQuery) {
        try {
            Searcher searcher = new Searcher(indexDirectoryPath);
            TopDocs topDocs = searcher.search(searchQuery);

            System.out.println("Résultats de la recherche pour : " + searchQuery);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = searcher.getDocument(scoreDoc);

                // Obtenez le chemin complet du fichier
                String filePath = document.get(LuceneConstants.FILE_PATH);

                // Utilisez la classe Path pour extraire le nom du fichier sans l'extension
                Path path = Paths.get(filePath);
                String fileNameWithoutExtension = path.getFileName().toString().replaceFirst("[.][^.]+$", "");

                System.out.println("Score: " + scoreDoc.score);

                // Affichez le nom du fichier sans l'extension
                System.out.println("File: " + fileNameWithoutExtension);

                System.out.println("---------");
            }

            searcher.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

