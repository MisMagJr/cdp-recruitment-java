package adeo.leroymerlin.cdp.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericCollectionMapper {
    public static <T, R> R map(T source, Function<T, R> mapper) {
        if (source == null) return null;
        return mapper.apply(source);
    }

    public static <T, R> Set<R> mapSet(Set<T> source, Function<T, R> mapper) {
        if (source == null || source.isEmpty()) {
            return Set.of();
        }
        return source.stream()
                .map(mapper)
                .collect(Collectors.toSet());
    }

    public static <T, R> List<R> mapList(List<T> source, Function<T, R> mapper) {
        if (source == null || source.isEmpty()) {
            return List.of();
        }
        return source.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
