/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 *
 * @author Simon
 */
@NodeEntity
public class Room {

    //attributes
    @GraphId
    private Long graphId;

    private String name;//name of room unique? - no neo
    private int capacity;//capacity of room
    private RoomType type;//fixed enum of roomType
    
    @Fetch
    @RelatedTo(elementClass = Asset.class)
    private Collection<Asset> assets;//assets owned by this room

    //constructor
    /**
     * No-arg constructor for persistence.
     */
    private Room() {
    }

    public Room(String name, Collection<Asset> assets, int capacity, RoomType type) {
        this.name = name;
        this.assets = assets;
        this.capacity = capacity;
        this.type = type;
        this.assets = new HashSet<Asset>(); //is this right?
    }

    public void addAsset(Asset asset) {
        this.assets.add(asset);
    }

    //public protocol
    /**
     * Returns the name of this Room.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the assets of this Room.
     *
     * @return assets
     */
    public Collection<Asset> getAssets() {
        return this.assets;
    }

    /**
     * Returns the capacity of this Room.
     *
     * @return capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Returns the RoomType of this Room.
     *
     * @return type
     */
    public RoomType getType() {
        return this.type;
    }

    /**
     * Returns the Bookings of this Room.
     *
     * @return Collection of Booking objects linked to the receiver
     */
    public Collection<Booking> getBookings() {
        //stub method to be completed
        return null;
    }

    /**
     * @return the graphId
     */
    public Long getGraphId() {
        return graphId;
    }

    @Override
    public String toString() {
        return name
                + "\nType: " + type
                + "\nCapacity: " + capacity;
    }
}
