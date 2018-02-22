package com.ws.neo4j.example.graph.model.abs;

import com.ws.neo4j.example.graph.model.interfaces.IGraphEdge;
import com.ws.neo4j.example.graph.model.interfaces.IGraphNode;

public abstract class AbstractGraphNode<T extends IGraphEdge> extends AbstractGraphObject implements IGraphNode<T> {

}
