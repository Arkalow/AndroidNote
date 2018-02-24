package fr.iutamiens.lakraao.note;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/***
 * Fait le lien des notes vers la base de donnée
 * Created by omer on 24/02/18.
 */
public class NoteManage {
    /***
     * Retourne la liste de toutes les notes de la database
     * @return
     */
    public static List<Note> selectAll(DatabaseOpenHelper databaseOpenHelper){
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase database;
        Cursor cursor;
        try{
            database = databaseOpenHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM notes", null);
        }catch(Exception e){
            Log.e("Database", "Error selectAll note");
            Log.e("Database", e.getMessage());
            return notes;
        }


        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        while(cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            String title = cursor.getString(titleIndex);
            String content = cursor.getString(contentIndex);
            Note note = new Note(id, title, content);
            notes.add(note);
            Log.d("Database", note.toString());
        }
        cursor.close();

        return notes;
    }

    public static Note select(int id, DatabaseOpenHelper databaseOpenHelper){
        SQLiteDatabase database;
        Cursor cursor;
        try{
            database = databaseOpenHelper.getReadableDatabase();
            String sql = "SELECT * FROM notes WHERE id = " + id;
            cursor = database.rawQuery(sql, null);
        }catch(Exception e){
            Log.e("Database", "Error select note");
            Log.e("Database", e.getMessage());
            return null;
        }


        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        cursor.moveToNext();
        Note note = new Note(
                cursor.getInt(idIndex),
                cursor.getString(titleIndex),
                cursor.getString(contentIndex)
        );
        Log.d("Database", note.toString());

        cursor.close();
        Log.d("Database", "select : " + note.toString());
        return note;
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
            Log.d("Database", "Ajout d'une note dans la base de donnée");
        }catch(Exception e){
            Log.e("Database", "Error add note");
            Log.e("Database", e.getMessage());
        }
        return note;
    }
}
