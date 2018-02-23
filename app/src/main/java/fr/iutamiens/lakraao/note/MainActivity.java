package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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


        for (int i = 0; i < 3; i++){
            addItem("Item N°"+i);
        }
        //addItem("First Item");
        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //LayoutInflater layoutInflater = getLayoutInflater();
                //View dialogView = layoutInflater.inflate(R.layout.dialog_test, null);
                builder.setTitle("test");

                /**
                 * Bouton Valider
                 */
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        AlertDialog alertDialog = (AlertDialog) dialogInterface;
                        addItem("test");
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
        });
    }

    /***
     * Ajout un item au system
     * @param text text de l'item à ajouter
     */
    private void addItem(String text) {
        ((NameAdapter) recyclerView.getAdapter()).add(text);
    }
}
