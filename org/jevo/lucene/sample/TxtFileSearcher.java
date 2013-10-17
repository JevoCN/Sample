package org.jevo.lucene.sample;

import org.apache.lucene.search.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-15
 * Time: 15:54:52
 * To change this template use File | Settings | File Templates.
 */

public class TxtFileSearcher {
    public static void main(String[] args) throws Exception {
        String queryString = "介绍";
        IndexSearcher searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File("D:\\temp\\test"))));


        //查询解析器
        Query query = IKQueryParser.parse("title", queryString);
        BooleanQuery blQuery = new BooleanQuery();
        blQuery.add(query, BooleanClause.Occur.MUST);
        blQuery.add(new TermQuery(new Term("content", queryString)), BooleanClause.Occur.SHOULD);

        //搜索结果使用Hits存储
        TopDocs results = searcher.search(blQuery, 1000);

        ScoreDoc[] hits = results.scoreDocs;
        for (ScoreDoc sd : hits) {
            int docID = sd.doc;
            Document doc = searcher.doc(docID);
            System.err.println("title = " + doc.get("title"));
            System.err.println("content = " + doc.get("content"));
        }

    }

    public void testQueryPraser(Version version, Analyzer analyzer) throws Exception {
        //QueryParser parser = new QueryParser(version, "content", analyzer);//默认在content域中查找
        QueryParser parser = new MyQueryParser(version, "content", analyzer);//默认在content域中查找, 重写了getRangeQuery方法， 可以进行数字范围查询
        Query query = null;

        //查找content（默认域）中存在软件的结果
        query = parser.parse("软件");

        //当用户输入两个关键字时，QueryParser默认它们之间的关系为“或”关系
        //下面这么写的话在对用户输入进行扫描时，就会用空格分开的关键字理解为“与”，
       //其实也就是构建了一个“与”关系的布尔型查询   
//          parser.setDefaultOperator(Operator.AND);

        //查找name域中有java的结果
        query = parser.parse("name:java");

        //使用通配符
        query = parser.parse("name: j*");//默认*号不能出现在最前端， 会影响效率, 使用parser.setAllowLeadingWildcard(true)打开
        query = parser.parse("lu*n?");//只能在单个分词中使用通配符， 即 使用 “lucene*action”就匹配不到
        query = parser.parse("lucene test");//默认lucene和action是或关系
        query = parser.parse("lucene OR test");//同上, OR必须大写
        query = parser.parse("lucene AND test");//与关系
        query = parser.parse("- lucene + test");//存在test但不存在lucene
        //query = parser.parse("size:[100 TO 200]");//这个query是TermRangeQuery所以不能用于数字（日期）范围查询
        query = parser.parse("name:[java TO java]");//查找有a-z字母的    {}不包含
        //数字范围查询需要重写QueryParser的getRangeQuery方法
        query = parser.parse("date:[1334550379955 TO 1334550379955]");
        query = parser.parse("\"lucene action\"~1");//1即phraseQuery中的slop=1
        query = parser.parse("name:xava~0.74");//加上~代表模糊查询， 要和上面的额短语查询相区别开来，另外~后面可以加一个浮点数去顶相似度
        //查询大小在145到150， 名称与javv相似， 创建时间在1334550379955之前的 不要， lucene与action相距1
        query = parser.parse("size:[145 TO 150] + name:javv~ - date:[1 TO 1334550379954] + \"lucene action\"~1");
//        doSearch(query);
    }

    class MyQueryParser extends QueryParser {
        public MyQueryParser(Version matchVersion, String f, Analyzer a) {
            super(matchVersion, f, a);
        }

        @Override
        protected org.apache.lucene.search.Query getRangeQuery(String field,
                                                               String part1, String part2, boolean inclusive)
                throws ParseException {
            if ("size".equals(field) || "date".equals(field)) {
                return NumericRangeQuery.newLongRange(field, Long.parseLong(part1),
                        Long.parseLong(part2), inclusive, inclusive);
            }
            return super.newRangeQuery(field, part1, part2, inclusive);
        }
    }

}
