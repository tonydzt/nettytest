package lucene.tika;

import lucene.tika.pojo.PDFDocument;
import lucene.tika.pojo.PDFPage;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BasicContentHandlerFactory;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerFactory;
import org.apache.tika.sax.RecursiveParserWrapperHandler;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author douzhitong
 * @date 2020/9/3
 */
public class TikaTest {

    public static void main(String[] args) throws Exception {
//        parsePDF();
//        parsePDFDocument();
        parsePDFRecursive();
    }

    public static void parsePDF() throws IOException, TikaException {
        //选择要提取的文件
        File file = new File("/Users/dzt/Downloads/books/elk-stack-guide-cn.pdf");

        //获取并打印文件内容
        Tika tika = new Tika();
        String filecontent = tika.parseToString(file);
        System.out.println("Extracted Content: " + filecontent);
    }

    public static void parsePDFRecursive() throws IOException, TikaException, SAXException {
        //选择要提取的文件
        File file = new File("/Users/dzt/Downloads/books/elk-stack-guide-cn.pdf");
        PDFParser pdfParser = new PDFParser();
        RecursiveParserWrapper wrapper = new RecursiveParserWrapper(pdfParser);
        ContentHandlerFactory factory = new BasicContentHandlerFactory(BasicContentHandlerFactory.HANDLER_TYPE.BODY, -1);
        RecursiveParserWrapperHandler recursiveParserWrapperHandler = new RecursiveParserWrapperHandler(factory, -1);
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        wrapper.parse(new FileInputStream(file), recursiveParserWrapperHandler, metadata, context);
        System.out.println(recursiveParserWrapperHandler.getMetadataList());
        System.out.println("hahaha");
    }

    public static void parsePDFDocument() throws IOException, TikaException, SAXException {
        //选择要提取的文件
        File file = new File("/Users/dzt/Downloads/books/elk-stack-guide-cn.pdf");

        PDFParser pdfParser = new PDFParser();
        BodyContentHandler contentHandler = new BodyContentHandler(20 * 1000 * 1000);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        pdfParser.parse(new FileInputStream(file), contentHandler, metadata, parseContext);
        PDFDocument pdfDocument = getDocument(contentHandler, metadata);
        System.out.println(pdfDocument);
    }

    public static PDFDocument getDocument(BodyContentHandler contentHandler, Metadata metadata) throws SAXException {
        PDFDocument pdfDocument = new PDFDocument();
        pdfDocument.setTitle(metadata.get("title"));
        pdfDocument.setTotalPageNum(Integer.parseInt(metadata.get("xmpTPg:NPages")));
        List<PDFPage> pdfPageList = new ArrayList<>();
        String[] pages = metadata.getValues("pdf:charsPerPage");
        int pageStart = 0;
        for (int pageNum = 0; pageNum < pdfDocument.getTotalPageNum(); pageNum++) {
            PDFPage pdfPage = new PDFPage();
            int pageSize = Integer.parseInt(pages[pageNum]);
            char[] chars = new char[pageSize];
            contentHandler.characters(chars, pageStart, pageSize);
            pdfPage.setContent(new String(chars));
            pdfPage.setPageNum(pageNum);
            pdfPageList.add(pdfPage);
            pageStart += pageSize;
        }
        pdfDocument.setPageList(pdfPageList);
        pdfDocument.setMetadata(metadata);
        return pdfDocument;
    }

}
