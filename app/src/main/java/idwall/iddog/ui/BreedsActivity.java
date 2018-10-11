package idwall.iddog.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import idwall.iddog.R;
import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.data.model.Breed;
import idwall.iddog.ui.dogs.DogsActivity;
import idwall.iddog.ui.signin.SignInActivity;

public class BreedsActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        if(!isUserLogged()) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }

        linearLayout = findViewById(R.id.ll_breeds);

        List<Breed> breeds = createBreeds();

        createBreedCards(breeds);

    }

    private List<Breed> createBreeds() {
        List<Breed> breeds = new ArrayList<>();

        breeds.add(new Breed("Pug", R.drawable.pug));
        breeds.add(new Breed("Labrador", R.drawable.labrador));
        breeds.add(new Breed("Husky", R.drawable.husky));
        breeds.add(new Breed("Hound", R.drawable.hound));

        return breeds;
    }

    private void createBreedCards(List<Breed> breeds) {
        for(final Breed breed: breeds){
            CardView card = (CardView) getLayoutInflater().inflate(R.layout.card_breed, linearLayout, false);

            ImageView iv = (ImageView) card.getChildAt(0);
            iv.setImageResource(breed.getImageRes());

            TextView tv = (TextView) card.getChildAt(1);
            tv.setText(breed.getName());

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BreedsActivity.this, DogsActivity.class);
                    intent.putExtra("breed", breed.getName());
                    startActivity(intent);
                }
            });

            linearLayout.addView(card);
        }
    }

    private boolean isUserLogged() {
        if(PreferencesHelper.getUserToken(this) != null) {
            return true;
        }

        return false;
    }
}
