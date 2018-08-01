package com.example.manjunath.farmerhelpapp.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manjunath.farmerhelpapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mcontext;
    private List<Upload> muploads;

    public ImageAdapter(Context mcontext, List<Upload> muploads) {
        this.mcontext = mcontext;
        this.muploads = muploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = muploads.get(position);
        holder.textName.setText(uploadCurrent.getMname());
        holder.Contactno.setText(uploadCurrent.getContactno());
        holder.textpincode.setText(uploadCurrent.getPincode());
        holder.textDistrict.setText(uploadCurrent.getDistrict());
        Picasso.with(mcontext)
                .load(uploadCurrent.getMimageurl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textDistrict;
        public ImageView imageView;
        public TextView textpincode;
        public TextView Contactno;

        public ImageViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.imagedname);
            textDistrict = itemView.findViewById(R.id.imageddistrict);
            imageView = itemView.findViewById(R.id.imaged);
            textpincode = itemView.findViewById(R.id.pincoded);
            Contactno = itemView.findViewById(R.id.contactd);
        }
    }
}
