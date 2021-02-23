package micronaut.cache.rx.execution;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Foo {
    String id;
    String barId;
}
