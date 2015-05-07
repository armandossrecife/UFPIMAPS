package com.ufpimaps.models;

/**
 *  Essa classe representa a associacao entre nos geograficos e nos associativos.
 * Created by HugoPiauilino on 07/05/15.
 */
public class GeoAdmAssociation {

    /**
     * No mapeamento, esse atributo sera o identificador de uma associacao de nos geograficos e
     * nos administrativos e tera a funcao de chave primaria da tabela.
     */

    private int idAssociation;


    /**
     * Foreign Key da tabela de nos administrativos.
     */

    private AdministrativeNode idAdministrativeNode;

    /**
     * Foreign Key da tabela de nos geograficos.
     */

    private GeographicNode idGeographicNode;


    /**
     * Esse atributo representa a distancia da associacao entre os nos.
     */

    private double distance;


    public int getIdAssociation() {
        return idAssociation;
    }

    public void setIdAssociation(int idAssociation) {
        this.idAssociation = idAssociation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public AdministrativeNode getIdAdministrativeNode() {
        return idAdministrativeNode;
    }

    public void setIdAdministrativeNode(AdministrativeNode idAdministrativeNode) {
        this.idAdministrativeNode = idAdministrativeNode;
    }

    public GeographicNode getIdGeographicNode() {
        return idGeographicNode;
    }

    public void setIdGeographicNode(GeographicNode idGeographicNode) {
        this.idGeographicNode = idGeographicNode;
    }

}
