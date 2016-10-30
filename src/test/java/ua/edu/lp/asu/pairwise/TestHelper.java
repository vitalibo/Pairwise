package ua.edu.lp.asu.pairwise;

import org.testng.internal.collections.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

final class TestHelper {

    private TestHelper() {
        super();
    }

    static Parameter<Object> parameter(String name, Object... values) {
        return new Parameter<>(name, values);
    }

    @SafeVarargs
    static Case pairs(Pair<String, Object>... pairs) {
        return new Case(Arrays.stream(pairs)
            .collect(HashMap::new, (m, v) -> m.put(v.first(), v.second()), HashMap::putAll));
    }

    static Case pairs(Object... values) {
        return new Case(IntStream.range(0, values.length)
            .mapToObj(i -> new Pair<>("key" + i, values[i]))
            .collect(HashMap::new, (m, p) -> m.put(p.first(), p.second()), Map::putAll));
    }

    static Pair<String, Object> tuple(String name, Object value) {
        return new Pair<>(name, value);
    }

}
