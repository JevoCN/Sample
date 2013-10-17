package org.jevo.lucene.exam1;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-15
 * Time: 16:40:19
 * To change this template use File | Settings | File Templates.
 */

public class Index {
    /** �����ļ�·�� */
    private static final String INDEX_PATH = "/workspace2/indexing";

    /** ��� */
    public static final String AUCTION_NO = "auctionNo";
    /** ���� */
    public static final String AUCTION_NAME = "auctionName";
    /** �۸� */
    public static final String MAX_PRICE = "maxPrice";
    /** ���� */
    public static final String END_DATE = "endDate";

    /**
     * �������
     * @param args
     */
    public static void main(String[] args) {
        createIndex();
//        deleteIndex();
//        readIndex();
        updateIndex();
    }

    /**
     * ��������
     */
    public static void createIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);

            //Document�������ֵ
            Document doc = new Document();
            Field auctionNoField = new Field(AUCTION_NO, "10003",Field.Store.YES, Field.Index.NOT_ANALYZED);
            Field auctionNameField = new Field(AUCTION_NAME, "��԰��֭", Field.Store.YES, Field.Index.ANALYZED);
            Field endDateField = new Field(END_DATE, "2012-03-06 18:00:00",Field.Store.YES, Field.Index.NOT_ANALYZED);
            doc.add(auctionNoField);
            doc.add(auctionNameField);
            doc.add(new NumericField(Index.MAX_PRICE, Field.Store.YES, true).setDoubleValue(300));
            doc.add(endDateField);

            writer.addDocument(doc);
//            writer.addDocuments(docs);//��Ӷ������
            writer.close();
            System.out.println("=========�����������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IndexWriter��ʽɾ������
     * <ul>
     *         <li>ȫ��ɾ��</li>
     *         <li>��������ɾ��</li>
     * </ul>
     */
    public static void indexWriterDeleteIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);
//            writer.deleteAll();//ɾ����������
            Term term = new Term(Index.AUCTION_NO, "10001");//ɾ����������
            writer.deleteDocuments(term);
            writer.close();
            System.out.println("=========ɾ�������ɹ�");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IndexReader��ʽɾ������
     */
    public static void indexReaderDeleteIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexReader reader = IndexReader.open(directory, false);//��ΪtrueΪֻ��ģʽ
            Term term = new Term(Index.AUCTION_NO, "10000");//ɾ����������
            reader.deleteDocuments(term);
            reader.flush();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��������(��ɾ���ٴ���)
     * <ul>
     *         <li>���µ�������</li>
     *         <li>���¶������</li>
     * </ul>
     */
    public static void updateIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);

            //Document�������ֵ
            Document doc = new Document();
            Field auctionNoField = new Field(AUCTION_NO, "10003",Field.Store.YES, Field.Index.NOT_ANALYZED);
            Field auctionNameField = new Field(AUCTION_NAME, "��Ʒ����", Field.Store.YES, Field.Index.ANALYZED);
            Field endDateField = new Field(END_DATE, "2011-03-06 18:00:00",Field.Store.YES, Field.Index.NOT_ANALYZED);
            doc.add(auctionNoField);
            doc.add(auctionNameField);
            doc.add(new NumericField(Index.MAX_PRICE, Field.Store.YES, true).setDoubleValue(200));
            doc.add(endDateField);

            //����Ψһ��ƷID���и�������
            Term term = new Term(Index.AUCTION_NO,"10003");
            writer.updateDocument(term, doc);//��������
//            writer.updateDocuments(delTerm, docs);//���¶������
            writer.close();
            System.out.println("=========�����������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ������
     */
    public static void readIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexReader reader = IndexReader.open(directory, true);//��ΪtrueΪֻ��ģʽ
            int num = reader.numDocs();
            for (int i = 0; i < num; i++) {
                Document doc = reader.document(i);
                System.out.println(doc);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


