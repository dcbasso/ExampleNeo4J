package com.ws.neo4j.example.graph.model.interfaces;

import java.util.List;

public interface IGraphIndex {

    /**
     * Index is based on label to identify objects
     *
     * @return
     */
    String getLabel();

    /**
     * Define label to index, used it's to identify object
     *
     * @param label
     * @return
     */
    IGraphIndex setLabel(final String label);

    /**
     * Index is apply over a field
     *
     * @return
     */
    List<String> getFields();

    /**
     * Define field to apply the index
     *
     * @param fields
     * @return
     */
    IGraphIndex setFields(final List<String> fields);

}
