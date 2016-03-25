package android.alcode.com.material.fragments;

import android.alcode.com.material.R;
import android.alcode.com.material.adapters.PostAdapter;
import android.alcode.com.material.databases.Database;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MOMANI on 2016/03/22.
 */
public class PostListFragment extends Fragment {

    RecyclerView mRecyclerView;
    private int gridColumns;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gridColumns = getResources().getInteger(R.integer.grid_columns);
        mRecyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_post_list, container, false);
        setupRecyclerView(mRecyclerView);
        return mRecyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), gridColumns);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position + 1) % 3 == 0)
                    return gridColumns;
                else
                    return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        PostAdapter adapter = new PostAdapter(Database.getInstance().getAllPosts(), getActivity());
        recyclerView.setAdapter(adapter);
    }

}
