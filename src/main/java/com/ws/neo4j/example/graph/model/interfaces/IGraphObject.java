package com.ws.neo4j.example.graph.model.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public interface IGraphObject extends Serializable {

    /**
     * Returns the identifier of database object
     *
     * @return
     */
    UUID getUuid();

    /**
     * Set identifier of object
     *
     * @param id
     * @return
     */
    IGraphObject setUuid(final UUID id);

    /**
     * add new labels, labels can be used to identify type object
     *
     * @param label
     * @return
     */
    IGraphObject addLabel(final String label);

    /**
     * Remove labels
     *
     * @param label
     * @return
     */
    IGraphObject removeLabel(final String label);

    /**
     * Returns all labels existing on object
     *
     * @return
     */
    Collection<String> getLabels();

}
