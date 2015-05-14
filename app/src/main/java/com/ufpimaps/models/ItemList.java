package com.ufpimaps.models;

/**
 * Created by HugoPiauilino on 12/05/15.
 */
public class ItemList {

    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public ItemList(String title){
        this.itemTitle = title;
    }
}
