package com.ufpimaps.models;

/**
 * Created by HugoPiauilino on 14/05/15.
 */
/**
 * Classe que representa uma Ancora
 */
public class Anchor {

    private String id;
    private String content;

    public Anchor(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
