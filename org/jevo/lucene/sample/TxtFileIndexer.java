package org.jevo.lucene.sample;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-15
 * Time: 14:55:57
 * To change this template use File | Settings | File Templates.
 */

public class TxtFileIndexer {
    public static void main(String[] args) throws Exception {

        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(org.apache.lucene.util.Version.LUCENE_35, analyzer);
        Directory directory = FSDirectory.open(new File("D:\\temp\\test"));

        IndexWriter writer = new IndexWriter(directory, config);
        Document doc = new Document();

        doc.add(new Field("title", "Luceneʹ�ý���", Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("content", "���Ķ�Lucene�ļ�ʹ����һ���򵥵Ľ���", Field.Store.YES, Field.Index.ANALYZED));
//      doc.setBoost(100);//

        //���ĵ�д������
        writer.addDocument(doc);
        writer.optimize();
        //�ر�д������
        writer.close();

    }

    public void delete() {
        IndexWriter writer = null;

        try {
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(org.apache.lucene.util.Version.LUCENE_35, analyzer);
            Directory directory = FSDirectory.open(new File("D:\\temp\\test"));
            writer = new IndexWriter(directory,config);
            //����������һ��Query��Ҳ������һ��term��term��һ����ȷ���ҵ�ֵ
            //��ʱɾ�����ĵ������ᱻ��ȫɾ�������Ǵ洢��һ������վ�еģ����Իָ�
            //ִ������������������ļ����¾ͻ���һ������_0_1.del���ļ���Ҳ����ɾ�����ļ�������ļ��м�¼��
            writer.deleteDocuments(new Term("title","Luceneʹ�ý���"));
            writer.commit();
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

    public void update() {
        IndexWriter writer = null;
        try {
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(org.apache.lucene.util.Version.LUCENE_35, analyzer);
            Directory directory = FSDirectory.open(new File("D:\\temp\\test"));
            writer = new IndexWriter(directory,config);

            Document doc = new Document();
            doc.add(new Field("title","Luceneʹ�ý���",Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("content","���Ķ�Lucene�ļ�ʹ����һ���򵥵Ľ���",Field.Store.NO,Field.Index.ANALYZED));
            doc.add(new Field("comments","����Luceneʹ�õ�˵��",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            writer.updateDocument(new Term("title","Luceneʹ�ý���"), doc);
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
