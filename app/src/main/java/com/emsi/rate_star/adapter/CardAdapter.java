package com.emsi.rate_star.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emsi.rate_star.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context context;
    private ArrayList card_id,card_name,card_image,card_rate;

    public CardAdapter(Context context, ArrayList card_id, ArrayList card_name, ArrayList card_image, ArrayList card_rate){
        this.context=context;
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_image = card_image;
        this.card_rate = card_rate;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText((String.valueOf(card_id.get(position))));
        holder.name.setText((String.valueOf(card_name.get(position))));
        holder.Rate.setText((String.valueOf(card_rate.get(position))));
        holder.rate_b.setRating(Float.parseFloat((String) card_rate.get(position)));
        Glide
                .with(context)
                .load((String.valueOf(card_image.get(position))))
                .centerCrop()
                .into(holder.Card_image);

    }

    @Override
    public int getItemCount() {
        return card_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,Rate;
        ImageView Card_image;
        RatingBar rate_b;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            Rate = itemView.findViewById(R.id.Rate);
            Card_image = itemView.findViewById(R.id.Card_image);
            rate_b = itemView.findViewById(R.id.ratingBar);

        }
    }
}
