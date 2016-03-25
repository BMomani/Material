package android.alcode.com.material.fragments;

import android.alcode.com.material.R;
import android.alcode.com.material.adapters.PostDetailsAdapter;
import android.alcode.com.material.databases.Database;
import android.alcode.com.material.models.PostDetails;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by MOMANI on 2016/03/23.
 */
public class PostDetailActivityFragment extends Fragment {

    private String id;
    private PostDetails postDetails;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private ImageView mBackdrop;

    private FloatingActionButton fab;

    public PostDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post_detail, container, false);

        id = getActivity().getIntent().getStringExtra("id");
        postDetails = Database.getInstance().getMovieDetailsFromID(id);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_movie_details);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) v.findViewById(R.id.collapsing_toolbar_layout_movie_details);
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar_movie_details);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mBackdrop = (ImageView) v.findViewById(R.id.backdrop);

        if (null != postDetails)
            Picasso.with(getContext()).load(postDetails.getImageUrl())
                    .into(mBackdrop, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap posterBitmap = ((BitmapDrawable) mBackdrop.getDrawable()).getBitmap();
                            Palette.from(posterBitmap).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    //container.setBackgroundColor(ColorUtils.setAlphaComponent(palette.getMutedColor(mDefaultColor), 190)); //Opacity
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                        int colorDark = ContextCompat.getColor(getContext(), (R.color.colorPrimaryDark));
                                        getActivity().getWindow().setStatusBarColor(colorDark);
                                        fab.setBackgroundTintList(ColorStateList.valueOf(colorDark));
                                    }
                                    mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getContext(), (R.color.colorPrimaryTransparent)));
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });


        fab = (FloatingActionButton) v.findViewById(R.id.fab);


        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), R.color.transparent));
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.transparent));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (null != postDetails) {
            mAdapter = new PostDetailsAdapter(postDetails, getActivity());
            mRecyclerView.setAdapter(mAdapter);
        }
//        // mBackdrop.setResponseObserver(new PaletteNetworkImageView
//        int colorLight = mBackdrop.getVibrantColor();
//        mCollapsingToolbarLayout.setContentScrimColor(colorLight);


        mToolbar.setNavigationIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_back));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mToolbar.inflateMenu(R.menu.menu_post_detail);


        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_share) {
                    String[] data = {PostDetailActivityFragment.this.postDetails.getTitle()};
                    startActivity(Intent.createChooser(shareIntent(data[0]), "Share Via"));
                    return true;
                }
                return true;
            }
        });

        return v;

    }


    public Intent shareIntent(String data) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.post_extra_subject));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, data);
        return sharingIntent;
    }
}