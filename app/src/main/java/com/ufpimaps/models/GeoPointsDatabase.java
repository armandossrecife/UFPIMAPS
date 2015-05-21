package com.ufpimaps.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HugoPiauilino on 21/05/15.
 */
public class GeoPointsDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "geoPointsUFPI";

    private static final String TABLE_NODES = "nodes";

    private static final String KEY_ID = "id";

    private static final String KEY_DESCRIPTION = "description";

    public GeoPointsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GEOPOINTS_TABLE = "CREATE TABLE " + TABLE_NODES
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_GEOPOINTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NODES);
        onCreate(db);
    }

    public void addNode(Node no) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, no.getIdNode());
        values.put(KEY_DESCRIPTION, no.getDescription());

        db.insert(TABLE_NODES, null, values);
        db.close();
    }
}
