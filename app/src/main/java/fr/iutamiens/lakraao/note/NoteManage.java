package fr.iutamiens.lakraao.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by omer on 24/02/18.
 */

public class NoteManage {
    /***
     * Retourne la liste de toutes les notes de la database
     * @return
     */
    public static List<Note> selectAll(){
        return null;
    }

    /***
     * Ajoute une note dans la database
     * @param note note à ajouter
     * @return return note
     */
    public static Note add(Note note, DatabaseOpenHelper databaseOpenHelper){
        try{
            SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", note.getTitle());
            values.put("content", note.getContent());
            database.insert("notes", null, values);
            database.close();
            Log.d("Database", "Ajout d'une note dans la base de donnée");
        }catch(Exception e){
            Log.e("Database", "Error add note");
            Log.e("Database", e.getMessage());
        }
        return note;
    }
}

/*
        openHelper = DatabaseOpenHelper.getSelf(this);
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM todo", null);

        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("name");
        while(cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            int name = cursor.getInt(nameIndex);
            Log.d("Query", "id = " + id + "| name = " + name);
        }
        cursor.close();
* */
