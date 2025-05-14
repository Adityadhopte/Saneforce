package com.example.saneforce.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.saneforce.model.Product;
import com.example.saneforce.service.ApiClient;
import com.example.saneforce.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<List<Product>> productListLiveData;

    public ProductViewModel(Application application) {
        super(application);
        productListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Product>> getProductList() {
        return productListLiveData;
    }

    public void fetchProducts() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Product>> call = apiService.getProducts("get/taskproducts", "258");

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productListLiveData.setValue(response.body());
                } else {
                    productListLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                productListLiveData.setValue(null);
            }
        });
    }
}
