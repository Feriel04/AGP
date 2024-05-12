package database.lucene;
import java.io.File;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;

import com.orsoncharts.util.json.parser.ParseException;

public class Searcher {
    private IndexSearcher indexSearcher;
    private QueryBuilder queryBuilder;

    public Searcher(String indexDirectoryPath) throws IOException, ParseException {
        try {
            Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
            indexSearcher = new IndexSearcher(DirectoryReader.open(indexDirectory));
            queryBuilder = new QueryBuilder(new StandardAnalyzer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TopDocs searchByQuery(Query query) throws IOException, ParseException {
        return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
    }

    public TopDocs search(String searchQuery) throws IOException, ParseException {
        Query query = queryBuilder.createBooleanQuery(LuceneConstants.CONTENTS, searchQuery);
        return indexSearcher.search(query, LuceneConstants.MAX_SEARCH);
    }


    public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }

    public void close() throws IOException {
        indexSearcher.getIndexReader().close();
    }
    
    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }
}