package android.alcode.com.material.fragments;

import android.alcode.com.material.R;
import android.alcode.com.material.adapters.PostRecyclerViewAdapter;
import android.alcode.com.material.databases.Database;
import android.alcode.com.material.models.Post;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMANI on 2016/03/22.
 */
public class PostListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_post_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        PostRecyclerViewAdapter postRecyclerViewAdapter = new PostRecyclerViewAdapter(Database.getInstance().getAllPosts(),getActivity());
        recyclerView.setAdapter(postRecyclerViewAdapter);
    }

}
