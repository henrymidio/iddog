package idwall.iddog.ui.dogs;

import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

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

        setRecyclerView();
        loadDogs();
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
    }

    public void loadDogs() {
        //mProgressBar.setVisibility(View.VISIBLE);

        List<String> dogs = new ArrayList<String>();
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1.jpg");
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1000.jpg");
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1.jpg");
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1.jpg");
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1.jpg");
        dogs.add("https://dog.ceo/api/img/hound-english/n02089973_1.jpg");

        renderProducts(dogs);
    }

    private void renderProducts(List<String> productsList) {
        if(rcAdapter != null) {
            //rcAdapter.filter(productsList);
            //mProgressBar.setVisibility(View.GONE);
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        rcAdapter = new DogsRVAdapter(productsList, fm);
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
