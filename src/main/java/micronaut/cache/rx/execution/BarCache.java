package micronaut.cache.rx.execution;

import io.micronaut.cache.annotation.Cacheable;
import io.reactivex.Single;
import javax.inject.Singleton;

@Singleton
@Cacheable("bar-cache") // <-- Remove this
public class BarCache {

    @Cacheable // <-- and this for expected behaviour
    public Single<Bar> getBar(String id) {
        return Single.fromCallable(() -> {
            System.out.println("2. Get cacheable Bar");
            return Bar.builder().id(id).build();
        });
    }
}
