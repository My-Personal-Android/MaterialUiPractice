package com.materialuipractice.staggeregridlayout;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.materialuipractice.R;
import com.materialuipractice.data.ProductEntry;

import java.util.List;

/**
 * Adapter used to show an asymmetric grid of products, with 2 items in the first column, and 1
 * item in the second column, and so on.
 */
public class StaggeredProductCardRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredProductCardRecyclerViewAdapter.StaggeredProductCardViewHolder> {

    private List<ProductEntry> productList;
    private Context mContext;

    public StaggeredProductCardRecyclerViewAdapter(List<ProductEntry> productList, Context context) {
        this.productList = productList;
        this.mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public StaggeredProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.shr_staggered_product_card_first;
        if (viewType == 1) {
            layoutId = R.layout.shr_staggered_product_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.shr_staggered_product_card_third;
        }

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new StaggeredProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredProductCardViewHolder holder, int position) {
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            Log.v("Kaloo",product.url);
            Glide.with(mContext)
                    .load(product.url)
                    .centerCrop()
                    .into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class StaggeredProductCardViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImage;
        public TextView productTitle;
        public TextView productPrice;

        StaggeredProductCardViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
