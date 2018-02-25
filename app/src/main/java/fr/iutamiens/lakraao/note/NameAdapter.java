package fr.iutamiens.lakraao.note;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Classe qui représente les items
 * Created by omer on 22/02/18.
 */
public class NameAdapter extends RecyclerView.Adapter<NameViewHolder> {
    private List<Note> notes;
    private Context context;
    private final LayoutInflater layoutInflater;

    /***
     * Ajoute un textView
     * @param note
     */
    public void add(Note note) {
        Log.d("NameAdapter", "Add element");
        notes.add(note);
        notifyItemInserted(getItemCount());
    }


    /***
     * Met à jour la liste des notes
     */
    public void update(){
        notes = NoteManage.selectAll(DatabaseOpenHelper.getSelf(context));
        notifyDataSetChanged();
    }

    /***
     * Constructeur de NameAdapter
     * @param context
     */
    public NameAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        notes = NoteManage.selectAll(DatabaseOpenHelper.getSelf(context));
    }

    /***
     * Créer la vue d'un cellule
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("NameAdapter", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.listitem_name, parent, false);
        return new NameViewHolder(view, context);
    }

    /***
     * Applique un donnée à une vue
     * @param holder le view holder qui va recevoir a donnée
     * @param position la position de la cellule
     */
    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        Log.d("NameAdapter", "onBindViewHolder");

        //On l'envoi au viewholder pour qu'il l'affiche
        holder.bind(notes.get(position));
    }

    /***
     * Compte le nombre de cellule
     * @return Compte le nombre de cellule
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public void onViewRecycled(NameViewHolder holder) {
        Log.d("NameAdapter", "Recycle view holder");
        holder.recycle();
    }
}
