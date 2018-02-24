package fr.iutamiens.lakraao.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        /***
         * Récupération de la note
         */
        Bundle b = getIntent().getExtras();
        if (null != b) {
            int Noteid = b.getInt("NoteId");
        }
    }
}
