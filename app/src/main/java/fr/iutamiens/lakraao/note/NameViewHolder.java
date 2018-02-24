package fr.iutamiens.lakraao.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Class qui représente un item
 * Created by omer on 22/02/18.
 */
public class NameViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private Note note;
    /***
     * Constructeur de NameViewHolder
     * @param itemView La vue à afficher
     */
    public NameViewHolder(View itemView, final Context context) {
        super(itemView);

        textView = itemView.findViewById(R.id.item);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Intent", note.toString());
                ((MainActivity)context).selectNote(note);
            }
        });
    }

    /***
     * Modifie la vue
     * @param note
     */
    public void bind(Note note) {
        this.note = note;
        textView.setText(note.getTitle());
        Log.d("NameViewHolder", textView.getText().toString());
    }

    public void recycle() {
        textView.setText("");
    }
}
