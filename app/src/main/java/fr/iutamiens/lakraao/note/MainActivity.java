package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/***
 * Fenêtre où toutes les notes sont affichées
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; //List of item
    private DatabaseOpenHelper openHelper; //database

    /***
     * Procédure appelé à la création de la fenêtre
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NameAdapter(this));

        openHelper = DatabaseOpenHelper.getSelf(this);
    }

    /***
     * Ajout d'une note à la liste
     * @param note note à ajouter
     */
    private void addNote(Note note) {
        ((NameAdapter) recyclerView.getAdapter()).add(note);//Ajout à la liste
        NoteManage.add(note, openHelper);//Ajout à la BD
    }

    /***
     * Met à jour la liste des notes
     */
    private void updateNote(){
        ((NameAdapter) recyclerView.getAdapter()).update();
    }

    /***
     * Affiche une note dans une autre activity
     * @param note note à afficher
     */
    public void selectNote(Note note){
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("NoteId", note.getId());
        startActivity(intent);
    }

    /***
     * Créer le menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    /***
     * Selection d'un bouton du menu
     * @param item Selected item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            LayoutInflater layoutInflater = getLayoutInflater();
            View dialogView = layoutInflater.inflate(R.layout.dialog_view, null);
            builder.setView(dialogView);
            builder.setTitle("Création d'une nouvelle Note");

            /**
             * Bouton Valider
             */
            builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                private EditText inputTitle;//Input de la fenêtre de dialog
                private EditText inputContent;

                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    AlertDialog alertDialog = (AlertDialog) dialogInterface;
                    inputTitle = alertDialog.findViewById(R.id.dialog_inputTitle);
                    inputContent = alertDialog.findViewById(R.id.dialog_inputContent);
                    Note note = new Note(inputTitle.getText().toString(), inputContent.getText().toString());
                    addNote(note);
                    updateNote();
                    Log.d("Dialog", "Valider");
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
        return super.onOptionsItemSelected(item);
    }
}
