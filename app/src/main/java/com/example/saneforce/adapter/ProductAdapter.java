package com.example.saneforce.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saneforce.R;
import com.example.saneforce.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view, deleteClickListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.rate = product.getRate();
        holder.updateTotal();
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public void removeItem(int position) {
        if (productList != null && position >= 0 && position < productList.size()) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, total;
        EditText tvPlus, tvMinus;
        ImageButton btnPlus, btnMinus;
        Button btnDelete;
        double rate; // Rate of the product

        public ProductViewHolder(View itemView, OnDeleteClickListener deleteClickListener) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_product_name);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            tvPlus = itemView.findViewById(R.id.tvPlus);
            tvMinus = itemView.findViewById(R.id.tvMinus);
            total = itemView.findViewById(R.id.total);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);

            // Set the delete button click listener
            btnDelete.setOnClickListener(view -> {
                if (deleteClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    deleteClickListener.onDeleteClick(getAdapterPosition());
                }
            });

            // Handle the increase quantity button
            btnPlus.setOnClickListener(view -> {
                int currentQty = Integer.parseInt(tvPlus.getText().toString());
                tvPlus.setText(String.valueOf(currentQty + 1));
                updateTotal();
            });

            // Handle the decrease quantity button
            btnMinus.setOnClickListener(view -> {
                int currentQty = Integer.parseInt(tvMinus.getText().toString());
                if (currentQty > 0) {
                    tvMinus.setText(String.valueOf(currentQty - 1));
                    updateTotal();
                }
            });

            // TextWatcher for EditText fields to detect manual changes
            tvPlus.addTextChangedListener(new android.text.TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    updateTotal();
                }

                @Override
                public void afterTextChanged(android.text.Editable editable) {}
            });

            tvMinus.addTextChangedListener(new android.text.TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    updateTotal();
                }

                @Override
                public void afterTextChanged(android.text.Editable editable) {}
            });
        }

        // Method to update the total value dynamically
        private void updateTotal() {
            try {
                int qtyPlus = Integer.parseInt(tvPlus.getText().toString());
                int qtyMinus = Integer.parseInt(tvMinus.getText().toString());

                // Calculate the final quantity: qtyPlus - qtyMinus
                int finalQty = qtyPlus - qtyMinus;

                // Set only the final total in the TextView
                total.setText(String.valueOf(finalQty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
