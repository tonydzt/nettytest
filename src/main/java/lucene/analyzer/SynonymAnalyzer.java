//package lucene.analyzer;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.CharArraySet;
//import org.apache.lucene.analysis.LowerCaseFilter;
//import org.apache.lucene.analysis.StopFilter;
//import org.apache.lucene.analysis.core.LowerCaseTokenizer;
//import org.apache.lucene.analysis.standard.StandardFilter;
//import org.apache.lucene.analysis.standard.StandardTokenizer;
//import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
//import org.apache.lucene.analysis.synonym.SynonymMap;
//import org.apache.lucene.util.CharsRef;
//import org.apache.lucene.util.Version;
//
//import java.util.Arrays;
//import java.util.Collections;
//
///**
// * @author douzhitong
// * @date 2020/7/20
// */
//public class SynonymAnalyzer extends Analyzer {
//
//    @Override
//    protected TokenStreamComponents createComponents(String fieldName) {
//
//        SynonymMap.Builder builder = new SynonymMap.Builder(true);
//        builder.add(new CharsRef("one"), new CharsRef("first"), true);
//
//        return new TokenStreamComponents(new SynonymGraphFilter(new StopFilter(new LowerCaseFilter(new StandardFilter(new StandardTokenizer())),
//                        CharArraySet.unmodifiableSet(new CharArraySet(Collections.singletonList(","), true))
//                )
//        ));
//    }
//}
