package com.ws.neo4j.example.graph.model.abs;

import com.ws.neo4j.example.graph.model.interfaces.IGraphObject;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.id.InternalIdStrategy;
import org.neo4j.ogm.typeconversion.OffsettDateTimeStringConverter;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.time.OffsetDateTime;
import java.util.*;

abstract class AbstractGraphObject implements IGraphObject {

    /**
     * Identifier of object
     */
    @Id
    @GeneratedValue(strategy = InternalIdStrategy.class)
    private Long id;

    /**
     * Identifier of object
     */
    @Property(name = "uuid")
    @Convert(UuidStringConverter.class)
    private UUID uuid;

    /**
     * Label can be used to identify type object
     */
    @Labels
    private List<String> labels = new ArrayList<>();

    /**
     * Use to lock object in database.
     */
    @Convert(OffsettDateTimeStringConverter.class)
    private OffsetDateTime lastAccess;

    /**
     * Get of property {@link #id}
     *
     * @return Long
     */
    public final Long getId() {
        return id;
    }

    /**
     * Set of property {@link #id}
     *
     * @param id
     */
    public final void setId(Long id) {
        this.id = id;
    }

    /**
     * @see IGraphObject#getUuid()
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @see AbstractGraphObject#setUuid(UUID)
     */
    public IGraphObject setUuid(final UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * @see IGraphObject#addLabel(String)
     */
    public AbstractGraphObject addLabel(final String label) {
        Objects.requireNonNull(label, "label cannot be null");
        this.labels.add(label);
        return this;
    }

    /**
     * @see IGraphObject#removeLabel(String)
     */
    public AbstractGraphObject removeLabel(final String label) {
        Objects.requireNonNull(label, "label cannot be null");
        this.labels.remove(label);
        return this;
    }

    /**
     * @see IGraphObject#getLabels()
     */
    public List<String> getLabels() {
        return Collections.unmodifiableList(this.labels);
    }

    /**
     * Return last Access of the Object.
     *
     * @return {@link OffsetDateTime}
     */
    public OffsetDateTime getLastAccess() {
        return lastAccess;
    }

    /**
     * Set Last Access of object. This will be used to make Neo4J lock the entity in transaction.
     *
     * @param lastAccess
     */
    public void setLastAccess(OffsetDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }
}
