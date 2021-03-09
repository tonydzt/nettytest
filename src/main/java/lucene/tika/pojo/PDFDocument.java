package lucene.tika.pojo;

import org.apache.tika.metadata.Metadata;

import java.util.List;

/**
 * @author douzhitong
 * @date 2020/9/4
 */
public class PDFDocument {

    private List<PDFPage> pageList;
    private Integer totalPageNum;
    private String title;
    private Metadata metadata;

    public List<PDFPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<PDFPage> pageList) {
        this.pageList = pageList;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
