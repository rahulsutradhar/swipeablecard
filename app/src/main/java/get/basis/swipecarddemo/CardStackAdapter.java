package get.basis.swipecarddemo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ItemViewHolder> {

    private ArrayList<CardModel> cardModels;

    /**
     * Constructor
     *
     * @param cardModels
     */
    public CardStackAdapter( ArrayList<CardModel> cardModels ) {
        this.cardModels = cardModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int i ) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull ItemViewHolder itemViewHolder, int position ) {
        ItemViewHolder itemViewHolder1 = (ItemViewHolder) itemViewHolder;
        itemViewHolder1.setViews(cardModels.get(position));
    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    /**
     * Item View Holder Class
     */
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView contentText;

        public ItemViewHolder( @NonNull View itemView ) {
            super(itemView);
            contentText = (TextView) itemView.findViewById(R.id.content_text);
        }

        public void setViews( CardModel cardModel ) {

            if ( cardModel == null ) {
                return;
            }
            contentText.setText(cardModel.getContent() == null ? "" : cardModel.getContent());
        }
    }
}
