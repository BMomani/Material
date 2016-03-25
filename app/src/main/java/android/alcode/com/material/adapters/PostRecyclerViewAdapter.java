package android.alcode.com.material.adapters;

import android.alcode.com.material.R;
import android.alcode.com.material.models.Post;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by MOMANI on 2016/03/22.
 */
public class PostRecyclerViewAdapter  extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolderSmall> {
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
    public ViewHolderSmall onCreateViewHolder(ViewGroup parent, int viewType) {
        mDefaultColor= ContextCompat.getColor(parent.getContext(), (R.color.colorPrimaryTransparent));
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_column, parent, false);
        return new ViewHolderSmall(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderSmall holder, final int position) {
        final Post post = mPostList.get(position);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapterCallback != null) {
                    mAdapterCallback.onItemSelected(mPostList.get(position).getId());
                }
            }
        });

        holder.titleView.setText(post.getTitle());
        Picasso.with(holder.imageView.getContext()).load(post.getImageUrl())
                .into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return null == mPostList ? 0 : mPostList.size();
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
}
