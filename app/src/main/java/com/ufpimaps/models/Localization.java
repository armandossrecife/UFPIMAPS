package com.ufpimaps.models;

/**
 * Essa classe tem a funcao de representar a localizacao x e y de um no.
 * Created by HugoPiauilino on 07/05/15.
 */
public class Localization {
    /**
     * No mapeamento, esse atributo sera a latitude de um no.
     */
    private double latitude;

    /**
     * No mapeamento, esse atributo sera a longitude de um no.
     */
    private double longitude;

    /**
     * Retorna o valor do atributo 'latitude' da classe Localization
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Atribui um valor ao atributo 'latitude' da classe Localization
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retorna o valor do atributo 'longitude' da classe Localization
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Atribui um valor ao atributo 'longitude' da classe Localization
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Construtor padrao da classe Localization
     */
    public Localization(){

    }

    /**
     * /**
     * Construtor da classe Localization com alguns atributos na instancia do objeto.
     * @param latitude
     * @param longitude
     */
    public Localization(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
