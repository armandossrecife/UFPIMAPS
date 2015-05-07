package com.ufpimaps.models;

import java.util.List;

/**
 * Essa classe representa um tipo de no derivado da classe Node: no geografico.
 * Created by HugoPiauilino on 07/05/15.
 */
public class GeographicNode extends Node{

    @SuppressWarnings("unused")
    private List<Neighborhood> neighborhood;

    /**
     * Essa variavel guarda todos os nos administrativos ligados ao no geografico.
     */

    private List<GeoAdmAssociation> geoAdmAssociation;


    public List<GeoAdmAssociation> getGeoAdmAssociation() {
        return geoAdmAssociation;
    }
    /**
     * Construtor padrao da classe GeographicNode
     */
    public GeographicNode(){

    }

    /**
     * Construtor da classe GeographicNode com alguns atributos na instancia do objeto.
     */
    public GeographicNode(String desc, double lat, double longi){
        super(desc, lat, longi);
    }

}
