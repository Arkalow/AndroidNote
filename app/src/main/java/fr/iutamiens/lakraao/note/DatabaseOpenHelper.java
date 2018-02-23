package fr.iutamiens.lakraao.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by omer on 23/02/18.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public DatabaseOpenHelper(Context context){
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        db.execSQL("INSERT INTO todo (name) VALUES ('Je ne sais pas1')");
        db.execSQL("INSERT INTO todo (name) VALUES ('Je ne sais pas2')");
        db.execSQL("INSERT INTO todo (name) VALUES ('Je ne sais pas3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
