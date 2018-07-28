package movie.bloder.domain;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import movie.bloder.repository.repository.Repository;
import movie.bloder.repository.repository.RepositoryFactory;

public class UseCase {

    protected final RepositoryFactory repository = Repository.getProduction();
    protected Scheduler scheduler = AndroidSchedulers.mainThread();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        compositeDisposable.dispose();
    }

    public void provideScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
