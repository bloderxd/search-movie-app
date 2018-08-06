package com.poppin.movies;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import movie.bloder.presentation.presentation.state.State;
import movie.bloder.presentation.presentation.view_model.AppViewModel;

abstract public class StateActivity<T extends State> extends AppCompatActivity {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(provideViewModel());
        observeViewModel();
    }

    private void observeViewModel() {
        provideViewModel().state().observe(this, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T state) {
                handleState(state);
            }
        });
    }

    abstract void handleState(T state);
    abstract AppViewModel<T> provideViewModel();
}
