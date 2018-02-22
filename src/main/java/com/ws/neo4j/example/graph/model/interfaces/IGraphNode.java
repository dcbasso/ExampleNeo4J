package com.ws.neo4j.example.graph.model.interfaces;

import java.util.Collection;
import java.util.List;

public interface IGraphNode<T extends  IGraphEdge> extends IGraphObject {

    /**
     * Get the edges from this Node
     *
     * @return
     */
    Collection<T> getEdges();

    /**
     * Set the edges to this Node
     *
     * @param edges
     */
    void setEdges(final List<T> edges);


}
