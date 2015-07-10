package com.ufpimaps.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.firebase.client.DataSnapshot;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que armazena os principais geopoints da ufpi para agilizar a busca no aplicativo.
 * Created by HugoPiauilino on 21/05/15.
 */
public class GeoPointsDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NODE = "node";
    public static final String COLUMN_NODE_ID = "id";
    public static final String COLUMN_NODE_NAME = "name";
    public static final String COLUMN_NODE_DESCRIPTION = "descricao";
    public static final String COLUMN_NODE_TYPE = "tipo";
    public static final String COLUMN_NODE_SERVICES = "servicos";
    public static final String COLUMN_NODE_LATITUDE = "latitude";
    public static final String COLUMN_NODE_LONGITUDE = "longitude";
    public static final String COLUMN_NODE_EMAIL = "email";
    public static final String COLUMN_NODE_WEBSITE = "site";
    public static final String COLUMN_NODE_PHONE = "telefone";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GeoPointsDatabase.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NODE + " (" +
                    COLUMN_NODE_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                    COLUMN_NODE_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_TYPE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NODE_SERVICES + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_LONGITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_EMAIL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_WEBSITE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_PHONE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NODE;

    /**
     * Construtor da Classe GeoPointsDatabase
     * @param context
     */
    public GeoPointsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método que cria o banco de dados.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Método que atualiza o banco de dados para uma versão superior.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Método que atualiza o banco de dados para uma versão inferior.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Método que adiciona um nó ao Banco de Dados.
     * @param no Tipo Nó
     */
    public void addNode(Node no) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NODE_ID, no.getId());
        values.put(COLUMN_NODE_NAME, no.getName());
        values.put(COLUMN_NODE_DESCRIPTION, no.getDescription());
        values.put(COLUMN_NODE_TYPE, no.getType());
        values.put(COLUMN_NODE_SERVICES, no.getServices());
        values.put(COLUMN_NODE_LATITUDE, no.getLocalization().latitude);
        values.put(COLUMN_NODE_LONGITUDE, no.getLocalization().longitude);
        values.put(COLUMN_NODE_EMAIL, no.getEmail());
        values.put(COLUMN_NODE_WEBSITE, no.getWebsite());
        values.put(COLUMN_NODE_PHONE, no.getPhone());

        db.insert(TABLE_NODE, null, values);
        db.close();
    }

    /**
     * Método que retorna uma lista com o nome de todos os nós existentes no banco de dados.
     * @return Lista de Nomes dos Nós existentes no Banco de Dados
     */
    public ArrayList<String> getNodesNames() {

        ArrayList<String> nodesDescriptionsList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                nodesDescriptionsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return nodesDescriptionsList;
    }

    /**
     * Método que verifica se determinado nó existe no banco de dados.
     * @param name Nome do nó escolhido
     * @return True - Nó existe | False - Nó não existe
     */
    public boolean hasNode(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_NAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.moveToFirst();
    }

    /**
     * Método que retorna um nó com um determinado nome.
     * @param name Nome do Nó
     * @return Nó com o nome escolhido
     */
    public Node selectByName(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_NAME + " = '" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Node node = new Node();
            node.setId(Integer.parseInt(cursor.getString(0)));
            node.setName(cursor.getString(1));
            LatLng localization = new LatLng(cursor.getDouble(5), cursor.getDouble(6));
            node.setLocalization(localization);
            return node;

        }
        return null;

    }

    /**
     * Metodo que retorna uma lista de nós de determinado tipo.
     * @param type Tipo do Nó
     * @return Lista de Nós do tipo escolhido
     */
    public List<Node> selectByType(String type) {

        List<Node> nodes = new ArrayList<Node>();

        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_TYPE + " = '" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToFirst()) {
            Node node = new Node();
            node.setId(Integer.parseInt(cursor.getString(0)));
            node.setName(cursor.getString(1));
            LatLng localization = new LatLng(cursor.getDouble(5), cursor.getDouble(6));
            node.setLocalization(localization);
            nodes.add(node);

        }
        return nodes;

    }

    /**
     * Método que popula o banco de dados interno da aplicação
     * @param snapshot
     */
    public void populateDatabase(DataSnapshot snapshot) {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, DATABASE_VERSION, DATABASE_VERSION+1);
        ArrayList<HashMap> fireB = (ArrayList<HashMap>) snapshot.getValue();
        HashMap nodeHash;
        HashMap localizationHash;
        Node no;
        for (int i = 0; i < fireB.size(); i++) {
            nodeHash = fireB.get(i);
            Long idLong = (Long) nodeHash.get("id");
            Integer idInteger = Integer.valueOf(idLong.intValue());
            Long typeLong = (Long) nodeHash.get("type");
            Integer typeInteger = Integer.valueOf(typeLong.intValue());
            localizationHash = (HashMap) nodeHash.get("localization");
            no = new Node(idInteger,(String) nodeHash.get("name"), (String) nodeHash.get("description"), typeInteger, (String) nodeHash.get("services"), new LatLng((Double)localizationHash.get("latitude"), (Double) localizationHash.get("longitude")),(String) nodeHash.get("email"),(String) nodeHash.get("website"), (String) nodeHash.get("phone"));
            addNode(no);
        }
    }
}