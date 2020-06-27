package com.nikitha.android.retrofitexample;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    Context context;
    RecyclerView recyclerView;
    public Repository(Context applicationContext,RecyclerView recyclerView) {
        context=applicationContext;
        this.recyclerView=recyclerView;
    }

    public MutableLiveData<List<RetroPhoto>> getTasks() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();

        final MutableLiveData<List<RetroPhoto>> newsData = new MutableLiveData<>();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                System.out.println("onFailure");
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
