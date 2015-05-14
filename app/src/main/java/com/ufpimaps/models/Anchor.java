package com.ufpimaps.models;

/**
 * Created by HugoPiauilino on 14/05/15.
 */
/**
 * Classe que representa uma Ancora
 */
public class Anchor {
    public String id;
    public String content;

    public Anchor(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
