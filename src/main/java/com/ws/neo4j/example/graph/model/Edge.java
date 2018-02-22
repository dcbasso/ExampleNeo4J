package com.ws.neo4j.example.graph.model;


import com.ws.neo4j.example.graph.model.abs.AbstractGraphEdge;
import com.ws.neo4j.example.graph.model.converter.EdgeMeasureMapConverter;
import com.ws.neo4j.example.graph.model.interfaces.IGraphEdge;
import com.ws.neo4j.example.graph.model.interfaces.IGraphNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.math.BigDecimal;
import java.util.*;

@RelationshipEntity(type = IGraphEdge.TYPE_CHILDREN)
public class Edge extends AbstractGraphEdge<Node> {

    /**
     * Used to identifier measures on database
     */
    public static final String MEASURE_PREFIX = "measure";

    /**
     * Dynamic measures on edge
     */
    @Convert(value=EdgeMeasureMapConverter.class)
    private Map<String, BigDecimal> measures;

    /**
     * Genotype is identifier the origin of edge, carry of identifier all your parents
     */
    private List<String> genotype;

    /**
     * Node which the relation ends
     */
    @EndNode
    private Node endNode;

    /**
     * Node which the relation starts
     */
    @StartNode
    private Node startNode;

    /**
     * Forecast UUID
     */
    @Property(name = "forecast_id")
    @Convert(UuidStringConverter.class)
    private UUID forecastId;

    /**
     * Get the measures from Edge
     * Construct new object with null in all fields
     */
    public Edge() {
    }

    /**
     * Construct new object with id, startNode, endNode and genotype
     *
     * @param id
     * @param start
     * @param end
     * @param genotype
     */
    public Edge(final UUID id, final Node start, final Node end, final Genotype genotype) {
        this.setUuid(id);
        this.startNode = start;
        this.endNode = end;
        this.genotype = Arrays.asList(genotype.getValue());
    }

    /**
     * Get the measures from Edge
     *
     * @return
     */
    public Map<String, BigDecimal> getMeasures() {
        return Collections.unmodifiableMap(this.measures);
    }

    /**
     * Set the measures to the Edge
     *
     * @param measures
     */
    public void setMeasures(final Map<String, BigDecimal> measures) {
        this.measures = measures;
    }

    /**
     * Add new property to {@link #measures}
     *
     * @param key
     * @param value
     */
    public void addMeasure(final String key, final BigDecimal value) {
        if (this.measures == null) {
            this.measures = new HashMap<>();
        }
        this.measures.put(key, value);
    }

    /**
     * Get the genotype from this Edge
     *
     * @return
     */
    public Genotype getGenotype() {
        if (this.genotype != null) {
            final String[] array = new String[this.genotype.size()];
            this.genotype.toArray(array);
            return new Genotype(array);
        }
        return null;
    }

    /**
     * Set the genotype to this Edge
     *
     * @param genotype
     */
    public void setGenotype(final Genotype genotype) {
        this.genotype = genotype == null ? null : Arrays.asList(genotype.getValue());
    }

    /**
     * @see IGraphEdge#getEndNode()
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * @see IGraphEdge#setEndNode(IGraphNode)
     */
    public AbstractGraphEdge setEndNode(final Node endNode) {
        this.endNode = endNode;
        return this;
    }

    /**
     * @see IGraphEdge#getStartNode()
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * @see IGraphEdge#setStartNode(IGraphNode)
     */
    public AbstractGraphEdge setStartNode(final Node startNode) {
        this.startNode = startNode;
        return this;
    }

    /**
     * Get UUID from Forecast Parent
     *
     * @return {@link UUID}
     */
    public UUID getForecastId() {
        return forecastId;
    }

    /**
     * Set UUID for Forecast Parent
     *
     * @param forecastId
     */
    public void setForecastId(UUID forecastId) {
        this.forecastId = forecastId;
    }

    /**
     * Check if param edgeChild if parent of edgeParent
     *
     * @param edgeParent
     * @param edgeChild
     * @return boolean
     */
    public static boolean isParent(Edge edgeParent, Edge edgeChild) {
        final Genotype genotype = edgeChild.getGenotype();
        final Genotype parent = Genotype.getParent(genotype);
        return Arrays.equals(edgeParent.getGenotype().getValue(), parent.getValue());
    }

}
