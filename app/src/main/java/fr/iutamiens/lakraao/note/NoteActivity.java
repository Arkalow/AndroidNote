package fr.iutamiens.lakraao.note;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        /***
         * Récupération de la note
         */
        Bundle b = getIntent().getExtras();
        int noteId = b.getInt("NoteId");
        Note note = NoteManage.select(noteId, DatabaseOpenHelper.getSelf(this));
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }
    /***
     * Créer le menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }
    /***
     * Selection d'un bouton du menu
     * @param item Selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){

        }
        switch (item.getItemId()){
            case R.id.share:
                Log.d("Menu", "share");
                break;
            case R.id.edit:
                Log.d("Menu", "edit");
                break;
            case R.id.delete:
                Log.d("Menu", "delete");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
