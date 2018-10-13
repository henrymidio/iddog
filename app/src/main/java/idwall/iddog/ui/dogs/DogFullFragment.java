package idwall.iddog.ui.dogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import idwall.iddog.R;

public class DogFullFragment extends DialogFragment {


    public DogFullFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dog, container, false);

        String imagePath = getArguments().getString("imagePath");
        ImageView dogPhoto = view.findViewById(R.id.fullImg);

        Glide.with(getContext())
                .load(imagePath)
                .into(dogPhoto);

        return view;
    }

}
