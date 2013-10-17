package org.jevo.lucene.exam2;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;


class IndexUtil_11 {

    private   String [] ids={"1","2","3","4"};
    private   String [] emails={"lele@qq.com","1534432371@qq.com","hello@sine.com","Tom@163.com"};
    private  String [] contents={"Hello world!","lele is so cool��","hello@sine.com","Tom@163.com"};
    private   String [] names={"lele!","Tom","zhangsan ","zhaosi"};
    private  int  [] attachs={1,2,3,5};
    private  Directory directory=null;

    public  void  index()
    {
        //����Derectory
        try {
            directory=FSDirectory.open(new File ("G:/Lucene/indext2"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // ����Writer
        IndexWriter writer=null;
        try {
            writer=new IndexWriter (directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
        Document doc=null;
        for (int i=0;i<ids.length;i++)
        {
            //����Decoment�����Field
            doc=new Document();
            doc.add(new Field("ids",ids[i],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("names",names[i],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("emails",emails[i],Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field("contents",contents[i],Field.Store.NO,Field.Index.ANALYZED));
            //���ĵ�д��������
            writer.addDocument(doc);
        }


        } catch (CorruptIndexException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (writer!=null)
            {
                try {
                    writer.close();
                } catch (CorruptIndexException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    //throw new Exception ();
                }
            }

        }


    }
    public void unDelete()//�ָ�����վ��δ��ɾ���Ķ���
    {
        try {
            //�ָ�ʱ�����IndexReader��ֻ������Ϊfalse
            IndexReader reader=IndexReader.open(directory,false);
            reader.undeleteAll();
            reader.close();
        } catch (CorruptIndexException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void updata()
    {
        IndexWriter writer=null;
        try {
            writer=writer=new IndexWriter (directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
            Document doc=new Document();
            //���¾�����ɾ�������
            doc.add(new Field("ids","11",Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("names",names[0],Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("emails",emails[0],Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field("contents",contents[0],Field.Store.NO,Field.Index.ANALYZED));

            writer.updateDocument(new Term("ids","2"), doc);

        } catch (CorruptIndexException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (writer!=null)
            {try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}
        }

    }



    public void forceDelete()//ǿ��ɾ������վ�еĶ���
    {IndexWriter writer=null;
    try {
        writer=writer=new IndexWriter (directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
        writer.forceMergeDeletes();

    } catch (CorruptIndexException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (LockObtainFailedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    finally
    {
        if (writer!=null)
        {try {
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
    }

    }


    public void delete()//ɾ���ĵ�������վ��
    {
        IndexWriter writer=null;
        try {
            writer=writer=new IndexWriter (directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
            writer.deleteDocuments(new Term("names","Tom"));

        } catch (CorruptIndexException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (writer!=null)
            {try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}
        }
    }


    public void query() throws IOException
    {

        try {
            //ͨ��IndexReader���в�ѯ
            IndexReader reader=IndexReader.open(directory);
            //ͨ��reader������Ч�Ļ�ȡ�ĵ�������
            System.out.println(reader.numDocs());
            System.out.println(reader.maxDoc());
            System.out.println("Delete : "+reader.numDeletedDocs());
            reader.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


