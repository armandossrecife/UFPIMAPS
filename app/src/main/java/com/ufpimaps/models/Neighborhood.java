package com.ufpimaps.models;

/**
 * Essa classe representa a adjac�ncia(conex�o) entre dois n�s geogr�ficos.
 * Created by HugoPiauilino on 07/05/15.
 */
public class Neighborhood {

    /**
     * No mapeamento, esse atributo ser� o identificador de uma conex�o de n�s
     * geogr�ficos e ter� a fun��o de chave prim�ria da tabela neighbohorhood.
     */

    private int idNeighborhood;

    /**
     * Foreign Key de um dos n�s geogr�ficos. Node 1
     */

    private GeographicNode idNode1;

    /**
     * Foreign Key de um dos n�s geogr�ficos. Node 2
     */

    private GeographicNode idNode2;

    /**
     * No mapeamento, esse atributo ser� a dist�ncia em metros da liga��o entre
     * dois n�s geogr�ficos.
     */

    private double distance;


    /**
     * Retorna o valor do atributo 'idNeighbohorhood' da classe Neighbohoorhood.
     * @return
     */
    public int getIdNeighborhood() {
        return idNeighborhood;
    }

    /**
     * Retorna o valor do atributo 'idnNode1' da classe Neighborhood
     * @return idNode1
     */
    public GeographicNode getIdNode1() {
        return idNode1;
    }

    /**
     * Atribui um valor ao atributo 'idNode1' da classe Neighborhood
     * @param idNode1
     */
    public void setIdNode1(GeographicNode idNode1) {
        this.idNode1 = idNode1;
    }

    /**
     * Retorna o valor do atributo 'idnNode2' da classe Neighborhood
     * @return idNode2
     */
    public GeographicNode getIdNode2() {
        return idNode2;
    }

    /**
     * Atribui um valor ao atributo 'idNode3' da classe Neighborhood
     * @param idNode2
     */
    public void setIdNode2(GeographicNode idNode2) {
        this.idNode2 = idNode2;
    }

    /**
     * Retorna o valor do atributo 'distance' da classe Neighborhood
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Atribui um valor ao atributo 'distance' da classe Neighborhood
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Construtor padr�o da classe Neighborhood.
     */
    public Neighborhood(){

    }

}
