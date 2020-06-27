package com.nikitha.android.retrofitexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    MutableLiveData<List<RetroPhoto>> photoList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        recyclerView = findViewById(R.id.customRecyclerView);
        setupViewModel();


    }

    private void setupViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(this,recyclerView);
        MainViewModel viewModel = ViewModelProviders.of(this,factory).get(MainViewModel.class);

        viewModel.getTasks().observe(this,  new Observer<List<RetroPhoto>>(){
            @Override
            public void onChanged(List<RetroPhoto> retroPhotos) {
                if(retroPhotos.isEmpty()){
                    progressDoalog.dismiss();
                    Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDoalog.dismiss();
                    generateDataList( retroPhotos );
                }
            }
        });
    }

    private void generateDataList( List<RetroPhoto> photoList) {
        CustomAdapter adapter;
        //RecyclerView recyclerView;
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}