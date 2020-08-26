package com.example.alfonsogonzaleztfg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {
    public static final String digidb="digimon";
    public static final String Table_digimon="digimon_table";
    public static final int Database_version=1;
    public static final String Create_Digimon="CREATE TABLE IF NOT EXISTS "+ Table_digimon + " " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "digimonName TEXT NOT NULL UNIQUE, " +
            "digimonType TEXT NOT NULL, " +
            "digimonLevel TEXT NOT NULL, " +
            "digimonSignatureMove TEXT NOT NULL, " +
            "digimonDescription TEXT NOT NULL, " +
            "digimonImage INTEGER NOT NULL)";
    public static final String Delete_Digimon="DROP TABLE IF EXISTS "+ Table_digimon;

    public DataHelper(Context context) {
        super(context, digidb, null, Database_version);

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_Digimon);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(Delete_Digimon);
        onCreate(db);
    }

    public void insertDigimon(String digimonName, Type digimonType, Level digimonLevel,
                              String digimonSignatureMove, String digimonDescription,
                              int digimonImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        ContentValues values;
        try {
            values = new ContentValues();
            values.put("digimonName", digimonName);
            values.put("digimonType", digimonType.name());
            values.put("digimonLevel", digimonLevel.name());
            values.put("digimonSignatureMove", digimonSignatureMove);
            values.put("digimonDescription", digimonDescription);
            values.put("digimonImage", digimonImage);
            db.insert(Table_digimon, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public ArrayList<String> getAllDigimon(){
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT digimonName FROM " + Table_digimon + " ORDER BY digimonName";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String dname = cursor.getString(cursor.getColumnIndex("digimonName"));
                    list.add(dname);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public String getDigimonInfo(String digimonName, Column column){
        String digiInfo = "";
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT "+column.name()+" FROM " + Table_digimon + " WHERE digimonName LIKE '"+ digimonName + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String queryReturn = cursor.getString(cursor.getColumnIndex(column.name()));
                    digiInfo = queryReturn;
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
        e.printStackTrace();
    } finally {
        db.endTransaction();
        db.close();
    }
        return digiInfo;
    }

    public int getDigimonImage(String digimonName){
        int imgId = 0;
        SQLiteDatabase db=this.getReadableDatabase();
        db.beginTransaction();
        try{
            String selectQuery = "SELECT digimonImage FROM " + Table_digimon + " WHERE digimonName LIKE '"+ digimonName + "'";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int queryReturn = cursor.getInt(cursor.getColumnIndex("digimonImage"));
                    imgId = queryReturn;
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return imgId;
    }


}
