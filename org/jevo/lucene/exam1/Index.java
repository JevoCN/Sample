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
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-15
 * Time: 16:40:19
 * To change this template use File | Settings | File Templates.
 */

public class Index {
    /** 索引文件路径 */
    private static final String INDEX_PATH = "/workspace2/indexing";

    /** 编号 */
    public static final String AUCTION_NO = "auctionNo";
    /** 名称 */
    public static final String AUCTION_NAME = "auctionName";
    /** 价格 */
    public static final String MAX_PRICE = "maxPrice";
    /** 日期 */
    public static final String END_DATE = "endDate";

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        createIndex();
//        deleteIndex();
//        readIndex();
        updateIndex();
    }

    /**
     * 创建索引
     */
    public static void createIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);

            //Document添加索引值
            Document doc = new Document();
            Field auctionNoField = new Field(AUCTION_NO, "10003",Field.Store.YES, Field.Index.NOT_ANALYZED);
            Field auctionNameField = new Field(AUCTION_NAME, "汇园果汁", Field.Store.YES, Field.Index.ANALYZED);
            Field endDateField = new Field(END_DATE, "2012-03-06 18:00:00",Field.Store.YES, Field.Index.NOT_ANALYZED);
            doc.add(auctionNoField);
            doc.add(auctionNameField);
            doc.add(new NumericField(Index.MAX_PRICE, Field.Store.YES, true).setDoubleValue(300));
            doc.add(endDateField);

            writer.addDocument(doc);
//            writer.addDocuments(docs);//添加多个索引
            writer.close();
            System.out.println("=========创建索引完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IndexWriter方式删除索引
     * <ul>
     *         <li>全部删除</li>
     *         <li>单个或多个删除</li>
     * </ul>
     */
    public static void indexWriterDeleteIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);
//            writer.deleteAll();//删除所有索引
            Term term = new Term(Index.AUCTION_NO, "10001");//删除单个索引
            writer.deleteDocuments(term);
            writer.close();
            System.out.println("=========删除索引成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * IndexReader方式删除索引
     */
    public static void indexReaderDeleteIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexReader reader = IndexReader.open(directory, false);//设为true为只读模式
            Term term = new Term(Index.AUCTION_NO, "10000");//删除单个索引
            reader.deleteDocuments(term);
            reader.flush();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新索引(先删除再创建)
     * <ul>
     *         <li>更新单个索引</li>
     *         <li>更新多个索引</li>
     * </ul>
     */
    public static void updateIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_35,analyzer);
            IndexWriter writer = new IndexWriter(directory, iwConfig);

            //Document添加索引值
            Document doc = new Document();
            Field auctionNoField = new Field(AUCTION_NO, "10003",Field.Store.YES, Field.Index.NOT_ANALYZED);
            Field auctionNameField = new Field(AUCTION_NAME, "商品名称", Field.Store.YES, Field.Index.ANALYZED);
            Field endDateField = new Field(END_DATE, "2011-03-06 18:00:00",Field.Store.YES, Field.Index.NOT_ANALYZED);
            doc.add(auctionNoField);
            doc.add(auctionNameField);
            doc.add(new NumericField(Index.MAX_PRICE, Field.Store.YES, true).setDoubleValue(200));
            doc.add(endDateField);

            //根据唯一商品ID进行更新索引
            Term term = new Term(Index.AUCTION_NO,"10003");
            writer.updateDocument(term, doc);//更新索引
//            writer.updateDocuments(delTerm, docs);//更新多个索引
            writer.close();
            System.out.println("=========更新索引完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读索引
     */
    public static void readIndex(){
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexReader reader = IndexReader.open(directory, true);//设为true为只读模式
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


