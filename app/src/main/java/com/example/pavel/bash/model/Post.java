package com.example.pavel.bash.model;

import java.util.ArrayList;

/**
 * Created by pavel on 02.01.18.
 */

public class Post {
    String site;
    String name;
//    String desc;
//    String link;
    String elementPureHtml;

    public Post(String site, String name, String elementPureHtml) {
        this.site = site;
        this.name = name;
        this.elementPureHtml = elementPureHtml;
    }
    public Post(){

    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

//    public String getDesc() {
//        return desc;
//    }
//
//    public String getLink() {
//        return link;
//    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

    /**
     * @param mainList is the main list with all posts, it will be going straight to adapter.
     * @param newPosts this will get all posts straight from internets. ->> addNewPosts() method will add this new posts to mainList
     */
    public void addNewPosts(ArrayList<Post> mainList, ArrayList<Post> newPosts){
        newPosts.removeAll(mainList);
        ArrayList<Post> allPosts = new ArrayList<Post>();
        allPosts.addAll(newPosts);
        allPosts.addAll(mainList);
        mainList.clear();
        mainList.addAll(allPosts);
    }

    /**
     * method add ads to every fifth post
     * @param posts is the main list of posts
     * @return
     */
    public ArrayList<Post> addAds(ArrayList<Post> posts){

        return posts;
    }





}
