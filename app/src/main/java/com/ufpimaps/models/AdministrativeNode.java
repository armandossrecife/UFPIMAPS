package com.ufpimaps.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Classe que representa um tipo de No derivado da classe Node: Nos Administrativos.
 * Created by HugoPiauilino on 07/05/15.
 */
public class AdministrativeNode extends Node {

    private List<GeoAdmAssociation> geoAdmAssociation;

    private List<Partition> partitions;

    /**
     * Construtor padrao da classe AdministrativeNode
     */
    public AdministrativeNode() {
    }

    /**
     * Construtor padrao da classe AdministrativeNode com alguns atributos na instancia do objeto.
     */
    public AdministrativeNode(String desc, LatLng localization) {
        super(desc, localization);
    }

    /**
     * Retorna o valor do atributo 'partitions' da classe GeographicNode.
     *
     * @return partitions
     */
    public List<Partition> getPartitions() {
        return partitions;
    }
}
