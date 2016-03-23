package android.alcode.com.material.adapters;

import android.alcode.com.material.R;
import android.alcode.com.material.models.Post;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;


/**
 * Created by MOMANI on 2016/03/22.
 */
public class PostRecyclerViewAdapter  extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {
    private final List<Post> mPostList;
    private final Activity mAct;
    private final OnAdapterItemSelectedListener mAdapterCallback;
    private int mDefaultColor;

    public PostRecyclerViewAdapter(List<Post> posts, Activity activity) {
        mPostList = posts;
        this.mAct = activity;

        mAdapterCallback = (OnAdapterItemSelectedListener) mAct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mDefaultColor= ContextCompat.getColor(parent.getContext(), (R.color.colorPrimaryTransparent));
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_column, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Post post = mPostList.get(position);
        holder.mGridItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapterCallback != null) {
                    mAdapterCallback.onItemSelected(mPostList.get(position).getId());
                }
            }
        });

        holder.mGridItemContainer.setContentDescription(holder.mGridItemContainer.getContext().getString(R.string.a11y_Post_title, post.getTitle()));

        holder.mReleaseDateTextView.setText(post.getTitle());

        holder.mSortTypeValueTextView.setText(post.getLikes()+"");


        final RelativeLayout container = holder.mPostTitleContainer;

        Picasso.with(holder.mPostImageView.getContext()).load(post.getImageUrl())
                .into(holder.mPostImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap posterBitmap = ((BitmapDrawable) holder.mPostImageView.getDrawable()).getBitmap();
                        Palette.from(posterBitmap).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                container.setBackgroundColor(ColorUtils.setAlphaComponent(palette.getMutedColor(mDefaultColor), 190)); //Opacity
                            }
                        });
                    }

                    @Override
                    public void onError() {
                    }
                });


    }


    @Override
    public int getItemCount() {
        return null == mPostList ? 0 : mPostList.size();
    }

    public interface OnAdapterItemSelectedListener {
        void onItemSelected(String id);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mSortTypeValueTextView;

        TextView mReleaseDateTextView;

        ImageView mPostImageView;

        ImageView mSortTypeIconImageView;

        RelativeLayout mPostTitleContainer;

        FrameLayout mGridItemContainer;

        public ViewHolder(View view) {
            super(view);
            mSortTypeValueTextView = (TextView) view.findViewById(R.id.grid_item_sort_type_text_view);
            mReleaseDateTextView = (TextView) view.findViewById(R.id.grid_item_release_date_text_view);
            mPostImageView = (ImageView) view.findViewById(R.id.grid_item_poster_image_view);
            mSortTypeIconImageView = (ImageView) view.findViewById(R.id.grid_item_sort_type_image_view);
            mPostTitleContainer = (RelativeLayout) view.findViewById(R.id.grid_item_title_container);
            mGridItemContainer = (FrameLayout) view.findViewById(R.id.grid_item_container);
        }
    }
}
