package fr.iutamiens.lakraao.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater layoutInflater = getLayoutInflater();
                //View dialogView = layoutInflater.inflate(R.layout.dialog_test, null);
                builder.setTitle("test");

                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        AlertDialog alertDialog = (AlertDialog) dialogInterface;
                        TextView textView = MainActivity.this.findViewById(R.id.textView);
                        Log.d("Dialog", "Valider");
                    }
                });

                builder.setPositiveButton("Annuler", new DialogInterface.OnClickListener(){

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
