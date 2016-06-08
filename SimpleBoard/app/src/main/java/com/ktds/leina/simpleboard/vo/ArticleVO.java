package com.ktds.leina.simpleboard.vo;

/**
 * Created by 206-025 on 2016-06-08.
 */
public class ArticleVO {

    private int articleNo;
    private String subject;
    private String description;
    private String author;

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArticleVO(int articleNo, String subject, String description, String author) {
        this.articleNo = articleNo;
        this.subject = subject;
        this.description = description;
        this.author = author;
    }
}
