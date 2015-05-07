package com.ufpimaps.models;

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

    /**
     * Esse objeto se refere a localizacao de um no.
     * Coordenadas x e y.
     */

    private Localization localization;


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
    /**
     * Retorna o valor do atributo 'localization' da classe Node.
     * @return localization.
     */
    public Localization getLocalization() {
        return localization;
    }

    /**
     * Atribui um valor ao atributo de 'localization' da classe Node.
     * @param localization
     */
    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    /**
     * Construtor padrao da classe Node.
     */
    public Node(){

    }

    /**
     * Construtor da classe Node com alguns atributos na instancia do objeto.
     */
    public Node(String desc, double lat, double longi){
        this.description = desc;
        this.localization = new Localization(lat, longi);

    }

}
