package com.ws.neo4j.example.graph.model.interfaces;

public interface IGraphEdge<T extends IGraphNode> extends IGraphObject {

    /**
     * Label possible to use on edge identification
     */
    String TYPE_CHILDREN = "CHILDREN";

    /**
     * Returns which node the relation starts
     *
     * @return
     */
    T getStartNode();

    /**
     * Sets the starting node
     *
     * @param start
     * @return
     */
    IGraphEdge setStartNode(final T start);

    /**
     * Returns which node the relation ends
     *
     * @return
     */
    T getEndNode();

    /**
     * Sets the ending node
     *
     * @param end
     * @return
     */
    IGraphEdge setEndNode(final T end);

}
