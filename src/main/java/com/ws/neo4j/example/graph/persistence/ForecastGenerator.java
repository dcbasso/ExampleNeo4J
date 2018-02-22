/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.neo4j.example.graph.persistence;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.ws.neo4j.example.graph.model.Edge;
import com.ws.neo4j.example.graph.model.Genotype;
import com.ws.neo4j.example.graph.model.Node;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author dante.basso
 */
public class ForecastGenerator {
    
    private static final Integer BATCH_INSERT_LIMIT = 400;
    
    private boolean useThreadPoolInEdgeCreation = true; 
    
    private UUID uuidForecast;
    private Session sessionOGM;
    private Node nodeForecast;
    private Neo4jSessionFactory factory;
    
    private final Set<Node> mapForecast = new LinkedHashSet<>();
    private final Set<Node> mapCompany = new LinkedHashSet<>();
    private final Set<Node> mapProfessional = new LinkedHashSet<>();
    private final Set<Node> mapCustomer = new LinkedHashSet<>();
    private final Set<Node> mapProduct = new LinkedHashSet<>();
    private final HashMap<Long, List<Edge>> parentEdges = new HashMap<>();

    /**
     * This method will generate all nodes and edges for the forecast.
     * First all nodes are created and after the relation's (edges) will be created.
     *
     * @param companiesAmount - Amount of companies to generate
     * @param professionalsAmount - Amount of professionals to generate
     * @param customersAmount - Amount of customers to generate
     * @param productsAmount - Amount of products to generate
     * @param useThreadPoolInEdgeCreation - parameter to define if is to use edge insertion with threads or not.
     */
    public void generateForecast(Integer companiesAmount, Integer professionalsAmount, Integer customersAmount, Integer productsAmount, boolean useThreadPoolInEdgeCreation) {
        this.uuidForecast = UUID.randomUUID();
        this.useThreadPoolInEdgeCreation = useThreadPoolInEdgeCreation;     
        
        this.factory = new Neo4jSessionFactory();
        sessionOGM = factory.getSession();

        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            this.sessionOGM.beginTransaction();
            this.save(getForecast());
            this.saveNodesWithoutThread(Node.TYPE_COMPANY, companiesAmount, mapCompany);
            this.saveNodesWithoutThread(Node.TYPE_PROFESSIONAL, professionalsAmount, mapProfessional);
            this.saveNodesWithoutThread(Node.TYPE_CUSTOMER, customersAmount, mapCustomer);
            this.saveNodesWithoutThread(Node.TYPE_PRODUCT, productsAmount, mapProduct);
            this.sessionOGM.getTransaction().commit();
            if (this.useThreadPoolInEdgeCreation) {
                this.createEdgesWithThreadPool();
            } else {
                this.createEdgesWithoutThreadPool();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        stopwatch.stop();


        long timeElapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        final long hr = TimeUnit.MILLISECONDS.toHours(timeElapsed);
        final long min = TimeUnit.MILLISECONDS.toMinutes(timeElapsed - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(timeElapsed - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(timeElapsed - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        System.out.println("------------------------------------------");
        System.out.println("TOTAL TIME ELAPSED: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms (" +  String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms) + ")");
    }
    
    private void createEdgesWithThreadPool() throws InterruptedException {
        this.saveEdgesWithThread(createJoinEdges(mapForecast, mapCompany, parentEdges));
        this.saveEdgesWithThread(createJoinEdges(mapCompany, mapProfessional, parentEdges));
        this.saveEdgesWithThread(createJoinEdges(mapProfessional, mapCustomer, parentEdges));
        this.saveEdgesWithThread(createCartesianEdges(mapCustomer, mapProduct, parentEdges));
    }
           
    private void createEdgesWithoutThreadPool() throws InterruptedException {
        this.saveEdgesWithoutThread(createJoinEdges(mapForecast, mapCompany, parentEdges));
        this.saveEdgesWithoutThread(createJoinEdges(mapCompany, mapProfessional, parentEdges));
        this.saveEdgesWithoutThread(createJoinEdges(mapProfessional, mapCustomer, parentEdges));
        this.saveEdgesWithoutThread(createCartesianEdges(mapCustomer, mapProduct, parentEdges));
    }
    
    
    //--------------------------------------------------------------------
    
    /**
     * Create a Forecast Node
     *
     * @return Forecast {@link Node}
     */
    private Node getForecast() {
        if (nodeForecast == null) {
            nodeForecast = new Node();
            nodeForecast.setUuid(uuidForecast);
            nodeForecast.setType(Node.TYPE_FORECAST);
            nodeForecast.setDescription("Forecast Test OGM");
            nodeForecast.addLabel(uuidForecast.toString());
            mapForecast.add(nodeForecast);
        }
        return nodeForecast;
    }
    
    /**
     * Implementation to create a Node with some parameters
     *
     * @param index
     * @param type
     * @return new {@link Node}
     */
    private Node createNode(Integer index, String type) {
        Node node = new Node();
        node.setUuid(UUID.randomUUID());
        node.setType(type);
        node.setDescription(type + index);
        node.addLabel(getForecast().getUuid().toString());
        node.setSourceId(UUID.randomUUID());
        return node;
    }
    
    /**
     * Implementation to create a Edge with some parameters
     *
     * @param startNode
     * @param endNode
     * @param edgeParent
     * @return new {@link Edge}
     */
    private Edge createEdge(Node startNode, Node endNode, Edge edgeParent) {
        Edge edge = new Edge();
        edge.setUuid(UUID.randomUUID());
        edge.setStartNode(startNode);
        edge.setEndNode(endNode);
        edge.addLabel(getForecast().getUuid().toString());
        edge.setForecastId(getForecast().getUuid());
        edge.addMeasure("quantity", new BigDecimal(0));
        edge.addMeasure("value", new BigDecimal(0));
        edge.setGenotype(createGenotype(edge, edgeParent));
        return edge;
    }
    
    /**
     * Create cartesian edges to relate Customer with Product.
     *
     * @param startNodesMap
     * @param endNodesMap
     * @param parentEdges
     * @return List<Edge>
     */
    private List<Edge> createCartesianEdges(Set<Node> startNodesMap, Set<Node> endNodesMap, HashMap<Long, List<Edge>> parentEdges) {
        List<Edge> edgeList = new ArrayList<>();
        for (Node startNode : startNodesMap) {

            List<Edge> edgeParentList = parentEdges.get(startNode.getId());
            Iterator<Edge> itEdgesParent = edgeParentList.iterator();

            for (Node endNode : endNodesMap) {

                itEdgesParent = itEdgesParent.hasNext() ? itEdgesParent : edgeParentList.iterator();
                Edge edgeParent = itEdgesParent.next();

                Edge edge = createEdge(startNode, endNode, edgeParent);
                edgeList.add(edge);
            }
        }

        return edgeList;
    }
    
    /**
     * Create a genotype based on current Edge and his Parent.
     *
     * @param edge
     * @param edgeParent
     * @return new {@link Genotype}
     */
    private Genotype createGenotype(Edge edge, Edge edgeParent) {
        String[] genotype;
        if (edgeParent != null) {
            int size = edgeParent.getGenotype().getValue().length + 1;
            genotype = new String[size];
            System.arraycopy(edgeParent.getGenotype().getValue(), 0, genotype, 0, edgeParent.getGenotype().getValue().length);            
        } else {
            genotype = new String[1];
        }
        genotype[genotype.length - 1] = edge.getUuid().toString();
        return new Genotype(genotype);
    }
    
    /**
     * Create a Edges with Join.
     *
     * @param startNodes
     * @param endNodes
     * @param parentEdgesMap
     * @return List<Edge>
     */
    private List<Edge> createJoinEdges(Set<Node> startNodes, Set<Node> endNodes, HashMap<Long, List<Edge>> parentEdgesMap) {
        List<Edge> edgeList = new ArrayList<>();
        Iterator<Node> itStartNode = startNodes.iterator(), itEndNode = endNodes.iterator();

        HashMap<Long, Iterator<Edge>> parentEdgesIteratorMap = new HashMap<>();
        parentEdgesMap.forEach((key, value) -> parentEdgesIteratorMap.put(key, value.iterator()));

        while (itEndNode.hasNext()) {
            Node startNode = itStartNode.next();
            itStartNode = itStartNode.hasNext() ? itStartNode : startNodes.iterator();

            Node endNode = itEndNode.next();

            Iterator<Edge> itEdgesParent = parentEdgesIteratorMap.get(startNode.getId());
            Edge edgeParent = (itEdgesParent != null) ? itEdgesParent.next() : null;
            if (itEdgesParent != null && !itEdgesParent.hasNext()) {
                itEdgesParent = parentEdgesMap.get(startNode.getId()).iterator();
                parentEdgesIteratorMap.put(startNode.getId(), itEdgesParent);
            }

            Edge edge = createEdge(startNode, endNode, edgeParent);
            startNode.addEdge(edge);
            edgeList.add(edge);

            parentEdgesMap.putIfAbsent(endNode.getId(), new ArrayList<>());
            List<Edge> edges = parentEdgesMap.get(endNode.getId());
            edges.add(edge);
        }

        return edgeList;
    }
           
    //---------------------------------------------------------------------
    
    /**
     * Save nodes without {@link ThreadPoolExecutor}
     *
     * @param nodeType
     * @param numberOfInserts
     * @param map
     */
    private void saveNodesWithoutThread(String nodeType, Integer numberOfInserts, Set<Node> map) {
        Integer index;
        for (index = 0; index < numberOfInserts; index++) {
            Node node = createNode(index, nodeType);
            save(node);
            map.add(node);
        }
    }   
    
    /**
     * Save edges without {@link ThreadPoolExecutor}
     *
     * @param edgeList
     * @throws InterruptedException
     */
    private void saveEdgesWithoutThread(List<Edge> edgeList) throws InterruptedException {
        List<List<Edge>> listEdgeList = Lists.partition(edgeList, BATCH_INSERT_LIMIT);
        listEdgeList.forEach((edges) -> {
            save(edges);
        });
    }
    
    /**
     * Default implementation to save a list of edges
     *
     * @param edgeList
     */
    private void save(List<Edge> edgeList) {
        try {
            this.sessionOGM.beginTransaction();
            this.sessionOGM.save(edgeList);
            this.sessionOGM.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //stop the RequestContext to ensure that all request-scoped beans get cleaned up.
        }
    }
    
    /**
     * Default implementation to save a node
     *
     * @param node
     */
    private void save(Node node) {
        sessionOGM.save(node);
    }
    
    /**
     * Default implementation to save a list of edges
     *
     * @param edgeList
     */
    private void save(List<Edge> edgeList, Session sessionOGM) {
        sessionOGM.beginTransaction();
        sessionOGM.save(edgeList);
        sessionOGM.getTransaction().commit();
    }        
    
    /**
     * Save edges using {@link ThreadPoolExecutor}
     *
     * @param edgeList
     * @throws InterruptedException
     */
    private void saveEdgesWithThread(List<Edge> edgeList) throws InterruptedException {
        List<List<Edge>> listEdgeList = Lists.partition(edgeList, BATCH_INSERT_LIMIT);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        listEdgeList.stream().forEach(edges -> {
            executor.submit(() -> {
//                save(edges); //AVOID: using the same session make the performance decrease a lot.
                save(edges, factory.getSession());
            });

        });        
        listEdgeList.forEach((edges) -> {
            executor.submit(() -> {
//                save(edges); //AVOID: using the same session make the performance decrease a lot.
                save(edges, factory.getSession());
            });
        });
        executor.shutdown();
        // Wait for everything to finish.
        while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("Awaiting completion of threads.");
        }
    }        
    
}
