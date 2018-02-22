package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = this.findViewById(R.id.recyclerView);
        recyclerView.setOnClickListener(new View.OnClickListener(){
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
}
