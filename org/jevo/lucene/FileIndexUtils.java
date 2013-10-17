package org.jevo.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-15
 * Time: 16:33:26
 * To change this template use File | Settings | File Templates.
 */

public class FileIndexUtils {
    private static Directory directory = null;
    private static Analyzer analyzer = new IKAnalyzer();
    static{
        try {
            directory = FSDirectory.open(new File("F:\\Workspaces\\lucenes\\02_lucene_searcher\\index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    public static void index(boolean hasNew) {
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, analyzer));
            if(hasNew) {
                writer.deleteAll();
            }
            File file = new File("F:\\Workspaces\\lucenes\\02_lucene_searcher\\resource");
            Document doc = null;
            for(File f:file.listFiles()) {
                doc = new Document();
                doc.add(new Field("content",FileUtils.readFileToString(f),Field.Store.YES,Field.Index.ANALYZED));
                doc.add(new Field("filename",f.getName(),Field.Store.YES,Field.Index.ANALYZED));
                doc.add(new Field("path",f.getAbsolutePath(),Field.Store.YES,Field.Index.ANALYZED));
                doc.add(new NumericField("date",Field.Store.YES,true).setLongValue(f.lastModified()));
                doc.add(new NumericField("size",Field.Store.YES,true).setIntValue((int)(f.length())));
                writer.addDocument(doc);
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer!=null) writer.close();
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


