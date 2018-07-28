package movie.bloder.domain;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import movie.bloder.domain.binding.SingleUseCaseBinding;

public class SingleUseCase extends UseCase {

    protected <T> SingleUseCaseBinding<T> run(Single<T> single) {
        single.subscribeOn(Schedulers.newThread()).observeOn(scheduler);
        return new SingleUseCaseBinding<>(this, single);
    }
}
