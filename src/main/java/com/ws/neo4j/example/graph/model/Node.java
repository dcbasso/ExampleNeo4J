package com.ws.neo4j.example.graph.model;

import com.ws.neo4j.example.graph.model.abs.AbstractGraphNode;
import com.ws.neo4j.example.graph.model.interfaces.IGraphEdge;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.*;

@NodeEntity
public class Node extends AbstractGraphNode<Edge> {

    public static final String TYPE_FORECAST = "Forecast";
    public static final String TYPE_COMPANY = "Company";
    public static final String TYPE_PROFESSIONAL = "Professional";
    public static final String TYPE_CUSTOMER = "Customer";
    public static final String TYPE_PRODUCT = "Product";

    /**
     * Type of node, use to identify object type, because on graphs database non existing fixed type
     */
    @Index
    private String type;

    /**
     * ID from relational database.
     */
    @Convert(UuidStringConverter.class)
    UUID sourceId;

    /**
     * Description of node
     */
    @Property
    private String description;

    /**
     * Dimension of origin data
     */
    @Index
    @Property(name = "dimension_id")
    @Convert(UuidStringConverter.class)
    private UUID dimensionId;

    /**
     * Forecast owner this node
     */
    @Index
    @Property(name = "forecast_id")
    @Convert(UuidStringConverter.class)
    private UUID forecastId;

    /**
     * Relation OneToMany with edges
     */
    @Relationship(type = IGraphEdge.TYPE_CHILDREN)
    private List<Edge> edges = new ArrayList<>();

    /**
     * Construct new object
     */
    public Node() {
    }

    /**
     * Construct new object with parameters
     *
     * @param description
     * @param type
     */
    public Node(final String description, final String type) {
        this.description = description;
        this.type = type;
    }

    @Override
    public Node addLabel(final String label) {
        return (Node) super.addLabel(label);
    }

    /**
     * Get of property {@link #type}
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Return ID of Relational Database
     *
     * @return UUID
     */
    public UUID getSourceId() {
        return sourceId;
    }

    /**
     * Set ID of Relational Database
     *
     * @param sourceId
     */
    public void setSourceId(UUID sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * Set of property {@link #type}
     *
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the description from this Node
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description to this Node
     *
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get of property {@link #dimensionId}
     *
     * @return
     */
    public UUID getDimensionId() {
        return dimensionId;
    }

    /**
     * Set of property {@link #dimensionId}
     *
     * @param dimensionId
     */
    public void setDimensionId(final UUID dimensionId) {
        this.dimensionId = dimensionId;
    }

    /**
     * @see IGraphNode#getEdges()
     */
    public Collection<Edge> getEdges() {
        return Collections.unmodifiableList(this.edges);
    }

    /**
     * @see IGraphNode#setEdges(List)
     */
    public void setEdges(final List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Add a new Edge to the node
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    /**
     * Get Forecast UUID
     *
     * @return Forecast {@link UUID}
     */
    public UUID getForecastId() {
        return forecastId;
    }

    /**
     * Set Forecast UUID
     *
     * @param forecastId
     */
    public Node setForecastId(UUID forecastId) {
        this.forecastId = forecastId;
        return this;
    }
}
