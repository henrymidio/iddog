package idwall.iddog.ui.dogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import idwall.iddog.R;

public class DogsRVAdapter extends RecyclerView.Adapter<DogsRVAdapter.ViewHolder> {

    private List<String> itemList;
    private static FragmentManager fm;
    private static Context context;

    public DogsRVAdapter(List<String> itemList, FragmentManager fm, final Context context) {
        this.itemList = itemList;
        this.fm = fm;
        this.context = context;
    }

    @Override
    public DogsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        ViewHolder vh = new ViewHolder(layoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    public void setItemList(List<String> list) {
        itemList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView dogPhoto;

        public ViewHolder(View v) {
            super(v);

            dogPhoto = v.findViewById(R.id.dog_photo);

        }

        public void bind(final String imagePath) {

            Glide.with(context)
                    .load(imagePath)
                    .into(dogPhoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("imagePath", imagePath);

                    DogFullFragment dogFragment = new DogFullFragment();
                    dogFragment.setArguments(bundle);
                    dogFragment.show(fm.beginTransaction(), "dog");
                }
            });
        }

    }

}
