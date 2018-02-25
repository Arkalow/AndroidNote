package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    EditText content;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        /***
         * Récupération de la note
         */
        Bundle b = getIntent().getExtras();
        int noteId = b.getInt("NoteId");
        try{
            note = NoteManage.select(noteId, DatabaseOpenHelper.getSelf(this));
        }catch (Exception e){
            Log.e("Intent", "La note n'existe pas !");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        /***
         * Affichage
         */
        TextView title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        title.setText(note.getTitle());
        content.setText(note.getContent());
        setTitle(note.getTitle());
    }
    /***
     * Création du menu
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (item.getItemId()){
            case R.id.save:
                Log.d("Menu", "save");
                save();
                finish();
                break;
            case R.id.share:
                Log.d("Menu", "share");
                share();
                break;
            case R.id.delete:
                Log.d("Menu", "delete");

                /***
                 * AlertDialog
                 */
                builder.setTitle("Voulez-vous vraiment supprimer la note ?");
                /**
                 * Bouton Valider
                 */
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d("Dialog", "Valider");
                        delete();
                    }
                });

                /**
                 * Bouton Annuler
                 */
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.d("Dialog", "Annuler");
                    }
                });
                builder.create().show();
                break;
            case R.id.close:
                Log.d("Menu", "close");
                if (content.getText().toString() != note.getContent()){
                    /***
                     * AlertDialog
                     */
                    builder.setTitle("Voulez-vous enregistrer les modifications ?");
                    /**
                     * Bouton Valider
                     */
                    builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Log.d("Dialog", "Valider");
                            save();
                            finish();
                        }
                    });

                    /**
                     * Bouton Annuler
                     */
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Log.d("Dialog", "Annuler");
                        }
                    });
                    builder.create().show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /***
     * Supprimer la note
     */
    private void delete(){
        NoteManage.delete(note, DatabaseOpenHelper.getSelf(this));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /***
     * Partage la note
     */
    private void share(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, note.export().toString());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share your note"));
    }

    /***
     * Sauvegarde la note
     */
    private void save(){
        Log.d("SaveNote", "Save");
    }
}
