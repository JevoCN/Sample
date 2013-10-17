package org.jevo.lucene.exam1;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-15
 * Time: 16:37:25
 * To change this template use File | Settings | File Templates.
 */
//引自：http://wenxin2009.iteye.com/blog/1457188
public class Search {
    public IndexSearcher searcher = null;
    String keyword = "北 AND 要";
    String keyword2 = "100";

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        Search search =  new Search();
        search.getSearcher();

//        search.termQuery();//词条查询
        search.booleanQuery_1();
//        search.booleanQuery();//组合查询
//        search.wildcardQuery();//通配符查询
//        search.phraseQuery();//短语查询
//        search.prefixQuery();//前缀查询
//        search.multiPhraseQuery();//多短语查询-
//        search.fuzzyQuery();//模糊查询
//        search.termRangeQuery();//文本范围查询 2011-03-06 18:00:00  TO 2012-03-06 18:00:00
//        search.numericRangeQuery(100.00,200.00);//数字范围查询
//        search.sortQuery();//排序查询
//        search.heightQuery();//高亮查询
//        search.pageQuery(2, 5);//分页查询
    }

    /**
     * 获得搜索
     */
    public  void getSearcher(){
        IndexReader reader = null;
        try {
            reader = IndexReader.open(FSDirectory.open(new File("/workspace2/indexing")), true);
            searcher = new IndexSearcher(reader);
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询
     * @param q
     */
    public void query(Query q){
        TopScoreDocCollector collector = TopScoreDocCollector.create(5*10, false);
        try {
            searcher.search(q, collector);
            int count = collector.getTotalHits();
            System.out.println("------------获得 "+count+" 记录！");
            TopDocs top = collector.topDocs();
            ScoreDoc[] docs = top.scoreDocs;
            for (ScoreDoc sd : docs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get(Index.AUCTION_NO)+" , "+doc.get(Index.AUCTION_NAME)+" , "+doc.get(Index.MAX_PRICE)+" , "+doc.get(Index.END_DATE));
            }
            searcher.close();//关闭搜索
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 排序查询
     * @param q
     */
    public void querySort(Query q,Sort sort){
        System.out.println("==============排序搜索");
        TopScoreDocCollector collector = TopScoreDocCollector.create(5*10, false);
        try {
             searcher.search(q,1000, sort);
            int count = collector.getTotalHits();
            System.out.println("------------获得 "+count+" 记录！");
            TopDocs top = collector.topDocs();
            ScoreDoc[] docs = top.scoreDocs;
            for (ScoreDoc sd : docs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get(Index.AUCTION_NO)+" , "+doc.get(Index.AUCTION_NAME)+" , "+doc.get(Index.MAX_PRICE)+" , "+doc.get(Index.END_DATE));
            }
            searcher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 词条搜索 TermQuery
     */
    public void termQuery(){
        Term t = new Term(Index.AUCTION_NAME, keyword);
        TermQuery q = new TermQuery(t);
        System.out.println("=====词条搜索=====");
        query(q);
    }

    /**
     * MultiTermQuery
     */
    public void multiTermQuery(){

    }

    public void booleanQuery_1() {
        BooleanQuery q = new BooleanQuery();
        QueryParser parser = new QueryParser(Version.LUCENE_35, Index.AUCTION_NAME, new StandardAnalyzer(Version.LUCENE_35));
        try {
            Query query = parser.parse(keyword);
            TermQuery termQuery = new TermQuery(new Term(Index.AUCTION_NAME,
                    keyword));
            q.add(query, BooleanClause.Occur.SHOULD);
            System.out.println("q : " + q.toString());
            System.out.println("========= 组合搜索");
            query(q);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 组合搜索 BooleanQuery
     * MUST_NOT :不包含
     * SHOULD ：表或关系
     * MUST ：表并关系
     */
    public void booleanQuery(){
        BooleanQuery q = new BooleanQuery();
        String[] s = keyword.split(" ");
        if (s.length > 0) {
            for (int i = 0; i < s.length; i++) {
//                TermQuery termQuery = new TermQuery(new Term(Index.AUCTION_NAME,s[i]));
                if (s[i].indexOf("-") != -1) {
                    String query = s[i].replaceAll("-", " NOT ");
                    TermQuery termQuery = new TermQuery(new Term(Index.AUCTION_NAME,query));
                    q.add(termQuery,BooleanClause.Occur.MUST_NOT);
                }else{
                    TermQuery termQuery = new TermQuery(new Term(Index.AUCTION_NAME,s[i]));
                    q.add(termQuery, BooleanClause.Occur.SHOULD);
                }
            }
        }else{
            TermQuery termQuery = new TermQuery(new Term(Index.AUCTION_NAME,keyword));
            q.add(termQuery,BooleanClause.Occur.SHOULD);
        }
        System.out.println("q : "+q.toString());
        System.out.println("========= 组合搜索");
        query(q);
    }

    /**
     *
     * 通配符搜索 WildcardQuery
     * ?*
     */
    public void wildcardQuery(){
        Term t = new Term(Index.AUCTION_NAME, keyword);
        WildcardQuery q = new WildcardQuery(t);
        System.out.println(q.toString());
        System.out.println("=======通配符搜索");
        query(q);
    }

    /**
     * 短语搜索 PhraseQuery
     */
    public void phraseQuery(){
        PhraseQuery q = new PhraseQuery();
        q.add(new Term(Index.AUCTION_NAME,keyword));
        q.add(new Term(Index.AUCTION_NAME, keyword2));
        q.setSlop(10);//设置坡度，默认为0。两个关键字之间的字符数量
        System.out.println("=======短语搜索");
        query(q);
    }

    /**
     * 前缀搜索 PrefixQuery
     */
    public void prefixQuery(){
        Term term = new Term(Index.AUCTION_NAME, keyword);
        PrefixQuery q = new PrefixQuery(term);
        System.out.println("==========前缀搜索");
        query(q);
    }

    /**
     * 多短语搜索 MultiPhraseQuery
     */
    public void multiPhraseQuery(){
        Term[] terms = new Term[]{new Term(Index.AUCTION_NAME, keyword),new Term(Index.AUCTION_NAME,keyword2)};
        MultiPhraseQuery q = new MultiPhraseQuery();
        q.add(terms);
        q.setSlop(0);//设置坡度，默认为0。两个关键字之间的字符数量
        System.out.println("==========多短语搜索");
        query(q);
    }

    /**
     * 模糊搜索 FuzzyQuery
     */
    public void fuzzyQuery(){
        Term term = new Term(Index.AUCTION_NAME, keyword);
        FuzzyQuery q = new FuzzyQuery(term);
        //默认匹配度为0.5,当该值越小，模糊匹配度越低
//        FuzzyQuery q = new FuzzyQuery(term, 0.1f);
        System.out.println("q："+q.toString());
        System.out.println("=======模糊搜索");
        query(q);
    }

    /**
     * 文本范围搜索 TermRangeQuery
     * 后面两个参数分别为是否包含前边界和后边界
     */
    public void termRangeQuery(){
        TermRangeQuery q    = new TermRangeQuery(Index.END_DATE, keyword, keyword2, true, false);
        System.out.println("===========范围搜索");
        query(q);
    }

    /**
     * 数字范围搜索 NumericRangeQuery
     * 后面两个参数分别为是否包含前边界和后边界
     */
    public void numericRangeQuery(double start,double end){
        Query q = NumericRangeQuery.newDoubleRange(Index.MAX_PRICE, start, end, true, true);
        System.out.println("===========数字范围搜索");
        query(q);
    }

    /**
     * 跨度查询 SpanQuery
     */
    public void spanQuery(){

    }

    /**
     * 排序搜索(根据拍品名称按价格排序)
     */
    public void sortQuery(){
        try {
            QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, new String[]{Index.AUCTION_NAME}, new StandardAnalyzer(Version.LUCENE_35));
            Query q = parser.parse(keyword);
            Sort sort = new Sort();
            sort.setSort(new SortField(Index.MAX_PRICE, SortField.DOUBLE, false));//true为降序，false为升序
            ScoreDoc[] hits = searcher.search(q, null, Integer.MAX_VALUE, sort).scoreDocs;
            System.out.println(hits.length);
            for (int i = 0; i < hits.length; i++) {
                Document doc  = searcher.doc(hits[i].doc);
                System.out.println(doc.get(Index.AUCTION_NAME)+" , "+doc.get(Index.MAX_PRICE));
            }
            searcher.close();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 高亮显示搜索
     * Lucene高亮与solr高亮有些不一样，Lucene是先查询出结果再设置高亮，
     * 而solr是先设置高亮再查询，直接得到高亮内容
     */
    public void heightQuery(){
        Term t = new Term(Index.AUCTION_NAME, keyword);
        TermQuery q = new TermQuery(t);
        TopScoreDocCollector collector = TopScoreDocCollector.create(5*10, false);
        try {
            searcher.search(q, collector);
            int count = collector.getTotalHits();
            System.out.println("------------获得 "+count+" 记录！");
            TopDocs top = collector.topDocs();
            ScoreDoc[] docs = top.scoreDocs;
            for (ScoreDoc sd : docs) {
                Document doc = searcher.doc(sd.doc);
                String auctionName = doc.get(Index.AUCTION_NAME);
                SimpleHTMLFormatter shf = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
                Highlighter highlighter = new Highlighter(shf, new QueryScorer(q));
                highlighter.setTextFragmenter(new SimpleFragmenter(Integer.MAX_VALUE));
                String content = highlighter.getBestFragment(new StandardAnalyzer(Version.LUCENE_35), Index.AUCTION_NAME, auctionName);
                System.out.println(doc.get(Index.AUCTION_NO)+" , "+content+" , "+doc.get(Index.MAX_PRICE)+" , "+doc.get(Index.END_DATE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页查询
     *
     * @param start
     * @param howMany
     */
    public void pageQuery(int start, int howMany){
        Term t = new Term(Index.AUCTION_NAME, keyword);
        TermQuery q = new TermQuery(t);
        System.out.println("=============分页搜索");
        this.doPageSearch(q, start, howMany);
    }

    /**
     * 分页
     *
     */
    public void doPageSearch(Query q, int start, int howMany){
        TopScoreDocCollector collector = TopScoreDocCollector.create(start+howMany, false);
        try {
            searcher.search(q, collector);
            int count = collector.getTotalHits();
            System.out.println("------------获得 "+count+" 记录！");
            TopDocs top = collector.topDocs(start, howMany);
            ScoreDoc[] docs = top.scoreDocs;
            for (ScoreDoc sd : docs) {
                Document doc = searcher.doc(sd.doc);
                System.out.println(doc.get(Index.AUCTION_NO)+","+doc.get(Index.AUCTION_NAME));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


