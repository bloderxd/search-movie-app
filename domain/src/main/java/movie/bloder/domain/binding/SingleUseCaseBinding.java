package movie.bloder.domain.binding;


import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import movie.bloder.domain.UseCase;

public class SingleUseCaseBinding<T> extends UseCaseBinding {

    private Function<T, ?> onSuccess;
    private Function<Throwable, ?> onError;
    private Single<T> single;

    public SingleUseCaseBinding(UseCase useCase, Single<T> single) {
        super(useCase);
        this.single = single;
    }

    public SingleUseCaseBinding<T> onSuccess(Function<T, ?> onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public SingleUseCaseBinding<T> onError(Function<Throwable, ?> onError) {
        this.onError = onError;
        return this;
    }

    @Override void bind() {
        addDisposable(single.subscribeWith(new DisposableSingleObserver<T>() {
            @Override
            public void onSuccess(T t) {
                try {
                    SingleUseCaseBinding.this.onSuccess.apply(t);
                } catch (Exception ignored) {}
            }

            @Override
            public void onError(Throwable e) {
                try {
                    SingleUseCaseBinding.this.onError.apply(e);
                } catch (Exception ignored) {}
            }
        }));
    }
}
