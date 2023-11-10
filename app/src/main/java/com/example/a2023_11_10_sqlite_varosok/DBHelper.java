package com.example.a2023_11_10_sqlite_varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "varos.db";
    private static final String TABLE_NAME = "Varosok";
    private static final String COL_ID = "ID";
    private static final String COL_NEV = "Varosnev";
    private static final String COL_ORSZAG = "Orszag";
    private static final String COL_LAKOSSAG = "Lakossag";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NEV + " TEXT NOT NULL," +
                COL_ORSZAG + " TEXT NOT NULL," +
                COL_LAKOSSAG + " INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public Cursor adatLekerdezes() {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NAME, new String[]{COL_ID, COL_NEV,
                        COL_ORSZAG, COL_LAKOSSAG}, null, null,
                null, null, null);
    }
    public boolean adatRogzites(String varosnev, String orszag, int lakossag) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, varosnev);
        values.put(COL_ORSZAG, orszag);
        values.put(COL_LAKOSSAG, lakossag);
        long result = database.insert(TABLE_NAME, null, values);
        return result != -1;
    }
}
