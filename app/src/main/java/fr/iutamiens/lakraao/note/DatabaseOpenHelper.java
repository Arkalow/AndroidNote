package fr.iutamiens.lakraao.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/***
 * Created by omer on 23/02/18.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private DatabaseOpenHelper(Context context){
        super(context, "database", null, 1);
        self = this;
    }
    private static DatabaseOpenHelper self = null;//Instance unique de la classe DatabaseOpenHelper

    /***
     * Retourne l'instance unique de la classe DatabaseOpenHelper
     * @param context
     * @return
     */
    public static DatabaseOpenHelper getSelf(Context context){
        if(self == null){
            self = new DatabaseOpenHelper(context);
        }
        return self;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)");
        //db.execSQL("INSERT INTO notes (name) VALUES ('Je ne sais pas1')");
        //db.execSQL("INSERT INTO notes (name) VALUES ('Je ne sais pas2')");
        //db.execSQL("INSERT INTO notes (name) VALUES ('Je ne sais pas3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
