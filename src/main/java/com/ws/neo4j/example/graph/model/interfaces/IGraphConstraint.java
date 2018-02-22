package com.ws.neo4j.example.graph.model.interfaces;

public interface IGraphConstraint {

    /**
     * Constraints is based on label to identify objects
     *
     * @return
     */
    String getLabel();

    /**
     * Define label to constraint, used it's to identify object
     *
     * @param label
     * @return
     */
    IGraphConstraint setLabel(final String label);

    /**
     * Constraint is apply over a field
     *
     * @return
     */
    String getField();

    /**
     * Define field to apply the constraint
     *
     * @param field
     * @return
     */
    IGraphConstraint setField(final String field);

}
