package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private RecyclerView recyclerView; //Liste d'item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NameAdapter(this));

    }

    /***
     * Ajout un item au system
     * @param text text de l'item à ajouter
     */
    private void addItem(String text) {
        ((NameAdapter) recyclerView.getAdapter()).add(text);
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
     * Selection du menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            LayoutInflater layoutInflater = getLayoutInflater();
            View dialogView = layoutInflater.inflate(R.layout.dialog_view, null);
            builder.setView(dialogView);
            builder.setTitle("Créer une nouvelle Note");

            /**
             * Bouton Valider
             */
            builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){
                private EditText input;//Input de la fenêtre de dialog

                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    AlertDialog alertDialog = (AlertDialog) dialogInterface;
                    input = alertDialog.findViewById(R.id.dialog_input);
                    addItem(input.getText().toString());
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
