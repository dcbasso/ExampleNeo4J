package com.ws.neo4j.example.graph.model;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

public class Genotype implements Serializable {

    /**
     * Value of genotype
     */
    private final String[] value;

    /**
     * Genotype represent of path to current node. Genotype is value object, because change the path,  is not same object
     *
     * @param value
     */
    public Genotype(final String[] value) {
        Validate.notEmpty(value, "Parameter value can be neither null nor empty");
        this.value = Arrays.copyOf(value, value.length);
    }

    /**
     * Get the level from this Genotype
     *
     * @return
     */
    public Integer getLevel() {
        return this.value.length;
    }

    /**
     * Get the value from this Genotype
     *
     * @return
     */
    public String[] getValue() {
        return Arrays.copyOf(this.value, this.value.length);
    }

    /**
     * Return parents Array of UUID's.
     *
     * @return UUID[]
     */
    public UUID[] getValueAsUUID() {
        return Stream.of(this.getValue()).map(UUID::fromString).toArray(UUID[]::new);
    }

    /**
     * Get the genotype parent from $this parameters
     *
     * @return Genotype Parent
     */
    public static Genotype getParent(Genotype $this) {
        if ($this.getLevel() <= 1) {
            return null;
        }
        final String[] parentValue = Arrays.copyOfRange($this.value, 0, $this.value.length - 1);
        return new Genotype(parentValue);
    }

}
