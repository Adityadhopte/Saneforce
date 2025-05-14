package com.example.saneforce;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saneforce.adapter.ProductAdapter;
import com.example.saneforce.model.Product;
import com.example.saneforce.viewmodel.ProductViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private ProductAdapter productAdapter;
    private List<Product> currentProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnDeleteClickListener(position -> {
            if (currentProductList != null) {
                currentProductList.remove(position);
                productAdapter.removeItem(position);
            }
        });

        productViewModel.getProductList().observe(this, products -> {
            currentProductList = products;
            productAdapter.setProductList(products);
        });

        productViewModel.fetchProducts();
    }
}
