package com.example.devinette;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class BD extends SQLiteOpenHelper {

    private static final String DB_NAME = "Guessgamedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "joueurs";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String SCORE_COL = "score";
    private Context context;

    public BD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT," + SCORE_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewGamer(String nameJ,String scoreJ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, nameJ);
        values.put(SCORE_COL, scoreJ);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, scoreJ, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, nameJ, Toast.LENGTH_SHORT).show();

        }

    }
Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
           cursor =  db.rawQuery(query,null);
        }
        return  cursor;
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
