package com.ufpimaps.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que armazena os tipos de anconras
 */

public class AnchorsList {

    /**
     * Array de Ancoras.
     */
    public static List<Anchor> ITEMS = new ArrayList<Anchor>();

    /**
     * Hash Map de Ancoras, por ID.
     */
    public static Map<String, Anchor> ITEM_MAP = new HashMap<String, Anchor>();

    static {
        addItem(new Anchor("Departamentos", "Departamentos"));
        addItem(new Anchor("Setores Administrativos", "Setores Administrativos"));
        addItem(new Anchor("Bibliotecas", "Bibliotecas"));
        addItem(new Anchor("Lanchonetes", "Lanchonetes" ));
        addItem(new Anchor("Pró Reitorias", "Pró Reitorias"));
        addItem(new Anchor("Entradas e Saídas", "Entradas e Saídas"));
    }

    private static void addItem(Anchor item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
}
