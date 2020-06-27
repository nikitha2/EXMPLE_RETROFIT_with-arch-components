package com.nikitha.android.retrofitexample;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewModel extends ViewModel {
    Repository repository;

    public MainViewModel(Context context, RecyclerView recyclerView) {
        repository = new Repository(context,recyclerView);
    }

    public MutableLiveData<List<RetroPhoto>> getTasks() {
        return repository.getTasks();
    }

}
