package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.CharsRefBuilder;
import org.apache.lucene.util.RamUsageEstimator;
import org.apache.lucene.util.fst.FST;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author douzhitong
 * @date 2020/7/17
 */
public class TokenStreamTest {

    public static void main(String[] args) throws Exception {
//        displayTokensWithFullDetails(new SimpleAnalyzer(), "The quick brown fox jumped over the lazy dog");
        ramUsageEstimator();
//        synonymMapBuilder();
//        perFieldAnalyzer();
    }

    private static void displayTokensWithFullDetails(Analyzer analyzer, String text) throws IOException {
        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));

        // 这四个Attribute其实对应的是一个对象 - PackedTokenAttributeImpl。
        CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
        PositionIncrementAttribute posIncr =  stream.addAttribute(PositionIncrementAttribute.class);
        OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
        TypeAttribute typeAttribute = stream.addAttribute(TypeAttribute.class);

        int position = 0;
        stream.reset();
        //这里其实只修改了PackedTokenAttributeImpl的offset但是看起来像是修改了四个对象的offset
        while(stream.incrementToken()) {
            int increment = posIncr.getPositionIncrement();
            if (increment > 0) {
                position = position + increment;
                System.out.println();
                System.out.print(position + ": ");
            }
            System.out.print(
                    "[" + term.toString() + ":" +
                            offset.startOffset() + "->" +
                            offset.endOffset() + ":" + typeAttribute.type() + "]");
        }
        System.out.println();
    }

    private static void synonymMapBuilder() throws IOException {
        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("one"), new CharsRef("first"), true);
        builder.add(new CharsRef("one"), new CharsRef("alpha"), true);
        builder.add(new CharsRef("one"), new CharsRef("beguine"), true);
        CharsRefBuilder multiWordCharsRef = new CharsRefBuilder();
        SynonymMap.Builder.join(new String[]{"and", "indubitably", "single", "only"}, multiWordCharsRef);
        System.out.println(multiWordCharsRef.get());
        builder.add(new CharsRef("one"), multiWordCharsRef.get(), true);
        SynonymMap.Builder.join(new String[]{"dopple", "ganger"}, multiWordCharsRef);
        builder.add(new CharsRef("two"), multiWordCharsRef.get(), true);
        SynonymMap synonymMap = builder.build();
        System.out.println(synonymMap.words);
    }

    private static void ramUsageEstimator() {
        System.out.println(RamUsageEstimator.shallowSizeOfInstance(FST.class));
        System.out.println(RamUsageEstimator.shallowSizeOf(FST.class));
    }

    private static void perFieldAnalyzer() throws ParseException {
        Map<String, Analyzer> analyzerMap = new HashMap<>();
        analyzerMap.put("partnum", new KeywordAnalyzer());
        SimpleAnalyzer analyzer = new SimpleAnalyzer();
        Query query = new QueryParser("description", analyzer).parse("partnum:Q36 AND SPACE");
        System.out.println(query.toString("description"));

        PerFieldAnalyzerWrapper analyzerWrapper = new PerFieldAnalyzerWrapper(new SimpleAnalyzer(), analyzerMap);
        query = new QueryParser("description", analyzerWrapper).parse("partnum:Q36 AND SPACE");
        System.out.println(query.toString("description"));
    }
}
