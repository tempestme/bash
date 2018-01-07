package com.example.pavel.bash.model;

/**
 * Created by pavel on 02.01.18.
 */

public class Post {
    String site;
    String name;
    String desc;
    String link;
    String elementPureHtml;

    public Post(String site, String name, String desc, String link, String elementPureHtml) {
        this.site = site;
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.elementPureHtml = elementPureHtml;
    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

}
