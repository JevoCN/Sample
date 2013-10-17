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
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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

        doc.add(new Field("title", "Lucene使用介绍", Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("content", "本文对Lucene的简单使用作一个简单的介绍", Field.Store.YES, Field.Index.ANALYZED));
//      doc.setBoost(100);//

        //将文档写入索引
        writer.addDocument(doc);
        writer.optimize();
        //关闭写索引器
        writer.close();

    }

    public void delete() {
        IndexWriter writer = null;

        try {
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(org.apache.lucene.util.Version.LUCENE_35, analyzer);
            Directory directory = FSDirectory.open(new File("D:\\temp\\test"));
            writer = new IndexWriter(directory,config);
            //参数可以是一个Query，也可以是一个term，term是一个精确查找的值
            //此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
            //执行完这个操作，索引文件夹下就会多出一个名叫_0_1.del的文件，也就是删除的文件在这个文件中记录了
            writer.deleteDocuments(new Term("title","Lucene使用介绍"));
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
            doc.add(new Field("title","Lucene使用介绍",Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("content","本文对Lucene的简单使用作一个简单的介绍",Field.Store.NO,Field.Index.ANALYZED));
            doc.add(new Field("comments","关于Lucene使用的说明",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            writer.updateDocument(new Term("title","Lucene使用介绍"), doc);
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
