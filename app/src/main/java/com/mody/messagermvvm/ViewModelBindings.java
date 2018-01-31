package com.mody.messagermvvm;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.mody.messagermvvm.viewmodel.RecyclerViewModel;

public class ViewModelBindings {

    @BindingAdapter("recyclerViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                RecyclerViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}
