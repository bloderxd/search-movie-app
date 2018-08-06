package movie.bloder.presentation.presentation.view_model;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import movie.bloder.presentation.presentation.state.State;

abstract public class AppViewModel<T extends State> extends ViewModel implements LifecycleObserver {

    private MutableLiveData<T> liveData = new MutableLiveData<>();

    protected AppViewModel<T> postState(T state) {
        liveData.postValue(state);
        return this;
    }

    public LiveData<T> state() {
        return liveData;
    }
}
