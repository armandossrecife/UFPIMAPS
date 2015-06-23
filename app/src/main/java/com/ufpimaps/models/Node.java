package com.ufpimaps.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Classe que implementa a estrutura generica de um no
 * Created by HugoPiauilino on 07/05/15.
 */

public class Node {

    private int id;
    private String name;
    private String description;
    private int type;
    private String services;
    private LatLng localization;
    private String email;
    private String website;
    private String phone;

    public Node(String desc, LatLng localization){
        this.name = desc;
        this.localization = localization;
    }

    public Node() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocalization(LatLng localization){
        this.localization = localization;
    }

    public LatLng getLocalization(){
        return localization;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}


