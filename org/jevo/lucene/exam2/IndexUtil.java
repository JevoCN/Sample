package org.jevo.lucene.exam2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StaleReaderException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;


public class IndexUtil {
    private String[] ids = {"1","2","3","4","5","6"};
    private String[] emails = {"aa@itat.org","bb@itat.org","cc@cc.org","dd@sina.org","ee@zttc.edu","ff@itat.org"};
    private String[] contents = {
            "welcome to visited the space,I like book",
            "hello boy, I like pingpeng ball",
            "my name is cc I like game",
            "I like football",
            "I like football and I like basketball too",
            "I like movie and swim"
    };
    private Date[] dates = null;
    private int[] attachs = {2,3,1,4,5,5};
    private String[] names = {"zhangsan","lisi","john","jetty","mike","jake"};
    private Directory directory = null;
    private Map<String,Float> scores = new HashMap<String,Float>();
    private static IndexReader reader = null;

    public IndexUtil() {
        try {
            setDates();
            scores.put("itat.org",2.0f);
            scores.put("zttc.edu", 1.5f);
            //directory = FSDirectory.open(new File("d:/lucene/index02"));
            directory = new RAMDirectory();
            index();
            reader = IndexReader.open(directory,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对于IndexReader而言，反复使用Index.open打开会有很大的开销，所以一般在整个程序的生命周期中
     * 只会打开一个IndexReader,通过这个IndexReader来创建不同的IndexSearcher,如果使用单例模式，
     * 可能出现的问题有：
     * 1、当使用Writer修改了索引之后不会更新信息，所以需要使用IndexReader.openIfChange方法操作
     * 如果IndexWriter在创建完成之后，没有关闭，需要进行commit操作之后才能提交
     * @return
     */
    public IndexSearcher getSearcher() {
        try {
            if(reader==null) {
                reader = IndexReader.open(directory,false);
            } else {
                IndexReader tr = IndexReader.openIfChanged(reader);
                //如果原来的reader没改变，返回null
                //如果原来的reader改变，则更新为新的索引
                if(tr!=null) {
                    reader.close();
                    reader = tr;
                }
            }
            return new IndexSearcher(reader);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void setDates() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dates = new Date[ids.length];
            dates[0] = sdf.parse("2010-02-19");
            dates[1] = sdf.parse("2012-01-11");
            dates[2] = sdf.parse("2011-09-19");
            dates[3] = sdf.parse("2010-12-22");
            dates[4] = sdf.parse("2012-01-01");
            dates[5] = sdf.parse("2011-05-19");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * 把之前删除的索引数据进行恢复
     */
    public void undelete() {
        //使用IndexReader进行恢复
        try {
            IndexReader reader = IndexReader.open(directory,false);
            //恢复时，必须把IndexReader的只读(readOnly)设置为false
            reader.undeleteAll();
            reader.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (StaleReaderException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * forceMerge是lucene3.5之前替代optimize方法的，其实只是改了个名称，因为优化的使效率变低
     * 因为一到优化它就会全部更新索引，这个所涉及到的负载是很大的
     * 所以改了个名称，不推荐使用，在做优化的时候会把索引回收站中的数据文件全部删除
     * lucene会在你写索引的时候根据你的索引的段越来越多会自动帮忙优化的，force是强制优化
     */
    public void merge() {
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory,
                    new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35)));
            //会将索引合并为2段，这两段中的被删除的数据会被清空
            //特别注意：此处Lucene在3.5之后不建议使用，因为会消耗大量的开销，
            //Lucene会根据情况自动处理的
            writer.forceMerge(2);
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

    /**
     * 假如你想要强制删除回收站的信息可以调用writer.forceMergeDeletes()这个方法，
     * 但是这个方法不推荐使用，比较消耗内存，lucene会自动根据容量的大小删除所删除的文件
     */
    public void forceDelete() {
        IndexWriter writer = null;

        try {
            writer = new IndexWriter(directory,
                    new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35)));
            writer.forceMergeDeletes();
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
    /**
     * 删除索引数据,默认不会完全删除，被放入索引回收站
     */
    public void delete() {
        IndexWriter writer = null;

        try {
            writer = new IndexWriter(directory,
                    new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35)));
            //参数是一个选项，可以是一个Query，也可以是一个term，term是一个精确查找的值
            //此时删除的文档并不会被完全删除，而是存储在一个回收站中的，可以恢复
            //执行完这个操作，索引文件夹下就会多出一个名叫_0_1.del的文件，也就是删除的文件在这个文件中记录了
            writer.deleteDocuments(new Term("id","1"));
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
    /**
     * 使用reader删除，其实里面也会调用writer删除，
     * 优点是使用reader删除马上会更新索引信息
     * 现在一般还是使用writer来删除，reader.getWriter这个方法被过时了
     */
    public void delete02() {
        try {
            reader.deleteDocuments(new Term("id","1"));
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 更新操作
     * Lucene并没有提供更新，这里的更新操作其实是如下两个操作的合集
     * 先删除之后再添加
     */
    public void update() {
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory,
                    new IndexWriterConfig(Version.LUCENE_35,new StandardAnalyzer(Version.LUCENE_35)));
            Document doc = new Document();
            doc.add(new Field("id","11",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("email",emails[0],Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field("content",contents[0],Field.Store.NO,Field.Index.ANALYZED));
            doc.add(new Field("name",names[0],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            writer.updateDocument(new Term("id","1"), doc);
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

    public void query() {
        try {
            IndexReader reader = IndexReader.open(directory);
            //通过reader可以有效的获取到文档的数量
            System.out.println("numDocs:"+reader.numDocs());//存储的文档数//不包括被删除的
            System.out.println("maxDocs:"+reader.maxDoc());//总存储量,包括在回收站中的索引
            System.out.println("deleteDocs:"+reader.numDeletedDocs());
            reader.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  索引文件后缀为.fmn为保存的是域的名称等
     * .fdt和.fdx保存的是Store.YES的信息，保存域里面存储的数据
     * .frq表示这里的域哪些出现多少次，哪些单词出现多少次，
     * .nrm存储一些评分信息
     * .prx存储一些偏移量等
     * .tii和.tis专门存储索引里面的所有内容信息
     */
    public void index() {
        IndexWriter writer = null;
        try {
            //在2.9版本之后,lucene的就不是全部的索引格式都兼容的了,所以在使用的时候必须写明版本号
            writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
            writer.deleteAll();//清空索引
            Document doc = null;
            for(int i=0;i<ids.length;i++) {
                doc = new Document();
                doc.add(new Field("id",ids[i],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field("email",emails[i],Field.Store.YES,Field.Index.NOT_ANALYZED));
                doc.add(new Field("email","test"+i+"@test.com",Field.Store.YES,Field.Index.NOT_ANALYZED));
                doc.add(new Field("content",contents[i],Field.Store.NO,Field.Index.ANALYZED));
                doc.add(new Field("name",names[i],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
                //存储数字
                //NumberTools.stringToLong("");已经被标记为过时了
                doc.add(new NumericField("attach",Field.Store.YES,true).setIntValue(attachs[i]));
                //存储日期
                doc.add(new NumericField("date",Field.Store.YES,true).setLongValue(dates[i].getTime()));
                String et = emails[i].substring(emails[i].lastIndexOf("@")+1);
                System.out.println(et);
                if(scores.containsKey(et)) {
                    doc.setBoost(scores.get(et));
                } else {
                    doc.setBoost(0.5f);//默认是1.0f
                }
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
                if(writer!=null)writer.close();
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void search01() {
        try {
            IndexReader reader = IndexReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            TermQuery query = new TermQuery(new Term("email","test0@test.com"));
            TopDocs tds = searcher.search(query, 10);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println("("+sd.doc+"-"+doc.getBoost()+"-"+sd.score+")"+
                        doc.get("name")+"["+doc.get("email")+"]-->"+doc.get("id")+","+
                        doc.get("attach")+","+doc.get("date")+","+doc.getValues("email")[1]);
            }
            reader.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search02() {
        try {
            IndexSearcher searcher = getSearcher();
            TermQuery query = new TermQuery(new Term("content","like"));
            TopDocs tds = searcher.search(query, 10);
            for(ScoreDoc sd:tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get("id")+"---->"+
                        doc.get("name")+"["+doc.get("email")+"]-->"+doc.get("id")+","+
                        doc.get("attach")+","+doc.get("date")+","+doc.getValues("email")[1]);
            }
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

