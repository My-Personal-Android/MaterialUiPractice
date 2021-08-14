package com.materialuipractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.materialuipractice.data.ProductEntry;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardRecyclerViewAdapter.ProductCardViewHolder> {

    private List<ProductEntry> productList;
    private Context mCOntext;

    ProductCardRecyclerViewAdapter(List<ProductEntry> productList, Context context) {
        this.productList = productList;
        mCOntext = context;
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // TODO: Put ViewHolder binding code here in MDC-102
        if (productList != null && position < productList.size()) {
            ProductEntry product = productList.get(position);

            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);

            Glide.with(mCOntext)
                    .load(product.url)
                    .centerCrop()
                    .into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductCardViewHolder extends RecyclerView.ViewHolder{

        public ImageView productImage;
        public TextView productTitle;
        public TextView productPrice;

        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}