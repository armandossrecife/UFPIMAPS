package com.ufpimaps.models;

/**
 * Essa classe representa um tipo de n� derivado da classe Node: parti��es
 * Created by HugoPiauilino on 07/05/15.
 */
public class Partition extends Node {

    /**
     * Esse atributo representar�  o id do n� administrativo a qual o
     * a parti��o pertence.
     *
     */

    private AdministrativeNode administrativeNode;


    /**
     * Retorna o valor do atributo 'administrativeNode' da classe Partition
     * @return administrativeNode
     */
    public AdministrativeNode getAdministrativeNode() {
        return administrativeNode;
    }

    /**
     * Atribui um valor ao atributo 'administrativeNode' da classe Partition
     * @param administrativeNode
     */
    public void setAdministrativeNode(AdministrativeNode administrativeNode) {
        this.administrativeNode = administrativeNode;
    }

    /**
     * Construtor padr�o da classe Partition.
     */
    public Partition(){

    }

    /**
     * Construtor padr�o da classe Partition com alguns atributos na inst�ncia do objeto.
     */
    public Partition(String desc, double lat, double longi){
        super(desc, lat, longi);
    }

}
