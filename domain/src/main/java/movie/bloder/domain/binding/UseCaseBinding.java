package movie.bloder.domain.binding;

import io.reactivex.disposables.Disposable;
import movie.bloder.domain.UseCase;

abstract class UseCaseBinding<T> {

    private final UseCase useCase;

    UseCaseBinding(UseCase useCase) {
        this.useCase = useCase;
    }

    void addDisposable(Disposable disposable) {
        useCase.addDisposable(disposable);
    }

    abstract public T bind();
}
