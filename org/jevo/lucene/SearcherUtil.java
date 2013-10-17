package org.jevo.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-15
 * Time: 16:21:31
 * To change this template use File | Settings | File Templates.
 */

public class SearcherUtil {
    private Directory directory;
    private Analyzer analyzer = new IKAnalyzer();
    private IndexReader reader;
    private String[] ids = {"1", "2", "3", "4", "5", "6"};
    private String[] emails = {"aa@itat.org", "bb@itat.org", "cc@cc.org", "dd@sina.org", "ee@zttc.edu", "ff@itat.org"};
    private String[] contents = {
            "welcome to visited the space,I like book",
            "hello boy, I like pingpeng ball",
            "my name is cc I like game",
            "I like football",
            "I like football and I like basketball too",
            "I like movie and swim"
    };
    private Date[] dates = null;
    private int[] attachs = {2, 3, 1, 4, 5, 5};
    private String[] names = {"zhangsan", "lisi", "john", "jetty", "mike", "jake"};
    private Map<String, Float> scores = new HashMap<String, Float>();

    public SearcherUtil() {
//      directory = new RAMDirectory();
        try {
            directory = FSDirectory.open(new File("F:\\Workspaces\\lucenes\\02_lucene_searcher\\index"));
            setDates();
            scores.put("itat.org", 2.0f);
            scores.put("zttc.edu", 1.5f);
//          index();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    public void index() {
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
            writer.deleteAll();
            Document doc = null;
            for (int i = 0; i < ids.length; i++) {
                doc = new Document();
                doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
                doc.add(new Field("email", emails[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("content", contents[i], Field.Store.NO, Field.Index.ANALYZED));
                doc.add(new Field("name", names[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
                //存储数字
                doc.add(new NumericField("attach", Field.Store.YES, true).setIntValue(attachs[i]));
                //存储日期
                doc.add(new NumericField("date", Field.Store.YES, true).setLongValue(dates[i].getTime()));
                String et = emails[i].substring(emails[i].lastIndexOf("@") + 1);
                if (scores.containsKey(et)) {
                    doc.setBoost(scores.get(et));
                } else {
                    doc.setBoost(0.5f);
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
                if (writer != null) writer.close();
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public IndexSearcher getSearcher() {
        try {
            if (reader == null) {
                reader = IndexReader.open(directory);
            } else {
                IndexReader tr = IndexReader.openIfChanged(reader);
                if (tr != null) {
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

    public IndexSearcher getSearcher(Directory directory) {
        try {
            if (reader == null) {
                reader = IndexReader.open(directory);
            } else {
                IndexReader tr = IndexReader.openIfChanged(reader);
                if (tr != null) {
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

    public void searchByTerm(String field, String name, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = new TermQuery(new Term(field, name));
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByTermToken(String field, String name, int num) {
        try {
            IndexSearcher searcher = getSearcher();
//          Query query = new TermQuery(new Term(field,name));
            //当用户输入两个关键字时，QueryParser默认它们之间的关系为“或”关系
            //下面这么写的话在对用户输入进行扫描时，就会用空格分开的关键字理解为“与”，
            //其实也就是构建了一个“与”关系的布尔型查询
//          parser.setDefaultOperator(Operator.AND);
            QueryParser parser = new QueryParser(Version.LUCENE_35, field, analyzer);
            String k = analyzerKey(name);
            Query query = parser.parse(name);
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String analyzerKey(String key) {
//      StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
        StringReader reader = new StringReader(key);
        TokenStream tokenStream = analyzer.tokenStream("", reader);
        CharTermAttribute termattr = tokenStream.addAttribute(CharTermAttribute.class);
        StringBuilder sb = new StringBuilder();
        try {
            while (tokenStream.incrementToken()) {
                String k = termattr.toString();
                sb.append(k).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = sb.toString().trim();
        key = key.replaceAll("\\s+", " AND ");
        return sb.toString();
    }

    public void printDocument(IndexSearcher searcher, TopDocs tds) {
        System.out.println("共查询了【" + tds.totalHits + "】条");
        for (ScoreDoc sd : tds.scoreDocs) {
            try {
                Document doc = searcher.doc(sd.doc);
                System.out.println("filename:" + doc.get("filename"));
                System.out.println("path:" + doc.get("path"));
                System.out.println("date:" + doc.get("date"));
                System.out.println("size:" + doc.get("size"));
                System.out.println("content:" + doc.get("content"));
                System.out.println("-------------------------------------------");
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchByTermRange(String field, String start, String end, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = new TermRangeQuery(field, start, end, true, true);
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立索引时：使用的Field， 而使用NumericRangeQuery， 必须使用NumericField
     *
     * @param field
     * @param start
     * @param end
     * @param num
     */
    public void searchByNumricRange(String field, int start, int end, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = NumericRangeQuery.newIntRange(field, start, end, true, true);
//           DateTools.dateToString(new Date(), null);
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByPrefix(String field, String value, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            Query query = new PrefixQuery(new Term(field, value));
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByWildcard(String field, String value, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            //在传入的value中可以使用通配符:?和*,?表示匹配一个字符，*表示匹配任意多个字符
            Query query = new WildcardQuery(new Term(field, value));
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByBoolean(int num) {
        try {
            IndexSearcher searcher = getSearcher();
            BooleanQuery query = new BooleanQuery();
            /*
            * BooleanQuery可以连接多个子查询
            * Occur.MUST表示必须出现
            * Occur.SHOULD表示可以出现
            * Occur.MUSE_NOT表示不能出现
            */
            query.add(new TermQuery(new Term("name", "3")), Occur.MUST_NOT);
            query.add(new TermQuery(new Term("content", "健壮")), Occur.SHOULD);
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByPhrase(int num) {
        try {
            IndexSearcher searcher = getSearcher();
            PhraseQuery query = new PhraseQuery();
            query.setSlop(10);
            query.add(new Term("content", "java"));
            //第一个Term
            query.add(new Term("content", "程序"));
            //产生距离之后的第二个Term
//          query.add(new Term("content","football"));
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用于匹配与指定项相似的项
     * 默认是匹配一个有不同的，其他一样的，比如like 和 mike，就是距离算法的相似距离为1
     * 这种方式少用，影响效率
     */
    public void searchByFuzzy(int num) {
        try {
            IndexSearcher searcher = getSearcher();
            //最后两个参数为匹配率和距离
            FuzzyQuery query = new FuzzyQuery(new Term("content", "总统"), 0.4f, 0);
            System.out.println(query.getPrefixLength());
            System.out.println(query.getMinSimilarity());
            TopDocs tds = searcher.search(query, num);
            printDocument(searcher, tds);
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByQueryParse(Query query, int num) {
        try {
            IndexSearcher searcher = getSearcher();
            TopDocs tds = searcher.search(query, num);
            System.out.println("一共查询了:" + tds.totalHits);
            for (ScoreDoc sd : tds.scoreDocs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get("id") + "---->" +
                        doc.get("name") + "[" + doc.get("email") + "]-->" + doc.get("id") + "," +
                        doc.get("attach") + "," + doc.get("date") + "==" + sd.score);
            }
            searcher.close();
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * lucene3.5之前采用的是一种再查询的方式，也就是说先把全部的结果的docid查询出来，然后
     * 分页得到该页的docid，然后根据docid得到document信息，
     * lucene官方是说他的速度已经够快，再查询不会有效率问题
     *
     * @param query
     * @param pageIndex
     * @param pageSize
     */
    public void searchPage(String query, int pageIndex, int pageSize) {
        try {
            Directory dir = FileIndexUtils.getDirectory();
            IndexSearcher searcher = getSearcher(dir);
            QueryParser parser = new QueryParser(Version.LUCENE_35, "content", analyzer);
            Query q = parser.parse(query);
            TopDocs tds = searcher.search(q, 500);
            ScoreDoc[] sds = tds.scoreDocs;
            int start = (pageIndex - 1) * pageSize;
            int end = pageIndex * pageSize;
            for (int i = start; i < end; i++) {
                Document doc = searcher.doc(sds[i].doc);
                System.out.println("filename:" + doc.get("filename"));
                System.out.println("path:" + doc.get("path"));
                System.out.println("date:" + doc.get("date"));
                System.out.println("size:" + doc.get("size"));
                System.out.println("content:" + doc.get("content"));
                System.out.println("-------------------------------------------");
            }

            searcher.close();
        } catch (org.apache.lucene.queryParser.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 目前没有办法只取当前这页的数据，而是要全部查询然后得到docid
     * 一种增加效率的方式是取的条数做下限制，比如不要每次都取500条，
     * 也是把取的条数设置为当前页的所在位置数，比如每页10条，
     * 取第一页数据则取10条，取第二页则取20条，取五页则去50条
     * 根据页码和分页大小获取上一次的最后一个ScoreDoc
     */
    private ScoreDoc getLastScoreDoc(int pageIndex, int pageSize, Query query, IndexSearcher searcher) throws IOException {
        if (pageIndex == 1) return null;//如果是第一页就返回空
        int num = pageSize * (pageIndex - 1);//获取上一页的数量
        TopDocs tds = searcher.search(query, num);
        return tds.scoreDocs[num - 1];
    }

    /**
     * 使用这种方式的话是把上一页的最后一个元素给拿到，然后再把pagesize传入，
     * 就可以得到当页的数据，其实就是简便了查询，原理还是把全部的docid查询后在得到document
     *
     * @param query
     * @param pageIndex
     * @param pageSize
     */
    public void searchPageByAfter(String query, int pageIndex, int pageSize) {
        try {
            Directory dir = FileIndexUtils.getDirectory();
            IndexSearcher searcher = getSearcher(dir);
            QueryParser parser = new QueryParser(Version.LUCENE_35, "content", analyzer);
            Query q = parser.parse(query);
            //先获取上一页的最后一个元素
            ScoreDoc lastSd = getLastScoreDoc(pageIndex, pageSize, q, searcher);
            //通过最后一个元素搜索下页的pageSize个元素
            TopDocs tds = searcher.searchAfter(lastSd, q, pageSize);
            printDocument(searcher, tds);
            searcher.close();
        } catch (org.apache.lucene.queryParser.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchNoPage(String query) {
        try {
            Directory dir = FileIndexUtils.getDirectory();
            IndexSearcher searcher = getSearcher(dir);
            QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
            Query q = parser.parse(query);
            TopDocs tds = searcher.search(q, 20);
            ScoreDoc[] sds = tds.scoreDocs;
            for (int i = 0; i < sds.length; i++) {
                Document doc = searcher.doc(sds[i].doc);
                System.out.println(sds[i].doc + ":" + doc.get("path") + "-->" + doc.get("filename"));
            }

            searcher.close();
        } catch (org.apache.lucene.queryParser.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


