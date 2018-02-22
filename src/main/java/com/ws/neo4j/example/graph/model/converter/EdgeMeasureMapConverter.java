package com.ws.neo4j.example.graph.model.converter;

import com.ws.neo4j.example.graph.model.Edge;
import org.neo4j.ogm.typeconversion.CompositeAttributeConverter;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class EdgeMeasureMapConverter implements CompositeAttributeConverter<Map<String, BigDecimal>> {

    private static final String FIRST_PATH = Edge.MEASURE_PREFIX + '.';

    /**
     * Overrider method converts from {@code Map<String, BigDecimal>} to {@code Map<String, ?>}
     *
     * @param value
     * @return Map content
     */
    @Override
    public Map<String, ?> toGraphProperties(Map<String, BigDecimal> value) {
        if (value == null)
            return Collections.emptyMap();
        Map<String, String> graphProperties = new HashMap<>(value.size());
        addMapToProperties(value, graphProperties, FIRST_PATH);
        return graphProperties;
    }

    private void addMapToProperties(Map<?, ?> fieldValue, Map<String, String> graphProperties, String prefix) {
        for (Map.Entry<?, ?> entry : fieldValue.entrySet()) {
            final String key = new StringBuilder(prefix)
                    .append(entry.getKey())
                    .toString();
            final String entryValue = entry.getValue()
                    .toString();
            graphProperties.put(key, entryValue);
        }
    }

    /**
     * Overrider method converts from {@code Map<String, ?>} to {@code Map<String, BigDecimal>}
     *
     * @param value
     * @return Map content
     */
    @Override
    public Map<String, BigDecimal> toEntityAttribute(Map<String, ?> value) {
        Set<? extends Map.Entry<String, ?>> prefixedProperties = value.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(FIRST_PATH))
                .collect(toSet());

        Map<String, BigDecimal> result = new HashMap<>();
        for (Map.Entry<String, ?> entry : prefixedProperties) {
            String propertyKey = entry.getKey().substring(FIRST_PATH.length());
            result.put(propertyKey, new BigDecimal((String) entry.getValue()));
        }
        return result;
    }

}
