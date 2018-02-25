package fr.iutamiens.lakraao.note;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Fait le lien des notes vers la base de donnée
 * Created by omer on 24/02/18.
 */
public class NoteManage {

    /***
     * Récupère la liste de toutes les notes de la database
     * @param databaseOpenHelper base de donnée
     * @return liste de note
     */
    public static List<Note> selectAll(DatabaseOpenHelper databaseOpenHelper){
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase database;
        Cursor cursor;
        try{
            database = databaseOpenHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM notes ORDER BY modified DESC", null);
        }catch(Exception e){
            Log.e("Database", "Error selectAll note");
            Log.e("Database", e.getMessage());
            return notes;
        }

        /***
         * Lecture des notes récupérée
         */
        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        while(cursor.moveToNext()){
            int id = cursor.getInt(idIndex);
            String title = cursor.getString(titleIndex);
            String content = cursor.getString(contentIndex);
            Note note = new Note(id, title, content);
            notes.add(note);
            Log.d("Database", "Select(All) : " + note.toString());
        }
        cursor.close();

        return notes;
    }

    /***
     * Récupère une seule note de la base de donnée
     * @param id id de la note à recupérer
     * @param databaseOpenHelper base de donnée
     * @return retourne la note récupérer dans la base de donnée portant l'id passé en paramètre
     */
    public static Note selectById(int id, DatabaseOpenHelper databaseOpenHelper){
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

        /***
         * Lecture des données récupérée
         */
        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        cursor.moveToNext();
        Note note = new Note(
                cursor.getInt(idIndex),
                cursor.getString(titleIndex),
                cursor.getString(contentIndex)
        );

        cursor.close();
        Log.d("Database", "select : " + note.toString());
        return note;
    }

    /***
     * Récupère une seule note de la base de donnée
     * @param modified temps du champs modified de la note
     * @param databaseOpenHelper base de donnée
     * @return retourne la note récupérer dans la base de donnée portant l'id passé en paramètre
     */
    public static Note selectByModified(String modified, DatabaseOpenHelper databaseOpenHelper){
        SQLiteDatabase database;
        Cursor cursor;
        try{
            database = databaseOpenHelper.getReadableDatabase();
            String sql = "SELECT * FROM notes WHERE modified = " + modified;
            cursor = database.rawQuery(sql, null);
        }catch(Exception e){
            Log.e("Database", "Error select note");
            Log.e("Database", e.getMessage());
            return null;
        }

        /***
         * Lecture des données récupérée
         */
        int idIndex = cursor.getColumnIndex("id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        cursor.moveToNext();
        Note note = new Note(
                cursor.getInt(idIndex),
                cursor.getString(titleIndex),
                cursor.getString(contentIndex)
        );

        cursor.close();
        Log.d("Database", "select : " + note.toString());
        return note;
    }

    /***
     * Ajoute une note dans la database
     * @param note note à ajouter
     * @param databaseOpenHelper base de donnée
     * @return return note
     */
    public static String add(Note note, DatabaseOpenHelper databaseOpenHelper){
        String now = now();
        try{
            SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", note.getTitle());
            values.put("content", note.getContent());
            values.put("modified", now);
            database.insert("notes", null, values);
            Log.d("Database", "Ajout d'une note dans la base de donnée");
        }catch(Exception e){
            Log.e("Database", "Error add note");
            Log.e("Database", e.getMessage());
        }
        return now;
    }

    /***
     * Supprime une note dans la database
     * @param note note à spprimer
     * @param databaseOpenHelper base de donnée
     */
    public static void delete(Note note, DatabaseOpenHelper databaseOpenHelper){
        try{
            SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
            database.delete("notes", "id = ?", new String[]{note.getId()+""});
            Log.d("Database", "Delete : " + note.toString());
        }catch(Exception e){
            Log.e("Database", "Error delete note");
            Log.e("Database", e.getMessage());
        }
    }

    public static Note update(Note note, DatabaseOpenHelper databaseOpenHelper){
        try{
            SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", note.getId());
            contentValues.put("title", note.getTitle());
            contentValues.put("content",note.getContent());
            contentValues.put("modified", now());
            database.update("notes", contentValues,"id = ?", new String[]{ note.getId()+"" });
            Log.d("Database", "Update : " + note.toString());
        }catch(Exception e){
            Log.e("Database", "Error update note");
            Log.e("Database", e.getMessage());
        }
        return note;
    }

    /***
     * Retourne la date en seconde
     * @return Timesamp
     */
    private static String now(){
        return new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
    }
}
