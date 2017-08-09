package com.dagger.example.features.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dagger.example.R;
import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for Photo items in {@link com.dagger.example.features.main.MainFragment}
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private Context context;
    private List<PhotoDto> dataset;

    public PhotoAdapter(Context context) {
        this.context = context;
        this.dataset = new ArrayList<>();
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        if (holder != null) {
            holder.onBindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return dataset!=null ? dataset.size() : 0;
    }

    public void addItems(List<PhotoDto> photoList) {
        dataset.addAll(photoList);
        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo_image) ImageView ivImage;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBindView(final int position) {
            final String url;

            if (new NetworkUtils(context).isOnline()) {
                url = dataset.get(position).getUrl();
            } else {
                url = dataset.get(position).getDownloadUrl();
            }

            Picasso.with(context)
                    .load(url)
                    .fit()
                    .centerCrop()
                    .into(ivImage);
        }

    }

}
