package com.app.softxperttask.Views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import android.widget.Toast;

import com.app.softxperttask.Model.CarModel;
import com.app.softxperttask.R;
import com.app.softxperttask.Adapter.RecyclerViewAdapter;

import com.app.softxperttask.Tools.LoadEnd;
import com.app.softxperttask.ViewModel.MainActivityViewModel;
import com.app.softxperttask.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    LoadEnd loadEnd;
    private ActivityMainBinding binding;
    private RecyclerViewAdapter mAdapter;
    MainActivityViewModel viewModel;
    private ArrayList<CarModel> modelList = new ArrayList<>();
    static int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        loadEnd = new LoadEnd() {
            @Override
            public void end() {
                binding.progressBar.setVisibility(View.GONE);
                binding.swipeRefreshRecyclerList.setRefreshing(false);
            }
        };
        setAdapter();

        viewModel.getPending().observe(this, new Observer<ArrayList<CarModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<CarModel> Cars) {
                try {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.swipeRefreshRecyclerList.setRefreshing(false);
                    modelList.addAll(Cars);
                    mAdapter.updateList(Cars);
                } catch (Exception e) {

                }
            }
        });

        viewModel.Get(this, page, loadEnd);

        binding.swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                page = 1;
                mAdapter.ResetList();
                viewModel.Get(MainActivity.this, page, loadEnd);

            }
        });

    }


    private void setAdapter() {


        mAdapter = new RecyclerViewAdapter(MainActivity.this, modelList);

        binding.recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.recyclerView.setLayoutManager(layoutManager);


        binding.recyclerView.setAdapter(mAdapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.progressBar.setVisibility(View.VISIBLE);

                    // supply a positive number to recyclerView.canScrollVertically(int direction) to check if scrolling down.
                    boolean canScrollDownMore = recyclerView.canScrollVertically(1);
                    // If recyclerView.canScrollVertically(1) returns false it means you're at the end of the list.
                    if (!canScrollDownMore) {
                        //call the overridden onScrolled() method in our EndlessRecyclerViewScrollListener class.
                        // supply any positive number to the third argument to indicate that we've scrolled downward.
                        page++;
                        viewModel.Get(MainActivity.this, page, loadEnd);
                        onScrolled(recyclerView, 0, 1);
                    } else {
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CarModel model) {

                Toast.makeText(MainActivity.this, "Hey " + model.getBrand(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}
