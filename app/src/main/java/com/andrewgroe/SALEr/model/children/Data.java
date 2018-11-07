package com.andrewgroe.SALEr.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("created_utc")
    @Expose
    private Long created;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("domain")
    @Expose
    private String domain;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("num_comments")
    @Expose
    private int numComments;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("stickied")
    @Expose
    private Boolean stickied;

    @SerializedName("link_flair_text")
    @Expose
    private String linkFlairText;

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getStickied() {
        return stickied;
    }

    public void setStickied(Boolean stickied) {
        this.stickied = stickied;
    }

    public String getLinkFlairText() {
        return linkFlairText;
    }

    public void setLinkFlairText(String linkFlairText) {
        this.linkFlairText = linkFlairText;
    }

    public Data(Long created, String title, String domain, String url, String permalink, String author, int score, int numComments, String thumbnail, Boolean stickied, String linkFlairText) {
        this.created = created;
        this.title = title;
        this.domain = domain;
        this.url = url;
        this.permalink = permalink;
        this.author = author;
        this.score = score;
        this.numComments = numComments;
        this.thumbnail = thumbnail;
        this.stickied = stickied;
        this.linkFlairText = linkFlairText;

    }

}
