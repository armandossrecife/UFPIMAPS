package com.ufpimaps.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Classe que implementa a estrutura generica de um no
 * Created by HugoPiauilino on 07/05/15.
 */

public class Node {

    /**
     * No mapeamento, esse atributo sera o identificador de um no e tera a funcao
     * de chave primaria da tabela.
     */

    private int idNode;

    /**
     * No mapeamento, esse atributo sera a descricao de um no.
     */

    private String description;

    private LatLng localization;

    /**
     * Retorna o valor do atributo 'id' da classe Node.
     * @return id.
     */
    public int getIdNode() {
        return idNode;
    }

    /**
     * Atribui um valor ao atributo 'description' da classe Node.
     * @param idNode
     */
    public void setIdNode(int idNode){
        this.idNode = idNode;
    }

    /**
     * Retorna o valor do atributo 'description' da classe Node.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Atribui um valor ao atributo 'description' da classe Node.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalization(LatLng localization){
        this.localization = localization;
    }

    public LatLng getLocalization(){
        return localization;
    }

    /**
     * Construtor padrao da classe Node.
     */
    public Node(){

    }

    /**
     * Construtor da classe Node com alguns atributos na instancia do objeto.
     */
    public Node(String desc, LatLng localization){
        this.description = desc;
        this.localization = localization;
    }

}
