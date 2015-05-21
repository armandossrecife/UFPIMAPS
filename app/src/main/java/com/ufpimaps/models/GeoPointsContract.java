package com.ufpimaps.models;

import android.provider.BaseColumns;

/**
 * Classe que cria o Contract do Database de GeoPoints
 */

public final class GeoPointsContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public GeoPointsContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class GeoPointsEntry implements BaseColumns {

        /**
         * Elementos da Tabela Node
         */

        public static final String TABLE_NODE = "node";
        public static final String COLUMN_NODE_ID = "idnode";
        public static final String COLUMN_NODE_DESCRIPTION = "description";
        public static final String COLUMN_NODE_LATITUDE = "latitude";
        public static final String COLUMN_NODE_LONGITUDE = "longitude";

    }
}
