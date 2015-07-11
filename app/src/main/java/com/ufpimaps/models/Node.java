package com.ufpimaps.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Classe que implementa a estrutura de um no.
 * Created by HugoPiauilino on 07/05/15.
 */

public class Node {

    private Integer id;
    private String name;
    private String description;
    private Integer type;
    private String services;
    private LatLng localization;
    private String email;
    private String website;
    private String phone;

    /**
     * Construtor da classe Node com suas tipificações.
     *
     * @param id
     * @param name
     * @param description
     * @param type
     * @param services
     * @param localization
     * @param email
     * @param website
     * @param phone
     */
    public Node(Integer id, String name, String description, Integer type, String services, LatLng localization, String email, String website, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.services = services;
        this.localization = localization;
        this.email = email;
        this.website = website;
        this.phone = phone;
    }

    /**
     * Construtor vazio da classe Node.
     */
    public Node() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocalization(LatLng localization) {
        this.localization = localization;
    }

    public LatLng getLocalization() {
        return localization;
    }

    public String getDescription() {
        return description;
    }

    public Integer getType() {
        return type;
    }

    public String getServices() {
        return services;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }
}


