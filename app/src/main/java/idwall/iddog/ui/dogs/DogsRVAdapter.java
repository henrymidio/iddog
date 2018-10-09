package idwall.iddog.ui.dogs;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import idwall.iddog.R;

public class DogsRVAdapter extends RecyclerView.Adapter<DogsRVAdapter.ViewHolder> {

    private List<String> itemList;
    private OnItemClickListener listener;

    public DogsRVAdapter(List<String> itemList) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public DogsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        ViewHolder vh = new ViewHolder(layoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(itemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productPhoto;

        public ViewHolder(View v) {
            super(v);

            productPhoto = v.findViewById(R.id.dog_photo);
            Log.e("tag", productPhoto.getTag().toString());
        }

        public void bind(final String item, final OnItemClickListener listener) {
            Picasso.get().load(item).into(productPhoto);
            Log.e("img", item);
        }

    }

}
