package org.cloudbus.cloudsim.network.topologies;

import org.cloudbus.cloudsim.network.topologies.readers.GraphReaderBrite;
import org.cloudbus.cloudsim.util.Log;

import java.io.IOException;
import java.util.HashMap;

/**
 **
 * Implements a network layer by reading the topology from a file in a specific format
 * that is defined by each implementing class.
 *
 * @author Manoel Campos da Silva Filho
 * @see BriteNetworkTopology
 */
public interface NetworkTopology {
    /**
     * Adds a new link in the network topology. The CloudSim entities that
     * represent the source and destination of the link will be mapped to BRITE
     * entities.
     *
     * @param srcId ID of the CloudSim entity that represents the link's source
     * node
     * @param destId ID of the CloudSim entity that represents the link's
     * destination node
     * @param bw Link's bandwidth
     * @param lat link's latency
     * @pre srcId > 0
     * @pre destId > 0
     * @post $none
     */
    void addLink(int srcId, int destId, double bw, double lat);

    /**
     * Maps a CloudSim entity to a BRITE node in the network topology.
     *
     * @param cloudSimEntityID ID of the entity being mapped
     * @param briteID ID of the BRITE node that corresponds to the CloudSim
     * entity
     * @pre cloudSimEntityID >= 0
     * @pre briteID >= 0
     * @post $none
     */
    void mapNode(int cloudSimEntityID, int briteID);

    /**
     * Unmaps a previously mapped CloudSim entity to a BRITE node in the network
     * topology.
     *
     * @param cloudSimEntityID ID of the entity being unmapped
     * @pre cloudSimEntityID >= 0
     * @post $none
     */
    void unmapNode(int cloudSimEntityID);

    /**
     * Calculates the delay between two nodes.
     *
     * @param srcID ID of the CloudSim entity that represents the link's source
     * node
     * @param destID ID of the CloudSim entity that represents the link's
     * destination node
     * @return communication delay between the two nodes
     * @pre srcID >= 0
     * @pre destID >= 0
     * @post $none
     */
    double getDelay(int srcID, int destID);

    /**
     * Checks if the network simulation is working. If there were some problem
     * during creation of network (e.g., during parsing of BRITE file) that does
     * not allow a proper simulation of the network, this method returns false.
     *
     * @return $true if network simulation is working, $false otherwise
     * @pre $none
     * @post $none
     */
    boolean isNetworkEnabled();

    /**
     * @return the graph
     */
    TopologicalGraph getTopologycalGraph();

    /**
     * An attribute that implements the Null Object Design Pattern for {@link NetworkTopology}
     * objects.
     */
    NetworkTopology NULL = new NetworkTopology() {
        private final TopologicalGraph graph = new TopologicalGraph();

        @Override public void addLink(int srcId, int destId, double bw, double lat) {}
        @Override public void mapNode(int cloudSimEntityID, int briteID) {}
        @Override public void unmapNode(int cloudSimEntityID) {}
        @Override public double getDelay(int srcID, int destID) { return 0; }
        @Override public boolean isNetworkEnabled() { return false; }
        @Override public TopologicalGraph getTopologycalGraph() { return graph; }
    };

}
