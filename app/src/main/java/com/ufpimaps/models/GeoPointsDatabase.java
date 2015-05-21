package com.ufpimaps.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_DESCRIPTION;
import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_ID;
import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_LATITUDE;
import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_LONGITUDE;
import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry.TABLE_NODE;
import static com.ufpimaps.models.GeoPointsContract.GeoPointsEntry._ID;

/**
 * Created by HugoPiauilino on 21/05/15.
 * Classe que armazena os principais geopoints da ufpi para agilizar a busca no aplicativo
 */
public class GeoPointsDatabase extends SQLiteOpenHelper {

    /**
     * Se o Schema do Banco de Dados for modificado, incrementar DATABASE_VERSION
     */

    private static final int DATABASE_VERSION = 1;

    /**
     * Nome do banco de dados que armazena os GeoPoints
     */

    private static final String DATABASE_NAME = "GeoPointsDatabase.db";

    /**
     *
     */

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /**
     *
     */

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NODE + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NODE_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NODE_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_LONGITUDE + DOUBLE_TYPE +
                    " )";

    /**
     *
     */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NODE;


    /**
     * @param context
     */
    public GeoPointsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
    }


    /**
     *
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
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * @param no
     */
    public void addNode(Node no) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NODE_ID, no.getIdNode());
        values.put(COLUMN_NODE_DESCRIPTION, no.getDescription());
        values.put(COLUMN_NODE_LATITUDE, no.getLocalization().getLatitude());
        values.put(COLUMN_NODE_LONGITUDE, no.getLocalization().getLongitude());

        db.insert(TABLE_NODE, null, values);
        db.close();
    }

    public List<Node> getAllNodes() {
        return null;
    }
}

