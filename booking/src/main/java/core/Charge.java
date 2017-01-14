/*
 * This is very much a stub for usecase/testing purposes.
 */
package core;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 * @author neo
 */
@NodeEntity
public class Charge {

    //public protocol
    @GraphId
    private Long graphId;

    /**
     * No-arg constructor for persistence.
     */
    private Charge() {
    }

    public Long getGraphId() {
        return graphId;
    }
    //package protocol
}
