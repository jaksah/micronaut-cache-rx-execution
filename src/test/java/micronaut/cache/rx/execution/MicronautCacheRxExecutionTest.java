package micronaut.cache.rx.execution;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class MicronautCacheRxExecutionTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testCache() {
        BarCache cache = application.getApplicationContext().getBean(BarCache.class);

        Foo foo = Foo.builder().id("foo").barId("barId").build();
        validateFoo(foo)
            .andThen(cache.getBar(foo.getBarId()))
            .flatMapCompletable(bar -> saveFooWithBar(foo, bar))
            .blockingAwait();
    }

    private Completable validateFoo(Foo foo) {
        return Completable.fromAction(() -> {
            System.out.println("1. Validate Foo");
        });
    }

    private Completable saveFooWithBar(Foo foo, Bar bar) {
        return Completable.fromAction(() -> System.out.println("3. Saving Foo with Bar"));
    }
}
