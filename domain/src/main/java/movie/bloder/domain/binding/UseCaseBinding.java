package movie.bloder.domain.binding;


import io.reactivex.disposables.Disposable;
import movie.bloder.domain.UseCase;

abstract class UseCaseBinding {

    private final UseCase useCase;

    UseCaseBinding(UseCase useCase) {
        this.useCase = useCase;
    }

    protected void addDisposable(Disposable disposable) {
        useCase.addDisposable(disposable);
    }

    abstract void bind();
}
