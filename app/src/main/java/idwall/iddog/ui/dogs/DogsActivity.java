package idwall.iddog.ui.dogs;

import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import idwall.iddog.R;

public class DogsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private DogsRVAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        // Recupera breed da activity anterior para fazer o request
        String breed = getIntent().getStringExtra("breed").toLowerCase();

        //Inicializa View Model
        DogsViewModel dogsViewModel = new DogsViewModel();
        dogsViewModel.init(this);

        buildRecyclerView();

        dogsViewModel.feed(breed).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dogsList) {
                if(dogsList != null) {
                    loadDogs(dogsList);
                } else {
                    Toast.makeText(DogsActivity.this, "Erro de carregamento", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void buildRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
    }

    private void loadDogs(List<String> dogsList) {
        if(rcAdapter != null) {
            //mProgressBar.setVisibility(View.GONE);
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        rcAdapter = new DogsRVAdapter(dogsList, fm);
        int spanCount = 2; // 3 columns
        int spacing = 6; // 50px
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setAdapter(rcAdapter);

        //mProgressBar.setVisibility(View.GONE);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = 0; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
