package com.ws.neo4j.example.graph.model.abs;

import com.ws.neo4j.example.graph.model.interfaces.IGraphEdge;
import com.ws.neo4j.example.graph.model.interfaces.IGraphNode;

public abstract class AbstractGraphEdge<T extends IGraphNode> extends AbstractGraphObject implements IGraphEdge<T> {

}
