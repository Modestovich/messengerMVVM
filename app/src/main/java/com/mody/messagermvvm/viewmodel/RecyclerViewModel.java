package com.mody.messagermvvm.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewModel extends ViewModel {

    RecyclerView.LayoutManager layoutManager;
    private Parcelable savedLayoutManagerState;

    abstract RecyclerView.Adapter getAdapter();
    abstract RecyclerView.LayoutManager createLayoutManager();

    public RecyclerViewModel() {
    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        layoutManager = createLayoutManager();
        if (savedLayoutManagerState != null) {
            layoutManager.onRestoreInstanceState(savedLayoutManagerState);
            savedLayoutManagerState = null;
        }
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(layoutManager);
    }

    protected static class RecyclerViewViewModelState extends RecyclerView.State {

        private final Parcelable layoutManagerState;

        public RecyclerViewViewModelState(RecyclerViewModel viewModel) {
            super();
            layoutManagerState = viewModel.layoutManager.onSaveInstanceState();
        }

        public RecyclerViewViewModelState(Parcel in) {
            super();
            layoutManagerState = in.readParcelable(
                    RecyclerView.LayoutManager.class.getClassLoader());
        }

        public static Parcelable.Creator<RecyclerViewViewModelState> CREATOR =
                new Parcelable.Creator<RecyclerViewViewModelState>() {
                    @Override
                    public RecyclerViewViewModelState createFromParcel(Parcel source) {
                        return new RecyclerViewViewModelState(source);
                    }

                    @Override
                    public RecyclerViewViewModelState[] newArray(int size) {
                        return new RecyclerViewViewModelState[size];
                    }
                };
    }
}
