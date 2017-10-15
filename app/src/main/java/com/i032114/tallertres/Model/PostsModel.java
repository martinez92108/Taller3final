package com.i032114.tallertres.Model;

import java.util.StringTokenizer;

/**
 * Created by martinez on 14/10/17.
 */

public class PostsModel {

    private Integer userid;
    private Integer id;
    private String title;
    private String body;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
