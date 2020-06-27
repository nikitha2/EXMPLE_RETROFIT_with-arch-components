package com.nikitha.android.retrofitexample;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

// COMPLETED (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
         RecyclerView recyclerView;
        Context context;

        public MainViewModelFactory(Context context, RecyclerView recyclerView) {
           this.recyclerView=recyclerView;
           this.context=context;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MainViewModel(context,recyclerView);
        }
}

