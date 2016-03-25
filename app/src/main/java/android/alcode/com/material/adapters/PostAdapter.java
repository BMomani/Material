package android.alcode.com.material.adapters;

import android.alcode.com.material.R;
import android.alcode.com.material.models.Post;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by MOMANI on 2016/03/22.
 */
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LAYOUT_LARGE = 1;
    private static final int LAYOUT_SMALL = 2;
    private final List<Post> mPostList;
    private final Activity mAct;
    private final OnAdapterItemSelectedListener mAdapterCallback;
    private int mDefaultColor;
    private LayoutInflater mInflater;

    public PostAdapter(List<Post> posts, Activity activity) {
        mPostList = posts;
        this.mAct = activity;

        mInflater = (LayoutInflater) this.mAct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mAdapterCallback = (OnAdapterItemSelectedListener) mAct;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mDefaultColor = ContextCompat.getColor(parent.getContext(), (R.color.colorPrimaryTransparent));
        RecyclerView.ViewHolder vh;
        if (viewType == LAYOUT_SMALL) {
            View v = mInflater.inflate(R.layout.layout_holder_movie_small, parent, false);
            vh = new ViewHolderSmall(v);
        } else {
            View v = mInflater.inflate(R.layout.layout_holder_movie_large, parent, false);
            vh = new ViewHolderLarge(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Post post = mPostList.get(position);
        switch (getItemViewType(position)) {
            case LAYOUT_SMALL:
                ((ViewHolderSmall) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapterCallback != null) {
                            mAdapterCallback.onItemSelected(mPostList.get(position).getId());
                        }
                    }
                });

                ((ViewHolderSmall) holder).titleView.setText(post.getTitle());
                Picasso.with(((ViewHolderSmall) holder).imageView.getContext()).load(post.getImageUrl())
                        .into(((ViewHolderSmall) holder).imageView);
                break;
            case LAYOUT_LARGE:
                Picasso.with(((ViewHolderLarge) holder).imageView.getContext()).load(post.getImageUrl());
                ((ViewHolderLarge) holder).titleView.setText(post.getTitle());
                ((ViewHolderLarge) holder).overviewView.setText(post.getOverview());

                ((ViewHolderLarge) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapterCallback != null) {
                            mAdapterCallback.onItemSelected(post.getId());
                        }
                    }
                });

                ((ViewHolderLarge) holder).readMoreView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapterCallback != null) {
                            mAdapterCallback.onItemSelected(post.getId());
                        }
                    }
                });


        }
    }

    @Override
    public int getItemCount() {
        return null == mPostList ? 0 : mPostList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ((position + 1) % 3 == 0 ? LAYOUT_LARGE : LAYOUT_SMALL);
    }


    public interface OnAdapterItemSelectedListener {
        void onItemSelected(String id);
    }

    public class ViewHolderSmall extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleView;

        public ViewHolderSmall(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image);
            titleView = (TextView) v.findViewById(R.id.title);
            MaterialRippleLayout.on(imageView)
                    .rippleColor(Color.parseColor("#FF0000"))
                    .rippleAlpha(0.2f)
                    .rippleHover(true)
                    .create();
        }
    }

    public class ViewHolderLarge extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleView, overviewView, readMoreView;

        public ViewHolderLarge(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image);
            titleView = (TextView) v.findViewById(R.id.title);
            overviewView = (TextView) v.findViewById(R.id.overview);
            readMoreView = (TextView) v.findViewById(R.id.read_more);
            MaterialRippleLayout.on(imageView)
                    .rippleColor(Color.parseColor("#FF0000"))
                    .rippleAlpha(0.2f)
                    .rippleHover(true)
                    .create();
            MaterialRippleLayout.on(readMoreView)
                    .rippleColor(Color.parseColor("#FF0000"))
                    .rippleAlpha(0.2f)
                    .rippleHover(true)
                    .create();
        }
    }
}
