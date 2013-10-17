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
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-15
 * Time: 15:54:52
 * To change this template use File | Settings | File Templates.
 */

public class TxtFileSearcher {
    public static void main(String[] args) throws Exception {
        String queryString = "����";
        IndexSearcher searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File("D:\\temp\\test"))));


        //��ѯ������
        Query query = IKQueryParser.parse("title", queryString);
        BooleanQuery blQuery = new BooleanQuery();
        blQuery.add(query, BooleanClause.Occur.MUST);
        blQuery.add(new TermQuery(new Term("content", queryString)), BooleanClause.Occur.SHOULD);

        //�������ʹ��Hits�洢
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
        //QueryParser parser = new QueryParser(version, "content", analyzer);//Ĭ����content���в���
        QueryParser parser = new MyQueryParser(version, "content", analyzer);//Ĭ����content���в���, ��д��getRangeQuery������ ���Խ������ַ�Χ��ѯ
        Query query = null;

        //����content��Ĭ�����д�������Ľ��
        query = parser.parse("���");

        //���û����������ؼ���ʱ��QueryParserĬ������֮��Ĺ�ϵΪ���򡱹�ϵ
        //������ôд�Ļ��ڶ��û��������ɨ��ʱ���ͻ��ÿո�ֿ��Ĺؼ������Ϊ���롱��
       //��ʵҲ���ǹ�����һ�����롱��ϵ�Ĳ����Ͳ�ѯ   
//          parser.setDefaultOperator(Operator.AND);

        //����name������java�Ľ��
        query = parser.parse("name:java");

        //ʹ��ͨ���
        query = parser.parse("name: j*");//Ĭ��*�Ų��ܳ�������ǰ�ˣ� ��Ӱ��Ч��, ʹ��parser.setAllowLeadingWildcard(true)��
        query = parser.parse("lu*n?");//ֻ���ڵ����ִ���ʹ��ͨ����� �� ʹ�� ��lucene*action����ƥ�䲻��
        query = parser.parse("lucene test");//Ĭ��lucene��action�ǻ��ϵ
        query = parser.parse("lucene OR test");//ͬ��, OR�����д
        query = parser.parse("lucene AND test");//���ϵ
        query = parser.parse("- lucene + test");//����test��������lucene
        //query = parser.parse("size:[100 TO 200]");//���query��TermRangeQuery���Բ����������֣����ڣ���Χ��ѯ
        query = parser.parse("name:[java TO java]");//������a-z��ĸ��    {}������
        //���ַ�Χ��ѯ��Ҫ��дQueryParser��getRangeQuery����
        query = parser.parse("date:[1334550379955 TO 1334550379955]");
        query = parser.parse("\"lucene action\"~1");//1��phraseQuery�е�slop=1
        query = parser.parse("name:xava~0.74");//����~����ģ����ѯ�� Ҫ������Ķ�����ѯ��������������~������Լ�һ��������ȥ�����ƶ�
        //��ѯ��С��145��150�� ������javv���ƣ� ����ʱ����1334550379955֮ǰ�� ��Ҫ�� lucene��action���1
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
