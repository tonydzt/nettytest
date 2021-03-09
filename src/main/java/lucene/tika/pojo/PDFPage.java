package lucene.tika.pojo;

/**
 * @author douzhitong
 * @date 2020/9/4
 */
public class PDFPage {

    private String content;
    private Integer pageNum;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
