package fr.iutamiens.lakraao.note;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente les items
 * Created by omer on 22/02/18.
 */
public class NameAdapter extends RecyclerView.Adapter<NameViewHolder> {
    private List<String> items = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    /***
     * Ajoute un textView
     * @param item
     */
    public void add(String item) {
        Log.d("NameAdapter", "Add element");
        items.add(item);
        notifyItemInserted(getItemCount());
    }

    /***
     * Constructeur de NameAdapter
     * @param context
     */
    public NameAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
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
        return new NameViewHolder(view);
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
        holder.bind(items.get(position));
    }

    /***
     * Compte le nombre de cellule
     * @return Compte le nombre de cellule
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewRecycled(NameViewHolder holder) {
        Log.d("NameAdapter", "Recycle view holder");
        holder.recycle();
    }
}
