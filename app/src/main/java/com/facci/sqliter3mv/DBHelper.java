package com.facci.sqliter3mv;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Madeleine on 25/8/2016.
 */
  public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_Nombre = "CNE_MMVL.db";
    public static final String tabla_votantes_MMVL = "VOTANTES_MMVL";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO  DE NACIMIENTO";
    public DBHelper(Context context){
        super(context, DB_Nombre, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT,%s TEXT,%s INTEGER)",tabla_votantes_MMVL,COL_2,COL_3,COL_4,COL_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s",tabla_votantes_MMVL));
        onCreate(db);

    }
// inserta
    public boolean Insertar(String nombre, String apellido,String recinto,int año){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,año);
        long resultado = db.insert(tabla_votantes_MMVL,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }
        //ve todos los registros
    public Cursor VerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s", tabla_votantes_MMVL), null);
        return  res;
    }

    //Modificar
    public boolean Modificar(String id,String nombre, String apellido,String recinto,int año){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recinto);
        contentValues.put(COL_5,año);
        db.update(tabla_votantes_MMVL, contentValues, "id = ?", new String[]{id});
        return true;
    }

    //Eliminar
    public Integer Eliminar(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tabla_votantes_MMVL,"id = ?",new String[]{id});

    }

}
