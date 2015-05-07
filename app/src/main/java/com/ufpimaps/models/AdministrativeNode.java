package com.ufpimaps.models;

import java.util.List;

/**
 * Essa classe representa um tipo de no derivado da classe Node: nos administrativos.
 * Created by HugoPiauilino on 07/05/15.
 */
public class AdministrativeNode extends Node {
    private List<GeoAdmAssociation> geoAdmAssociation;


    private List<Partition> partitions;


    /**
     * Retorna o valor do atributo 'partitions' da classe GeographicNode.
     * @return partitions
     */
    public List<Partition> getPartitions() {
        return partitions;
    }

    /**
     * Construtor padrao da classe AdministrativeNode
     */
    public AdministrativeNode(){

    }

    /**
     * Construtor padrao da classe AdministrativeNode com alguns atributos na instancia do objeto.
     */
    public AdministrativeNode(String desc, double lat, double longi){
        super(desc, lat, longi);
    }
}
