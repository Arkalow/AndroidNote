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
    private DatabaseOpenHelper openHelper; //database
    private NameAdapter nameAdapter;

    /***
     * Procédure appelé à la création de la fenêtre
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***
         * nameAdapter : contient la liste des notes
         */
        RecyclerView recyclerView; //List of item
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NameAdapter(this));
        nameAdapter = ((NameAdapter) recyclerView.getAdapter());

        openHelper = DatabaseOpenHelper.getSelf(this);// Base de donnée
        
    }

    /***
     * Ajout d'une note à la liste
     * @param note note à ajouter
     */
    private void addNote(Note note) {
        nameAdapter.add(note);//Ajout à la liste
        NoteManage.add(note, openHelper);//Ajout à la BD
    }

    /***
     * Affiche une note dans une autre activity
     * @param note note à afficher
     */
    public void selectNote(Note note){
        Intent intent = new Intent(this, NoteActivity.class);
        try{
            intent.putExtra("NoteId", note.getId());
            startActivity(intent);
        }catch (Exception e){
            Log.e("Intent", "La note n'existe pas");
            nameAdapter.notifyDataSetChanged();
        }
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

            /***
             * AlertDialog
             */
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View dialogView = layoutInflater.inflate(R.layout.dialog_view, null);
            builder.setView(dialogView);
            builder.setTitle("Création d'une nouvelle Note");

            /**
             * Bouton Valider
             */
            builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                private EditText inputTitle;//Input de la fenêtre de dialog

                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Log.d("Dialog", "Valider");

                    /***
                     * AlertDialog
                     */
                    AlertDialog alertDialog = (AlertDialog) dialogInterface;
                    inputTitle = alertDialog.findViewById(R.id.dialog_inputTitle);

                    /***
                     * Ajout de la note
                     */
                    addNote(new Note(
                            inputTitle.getText().toString(),
                            ""
                            //"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat nonproident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat nonproident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    ));
                    nameAdapter.update();
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

    /**
     * Restauration de l'activity
     */
    @Override
    public void onResume (){
        super.onResume();

        Log.d("Status", "Main : onResume");
        nameAdapter.update();
    }
}
