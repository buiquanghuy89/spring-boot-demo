package com.demo.spring.boot.extractHtml;

/**
 * Created by bqhuy on 7/23/2018.
 */
public class HtmlLink {
    String link;
    String title;

    @Override
    public String toString() {
        return new StringBuffer("Title : ").append(this.title)
                .append(" link : ").append(this.link).toString();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = replaceInvalidChar(link);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String replaceInvalidChar(String link) {
        link = link.replaceAll("'", "");
        link = link.replaceAll("\"", "");
        return link;
    }
}
