package com.demo.spring.boot.extractHtml;

/**
 * Created by HuyBQ on 8/5/2018.
 */
public class Book {
    private String title;
    private String linkDownload;
    private String prefixFileName;
    private String extension;

    public Book(String title, String linkDownload, String prefixFileName, String extension) {
        this.title = title;
        this.linkDownload = linkDownload;
        this.prefixFileName = prefixFileName;
        this.extension = extension;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public String getPrefixFileName() {
        return prefixFileName;
    }

    public void setPrefixFileName(String prefixFileName) {
        this.prefixFileName = prefixFileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
