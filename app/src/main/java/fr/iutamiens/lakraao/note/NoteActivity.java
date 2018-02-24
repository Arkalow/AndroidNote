package fr.iutamiens.lakraao.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
