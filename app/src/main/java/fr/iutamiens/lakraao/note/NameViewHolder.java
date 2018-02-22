package fr.iutamiens.lakraao.note;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by omer on 22/02/18.
 */

public class NameViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    /***
     * Constructeur de NameViewHolder
     * @param itemView La vue à afficher
     */
    public NameViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.item);
        textView.setText("Test");
    }

    public void bind(TextView textView) {
        this.textView = textView;
    }
}
