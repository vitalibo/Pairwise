package ua.edu.lp.asu.pairwise;

import org.testng.internal.collections.Pair;

import java.util.Arrays;
import java.util.HashMap;

final class TestHelper {

    private TestHelper() {
        super();
    }

    static Parameter<Object> parameter(String name, Object... values) {
        return new Parameter<>(name, values);
    }

    @SafeVarargs
    static Case case_(Pair<String, Object>... pairs) {
        return new Case(Arrays.stream(pairs)
            .collect(HashMap::new, (m, v) -> m.put(v.first(), v.second()), HashMap::putAll));
    }

    static Pair<String, Object> pair(String name, Object value) {
        return new Pair<>(name, value);
    }

    static Pair<String, Object> a(Object value) {
        return pair("A", value);
    }

    static Pair<String, Object> b(Object value) {
        return pair("B", value);
    }

    static Pair<String, Object> c(Object value) {
        return pair("C", value);
    }

    static Pair<String, Object> d(Object value) {
        return pair("D", value);
    }

    static Pair<String, Object> e(Object value) {
        return pair("E", value);
    }

}
